package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.businessconfig.*;
import com.pfs.riskmodel.domain.RiskModelSummary;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.service.modelvaluator.CommonComputation;
import com.pfs.riskmodel.service.modelvaluator.Utils;

import java.util.List;

/**
 * Created by sajeev on 31-Dec-18.
 */
public class InfraRoadToll_OperationalPhase_Valuator {


    List<ProjectGrade> projectGradeList = InfraRoad_Toll_OperationalPhaseGrade.projectGradeList;


    public RiskModelTemplate valuate (RiskModelTemplate riskModelTemplate) {

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

                  projectGrade =
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
        riskModelTemplate.setOverallProjectGrade(projectGrade.getCommonScaleGrade());

        riskModelTemplate.setFinalProjectGrade(overallProjectGradeObject.getCommonScaleGrade());


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
            if (riskModelTemplate.getApplyParentalNotchup() != null) {

                if (riskModelTemplate.getApplyParentalNotchup() == true) {

                    afterParentalNotchupGrade = commonComputation.evaluateParentalNotchup(
                            riskModelTemplate, projectGradeList,
                            modifiedProjectGrade,
                            projectGradeList.size());
                }
            }
        }

        // Prepare Summary
        List<RiskModelSummary> riskModelSummaryList =
                commonComputation.getSummary( null,null,
                        projectScore,projectScoreGrade,
                        riskModelTemplate,0);

        riskModelTemplate.setRiskModelSummaries(riskModelSummaryList);

        return riskModelTemplate;
    }

}
