package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.businessconfig.InfraRoad_HAM_BuildPhaseGrade;
import com.pfs.riskmodel.businessconfig.ProjectGrade;
import com.pfs.riskmodel.domain.RiskModelSummary;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskRatingModifier;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.service.modelvaluator.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 31-Dec-18.
 */
public class InfraRoadHAM_OperationalPhase_Valuator {


    public RiskModelTemplate valuate (RiskModelTemplate riskModelTemplate) {



         // Risk Type Scores and Grades
         //Scores
          Double postProjectIRScore = 0D;
          Double overallProjectScore = 0D;


          //GRADE
          String projectIRGrade = "";
          String postProjectIRGrade = "";
          String overallProjectGrade = "";
          String modifiedProjectGrade = "";
          String afterParentalNotchUpGrade = "";
          String finalProjectGrade = "";

        // Risk Type Score
        for (RiskType riskType : riskModelTemplate.getRiskTypes()) {
            if (riskType.getDescription().equals("Infra Road HAM Post Project Implementation Risk Type")) {
                ProjectGrade projectGrade = Utils.fetchGrade(InfraRoad_HAM_BuildPhaseGrade.projectGradeList,riskType.getScore());
                postProjectIRScore = riskType.getScore();
                postProjectIRGrade = projectGrade.getCommonScaleGrade();
            }
        }


        // Build Phase Score = PostProjectIR
            overallProjectScore = postProjectIRScore;
        //  Build Phase Grade
             ProjectGrade overallProjectGradeObject = Utils.fetchGrade(InfraRoad_HAM_BuildPhaseGrade.projectGradeList,overallProjectScore); //.getCommonScaleGrade();
             overallProjectGrade = overallProjectGradeObject.getCommonScaleGrade();
             modifiedProjectGrade = overallProjectGrade;



        // Modified GRADE
        Integer itemNumberOfCurrentGrade = overallProjectGradeObject.getItemNo();
        Integer itemNumberofModifiedGrade = 0; //itemNumberOfCurrentGrade;

        for (RiskRatingModifier riskRatingModifier: riskModelTemplate.getRiskRatingModifiers()) {

            if (riskRatingModifier.getSubInvestmentGradeCapping() == true) {

                if (overallProjectScore >= 5.00 ){
                    modifiedProjectGrade = "GRADE 7";
                }
            }

            // Down by One Notch
            if (riskRatingModifier.getNumberOfNotchesDown() == 3 || riskRatingModifier.getNumberOfNotchesDown() == 4){
                itemNumberofModifiedGrade = itemNumberOfCurrentGrade - 1;
            }

            // Down by Two Notches
            if (riskRatingModifier.getNumberOfNotchesDown() == 5 || riskRatingModifier.getNumberOfNotchesDown() == 6){
                itemNumberofModifiedGrade = itemNumberOfCurrentGrade - 1;
            }

            for (ProjectGrade projectGrade: InfraRoad_HAM_BuildPhaseGrade.projectGradeList){
                if (projectGrade.getItemNo() == itemNumberofModifiedGrade)
                    modifiedProjectGrade = projectGrade.getCommonScaleGrade();
            }

        }


        //    Overall Rating Grade
        // GRADE AFTER PARENTAL NOTCHUP




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
