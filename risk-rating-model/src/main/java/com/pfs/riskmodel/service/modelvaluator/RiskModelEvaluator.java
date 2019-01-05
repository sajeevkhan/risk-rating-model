package com.pfs.riskmodel.service.modelvaluator;

/**
 * Created by sajeev on 30-Dec-18.
 */

import com.pfs.riskmodel.service.categoricval.CategoricModelValuator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.repository.ComputingMethodRepository;
import com.pfs.riskmodel.repository.ScoreTypeRepository;
import com.pfs.riskmodel.util.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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


        System.out.println("------------- EVALUATING RiskModel : " + riskModelTemplate.getDescription());

                // Risk Types
        List<RiskType> riskTypeSet = riskModelTemplate.getRiskTypes();

        // Evaluate Risk Type Set
        for (RiskType riskType: riskTypeSet) {

            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("------------- RiskType : "  + riskType.getItemNo() + " :" + riskType.getDescription());
            System.out.println("----------------------------------------------------------------------------------------");

            // Evaluate Risk Components
            for (RiskComponent riskComponent: riskType.getRiskComponents()) {


                System.out.println("----------------------------------------------------------------------------------------");
                System.out.println("------------- RiskComponent : "  + riskComponent.getItemNo() + " :" + riskComponent.getDescription());
                System.out.println("----------------------------------------------------------------------------------------");


                for (RiskFactor riskFactor: riskComponent.getRiskFactors()) {

                    System.out.println("****************************************************************************************");
                    System.out.println("------------- RiskFactor : "  + riskFactor.getItemNo() + " :" + riskFactor.getDescription());

                    for (RiskSubFactor riskSubFactor: riskFactor.getRiskSubFactors()) {
                             riskSubFactor = riskSubFactorEvaluator.evaluateRiskSubFactor(riskSubFactor);
                             System.out.println("Risk Sub Factor: " + riskSubFactor.getDescription() + " = " + riskSubFactor.getScore());
                    }
                    riskFactor = riskFactorEvaluator.evaluateRiskFactor(riskFactor);
                    System.out.println("Risk Factor: " + riskFactor.getDescription() + " = " + riskFactor.getScore());
                }
                riskComponent = riskComponentEvaluator.evaluateRiskComponent(riskComponent);
                System.out.println("Risk Component: " + riskComponent.getDescription() + " = " + riskComponent.getScore());

            }


            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            riskType = riskTypeEvaluator.evaluateRiskType(riskType);
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%      Risk Type: " + riskType.getDescription() + " = " + riskType.getScore());
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        }



        // Evaluate Rating Modifiers
        //   ----Parental Notchup will be evaluated inside this
        for (RiskRatingModifier riskRatingModifier : riskModelTemplate.getRiskRatingModifiers() ){
             riskRatingModifier =  riskRatingModifierEvaluator.evaluateRiskRatingModifier(riskRatingModifier);
        }



        // Evaluate Category Specific Valuation
        CategoricModelValuator categoricModelValuator = new CategoricModelValuator();
        riskModelTemplate = categoricModelValuator.executeCategoricValuation(riskModelTemplate);


        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%      Risk MODEL EVALUATION SUMMARY ");
        System.out.println("MODEL VALUATION ID :  " + riskModelTemplate.getId());


        for (RiskModelSummary riskModelSummary: riskModelTemplate.getRiskModelSummaries()) {

            System.out.println(riskModelSummary.getName() + " : " + riskModelSummary.getValue());


        }


        return  riskModelTemplate;
    }


}
