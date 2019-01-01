package com.pfs.riskmodel.Templates.InfraRoadToll.RiskTypes;

import com.pfs.riskmodel.Templates.InfraRoadToll.RiskComponents.IRToll_PPIR_AccountConductDTO;
import com.pfs.riskmodel.Templates.InfraRoadToll.RiskComponents.IRToll_PPIR_BusinessRisk;
import com.pfs.riskmodel.Templates.InfraRoadToll.RiskComponents.IRToll_PPIR_FinancialRiskDTO;
import com.pfs.riskmodel.Templates.InfraRoadToll.RiskComponents.IRToll_PPIR_ManagementRiskDTO;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

/**
 * Created by sajeev on 29-Dec-18.
 */
public class InfraRoadToll_PostProjectImplRiskTypes {

    public RiskTypeDTO buildPostProjectImplRiskTypes() {


    RiskTypeDTO riskTypeDTO = new RiskTypeDTO();

        riskTypeDTO = new RiskTypeDTO();
        riskTypeDTO.setId(null);
        riskTypeDTO.setItemNo(1);
        riskTypeDTO.setDescription("Infra Road HAM Project Implementation Risk Type");
        riskTypeDTO.setScore(0D);


    // OVERALL WEIGHTS FOR PPIR
    // 1. Business Risk  50%
        IRToll_PPIR_BusinessRisk irToll_ppir_businessRisk = new IRToll_PPIR_BusinessRisk();
    RiskComponentDTO businessRiskComponentDTO = irToll_ppir_businessRisk.getBusinessRiskDTO();

    // 2. Financial Risk 30%
        IRToll_PPIR_FinancialRiskDTO irToll_ppir_financialRiskDTO = new IRToll_PPIR_FinancialRiskDTO();
    RiskComponentDTO financialRiskComponentDTO = irToll_ppir_financialRiskDTO.getFinancialRiskDTO();

    // 3. Management Risk 10%
        IRToll_PPIR_ManagementRiskDTO irToll_ppir_managementRiskDTO = new IRToll_PPIR_ManagementRiskDTO();
    RiskComponentDTO managementRiskComponentDTO = irToll_ppir_managementRiskDTO.getManagementRiskDTO();

    // 4. Account Conduct Risk 10%
        IRToll_PPIR_AccountConductDTO irToll_ppir_accountConductDTO = new IRToll_PPIR_AccountConductDTO();
    RiskComponentDTO accountConductRiskComponentDTO = irToll_ppir_accountConductDTO.getAccountConductDTO();

    //------------------------------------------------------------------------------------------------------------------------------
    //If account conduct information is not available,
    //                              then (due to re-distribution of weights on pro-rata basis) weightages would apply as follows:
    //Business Risk - 56%
    //Financial Risk - 33%
    //Management Risk 11%
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
