package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;

/**
 * Created by sajeev on 08-Jan-19.
 */
public class InfraRoadHAM_Transmission_AccountConduct_Redistribution {

    public RiskModelTemplate executeAccountConductWeightageReDistrobution(RiskModelTemplate riskModelTemplate) {


        Boolean redistribute = false;

        // Check if Account Conduct Redistribution needs to be done
        for (RiskType riskType: riskModelTemplate.getRiskTypes()) {
            for (RiskComponent riskComponent : riskType.getRiskComponents()) {
                if (riskComponent.getDescription().contains("Account Conduct")) {
                    if (riskComponent.getIsApplicable() == true)
                        redistribute = false;
                    else
                        redistribute = true;
                }
            }
            if (riskType.getIsAccountConductRiskComponentPresent() == true) {

                // If redistribution needs to be done, Reset the Weightage
                if (redistribute == true) {
                    for (RiskComponent riskComponent : riskType.getRiskComponents()) {
                        if (riskComponent.getDescription().contains("Business"))
                            riskComponent.setWeightage(0.4023);
                        if (riskComponent.getDescription().contains("Financial"))
                            riskComponent.setWeightage(0.4598);
                        if (riskComponent.getDescription().contains("Management"))
                            riskComponent.setWeightage(0.1379);
                        if (riskComponent.getDescription().contains("Account"))
                            riskComponent.setWeightage(0.00);
                    }
                }

                // If redistribution is not requried, Keep the default weightages
                if (redistribute == false) {
                    for (RiskComponent riskComponent : riskType.getRiskComponents()) {
                        if (riskComponent.getDescription().contains("Business"))
                            riskComponent.setWeightage(0.35);
                        if (riskComponent.getDescription().contains("Financial"))
                            riskComponent.setWeightage(0.40);
                        if (riskComponent.getDescription().contains("Management"))
                            riskComponent.setWeightage(0.12);
                        if (riskComponent.getDescription().contains("Account"))
                            riskComponent.setWeightage(0.13);
                    }

                }
            }
        }

        return riskModelTemplate;
    }

    

}
