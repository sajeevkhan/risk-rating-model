package com.pfs.riskmodel.service.modelvaluator;

/**
 * Created by sajeev on 30-Dec-18.
 */

import com.pfs.riskmodel.service.categoricval.CategoricModelValuator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.pfs.riskmodel.domain.*;

import java.util.List;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Service
public class RiskModelEvaluator {


    public RiskModelTemplate evaluateRiskModel (RiskModelTemplate riskModelTemplate) {

        RiskModelTemplate riskModelTemplateEvaluated = new RiskModelTemplate();

        RiskSubFactorEvaluator riskSubFactorEvaluator = new RiskSubFactorEvaluator();
        RiskFactorEvaluator    riskFactorEvaluator = new RiskFactorEvaluator();
        RiskComponentEvaluator riskComponentEvaluator = new RiskComponentEvaluator();
        RiskTypeEvaluator      riskTypeEvaluator = new RiskTypeEvaluator();
        RiskRatingModifierEvaluator riskRatingModifierEvaluator = new RiskRatingModifierEvaluator();

        CategoricModelValuator categoricModelValuator = new CategoricModelValuator();


        // For Certain Risk Types, if the Risk Component "Account Conduct" is NOT Applicable,
        // then the Risk Component Weights needs to be redistributed
        // The redistribution process is specific for each project type
        // Therefore logic is included in the Categoric Valuator
        riskModelTemplate = categoricModelValuator.executeAccountConductWeightageReDistribution(riskModelTemplate);


        // Risk Types
        List<RiskType> riskTypeSet = riskModelTemplate.getRiskTypes();

        // Evaluate Risk Type Set
        for (RiskType riskType: riskTypeSet) {
            // Evaluate Risk Components
            for (RiskComponent riskComponent: riskType.getRiskComponents()) {
                for (RiskFactor riskFactor: riskComponent.getRiskFactors()) {
                    for (RiskSubFactor riskSubFactor: riskFactor.getRiskSubFactors()) {
                             riskSubFactor = riskSubFactorEvaluator.evaluateRiskSubFactor(riskSubFactor);
                     }
                    riskFactor = riskFactorEvaluator.evaluateRiskFactor(riskFactor);
                 }
                riskComponent = riskComponentEvaluator.evaluateRiskComponent(riskComponent);
            }

            riskType = riskTypeEvaluator.evaluateRiskType(riskType);
     }


        // Evaluate Rating Modifiers
        if (riskModelTemplate.getRiskRatingModifiers() != null) {
            for (RiskRatingModifier riskRatingModifier : riskModelTemplate.getRiskRatingModifiers()) {
                riskRatingModifier = riskRatingModifierEvaluator.evaluateRiskRatingModifier(riskRatingModifier);
            }
        }

        // Evaluate Category Specific Valuation
        //   Rating Modifiers and Parental Notchup will be applied inside this.
        riskModelTemplate = categoricModelValuator.executeCategoricValuation(riskModelTemplate);




        System.out.println("  ----------------- Insisde RISK MODEL VALUATOR --------------");
        System.out.println("  ----------------- Printing RISK MODEL Summary --------------");

        for (RiskModelSummary riskModelSummary: riskModelTemplate.getRiskModelSummaries()) {
            System.out.println(riskModelSummary.getName() + " : " + riskModelSummary.getScore());

        }


        return  riskModelTemplate;
    }


}
