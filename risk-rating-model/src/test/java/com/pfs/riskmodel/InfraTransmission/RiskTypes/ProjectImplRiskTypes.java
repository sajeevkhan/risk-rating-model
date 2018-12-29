package com.pfs.riskmodel.InfraTransmission.RiskTypes;

import com.pfs.riskmodel.InfraTransmission.RiskComponents.PIR_CompletionRiskDTO;
import com.pfs.riskmodel.InfraTransmission.RiskComponents.PIR_ExecutionRiskDTO;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class ProjectImplRiskTypes {


    public  RiskTypeDTO buildProjectImplRiskTypes() {


        RiskTypeDTO riskTypeDTO = new RiskTypeDTO();


        riskTypeDTO.setId(null);
        riskTypeDTO.setItemNo(1);
        riskTypeDTO.setDescription("Infra. Transmission - Project Implementation Risk Type");
        riskTypeDTO.setScore(0D);

        Set<RiskComponentDTO> riskComponentDTOs = new HashSet<>();


        PIR_CompletionRiskDTO pir_completionRiskDTO = new PIR_CompletionRiskDTO();
        RiskComponentDTO completionRiskDTO = pir_completionRiskDTO.getCompletionRiskDTO();

        PIR_ExecutionRiskDTO pir_executionRiskDTO = new PIR_ExecutionRiskDTO();
        RiskComponentDTO executionRiskComponentDTO = pir_executionRiskDTO.getExecutionRiskDTO();

        riskComponentDTOs.add(completionRiskDTO);
        riskComponentDTOs.add(executionRiskComponentDTO);

        riskTypeDTO.setRiskComponents(riskComponentDTOs);
        return riskTypeDTO;



    }
}
