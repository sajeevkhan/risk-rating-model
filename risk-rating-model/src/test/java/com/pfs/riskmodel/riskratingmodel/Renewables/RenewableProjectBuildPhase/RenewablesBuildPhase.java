package com.pfs.riskmodel.riskratingmodel.Renewables.RenewableProjectBuildPhase;

import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;
import com.pfs.riskmodel.riskratingmodel.Renewables.RenewableProjectBuildPhase.RiskTypes.RenewablesBuildPhasePostProjectImplementationRiskTypesData;
import com.pfs.riskmodel.riskratingmodel.Renewables.RenewableProjectBuildPhase.RiskTypes.RenewablesBuildPhaseProjectImplementationRiskTypesData;

/**
 * Created by sajeev on 18-Dec-18.
 */
public class RenewablesBuildPhase {

    public  static  RiskModelTemplateDTO riskModelTemplateDTO;

    public static RiskModelTemplate getRenewableProjectBuildPhaseData( RiskModelTemplate riskModelTemplate) {




//        Overall Project Score:        Minimum of PIR and PPIR scores


//        Project Implementation Risk (PIR)

//        Project Implementation Risk  -  Weighted Scores
//        Completion Risk -50%
//        Execution Risk 50%
         RiskTypeDTO projectImplriskTypeDTO = new RiskTypeDTO();
         projectImplriskTypeDTO = RenewablesBuildPhaseProjectImplementationRiskTypesData.buildRiskTypes();
         riskModelTemplateDTO.addRiskTypeDTO(projectImplriskTypeDTO);


//        Post Project Implementation Risk - Weighted Scores
//        Financial Risk 32%
//        Management Risk 20%
//        Industry Risk 16%
//        Business Risk 32%

         RiskTypeDTO postProjectImplRiskTypeDTO = new RiskTypeDTO();
         postProjectImplRiskTypeDTO = RenewablesBuildPhasePostProjectImplementationRiskTypesData.buildRiskTypes();
         riskModelTemplateDTO.addRiskTypeDTO(postProjectImplRiskTypeDTO);


        return riskModelTemplate;
    }

}
