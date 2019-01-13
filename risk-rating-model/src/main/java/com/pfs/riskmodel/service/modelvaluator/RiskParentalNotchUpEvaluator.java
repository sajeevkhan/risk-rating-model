package com.pfs.riskmodel.service.modelvaluator;

import com.pfs.riskmodel.domain.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sajeev on 30-Dec-18.
 */
public class RiskParentalNotchUpEvaluator {

    //The notch-up score would then be represented as a percentage of maximum possible score.
    private static Double maximumScoreParentalNotchup;


//    The notch-up criteria would be applicable if all the following conditions are true:
//            1. The parent’s rating is better than the borrower’s rating.
//            2. The borrower’s rating is not GRADE10 (in Default).
//            3. The notch-up score as a percentage of maximum possible score is higher than 35%.


        private Double notchupScore;
        private Double notchupScoreAsAPctOfMaxScore;

        private Integer borrowersRating;
        private Integer parentsRating;

        private Integer numberOfNotchesForUpgrade;

        private Integer numberOfNotchesCalculated;

 // TODO
////The post notch-up grade would be capped at one notch below the parent’s grad
//    For SPVs/Projects,
//             For Build-phase projects,
//                                  the maximum notch-up would be limited to two notches and final rating should not exceed P3 (GRADE5).
//             For Operational phase projects,
//                                  the post notch-up rating can go as high as (P1) GRADE3 provided above criteria are met.


    public  Map <String, Integer> evaluateParentalNotchup(RiskParentalNotchUp riskParentalNotchUp,
                                                         String riskLevel,
                                                         Integer borrowerRatingGrade) {

        // Set Initial Values
        numberOfNotchesForUpgrade = 0;
        numberOfNotchesCalculated = 0;
        notchupScore = 0D;

        // Set Initial Values
        riskParentalNotchUp.setNumberOfNotchesUpgraded(0);
        riskParentalNotchUp.setParentalNotchUpScore(0.0D);
        riskParentalNotchUp.setNumberOfNotchesCalculated(0);
        riskParentalNotchUp.setIsParentalNotchUpApplicable(false);


        Map <String, Integer> result = new HashMap<>();

        borrowersRating =  borrowerRatingGrade;
        // Calculate Maximum Possible Score
        this.calcMaxPossibleScore( riskParentalNotchUp);

        // Calculate Notchup Score
        this.calcNotchupScore(riskParentalNotchUp);

        //The notch-up score would then be represented as a percentage of maximum possible score.
        notchupScoreAsAPctOfMaxScore = notchupScore / maximumScoreParentalNotchup;

        riskParentalNotchUp.setParentalNotchUpScore(notchupScore);

        // Check if Notchup Conditions are Applicable;
        Boolean isNotchupApplicable = false;
        isNotchupApplicable = this.isNotchupCriteriaApplicable(riskParentalNotchUp);

        result.put("Parent Rating", parentsRating);


        if (isNotchupApplicable == true) {
            riskParentalNotchUp.setIsParentalNotchUpApplicable(true);
        } else {
            riskParentalNotchUp.setIsParentalNotchUpApplicable(false);
            result.put("Notchup",0);
            return result;
        }

        //Calculate Number of Notches
        numberOfNotchesCalculated = calculateNumberOfNotches();
        riskParentalNotchUp.setNumberOfNotchesCalculated(numberOfNotchesCalculated);


        result.put("NotchupCalc",numberOfNotchesCalculated);

        return  result;


    }



     /*
            Number of notches (upgrade) =
                    (Notch-up score as a percentage of maximum possible score)
                                  * Difference between parent and borrower grades (in terms of notches)
     */
     private Integer calculateNumberOfNotches() {


         Double numberOfNotches = notchupScoreAsAPctOfMaxScore * ( parentsRating - borrowersRating );
         Utils.round(numberOfNotches);

         int number = (int) Math.round(numberOfNotches);
         if (number < 0 )
             number = number * -1;

         return number;
     }





    // Check Notchup Criteria is Applicable or not
    /*
    The notch-up criteria would be applicable if all the following conditions are true:
            1. The parent’s rating is better than the borrower’s rating.
            2. The borrower’s rating is not GRADE10 (in Default).
            3. The notch-up score as a percentage of maximum possible score is higher than 35%.
    */
    private Boolean isNotchupCriteriaApplicable (RiskParentalNotchUp riskParentalNotchUp ) {

        Boolean isNotchupCriteriaApplicable = true;

        for (RiskParentalNotchUpCondition riskParentalNotchUpCondition: riskParentalNotchUp.getRiskParentalConditions()){

            switch (riskParentalNotchUpCondition.getCategory()) {

                case 0:  //Rating of Parent Entity - Convert String to Integer
                    parentsRating = Integer.parseInt(riskParentalNotchUpCondition.getValue()) ;
                    break;
                case 1:  //Source of Rating of Parent Firm
                    break;
                case 2:  //Nature of Rating of Parent Firm
                    break;
                case 3: // Is Parent's rating at GRADE 10
                    if (riskParentalNotchUpCondition.getYesNoIndicatorValue() == 'N')
                        isNotchupCriteriaApplicable = false;
                        return isNotchupCriteriaApplicable;

                case 4: //Is Parent's Rating Better Than Borrower's Rating
                     if (riskParentalNotchUpCondition.getYesNoIndicatorValue() == 'Y')
                         isNotchupCriteriaApplicable = true;
                    break;
            }

            if (isNotchupCriteriaApplicable == false)
                break;
        }

        // Check if parent's rating is greater than borrower's rating
        if (parentsRating < borrowersRating)
            isNotchupCriteriaApplicable = true;
        else
            return false;

        // Check - Notch-up score as a percentage of maximum possible score is higher than 35%.
        if (notchupScoreAsAPctOfMaxScore > 0.35)
            isNotchupCriteriaApplicable = true;

        return isNotchupCriteriaApplicable;

    }

    // Calculate Notchup Score
    private void calcNotchupScore (RiskParentalNotchUp riskParentalNotchUp) {

        Double notchUpScoreCalculated = 0D;

        for (RiskSubFactor riskSubFactor: riskParentalNotchUp.getRiskSubFactors()) {

            for (RiskSubFactorAttribute riskSubFactorAttribute: riskSubFactor.getRiskSubFactorAttributes()) {
                if (riskSubFactorAttribute.getIsSelected() == true) {
                    notchUpScoreCalculated = notchUpScoreCalculated + riskSubFactor.getWeightage() * riskSubFactorAttribute.getScore();
                }
            }
        }

        notchupScore = notchUpScoreCalculated;

    }


    // Calculate Maximum Possible Score
    private void calcMaxPossibleScore ( RiskParentalNotchUp riskParentalNotchUp ) {

        Double maxParentalScore = 0D;

        for (RiskSubFactor riskSubFactor: riskParentalNotchUp.getRiskSubFactors()) {

             Double maxScore = 0D;
             for (RiskSubFactorAttribute riskSubFactorAttribute: riskSubFactor.getRiskSubFactorAttributes()){
                 if (riskSubFactorAttribute.getScore() > maxScore) {
                     maxScore = riskSubFactorAttribute.getScore();
                 }
             }

             maxParentalScore =maxParentalScore + maxScore * riskSubFactor.getWeightage();
        }

        maximumScoreParentalNotchup = maxParentalScore;

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






}
