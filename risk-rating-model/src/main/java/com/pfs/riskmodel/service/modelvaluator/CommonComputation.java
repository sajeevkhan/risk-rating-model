package com.pfs.riskmodel.service.modelvaluator;

import com.pfs.riskmodel.businessconfig.ProjectGrade;
import com.pfs.riskmodel.domain.RiskModelSummary;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskRatingModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sajeev on 14-Jan-19.
 */
public class CommonComputation {


    // Appply Rating Modifiers
    public ProjectGrade applyRatingModifier(RiskModelTemplate riskModelTemplate,
                                            ProjectGrade projectGradeObject,
                                            List<ProjectGrade> projectGradeList,
                                            Double subInvestmentStartScore) {


        ProjectGrade modifiedProjectGrade = new ProjectGrade();
        Integer modifiedProjectGradeAsNumber = 0;
        Boolean ratingModifiersInAction = false;

        // If Rating Modifiers are not applicable, then return the grade
        try {
            if (riskModelTemplate.getApplyRatingModifiers() == false) {
                return projectGradeObject;
            }
        } catch (NullPointerException ex) {
            return projectGradeObject;
        }


        // APPLY RISK RATING MODIFIERS
        for (RiskRatingModifier riskRatingModifier : riskModelTemplate.getRiskRatingModifiers()) {

            // Execute Grade Capping to SubInvestment GRADE
            if (riskRatingModifier.getModifierType() == 0)
                if (riskRatingModifier.getSubInvestmentGradeCapping() == true) {
                    if (riskModelTemplate.getScore() >= subInvestmentStartScore) {
                        modifiedProjectGrade = Utils.getProjectGradeByGradeAsNumber(projectGradeList, 7);
                        //modifiedProjectGrade.setCommonScaleGrade("GRADE 7");
                        ratingModifiersInAction = true;
                        //ONLY relevant for PDF Printing
                        riskModelTemplate.setSubInvestementGradeAfterRatingModifier(true);
                        continue;
                    } else {
                        modifiedProjectGrade = projectGradeObject;
                    }
                } else {
                    modifiedProjectGrade = projectGradeObject;
                }

            if (riskRatingModifier.getNumberOfNotchesDown() != 0) {

                // Add the "Number of Notch downs" to the overall project grade number
                modifiedProjectGradeAsNumber = modifiedProjectGrade.getGradeAsNumber() + riskRatingModifier.getNumberOfNotchesDown();
                // Find the the Modified Grade
                modifiedProjectGrade = Utils.getProjectGradeByGradeAsNumber(projectGradeList, modifiedProjectGradeAsNumber);
                ratingModifiersInAction = true;

                //ONLY relevant for PDF Printing
                riskModelTemplate.setNumberOfNotchesDownAfterRatingModifier(riskRatingModifier.getNumberOfNotchesDown().toString());
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
            } else {

                riskModelTemplate.setModifiedProjectGradeAsNumber(modifiedProjectGrade.getGradeAsNumber());
                riskModelTemplate.setModifiedProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
                riskModelTemplate.setAfterParentalNotchUpGrade(modifiedProjectGrade.getCommonScaleGrade());
                riskModelTemplate.setFinalProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
                return modifiedProjectGrade;
                //riskModelTemplate.setOverallProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
            }
        } else {
            // If Rating Modifier is not in action return projectGrade as the modified Grade
            riskModelTemplate.setModifiedProjectGrade(projectGradeObject.getCommonScaleGrade());
            riskModelTemplate.setModifiedProjectGradeAsNumber(projectGradeObject.getGradeAsNumber());
            //riskModelTemplate.setOverallProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
            riskModelTemplate.setFinalProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
            return projectGradeObject;
        }

        //return null;
    }


    // Evaluate Parental Notchup

    public ProjectGrade evaluateParentalNotchup(RiskModelTemplate riskModelTemplate,
                                                List<ProjectGrade> projectGradeList,
                                                ProjectGrade modifiedProjectGrade,
                                                Integer totalItemsInGradeTable) {


        try {

            // If Parental Notchup is not applicable, return the original grade itself
            if (riskModelTemplate.getApplyParentalNotchup() == false) {
                return modifiedProjectGrade;
            }
        } catch (NullPointerException ex) {
            return modifiedProjectGrade;
        }

        ProjectGrade afterParentalNotchUpGrade = new ProjectGrade();

        // Evaluate Parental NotchUp
        RiskParentalNotchUpEvaluator riskParentalNotchUpEvaluator = new RiskParentalNotchUpEvaluator();

        Map<String, Integer> parentalNotchupResult =
                riskParentalNotchUpEvaluator.evaluateParentalNotchup(
                        riskModelTemplate.getRiskParentalNotchUps().get(0),
                        riskModelTemplate.getProjectRiskLevel().getCode(),
                        modifiedProjectGrade.getGradeAsNumber());

        Integer modifiedProjectGradeAsNumber = modifiedProjectGrade.getGradeAsNumber();
        ProjectGrade afterParentalNotchUpGradeObject = new ProjectGrade();

        if (riskModelTemplate.getRiskParentalNotchUps().get(0).getIsParentalNotchUpApplicable() == true) {

            // Apply after Parental Notchup
            Integer numberOfNotchesAfterParental = parentalNotchupResult.get("NotchupCalc");

            //ONLY for PDF printing
            riskModelTemplate.setNumberOfNotchesUpAfterParentalNotchup(numberOfNotchesAfterParental.toString());


            if (numberOfNotchesAfterParental > 0) {
                Integer afterParentNotchupGradeItemNumber = 0;
                Integer afterParentNotchupGradeAsNumber = 0;

                afterParentNotchupGradeAsNumber = modifiedProjectGrade.getGradeAsNumber() - numberOfNotchesAfterParental;


                /*
                Clarified with PS:
                It was clarified that the table holds true for standalone SPV rating,
                however the benefit of good promotor is to be passed to the SPV,
                though the rating post parental notch up shall be capped one grade below parent rating.
                 */


                // Check if after grade notchup has crossed the highest grade.
                //   If yes, cap it to the highest grade
//                if (afterParentNotchupGradeAsNumber <= Utils.getHighestGrade(projectGradeList).getGradeAsNumber()) {
//
//                    afterParentalNotchUpGradeObject = Utils.getHighestGrade(projectGradeList);
//                    afterParentNotchupGradeAsNumber = afterParentalNotchUpGradeObject.getGradeAsNumber();
//
//                    //The post notch-up grade would be capped at one notch below the parent’s grade.
//                    // Fetch Parent's Grade
//                    Integer parentRating = parentalNotchupResult.get("Parent Rating");
//                    if (afterParentNotchupGradeAsNumber >= parentRating) {
//                        afterParentNotchupGradeAsNumber = parentRating + 1;
//
//                        //ONLY for PDF Printing
//                        riskModelTemplate.setNumberOfNotchesUpAfterParentalNotchup(riskModelTemplate.getNumberOfNotchesUpAfterParentalNotchup());// + "  (Capped to 1 Level below parent's grade)");
//
//                    }
//
//                    // Get the Re-capped Grade
//                    if(afterParentNotchupGradeAsNumber <= Utils.getHighestGrade(projectGradeList).getGradeAsNumber())
//                        afterParentalNotchUpGradeObject = Utils.getHighestGrade(projectGradeList);
//                    else
//                        afterParentalNotchUpGradeObject =
//                                Utils.getProjectGradeByGradeAsNumber(projectGradeList,afterParentNotchupGradeAsNumber);
//
//                    riskModelTemplate.setAfterParentalNotchUpGrade(afterParentalNotchUpGradeObject.getCommonScaleGrade());
//                    riskModelTemplate.setFinalProjectGrade(afterParentalNotchUpGradeObject.getCommonScaleGrade());
//                    return afterParentalNotchUpGradeObject;
//
//                }
//                else {
                afterParentNotchupGradeItemNumber = modifiedProjectGrade.getItemNo() + numberOfNotchesAfterParental;

                if (afterParentNotchupGradeItemNumber >= totalItemsInGradeTable) {
                    afterParentNotchupGradeItemNumber = totalItemsInGradeTable;
                }
                if (afterParentNotchupGradeItemNumber < 0)
                    afterParentNotchupGradeItemNumber = 0;

                //The post notch-up grade would be capped at one notch below the parent’s grade.
                // Fetch Parent's Grade
                Integer parentRating = parentalNotchupResult.get("Parent Rating");
                if (afterParentNotchupGradeAsNumber <= parentRating) {
                    afterParentNotchupGradeAsNumber = parentRating + 1;

                    //Only for PDF Printing
                    riskModelTemplate.setNumberOfNotchesUpAfterParentalNotchup(riskModelTemplate.getNumberOfNotchesUpAfterParentalNotchup());// + "  (Capped to 1 Level below parent's grade)");

                }

//                afterParentalNotchUpGradeObject =
//                        Utils.getProjectGradeByGradeAsNumber(projectGradeList, afterParentNotchupGradeAsNumber);


                //}
                System.out.println("Modified Grade Item Number : " + modifiedProjectGrade.getItemNo());
                System.out.println("Number of Notches after Parental : " + numberOfNotchesAfterParental);
                System.out.println("After Parental Item Number : " + afterParentNotchupGradeItemNumber);

                String afterParentalNotchupGradeString = "GRADE " + afterParentNotchupGradeAsNumber;
                System.out.println("After Parental GRADE : " + afterParentalNotchupGradeString);


                Integer numberOfNotchesUpagraded = afterParentNotchupGradeItemNumber - modifiedProjectGrade.getItemNo();
                riskModelTemplate.getRiskParentalNotchUps().get(0).setNumberOfNotchesUpgraded(numberOfNotchesUpagraded);

                afterParentalNotchUpGradeObject.setCommonScaleGrade(afterParentalNotchupGradeString);


                // Set the Grade after Parental Notchup
                if (afterParentalNotchUpGradeObject != null) {
                    riskModelTemplate.setAfterParentalNotchUpGrade(afterParentalNotchupGradeString);
                    riskModelTemplate.setFinalProjectGrade(afterParentalNotchupGradeString);
                    return afterParentalNotchUpGradeObject;

                } else {
                    riskModelTemplate.setAfterParentalNotchUpGrade(modifiedProjectGrade.getCommonScaleGrade());
                    riskModelTemplate.setFinalProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
                    return modifiedProjectGrade;
                }
            } else {
                riskModelTemplate.setAfterParentalNotchUpGrade(modifiedProjectGrade.getCommonScaleGrade());
                riskModelTemplate.setFinalProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
                return modifiedProjectGrade;
            }
        }

        riskModelTemplate.setAfterParentalNotchUpGrade(modifiedProjectGrade.getCommonScaleGrade());
        riskModelTemplate.setFinalProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
        return modifiedProjectGrade;
    }


    // Prepare Summary
    // mode = 1 for build phase
    public List<RiskModelSummary> getSummary(Double projectScore, String projectGrade,
                                             Double postProjectScore, String postProjectGrade,
                                             RiskModelTemplate riskModelTemplate, Integer mode) {

        // Prepare Summary
        RiskModelSummary riskModelSummary = new RiskModelSummary();
        List<RiskModelSummary> riskModelSummaries = new ArrayList<>();
        Integer itemNo = 0;


        if (mode == 1) { // Build Phase Only
            riskModelSummary = new RiskModelSummary();
            itemNo++;
            riskModelSummary.setItemNo(itemNo);
            riskModelSummary.setName("Project Implementation Risk");
            projectScore = Utils.round(projectScore);
            riskModelSummary.setScore(projectScore.toString());
            riskModelSummary.setGrade(projectGrade.toString());
            riskModelSummaries.add(riskModelSummary);
        }

        riskModelSummary = new RiskModelSummary();
        itemNo++;
        riskModelSummary.setItemNo(itemNo);
        postProjectScore = Utils.round(postProjectScore);

            if (riskModelTemplate.getRiskProjectType().getCode().equals("05"))
                riskModelSummary.setName("Holding Company Risk Score");
            else
                riskModelSummary.setName("Post Project Implementation Risk");

        riskModelSummary.setScore(postProjectScore.toString());
        riskModelSummary.setGrade(postProjectGrade);
        riskModelSummaries.add(riskModelSummary);

//            riskModelSummary = new RiskModelSummary();
//            itemNo++;
//            riskModelSummary.setItemNo(itemNo);
//            riskModelSummary.setName(" Post Project Implementation Risk Grade");
//            riskModelSummary.setScore(postProjectGrade.toString());
//            riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummary();
        itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Overall Project Rating");
        riskModelSummary.setScore(Utils.round(riskModelTemplate.getScore()).toString());
        riskModelSummary.setGrade(riskModelTemplate.getOverallProjectGrade());
        riskModelSummaries.add(riskModelSummary);


        if (riskModelTemplate.getApplyRatingModifiers() != null) {
            if (riskModelTemplate.getApplyRatingModifiers() == true) {

                if (riskModelTemplate.getNumberOfNotchesDownAfterRatingModifier() != null) {
                    riskModelSummary = new RiskModelSummary();
                    itemNo++;
                    riskModelSummary.setItemNo(itemNo);
                    riskModelSummary.setName("Number of Notches Down after applying Rating Modifier");
                    riskModelSummary.setGrade(riskModelTemplate.getNumberOfNotchesDownAfterRatingModifier());
                    riskModelSummaries.add(riskModelSummary);
                }

                riskModelSummary = new RiskModelSummary();
                itemNo++;
                riskModelSummary.setItemNo(itemNo);
                riskModelSummary.setName("Modified Project Grade");
                riskModelSummary.setGrade(riskModelTemplate.getModifiedProjectGrade());
                riskModelSummaries.add(riskModelSummary);
            }
        }

        if (riskModelTemplate.getApplyParentalNotchup() != null) {
            if (riskModelTemplate.getApplyParentalNotchup() == true) {

                if (riskModelTemplate.getNumberOfNotchesUpAfterParentalNotchup() != null) {
                    riskModelSummary = new RiskModelSummary();
                    itemNo++;
                    riskModelSummary.setItemNo(itemNo);
                    riskModelSummary.setName("Number of Notches up after applying Parental Notchup");
                    riskModelSummary.setGrade(riskModelTemplate.getNumberOfNotchesUpAfterParentalNotchup());
                    riskModelSummaries.add(riskModelSummary);
                }


                riskModelSummary = new RiskModelSummary();
                itemNo++;
                riskModelSummary.setItemNo(itemNo);
                riskModelSummary.setName("Grade after Parental Notchup");
                riskModelSummary.setGrade(riskModelTemplate.getFinalProjectGrade());
                riskModelSummaries.add(riskModelSummary);
            }
        }

        riskModelSummary = new RiskModelSummary();
        itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Final Project Grade");
        riskModelSummary.setGrade(riskModelTemplate.getFinalProjectGrade());
        riskModelSummaries.add(riskModelSummary);


        return riskModelSummaries;

    }


}
