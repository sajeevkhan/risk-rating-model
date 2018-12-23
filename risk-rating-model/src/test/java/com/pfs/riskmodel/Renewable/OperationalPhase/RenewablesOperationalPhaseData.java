package com.pfs.riskmodel.Renewable.OperationalPhase;

import com.pfs.riskmodel.Renewable.BuildPhase.PostProjectRisk.RenewablePostProjectRiskTypes;
import com.pfs.riskmodel.Renewable.BuildPhase.ProjectRisks.RenewableProjectRiskTypes;
import com.pfs.riskmodel.Renewable.OperationalPhase.OperationalPhaseRisk.RenewablesOperatonalRiskTypes;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class RenewablesOperationalPhaseData {


    private  static RiskModelTemplateDTO riskModelTemplateDTO;

    public RiskModelTemplateDTO buildRiskModelTemplate() {

        riskModelTemplateDTO = new RiskModelTemplateDTO();

        //        Overall Project Score:        Risk Type Score
        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        riskModelTemplateDTO.setModelCategoryCode(2);

        riskModelTemplateDTO.setProjectRiskLevelCode("02");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Renewables Operational Phase");

        riskModelTemplateDTO.setProjectTypeCode("01");
        riskModelTemplateDTO.setProjectTypeDescription("Renewables");
        riskModelTemplateDTO.setDescription("Renewables Operational Phase");
        riskModelTemplateDTO.setComputingMethodCode("01");
        riskModelTemplateDTO.setComputingMethodDescription("Normal");
        riskModelTemplateDTO.setScore(0D);


        RiskTypeDTO operationPhaseRiskTypeDTO = RenewablesOperatonalRiskTypes.buildOperationalRiskTypes();


        riskModelTemplateDTO.addRiskTypeDTO(operationPhaseRiskTypeDTO);


        return riskModelTemplateDTO;

    }
}
