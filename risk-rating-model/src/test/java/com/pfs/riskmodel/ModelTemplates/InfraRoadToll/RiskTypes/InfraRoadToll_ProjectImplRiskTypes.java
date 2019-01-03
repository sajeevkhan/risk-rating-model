package com.pfs.riskmodel.ModelTemplates.InfraRoadToll.RiskTypes;

import com.pfs.riskmodel.ModelTemplates.InfraRoadToll.RiskComponents.IRToll_PIR_CompletionRiskDTO;
import com.pfs.riskmodel.ModelTemplates.InfraRoadToll.RiskComponents.IRToll_PIR_ExecutionRiskDTO;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 29-Dec-18.
 */
public class InfraRoadToll_ProjectImplRiskTypes {


    public RiskTypeDTO buildProjectImplRiskTypes() {


        RiskTypeDTO riskTypeDTO = new RiskTypeDTO();


        riskTypeDTO.setId(null);
        riskTypeDTO.setItemNo(1);
        riskTypeDTO.setDescription("Infra. Road HAM - Project Implementation Risk Type");
        riskTypeDTO.setScore(0D);
        riskTypeDTO.setGrade(" ");


        List<RiskComponentDTO> riskComponentDTOs = new ArrayList<>();


        IRToll_PIR_CompletionRiskDTO irToll_pir_completionRiskDTO = new IRToll_PIR_CompletionRiskDTO();
        RiskComponentDTO completionRiskDTO = irToll_pir_completionRiskDTO.getCompletionRiskDTO();

        IRToll_PIR_ExecutionRiskDTO irToll_pir_executionRiskDTO = new IRToll_PIR_ExecutionRiskDTO();
        RiskComponentDTO executionRiskComponentDTO = irToll_pir_executionRiskDTO.getExecutionRiskDTO();

        riskComponentDTOs.add(completionRiskDTO);
        riskComponentDTOs.add(executionRiskComponentDTO);

        riskTypeDTO.setRiskComponents(riskComponentDTOs);
        return riskTypeDTO;

    }
}
