package com.pfs.riskmodel.riskratingmodel.Renewables.RenewableProjectBuildPhase.RiskTypes;

import com.pfs.riskmodel.dto.*;
import com.pfs.riskmodel.riskratingmodel.Renewables.RenewableProjectBuildPhase.RiskTypes.RiskComponents.BuildPhaseBusinessRiskComponentDTO;
import com.pfs.riskmodel.riskratingmodel.Renewables.RenewableProjectBuildPhase.RiskTypes.RiskComponents.BuildPhaseFinancialRiskRiskComponentDTO;
import com.pfs.riskmodel.riskratingmodel.Renewables.RenewableProjectBuildPhase.RiskTypes.RiskComponents.BuildPhaseIndustryRiskRiskComponentDTO;
import com.pfs.riskmodel.riskratingmodel.Renewables.RenewableProjectBuildPhase.RiskTypes.RiskComponents.BuildPhaseManagementRiskRiskComponentDTO;

/**
 * Created by sajeev on 18-Dec-18.
 */
public class RenewablesBuildPhasePostProjectImplementationRiskTypesData {


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
        financialRiskComponent = BuildPhaseFinancialRiskRiskComponentDTO.getFinancialRiskComponentDTO();

        //  Risk Component 1 : Management Risk - 20%
        RiskComponentDTO managementRiskComponent = new RiskComponentDTO();
        managementRiskComponent = BuildPhaseManagementRiskRiskComponentDTO.managementRiskComponentDTO();

        //  Risk Component 1 : Industry Risk - 16%
        RiskComponentDTO industryRiskComponent = new RiskComponentDTO();
        industryRiskComponent = BuildPhaseIndustryRiskRiskComponentDTO.getIndustryRiskComponentDTO();

        //  Risk Component 1 : Business Risk - 32%
        RiskComponentDTO businessRiskComponent = new RiskComponentDTO();
        businessRiskComponent = BuildPhaseBusinessRiskComponentDTO.getBusinessRiskComponentDTO();

        riskTypeDTO.addRiskComponentDTO(financialRiskComponent);
        riskTypeDTO.addRiskComponentDTO(managementRiskComponent);
        riskTypeDTO.addRiskComponentDTO(industryRiskComponent);
        riskTypeDTO.addRiskComponentDTO(businessRiskComponent);

        return  riskTypeDTO;
    }
}
