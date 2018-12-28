package com.pfs.riskmodel.InfraTransmission.OperationalPhase;

import com.pfs.riskmodel.InfraTransmission.BuildPhase.PostProjectRisk.PostProjectImplRiskTypes;
import com.pfs.riskmodel.Renewable.OperationalPhase.OperationalPhaseRisk.RenewablesOperatonalRiskTypes;
import com.pfs.riskmodel.domain.RiskParentalNotchUp;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import com.pfs.riskmodel.dto.RiskRatingModifierDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class InfraTransmissionOperationalPhaseData {


    RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();


    public RiskModelTemplateDTO getInfraTransmissionBuildPhaseData() {


        riskModelTemplateDTO = new RiskModelTemplateDTO();

        //        Overall Project Score:        Risk Type Score
        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        // Model Category 4: InfraTransmission-Operational
        riskModelTemplateDTO.setModelCategoryCode(4);

        riskModelTemplateDTO.setProjectRiskLevelCode("02");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Infrastructure Transmission Operational Phase");

        riskModelTemplateDTO.setProjectTypeCode("01");
        riskModelTemplateDTO.setProjectTypeDescription("Infrastructure Transmission");
        riskModelTemplateDTO.setDescription("Infrastructure Transmission Operational Phase");
        riskModelTemplateDTO.setComputingMethodCode("01");
        riskModelTemplateDTO.setComputingMethodDescription("Normal");
        riskModelTemplateDTO.setScore(0D);


        // RiskType
        //Project Risk Rating of Infrastructure Tranmission Operational Phase = Post Project Implementation Score
        PostProjectImplRiskTypes postProjectImplRiskTypes = new PostProjectImplRiskTypes();
        RiskTypeDTO riskTypeDTO = postProjectImplRiskTypes.buildPostProjectImplRiskTypes();

        riskModelTemplateDTO.addRiskTypeDTO(riskTypeDTO);


        // Rating Modifiers
        Set<RiskRatingModifierDTO> riskRatingModifierDTOSet = new HashSet<>();
        //TODO

        // Parental Notchup
        Set<RiskParentalNotchUp> riskParentalNotchUpSet = new HashSet<>();
        //TODO

        return riskModelTemplateDTO;

    }

}
