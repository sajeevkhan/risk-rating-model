package com.pfs.riskmodel.Evaluations.Eval_InfraRoadHAM;

import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.ParentalNotchUp.InfraRoadHAM_RiskParentalNotchUp;
import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.RiskTypes.InfraRoadHAM_PostProjectImplRiskTypes;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.RiskRatingModifiers.InfraTrans_RatingModifierDTO;
import com.pfs.riskmodel.dto.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 29-Dec-18.
 */
public class Evaluate_InfraRoadHAM_OperationalPhaseData {


    RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();


    public RiskModelTemplateDTO getInfraRoadHAM_OperationalPhaseData() {


        riskModelTemplateDTO = new RiskModelTemplateDTO();

        //        Overall Project Score:        Risk Type Score
        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        // Model Category 6: InfraRoadProjectHybridAnnuity-Operational
        riskModelTemplateDTO.setModelCategoryCode(6);

        riskModelTemplateDTO.setModelType(1); //Evaluation

        riskModelTemplateDTO.setPurposeCode("01");
        riskModelTemplateDTO.setPurposeDescription("Project Assessment");
        riskModelTemplateDTO.setProcessInstanceId(" ");
        riskModelTemplateDTO.setWorkflowStatusCode("01");
        riskModelTemplateDTO.setWorkflowStatusDescription("Created");


        riskModelTemplateDTO.setProjectRiskLevelCode("02");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Infrastructure Road HAM Operational Phase");

        riskModelTemplateDTO.setRiskProjectTypeCode("03");
        riskModelTemplateDTO.setRiskProjectTypeDescription("Infrastructure Road HAM");

        riskModelTemplateDTO.setDescription("Infrastructure Road HAM Operational Phase");
        riskModelTemplateDTO.setComputingMethodCode("05");
        riskModelTemplateDTO.setComputingMethodDescription("Equals");
        riskModelTemplateDTO.setScore(0D);


        riskModelTemplateDTO.setLoanNumber("100002");
        riskModelTemplateDTO.setLoanAmountInCrores(0D);
        riskModelTemplateDTO.setProjectName("ABC Infra. Road HAM Operational Phase");
        riskModelTemplateDTO.setRatingDate(Date.from(Instant.now()));


        riskModelTemplateDTO.setOverallProjectGrade(" ");
        riskModelTemplateDTO.setModifiedProjectGrade(" ");
        riskModelTemplateDTO.setAfterParentalNotchUpGrade(" ");
        riskModelTemplateDTO.setFinalProjectGrade(" ");


        // RiskType
        //Project Risk Rating of Infrastructure HAM Operational Phase = Post Project Implementation Score
        InfraRoadHAM_PostProjectImplRiskTypes infraTrans_postProjectImplRiskTypes = new InfraRoadHAM_PostProjectImplRiskTypes();
        RiskTypeDTO riskTypeDTO = infraTrans_postProjectImplRiskTypes.buildPostProjectImplRiskTypes();


        // Set TEST DATA for Risk Sub Factor Attributes per Risk Component per Risk Sub Factor
        for (RiskComponentDTO riskComponentDTO : riskTypeDTO.getRiskComponents()) {

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


        riskModelTemplateDTO.addRiskTypeDTO(riskTypeDTO);
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
