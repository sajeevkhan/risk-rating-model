package com.pfs.riskmodel.Evaluations.Renewables;

import com.pfs.riskmodel.ModelTemplates.Renewable.OperationalPhase.Renewable_OperationalPhase_RiskModelSummary;
import com.pfs.riskmodel.ModelTemplates.Renewable.ParentalNotchUp.Renewable_RiskParentalNotchUp;
import com.pfs.riskmodel.ModelTemplates.Renewable.RiskRatingModifiers.Renewable_RatingModifierDTO;
import com.pfs.riskmodel.ModelTemplates.Renewable.RiskTypes.RenewablesOperatonalRiskTypes;
import com.pfs.riskmodel.dto.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 29-Dec-18.
 */
public class Evaluate_Renewables_OperationalPhaseData {

    RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();


    public RiskModelTemplateDTO getRenewablesOperationalPhaseData() {


        riskModelTemplateDTO = new RiskModelTemplateDTO();

        //        Overall Project Score:        Minimum of PIR and PPIR
        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        // Model Category 2: Renewable-Operational
        riskModelTemplateDTO.setModelCategoryCode(2);

        riskModelTemplateDTO.setModelType(1); //Valuation - NOT TEMPLATE

        riskModelTemplateDTO.setPurposeCode("01");
        riskModelTemplateDTO.setPurposeDescription("Project Assessment");
        riskModelTemplateDTO.setProcessInstanceId(" ");
        riskModelTemplateDTO.setWorkflowStatusCode("01");
        riskModelTemplateDTO.setWorkflowStatusDescription("Created");


        riskModelTemplateDTO.setProjectRiskLevelCode("02");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Operational Phase");

        riskModelTemplateDTO.setRiskProjectTypeCode("01");
        riskModelTemplateDTO.setRiskProjectTypeDescription("Renewables");

        riskModelTemplateDTO.setDescription("Renewables Operational Phase");
        riskModelTemplateDTO.setComputingMethodCode("05");
        riskModelTemplateDTO.setComputingMethodDescription("Equals");
        riskModelTemplateDTO.setScore(0D);

        riskModelTemplateDTO.setOverallProjectGrade(" ");
        riskModelTemplateDTO.setModifiedProjectGrade(" ");
        riskModelTemplateDTO.setAfterParentalNotchUpGrade(" ");
        riskModelTemplateDTO.setFinalProjectGrade(" ");



        riskModelTemplateDTO.setLoanNumber("200000");
        riskModelTemplateDTO.setLoanAmountInCrores(2000D);
        riskModelTemplateDTO.setProjectName("XYZ Solar Renewables Pvt. Ltd");
        riskModelTemplateDTO.setRatingDate(Date.from(Instant.now()));



        // Operational Phase Risk Types
        RiskTypeDTO operationalPhaseRiskTypeDTO = RenewablesOperatonalRiskTypes.buildOperationalRiskTypes();



        // Set TEST DATA for Risk Sub Factor Attributes per Risk Component per Risk Sub Factor
        for (RiskComponentDTO riskComponentDTO: operationalPhaseRiskTypeDTO.getRiskComponents()) {

            for (RiskFactorDTO riskFactorDTO: riskComponentDTO.getRiskFactors())  {

                for (RiskSubFactorDTO riskSubFactorDTO: riskFactorDTO.getRiskSubFactors()) {

                    Boolean riskDeflator = false;
                    if (riskSubFactorDTO.getScoreTypeCode().equals("02") ||  riskSubFactorDTO.getScoreTypeCode().equals("03"))
                        riskDeflator = true;

                    for (RiskSubFactorAttributeDTO riskSubFactorAttributeDTO: riskSubFactorDTO.getRiskSubFactorAttributes()) {

                        // Select the second last attributes
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

        riskModelTemplateDTO.addRiskTypeDTO(operationalPhaseRiskTypeDTO);


        // Rating Modifiers ARE APPLICABLE FOR RENEWABLES
        Renewable_RatingModifierDTO renewable_ratingModifierDTO = new Renewable_RatingModifierDTO();
        List<RiskRatingModifierDTO> renewable_ratingModifierDTOS =  renewable_ratingModifierDTO.getRiskRatingModifierDTOs();


        for (RiskRatingModifierDTO riskRatingModifierDTO: renewable_ratingModifierDTOS)
            if (riskRatingModifierDTO.getModifierType() == 0) {
                for (RiskRatingModifierAttributeDTO riskRatingModifierAttributeDTO : riskRatingModifierDTO.getRiskRatingModifierAttributes()) {
                    riskRatingModifierAttributeDTO.setYesOrNoIndicator('N');
                }
            } else {
                for (RiskRatingModifierAttributeDTO riskRatingModifierAttributeDTO : riskRatingModifierDTO.getRiskRatingModifierAttributes()) {
                    riskRatingModifierAttributeDTO.setYesOrNoIndicator('N');
                }
            }

        riskModelTemplateDTO.setRiskRatingModifiers(renewable_ratingModifierDTOS);


        // Parental Notchup
        Renewable_RiskParentalNotchUp renewable_riskParentalNotchUp = new Renewable_RiskParentalNotchUp();
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = renewable_riskParentalNotchUp.getParentalNotchUp();

        for (RiskParentalNotchUpConditionDTO riskParentalNotchUpConditionDTO: riskParentalNotchUpDTO.getRiskParentalConditions()) {

            // Source of Rating of the Parent Firm
            if (riskParentalNotchUpConditionDTO.getItemNo() == 1) {
                riskParentalNotchUpConditionDTO.setValue("CRISIL");

            }
            //Nature of Rating of Parent Firm - Short Term or Long Term
            // 0 - Short Term ;   1 - Long Term
            if (riskParentalNotchUpConditionDTO.getItemNo() == 2) {
                riskParentalNotchUpConditionDTO.setNatureOfRatingOfParentFirm('0');

            }

            // Rating of Parent Entity ("Obligor Rating Grade of the Parent Firm as per reference source")
            if (riskParentalNotchUpConditionDTO.getItemNo() == 3) {
                riskParentalNotchUpConditionDTO.setValue("1");
            }
            //Borrower Rating Grade of Parent Firm
            if (riskParentalNotchUpConditionDTO.getItemNo() == 4) {
                riskParentalNotchUpConditionDTO.setValue("1");
            }

            //            3 - Is Parent's rating at GRADE 10
            if (riskParentalNotchUpConditionDTO.getCategory() == 3) {
                riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('N');
            }

            //            4 - Is Parent's Rating Better Than Borrower's Rating
            if (riskParentalNotchUpConditionDTO.getCategory() == 4) {
                riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('Y');
            }
        }



        // Select First Item for Parental Notcuhup Sub Factor Attributes
        for (RiskSubFactorDTO riskSubFactorDTO: riskParentalNotchUpDTO.getRiskSubFactors()) {
             for (RiskSubFactorAttributeDTO riskSubFactorAttributeDTO: riskSubFactorDTO.getRiskSubFactorAttributes()){
                 if (riskSubFactorAttributeDTO.getItemNo() == 1)
                     riskSubFactorAttributeDTO.setIsSelected(true);
             }
        }

        List<RiskParentalNotchUpDTO> riskParentalNotchUpDTOSet = new ArrayList<>();
        riskParentalNotchUpDTOSet.add(riskParentalNotchUpDTO);
        riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOSet);

        // Summary
        Renewable_OperationalPhase_RiskModelSummary renewable_operationalPhase_riskModelSummary = new Renewable_OperationalPhase_RiskModelSummary();
        List<RiskModelSummaryDTO> riskModelSummaryDTOS = renewable_operationalPhase_riskModelSummary.getRiskModelSummary();
        riskModelTemplateDTO.setRiskModelSummaries(riskModelSummaryDTOS);

        return riskModelTemplateDTO;
    }
}
