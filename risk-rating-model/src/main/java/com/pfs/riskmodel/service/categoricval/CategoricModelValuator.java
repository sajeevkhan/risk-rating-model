package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.domain.RiskModelTemplate;

/**
 * Created by sajeev on 31-Dec-18.
 */
public class CategoricModelValuator {

    public RiskModelTemplate executeCategoricValuation (RiskModelTemplate riskModelTemplate) {


        switch (riskModelTemplate.getModelCategory().getCode()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                InfraRoadHAM_BuildPhase_Valuator infraRoadHAM_buildPhase_valuator = new InfraRoadHAM_BuildPhase_Valuator();
                riskModelTemplate = infraRoadHAM_buildPhase_valuator.valuate(riskModelTemplate);
                break;
            case 6:
                InfraRoadHAM_OperationalPhase_Valuator infraRoadHAM_operationalPhase_valuator = new InfraRoadHAM_OperationalPhase_Valuator();
                riskModelTemplate = infraRoadHAM_operationalPhase_valuator.valuate(riskModelTemplate);
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;

        }



        return riskModelTemplate;
    }

}
