package com.pfs.riskmodel.InfraRoadToll.OperationalPhase;

import com.pfs.riskmodel.InfraRoadToll.ParentalNotchUp.InfraRoadToll_RiskParentalNotchUp;
import com.pfs.riskmodel.InfraRoadToll.RiskRatingModifier.InfraRoadToll_RatingModifierDTO;
import com.pfs.riskmodel.InfraRoadToll.RiskTypes.InfraRoadToll_PostProjectImplRiskTypes;
import com.pfs.riskmodel.InfraTransmission.ParentalNotchUp.InfraTrans_RiskParentalNotchUp;
import com.pfs.riskmodel.InfraTransmission.RiskRatingModifiers.InfraTrans_RatingModifierDTO;
import com.pfs.riskmodel.InfraTransmission.RiskTypes.PostProjectImplRiskTypes;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import com.pfs.riskmodel.dto.RiskParentalNotchUpDTO;
import com.pfs.riskmodel.dto.RiskRatingModifierDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 29-Dec-18.
 */
public class InfraRoadToll_OperationalPhaseData {


    RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();


    public RiskModelTemplateDTO getInfraRoadToll_OperationalPhaseData() {


        riskModelTemplateDTO = new RiskModelTemplateDTO();

        //        Overall Project Score:        Risk Type Score
        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        // Model Category 8: InfraRoadProjectToll-Operational
        riskModelTemplateDTO.setModelCategoryCode(8);

        riskModelTemplateDTO.setProjectRiskLevelCode("02");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Infrastructure Road Toll Operational Phase");

        riskModelTemplateDTO.setProjectTypeCode("03");
        riskModelTemplateDTO.setProjectTypeDescription("Infrastructure Road Toll");

        riskModelTemplateDTO.setDescription("Infrastructure Road Toll Operational Phase");
        riskModelTemplateDTO.setComputingMethodCode("05");
        riskModelTemplateDTO.setComputingMethodDescription("Equals");
        riskModelTemplateDTO.setScore(0D);


        // RiskType
        //Project Risk Rating of Infrastructure Tranmission Operational Phase = Post Project Implementation Score
        InfraRoadToll_PostProjectImplRiskTypes infraRoadToll_postProjectImplRiskTypes = new InfraRoadToll_PostProjectImplRiskTypes();
        RiskTypeDTO riskTypeDTO = infraRoadToll_postProjectImplRiskTypes.buildPostProjectImplRiskTypes();

        riskModelTemplateDTO.addRiskTypeDTO(riskTypeDTO);


        // Rating Modifiers
        Set<RiskRatingModifierDTO> riskRatingModifierDTOSet = new HashSet<>();
        InfraRoadToll_RatingModifierDTO infraRoadToll_ratingModifierDTO = new InfraRoadToll_RatingModifierDTO();
        riskRatingModifierDTOSet = infraRoadToll_ratingModifierDTO.getRiskRatingModifierDTOs();

        riskModelTemplateDTO.setRiskRatingModifiers(riskRatingModifierDTOSet);

        // Parental Notchup
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = new RiskParentalNotchUpDTO();
        InfraRoadToll_RiskParentalNotchUp infraRoadToll_riskParentalNotchUp = new InfraRoadToll_RiskParentalNotchUp();
        riskParentalNotchUpDTO = infraRoadToll_riskParentalNotchUp.getInfraRoadToll_ParentalNotchup();

        Set <RiskParentalNotchUpDTO> riskParentalNotchUpDTOSet = new HashSet<>();
        riskParentalNotchUpDTOSet.add(riskParentalNotchUpDTO);

        riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOSet);

        return riskModelTemplateDTO;

    }

}
