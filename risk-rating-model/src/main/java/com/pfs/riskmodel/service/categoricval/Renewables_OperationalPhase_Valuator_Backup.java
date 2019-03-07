package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.businessconfig.ProjectGrade;
import com.pfs.riskmodel.businessconfig.Renewables_Grade;
import com.pfs.riskmodel.domain.RiskModelSummary;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskRatingModifier;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.service.modelvaluator.RiskParentalNotchUpEvaluator;
import com.pfs.riskmodel.service.modelvaluator.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sajeev on 31-Dec-18.
 */
public class Renewables_OperationalPhase_Valuator_Backup {


    List<ProjectGrade> projectGradeList = Renewables_Grade.projectGradeList;

    public RiskModelTemplate valuate (RiskModelTemplate riskModelTemplate) {

        // Project Scores and Grade
        Double projectScore = 0D;
        String projectScoreGrade = "";
        Double overallProjectScore = 0D;

        // Modified Project Scores and Grade
        String overallProjectGrade = "";
        String modifiedProjectGrade = "";
        Integer modifiedProjectGradeAsNumber = 0;

        // After Parental Notchup GRADE
        String afterParentalNotchUpGrade = " ";
        Integer afterParentNotchupGradeAsNumber = 0;
        String finalProjectGrade = " ";


        // Risk Type Score
        for (RiskType riskType : riskModelTemplate.getRiskTypes()) {
            if (riskType.getDescription().contains("Operational")) {

                // TODO - Change the Grade Scale - OPEN WITH PFS
                ProjectGrade projectGrade =
                        Utils.fetchGrade(projectGradeList,riskType.getScore());

                projectScore = riskType.getScore();
                projectScoreGrade = projectGrade.getCommonScaleGrade();
                riskType.setGrade(projectScoreGrade);
            }
        }


         // Operational Phase Score = projectScore
        overallProjectScore = projectScore;
        riskModelTemplate.setScore(projectScore);

        //  Operational Phase Grade
        ProjectGrade overallProjectGradeObject =
                     Utils.fetchGrade(projectGradeList,overallProjectScore);
        overallProjectGrade = overallProjectGradeObject.getCommonScaleGrade();





        Boolean ratingModifiersInAction = false;

        // EXECUTE RISK RATING MODIFIERS
        for (RiskRatingModifier riskRatingModifier: riskModelTemplate.getRiskRatingModifiers()) {

            // Execute Grade Capping to SubInvestment GRADE
            if (riskRatingModifier.getModifierType() == 0)
                if (riskRatingModifier.getSubInvestmentGradeCapping() == true) {
                    if (overallProjectScore >= 4.25) {
                        modifiedProjectGrade = "GRADE 7";
                        ratingModifiersInAction = true;
                    }
                }

            if (riskRatingModifier.getNumberOfNotchesDown() != 0) {

                // Add the "Number of Notch downs" to the overall project grade number
                modifiedProjectGradeAsNumber =
                        overallProjectGradeObject.getGradeAsNumber() + riskRatingModifier.getNumberOfNotchesDown();

                // Find the the Modified Grade
                for (ProjectGrade projectGrade : projectGradeList) {
                    if (projectGrade.getGradeAsNumber() == modifiedProjectGradeAsNumber) {
                        modifiedProjectGrade = projectGrade.getCommonScaleGrade();
                        ratingModifiersInAction = true;
                        break;
                    }
                    else // Rating is not found in the rating table
                        ratingModifiersInAction = false;
                }
            }
        }

        if (ratingModifiersInAction == false) {
            // If Rating Modifiers do not influence the calculation, Project Rating is passed on
            modifiedProjectGrade = overallProjectGrade;
            modifiedProjectGradeAsNumber = overallProjectGradeObject.getGradeAsNumber();
        }

        // Set the Grade on the Model
        riskModelTemplate.setOverallProjectGrade(overallProjectGrade);
        riskModelTemplate.setModifiedProjectGrade(modifiedProjectGrade);
        riskModelTemplate.setModifiedProjectGradeAsNumber(modifiedProjectGradeAsNumber);


        // Evaluate Parental NotchUp
        RiskParentalNotchUpEvaluator riskParentalNotchUpEvaluator = new RiskParentalNotchUpEvaluator();

        Map<String, Integer> parentalNotchupResult =
                riskParentalNotchUpEvaluator.evaluateParentalNotchup(
                        riskModelTemplate.getRiskParentalNotchUps().get(0),
                        riskModelTemplate.getProjectRiskLevel().getCode(),
                        riskModelTemplate.getModifiedProjectGradeAsNumber());



        if (riskModelTemplate.getRiskParentalNotchUps().get(0).getIsParentalNotchUpApplicable() == true) {

            // Apply after Parental Notchup
            // Get Modified Project Grade Object
            ProjectGrade modProjectGrade = Utils.getProjectGradeByCommonScaleGrade(projectGradeList,
                    riskModelTemplate.getModifiedProjectGrade());
            modifiedProjectGradeAsNumber = modProjectGrade.getGradeAsNumber();

            Integer numberOfNotchesAfterParental = parentalNotchupResult.get("NotchupCalc");

            Integer afterParentNotchupGradeItemNumber = 0;


            afterParentNotchupGradeItemNumber = modProjectGrade.getItemNo() + numberOfNotchesAfterParental;

            if (afterParentNotchupGradeItemNumber >= 10) {
                afterParentNotchupGradeItemNumber = 10;
            }
            if (afterParentNotchupGradeItemNumber < 0)
                afterParentNotchupGradeItemNumber = 0;
            //
//            //The post notch-up grade would be capped at one notch below the parent’s grade.
//            // Fetch Parent's Grade
//            Integer parentRating = parentalNotchupResult.get("Parent Rating");
//            if (afterParentNotchupGradeItemNumber <= parentRating) {
//                afterParentNotchupGradeAsNumber = parentRating + 1;
//            }

            ProjectGrade afterParentalNotchUpGradeObject =
                    Utils.getProjectGradeByItemNumber(projectGradeList, afterParentNotchupGradeItemNumber);

            System.out.println("Modified Grade Item Number : " + modProjectGrade.getItemNo());
            System.out.println("Number of Notches after Parental : " + numberOfNotchesAfterParental);
            System.out.println("After Parental Item Number : " + afterParentNotchupGradeItemNumber);
            System.out.println("After Parental GRADE : " + afterParentalNotchUpGradeObject.getCommonScaleGrade());


            Integer numberOfNotchesUpagraded = afterParentNotchupGradeItemNumber -  modProjectGrade.getItemNo();
            riskModelTemplate.getRiskParentalNotchUps().get(0).setNumberOfNotchesUpgraded(numberOfNotchesUpagraded);

            //        Utils.getProjectGradeByGradeAsNumber( projectGradeList, afterParentNotchupGradeAsNumber);
            // Set the Grade after Parental Notchup
            if (afterParentalNotchUpGradeObject != null) {
                riskModelTemplate.setAfterParentalNotchUpGrade(afterParentalNotchUpGradeObject.getCommonScaleGrade());
                afterParentalNotchUpGrade = afterParentalNotchUpGradeObject.getCommonScaleGrade();
            } else {
                afterParentalNotchUpGrade = modifiedProjectGrade;
            }

        }



        //    Overall Rating Grade
        // GRADE AFTER PARENTAL NOTCHUP
        riskModelTemplate.setFinalProjectGrade(afterParentalNotchUpGrade);
        finalProjectGrade = afterParentalNotchUpGrade;


        // Prepare Summary
        RiskModelSummary riskModelSummary = new RiskModelSummary();
        List<RiskModelSummary> riskModelSummaries = new ArrayList<>();
        Integer itemNo = 0;


        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Project Implementation Risk Score");
        riskModelSummary.setScore(projectScore.toString());
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName(" Project Implementation Risk Grade");
        riskModelSummary.setScore(projectScoreGrade.toString());
        riskModelSummaries.add(riskModelSummary);


        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Overall Project Score");
        riskModelSummary.setScore(overallProjectScore.toString());
        riskModelSummaries.add(riskModelSummary);


        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Overall Project Grade");
        riskModelSummary.setScore(overallProjectGrade.toString());
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Modified Project Grade");
        riskModelSummary.setScore(modifiedProjectGrade.toString());
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Grade after Parental Notchup");
        riskModelSummary.setScore(afterParentalNotchUpGrade.toString());
        riskModelSummaries.add(riskModelSummary);


        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Final Project Grade");
        riskModelSummary.setScore(finalProjectGrade.toString());
        riskModelSummaries.add(riskModelSummary);


        riskModelTemplate.setRiskModelSummaries(riskModelSummaries);
        return riskModelTemplate;
    }

}
