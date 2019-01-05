package com.pfs.riskmodel.service.modelvaluator;

import com.pfs.riskmodel.businessconfig.ProjectGrade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

/**
 * Created by sajeev on 31-Dec-18.
 */
public class Utils {


    // Minimum
    public static Double getMinimum(List<Double> list ) {
        Double minValue = 0D;
        minValue = Collections.min(list);
        return minValue;
    }

    // Get Maximum
    public static Double getMaximum(List<Double> list ) {
        Double minValue = 0D;
        minValue = Collections.max(list);
        return minValue;
    }

    // Round to 2 Decimals
    public static Double round(Double score) {


        BigDecimal bd = new BigDecimal(Double.toString(score));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

    public static ProjectGrade fetchGrade(List<ProjectGrade> projectGrades, Double score) {

        ProjectGrade projectGrade = new ProjectGrade();

        for (ProjectGrade projectGrade1: projectGrades) {
            if ( score >= projectGrade1.getFromScore() & score <= projectGrade1.getToScore() ) {
                return projectGrade1;
            }
        }

        return null;

    }

    public static ProjectGrade getProjectGradeByCommonScaleGrade( List<ProjectGrade> projectGrades, String commonScaleGrade ) {


        for (ProjectGrade projectGrade : projectGrades) {
            if (projectGrade.getCommonScaleGrade().equals(commonScaleGrade)) {
                return projectGrade;
            }
        }
        return null;
    }


    public static ProjectGrade getProjectGradeByGradeAsNumber(List<ProjectGrade> projectGrades, Integer gradeAsNumber) {

        for (ProjectGrade projectGrade: projectGrades) {
            if (projectGrade.getGradeAsNumber() == gradeAsNumber) {
                return projectGrade;
            }
        }

        return null;
    }


}
