package com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.OperationalPhase;

import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.ParentalNotchUp.InfraRoadHAM_RiskParentalNotchUp;
import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.RiskRatingModifier.InfraRoadHAM_RatingModifierDTO;
import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.RiskTypes.InfraRoadHAM_PostProjectImplRiskTypes;
import com.pfs.riskmodel.ModelTemplates.ParentalNotchupTemplate;
import com.pfs.riskmodel.dto.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 29-Dec-18.
 */
public class InfraRoadHAM_OperationalPhaseData {


    RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();


    public RiskModelTemplateDTO getInfraRoadHAM_OperationalPhaseData() {


        riskModelTemplateDTO = new RiskModelTemplateDTO();

        //        Overall Project Score:        Risk Type Score
        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        // Model Category 6: InfraRoadProjectHybridAnnuity-Operational
        riskModelTemplateDTO.setModelCategoryCode(6);

        riskModelTemplateDTO.setModelType(0); //Template

        riskModelTemplateDTO.setPurposeCode("01");
        riskModelTemplateDTO.setPurposeDescription("Project Assessment");
        riskModelTemplateDTO.setProcessInstanceId(" ");
        riskModelTemplateDTO.setWorkflowStatusCode("01");
        riskModelTemplateDTO.setWorkflowStatusDescription("Created");

        riskModelTemplateDTO.setProjectRiskLevelCode("02");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Infrastructure Road HAM Operational Phase");

        riskModelTemplateDTO.setRiskProjectTypeCode("03");
        riskModelTemplateDTO.setRiskProjectTypeDescription("Infrastructure Road HAM");

        riskModelTemplateDTO.setDescription("Infrastructure Road HAM Operational Phase");
        riskModelTemplateDTO.setComputingMethodCode("05");
        riskModelTemplateDTO.setComputingMethodDescription("Equals");
        riskModelTemplateDTO.setScore(0D);


        riskModelTemplateDTO.setLoanNumber(" ");
        riskModelTemplateDTO.setLoanAmountInCrores(0D);
        riskModelTemplateDTO.setProjectName("Template Model for Infra. Road HAM Operational Phase");
        riskModelTemplateDTO.setRatingDate(Date.from(Instant.now()));

        riskModelTemplateDTO.setOverallProjectGrade(" ");
        riskModelTemplateDTO.setModifiedProjectGrade(" ");
        riskModelTemplateDTO.setAfterParentalNotchUpGrade(" ");
        riskModelTemplateDTO.setFinalProjectGrade(" ");



        // RiskType
        //Project Risk Rating of Infrastructure Road HAM Operational Phase = Post Project Implementation Score

        InfraRoadHAM_PostProjectImplRiskTypes infraRoadHAM_postProjectImplRiskTypes = new InfraRoadHAM_PostProjectImplRiskTypes();
        RiskTypeDTO riskTypeDTO = infraRoadHAM_postProjectImplRiskTypes.buildPostProjectImplRiskTypes();
        riskModelTemplateDTO.addRiskTypeDTO(riskTypeDTO);


        // Rating Modifiers
        List<RiskRatingModifierDTO> riskRatingModifierDTOSet = new ArrayList<>();
        InfraRoadHAM_RatingModifierDTO infraRoadHAM_ratingModifierDTO = new InfraRoadHAM_RatingModifierDTO();
        riskRatingModifierDTOSet = infraRoadHAM_ratingModifierDTO.getRiskRatingModifierDTOs();

        riskModelTemplateDTO.setRiskRatingModifiers(riskRatingModifierDTOSet);


        //  Parental Notch Up
        ParentalNotchupTemplate parentalNotchupTemplate = new ParentalNotchupTemplate();
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = parentalNotchupTemplate.getParentalNotchUp();

        List<RiskParentalNotchUpDTO> riskParentalNotchUpDTOSet = new ArrayList<>();
        riskParentalNotchUpDTOSet.add(riskParentalNotchUpDTO);

        riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOSet);

        InfraRoadHAM_OperationalPhase_RiskModelSummary infraRoadHAM_operationalPhase_riskModelSummary = new InfraRoadHAM_OperationalPhase_RiskModelSummary();
        List<RiskModelSummaryDTO> riskModelSummaryDTOS = infraRoadHAM_operationalPhase_riskModelSummary.getRiskModelSummary();
        riskModelTemplateDTO.setRiskModelSummaries(riskModelSummaryDTOS);


        return riskModelTemplateDTO;

    }

}
