package com.pfs.riskmodel.service.categoricval;

import com.pfs.riskmodel.businessconfig.InfraRoad_HAM_BuildPhaseGrade;
import com.pfs.riskmodel.businessconfig.ProjectGrade;
import com.pfs.riskmodel.domain.RiskModelSummary;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskRatingModifier;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.service.modelvaluator.RiskParentalNotchUpEvaluator;
import com.pfs.riskmodel.service.modelvaluator.Utils;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 31-Dec-18.
 */
public class InfraRoadHAM_BuildPhase_Valuator {


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
          String modifiedProjectGrade = " ";
          Integer modifiedProjectGradeAsNumber = 0;
          String afterParentalNotchUpGrade = " ";
          Integer afterParentNotchupGradeAsNumber;
          String finalProjectGrade = " ";

          // Overall

        for (RiskType riskType : riskModelTemplate.getRiskTypes()) {
            if (riskType.getDescription().equals("Infra Road HAM Project Implementation Risk Type")){
               ProjectGrade projectGrade = Utils.fetchGrade(InfraRoad_HAM_BuildPhaseGrade.projectGradeList,riskType.getScore());
               projectIRScore = riskType.getScore();
               projectIRGrade = projectGrade.getCommonScaleGrade();
               riskType.setGrade(projectGrade.getCommonScaleGrade());
            }

            if (riskType.getDescription().equals("Infra Road HAM Post Project Implementation Risk Type")) {
                ProjectGrade projectGrade = Utils.fetchGrade(InfraRoad_HAM_BuildPhaseGrade.projectGradeList,riskType.getScore());
                postProjectIRScore = riskType.getScore();
                postProjectIRGrade = projectGrade.getCommonScaleGrade();
                riskType.setGrade(projectGrade.getCommonScaleGrade());
            }
        }


        // Build Phase Score = Min of ProjectIR and PostProjectIR
            overallProjectScore = Math.min(projectIRScore, postProjectIRScore);
            riskModelTemplate.setScore(overallProjectScore);
        //  Build Phase Grade
             ProjectGrade overallProjectGradeObject = Utils.fetchGrade(InfraRoad_HAM_BuildPhaseGrade.projectGradeList,overallProjectScore); //.getCommonScaleGrade();
             overallProjectGrade = overallProjectGradeObject.getCommonScaleGrade();
             modifiedProjectGrade = overallProjectGrade;
             finalProjectGrade = overallProjectGrade;


        // Modified GRADE
        Integer itemNumberOfCurrentGrade = overallProjectGradeObject.getItemNo();
        Integer itemNumberofModifiedGrade = 0; //itemNumberOfCurrentGrade;

        for (RiskRatingModifier riskRatingModifier: riskModelTemplate.getRiskRatingModifiers()) {

            // Execute Grade Capping to SubInvestment GRADE
            if (riskRatingModifier.getSubInvestmentGradeCapping() == true) {

                if (overallProjectScore >= 6.25 ){
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
                    modifiedProjectGradeAsNumber = projectGrade.getGradeAsNumber();
            }

        }


        finalProjectGrade = modifiedProjectGrade;
        afterParentalNotchUpGrade = modifiedProjectGrade;

        //    Overall Rating Grade
        // GRADE AFTER PARENTAL NOTCHUP

        // TODO Evaluate ParentalNotcup to SET afterParentalNotchUpGrade




        finalProjectGrade = modifiedProjectGrade;


        // Prepare Summary
        RiskModelSummary riskModelSummary = new RiskModelSummary();
        List<RiskModelSummary> riskModelSummaries = new ArrayList<>();
        Integer itemNo = 1;

        riskModelSummary.setId(null);
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Project Implementation Risk Score");
        riskModelSummary.setValue(projectIRScore.toString());
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummary(); itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Project Implementation Risk Grade");
        riskModelSummary.setValue(projectIRGrade.toString());
        riskModelSummaries.add(riskModelSummary);


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

        riskModelTemplate.setOverallProjectGrade(overallProjectGrade);
        riskModelTemplate.setModifiedProjectGrade(modifiedProjectGrade);
        riskModelTemplate.setModifiedProjectGradeAsNumber(modifiedProjectGradeAsNumber);


        // Evaluate Parental NotchUp
        RiskParentalNotchUpEvaluator riskParentalNotchUpEvaluator = new RiskParentalNotchUpEvaluator();

        Integer numberofNotchesAfterParental =
                riskParentalNotchUpEvaluator.evaluateParentalNotchup(riskModelTemplate.getRiskParentalNotchUps().get(0),
                        riskModelTemplate.getProjectRiskLevel().getCode(),
                        riskModelTemplate.getModifiedProjectGradeAsNumber());


        // TODO   - Parental Notcup Cappings ------------------------


        // Apply after Parental Notchup
        // Get Modified Project Grade Object
        ProjectGrade modProjectGrade =  Utils.getProjectGradeByCommonScaleGrade(InfraRoad_HAM_BuildPhaseGrade.projectGradeList,
                                                                riskModelTemplate.getModifiedProjectGrade());
        modifiedProjectGradeAsNumber = modProjectGrade.getGradeAsNumber();
        afterParentNotchupGradeAsNumber = modifiedProjectGradeAsNumber + numberofNotchesAfterParental;
        ProjectGrade afterParentalNotchUpGradeObject = Utils.getProjectGradeByGradeAsNumber( InfraRoad_HAM_BuildPhaseGrade.projectGradeList,
                                                                                                 afterParentNotchupGradeAsNumber);
        // Set the Grade after Parental Notchup
        riskModelTemplate.setAfterParentalNotchUpGrade(afterParentalNotchUpGradeObject.getCommonScaleGrade());

        riskModelTemplate.setAfterParentalNotchUpGrade(afterParentalNotchUpGrade);
        riskModelTemplate.setFinalProjectGrade(finalProjectGrade);

        return riskModelTemplate;
    }



    public   RiskModelTemplate setGradeAfterParentalNotchup (RiskModelTemplate riskModelTemplate, Integer numberOfNotches) {


        ProjectGrade projectGrade = new ProjectGrade();

        String modifiedProjectGrade =  riskModelTemplate.getModifiedProjectGrade();



        for (RiskType riskType : riskModelTemplate.getRiskTypes()) {
            if (riskType.getDescription().equals("Infra Road HAM Project Implementation Risk Type")){
                 projectGrade = Utils.fetchGrade(InfraRoad_HAM_BuildPhaseGrade.projectGradeList,riskType.getScore());
             }

            if (riskType.getDescription().equals("Infra Road HAM Post Project Implementation Risk Type")) {
                 projectGrade = Utils.fetchGrade(InfraRoad_HAM_BuildPhaseGrade.projectGradeList,riskType.getScore());
            }
        }








        return riskModelTemplate;
    }

}