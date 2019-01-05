package com.pfs.riskmodel.Evaluations.Eval_InfraRoadToll;

import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.RiskTypes.InfraRoadHAM_PostProjectImplRiskTypes;
import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.RiskTypes.InfraRoadHAM_ProjectImplRiskTypes;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.ParentalNotchUp.InfraTrans_RiskParentalNotchUp;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.RiskRatingModifiers.InfraTrans_RatingModifierDTO;
import com.pfs.riskmodel.dto.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 29-Dec-18.
 */
public class Evaluate_InfraRoad_Toll_BuildPhaseData {
    RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();


    public RiskModelTemplateDTO getInfraRoad_Toll_BuildPhaseData() {


        riskModelTemplateDTO = new RiskModelTemplateDTO();

        //        Overall Project Score:        Minimum of PIR and PPIR
        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        // Model Category 5: InfraRoadProjectToll-Build
        riskModelTemplateDTO.setModelCategoryCode(7);

        riskModelTemplateDTO.setModelType(1); //Valuation - NOT TEMPLATE

        riskModelTemplateDTO.setProjectRiskLevelCode("01");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Infrastructure Road TOLL Build Phase");

        riskModelTemplateDTO.setProjectTypeCode("04");
        riskModelTemplateDTO.setProjectTypeDescription("Infrastructure Road TOLL");

        riskModelTemplateDTO.setDescription("Infrastructure TOLL Road  Build Phase");
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

                        Integer itemNo = 1;
                        if (riskDeflator == true)
                            itemNo = 1;
                        else
                            itemNo = 4;

                        if (riskSubFactorAttributeDTO.getItemNo() == itemNo) {
                             riskSubFactorAttributeDTO.setIsSelected(true);
                            break;
                        }


                    }
                }
            }

        }


        riskModelTemplateDTO.addRiskTypeDTO(postProjectImplRiskTypeDTO);


        // Set TEST DATA for Risk Sub Factor Attributes per Risk Component per Risk Sub Factor
        for (RiskComponentDTO riskComponentDTO: projectImplRiskTypeDTO.getRiskComponents()) {

            for (RiskFactorDTO riskFactorDTO : riskComponentDTO.getRiskFactors()) {

                for (RiskSubFactorDTO riskSubFactorDTO : riskFactorDTO.getRiskSubFactors()) {

                    Boolean riskDeflator = false;
                    if (riskSubFactorDTO.getScoreTypeCode().equals("02") || riskSubFactorDTO.getScoreTypeCode().equals("03"))
                        riskDeflator = true;

                    for (RiskSubFactorAttributeDTO riskSubFactorAttributeDTO : riskSubFactorDTO.getRiskSubFactorAttributes()) {

                        Integer itemNo = 1;
                        if (riskDeflator == true)
                            itemNo = 1;
                        else
                            itemNo = 2;

                        if (riskSubFactorAttributeDTO.getItemNo() == itemNo) {
                            riskSubFactorAttributeDTO.setIsSelected(true);
                            break;
                        }
                    }

                }
            }

        }

        riskModelTemplateDTO.addRiskTypeDTO(projectImplRiskTypeDTO);


        // Rating Modifiers

        List<RiskRatingModifierDTO> riskRatingModifierDTOSet = new ArrayList<>();
        InfraTrans_RatingModifierDTO infraTrans_ratingModifierDTO = new InfraTrans_RatingModifierDTO();
        riskRatingModifierDTOSet = infraTrans_ratingModifierDTO.getRiskRatingModifierDTOs();

        for (RiskRatingModifierDTO riskRatingModifierDTO:riskRatingModifierDTOSet) {
            for (RiskRatingModifierAttributeDTO riskRatingModifierAttributeDTO: riskRatingModifierDTO.getRiskRatingModifierAttributes()){
                if (riskRatingModifierDTO.getItemNo()/2 == 0)
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

        List<RiskParentalNotchUpDTO> riskParentalNotchUpDTOSet = new ArrayList<>();
        riskParentalNotchUpDTOSet.add(riskParentalNotchUpDTO);

        riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOSet);

        return riskModelTemplateDTO;
    }
}
