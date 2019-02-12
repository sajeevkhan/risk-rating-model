package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.businessconfig.InfraRoad_Toll_BuildPhaseGrade;
import com.pfs.riskmodel.businessconfig.InfraRoad_Toll_OperationalPhaseGrade;
import com.pfs.riskmodel.businessconfig.ProjectGrade;
import com.pfs.riskmodel.domain.RiskModelSummary;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.service.modelvaluator.CommonComputation;
import com.pfs.riskmodel.service.modelvaluator.Utils;

import java.util.List;

/**
 * Created by sajeev on 31-Dec-18.
 */
public class InfraRoadToll_BuildPhase_Valuator {


    List<ProjectGrade> projectGradeList = InfraRoad_Toll_BuildPhaseGrade.projectGradeList;
    List<ProjectGrade> operationalProjectGradeList = InfraRoad_Toll_OperationalPhaseGrade.projectGradeList;


    public RiskModelTemplate valuate (RiskModelTemplate riskModelTemplate) {

        // Post Project Implementation Project Score
        Double postProjectImplScore = 0D;
        String postProjectImplScoreGrade = " ";
        // Project Implementation Score
        Double projectImplScore = 0D;
        String projectImplScoreGrade = " ";


        // Project Scores and Grade
        Double projectScore = 0D;
         Double overallProjectScore = 0D;

         String overallProjectGrade = "";


        ProjectGrade projectGrade = new ProjectGrade();
        ProjectGrade postProjectGrade = new ProjectGrade();
        ProjectGrade modifiedProjectGrade = new ProjectGrade();
        ProjectGrade afterParentalNotchupGrade = new ProjectGrade();

        // Risk Type Score
        for (RiskType riskType : riskModelTemplate.getRiskTypes()) {
            if (riskType.getDescription().contains("Post")) {

                  // PPIR Grade = Operational Phase Grade
                  projectGrade =
                        Utils.fetchGrade(operationalProjectGradeList,riskType.getScore());

                postProjectImplScore = riskType.getScore();
                postProjectImplScoreGrade = projectGrade.getCommonScaleGrade();
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
        overallProjectGrade = overallProjectGradeObject.getCommonScaleGrade();
        riskModelTemplate.setOverallProjectGrade(overallProjectGrade);


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
                commonComputation.getSummary( projectScore, projectImplScoreGrade,
                                                        postProjectImplScore,postProjectImplScoreGrade,
                                                        riskModelTemplate,1);

        riskModelTemplate.setRiskModelSummaries(riskModelSummaryList);

        return riskModelTemplate;
    }

}
