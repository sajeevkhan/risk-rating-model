package com.pfs.riskmodel.Renewable.RiskTypes;

import com.pfs.riskmodel.Renewable.RiskComponents.RP_CompletionRiskComponentDTO;
import com.pfs.riskmodel.Renewable.RiskComponents.RP_ExecutionRiskDTO;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class RenewableProjectRiskTypes {
    public static RiskTypeDTO riskTypeDTO ;

    public static RiskTypeDTO buildRiskTypes () {

        //---------------------------------------------------------------------------------//
        // ---------------------------RiskType 1 -----------------------------------------//
        //                      Project Implementation Risk

        riskTypeDTO = new RiskTypeDTO();
        riskTypeDTO.setId(null);
        riskTypeDTO.setItemNo(1);
        riskTypeDTO.setDescription("Project Implementation Risk");
        riskTypeDTO.setScore(0D);


        //  Risk Component 1 : Completion Risk - 50%
        RP_CompletionRiskComponentDTO rp_completionRiskComponentDTO = new RP_CompletionRiskComponentDTO();
        RiskComponentDTO completionRiskComponentDTO = rp_completionRiskComponentDTO.getCompletionRisk();

        //  Risk Component 2 : Execution Risk - 50%
        RP_ExecutionRiskDTO rp_executionRiskDTO = new RP_ExecutionRiskDTO();
        RiskComponentDTO executionRiskComponentDTO = rp_executionRiskDTO.getExecutionRiskComponentDTO();


        riskTypeDTO.addRiskComponentDTO(completionRiskComponentDTO);
        riskTypeDTO.addRiskComponentDTO(executionRiskComponentDTO);


        return  riskTypeDTO;
    }
}
