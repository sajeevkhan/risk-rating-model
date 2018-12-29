package com.pfs.riskmodel.Renewable.BuildPhase;

import com.pfs.riskmodel.Renewable.ParentalNotchUp.Renewable_RiskParentalNotchUp;
import com.pfs.riskmodel.Renewable.RiskTypes.RenewablePostProjectRiskTypes;
import com.pfs.riskmodel.Renewable.RiskTypes.RenewableProjectRiskTypes;
import com.pfs.riskmodel.dto.*;

import java.util.HashSet;
import java.util.Set;

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
        riskModelTemplateDTO.setComputingMethodCode("03");
        riskModelTemplateDTO.setComputingMethodDescription("Minimum");
        riskModelTemplateDTO.setScore(0D);


        RiskTypeDTO projectImplRiskTypeDTO = RenewableProjectRiskTypes.buildRiskTypes();
        RiskTypeDTO postProjectImplRiskTypeDTO = RenewablePostProjectRiskTypes.buildRiskTypes();

        // Risk Types

        riskModelTemplateDTO.addRiskTypeDTO(projectImplRiskTypeDTO);
        riskModelTemplateDTO.addRiskTypeDTO(postProjectImplRiskTypeDTO);


        // Parental NotchUP
        Renewable_RiskParentalNotchUp renewable_riskParentalNotchUp = new Renewable_RiskParentalNotchUp();
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = renewable_riskParentalNotchUp.getParentalNotchUp();
        Set<RiskParentalNotchUpDTO> riskParentalNotchUpDTOSet = new HashSet<>();
        riskParentalNotchUpDTOSet.add(riskParentalNotchUpDTO);

        riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOSet);


        return riskModelTemplateDTO;
    }

}
