package com.pfs.riskmodel.InfraTransmission.OperationalPhase;

import com.pfs.riskmodel.InfraTransmission.RiskTypes.PostProjectImplRiskTypes;
import com.pfs.riskmodel.InfraTransmission.ParentalNotchUp.InfraTrans_RiskParentalNotchUp;
import com.pfs.riskmodel.InfraTransmission.RiskRatingModifiers.InfraTrans_RatingModifierDTO;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import com.pfs.riskmodel.dto.RiskParentalNotchUpDTO;
import com.pfs.riskmodel.dto.RiskRatingModifierDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class InfraTransmissionOperationalPhaseData {


    RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();


    public RiskModelTemplateDTO getInfraTransmissionOperationalPhaseData() {


        riskModelTemplateDTO = new RiskModelTemplateDTO();

        //        Overall Project Score:        Risk Type Score
        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        // Model Category 4: InfraTransmission-Operational
        riskModelTemplateDTO.setModelCategoryCode(4);

        riskModelTemplateDTO.setProjectRiskLevelCode("02");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Infrastructure Transmission Operational Phase");

        riskModelTemplateDTO.setProjectTypeCode("02");
        riskModelTemplateDTO.setProjectTypeDescription("Infrastructure Transmission");

        riskModelTemplateDTO.setDescription("Infrastructure Transmission Operational Phase");
        riskModelTemplateDTO.setComputingMethodCode("05");
        riskModelTemplateDTO.setComputingMethodDescription("Equals");
        riskModelTemplateDTO.setScore(0D);


        // RiskType
        //Project Risk Rating of Infrastructure Tranmission Operational Phase = Post Project Implementation Score
        PostProjectImplRiskTypes postProjectImplRiskTypes = new PostProjectImplRiskTypes();
        RiskTypeDTO riskTypeDTO = postProjectImplRiskTypes.buildPostProjectImplRiskTypes();

        riskModelTemplateDTO.addRiskTypeDTO(riskTypeDTO);


        // Rating Modifiers
        Set<RiskRatingModifierDTO> riskRatingModifierDTOSet = new HashSet<>();
        InfraTrans_RatingModifierDTO infraTrans_ratingModifierDTO = new InfraTrans_RatingModifierDTO();
        riskRatingModifierDTOSet = infraTrans_ratingModifierDTO.getRiskRatingModifierDTOs();

        riskModelTemplateDTO.setRiskRatingModifiers(riskRatingModifierDTOSet);

        // Parental Notchup
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = new RiskParentalNotchUpDTO();
        InfraTrans_RiskParentalNotchUp infraTrans_riskParentalNotchUp = new InfraTrans_RiskParentalNotchUp();
        riskParentalNotchUpDTO = infraTrans_riskParentalNotchUp.getInfraTransmissonParentalNotchup();

        Set <RiskParentalNotchUpDTO> riskParentalNotchUpDTOSet = new HashSet<>();
        riskParentalNotchUpDTOSet.add(riskParentalNotchUpDTO);

        riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOSet);

        return riskModelTemplateDTO;

    }

}
