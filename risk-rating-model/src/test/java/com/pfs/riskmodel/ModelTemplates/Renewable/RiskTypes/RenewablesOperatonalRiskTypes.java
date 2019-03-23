package com.pfs.riskmodel.ModelTemplates.Renewable.RiskTypes;

import com.pfs.riskmodel.ModelTemplates.Renewable.RiskComponents.*;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class RenewablesOperatonalRiskTypes {


    public static  RiskTypeDTO buildOperationalRiskTypes() {


        RiskTypeDTO riskTypeDTO = new RiskTypeDTO();


        riskTypeDTO.setId(null);
        riskTypeDTO.setItemNo(1);
        riskTypeDTO.setDescription("Operational Phase Risk");
        riskTypeDTO.setScore(0D);
        riskTypeDTO.setGrade(" ");
        riskTypeDTO.setIsAccountConductRiskComponentPresent(true);


        List<RiskComponentDTO> riskComponentDTOs = new ArrayList<>();

        ROP_BusinessRiskDTO rop_businessRiskDTO = new ROP_BusinessRiskDTO();
        riskComponentDTOs.add(rop_businessRiskDTO.getBusinessRiskDTO());


        ROP_FinancialRiskDTO rop_financialRiskDTO = new ROP_FinancialRiskDTO();
        riskComponentDTOs.add(rop_financialRiskDTO.getFinancialRiskDTO());


         ROP_IndustryRiskDTO rop_industryRiskDTO = new ROP_IndustryRiskDTO();
        riskComponentDTOs.add(rop_industryRiskDTO.getIndustryRiskDTO());

        ROP_ManagementRiskDTO rop_managementRiskDTO = new ROP_ManagementRiskDTO();
        riskComponentDTOs.add(rop_managementRiskDTO.getManagementRiskDTO());

        ROP_BP_AccountConductRiskDTO rop_bp_accountConductRiskDTO = new ROP_BP_AccountConductRiskDTO();
        riskComponentDTOs.add(rop_bp_accountConductRiskDTO.getAccountConductRiskDTO());


        riskTypeDTO.setRiskComponents(riskComponentDTOs);
        return riskTypeDTO;



    }


}
