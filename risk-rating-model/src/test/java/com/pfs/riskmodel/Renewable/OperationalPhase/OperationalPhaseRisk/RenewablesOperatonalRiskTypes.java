package com.pfs.riskmodel.Renewable.OperationalPhase.OperationalPhaseRisk;

import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class RenewablesOperatonalRiskTypes {


    public static  RiskTypeDTO buildOperationalRiskTypes() {


        RiskTypeDTO riskTypeDTO = new RiskTypeDTO();


        riskTypeDTO.setId(null);
        riskTypeDTO.setItemNo(1);
        riskTypeDTO.setDescription("Renewable Operational Phase Risk Type");
        riskTypeDTO.setScore(0D);

        Set<RiskComponentDTO> riskComponentDTOs = new HashSet<>();

        ROP_BusinessRiskDTO rop_businessRiskDTO = new ROP_BusinessRiskDTO();
        riskComponentDTOs.add(rop_businessRiskDTO.getBusinessRiskDTO());


       ROP_FinancialRiskDTO rop_financialRiskDTO = new ROP_FinancialRiskDTO();
        riskComponentDTOs.add(rop_financialRiskDTO.getFinancialRiskDTO());


         ROP_IndustryRiskDTO rop_industryRiskDTO = new ROP_IndustryRiskDTO();
        riskComponentDTOs.add(rop_industryRiskDTO.getIndustryRiskDTO());

        ROP_ManagementRiskDTO rop_managementRiskDTO = new ROP_ManagementRiskDTO();
        riskComponentDTOs.add(rop_managementRiskDTO.getManagementRiskDTO());



        riskTypeDTO.setRiskComponents(riskComponentDTOs);
        return riskTypeDTO;



    }


}
