package com.pfs.riskmodel.InfraRoadHAM.BuildPhase;

import com.pfs.riskmodel.InfraTransmission.ParentalNotchUp.InfraTrans_RiskParentalNotchUp;
import com.pfs.riskmodel.InfraTransmission.RiskRatingModifiers.InfraTrans_RatingModifierDTO;
import com.pfs.riskmodel.InfraTransmission.RiskTypes.PostProjectImplRiskTypes;
import com.pfs.riskmodel.InfraTransmission.RiskTypes.ProjectImplRiskTypes;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import com.pfs.riskmodel.dto.RiskParentalNotchUpDTO;
import com.pfs.riskmodel.dto.RiskRatingModifierDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 29-Dec-18.
 */
public class InfraRoadHAM_BuildPhaseData {
    RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();


    public RiskModelTemplateDTO getInfraRoadHAM_BuildPhaseData() {


        riskModelTemplateDTO = new RiskModelTemplateDTO();

        //        Overall Project Score:        Minimum of PIR and PPIR
        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        // Model Category 5: InfraRoadProjectHybridAnnuity-Build
        riskModelTemplateDTO.setModelCategoryCode(5);

        riskModelTemplateDTO.setProjectRiskLevelCode("01");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Infrastructure Road HAM Build Phase");

        riskModelTemplateDTO.setProjectTypeCode("03");
        riskModelTemplateDTO.setProjectTypeDescription("Infrastructure Road HAM");

        riskModelTemplateDTO.setDescription("Infrastructure Road HAM Build Phase");
        riskModelTemplateDTO.setComputingMethodCode("03");
        riskModelTemplateDTO.setComputingMethodDescription("Minimum"); //Minimum of PIR and PPIR
        riskModelTemplateDTO.setScore(0D);


        // RiskType
        //Project Risk Rating of Infrastructure Tranmission Operational Phase = Minimum of PIR and PPIR  Scores

        // Post Project Impl. Risk Types
        PostProjectImplRiskTypes postProjectImplRiskTypes = new PostProjectImplRiskTypes();
        RiskTypeDTO postProjectImplRiskTypeDTO = postProjectImplRiskTypes.buildPostProjectImplRiskTypes();

        // Project Impl. Risk Types
        ProjectImplRiskTypes projectImplRiskTypes = new ProjectImplRiskTypes();
        RiskTypeDTO projectImplRiskTypeDTO = projectImplRiskTypes.buildProjectImplRiskTypes();


        riskModelTemplateDTO.addRiskTypeDTO(postProjectImplRiskTypeDTO);


        // Rating Modifiers
        Set<RiskRatingModifierDTO> riskRatingModifierDTOSet = new HashSet<>();
        InfraTrans_RatingModifierDTO infraTrans_ratingModifierDTO = new InfraTrans_RatingModifierDTO();
        riskRatingModifierDTOSet = infraTrans_ratingModifierDTO.getRiskRatingModifierDTOs();

        riskModelTemplateDTO.setRiskRatingModifiers(riskRatingModifierDTOSet);

        // Parental Notchup
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = new RiskParentalNotchUpDTO();
        InfraTrans_RiskParentalNotchUp infraTrans_riskParentalNotchUp = new InfraTrans_RiskParentalNotchUp();
        riskParentalNotchUpDTO = infraTrans_riskParentalNotchUp.getInfraTransmissonParentalNotchup();

        Set<RiskParentalNotchUpDTO> riskParentalNotchUpDTOSet = new HashSet<>();
        riskParentalNotchUpDTOSet.add(riskParentalNotchUpDTO);

        riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOSet);

        return riskModelTemplateDTO;
    }
}
