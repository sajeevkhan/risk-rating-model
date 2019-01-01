package com.pfs.riskmodel.Templates.InfraRoadToll.BuildPhase;

import com.pfs.riskmodel.Templates.InfraRoadToll.ParentalNotchUp.InfraRoadToll_RiskParentalNotchUp;
import com.pfs.riskmodel.Templates.InfraRoadToll.RiskRatingModifier.InfraRoadToll_RatingModifierDTO;
import com.pfs.riskmodel.Templates.InfraRoadToll.RiskTypes.InfraRoadToll_PostProjectImplRiskTypes;
import com.pfs.riskmodel.Templates.InfraRoadToll.RiskTypes.InfraRoadToll_ProjectImplRiskTypes;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import com.pfs.riskmodel.dto.RiskParentalNotchUpDTO;
import com.pfs.riskmodel.dto.RiskRatingModifierDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 29-Dec-18.
 */
public class InfraRoadToll_BuildPhaseData {
    RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();


    public RiskModelTemplateDTO getInfraRoadToll_BuildPhaseData() {


        riskModelTemplateDTO = new RiskModelTemplateDTO();

        //        Overall Project Score:        Minimum of PIR and PPIR
        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        // Model Category 5: InfraRoadProjectToll-Build
        riskModelTemplateDTO.setModelCategoryCode(7);

        riskModelTemplateDTO.setModelType(0); //Template

        riskModelTemplateDTO.setProjectRiskLevelCode("01");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Infrastructure Road Toll Build Phase");

        riskModelTemplateDTO.setProjectTypeCode("04");
        riskModelTemplateDTO.setProjectTypeDescription("Infrastructure Road Toll");

        riskModelTemplateDTO.setDescription("Infrastructure Road Toll Build Phase");
        riskModelTemplateDTO.setComputingMethodCode("03");
        riskModelTemplateDTO.setComputingMethodDescription("Minimum"); //Minimum of PIR and PPIR
        riskModelTemplateDTO.setScore(0D);


        riskModelTemplateDTO.setLoanNumber(" ");
        riskModelTemplateDTO.setLoanAmountInCrores(0D);
        riskModelTemplateDTO.setProjectName("Template Model for Infra Road Toll Build");
        riskModelTemplateDTO.setRatingDate(Date.from(Instant.now()));

        // RiskType
        //Project Risk Rating of Infrastructure Tranmission Operational Phase = Minimum of PIR and PPIR  Scores

        // Post Project Impl. Risk Types
        InfraRoadToll_PostProjectImplRiskTypes postProjectImplRiskTypes = new InfraRoadToll_PostProjectImplRiskTypes();
        RiskTypeDTO postProjectImplRiskTypeDTO = postProjectImplRiskTypes.buildPostProjectImplRiskTypes();

        // Project Impl. Risk Types
        InfraRoadToll_ProjectImplRiskTypes projectImplRiskTypes = new InfraRoadToll_ProjectImplRiskTypes();
        RiskTypeDTO projectImplRiskTypeDTO = projectImplRiskTypes.buildProjectImplRiskTypes();


        riskModelTemplateDTO.addRiskTypeDTO(postProjectImplRiskTypeDTO);


        // Rating Modifiers
        List<RiskRatingModifierDTO> riskRatingModifierDTOSet = new ArrayList<>();
        InfraRoadToll_RatingModifierDTO infraRoadToll_ratingModifierDTO = new InfraRoadToll_RatingModifierDTO();
        riskRatingModifierDTOSet = infraRoadToll_ratingModifierDTO.getRiskRatingModifierDTOs();

        riskModelTemplateDTO.setRiskRatingModifiers(riskRatingModifierDTOSet);

        // Parental Notchup
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = new RiskParentalNotchUpDTO();
        InfraRoadToll_RiskParentalNotchUp infraRoadToll_riskParentalNotchUp = new InfraRoadToll_RiskParentalNotchUp();
        riskParentalNotchUpDTO = infraRoadToll_riskParentalNotchUp.getInfraRoadToll_ParentalNotchup();

        List<RiskParentalNotchUpDTO> riskParentalNotchUpDTOSet = new ArrayList<>();
        riskParentalNotchUpDTOSet.add(riskParentalNotchUpDTO);

        riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOSet);

        return riskModelTemplateDTO;
    }
}
