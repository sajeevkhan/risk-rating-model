package com.pfs.riskmodel.HoldingCompany.RiskTypes;

import com.pfs.riskmodel.HoldingCompany.RiskComponents.HC_BusinessRiskDTO;
import com.pfs.riskmodel.HoldingCompany.RiskComponents.HC_FinancialRiskDTO;
import com.pfs.riskmodel.HoldingCompany.RiskComponents.HC_ManagementRiskDTO;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

/**
 * Created by sajeev on 25-Dec-18.
 */
public class HoldingCompanyRiskTypes {


    public static RiskTypeDTO riskTypeDTO ;

    public static RiskTypeDTO buildRiskType () {

        //---------------------------------------------------------------------------------//
        // ---------------------------RiskType 1 -----------------------------------------//
        //                      Project Implementation Risk

        riskTypeDTO = new RiskTypeDTO();
        riskTypeDTO.setId(null);
        riskTypeDTO.setItemNo(1);
        riskTypeDTO.setDescription("Holding Company Risk Rating");
        riskTypeDTO.setScore(0D);


        //  Risk Component 1 : Business Risk - 35%
         HC_BusinessRiskDTO hc_businessRiskDTO = new HC_BusinessRiskDTO();
         RiskComponentDTO businessRiskComponentDTO = hc_businessRiskDTO.getBusinessRiskDTO();

        //  Risk Component 2 : Financial Risk - 55%
        HC_FinancialRiskDTO hc_financialRiskDTO = new HC_FinancialRiskDTO();
        RiskComponentDTO financialRiskComponentDTO = hc_financialRiskDTO.getFinancialRiskDTO();


        //  Risk Component 3 : Management Risk - 10%
        HC_ManagementRiskDTO hc_managementRiskComponentDTO = new HC_ManagementRiskDTO();
        RiskComponentDTO managementRiskComponentDDTO = hc_managementRiskComponentDTO.getManagementRiskSubFactorDTO();


        riskTypeDTO.addRiskComponentDTO(businessRiskComponentDTO);
        riskTypeDTO.addRiskComponentDTO(financialRiskComponentDTO);
        riskTypeDTO.addRiskComponentDTO(managementRiskComponentDDTO);



        return  riskTypeDTO;
    }

}
