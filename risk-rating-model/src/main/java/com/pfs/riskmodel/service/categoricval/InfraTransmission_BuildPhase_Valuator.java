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

/**
 * Created by sajeev on 31-Dec-18.
 */
public class InfraTransmission_BuildPhase_Valuator {


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
                        Utils.fetchGrade(Infra_Transmission_BuildPhaseGrade.projectGradeList,riskType.getScore());

                postProjectImplScore = riskType.getScore();
                postProjectImplScoreGrade = postProjectGrade.getCommonScaleGrade();
                riskType.setGrade(postProjectImplScoreGrade);
            }

            else {
                ProjectGrade projectGrade =
                        Utils.fetchGrade(Renewables_Grade.projectGradeList,riskType.getScore());

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
                Utils.fetchGrade(Infra_Transmission_BuildPhaseGrade.projectGradeList,overallProjectScore);
        overallProjectGrade = overallProjectGradeObject.getCommonScaleGrade();

        // Modified GRADE
        Integer itemNumberOfCurrentGrade = overallProjectGradeObject.getItemNo();
        Integer itemNumberofModifiedGrade = 0; //itemNumberOfCurrentGrade;


        // EXECUTE RISK RATING MODIFIERS
        for (RiskRatingModifier riskRatingModifier: riskModelTemplate.getRiskRatingModifiers()) {

            // Execute Grade Capping to SubInvestment GRADE
            if (riskRatingModifier.getSubInvestmentGradeCapping() == true) {
                if (overallProjectScore >= 6.25 ){
                    modifiedProjectGrade = "GRADE 7";
                }
            }

            // Down by One Notch
            if (riskRatingModifier.getNumberOfNotchesDown() == 3 || riskRatingModifier.getNumberOfNotchesDown() == 4){
                itemNumberofModifiedGrade = itemNumberOfCurrentGrade + 1;
            }

            // Down by Two Notches
            if (riskRatingModifier.getNumberOfNotchesDown() == 5 || riskRatingModifier.getNumberOfNotchesDown() == 6){
                itemNumberofModifiedGrade = itemNumberOfCurrentGrade + 2;
            }

            if (itemNumberofModifiedGrade != 0) {
                for (ProjectGrade projectGrade : Infra_Transmission_BuildPhaseGrade.projectGradeList) {
                    if (projectGrade.getItemNo() == itemNumberofModifiedGrade)
                        modifiedProjectGrade = projectGrade.getCommonScaleGrade();
                    modifiedProjectGradeAsNumber = projectGrade.getGradeAsNumber();
                }
            }
            else {
                // If Rating Modifiers do not influence the calculation, Project Rating is passed on
                modifiedProjectGrade = overallProjectGrade;
                modifiedProjectGradeAsNumber = overallProjectGradeObject.getGradeAsNumber();
            }
        }

        riskModelTemplate.setOverallProjectGrade(overallProjectGrade);
        riskModelTemplate.setModifiedProjectGrade(modifiedProjectGrade);
        riskModelTemplate.setModifiedProjectGradeAsNumber(modifiedProjectGradeAsNumber);


        // Evaluate Parental NotchUp
        RiskParentalNotchUpEvaluator riskParentalNotchUpEvaluator = new RiskParentalNotchUpEvaluator();

        Integer numberofNotchesAfterParental =
                riskParentalNotchUpEvaluator.evaluateParentalNotchup(
                        riskModelTemplate.getRiskParentalNotchUps().get(0),
                        riskModelTemplate.getProjectRiskLevel().getCode(),
                        riskModelTemplate.getModifiedProjectGradeAsNumber());

        // TODO   - Parental Notchup Cappings ------------------------

        // Apply after Parental Notchup
        // Get Modified Project Grade Object
        ProjectGrade modProjectGrade =  Utils.getProjectGradeByCommonScaleGrade(Infra_Transmission_BuildPhaseGrade.projectGradeList,
                riskModelTemplate.getModifiedProjectGrade());
        modifiedProjectGradeAsNumber = modProjectGrade.getGradeAsNumber();
        afterParentNotchupGradeAsNumber = modifiedProjectGradeAsNumber - numberofNotchesAfterParental;

        ProjectGrade afterParentalNotchUpGradeObject =
                Utils.getProjectGradeByGradeAsNumber( Infra_Transmission_BuildPhaseGrade.projectGradeList, afterParentNotchupGradeAsNumber);
        // Set the Grade after Parental Notchup
        if (afterParentalNotchUpGrade != null) {
            riskModelTemplate.setAfterParentalNotchUpGrade(afterParentalNotchUpGradeObject.getCommonScaleGrade());
            afterParentalNotchUpGrade = afterParentalNotchUpGradeObject.getCommonScaleGrade();
        }
        else {
            afterParentalNotchUpGrade = modifiedProjectGrade;
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