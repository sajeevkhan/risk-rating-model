package com.pfs.riskmodel.ModelTemplates.Renewable.RiskTypes;

import com.pfs.riskmodel.ModelTemplates.Renewable.RiskComponents.*;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class RenewablePostProjectRiskTypes {
    public static RiskTypeDTO riskTypeDTO ;

    public static RiskTypeDTO buildRiskTypes () {

        //---------------------------------------------------------------------------------//
        // ---------------------------RiskType 1 -----------------------------------------//
        //                      Post Project Implementation Risk

        riskTypeDTO = new RiskTypeDTO();
        riskTypeDTO.setId(null);
        riskTypeDTO.setItemNo(2);
        riskTypeDTO.setDescription("Post Project Implementation Risk");
        riskTypeDTO.setScore(0D);
        riskTypeDTO.setGrade(" ");



        //  Risk Component 1 : Business Risk - 28%
        RiskComponentDTO businessRiskComponent = new RiskComponentDTO();
        businessRiskComponent = RPP_BusinessRiskComponentDTO.getBusinessRiskComponentDTO();
        businessRiskComponent.setWeightage(0.28D);

        //  Risk Component 2 : Industry Risk - 14%
        RiskComponentDTO industryRiskComponent = new RiskComponentDTO();
        industryRiskComponent = RPP_IndustryRiskRiskComponentDTO.getIndustryRiskComponentDTO();
        industryRiskComponent.setWeightage(0.14D);

        //  Risk Component 3 : Financial Risk - 28%
        RiskComponentDTO financialRiskComponent = new RiskComponentDTO();
        financialRiskComponent = RPP_FinancialRiskRiskComponentDTO.getFinancialRiskComponentDTO();
        financialRiskComponent.setWeightage(0.28D);


        //  Risk Component 4 : Management Risk - 17%
        RiskComponentDTO managementRiskComponent = new RiskComponentDTO();
        managementRiskComponent = RPP_ManagementRiskRiskComponentDTO.managementRiskComponentDTO();
        managementRiskComponent.setWeightage(0.17D);

//        //  Risk Component 5 : Account Conduct Risk - 13%
        riskTypeDTO.setIsAccountConductRiskComponentPresent(true);
        ROP_BP_AccountConductRiskDTO accountConductRiskDTO = new ROP_BP_AccountConductRiskDTO();
        RiskComponentDTO accountConductRiskComponentDTO = new RiskComponentDTO();
        accountConductRiskComponentDTO = accountConductRiskDTO.getAccountConductRiskDTO();
        accountConductRiskComponentDTO.setWeightage(0.13D);


        riskTypeDTO.addRiskComponentDTO(businessRiskComponent);
        riskTypeDTO.addRiskComponentDTO(industryRiskComponent);
        riskTypeDTO.addRiskComponentDTO(financialRiskComponent);
        riskTypeDTO.addRiskComponentDTO(managementRiskComponent);
        riskTypeDTO.addRiskComponentDTO(accountConductRiskComponentDTO);


        return  riskTypeDTO;
    }
}
