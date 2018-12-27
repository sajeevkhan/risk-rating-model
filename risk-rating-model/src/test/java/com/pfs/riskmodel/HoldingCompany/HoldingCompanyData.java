package com.pfs.riskmodel.HoldingCompany;

import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import com.pfs.riskmodel.dto.RiskRatingModifierDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;

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
        riskModelTemplateDTO.setModelCategoryCode(1);

        riskModelTemplateDTO.setProjectRiskLevelCode("01");
        riskModelTemplateDTO.setProjectRiskLevelDescription("Holding Company Risk Rating");

        riskModelTemplateDTO.setProjectTypeCode("01");
        riskModelTemplateDTO.setProjectTypeDescription("Holding Company");
        riskModelTemplateDTO.setDescription("Holding Company Risk Rating");
        riskModelTemplateDTO.setComputingMethodCode("05");
        riskModelTemplateDTO.setComputingMethodDescription("Equals");
        riskModelTemplateDTO.setScore(0D);


        //Final Holding Company Rating
        // Rating Modifier - Parental Notch Up


        // Rating Modifier
        //Modified Holding Company Rating
        RiskRatingModifierDTO riskRatingModifierDTO = new RiskRatingModifierDTO();




        // Risk Type
        // Holding Company Risk Rating
         RiskTypeDTO riskTypeDTO = new RiskTypeDTO();
         riskTypeDTO =  HoldingCompanyRiskTypes.buildRiskType();


        return  riskModelTemplateDTO;

    }
}
