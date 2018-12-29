package com.pfs.riskmodel.Renewable.OperationalPhase;

import com.pfs.riskmodel.Renewable.ParentalNotchUp.Renewable_RiskParentalNotchUp;
import com.pfs.riskmodel.Renewable.RiskTypes.RenewablesOperatonalRiskTypes;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import com.pfs.riskmodel.dto.RiskParentalNotchUpDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class RenewablesOperationalPhaseData {


    private  static RiskModelTemplateDTO riskModelTemplateDTO;

    public RiskModelTemplateDTO buildRiskModelTemplate() {

        riskModelTemplateDTO = new RiskModelTemplateDTO();

        //        Overall Project Score:        Risk Type Score
        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        riskModelTemplateDTO.setModelCategoryCode(2);

        riskModelTemplateDTO.setProjectRiskLevelCode("02");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Renewables Operational Phase");

        riskModelTemplateDTO.setProjectTypeCode("01");
        riskModelTemplateDTO.setProjectTypeDescription("Renewables");
        riskModelTemplateDTO.setDescription("Renewables Operational Phase");
        riskModelTemplateDTO.setComputingMethodCode("01");
        riskModelTemplateDTO.setComputingMethodDescription("Normal");
        riskModelTemplateDTO.setScore(0D);


        RiskTypeDTO operationPhaseRiskTypeDTO = RenewablesOperatonalRiskTypes.buildOperationalRiskTypes();

        riskModelTemplateDTO.addRiskTypeDTO(operationPhaseRiskTypeDTO);


        // Parental NotchUP
        Renewable_RiskParentalNotchUp renewable_riskParentalNotchUp = new Renewable_RiskParentalNotchUp();
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = renewable_riskParentalNotchUp.getParentalNotchUp();
        Set<RiskParentalNotchUpDTO> riskParentalNotchUpDTOSet = new HashSet<>();
        riskParentalNotchUpDTOSet.add(riskParentalNotchUpDTO);

        riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOSet);


        return riskModelTemplateDTO;

    }
}
