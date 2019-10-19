package com.pfs.riskmodel.service.modelvaluator;

import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.domain.RiskSubFactor;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by sajeev on 30-Dec-18.
 */
public class RiskFactorEvaluator {


    public RiskFactor evaluateRiskFactor(RiskFactor riskFactor) {

       String computingMethodCode =  riskFactor.getComputingMethod().getCode();
      //String scoreTypeCode = riskFactor.getScoreType().getCode();

       String riskFactorCalculation = " ";


       // Initialize Risk Factor Score
       Double riskFactorScore = riskFactor.getScore();
       riskFactorScore = 0D;

        int i = 0; int j = getMainRiskFactorCount(riskFactor.getRiskSubFactors());

       switch (computingMethodCode) {
           case "01": // Weighted
                riskFactorScore = computeWeighted(riskFactor.getRiskSubFactors());


                for (RiskSubFactor riskSubFactor: riskFactor.getRiskSubFactors()) {
                    if (riskSubFactor.getScoreTypeCode().equals("01")) {
                        i++;
                        // Single Entry
                        if (i == 1 && i == j) {
                            riskFactorCalculation = riskFactorCalculation + " " + riskSubFactor.getScore().toString() + " * " + riskSubFactor.getWeightage().toString();
                            continue;
                        }
                        // First Entry
                        if (i == 1) {
                            riskFactorCalculation = riskFactorCalculation + " " + riskSubFactor.getScore().toString() + " * " + riskSubFactor.getWeightage().toString() + " + ";
                            continue;
                        }
                        //Middle Entires
                        if (i != 1 && i < j && i != j) {
                            riskFactorCalculation = riskFactorCalculation + " " + riskSubFactor.getScore().toString() + " * " + riskSubFactor.getWeightage().toString() + " + ";
                            ;
                            continue;
                        }
                        // Last Entry
                        if (i == j && j != 1) {
                            riskFactorCalculation = riskFactorCalculation + " " + riskSubFactor.getScore().toString() + " * " + riskSubFactor.getWeightage().toString();
                            continue;
                        }
                    }

                }
                break;
           case "02": // Sum
                riskFactorScore = computeSum(riskFactor.getRiskSubFactors());


               for (RiskSubFactor riskSubFactor: riskFactor.getRiskSubFactors()) {
                   if (i == 1)
                        riskFactorCalculation = riskFactorCalculation + " " + riskSubFactor.getScore().toString() + " + " ;
                   if (i!= 1 && i < j && i!=j)
                       riskFactorCalculation = riskFactorCalculation + " " + riskSubFactor.getScore().toString() + " + " ;
                   if (i == j && j != 1)
                       riskFactorCalculation =  riskFactorCalculation + " " + riskSubFactor.getScore().toString()  ;

                   i++;
               }
                break;
           case "03": // Minimum
               riskFactorScore = computeMaxOrMin(riskFactor.getRiskSubFactors(), 0);
                break;
           case "04": // Maximum
               riskFactorScore = computeMaxOrMin(riskFactor.getRiskSubFactors(),1);
               break;
           case "05": //Equals
                riskFactorScore = getEquals(riskFactor.getRiskSubFactors());
                break;
           case "06": //Multiply // TODO NOT Implemented right now.
                break;

       }

       // Deflators and Multipliers
       for (RiskSubFactor riskSubFactor: riskFactor.getRiskSubFactors()) {

           if ( riskFactorScore == 0D) {
               riskFactorScore = 1D;
           }

           if (riskSubFactor.getScoreType().getCode().equals("02") || riskSubFactor.getScoreType().getCode().equals("03")) {
               riskFactorScore = riskFactorScore * riskSubFactor.getScore();

               riskFactorCalculation = "( " + riskFactorCalculation + " ) " +   " * " + riskSubFactor.getScore().toString();
           }
       }
        //riskFactorScore = Utils.round(riskFactorScore);

        riskFactorCalculation = "Calculation :" + riskFactorCalculation + " = " + riskFactorScore.toString();


       riskFactor.setScore(riskFactorScore);
       riskFactor.setRiskFactorCalculation(riskFactorCalculation);

       return riskFactor;
    }


    // Weighted Computation
    private Double computeWeighted ( List<RiskSubFactor> riskSubFactorSet) {

        Double score = 0D;
        // Computing Method = 01 - Weighted
        for (RiskSubFactor riskSubFactor: riskSubFactorSet) {
            String scoreTypeCode = riskSubFactor.getScoreType().getCode();
            if (scoreTypeCode.equals("01")) {//Only Normal - Exclude Deflators or Multiplier

                Double interimScore = 0D;
                interimScore = (riskSubFactor.getScore() * ( riskSubFactor.getWeightage() * 100) ) / 100;

                score = score + interimScore;
            }
        }

         return score;
    }

    // Sum
    private Double computeSum(List<RiskSubFactor> riskSubFactorSet) {
        Double score = 0D;

        for (RiskSubFactor riskSubFactor: riskSubFactorSet){
            String scoreTypeCode = riskSubFactor.getScoreType().getCode();
            if (scoreTypeCode.equals("01")) {//Only Normal - Exclude Deflators or Multiplier

                score = score + riskSubFactor.getScore();
            }
        }

         return score;
    }


    // Compute Maximum or Minimum
    //  mode = 0 (Maximum)
    //  mode = 1 (Minimum)
    private Double computeMaxOrMin (List<RiskSubFactor> riskSubFactorSet, Integer mode) {
        Double score = 0D;

        List<Double> scores = new ArrayList<>();

        // Collect Scores into a list
        for (RiskSubFactor riskSubFactor: riskSubFactorSet) {
            String scoreTypeCode = riskSubFactor.getScoreType().getCode();
            if (scoreTypeCode.equals("01")) {//Only Normal - Exclude Deflators or Multiplier
                scores.add(riskSubFactor.getScore());
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
    private Double getEquals(List<RiskSubFactor> riskSubFactorSet ) {

        if (riskSubFactorSet.size() > 1 ) {
            //Raise Exception
        }
        return riskSubFactorSet.iterator().next().getScore();
    }



    private Integer getMainRiskFactorCount(List<RiskSubFactor> riskSubFactors) {

        Integer count = 0;

        for (RiskSubFactor riskSubFactor: riskSubFactors) {
            if (riskSubFactor.getScoreTypeCode().equals("01"))
                count++;
        }

        return count;

    }

}
