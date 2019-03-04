package com.pfs.riskmodel.service.categoricval;

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
public class InfraRoadHAM_OperationalPhase_Valuator {


    List<ProjectGrade> projectGradeList = InfraRoad_Toll_OperationalPhaseGrade.projectGradeList;


    public RiskModelTemplate valuate (RiskModelTemplate riskModelTemplate) {



         // Risk Type Scores and Grades
         //Scores
          Double postProjectIRScore = 0D;
          Double overallProjectScore = 0D;


        //GRADE
         String postProjectIRGrade = " ";
         String overallProjectGrade = " ";
         String afterParentalNotchUpGrade = " ";
         String finalProjectGrade = " ";


        ProjectGrade projectGrade = new ProjectGrade();
        ProjectGrade modifiedProjectGrade = new ProjectGrade();
        ProjectGrade afterParentalNotchupGrade = new ProjectGrade();

        // Risk Type Score
        for (RiskType riskType : riskModelTemplate.getRiskTypes()) {
            if (riskType.getDescription().contains("Post")) {
                projectGrade = Utils.fetchGrade(projectGradeList,riskType.getScore());
                postProjectIRScore = riskType.getScore();
                postProjectIRGrade = projectGrade.getCommonScaleGrade();
            }
        }


        // Operational Phase Score = PostProjectIR
            overallProjectScore = postProjectIRScore;
        //  Operational Phase Grade
             ProjectGrade overallProjectGradeObject = Utils.fetchGrade(projectGradeList,overallProjectScore); //.getCommonScaleGrade();
             overallProjectGrade = overallProjectGradeObject.getCommonScaleGrade();
             riskModelTemplate.setOverallProjectGrade(projectGrade.getCommonScaleGrade());
             riskModelTemplate.setScore(postProjectIRScore);

        riskModelTemplate.setFinalProjectGrade(overallProjectGradeObject.getCommonScaleGrade());

        // Compute Modified Project Grade
        CommonComputation commonComputation = new CommonComputation();
        modifiedProjectGrade = commonComputation.applyRatingModifier(riskModelTemplate,
                projectGrade,
                projectGradeList,
                5.00);

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
                commonComputation.getSummary( null,
                                                        null,
                                                         postProjectIRScore,
                                                         postProjectIRGrade,
                                                         riskModelTemplate,0);

        riskModelTemplate.setRiskModelSummaries(riskModelSummaryList);


        return riskModelTemplate;
    }

}
