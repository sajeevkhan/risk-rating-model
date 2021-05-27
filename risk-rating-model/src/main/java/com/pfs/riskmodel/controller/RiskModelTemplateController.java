package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.client.LMSEnquiryClient;
import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.dto.*;
import com.pfs.riskmodel.excel.RiskEvaluationReportExcelGen;
import com.pfs.riskmodel.repository.*;
import com.pfs.riskmodel.resource.LoanApplicationResource;
import com.pfs.riskmodel.resource.LoanNumberResource;
import com.pfs.riskmodel.service.IRiskModelService;
import com.pfs.riskmodel.service.IRiskModelTemplateService;
import com.pfs.riskmodel.service.modelvaluator.Utils;
import com.pfs.riskmodel.util.Check;
import com.pfs.riskmodel.util.CheckServiceResult;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class RiskModelTemplateController {


    @Autowired
    RiskModelTemplateRepository riskModelTemplateRepository;

    @Autowired
    RiskTypeRepository riskTypeRepository;

    @Autowired
    RiskComponentRepository riskComponentRepository;

    @Autowired
    ComputingMethodRepository computingMethodRepository;

    @Autowired
    RatingModifierComputingMethodRepository ratingModifierComputingMethodRepository;

    @Autowired
    ProjectRiskLevelRepository projectRiskLevelRepository;

    @Autowired
    RiskProjectTypeRepository riskProjectTypeRepository;

    @Autowired
    ScoreTypeRepository scoreTypeRepository;

    @Autowired
    RiskFactorRepository riskFactorRepository;

    @Autowired
    RiskSubFactorRepository riskSubFactorRepository;

    @Autowired
    ModelCategoryRepository modelCategoryRepository;

    @Autowired
    IRiskModelTemplateService riskModelTemplateService;

    @Autowired
    WorkflowStatusRepository workflowStatusRepository;

    @Autowired
    RiskPurposeRepository purposeRepository;

    @Autowired
    IRiskModelService riskModelService;

    @Autowired
    LMSEnquiryClient lmsEnquiryClient;


    //-----------------------------------------------------------------------------------------------------------------
    //                              RISK MODEL - VALUATIONS
    //      action 1 - Save only
    //      action 2 - Save and Send for Approval
    //      action 3 - Save and Approve
    //      action 4 - Save and Reject
    //-----------------------------------------------------------------------------------------------------------------
    @CrossOrigin
    @PostMapping("/riskModel")
    public ResponseEntity createRiskModel(@RequestBody RiskModelTemplateDTO riskModelTemplateDTO,
                                          @RequestParam(value = "action",required = true) Integer action,
                                          HttpServletRequest request) {

        RiskModelTemplate riskModelTemplate = mapDTOToDomain(riskModelTemplateDTO);


//              LoanNumberResource loanNumberResource = new LoanNumberResource();
//        loanNumberResource.setLoanNumber("10003205");
//        ResponseEntity<LoanApplicationResource> loanApplicationResourceResponseEntity;
//        try {
//            loanApplicationResourceResponseEntity
//                    = lmsEnquiryClient.getEnquiryByLoanNumber(loanNumberResource, getAuthorizationBearer(request.getUserPrincipal()));
//        } catch (  HTTPException httpException) {
//            System.out.println("HTTP Exception -> Get Loan by Loan Number:" + loanNumberResource.getLoanNumber() + ": " + httpException.getMessage() );
//            return null;
//        } catch (FeignException feignException) {
//            System.out.println("Feign Exception -> Get Loan by Loan Number:" + loanNumberResource.getLoanNumber()   +": " +  feignException.getMessage() );
//            return null;
//        }
//        LoanApplicationResource loanApplicationResource = loanApplicationResourceResponseEntity.getBody();





        Map<String, Object> result = riskModelService.createRiskModel(riskModelTemplate,action, request);
        CheckServiceResult.checkResult(result);


        riskModelTemplate = (RiskModelTemplate) result.get("RiskModel");
        RiskModelTemplateDTO riskModelTemplateDTOResponse = mapDomainToDTO(riskModelTemplate);

        return ResponseEntity.ok(riskModelTemplateDTOResponse);
    }

    @CrossOrigin
    @PutMapping("/riskModel")
    public ResponseEntity updateRiskModel(@RequestBody RiskModelTemplateDTO riskModelTemplateDTO,
                                          @RequestParam(value = "action",required = true) Integer action,
                                          HttpServletRequest request) {

        RiskModelTemplate riskModelTemplate = mapDTOToDomain(riskModelTemplateDTO);

        Map<String, Object> result = riskModelService.createRiskModel(riskModelTemplate,action, request);
        CheckServiceResult.checkResult(result);

        riskModelTemplate = (RiskModelTemplate) result.get("RiskModel");
        RiskModelTemplateDTO riskModelTemplateDTOResponse = mapDomainToDTO(riskModelTemplate);

        return ResponseEntity.ok(riskModelTemplateDTOResponse);
    }

    @CrossOrigin
    @PutMapping("/riskModel/process")
    public ResponseEntity updateRiskModel(@RequestParam(value = "id",required = true) Long id,
                                          @RequestParam(value = "action",required = true) Integer action,
                                          HttpServletRequest request) {

        RiskModelTemplate riskModelTemplate= (RiskModelTemplate) riskModelTemplateRepository.findById(id).get();

        Map<String, Object> result = riskModelService.createRiskModel(riskModelTemplate,action, request);
        CheckServiceResult.checkResult(result);

        riskModelTemplate = (RiskModelTemplate) result.get("RiskModel");
        RiskModelTemplateDTO riskModelTemplateDTOResponse = mapDomainToDTO(riskModelTemplate);

        return ResponseEntity.ok(riskModelTemplateDTOResponse);
    }








    //-----------------------------------------------------------------------------------------------------------------
    //                              RISK MODEL - TEMPLATES
    //-----------------------------------------------------------------------------------------------------------------

    @GetMapping("/riskModelTemplate/all")
    public ResponseEntity findAll (){


        List<RiskModelTemplateDTO> riskModelTemplateDTOS = new ArrayList<>();
        List<RiskModelTemplate> riskModelTemplates = new ArrayList<>();

        riskModelTemplates = riskModelTemplateRepository.findAll();

        riskModelTemplates.forEach(riskModelTemplate -> {
            RiskModelTemplateDTO riskModelTemplateDTO = mapDomainToDTO(riskModelTemplate);
            riskModelTemplateDTOS.add(riskModelTemplateDTO);
        });

        return ResponseEntity.ok(riskModelTemplateDTOS);
    }


    @GetMapping("/riskModel/loanNumber/{loanNumber}")
    public ResponseEntity findByLoanNumber (@PathVariable("loanNumber") String loanNumber,
                                            HttpServletRequest request){


        List<RiskModelTemplateDTO> riskModelTemplateDTOS = new ArrayList<>();
        List<RiskModelTemplate> riskModelTemplates = new ArrayList<>();

        riskModelTemplates = riskModelTemplateRepository.findByLoanNumber(loanNumber);

        riskModelTemplates.forEach(riskModelTemplate -> {
            RiskModelTemplateDTO riskModelTemplateDTO = mapDomainToDTO(riskModelTemplate);
            riskModelTemplateDTOS.add(riskModelTemplateDTO);
        });

        return ResponseEntity.ok(riskModelTemplateDTOS);
    }


    @GetMapping("riskModel/report")
    public ResponseEntity findByLoanNumberRiskProjectTypeProjectName(@RequestParam(required = false) String loanNumber,
                                                                     @RequestParam(required = false) String riskProjectTypeCode,
                                                                     @RequestParam(required = false) String projectName) {

        if (loanNumber != null && loanNumber.length() == 0 )
            loanNumber = null;
        if (riskProjectTypeCode != null && riskProjectTypeCode.length() == 0 )
            riskProjectTypeCode = null;
        if (projectName != null && projectName.length() == 0 )
            projectName = null;


        List<RiskModelReportDTO> riskModelTemplates
                    = riskModelTemplateService.findByLoanNumberAndRiskProjectTypeAndProjectName(loanNumber,riskProjectTypeCode,projectName);

        return ResponseEntity.ok(riskModelTemplates);
    }

    @GetMapping("riskModel/report/excel")
    public void generateExcelForFindByLoanNumberRiskProjectTypeProjectName(
            HttpServletResponse response,
            @RequestParam(required = false) String loanNumber,
            @RequestParam(required = false) String riskProjectTypeCode,
            @RequestParam(required = false) String projectName) throws IOException {

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=RiskEvaluationReport_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        if (loanNumber != null && loanNumber.length() == 0 )
            loanNumber = null;
        if (riskProjectTypeCode != null && riskProjectTypeCode.length() == 0 )
            riskProjectTypeCode = null;
        if (projectName != null && projectName.length() == 0 )
            projectName = null;


        List<RiskModelReportDTO> riskModelTemplates
                = riskModelTemplateService.findByLoanNumberAndRiskProjectTypeAndProjectName(loanNumber,riskProjectTypeCode,projectName);

        RiskEvaluationReportExcelGen riskEvaluationReportExcelGen = new RiskEvaluationReportExcelGen(riskModelTemplates);
        riskEvaluationReportExcelGen.export(response);
     }

    @GetMapping("/riskModel/loanEnquiryId/{loanEnquiryId}")
    public ResponseEntity findByLoanEnquiryId (@PathVariable("loanEnquiryId") String loanEnquiryId,
                                            HttpServletRequest request){


        List<RiskModelTemplateDTO> riskModelTemplateDTOS = new ArrayList<>();
        List<RiskModelTemplate> riskModelTemplates = new ArrayList<>();

        riskModelTemplates = riskModelTemplateRepository.findByLoanEnquiryId(loanEnquiryId);

        riskModelTemplates.forEach(riskModelTemplate -> {
            RiskModelTemplateDTO riskModelTemplateDTO = mapDomainToDTO(riskModelTemplate);
            riskModelTemplateDTOS.add(riskModelTemplateDTO);
        });

        return ResponseEntity.ok(riskModelTemplateDTOS);
    }



    @GetMapping("/riskModelTemplate/id/{id}")
    public ResponseEntity findOne (
                             @PathVariable("id") Long id,
                             HttpServletRequest request) {

        System.out.println("LOCALDATE --------- : " +LocalDate.now());


        RiskModelTemplate riskModelTemplate = new RiskModelTemplate();
        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();

        riskModelTemplate = riskModelTemplateRepository.getOne(id);
        Check.notNull(riskModelTemplate.getId(), "Exception.notFound",
                "RiskModelTemplate", id.toString());

        riskModelTemplateDTO =  mapDomainToDTO(riskModelTemplate);
        return ResponseEntity.ok(riskModelTemplateDTO);
    }

    @GetMapping("/riskModelTemplate")
    public ResponseEntity findByProjectTypeAndRiskLevel (@RequestParam(value = "projectType",required = true) String projectType,
                                                         @RequestParam(value = "projectRiskLevel",required = true) String projectRiskLevel,
                                                         HttpServletRequest request) {

        RiskModelTemplate riskModelTemplate = new RiskModelTemplate();
        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();

        Map<String, Object> result = riskModelTemplateService.findByProjectTypeAndRiskLevel(projectType,projectRiskLevel);
        CheckServiceResult.checkResult(result);


        riskModelTemplate = (RiskModelTemplate) result.get("RiskModelTemplate");

        riskModelTemplate.setRatingDate(DateTime.now().toDate());

        if (riskModelTemplate == null) {
            Check.notNull(riskModelTemplate.getId(), "Exception.notFound",
                    "RiskModelTemplate", projectType + ":" + projectRiskLevel);
        }else {
            Check.notNull(projectType, "Exception.notFound",
                    "RiskModelTemplate", projectType + ":" + projectRiskLevel);
        }

        riskModelTemplateDTO =  mapDomainToDTO(riskModelTemplate);

        riskModelTemplateDTO =  mapRiskTemplateDomainToDTO(riskModelTemplateDTO);
        return ResponseEntity.ok(riskModelTemplateDTO);
    }



    @CrossOrigin
    @PostMapping("/riskModelTemplate")
    public ResponseEntity create(@RequestBody RiskModelTemplateDTO riskModelTemplateDTO, HttpServletRequest request) {


        Boolean isAccountConductRiskApplicable = false;

        for (RiskTypeDTO  riskTypeDTO : riskModelTemplateDTO.getRiskTypes() ) {
            for (RiskComponentDTO riskComponentDTO: riskTypeDTO.getRiskComponents()) {
                if (riskComponentDTO.getDescription().contains("Account Conduct") == true) {
                    isAccountConductRiskApplicable = true;
                    break;
                }
            }
            if (isAccountConductRiskApplicable == true)
            riskTypeDTO.setIsAccountConductRiskComponentPresent(true);
        }

        RiskModelTemplate riskModelTemplate = mapDTOToDomain(riskModelTemplateDTO);

        Map<String, Object> result = riskModelTemplateService.createRiskModelTemplate(riskModelTemplate);
        CheckServiceResult.checkResult(result);


        riskModelTemplate = (RiskModelTemplate) result.get("RiskModelTemplate");
        RiskModelTemplateDTO riskModelTemplateDTOResponse = mapDomainToDTO(riskModelTemplate);

        return ResponseEntity.ok(riskModelTemplateDTOResponse);
    }

    @CrossOrigin
    @PutMapping("/riskModelTemplate")
    public ResponseEntity update(@RequestBody RiskModelTemplateDTO riskModelTemplateDTO,
                                 HttpServletRequest request) {

        RiskModelTemplate riskModelTemplate  = mapDTOToDomain(riskModelTemplateDTO);

        Map<String, Object> result = riskModelTemplateService.updateRiskModelTemplate(riskModelTemplate);
        CheckServiceResult.checkResult(result);

        riskModelTemplate = (RiskModelTemplate) result.get("RiskModelTemplate");

        Check.notNull(riskModelTemplate.getId(), "Exception.IdNull",
                "RiskModelTemplate");

        RiskModelTemplateDTO riskModelTemplateDTOResponse = mapDomainToDTO(riskModelTemplate);
        return ResponseEntity.ok(riskModelTemplateDTOResponse);


    }



    public String getAuthorizationBearer(Principal user) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) ((OAuth2Authentication) user).getDetails();
        return "Bearer " + details.getTokenValue();
    }

    //-----------------------------------------------------------------------------------------------------------------
    //                              DTO-> DOMAIN -> DTO Mappers
    //-----------------------------------------------------------------------------------------------------------------



    /*
       MAP Domain to DTO   */
     private RiskModelTemplateDTO mapDomainToDTO (RiskModelTemplate riskModelTemplate) {


        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();

        // Sort Risk Model Template
        riskModelTemplate = sortRiskModelTemplate(riskModelTemplate);

        DozerBeanMapper mapper = new DozerBeanMapper();
        riskModelTemplateDTO = mapper.map(riskModelTemplate, RiskModelTemplateDTO.class);

        riskModelTemplateDTO.setPurposeCode(riskModelTemplate.getPurpose().getCode());
        riskModelTemplateDTO.setPurposeDescription(riskModelTemplate.getPurpose().getDescription());

        riskModelTemplateDTO.setWorkflowStatusCode(riskModelTemplate.getWorkflowStatus().getCode());
        riskModelTemplateDTO.setWorkflowStatusDescription(riskModelTemplate.getWorkflowStatus().getDescription());

        riskModelTemplateDTO.setModelCategoryCode(riskModelTemplate.getModelCategory().getCode());
        riskModelTemplateDTO.setModelCategoryDescription(riskModelTemplate.getModelCategory().getValue());

        String computingMethodCode =  riskModelTemplate.getComputingMethod().getCode();
        riskModelTemplateDTO.setComputingMethodCode(computingMethodCode);
        riskModelTemplateDTO.setComputingMethodDescription(riskModelTemplate.getComputingMethod().getValue());

        String projectTypeCode = riskModelTemplate.getRiskProjectType().getCode();
        riskModelTemplateDTO.setRiskProjectTypeCode(projectTypeCode);
        riskModelTemplateDTO.setRiskProjectTypeDescription(riskModelTemplate.getRiskProjectType().getValue());


        String projectRiskLevelCode = riskModelTemplate.getProjectRiskLevel().getCode();
        riskModelTemplateDTO.setProjectRiskLevelCode(projectRiskLevelCode);
        riskModelTemplateDTO.setProjectRiskLevelDescription(riskModelTemplate.getProjectRiskLevel().getValue());


         for (RiskTypeDTO riskTypeDTO: riskModelTemplateDTO.getRiskTypes() ) {
             for (RiskComponentDTO riskComponentDTO : riskTypeDTO.getRiskComponents()) {

                 RiskComponent riskComponent = riskComponentRepository.getOne(riskComponentDTO.getId());

                  riskComponentDTO.setComputingMethodCode(  riskComponent.getComputingMethod().getCode());
                  riskComponentDTO.setComputingMethodDescription(riskComponent.getComputingMethod().getValue());
                  riskComponentDTO.setScoreTypeCode(riskComponent.getScoreType().getCode());
                  riskComponentDTO.setScoreTypeDescription(riskComponent.getScoreType().getDescription());

                  riskComponentDTO.setScore(Utils.round(riskComponentDTO.getScore()));

                  for (RiskFactorDTO riskFactorDTO: riskComponentDTO.getRiskFactors()) {

                      riskFactorDTO.setComputingMethodCode(riskFactorRepository.getOne(riskFactorDTO.getId()).getComputingMethod().getCode());
                      riskFactorDTO.setComputingMethodDescription(riskFactorRepository.getOne(riskFactorDTO.getId()).getComputingMethod().getValue());

                      riskFactorDTO.setScoreTypeCode(riskFactorRepository.getOne(riskFactorDTO.getId()).getScoreType().getCode());
                      riskFactorDTO.setScoreTypeDescription(riskFactorRepository.getOne(riskFactorDTO.getId()).getScoreType().getDescription());

                      for (RiskSubFactorDTO riskSubFactorDTO: riskFactorDTO.getRiskSubFactors()) {
                          riskSubFactorDTO.setScoreTypeCode( riskSubFactorRepository.getOne(riskSubFactorDTO.getId()).getScoreTypeCode());
                          riskSubFactorDTO.setScoreTypeDescription( riskSubFactorRepository.getOne(riskSubFactorDTO.getId()).getScoreTypeDescription());
                      }
                  }
             }

        }


        return riskModelTemplateDTO;

    }

    /*
        Map DTO to Domain
     */

    private  RiskModelTemplate mapDTOToDomain(RiskModelTemplateDTO riskModelTemplateDTO) {

        RiskModelTemplate riskModelTemplate = new RiskModelTemplate();

        DozerBeanMapper mapper = new DozerBeanMapper();
        riskModelTemplate = mapper.map(riskModelTemplateDTO, RiskModelTemplate.class);

        riskModelTemplate.setPurpose(purposeRepository.findByCode(riskModelTemplateDTO.getPurposeCode()));
        riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode(riskModelTemplateDTO.getWorkflowStatusCode()));



        for (RiskType riskType: riskModelTemplate.getRiskTypes()) {

            for (RiskComponent riskComponent: riskType.getRiskComponents()) {

                riskComponent.setComputingMethod(computingMethodRepository.findByCode(riskComponent.getComputingMethodCode()));
                riskComponent.setComputingMethodCode(computingMethodRepository.findByCode(riskComponent.getComputingMethodCode()).getCode());

                riskComponent.setScoreType(scoreTypeRepository.findByCode(riskComponent.getScoreTypeCode()));
                riskComponent.setScoreTypeCode(scoreTypeRepository.findByCode(riskComponent.getScoreTypeCode()).getCode());

                for (RiskFactor riskFactor: riskComponent.getRiskFactors()) {

                    riskFactor.setComputingMethod(computingMethodRepository.findByCode(riskFactor.getComputingMethodCode()));
                    riskFactor.setComputingMethodCode(computingMethodRepository.findByCode(riskFactor.getComputingMethodCode()).getCode());

                    riskFactor.setScoreType(scoreTypeRepository.findByCode(riskFactor.getScoreTypeCode()));
                    riskFactor.setScoreTypeCode(scoreTypeRepository.findByCode(riskFactor.getScoreTypeCode()).getCode());

                    for (RiskSubFactor riskSubFactor: riskFactor.getRiskSubFactors()) {

                        if (riskSubFactor.getWeightage() == null) {
                            System.out.println(" NULL Risk Sub Factor Weightage:" + riskSubFactor.getDescription());

                        }

                        riskSubFactor.setScoreType(scoreTypeRepository.findByCode(riskSubFactor.getScoreTypeCode()));
                        try {
                            riskSubFactor.setScoreTypeCode(scoreTypeRepository.findByCode(riskSubFactor.getScoreTypeCode()).getCode());
                        }
                        catch ( Exception ex) {
                            System.out.println(" ");
                        }

                        if (riskSubFactor.getRiskSubFactorAttributes() == null){

                            System.out.println(" NULL Risk Sub Factor Attributes:" + riskSubFactor.getDescription());
                        }

                        for (RiskSubFactorAttribute riskSubFactorAttribute: riskSubFactor.getRiskSubFactorAttributes() ){

                            if (riskSubFactorAttribute.getWeightage() == null){
                                System.out.println(" NULL Risk Sub Factor Attribute Weightage:" + riskSubFactorAttribute.getDescription());
                            }
                        }
                    }
                }


                // Rating Modifiers
                if (riskModelTemplate.getRiskRatingModifiers() != null ) {
                    for (RiskRatingModifier riskRatingModifier : riskModelTemplate.getRiskRatingModifiers()) {
                        riskRatingModifier.setComputingMethod(ratingModifierComputingMethodRepository.findByCode(riskRatingModifier.getComputingMethodCode()));
                    }
                }

                // Parental Notchup
                if (riskModelTemplate.getRiskParentalNotchUps() != null) {
                    for (RiskParentalNotchUp riskParentalNotchUp : riskModelTemplate.getRiskParentalNotchUps()) {

                        for (RiskSubFactor riskSubFactor : riskParentalNotchUp.getRiskSubFactors()) {
                            riskSubFactor.setScoreType(scoreTypeRepository.findByCode(riskSubFactor.getScoreTypeCode()));
                        }
                    }
                }


            }
        }

        riskModelTemplate.setModelCategory(modelCategoryRepository.findByCode(riskModelTemplateDTO.getModelCategoryCode()));
        riskModelTemplate.setComputingMethod(computingMethodRepository.findByCode(riskModelTemplateDTO.getComputingMethodCode()));
        riskModelTemplate.setProjectRiskLevel(projectRiskLevelRepository.findByCode(riskModelTemplateDTO.getProjectRiskLevelCode()));
        riskModelTemplate.setRiskProjectType(riskProjectTypeRepository.findByCode(riskModelTemplateDTO.getRiskProjectTypeCode()));

        riskModelTemplate = sortRiskModelTemplate(riskModelTemplate);

        return riskModelTemplate;
    }




    private RiskModelTemplate sortRiskModelTemplate (RiskModelTemplate riskModelTemplate) {


        for (RiskType riskType: riskModelTemplate.getRiskTypes()) {

            for (RiskComponent riskComponent: riskType.getRiskComponents()) {
                for (RiskFactor riskFactor: riskComponent.getRiskFactors()) {
                    for (RiskSubFactor riskSubFactor: riskFactor.getRiskSubFactors()) {

                        // Sort RiskSubFactorAttributes
                        List<RiskSubFactorAttribute> riskSubFactorAttributes = riskSubFactor.getRiskSubFactorAttributes();
                        Collections.sort(riskSubFactorAttributes,
                                                (a, b) -> a.getItemNo().compareTo(b.getItemNo()));
                        riskSubFactor.setRiskSubFactorAttributes(riskSubFactorAttributes);
                    }

                    // Sort RiskSubFactors
                    List<RiskSubFactor> riskSubFactors = riskFactor.getRiskSubFactors();
                    Collections.sort(riskSubFactors,
                            (a, b) -> a.getItemNo().compareTo(b.getItemNo()));
                    riskFactor.setRiskSubFactors(riskSubFactors);
                }

                //Sort RiskFactors
                List<RiskFactor> riskFactors = riskComponent.getRiskFactors();
                Collections.sort(riskFactors,
                        (a, b) -> a.getItemNo().compareTo(b.getItemNo()));

                riskComponent.setRiskFactors(riskFactors);
            }

            //Sort RiskComponents
            List<RiskComponent> riskComponents = riskType.getRiskComponents();
            Collections.sort(riskComponents,
                    (a, b) -> a.getItemNo().compareTo(b.getItemNo()));
            riskType.setRiskComponents(riskComponents);
        }

        //Sort Risk Types
        List<RiskType> riskTypes = riskModelTemplate.getRiskTypes();
        Collections.sort(riskTypes,
                (a, b) -> a.getItemNo().compareTo(b.getItemNo()));
        riskModelTemplate.setRiskTypes(riskTypes);

        return riskModelTemplate;
    }




    /*
   MAP Domain to DTO   */
    private RiskModelTemplateDTO mapRiskTemplateDomainToDTO (RiskModelTemplateDTO riskModelTemplateDTO) {


        riskModelTemplateDTO.setId(null);

        Integer iRiskTypeItemNo = 1;
        for (RiskTypeDTO riskTypeDTO: riskModelTemplateDTO.getRiskTypes() ) {

            riskTypeDTO.setItemNo(iRiskTypeItemNo);
            iRiskTypeItemNo += 1;
            riskTypeDTO.setId(null);

            Integer iRiskComponentItemNo = 1;
            for (RiskComponentDTO riskComponentDTO : riskTypeDTO.getRiskComponents()) {
                riskComponentDTO.setItemNo(iRiskComponentItemNo);
                riskComponentDTO.setId(null);
                iRiskComponentItemNo += 1;

                Integer iRiskFactorItemNo = 1;
                for (RiskFactorDTO riskFactorDTO: riskComponentDTO.getRiskFactors()) {
                    riskFactorDTO.setId(null);
                    riskFactorDTO.setItemNo(iRiskFactorItemNo);
                    iRiskFactorItemNo +=1;

                    Integer iRiskSubFactorItemNo = 1;
                    for (RiskSubFactorDTO riskSubFactorDTO: riskFactorDTO.getRiskSubFactors()) {
                        riskSubFactorDTO.setItemNo(iRiskSubFactorItemNo);
                        riskSubFactorDTO.setId(null);
                        iRiskSubFactorItemNo += 1;

                        Integer iRiskSubFactorAttrItemNo = 1;
                        for (RiskSubFactorAttributeDTO riskSubFactorAttributeDTO: riskSubFactorDTO.getRiskSubFactorAttributes()) {

                            riskSubFactorAttributeDTO.setItemNo(iRiskSubFactorAttrItemNo);
                            riskSubFactorAttributeDTO.setId(null);
                            iRiskSubFactorAttrItemNo += 1;
                        }
                     }
                }
            }

        }

        for (RiskParentalNotchUpDTO riskParentalNotchUpDTO: riskModelTemplateDTO.getRiskParentalNotchUps()) {
            riskParentalNotchUpDTO.setId(null);

            Integer iRiskParentalNotcuUpConditionItemNo = 1;
            for (RiskParentalNotchUpConditionDTO riskParentalNotchUpConditionDTO
                                    : riskParentalNotchUpDTO.getRiskParentalConditions()) {

                riskParentalNotchUpConditionDTO.setItemNo(iRiskParentalNotcuUpConditionItemNo);
                riskParentalNotchUpConditionDTO.setId(null);
                iRiskParentalNotcuUpConditionItemNo  += 1;
            }


            Integer iRiskParentalNotcuUpSubFactorItemNo = 1;
            for (RiskSubFactorDTO riskSubFactorDTO: riskParentalNotchUpDTO.getRiskSubFactors()) {
                riskSubFactorDTO.setId(null);
                riskSubFactorDTO.setItemNo(iRiskParentalNotcuUpSubFactorItemNo);
                iRiskParentalNotcuUpSubFactorItemNo += 1;

                Integer iRiskParentalNotcuUpSubFactorAttrItemNo = 1;
                for(RiskSubFactorAttributeDTO riskSubFactorAttributeDTO: riskSubFactorDTO.getRiskSubFactorAttributes()) {

                   riskSubFactorAttributeDTO.setItemNo(iRiskParentalNotcuUpSubFactorAttrItemNo);
                    riskSubFactorAttributeDTO.setId(null);
                    iRiskParentalNotcuUpSubFactorAttrItemNo += 1;
                }
            }
        }


        Integer iRiskRatingModifierItemNo = 1;
        for (RiskRatingModifierDTO riskRatingModifierDTO: riskModelTemplateDTO.getRiskRatingModifiers()) {

            riskRatingModifierDTO.setItemNo(iRiskRatingModifierItemNo);
            riskRatingModifierDTO.setId(null);
            iRiskRatingModifierItemNo += 1;

            Integer iRiskRatingModifierAttrItemNo = 1;
            for (RiskRatingModifierAttributeDTO riskRatingModifierAttributeDTO: riskRatingModifierDTO.getRiskRatingModifierAttributes()) {
                riskRatingModifierAttributeDTO.setId(null);
                riskRatingModifierAttributeDTO.setItemNo(iRiskRatingModifierAttrItemNo);
                iRiskRatingModifierAttrItemNo += 1;
            }

        }

        return riskModelTemplateDTO;

    }


}
