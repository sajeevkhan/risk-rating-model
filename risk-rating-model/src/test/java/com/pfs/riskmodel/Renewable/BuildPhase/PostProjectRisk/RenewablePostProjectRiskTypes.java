package com.pfs.riskmodel.Renewable.BuildPhase.PostProjectRisk;

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
        riskTypeDTO.setItemNo(1);
        riskTypeDTO.setDescription("Post Project Implementation Risk");
        riskTypeDTO.setScore(0D);


        //  Risk Component 1 : Financial Risk - 32%
        RiskComponentDTO financialRiskComponent = new RiskComponentDTO();
        financialRiskComponent = RPP_FinancialRiskRiskComponentDTO.getFinancialRiskComponentDTO();

        //  Risk Component 1 : Management Risk - 20%
        RiskComponentDTO managementRiskComponent = new RiskComponentDTO();
        managementRiskComponent = RPP_ManagementRiskRiskComponentDTO.managementRiskComponentDTO();

        //  Risk Component 1 : Industry Risk - 16%
        RiskComponentDTO industryRiskComponent = new RiskComponentDTO();
        industryRiskComponent = RPP_IndustryRiskRiskComponentDTO.getIndustryRiskComponentDTO();

        //  Risk Component 1 : Business Risk - 32%
        RiskComponentDTO businessRiskComponent = new RiskComponentDTO();
        businessRiskComponent = RPP_BusinessRiskComponentDTO.getBusinessRiskComponentDTO();

        riskTypeDTO.addRiskComponentDTO(financialRiskComponent);
        riskTypeDTO.addRiskComponentDTO(managementRiskComponent);
        riskTypeDTO.addRiskComponentDTO(industryRiskComponent);
        riskTypeDTO.addRiskComponentDTO(businessRiskComponent);

        return  riskTypeDTO;
    }
}
