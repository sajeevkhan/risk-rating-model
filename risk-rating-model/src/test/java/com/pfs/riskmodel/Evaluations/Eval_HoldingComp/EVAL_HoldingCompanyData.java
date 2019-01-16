package com.pfs.riskmodel.Evaluations.Eval_HoldingComp;

import com.pfs.riskmodel.ModelTemplates.HoldingCompany.HoldingCompanyRiskModelSummary;
import com.pfs.riskmodel.ModelTemplates.HoldingCompany.ParentalNotchup.HC_RiskParentalNotchUp;
import com.pfs.riskmodel.ModelTemplates.HoldingCompany.RiskRatingModifiers.HC_RatingModifierDTO;
import com.pfs.riskmodel.ModelTemplates.HoldingCompany.RiskTypes.HoldingCompanyRiskTypes;
import com.pfs.riskmodel.dto.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 25-Dec-18.
 */
public class EVAL_HoldingCompanyData {



    RiskModelTemplateDTO riskModelTemplateDTO;

    public  RiskModelTemplateDTO buildRiskModelTemplate ( ) {


        riskModelTemplateDTO = new RiskModelTemplateDTO();


        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");

        //  9 - HoldingCompany
        riskModelTemplateDTO.setModelCategoryCode(9);
        riskModelTemplateDTO.setModelType(0); //Template

        riskModelTemplateDTO.setProjectRiskLevelCode("01");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Holding Company Risk Rating");

        riskModelTemplateDTO.setProjectTypeCode("05");
        riskModelTemplateDTO.setProjectTypeDescription("Holding Company");
        riskModelTemplateDTO.setDescription("Holding Company Risk Rating");
        riskModelTemplateDTO.setComputingMethodCode("05");
        riskModelTemplateDTO.setComputingMethodDescription("Equals");
        riskModelTemplateDTO.setScore(0D);

        riskModelTemplateDTO.setLoanNumber("122112");
        riskModelTemplateDTO.setLoanAmountInCrores(2000D);
        riskModelTemplateDTO.setProjectName("ABC Holding Co.");
        riskModelTemplateDTO.setRatingDate(Date.from(Instant.now()));

        riskModelTemplateDTO.setOverallProjectGrade(" ");
        riskModelTemplateDTO.setModifiedProjectGrade(" ");
        riskModelTemplateDTO.setAfterParentalNotchUpGrade(" ");
        riskModelTemplateDTO.setFinalProjectGrade(" ");


        // Risk Type
        // Holding Company Risk Rating
         RiskTypeDTO riskTypeDTO = new RiskTypeDTO();
         riskTypeDTO =  HoldingCompanyRiskTypes.buildRiskType();
         List<RiskTypeDTO> riskTypeDTOs = new ArrayList<>();
         riskTypeDTOs.add(riskTypeDTO);

         for (RiskTypeDTO riskTypeDTO1: riskTypeDTOs) {

             // Set TEST DATA for Risk Sub Factor Attributes per Risk Component per Risk Sub Factor
             for (RiskComponentDTO riskComponentDTO : riskTypeDTO1.getRiskComponents()) {
                 for (RiskFactorDTO riskFactorDTO : riskComponentDTO.getRiskFactors()) {
                     for (RiskSubFactorDTO riskSubFactorDTO : riskFactorDTO.getRiskSubFactors()) {

                         Boolean riskDeflator = false;
                         if (riskSubFactorDTO.getScoreTypeCode().equals("02") || riskSubFactorDTO.getScoreTypeCode().equals("03"))
                             riskDeflator = true;

                         for (RiskSubFactorAttributeDTO riskSubFactorAttributeDTO : riskSubFactorDTO.getRiskSubFactorAttributes()) {
                             // Select the second last attribute
                             Integer secondLastItem = riskSubFactorDTO.getRiskSubFactorAttributes().size() - 1;

                             // Select first attribute for Deflators and secondLastItem for Normal SubFactors
                             Integer itemNo = 1;
                             if (riskDeflator == true)
                                 itemNo = 1;
                             else
                                 itemNo = secondLastItem;


                             if (riskSubFactorAttributeDTO.getItemNo() == itemNo) {
                                 riskSubFactorAttributeDTO.setIsSelected(true);
                                 break;
                             }
                         }
                     }
                 }
             }
         }

        riskModelTemplateDTO.setRiskTypes(riskTypeDTOs);


        //Final Holding Company Rating
        //  Parental Notch Up
        HC_RiskParentalNotchUp hc_riskParentalNotchUp = new HC_RiskParentalNotchUp();
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = hc_riskParentalNotchUp.getParentalNotchUp();

        for (RiskParentalNotchUpConditionDTO riskParentalNotchUpConditionDTO: riskParentalNotchUpDTO.getRiskParentalConditions()) {

            // Obligor Rating Grade of the Parent Firm as per reference source
            if (riskParentalNotchUpConditionDTO.getItemNo() == 1) {
                riskParentalNotchUpConditionDTO.setValue("1");

            }
            //Borrower Rating Grade of the Parent Firm
            if (riskParentalNotchUpConditionDTO.getItemNo() == 2) {
                riskParentalNotchUpConditionDTO.setValue("1");

            }

//            3 - Is Parent's rating at GRADE 10
//            4 - Is Parent's Rating Better Than Borrower's Rating
//
            if (riskParentalNotchUpConditionDTO.getCategory() == 3) {
                riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('N');
            }

            if (riskParentalNotchUpConditionDTO.getCategory() == 4) {
                riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('Y');
            }
        }

        // Select First Item for Parental Notchup Sub Factor Attributes
        for (RiskSubFactorDTO riskSubFactorDTO: riskParentalNotchUpDTO.getRiskSubFactors()) {
            for (RiskSubFactorAttributeDTO riskSubFactorAttributeDTO: riskSubFactorDTO.getRiskSubFactorAttributes()){
                if (riskSubFactorAttributeDTO.getItemNo() == 1)
                    riskSubFactorAttributeDTO.setIsSelected(true);
            }
        }


        List<RiskParentalNotchUpDTO> riskParentalNotchUpDTOs = new ArrayList<>();
        riskParentalNotchUpDTOs.add(riskParentalNotchUpDTO);


        // Rating Modifier
        //Modified Holding Company Rating
        RiskRatingModifierDTO riskRatingModifierDTO = new RiskRatingModifierDTO();
        List<RiskRatingModifierDTO> riskRatingModifierDTOs = new ArrayList<>();
        HC_RatingModifierDTO hc_ratingModifierDTO = new HC_RatingModifierDTO();
        riskRatingModifierDTOs = hc_ratingModifierDTO.getRiskRatingModifierDTOs();


         riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOs);
         riskModelTemplateDTO.setRiskRatingModifiers(riskRatingModifierDTOs);





         HoldingCompanyRiskModelSummary holdingCompanyRiskModelSummary = new HoldingCompanyRiskModelSummary();
         List<RiskModelSummaryDTO> riskModelSummaryDTOS = holdingCompanyRiskModelSummary.getRiskModelSummary();
         riskModelTemplateDTO.setRiskModelSummaries(riskModelSummaryDTOS);

        return  riskModelTemplateDTO;

    }
}
