package com.pfs.riskmodel.service.modelvaluator;

import com.pfs.riskmodel.businessconfig.ProjectGrade;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskRatingModifier;

import java.util.List;
import java.util.Map;

/**
 * Created by sajeev on 14-Jan-19.
 */
public class CommonComputation {



    // Appply Rating Modifiers
    public ProjectGrade applyRatingModifier (RiskModelTemplate riskModelTemplate,
                                             ProjectGrade projectGradeObject,
                                             List<ProjectGrade> projectGradeList,
                                             Double subInvestmentStartScore) {


        ProjectGrade modifiedProjectGrade = new ProjectGrade();
        Integer modifiedProjectGradeAsNumber = 0;
        Boolean ratingModifiersInAction = false;

        // APPLY RISK RATING MODIFIERS
        for (RiskRatingModifier riskRatingModifier: riskModelTemplate.getRiskRatingModifiers()) {

            // Execute Grade Capping to SubInvestment GRADE
            if (riskRatingModifier.getModifierType() == 0)
                if (riskRatingModifier.getSubInvestmentGradeCapping() == true) {
                    if (riskModelTemplate.getScore() >= subInvestmentStartScore) {
                        modifiedProjectGrade.setCommonScaleGrade("GRADE 7");
                        ratingModifiersInAction = true;
                        break;
                    }
                }

            if (riskRatingModifier.getNumberOfNotchesDown() != 0) {

                // Add the "Number of Notch downs" to the overall project grade number
                modifiedProjectGradeAsNumber = projectGradeObject.getGradeAsNumber() + riskRatingModifier.getNumberOfNotchesDown();
                // Find the the Modified Grade
                modifiedProjectGrade = Utils.getProjectGradeByGradeAsNumber(projectGradeList, modifiedProjectGradeAsNumber);
                ratingModifiersInAction = true;
            }
        }


        if (ratingModifiersInAction == true) {
            if (modifiedProjectGrade.getGradeAsNumber() >= 7) {
                //modifiedProjectGrade = projectGradeObject;
                riskModelTemplate.setFinalProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
                riskModelTemplate.setModifiedProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
                riskModelTemplate.setModifiedProjectGradeAsNumber(modifiedProjectGrade.getGradeAsNumber());
                //riskModelTemplate.setOverallProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
                return modifiedProjectGrade;
            }
            else {

                riskModelTemplate.setModifiedProjectGradeAsNumber(modifiedProjectGrade.getGradeAsNumber());
                riskModelTemplate.setModifiedProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
                riskModelTemplate.setAfterParentalNotchUpGrade(modifiedProjectGrade.getCommonScaleGrade());
                riskModelTemplate.setFinalProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
                //riskModelTemplate.setOverallProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
            }
        }
        else {
            // If Rating Modifier is not in action return projectGrade as the modified Grade
            riskModelTemplate.setModifiedProjectGrade(projectGradeObject.getCommonScaleGrade());
            riskModelTemplate.setModifiedProjectGradeAsNumber(projectGradeObject.getGradeAsNumber());
            //riskModelTemplate.setOverallProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
            riskModelTemplate.setFinalProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
            return projectGradeObject;
        }

        return null;
    }


    // Evaluate Parental Notchup

    public  ProjectGrade evaluateParentalNotchup (RiskModelTemplate riskModelTemplate,
                                                  List<ProjectGrade> projectGradeList,
                                                  ProjectGrade modifiedProjectGrade,
                                                  Integer totalItemsInGradeTable) {

        ProjectGrade afterParentalNotchUpGrade = new ProjectGrade();

        // Evaluate Parental NotchUp
        RiskParentalNotchUpEvaluator riskParentalNotchUpEvaluator = new RiskParentalNotchUpEvaluator();

        Map<String, Integer> parentalNotchupResult =
                riskParentalNotchUpEvaluator.evaluateParentalNotchup(
                        riskModelTemplate.getRiskParentalNotchUps().get(0),
                        riskModelTemplate.getProjectRiskLevel().getCode(),
                        riskModelTemplate.getModifiedProjectGradeAsNumber());

        Integer modifiedProjectGradeAsNumber = modifiedProjectGrade.getGradeAsNumber();

        if (riskModelTemplate.getRiskParentalNotchUps().get(0).getIsParentalNotchUpApplicable() == true) {

            // Apply after Parental Notchup
            Integer numberOfNotchesAfterParental = parentalNotchupResult.get("NotchupCalc");

            Integer afterParentNotchupGradeItemNumber = 0;
            Integer afterParentNotchupGradeAsNumber = 0;

            afterParentNotchupGradeAsNumber = modifiedProjectGrade.getGradeAsNumber() - numberOfNotchesAfterParental;

            afterParentNotchupGradeItemNumber = modifiedProjectGrade.getItemNo() + numberOfNotchesAfterParental;

            if (afterParentNotchupGradeItemNumber >= totalItemsInGradeTable) {
                afterParentNotchupGradeItemNumber = totalItemsInGradeTable;
            }
            if (afterParentNotchupGradeItemNumber < 0)
                afterParentNotchupGradeItemNumber = 0;

            //The post notch-up grade would be capped at one notch below the parent’s grade.
            // Fetch Parent's Grade
            Integer parentRating = parentalNotchupResult.get("Parent Rating");
            if (afterParentNotchupGradeAsNumber >= parentRating) {
                afterParentNotchupGradeAsNumber = parentRating + 1;
            }


            ProjectGrade afterParentalNotchUpGradeObject =
                    Utils.getProjectGradeByGradeAsNumber( projectGradeList, afterParentNotchupGradeAsNumber);


            //        Utils.getProjectGradeByItemNumber(projectGradeList, afterParentNotchupGradeItemNumber);

            System.out.println("Modified Grade Item Number : " + modifiedProjectGrade.getItemNo());
            System.out.println("Number of Notches after Parental : " + numberOfNotchesAfterParental);
            System.out.println("After Parental Item Number : " + afterParentNotchupGradeItemNumber);
            System.out.println("After Parental GRADE : " + afterParentalNotchUpGradeObject.getCommonScaleGrade());


            Integer numberOfNotchesUpagraded = afterParentNotchupGradeItemNumber -  modifiedProjectGrade.getItemNo();
            riskModelTemplate.getRiskParentalNotchUps().get(0).setNumberOfNotchesUpgraded(numberOfNotchesUpagraded);


            // Set the Grade after Parental Notchup
            if (afterParentalNotchUpGradeObject != null) {
                riskModelTemplate.setAfterParentalNotchUpGrade(afterParentalNotchUpGradeObject.getCommonScaleGrade());
                riskModelTemplate.setFinalProjectGrade(afterParentalNotchUpGradeObject.getCommonScaleGrade());
               // riskModelTemplate.setOverallProjectGrade(afterParentalNotchUpGrade.getCommonScaleGrade());

            } else {
                riskModelTemplate.setAfterParentalNotchUpGrade(modifiedProjectGrade.getCommonScaleGrade());
                riskModelTemplate.setFinalProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
                //riskModelTemplate.setOverallProjectGrade(modifiedProjectGrade.getCommonScaleGrade());

            }

        }

        return afterParentalNotchUpGrade;


    }



}
