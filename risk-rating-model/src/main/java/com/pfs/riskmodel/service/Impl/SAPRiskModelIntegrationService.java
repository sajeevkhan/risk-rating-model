package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.repository.RiskModelTemplateRepository;
import com.pfs.riskmodel.resource.*;
import com.pfs.riskmodel.service.IRiskModelService;
import com.pfs.riskmodel.service.IRiskModelTemplateService;
import com.pfs.riskmodel.service.ISAPRiskModelIntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.CriteriaBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


@Slf4j
@Service
@RequiredArgsConstructor
public class SAPRiskModelIntegrationService implements ISAPRiskModelIntegrationService {

    @Value("${sap.postUrl}")
    private String postURL;

    @Autowired
    private RiskModelTemplateRepository riskModelTemplateRepository;

    @Autowired
    private IRiskModelTemplateService iRiskModelService;


    public String fetchCSRFToken() {


        HttpHeaders headers = new HttpHeaders() {
            {
                String auth = "sajeev" + ":" + "sapsap";
                byte[] encodedAuth = Base64.encodeBase64(
                        auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
        headers.add("X-Csrf-Token", "Fetch ");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        System.out.println("THE REQUEST : " + requestEntity.toString());

        URI uri = null;
        try {
            uri = new URI("http://192.168.1.203:8000/sap/opu/odata/sap/ZPFS_LOAN_ENQ_PORTAL_SRV/?sap-client=300$metadata");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("THE URI : " + uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        System.out.println("Headers: " + responseEntity.getHeaders());
        System.out.println("Result - status (" + responseEntity.getStatusCode() + ") has body: " + responseEntity.hasBody());
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println("THE STATUS CODE: " + statusCode);

        System.out.println(responseEntity.getHeaders().get("x-csrf-token").get(0));

        return responseEntity.getHeaders().get("x-csrf-token").get(0);
    }

    public RiskEvaluationInSAP mapRiskModelToSAPModel(RiskModelTemplate riskModel) {


        RiskEvaluationInSAP riskEvaluationInSAP = new RiskEvaluationInSAP();

        riskEvaluationInSAP.RiskEvalId = riskModel.getId().toString();
        riskEvaluationInSAP.LoanContractId = riskModel.getLoanNumber();

        riskEvaluationInSAP.RiskPrjType = riskModel.getProjectRiskLevel().getCode();
        riskEvaluationInSAP.RiskPrjTypeT = riskModel.getRiskProjectType().getValue();
        riskEvaluationInSAP.ProjectName = riskModel.getProjectName();
        riskEvaluationInSAP.RiskPrjPhase = riskModel.getProjectRiskLevel().getValue();

        java.sql.Timestamp timestamp = new java.sql.Timestamp(riskModel.getRatingDate().getDate());

        LocalDate date = LocalDate.now();
        riskEvaluationInSAP.RatingDate = date.toString();

        riskEvaluationInSAP.CurrDepartment = riskModel.getPurposeDescription();
        riskEvaluationInSAP.InitiatedBy = riskModel.getCreatedByUserId();
        if (riskModel.getFirstLevelApprover() != null)
            riskEvaluationInSAP.FirstLvlApprover = riskModel.getFirstLevelApprover();
        else
            riskEvaluationInSAP.FirstLvlApprover = " ";

        if (riskModel.getSecondLevelApprover() != null)
            riskEvaluationInSAP.SecondLvlApprover = riskModel.getSecondLevelApprover();
        else
            riskEvaluationInSAP.SecondLvlApprover = " ";

        if (riskModel.getThirdLevelApprover() != null)
        riskEvaluationInSAP.ThirdLvlApprover = riskModel.getThirdLevelApprover();
        else
            riskEvaluationInSAP.ThirdLvlApprover = " ";

        if (riskModel.getReviewedBy() != null)
        riskEvaluationInSAP.LatestReviewer = riskModel.getReviewedBy();
        else
            riskEvaluationInSAP.LatestReviewer = " ";

        riskEvaluationInSAP.WfStatusDesc = riskModel.getWorkflowStatus().getDescription();

        riskEvaluationInSAP.FinalGrade = riskModel.getFinalProjectGrade();

        RiskEvaluationScore riskEvaluationScore = new RiskEvaluationScore();

        Integer ratingTypeId = 0;
        Double score = 0D; ;
        DecimalFormat df = new DecimalFormat("#.##");




        for (RiskType riskType : riskModel.getRiskTypes()) {
            riskEvaluationScore = new RiskEvaluationScore();
            ratingTypeId++;
            riskEvaluationScore.RiskEvalId = riskModel.getId().toString();
            riskEvaluationScore.RatingType = riskType.getDescription();
            riskEvaluationScore.RiskRatingId = ratingTypeId.toString();
            riskEvaluationScore.Grade = riskType.getGrade();

            score = Double.valueOf(df.format(riskType.getScore()));
            riskEvaluationScore.Score = score.toString();

            riskEvaluationInSAP.RiskEvaluation_OverallScore_Nav.add(riskEvaluationScore);
            ratingTypeId++;

            for (RiskComponent riskComponent : riskType.getRiskComponents()) {
                RiskEvaluationComponentScore riskEvaluationComponentScore = new RiskEvaluationComponentScore();
                riskEvaluationComponentScore.RiskEvalId = riskModel.getId().toString();

                riskEvaluationComponentScore.ProjectPhase = riskType.getDescription();
                riskEvaluationComponentScore.ComponentId = riskComponent.getId().toString();
                riskEvaluationComponentScore.ComponentName = riskComponent.getDescription();
                score = Double.valueOf(df.format(riskComponent.getScore()));
                riskEvaluationComponentScore.Score = score.toString();
                riskEvaluationInSAP.RiskEvaluation_ComponentScore_Nav.add(riskEvaluationComponentScore);
            }

        }


//      Overall Project Rating
        if (riskModel.getScore() != null) {
            riskEvaluationScore = new RiskEvaluationScore();
            ratingTypeId++;
            riskEvaluationScore.RiskEvalId = riskModel.getId().toString();
            riskEvaluationScore.RatingType = "Overall Project Rating";
            riskEvaluationScore.RiskRatingId = ratingTypeId.toString();
            riskEvaluationScore.Grade = riskModel.getOverallProjectGrade();
            if (riskModel.getScore() != null) {
                score = Double.valueOf(df.format(riskModel.getScore()));
                riskEvaluationScore.Score = score.toString();

            }
            else
                riskEvaluationScore.Score = " ";

            riskEvaluationInSAP.RiskEvaluation_OverallScore_Nav.add(riskEvaluationScore);
        }

//      Overall Project Rating
        if (riskModel.getOverallProjectGrade() != null) {
            riskEvaluationScore = new RiskEvaluationScore();
            ratingTypeId++;
            riskEvaluationScore.RiskEvalId = riskModel.getId().toString();
            riskEvaluationScore.RatingType = "Overall Project Grade";
            riskEvaluationScore.RiskRatingId = ratingTypeId.toString();
            riskEvaluationScore.Grade = riskModel.getOverallProjectGrade();
            riskEvaluationScore.Score = "";
            riskEvaluationInSAP.RiskEvaluation_OverallScore_Nav.add(riskEvaluationScore);
        }

        if (riskModel.getModifiedProjectGrade() != null) {
            riskEvaluationScore = new RiskEvaluationScore();
            ratingTypeId++;
            riskEvaluationScore.RiskEvalId = riskModel.getId().toString();
            riskEvaluationScore.RatingType = "Modified Project Grade";
            riskEvaluationScore.RiskRatingId = ratingTypeId.toString();
            riskEvaluationScore.Grade = riskModel.getModifiedProjectGrade();
            riskEvaluationScore.Score = " ";
            riskEvaluationInSAP.RiskEvaluation_OverallScore_Nav.add(riskEvaluationScore);
        }

        if (riskModel.getAfterParentalNotchUpGrade() != null) {
            riskEvaluationScore = new RiskEvaluationScore();
            ratingTypeId++;
            riskEvaluationScore.RiskEvalId = riskModel.getId().toString();
            riskEvaluationScore.RatingType = "After Parental Notchup Project Grade";
            riskEvaluationScore.RiskRatingId = ratingTypeId.toString();
            riskEvaluationScore.Grade = riskModel.getAfterParentalNotchUpGrade();
            riskEvaluationScore.Score = " ";
            riskEvaluationInSAP.RiskEvaluation_OverallScore_Nav.add(riskEvaluationScore);
        }

        if (riskModel.getFinalProjectGrade() != null) {
            riskEvaluationScore = new RiskEvaluationScore();
            ratingTypeId++;
            riskEvaluationScore.RiskEvalId = riskModel.getId().toString();
            riskEvaluationScore.RatingType = "Final Project Grade";
            riskEvaluationScore.RiskRatingId = ratingTypeId.toString();
            riskEvaluationScore.Grade = riskModel.getFinalProjectGrade();
            riskEvaluationScore.Score = " ";
            riskEvaluationInSAP.RiskEvaluation_OverallScore_Nav.add(riskEvaluationScore);

        }


        return riskEvaluationInSAP;

    }




    @Override
    public RiskEvaluationInSAP postRiskModelInSAP(RiskModelTemplate riskModel ) {


        // Map Risk Model to SAP Risk Model Summary Object
        RiskEvaluationInSAP riskEvaluationInSAP = mapRiskModelToSAPModel(riskModel );


        HttpHeaders headers = new HttpHeaders() {
            {
                String auth = "sajeev" + ":" + "sapsap";
                byte[] encodedAuth = Base64.encodeBase64(
                        auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
                setContentType(MediaType.APPLICATION_JSON);
                add("X-Requested-With", "X");
            }
        };

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskEvaluationInSAP> riskEvaluation = null;
        HttpEntity<RiskEvaluationInSAP> requestToPost = new HttpEntity<RiskEvaluationInSAP>(riskEvaluationInSAP, headers);


        try {
            riskEvaluation = restTemplate.exchange(postURL, HttpMethod.POST, requestToPost, RiskEvaluationInSAP.class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        HttpStatus statusCode = riskEvaluation.getStatusCode();
        RiskEvaluationInSAP riskEvaluationInSAPReponse = riskEvaluation.getBody();


        riskModelTemplateRepository.save(riskModel);


        return riskEvaluationInSAPReponse;

    }

    @Override
    public void putRiskModelInSAP(RiskModelTemplate riskModelTemplate) {

    }

    @Override
    public RiskEvaluationInSAP replicateRiskModelInSAP(RiskEvaluationInSAP riskEvaluationInSAP) {
        this.postSAPRiskModelInSAP(riskEvaluationInSAP);
        return riskEvaluationInSAP;

    }


    private RiskEvaluationInSAP postSAPRiskModelInSAP(RiskEvaluationInSAP riskEvaluationInSAP) {



        HttpHeaders headers = new HttpHeaders() {
            {
                String auth = "sajeev" + ":" + "sapsap";
                byte[] encodedAuth = Base64.encodeBase64(
                        auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
                setContentType(MediaType.APPLICATION_JSON);
                add("X-Requested-With", "X");
            }
        };

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskEvaluationInSAP> riskEvaluation = null;
        HttpEntity<RiskEvaluationInSAP> requestToPost = new HttpEntity<RiskEvaluationInSAP>(riskEvaluationInSAP, headers);


        try {
            riskEvaluation = restTemplate.exchange(postURL, HttpMethod.POST, requestToPost, RiskEvaluationInSAP.class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        HttpStatus statusCode = riskEvaluation.getStatusCode();
        RiskEvaluationInSAP riskEvaluationInSAPReponse = riskEvaluation.getBody();


        return riskEvaluationInSAPReponse;

    }


}
