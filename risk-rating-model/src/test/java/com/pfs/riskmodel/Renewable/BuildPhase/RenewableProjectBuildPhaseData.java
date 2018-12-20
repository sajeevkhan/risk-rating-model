package com.pfs.riskmodel.Renewable.BuildPhase;

import com.pfs.riskmodel.Renewable.BuildPhase.PostProjectRisk.RenewablePostProjectRiskTypes;
import com.pfs.riskmodel.Renewable.BuildPhase.ProjectRisks.RenewableProjectRiskTypes;
import com.pfs.riskmodel.dto.*;

/**
 * Created by sajeev on 18-Dec-18.
 */
public class RenewableProjectBuildPhaseData {

    public  static  RiskModelTemplateDTO riskModelTemplateDTO;

    public  RiskModelTemplateDTO getRenewableProjectBuildPhaseData( ) {

          riskModelTemplateDTO = new RiskModelTemplateDTO();


//        Overall Project Score:        Minimum of PIR and PPIR scores

//        Project Implementation Risk (PIR)
//        Project Implementation Risk  -  Weighted Scores
//        Completion Risk -50%
//        Execution Risk 50%


        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        riskModelTemplateDTO.setModelCategoryCode(1);

        riskModelTemplateDTO.setProjectRiskLevelCode("01");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Renewables Build Phase");

        riskModelTemplateDTO.setProjectTypeCode("01");
        riskModelTemplateDTO.setProjectTypeDescription("Renewables");
        riskModelTemplateDTO.setDescription("Renewables Build Phase");
        riskModelTemplateDTO.setComputingMethodCode("02");
        riskModelTemplateDTO.setScore(0D);


        RiskTypeDTO projectImplRiskTypeDTO = RenewableProjectRiskTypes.buildRiskTypes();
        RiskTypeDTO postProjectImplRiskTypeDTO = RenewablePostProjectRiskTypes.buildRiskTypes();


        riskModelTemplateDTO.addRiskTypeDTO(projectImplRiskTypeDTO);
        riskModelTemplateDTO.addRiskTypeDTO(postProjectImplRiskTypeDTO);


        return riskModelTemplateDTO;
    }

}
