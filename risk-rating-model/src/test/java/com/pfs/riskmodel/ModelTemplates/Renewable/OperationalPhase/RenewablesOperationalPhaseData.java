package com.pfs.riskmodel.ModelTemplates.Renewable.OperationalPhase;

import com.pfs.riskmodel.ModelTemplates.Renewable.BuildPhase.Renewable_BuildPhase_RiskModelSummary;
import com.pfs.riskmodel.ModelTemplates.Renewable.ParentalNotchUp.Renewable_RiskParentalNotchUp;
import com.pfs.riskmodel.ModelTemplates.Renewable.RiskTypes.RenewablesOperatonalRiskTypes;
import com.pfs.riskmodel.dto.RiskModelSummaryDTO;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import com.pfs.riskmodel.dto.RiskParentalNotchUpDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        // Renewable-Operational
        riskModelTemplateDTO.setModelCategoryCode(2);

        riskModelTemplateDTO.setModelType(0); //Template

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


        riskModelTemplateDTO.setLoanNumber(" ");
        riskModelTemplateDTO.setLoanAmountInCrores(0D);
        riskModelTemplateDTO.setProjectName("Template Model for Renewables Operational");
        riskModelTemplateDTO.setRatingDate(Date.from(Instant.now()));


        riskModelTemplateDTO.setOverallProjectGrade("");
        riskModelTemplateDTO.setModifiedProjectGrade("");
        riskModelTemplateDTO.setAfterParentalNotchUpGrade("");
        riskModelTemplateDTO.setFinalProjectGrade("");


        // Parental NotchUP
        Renewable_RiskParentalNotchUp renewable_riskParentalNotchUp = new Renewable_RiskParentalNotchUp();
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = renewable_riskParentalNotchUp.getParentalNotchUp();
        List<RiskParentalNotchUpDTO> riskParentalNotchUpDTOSet = new ArrayList<>();
        riskParentalNotchUpDTOSet.add(riskParentalNotchUpDTO);

        riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOSet);

        Renewable_OperationalPhase_RiskModelSummary renewable_operationalPhase_riskModelSummary = new Renewable_OperationalPhase_RiskModelSummary();
        List<RiskModelSummaryDTO> riskModelSummaryDTOS = renewable_operationalPhase_riskModelSummary.getRiskModelSummary();
        riskModelTemplateDTO.setRiskModelSummaries(riskModelSummaryDTOS);


        return riskModelTemplateDTO;

    }
}
