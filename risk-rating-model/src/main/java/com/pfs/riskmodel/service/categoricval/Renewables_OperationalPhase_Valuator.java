package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.businessconfig.Infra_Transmission_BuildPhaseGrade;
import com.pfs.riskmodel.businessconfig.Renewables_Grade;
import com.pfs.riskmodel.businessconfig.ProjectGrade;
import com.pfs.riskmodel.businessconfig.Renewables_Grade;
import com.pfs.riskmodel.domain.RiskModelSummary;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskRatingModifier;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.service.modelvaluator.CommonComputation;
import com.pfs.riskmodel.service.modelvaluator.RiskParentalNotchUpEvaluator;
import com.pfs.riskmodel.service.modelvaluator.Utils;
import org.springframework.lang.Nullable;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sajeev on 31-Dec-18.
 */
public class Renewables_OperationalPhase_Valuator {


    List<ProjectGrade> projectGradeList = Renewables_Grade.projectGradeList;

    public RiskModelTemplate valuate (RiskModelTemplate riskModelTemplate) {

//        // Project Scores and Grade
        Double projectScore = 0D;
        String projectScoreGrade = "";
        Double overallProjectScore = 0D;

        ProjectGrade projectGrade = new ProjectGrade();
        ProjectGrade modifiedProjectGrade = new ProjectGrade();
        ProjectGrade afterParentalNotchupGrade = new ProjectGrade();

        // Risk Type Score
        for (RiskType riskType : riskModelTemplate.getRiskTypes()) {
            if (riskType.getDescription().contains("Operational")) {
                projectGrade = Utils.fetchGrade(projectGradeList,riskType.getScore());

                projectScore = riskType.getScore();
                projectScoreGrade = projectGrade.getCommonScaleGrade();
                riskType.setGrade(projectScoreGrade);
            }
        }

         // Operational Phase Score = projectScore
          riskModelTemplate.setScore(projectScore);
          riskModelTemplate.setOverallProjectGrade(projectGrade.getCommonScaleGrade());


        // Compute Modified Project Grade
        CommonComputation commonComputation = new CommonComputation();
        modifiedProjectGrade = commonComputation.applyRatingModifier(riskModelTemplate,
                                                                     projectGrade,
                                                                     projectGradeList,
                                                                    4.25);

        // Check if Parental Notchup needs to be evaluated or not
        if ( modifiedProjectGrade.getGradeAsNumber() >= 7 ) {
            riskModelTemplate.getRiskParentalNotchUps().get(0).setIsParentalNotchUpApplicable(false);
            riskModelTemplate.setFinalProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
            riskModelTemplate.setOverallProjectGrade(modifiedProjectGrade.getCommonScaleGrade());
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
                commonComputation.getBuildPhaseSummary(null, null,
                        projectScore, projectGrade.getCommonScaleGrade()
                       ,riskModelTemplate, 0);
        riskModelTemplate.setRiskModelSummaries(riskModelSummaryList);


        return riskModelTemplate;
    }

}
