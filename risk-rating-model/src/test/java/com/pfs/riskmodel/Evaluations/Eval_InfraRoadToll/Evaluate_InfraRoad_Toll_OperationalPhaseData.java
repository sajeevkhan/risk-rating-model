package com.pfs.riskmodel.Evaluations.Eval_InfraRoadToll;

import com.pfs.riskmodel.ModelTemplates.InfraRoadToll.RiskRatingModifier.InfraRoadToll_RatingModifierDTO;
import com.pfs.riskmodel.ModelTemplates.InfraRoadToll.RiskTypes.InfraRoadToll_PostProjectImplRiskTypes;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.BuildPhase.InfraTransmission_BuildPhase_RiskModelSummary;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.ParentalNotchUp.InfraTrans_RiskParentalNotchUp;
import com.pfs.riskmodel.dto.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 29-Dec-18.
 */
public class Evaluate_InfraRoad_Toll_OperationalPhaseData {
    RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();


    public RiskModelTemplateDTO getInfraRoad_Toll_BuildPhaseData() {


        riskModelTemplateDTO = new RiskModelTemplateDTO();

        //        Overall Project Score:        Minimum of PIR and PPIR
        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        // Model Category 8: InfraRoadProjectToll-Operational
        riskModelTemplateDTO.setModelCategoryCode(8);

        riskModelTemplateDTO.setModelType(1); //Valuation - NOT TEMPLATE

        riskModelTemplateDTO.setPurposeCode("01");
        riskModelTemplateDTO.setPurposeDescription("Project Assessment");
        riskModelTemplateDTO.setProcessInstanceId(" ");
        riskModelTemplateDTO.setWorkflowStatusCode("01");
        riskModelTemplateDTO.setWorkflowStatusDescription("Created");


        riskModelTemplateDTO.setProjectRiskLevelCode("02");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Infrastructure Road Toll Operational Phase");

        riskModelTemplateDTO.setRiskProjectTypeCode("04");
        riskModelTemplateDTO.setRiskProjectTypeDescription("Infrastructure Road Toll");

        riskModelTemplateDTO.setDescription("Infrastructure Toll Road  Operational Phase");
        riskModelTemplateDTO.setComputingMethodCode("03");
        riskModelTemplateDTO.setComputingMethodDescription("Minimum"); //Minimum of PIR and PPIR
        riskModelTemplateDTO.setScore(0D);



        riskModelTemplateDTO.setLoanNumber("1000001");
        riskModelTemplateDTO.setLoanAmountInCrores(2000D);
        riskModelTemplateDTO.setProjectName("ABC ROAD Infrastructures - TOLL ROAD Project");
        riskModelTemplateDTO.setRatingDate(Date.from(Instant.now()));

        riskModelTemplateDTO.setOverallProjectGrade(" ");
        riskModelTemplateDTO.setModifiedProjectGrade(" ");
        riskModelTemplateDTO.setAfterParentalNotchUpGrade(" ");
        riskModelTemplateDTO.setFinalProjectGrade(" ");


        // RiskType
        //Project Risk Rating of Infrastructure TOLL Operational Phase =  PPIR  Scores


        //  Post Project Impl. Risk Types
        InfraRoadToll_PostProjectImplRiskTypes infraRoadToll_postProjectImplRiskTypes = new InfraRoadToll_PostProjectImplRiskTypes();
        RiskTypeDTO postProjectImplRiskTypes = infraRoadToll_postProjectImplRiskTypes.buildPostProjectImplRiskTypes();


        // Set TEST DATA for Risk Sub Factor Attributes per Risk Component per Risk Sub Factor
        for (RiskComponentDTO riskComponentDTO: postProjectImplRiskTypes.getRiskComponents()) {

            for (RiskFactorDTO riskFactorDTO: riskComponentDTO.getRiskFactors())  {

                for (RiskSubFactorDTO riskSubFactorDTO: riskFactorDTO.getRiskSubFactors()) {

                    Boolean riskDeflator = false;
                    if (riskSubFactorDTO.getScoreTypeCode().equals("02") ||  riskSubFactorDTO.getScoreTypeCode().equals("03"))
                        riskDeflator = true;

                    for (RiskSubFactorAttributeDTO riskSubFactorAttributeDTO: riskSubFactorDTO.getRiskSubFactorAttributes()) {
                        // Select the second last attribute
                        Integer secondLastItem = riskSubFactorDTO.getRiskSubFactorAttributes().size() - 1;

                        // Select first attribute for Deflators and secondLastItem for Normal SubFactors
                        Integer itemNo = 1;
                        if (riskDeflator == true)
                            itemNo = 1;
                        else
                            itemNo = secondLastItem;

                        // Select second item only for execution risk
                        if (riskComponentDTO.getDescription().contains("Execution")) {
                            if (riskSubFactorAttributeDTO.getItemNo() == 2) {
                                riskSubFactorAttributeDTO.setIsSelected(true);
                                break;
                            }
                        }
                        else {
                            if (riskSubFactorAttributeDTO.getItemNo() == itemNo) {
                                riskSubFactorAttributeDTO.setIsSelected(true);
                                break;
                            }
                        }
                    }
                }
            }
        }


        riskModelTemplateDTO.addRiskTypeDTO(postProjectImplRiskTypes);



        // Rating Modifiers
        List<RiskRatingModifierDTO> riskRatingModifierDTOSet = new ArrayList<>();
        InfraRoadToll_RatingModifierDTO infraRoadToll_ratingModifierDTO = new InfraRoadToll_RatingModifierDTO();
        riskRatingModifierDTOSet = infraRoadToll_ratingModifierDTO.getRiskRatingModifierDTOs();

        for (RiskRatingModifierDTO riskRatingModifierDTO:riskRatingModifierDTOSet) {
            for (RiskRatingModifierAttributeDTO riskRatingModifierAttributeDTO: riskRatingModifierDTO.getRiskRatingModifierAttributes()){
                if (riskRatingModifierAttributeDTO.getItemNo() / 2 == 0)
                    riskRatingModifierAttributeDTO.setYesOrNoIndicator('Y');
                else
                    riskRatingModifierAttributeDTO.setYesOrNoIndicator('N');
                }

        }


        riskModelTemplateDTO.setRiskRatingModifiers(riskRatingModifierDTOSet);

        // Parental Notchup
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = new RiskParentalNotchUpDTO();
        InfraTrans_RiskParentalNotchUp infraTrans_riskParentalNotchUp = new InfraTrans_RiskParentalNotchUp();
        riskParentalNotchUpDTO = infraTrans_riskParentalNotchUp.getInfraTransmissonParentalNotchup();


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


        // Select First Item for Parental Notcuhup Sub Factor Attributes
        for (RiskSubFactorDTO riskSubFactorDTO: riskParentalNotchUpDTO.getRiskSubFactors()) {
            for (RiskSubFactorAttributeDTO riskSubFactorAttributeDTO: riskSubFactorDTO.getRiskSubFactorAttributes()){
                if (riskSubFactorAttributeDTO.getItemNo() == 1)
                    riskSubFactorAttributeDTO.setIsSelected(true);
            }
        }


        List<RiskParentalNotchUpDTO> riskParentalNotchUpDTOSet = new ArrayList<>();
        riskParentalNotchUpDTOSet.add(riskParentalNotchUpDTO);


        InfraTransmission_BuildPhase_RiskModelSummary infraTransmission_buildPhase_riskModelSummary = new InfraTransmission_BuildPhase_RiskModelSummary();
        List<RiskModelSummaryDTO> riskModelSummaryDTOS = infraTransmission_buildPhase_riskModelSummary.getRiskModelSummary();
        riskModelTemplateDTO.setRiskModelSummaries(riskModelSummaryDTOS);

        riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOSet);

        return riskModelTemplateDTO;
    }
}
