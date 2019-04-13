package com.pfs.riskmodel.ModelTemplates.Renewable.RiskTypes;

import com.pfs.riskmodel.ModelTemplates.Renewable.RiskComponents.ROP_BP_AccountConductRiskDTO;
import com.pfs.riskmodel.ModelTemplates.Renewable.RiskComponents.RP_CompletionRiskComponentDTO;
import com.pfs.riskmodel.ModelTemplates.Renewable.RiskComponents.RP_ExecutionRiskDTO;
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
        riskTypeDTO.setGrade(" ");
        riskTypeDTO.setIsAccountConductRiskComponentPresent(true);



        //  Risk Component 1 : Completion Risk - 43.5%
        RP_CompletionRiskComponentDTO rp_completionRiskComponentDTO = new RP_CompletionRiskComponentDTO();
        RiskComponentDTO completionRiskComponentDTO = rp_completionRiskComponentDTO.getCompletionRisk();
        completionRiskComponentDTO.setWeightage(0.435);

        //  Risk Component 2 : Execution Risk - 43.5%
        RP_ExecutionRiskDTO rp_executionRiskDTO = new RP_ExecutionRiskDTO();
        RiskComponentDTO executionRiskComponentDTO = rp_executionRiskDTO.getExecutionRiskComponentDTO();
        executionRiskComponentDTO.setWeightage(0.435);

        //  Risk Component 3 : Account Conduct Risk - 13%
        ROP_BP_AccountConductRiskDTO rop_bp_accountConductRiskDTO = new ROP_BP_AccountConductRiskDTO();
        RiskComponentDTO accountConductRiskDTO = rop_bp_accountConductRiskDTO.getAccountConductRiskDTO();
        accountConductRiskDTO.setIsApplicable(false);
        accountConductRiskDTO.setWeightage(0.13);


        riskTypeDTO.addRiskComponentDTO(completionRiskComponentDTO);
        riskTypeDTO.addRiskComponentDTO(executionRiskComponentDTO);
        riskTypeDTO.addRiskComponentDTO(accountConductRiskDTO);


        return  riskTypeDTO;
    }
}
