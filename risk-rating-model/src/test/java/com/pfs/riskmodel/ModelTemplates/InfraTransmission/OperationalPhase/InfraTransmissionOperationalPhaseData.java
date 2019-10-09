package com.pfs.riskmodel.ModelTemplates.InfraTransmission.OperationalPhase;

import com.pfs.riskmodel.ModelTemplates.AllParentalNotchupTemplate;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.RiskTypes.InfraTrans_PostProjectImplRiskTypes;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.RiskRatingModifiers.InfraTrans_RatingModifierDTO;
import com.pfs.riskmodel.dto.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class InfraTransmissionOperationalPhaseData {


    RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();


    public RiskModelTemplateDTO getInfraTransmissionOperationalPhaseData() {


        riskModelTemplateDTO = new RiskModelTemplateDTO();

        //        Overall Project Score:        Risk Type Score
        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        // Model Category 4: InfraTransmission-Operational
        riskModelTemplateDTO.setModelCategoryCode(4);

        riskModelTemplateDTO.setModelType(0); //Template

        riskModelTemplateDTO.setPurposeCode("01");
        riskModelTemplateDTO.setPurposeDescription("Project Assessment");
        riskModelTemplateDTO.setProcessInstanceId(" ");
        riskModelTemplateDTO.setWorkflowStatusCode("01");
        riskModelTemplateDTO.setWorkflowStatusDescription("Created");


        riskModelTemplateDTO.setProjectRiskLevelCode("02");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Infrastructure Transmission Operational Phase");

        riskModelTemplateDTO.setRiskProjectTypeCode("02");
        riskModelTemplateDTO.setRiskProjectTypeDescription("Infrastructure Transmission");

        riskModelTemplateDTO.setDescription("Infrastructure Transmission Operational Phase");
        riskModelTemplateDTO.setComputingMethodCode("05");
        riskModelTemplateDTO.setComputingMethodDescription("Equals");
        riskModelTemplateDTO.setScore(0D);

        riskModelTemplateDTO.setLoanNumber(" ");
        riskModelTemplateDTO.setLoanAmountInCrores(0D);
        riskModelTemplateDTO.setProjectName("Template Model for Infra Transmission Operational");
        riskModelTemplateDTO.setRatingDate(Date.from(Instant.now()));

        riskModelTemplateDTO.setOverallProjectGrade(" ");
        riskModelTemplateDTO.setModifiedProjectGrade(" ");
        riskModelTemplateDTO.setAfterParentalNotchUpGrade(" ");
        riskModelTemplateDTO.setFinalProjectGrade(" ");

        riskModelTemplateDTO.setApplyParentalNotchup(false);
        riskModelTemplateDTO.setApplyParentalNotchup(false);


        // RiskType
        //Project Risk Rating of Infrastructure Tranmission Operational Phase = Post Project Implementation Score
        InfraTrans_PostProjectImplRiskTypes infraTransPostProjectImplRiskTypes = new InfraTrans_PostProjectImplRiskTypes();
        RiskTypeDTO riskTypeDTO = infraTransPostProjectImplRiskTypes.buildPostProjectImplRiskTypes();

        riskModelTemplateDTO.addRiskTypeDTO(riskTypeDTO);


        // Rating Modifiers
        List<RiskRatingModifierDTO> riskRatingModifierDTOSet = new ArrayList<>();
        InfraTrans_RatingModifierDTO infraTrans_ratingModifierDTO = new InfraTrans_RatingModifierDTO();
        riskRatingModifierDTOSet = infraTrans_ratingModifierDTO.getRiskRatingModifierDTOs();

        riskModelTemplateDTO.setRiskRatingModifiers(riskRatingModifierDTOSet);


        //  Parental Notch Up
        AllParentalNotchupTemplate parentalNotchupTemplate = new AllParentalNotchupTemplate();
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = parentalNotchupTemplate.getParentalNotchUp();

        List<RiskParentalNotchUpDTO> riskParentalNotchUpDTOSet = new ArrayList<>();
        riskParentalNotchUpDTOSet.add(riskParentalNotchUpDTO);

        riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOSet);


        InfraTransmission_OperationalPhase_RiskModelSummary infraTransmission_operationalPhase_riskModelSummary = new InfraTransmission_OperationalPhase_RiskModelSummary();
        List<RiskModelSummaryDTO> riskModelSummaryDTOS = infraTransmission_operationalPhase_riskModelSummary.getRiskModelSummary();
        riskModelTemplateDTO.setRiskModelSummaries(riskModelSummaryDTOS);


        return riskModelTemplateDTO;

    }

}
