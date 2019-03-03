package com.pfs.riskmodel.ModelTemplates.HoldingCompany;

import com.pfs.riskmodel.ModelTemplates.HoldingCompany.ParentalNotchup.HC_RiskParentalNotchUp;
import com.pfs.riskmodel.ModelTemplates.HoldingCompany.RiskRatingModifiers.HC_RatingModifierDTO;
import com.pfs.riskmodel.ModelTemplates.HoldingCompany.RiskTypes.HoldingCompanyRiskTypes;
import com.pfs.riskmodel.ModelTemplates.ParentalNotchupTemplate;
import com.pfs.riskmodel.dto.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 25-Dec-18.
 */
public class HoldingCompanyData {



    RiskModelTemplateDTO riskModelTemplateDTO;

    public  RiskModelTemplateDTO buildRiskModelTemplate ( ) {


        riskModelTemplateDTO = new RiskModelTemplateDTO();


        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");


        riskModelTemplateDTO.setPurposeCode("01");
        riskModelTemplateDTO.setPurposeDescription("Project Assessment");
        riskModelTemplateDTO.setProcessInstanceId(" ");
        riskModelTemplateDTO.setWorkflowStatusCode("01");
        riskModelTemplateDTO.setWorkflowStatusDescription("Created");


        //  9 - HoldingCompany
        riskModelTemplateDTO.setModelCategoryCode(9);
        riskModelTemplateDTO.setModelType(0); //Template

        riskModelTemplateDTO.setProjectRiskLevelCode("01");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Holding Company Risk Rating");

        riskModelTemplateDTO.setRiskProjectTypeCode("05");
        riskModelTemplateDTO.setRiskProjectTypeDescription("Holding Company");
        riskModelTemplateDTO.setDescription("Holding Company Risk Rating");
        riskModelTemplateDTO.setComputingMethodCode("01");
        riskModelTemplateDTO.setComputingMethodDescription("Weighted");
        riskModelTemplateDTO.setScore(0D);

        riskModelTemplateDTO.setLoanNumber(" ");
        riskModelTemplateDTO.setLoanAmountInCrores(0D);
        riskModelTemplateDTO.setProjectName("Template Model for Holding Co.");
        riskModelTemplateDTO.setRatingDate(Date.from(Instant.now()));

        riskModelTemplateDTO.setOverallProjectGrade(" ");
        riskModelTemplateDTO.setModifiedProjectGrade(" ");
        riskModelTemplateDTO.setAfterParentalNotchUpGrade(" ");
        riskModelTemplateDTO.setFinalProjectGrade(" ");


        //Final Holding Company Rating

        //  Parental Notch Up
        ParentalNotchupTemplate parentalNotchupTemplate = new ParentalNotchupTemplate();
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = parentalNotchupTemplate.getParentalNotchUp();

        List<RiskParentalNotchUpDTO> riskParentalNotchUpDTOs = new ArrayList<>();
        riskParentalNotchUpDTOs.add(riskParentalNotchUpDTO);


        // Rating Modifier
        //Modified Holding Company Rating
        RiskRatingModifierDTO riskRatingModifierDTO = new RiskRatingModifierDTO();
        List<RiskRatingModifierDTO> riskRatingModifierDTOs = new ArrayList<>();
        HC_RatingModifierDTO hc_ratingModifierDTO = new HC_RatingModifierDTO();
        riskRatingModifierDTOs = hc_ratingModifierDTO.getRiskRatingModifierDTOs();


        // Risk Type
        // Holding Company Risk Rating
         RiskTypeDTO riskTypeDTO = new RiskTypeDTO();
         riskTypeDTO =  HoldingCompanyRiskTypes.buildRiskType();
         List<RiskTypeDTO> riskTypeDTOs = new ArrayList<>();
         riskTypeDTOs.add(riskTypeDTO);


         riskModelTemplateDTO.setRiskTypes(riskTypeDTOs);
         riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOs);
         riskModelTemplateDTO.setRiskRatingModifiers(riskRatingModifierDTOs);





         HoldingCompanyRiskModelSummary holdingCompanyRiskModelSummary = new HoldingCompanyRiskModelSummary();
         List<RiskModelSummaryDTO> riskModelSummaryDTOS = holdingCompanyRiskModelSummary.getRiskModelSummary();
         riskModelTemplateDTO.setRiskModelSummaries(riskModelSummaryDTOS);

        return  riskModelTemplateDTO;

    }
}
