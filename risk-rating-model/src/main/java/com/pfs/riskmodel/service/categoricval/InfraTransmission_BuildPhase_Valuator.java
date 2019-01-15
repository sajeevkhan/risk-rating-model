package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.businessconfig.*;
import com.pfs.riskmodel.domain.RiskModelSummary;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskRatingModifier;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.service.modelvaluator.CommonComputation;
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



        ProjectGrade projectGrade = new ProjectGrade();
        ProjectGrade modifiedProjectGrade = new ProjectGrade();
        ProjectGrade afterParentalNotchupGrade = new ProjectGrade();



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
                projectGrade =
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
        riskModelTemplate.setOverallProjectGrade(overallProjectGradeObject.getCommonScaleGrade());



        // Compute Modified Project Grade
        CommonComputation commonComputation = new CommonComputation();
        modifiedProjectGrade = commonComputation.applyRatingModifier(riskModelTemplate,
                projectGrade,
                projectGradeList,
                6.25);

        // Check if Parental Notchup needs to be evaluated or not
        if ( modifiedProjectGrade.getGradeAsNumber() >= 7 ) {
            riskModelTemplate.getRiskParentalNotchUps().get(0).setIsParentalNotchUpApplicable(false);
            riskModelTemplate.setFinalProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
            riskModelTemplate.setAfterParentalNotchUpGrade(modifiedProjectGrade.getCommonScaleGrade());
        }
        else {  // Evaluate Parental Notchup

            afterParentalNotchupGrade = commonComputation.evaluateParentalNotchup(
                    riskModelTemplate, projectGradeList,
                    modifiedProjectGrade,
                    projectGradeList.size() );
        }

        // Prepare Summary
        List<RiskModelSummary> riskModelSummaryList =
                commonComputation.getBuildPhaseSummary(projectScore,
                                                       projectScoreGrade,
                                                       postProjectImplScore,
                                                       postProjectImplScoreGrade,
                                                       riskModelTemplate,1);

        riskModelTemplate.setRiskModelSummaries(riskModelSummaryList);

        return riskModelTemplate;
    }

}
