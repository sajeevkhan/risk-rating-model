package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.businessconfig.InfraRoad_HAM_BuildPhaseGrade;
import com.pfs.riskmodel.businessconfig.InfraRoad_HAM_OperationalPhaseGrade;
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
public class InfraRoadHAM_BuildPhase_Valuator {


    List<ProjectGrade> projectGradeList = InfraRoad_HAM_BuildPhaseGrade.projectGradeList;
    List<ProjectGrade> operationalProjectGradeList = InfraRoad_HAM_OperationalPhaseGrade.projectGradeList;


    public RiskModelTemplate valuate (RiskModelTemplate riskModelTemplate) {

         // Risk Type Scores and Grades
         //Scores
          Double projectIRScore = 0D;
          Double postProjectIRScore = 0D;
          Double overallProjectScore = 0D;


          //GRADE
          String projectIRGrade = " ";
          String postProjectIRGrade = " ";
          String overallProjectGrade = " ";

        ProjectGrade projectGrade = new ProjectGrade();
        ProjectGrade modifiedProjectGrade = new ProjectGrade();
        ProjectGrade afterParentalNotchupGrade = new ProjectGrade();

        // Get Risk Types Scores and Grades for Project and Post Project Risk Types
        for (RiskType riskType : riskModelTemplate.getRiskTypes()) {

            if (riskType.getDescription().contains("Post")) {
                // PPIR GRADE = Operational Phase GRADE
                projectGrade = Utils.fetchGrade(operationalProjectGradeList,riskType.getScore());
                postProjectIRScore = riskType.getScore();
                postProjectIRGrade = projectGrade.getCommonScaleGrade();
                riskType.setGrade(projectGrade.getCommonScaleGrade());
            } else {
                    projectGrade = Utils.fetchGrade(projectGradeList,riskType.getScore());
                    projectIRScore = riskType.getScore();
                    projectIRGrade = projectGrade.getCommonScaleGrade();
                    riskType.setGrade(projectGrade.getCommonScaleGrade());
            }
        }


        // Build Phase Phase Score = Minimum of Project Impl Score and Post Project Impl Score
        Double projectScore = Math.min(postProjectIRScore, projectIRScore);
        overallProjectScore = projectScore;
        riskModelTemplate.setScore(overallProjectScore);


        //  Build Phase Grade
        ProjectGrade overallProjectGradeObject = Utils.fetchGrade(projectGradeList,overallProjectScore);
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
                commonComputation.getSummary(projectIRScore, projectIRGrade,postProjectIRScore,postProjectIRGrade,riskModelTemplate,1);

        riskModelTemplate.setRiskModelSummaries(riskModelSummaryList);
        return riskModelTemplate;
    }

}
