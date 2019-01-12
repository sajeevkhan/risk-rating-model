package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.businessconfig.InfraRoad_HAM_BuildPhaseGrade;
import com.pfs.riskmodel.businessconfig.InfraRoad_Toll_OperationalPhaseGrade;
import com.pfs.riskmodel.businessconfig.ProjectGrade;
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
public class InfraRoadHAM_OperationalPhase_Valuator {


    List<ProjectGrade> projectGradeList = InfraRoad_Toll_OperationalPhaseGrade.projectGradeList;


    public RiskModelTemplate valuate (RiskModelTemplate riskModelTemplate) {



         // Risk Type Scores and Grades
         //Scores
          Double postProjectIRScore = 0D;
          Double overallProjectScore = 0D;


        //GRADE
        String projectIRGrade = " ";
        String postProjectIRGrade = " ";
        String overallProjectGrade = " ";
        String modifiedProjectGrade = " ";
        Integer modifiedProjectGradeAsNumber = 0;
        String afterParentalNotchUpGrade = " ";
        Integer afterParentNotchupGradeAsNumber;
        String finalProjectGrade = " ";

        // Risk Type Score
        for (RiskType riskType : riskModelTemplate.getRiskTypes()) {
            if (riskType.getDescription().equals("Infra Road HAM Post Project Implementation Risk Type")) {
                ProjectGrade projectGrade = Utils.fetchGrade(projectGradeList,riskType.getScore());
                postProjectIRScore = riskType.getScore();
                postProjectIRGrade = projectGrade.getCommonScaleGrade();
            }
        }


        // Build Phase Score = PostProjectIR
            overallProjectScore = postProjectIRScore;
        //  Build Phase Grade
             ProjectGrade overallProjectGradeObject = Utils.fetchGrade(projectGradeList,overallProjectScore); //.getCommonScaleGrade();
             overallProjectGrade = overallProjectGradeObject.getCommonScaleGrade();
             modifiedProjectGrade = overallProjectGrade;



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

            if (riskRatingModifier.getNumberOfNotchesDown() != 0) {

                // Add the "Number of Notch downs" to the overall project grade number
                modifiedProjectGradeAsNumber = overallProjectGradeObject.getGradeAsNumber() + riskRatingModifier.getNumberOfNotchesDown();

                // Find the the Modified Grade
                for (ProjectGrade projectGrade : projectGradeList) {
                    if (projectGrade.getItemNo() == modifiedProjectGradeAsNumber) {
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

        Integer numberofNotchesAfterParental =
                riskParentalNotchUpEvaluator.evaluateParentalNotchup(
                        riskModelTemplate.getRiskParentalNotchUps().get(0),
                        riskModelTemplate.getProjectRiskLevel().getCode(),
                        riskModelTemplate.getModifiedProjectGradeAsNumber());

        // TODO   - Parental Notchup Cappings ------------------------

        // Apply after Parental Notchup
        // Get Modified Project Grade Object
        ProjectGrade modProjectGrade =  Utils.getProjectGradeByCommonScaleGrade(projectGradeList,
                riskModelTemplate.getModifiedProjectGrade());
        modifiedProjectGradeAsNumber = modProjectGrade.getGradeAsNumber();
        afterParentNotchupGradeAsNumber = modifiedProjectGradeAsNumber - numberofNotchesAfterParental;

        ProjectGrade afterParentalNotchUpGradeObject =
                Utils.getProjectGradeByGradeAsNumber( projectGradeList, afterParentNotchupGradeAsNumber);
        // Set the Grade after Parental Notchup
        if (afterParentalNotchUpGradeObject != null) {
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
        Integer itemNo = 1;


        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Post Project Implementation Risk Score");
        riskModelSummary.setValue(postProjectIRScore.toString());
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummary();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Post Project Implementation Risk Grade");
        riskModelSummary.setValue(postProjectIRGrade.toString());
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
