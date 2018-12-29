package com.pfs.riskmodel.HoldingCompany;

import com.pfs.riskmodel.HoldingCompany.ParentalNotchup.HC_RiskParentalNotchUp;
import com.pfs.riskmodel.HoldingCompany.RiskRatingModifiers.HC_RatingModifierDTO;
import com.pfs.riskmodel.HoldingCompany.RiskTypes.HoldingCompanyRiskTypes;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import com.pfs.riskmodel.dto.RiskParentalNotchUpDTO;
import com.pfs.riskmodel.dto.RiskRatingModifierDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 25-Dec-18.
 */
public class HoldingCompanyData {



    RiskModelTemplateDTO riskModelTemplateDTO;

    public  RiskModelTemplateDTO buildRiskModelTemplate ( ) {


        riskModelTemplateDTO = new RiskModelTemplateDTO();


        riskModelTemplateDTO.setId(null);
        riskModelTemplateDTO.setStatus("X");
        riskModelTemplateDTO.setVersion("v1");
        riskModelTemplateDTO.setModelCategoryCode(10);

        riskModelTemplateDTO.setProjectRiskLevelCode("01");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Holding Company Risk Rating");

        riskModelTemplateDTO.setProjectTypeCode("01");
        riskModelTemplateDTO.setProjectTypeDescription("Holding Company");
        riskModelTemplateDTO.setDescription("Holding Company Risk Rating");
        riskModelTemplateDTO.setComputingMethodCode("05");
        riskModelTemplateDTO.setComputingMethodDescription("Equals");
        riskModelTemplateDTO.setScore(0D);


        //Final Holding Company Rating
        //  Parental Notch Up
        HC_RiskParentalNotchUp hc_riskParentalNotchUp = new HC_RiskParentalNotchUp();
        RiskParentalNotchUpDTO riskParentalNotchUpDTO = hc_riskParentalNotchUp.getParentalNotchUp();

        Set<RiskParentalNotchUpDTO> riskParentalNotchUpDTOs = new HashSet<>();
        riskParentalNotchUpDTOs.add(riskParentalNotchUpDTO);


        // Rating Modifier
        //Modified Holding Company Rating
        RiskRatingModifierDTO riskRatingModifierDTO = new RiskRatingModifierDTO();
        Set<RiskRatingModifierDTO> riskRatingModifierDTOs = new HashSet<>();
        HC_RatingModifierDTO hc_ratingModifierDTO = new HC_RatingModifierDTO();
        riskRatingModifierDTOs = hc_ratingModifierDTO.getRiskRatingModifierDTOs();


        // Risk Type
        // Holding Company Risk Rating
         RiskTypeDTO riskTypeDTO = new RiskTypeDTO();
         riskTypeDTO =  HoldingCompanyRiskTypes.buildRiskType();
         Set<RiskTypeDTO> riskTypeDTOs = new HashSet<>();
         riskTypeDTOs.add(riskTypeDTO);


         riskModelTemplateDTO.setRiskTypes(riskTypeDTOs);
         riskModelTemplateDTO.setRiskParentalNotchUps(riskParentalNotchUpDTOs);
         riskModelTemplateDTO.setRiskRatingModifiers(riskRatingModifierDTOs);

        return  riskModelTemplateDTO;

    }
}
