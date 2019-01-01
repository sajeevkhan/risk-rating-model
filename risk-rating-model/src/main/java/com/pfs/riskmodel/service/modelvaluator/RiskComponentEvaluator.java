package com.pfs.riskmodel.service.modelvaluator;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.domain.RiskSubFactor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by sajeev on 30-Dec-18.
 */
public class RiskComponentEvaluator {

    Double riskComponentScore;

    public RiskComponent evaluateRiskComponent(RiskComponent riskComponent) {

       String computingMethodCode =  riskComponent.getComputingMethod().getCode();

        //String scoreTypeCode = riskFactor.getScoreType().getCode();

        // Initialize Risk Component Score
        riskComponentScore = riskComponent.getScore();
        riskComponentScore = 0D;


       switch (computingMethodCode) {
           case "01": // Weighted
               riskComponentScore = computeWeighted(riskComponent.getRiskFactors());
                break;
           case "02": // Sum
               riskComponentScore = computeSum(riskComponent.getRiskFactors());
                break;
           case "03": // Minimum
               riskComponentScore = computeMaxOrMin(riskComponent.getRiskFactors(), 0);
                break;
           case "04": // Maximum
               riskComponentScore = computeMaxOrMin(riskComponent.getRiskFactors(),1);
               break;
           case "05": //Equals
               riskComponentScore = getEquals(riskComponent.getRiskFactors());
                break;
           case "06": //Multiply // TODO NOT Implemented right now.
                break;

       }


        // Deflators and Multiplers
        for (RiskFactor riskFactor: riskComponent.getRiskFactors()) {

            if (riskFactor.getScoreType().getCode().equals("02") || riskFactor.getScoreType().getCode().equals("03")) {

                riskComponentScore = riskComponentScore * riskFactor.getScore();
            }
        }

        riskComponentScore = Utils.round(riskComponentScore);
        riskComponent.setScore(riskComponentScore);

        return riskComponent;
    }


    // Weighted Computation
    private Double computeWeighted ( List<RiskFactor> riskFactorSet) {

        Double score = 0D;
        // Computing Method = 01 - Weighted
        for (RiskFactor riskFactor: riskFactorSet) {

            String scoreTypeCode = riskFactor.getScoreType().getCode();
            if (scoreTypeCode.equals("01")) {
                score = score + riskFactor.getScore() * riskFactor.getWeightage();
            }
        }
        return score;
    }

    // Sum
    private Double computeSum(List<RiskFactor> riskFactorSet) {
        Double score = 0D;

        for (RiskFactor riskFactor: riskFactorSet){
            String scoreTypeCode = riskFactor.getScoreType().getCode();
            if (scoreTypeCode.equals("01")) {
                score = score + riskFactor.getScore();
                }
            }
        return score;
    }


    // Compute Maximum or Minimum
    //  mode = 0 (Maximum)
    //  mode = 1 (Minimum)
    private Double computeMaxOrMin (List<RiskFactor> riskFactorSet, Integer mode) {
        Double score = 0D;

        List<Double> scores = new ArrayList<>();

        // Collect Scores into a list
        for (RiskFactor riskFactor: riskFactorSet) {
            String scoreTypeCode = riskFactor.getScoreType().getCode();
            if (scoreTypeCode.equals("01")) {
                scores.add(riskFactor.getScore());
            }
            }
        //Get Maximum
        if (mode == 0)
            score = Utils.getMaximum(scores);
        else // Get Minimum
            score = Utils.getMinimum(scores);

        return score;
    }

    // Compute Equals
    private Double getEquals(List<RiskFactor> riskFactorSet ) {

        if (riskFactorSet.size() > 1 ) {
            //Raise Exception
        }
        return riskFactorSet.iterator().next().getScore();
    }





}
