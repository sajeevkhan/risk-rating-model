package com.pfs.riskmodel.InfraTransmission.BuildPhase.ProjectRisk;

import com.pfs.riskmodel.Renewable.OperationalPhase.OperationalPhaseRisk.ROP_BusinessRiskDTO;
import com.pfs.riskmodel.Renewable.OperationalPhase.OperationalPhaseRisk.ROP_FinancialRiskDTO;
import com.pfs.riskmodel.Renewable.OperationalPhase.OperationalPhaseRisk.ROP_IndustryRiskDTO;
import com.pfs.riskmodel.Renewable.OperationalPhase.OperationalPhaseRisk.ROP_ManagementRiskDTO;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class ProjectImplRiskTypes {


    public static RiskTypeDTO buildProjectImplRiskTypes() {


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
