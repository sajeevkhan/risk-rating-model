package com.pfs.riskmodel.Evaluations.Eval_InfraRoadHAM;

import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.ParentalNotchUp.InfraRoadHAM_RiskParentalNotchUp;
import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.RiskTypes.InfraRoadHAM_PostProjectImplRiskTypes;
import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.RiskTypes.InfraRoadHAM_ProjectImplRiskTypes;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.RiskRatingModifiers.InfraTrans_RatingModifierDTO;
import com.pfs.riskmodel.dto.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 29-Dec-18.
 */
public class Evaluate_InfraRoadHAM_BuildPhaseData {
    RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();


    public RiskModelTemplateDTO getInfraRoadHAM_BuildPhaseData() {


        riskModelTemplateDTO = new RiskModelTemplateDTO();

        //        Overall Project Score:        Minimum of PIR and PPIR
        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        // Model Category 5: InfraRoadProjectHybridAnnuity-Build
        riskModelTemplateDTO.setModelCategoryCode(5);

        riskModelTemplateDTO.setModelType(1); //Valuation - NOT TEMPLATE

        riskModelTemplateDTO.setPurposeCode("01");
        riskModelTemplateDTO.setPurposeDescription("Project Assessment");
        riskModelTemplateDTO.setProcessInstanceId(" ");
        riskModelTemplateDTO.setWorkflowStatusCode("01");
        riskModelTemplateDTO.setWorkflowStatusDescription("Created");


        riskModelTemplateDTO.setProjectRiskLevelCode("01");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Infrastructure Road HAM Build Phase");

        riskModelTemplateDTO.setRiskProjectTypeCode("03");
        riskModelTemplateDTO.setRiskProjectTypeDescription("Infrastructure Road HAM");

        riskModelTemplateDTO.setDescription("Infrastructure Road HAM Build Phase");
        riskModelTemplateDTO.setComputingMethodCode("03");
        riskModelTemplateDTO.setComputingMethodDescription("Minimum"); //Minimum of PIR and PPIR
        riskModelTemplateDTO.setScore(0D);

        riskModelTemplateDTO.setOverallProjectGrade(" ");
        riskModelTemplateDTO.setModifiedProjectGrade(" ");
        riskModelTemplateDTO.setAfterParentalNotchUpGrade(" ");
        riskModelTemplateDTO.setFinalProjectGrade(" ");



        riskModelTemplateDTO.setLoanNumber("100000");
        riskModelTemplateDTO.setLoanAmountInCrores(1000D);
        riskModelTemplateDTO.setProjectName("ABC ROAD Infrastructures - Hybrid Annuity Model");
        riskModelTemplateDTO.setRatingDate(Date.from(Instant.now()));


        // RiskType
        //Project Risk Rating of Infrastructure Tranmission Operational Phase = Minimum of PIR and PPIR  Scores


        // Post Project Impl. Risk Types
        InfraRoadHAM_PostProjectImplRiskTypes postProjectImplRiskTypes = new InfraRoadHAM_PostProjectImplRiskTypes();
        RiskTypeDTO postProjectImplRiskTypeDTO = postProjectImplRiskTypes.buildPostProjectImplRiskTypes();

        // Project Impl. Risk Types
        InfraRoadHAM_ProjectImplRiskTypes projectImplRiskTypes = new InfraRoadHAM_ProjectImplRiskTypes();
        RiskTypeDTO projectImplRiskTypeDTO = projectImplRiskTypes.buildProjectImplRiskTypes();



        // Set TEST DATA for Risk Sub Factor Attributes per Risk Component per Risk Sub Factor
        for (RiskComponentDTO riskComponentDTO: postProjectImplRiskTypeDTO.getRiskComponents()) {

            for (RiskFactorDTO riskFactorDTO: riskComponentDTO.getRiskFactors())  {

                for (RiskSubFactorDTO riskSubFactorDTO: riskFactorDTO.getRiskSubFactors()) {

                    Boolean riskDeflator = false;
                    if (riskSubFactorDTO.getScoreTypeCode().equals("02") ||  riskSubFactorDTO.getScoreTypeCode().equals("03"))
                        riskDeflator = true;

                    for (RiskSubFactorAttributeDTO riskSubFactorAttributeDTO: riskSubFactorDTO.getRiskSubFactorAttributes()) {
                        Integer selectedItem = 0;
                        if (riskComponentDTO.getDescription().contains("Financial"))
                            selectedItem = riskSubFactorDTO.getRiskSubFactorAttributes().size();
                        else
                            selectedItem = riskSubFactorDTO.getRiskSubFactorAttributes().size() - 1;

                        // Select first attribute for Deflators and secondLastItem for Normal SubFactors
                        Integer itemNo = 1;
                        if (riskDeflator == true) {
                            if ( riskSubFactorDTO.getRiskSubFactorAttributes().size() == 2)
                                itemNo = 2;
                            else
                                itemNo = 3;
                        }
                        else
                            itemNo = selectedItem;

                        if (riskSubFactorAttributeDTO.getItemNo() == itemNo) {
                            riskSubFactorAttributeDTO.setIsSelected(true);
                            break;
                        }
                    }
                }
            }

        }




        // Set TEST DATA for Risk Sub Factor Attributes per Risk Component per Risk Sub Factor
        for (RiskComponentDTO riskComponentDTO: projectImplRiskTypeDTO.getRiskComponents()) {

            for (RiskFactorDTO riskFactorDTO : riskComponentDTO.getRiskFactors()) {

                for (RiskSubFactorDTO riskSubFactorDTO : riskFactorDTO.getRiskSubFactors()) {

                    Boolean riskDeflator = false;
                    if (riskSubFactorDTO.getScoreTypeCode().equals("02") || riskSubFactorDTO.getScoreTypeCode().equals("03"))
                        riskDeflator = true;

                    for (RiskSubFactorAttributeDTO riskSubFactorAttributeDTO : riskSubFactorDTO.getRiskSubFactorAttributes()) {

                        Integer selectedItem = 0;
                        if (riskComponentDTO.getDescription().contains("Financial"))
                            selectedItem = riskSubFactorDTO.getRiskSubFactorAttributes().size();
                        else
                            selectedItem = riskSubFactorDTO.getRiskSubFactorAttributes().size() - 1;

                        // Select first attribute for Deflators and secondLastItem for Normal SubFactors
                        Integer itemNo = 1;
                        if (riskDeflator == true) {
                            if ( riskSubFactorDTO.getRiskSubFactorAttributes().size() == 2)
                                itemNo = 2;
                            else
                                itemNo = 3;
                        }
                        else
                            itemNo = selectedItem;;

                        if (riskSubFactorAttributeDTO.getItemNo() == itemNo) {
                            riskSubFactorAttributeDTO.setIsSelected(true);
                            break;
                        }
                    }
                }
            }
        }
        riskModelTemplateDTO.addRiskTypeDTO(projectImplRiskTypeDTO);
        riskModelTemplateDTO.addRiskTypeDTO(postProjectImplRiskTypeDTO);


        // Rating Modifiers

        List<RiskRatingModifierDTO> riskRatingModifierDTOSet = new ArrayList<>();
        InfraTrans_RatingModifierDTO infraTrans_ratingModifierDTO = new InfraTrans_RatingModifierDTO();
        riskRatingModifierDTOSet = infraTrans_ratingModifierDTO.getRiskRatingModifierDTOs();

        for (RiskRatingModifierDTO riskRatingModifierDTO:riskRatingModifierDTOSet) {

            for (RiskRatingModifierAttributeDTO riskRatingModifierAttributeDTO:
                    riskRatingModifierDTO.getRiskRatingModifierAttributes()) {
                //if (riskRatingModifierDTO.getItemNo()/2 == 0)
                riskRatingModifierAttributeDTO.setYesOrNoIndicator('N');
//                else
//                    riskRatingModifierAttributeDTO.setYesOrNoIndicator('N');
//                }
            }

        }


        riskModelTemplateDTO.setRiskRatingModifiers(riskRatingModifierDTOSet);

        // Parental Notchup
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = new RiskParentalNotchUpDTO();
        InfraRoadHAM_RiskParentalNotchUp infraRoadHAM_riskParentalNotchUp = new InfraRoadHAM_RiskParentalNotchUp();
        riskParentalNotchUpDTO = infraRoadHAM_riskParentalNotchUp.getInfraRoadHAM_ParentalNotchup();

        for (RiskParentalNotchUpConditionDTO riskParentalNotchUpConditionDTO: riskParentalNotchUpDTO.getRiskParentalConditions()){

            if (riskParentalNotchUpConditionDTO.getItemNo() == 1) {
                riskParentalNotchUpConditionDTO.setValue("1");
            }

             if (riskParentalNotchUpConditionDTO.getItemNo() == 2) {
                 riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('Y');
             }
            //            3 - Is Parent's rating at GRADE 10
            if (riskParentalNotchUpConditionDTO.getCategory() == 3) {
                riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('N');
            }

            //            4 - Is Parent's Rating Better Than Borrower's Rating
            if (riskParentalNotchUpConditionDTO.getCategory() == 4) {
                riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('Y');
            }


        for (RiskSubFactorDTO riskSubFactorDTO: riskParentalNotchUpDTO.getRiskSubFactors()) {
            for (RiskSubFactorAttributeDTO riskSubFactorAttributeDTO : riskSubFactorDTO.getRiskSubFactorAttributes()) {
                if (riskSubFactorAttributeDTO.getItemNo() == 1)
                    riskSubFactorAttributeDTO.setIsSelected(true);
            }
        }
        }


        List<RiskParentalNotchUpDTO> riskParentalNotchUpDTOSet = new ArrayList<>();
        riskParentalNotchUpDTOSet.add(riskParentalNotchUpDTO);



        riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOSet);

        return riskModelTemplateDTO;
    }
}
