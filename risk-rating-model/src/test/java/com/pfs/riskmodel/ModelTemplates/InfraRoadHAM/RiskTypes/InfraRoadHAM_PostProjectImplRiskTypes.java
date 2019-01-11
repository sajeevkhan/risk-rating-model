package com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.RiskTypes;

import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.RiskComponents.*;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

/**
 * Created by sajeev on 29-Dec-18.
 */
public class InfraRoadHAM_PostProjectImplRiskTypes {

    public RiskTypeDTO buildPostProjectImplRiskTypes() {


    RiskTypeDTO riskTypeDTO = new RiskTypeDTO();

        riskTypeDTO = new RiskTypeDTO();
        riskTypeDTO.setId(null);
        riskTypeDTO.setItemNo(1);
        riskTypeDTO.setDescription("Infra Road HAM Post Project Implementation Risk");
        riskTypeDTO.setScore(0D);
        riskTypeDTO.setGrade(" ");



        // OVERALL WEIGHTS FOR PPIR
    // 1. Business Risk  35%
    IRHAM_PPIR_BusinessRisk irham_ppir_businessRisk = new IRHAM_PPIR_BusinessRisk();
    RiskComponentDTO businessRiskComponentDTO = irham_ppir_businessRisk.getBusinessRiskDTO();

    // 2. Financial Risk 40%
    IRHAM_PPIR_FinancialRiskDTO irham_ppir_financialRiskDTO = new IRHAM_PPIR_FinancialRiskDTO();
    RiskComponentDTO financialRiskComponentDTO = irham_ppir_financialRiskDTO.getFinancialRiskDTO();

    // 3. Management Risk 12%
    IRHAM_PPIR_ManagementRiskDTO irham_ppir_managementRiskDTO = new IRHAM_PPIR_ManagementRiskDTO();
    RiskComponentDTO managementRiskComponentDTO = irham_ppir_managementRiskDTO.getManagementRiskDTO();

    // 4. Account Conduct Risk 13%
    IRHAM_PPIR_AccountConductDTO irham_ppir_accountConductDTO = new IRHAM_PPIR_AccountConductDTO();
    RiskComponentDTO accountConductRiskComponentDTO = irham_ppir_accountConductDTO.getAccountConductDTO();

    //------------------------------------------------------------------------------------------------------------------------------
    //If account conduct information is not available,
    //                              then (due to re-distribution of weights on pro-rata basis) weightages would apply as follows:
    //Business Risk - 40%
    //Financial Risk - 14%
    //Management Risk 46%
    // While Building the Template, we go with the Overall Weights including Account Conduct Risk
    // During Evaluation, we will determine if Account Risk is present or not and then redistribute the weights if necessary

    //------------------------------------------------------------------------------------------------------------------------------


        riskTypeDTO.addRiskComponentDTO(businessRiskComponentDTO);
        riskTypeDTO.addRiskComponentDTO(financialRiskComponentDTO);
        riskTypeDTO.addRiskComponentDTO(managementRiskComponentDTO);
        riskTypeDTO.addRiskComponentDTO(accountConductRiskComponentDTO);


        return riskTypeDTO;
}


    }
