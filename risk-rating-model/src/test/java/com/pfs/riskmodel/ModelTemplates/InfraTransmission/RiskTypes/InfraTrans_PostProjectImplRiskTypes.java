package com.pfs.riskmodel.ModelTemplates.InfraTransmission.RiskTypes;

import com.pfs.riskmodel.ModelTemplates.InfraTransmission.RiskComponents.PPIR_AccountConductDTO;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.RiskComponents.PPIR_BusinessRisk;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.RiskComponents.PPIR_FinancialRiskDTO;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.RiskComponents.PPIR_ManagementRiskDTO;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class InfraTrans_PostProjectImplRiskTypes {

    public  RiskTypeDTO buildPostProjectImplRiskTypes() {


        RiskTypeDTO riskTypeDTO = new RiskTypeDTO();



        riskTypeDTO = new RiskTypeDTO();
        riskTypeDTO.setId(null);
        riskTypeDTO.setItemNo(1);
        riskTypeDTO.setDescription("Infra Transmission Post Project Implementation Risk Type");
        riskTypeDTO.setScore(0D);
        riskTypeDTO.setGrade(" ");



        // OVERALL WEIGHTS FOR PPIR
        // 1. Business Risk  35%
        PPIR_BusinessRisk ppir_businessRisk = new PPIR_BusinessRisk();
        RiskComponentDTO businessRiskComponentDTO = ppir_businessRisk.getBusinessRiskDTO();

        // 2. Financial Risk 40%
        PPIR_FinancialRiskDTO ppir_financialRiskDTO = new PPIR_FinancialRiskDTO();
        RiskComponentDTO financialRiskComponentDTO = ppir_financialRiskDTO.getFinancialRiskDTO();

        // 3. Management Risk 12%
        PPIR_ManagementRiskDTO ppir_managementRiskDTO = new PPIR_ManagementRiskDTO();
        RiskComponentDTO managementRiskComponentDTO = ppir_managementRiskDTO.getManagementRiskDTO();

        // 4. Account Conduct Risk 13%
        PPIR_AccountConductDTO ppir_accountConductDTO = new PPIR_AccountConductDTO();
        RiskComponentDTO accountConductRiskComponentDTO = ppir_accountConductDTO.getAccountConductDTO();

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
