package com.pfs.riskmodel.riskratingmodel.RenewableProjectBuildPhase;

import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.dto.*;

/**
 * Created by sajeev on 18-Dec-18.
 */
public class RenewableProjectBuildData {

    public  static  RiskModelTemplateDTO riskModelTemplateDTO;

    public static RiskModelTemplate getRenewableProjectBuildPhaseData( RiskModelTemplate riskModelTemplate) {


        RiskTypeDTO riskTypeDTO = new RiskTypeDTO();
        riskTypeDTO = RenewableProjectPostProject.buildRiskTypes();



        riskModelTemplateDTO.addRiskTypeDTO(riskTypeDTO);

        return riskModelTemplate;
    }

}
