package com.pfs.riskmodel.service.modelvaluator;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.domain.RiskType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by sajeev on 30-Dec-18.
 */
public class RiskTypeEvaluator {

    Double riskTypeScore;

    public RiskType evaluateRiskType(RiskType riskType) {


        // Initialize Risk Type Score
        riskTypeScore = riskType.getScore();
        riskTypeScore = 0D;

        // Risk Types - ONLY WEIGHTED METHOD is Implemented
        riskTypeScore = computeWeighted(riskType.getRiskComponents());

        String riskTypeCalculation = getCalculation(riskType.getRiskComponents());
        riskTypeCalculation = riskTypeCalculation + " = " + riskTypeScore;
        riskType.setRiskTypeCalculation(riskTypeCalculation);


        riskType.setScore(riskTypeScore);
        return riskType;
    }


    // Weighted Computation
    private Double computeWeighted ( List<RiskComponent> riskComponentSet) {

        Double score = 0D;
        // Computing Method = 01 - Weighted
        for (RiskComponent riskComponent: riskComponentSet) {
            String scoreTypeCode = riskComponent.getScoreType().getCode();
            score =  score + riskComponent.getScore() * riskComponent.getWeightage();
        }

        score = Utils.round(score);
        return score;
    }

    private String getCalculation (List<RiskComponent> riskComponents) {

        String riskTypeCalculation = "Calculation : ";

        int i = 0; int j = riskComponents.size();

        for (RiskComponent riskComponent: riskComponents ) {
            i++;
            // Single Entry
            if (i == 1 && i == j) {
                riskTypeCalculation = riskTypeCalculation + " " + riskComponent.getScore().toString() + " * " + riskComponent.getWeightage().toString()  ;
                continue;
            }
            // First Entry
            if (i == 1 ) {
                riskTypeCalculation = riskTypeCalculation + " " + riskComponent.getScore().toString() + " * " + riskComponent.getWeightage().toString() + " + ";
                continue;
            }
            //Middle Entires
            if (i!= 1 && i < j && i!=j) {
                riskTypeCalculation = riskTypeCalculation + " " + riskComponent.getScore().toString() + " * " + riskComponent.getWeightage().toString() + " + ";;
                continue;
            }
            // Last Entry
            if (i == j && j != 1) {
                riskTypeCalculation = riskTypeCalculation + " " + riskComponent.getScore().toString() + " * " + riskComponent.getWeightage().toString();
                continue;
            }

        }

        return riskTypeCalculation;

    }




}
