package com.pfs.riskmodel.service.modelvaluator;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskRatingModifier;
import com.pfs.riskmodel.domain.RiskRatingModifierAttribute;
import com.pfs.riskmodel.domain.RiskType;

import java.util.List;

/**
 * Created by sajeev on 30-Dec-18.
 */
public class RiskRatingModifierEvaluator {

    Double riskRatingModifierScore;

    public RiskRatingModifier evaluateRiskRatingModifier(RiskRatingModifier riskRatingModifier) {


        Boolean subInvestmentGradeCapping = false;
        Integer numberOfNotchesDown = 0;
        Integer numberOfNotchesDownItems = 0;

        for (RiskRatingModifierAttribute riskRatingModifierAttribute:riskRatingModifier.getRiskRatingModifierAttributes()){

             if (riskRatingModifier.getModifierType() == 0) {
                 if (riskRatingModifierAttribute.getYesOrNoIndicator().equals('Y') ){
                     subInvestmentGradeCapping = true;
                     break;
                 }
             }

             if (riskRatingModifier.getModifierType() == 1) {

                 if (riskRatingModifierAttribute.getYesOrNoIndicator().equals('Y') ) {
                     numberOfNotchesDownItems += 1;
                     numberOfNotchesDown += 1;
                 }
             }
        }

        riskRatingModifier.setSubInvestmentGradeCapping(subInvestmentGradeCapping);
        riskRatingModifier.setNumberOfNotchesDown(numberOfNotchesDown);

         return riskRatingModifier;


    }


    // Weighted Computation
    private Double computeWeighted ( List<RiskComponent> riskComponentSet) {

        Double score = 0D;
        // Computing Method = 01 - Weighted
        for (RiskComponent riskComponent: riskComponentSet) {
            String scoreTypeCode = riskComponent.getScoreType().getCode();
            score =  score + riskComponent.getScore() * riskComponent.getWeightage();
        }

        Math.round(score);

        return score;
    }






}
