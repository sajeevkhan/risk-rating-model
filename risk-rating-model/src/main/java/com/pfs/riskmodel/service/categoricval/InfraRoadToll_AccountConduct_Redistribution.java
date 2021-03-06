package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;

/**
 * Created by sajeev on 08-Jan-19.
 */
public class InfraRoadToll_AccountConduct_Redistribution {

    public RiskModelTemplate executeAccountConductWeightageReDistrobution(RiskModelTemplate riskModelTemplate) {


        Boolean redistribute = false;

        // Check if Account Conduct Redistribution needs to be done
        for (RiskType riskType: riskModelTemplate.getRiskTypes()) {
            for (RiskComponent riskComponent: riskType.getRiskComponents()){
                if (riskComponent.getDescription().contains("Account Conduct") ){
                        if ( riskComponent.getIsApplicable() == false )
                            redistribute = true;
                }
            }

            // If redistribution needs to be done, Reset the Weightage
            if (redistribute == true) {
                for (RiskComponent riskComponent : riskType.getRiskComponents()) {
                    if (riskComponent.getDescription().contains("Business"))
                        riskComponent.setWeightage(0.40);
                    if (riskComponent.getDescription().contains("Financial"))
                        riskComponent.setWeightage(0.14);
                    if (riskComponent.getDescription().contains("Management"))
                        riskComponent.setWeightage(0.46);
                    if (riskComponent.getDescription().contains("Account"))
                        riskComponent.setWeightage(0.00);

                }
            }

        }

        return riskModelTemplate;
    }



}
