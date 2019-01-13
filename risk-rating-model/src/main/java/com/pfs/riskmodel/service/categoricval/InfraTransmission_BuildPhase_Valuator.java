package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.businessconfig.*;
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
public class InfraTransmission_BuildPhase_Valuator {


    List<ProjectGrade> projectGradeList = Infra_Transmission_BuildPhaseGrade.projectGradeList;


    public RiskModelTemplate valuate (RiskModelTemplate riskModelTemplate) {

        // Post Project Implementation Project Score
        Double postProjectImplScore = 0D;
        String postProjectImplScoreGrade = " ";
        // Project Implementation Score
        Double projectImplScore = 0D;
        String projectImplScoreGrade = " ";


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
            if (riskType.getDescription().contains("Post")) {

                 ProjectGrade postProjectGrade =
                        Utils.fetchGrade(projectGradeList,riskType.getScore());

                postProjectImplScore = riskType.getScore();
                postProjectImplScoreGrade = postProjectGrade.getCommonScaleGrade();
                riskType.setGrade(postProjectImplScoreGrade);
            }

            else {
                ProjectGrade projectGrade =
                        Utils.fetchGrade(projectGradeList,riskType.getScore());

                projectImplScore = riskType.getScore();
                projectImplScoreGrade = projectGrade.getCommonScaleGrade();
                riskType.setGrade(projectImplScoreGrade);
            }
        }


        // Build Phase Phase Score = Minimum of Project Impl Score and Post Project Impl Score
        projectScore = Math.min(postProjectImplScore, projectImplScore);
        overallProjectScore = projectScore;
        riskModelTemplate.setScore(overallProjectScore);

        //  Build Phase Grade
        ProjectGrade overallProjectGradeObject =
                Utils.fetchGrade(projectGradeList,overallProjectScore);
        overallProjectGrade = overallProjectGradeObject.getCommonScaleGrade();



        Boolean ratingModifiersInAction = false;

        // EXECUTE RISK RATING MODIFIERS
        for (RiskRatingModifier riskRatingModifier: riskModelTemplate.getRiskRatingModifiers()) {

            // Execute Grade Capping to SubInvestment GRADE
            if (riskRatingModifier.getModifierType() == 0)
                if (riskRatingModifier.getSubInvestmentGradeCapping() == true) {
                     if (overallProjectScore >= 6.25) {
                        modifiedProjectGrade = "GRADE 7";
                        ratingModifiersInAction = true;
                    }
                }


            Integer modifiedProjectGradeItemNumber = 0;

            if (riskRatingModifier.getNumberOfNotchesDown() != 0) {

                riskRatingModifier.setIsApplicable(true);

                // Add the "Number of Notch downs" to the overall project grade number
                Integer modifiedGradeAsNumber = overallProjectGradeObject.getGradeAsNumber() + 2;


                //modifiedProjectGradeItemNumber = overallProjectGradeObject.getItemNo() - riskRatingModifier.getNumberOfNotchesDown();

                // Find the the Modified Grade
                ProjectGrade modifiedProjectGradeObject =
                        Utils.getProjectGradeByGradeAsNumber(projectGradeList,modifiedGradeAsNumber);

                if (modifiedProjectGradeObject != null) {
                        modifiedProjectGrade = modifiedProjectGradeObject.getCommonScaleGrade();
                        modifiedProjectGradeAsNumber = modifiedProjectGradeObject.getGradeAsNumber();
                        ratingModifiersInAction = true;

                    }
                    else // Rating is not found in the rating table
                        ratingModifiersInAction = false;
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
                        Utils.getProjectGradeByCommonScaleGrade(projectGradeList,riskModelTemplate.getOverallProjectGrade()).getGradeAsNumber()
                        );

        // TODO   -  Parental Notchup - Calculation Not Matching with EXCEL
        // TODO As per EXCEL, the rating can go upto GRADE 2.......
        if (riskModelTemplate.getRiskParentalNotchUps().get(0).getIsParentalNotchUpApplicable() == true) {

            // Apply after Parental Notchup
            // Get Modified Project Grade Object
            ProjectGrade modProjectGrade = Utils.getProjectGradeByCommonScaleGrade(projectGradeList,
                    riskModelTemplate.getModifiedProjectGrade());
            modifiedProjectGradeAsNumber = modProjectGrade.getGradeAsNumber();

            Integer numberOfNotchesAfterParental = parentalNotchupResult.get("NotchupCalc");

            //For Build-phase projects, the maximum notch-up would be limited to two notches
            // and final rating should not exceed P3 (GRADE5).
            if (numberOfNotchesAfterParental > 2)
                numberOfNotchesAfterParental = 2;

            Integer afterParentNotchupGradeItemNumber = 0;
            afterParentNotchupGradeAsNumber = 0;

            // Calculate the revised number of notches
            afterParentNotchupGradeItemNumber = modProjectGrade.getItemNo() + numberOfNotchesAfterParental;

            //final rating should not exceed P3 (GRADE5).
            if (afterParentNotchupGradeItemNumber >= 8) {
                afterParentNotchupGradeItemNumber = 8;
            }
            if (afterParentNotchupGradeItemNumber < 0)
                afterParentNotchupGradeItemNumber = 0;

            //The post notch-up grade would be capped at one notch below the parent’s grade.
            // Fetch Parent's Grade
            Integer parentRating = parentalNotchupResult.get("Parent Rating");
            Integer parentRatingGradeItemNumber = 0;
            ProjectGrade parentalProjectGradeObject = Utils.getProjectGradeByGradeAsNumber(projectGradeList, parentRating);

            Integer numberOfNotchesUpagraded = afterParentNotchupGradeItemNumber + modProjectGrade.getItemNo();
            riskModelTemplate.getRiskParentalNotchUps().get(0).setNumberOfNotchesUpgraded(numberOfNotchesUpagraded);

            if (parentalProjectGradeObject != null) // TODO - This can never be NULL
                parentRatingGradeItemNumber = parentalProjectGradeObject.getItemNo();
            else
                afterParentNotchupGradeItemNumber = modProjectGrade.getItemNo();

            // If, after Parental Notchup rating is higher than Parent's rating, Cap it to ONE LEVEL below Parent's Rating
            if (afterParentNotchupGradeItemNumber >= parentRatingGradeItemNumber) {
                afterParentNotchupGradeItemNumber = parentRatingGradeItemNumber + 1;
            }

            // Fetch the After Parental Notchup Grade
            ProjectGrade afterParentalNotchUpGradeObject =
                    Utils.getProjectGradeByItemNumber(projectGradeList, afterParentNotchupGradeItemNumber);

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
        riskModelTemplate.setAfterParentalNotchUpGrade(afterParentalNotchUpGrade);


        // Prepare Summary
        RiskModelSummary riskModelSummary = new RiskModelSummary();
        List<RiskModelSummary> riskModelSummaries = new ArrayList<>();
        Integer itemNo = 0;


        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Project Implementation Risk Score");
        riskModelSummary.setValue(projectImplScore.toString());
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName(" Project Implementation Risk Grade");
        riskModelSummary.setValue(projectImplScoreGrade.toString());
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Post Project Implementation Risk Score");
        riskModelSummary.setValue(postProjectImplScoreGrade.toString());
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName(" Post Project Implementation Risk Grade");
        riskModelSummary.setValue(postProjectImplScoreGrade.toString());
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Overall Project Score");
        riskModelSummary.setValue(overallProjectScore.toString());
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Overall Project Grade");
        riskModelSummary.setValue(overallProjectGrade.toString());
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Modified Project Grade");
        riskModelSummary.setValue(modifiedProjectGrade.toString());
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Grade after Parental Notchup");
        riskModelSummary.setValue(afterParentalNotchUpGrade.toString());
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Final Project Grade");
        riskModelSummary.setValue(finalProjectGrade.toString());
        riskModelSummaries.add(riskModelSummary);

        riskModelTemplate.setRiskModelSummaries(riskModelSummaries);
        return riskModelTemplate;
    }

}
