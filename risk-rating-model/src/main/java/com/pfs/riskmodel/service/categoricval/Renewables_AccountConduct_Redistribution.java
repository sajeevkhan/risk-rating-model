package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;

/**
 * Created by sajeev on 08-Jan-19.
 */
public class Renewables_AccountConduct_Redistribution {

    public RiskModelTemplate executeAccountConductWeightageReDistrobution(RiskModelTemplate riskModelTemplate) {


        Boolean redistribute = false;

        // Check if Account Conduct Redistribution needs to be done
        for (RiskType riskType: riskModelTemplate.getRiskTypes()) {
            for (RiskComponent riskComponent: riskType.getRiskComponents()){

                if (riskComponent.getDescription().contains("Account Conduct") ){
                        if ( riskComponent.getIsApplicable() == true )
                            redistribute = false;
                        else
                            redistribute = true;
                }
            }

            if (riskType.getDescription().contains("Post")) {

                if (riskType.getIsAccountConductRiskComponentPresent() == true) {

                    // If redistribution needs to be done, Reset the Weightage
                    if (redistribute == true) {
                        for (RiskComponent riskComponent : riskType.getRiskComponents()) {
                            if (riskComponent.getDescription().contains("Business"))
                                riskComponent.setWeightage(0.32);
                            if (riskComponent.getDescription().contains("Financial"))
                                riskComponent.setWeightage(0.32);
                            if (riskComponent.getDescription().contains("Management"))
                                riskComponent.setWeightage(0.20);
                            if (riskComponent.getDescription().contains("Industry"))
                                riskComponent.setWeightage(0.16);
                            if (riskComponent.getDescription().contains("Account"))
                                riskComponent.setWeightage(0.00);

                        }
                    }

                    // If redistribution is not requried, Keep the default weightages
                    if (redistribute == false) {
                        for (RiskComponent riskComponent : riskType.getRiskComponents()) {
                            if (riskComponent.getDescription().contains("Business"))
                                riskComponent.setWeightage(0.28);
                            if (riskComponent.getDescription().contains("Financial"))
                                riskComponent.setWeightage(0.28);
                            if (riskComponent.getDescription().contains("Management"))
                                riskComponent.setWeightage(0.17);
                            if (riskComponent.getDescription().contains("Industry"))
                                riskComponent.setWeightage(0.14);
                            if (riskComponent.getDescription().contains("Account"))
                                riskComponent.setWeightage(0.13);

                        }
                    }
                }
            } else {
                if (redistribute == true) {
                    for (RiskComponent riskComponent: riskType.getRiskComponents()) {
                        if (riskComponent.getDescription().contains("Execution")) {
                            riskComponent.setWeightage(0.50);
                        }

                        if (riskComponent.getDescription().contains("Completion")) {
                            riskComponent.setWeightage(0.50);
                        }
                        if (riskComponent.getDescription().contains("Account")) {
                            riskComponent.setWeightage(0.00);
                        }

                    }
                }

                if (redistribute == false) {
                    for (RiskComponent riskComponent: riskType.getRiskComponents()) {
                        if (riskComponent.getDescription().contains("Execution")) {
                            riskComponent.setWeightage(0.4350);
                        }

                        if (riskComponent.getDescription().contains("Completion")) {
                            riskComponent.setWeightage(0.4350);
                        }
                        if (riskComponent.getDescription().contains("Account")) {
                            riskComponent.setWeightage(0.1300);
                        }

                    }
                }

            }

        }

        return riskModelTemplate;
    }



}
