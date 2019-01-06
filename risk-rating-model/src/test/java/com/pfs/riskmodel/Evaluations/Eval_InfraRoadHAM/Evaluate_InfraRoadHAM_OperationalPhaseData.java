package com.pfs.riskmodel.Evaluations.Eval_InfraRoadHAM;

import com.pfs.riskmodel.ModelTemplates.InfraTransmission.ParentalNotchUp.InfraTrans_RiskParentalNotchUp;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.RiskRatingModifiers.InfraTrans_RatingModifierDTO;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.RiskTypes.InfraTrans_PostProjectImplRiskTypes;
import com.pfs.riskmodel.dto.*;

import javax.persistence.criteria.CriteriaBuilder;
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

        riskModelTemplateDTO.setProjectRiskLevelCode("02");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Infrastructure Road HAM Operational Phase");

        riskModelTemplateDTO.setProjectTypeCode("03");
        riskModelTemplateDTO.setProjectTypeDescription("Infrastructure Road HAM");

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
        //Project Risk Rating of Infrastructure Tranmission Operational Phase = Post Project Implementation Score
        InfraTrans_PostProjectImplRiskTypes infraTransPostProjectImplRiskTypes = new InfraTrans_PostProjectImplRiskTypes();
        RiskTypeDTO riskTypeDTO = infraTransPostProjectImplRiskTypes.buildPostProjectImplRiskTypes();


        // Set TEST DATA for Risk Sub Factor Attributes per Risk Component per Risk Sub Factor
        for (RiskComponentDTO riskComponentDTO : riskTypeDTO.getRiskComponents()) {

            for (RiskFactorDTO riskFactorDTO : riskComponentDTO.getRiskFactors()) {

                for (RiskSubFactorDTO riskSubFactorDTO : riskFactorDTO.getRiskSubFactors()) {

                    Boolean riskDeflator = false;
                    if (riskSubFactorDTO.getScoreTypeCode().equals("02") || riskSubFactorDTO.getScoreTypeCode().equals("03"))
                        riskDeflator = true;


                    Integer maxItems = riskSubFactorDTO.getRiskSubFactorAttributes().size();

                    for (RiskSubFactorAttributeDTO riskSubFactorAttributeDTO : riskSubFactorDTO.getRiskSubFactorAttributes()) {

                        Integer itemNo = 1;
                        if (riskDeflator == true)
                            itemNo = 1;
                        else
                            itemNo = maxItems;

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


        for (RiskRatingModifierDTO riskRatingModifierDTO : riskRatingModifierDTOSet) {
            for (RiskRatingModifierAttributeDTO riskRatingModifierAttributeDTO : riskRatingModifierDTO.getRiskRatingModifierAttributes()) {
                if (riskRatingModifierDTO.getItemNo() / 2 == 0)
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

        for (RiskParentalNotchUpConditionDTO riskParentalNotchUpConditionDTO : riskParentalNotchUpDTO.getRiskParentalConditions()) {

            if (riskParentalNotchUpConditionDTO.getItemNo() == 1) {
                riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('3');

            }

            if (riskParentalNotchUpConditionDTO.getItemNo() == 2) {
                riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('Y');

            }

            if (riskParentalNotchUpConditionDTO.getItemNo() == 3) {
                riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('N');

            }
            if (riskParentalNotchUpConditionDTO.getItemNo() == 4) {
                riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('Y');

            }
        }


            for (RiskSubFactorDTO riskSubFactorDTO : riskParentalNotchUpDTO.getRiskSubFactors()) {

                for (RiskSubFactorAttributeDTO riskSubFactorAttributeDTO : riskSubFactorDTO.getRiskSubFactorAttributes()) {
                    if (riskSubFactorAttributeDTO.getItemNo() == 1)
                        riskSubFactorAttributeDTO.setIsSelected(true);
                }

            }


            List<RiskParentalNotchUpDTO> riskParentalNotchUpDTOSet = new ArrayList<>();
            riskParentalNotchUpDTOSet.add(riskParentalNotchUpDTO);

            riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOSet);

            return riskModelTemplateDTO;



    }
}
