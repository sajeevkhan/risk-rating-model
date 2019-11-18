package com.pfs.riskmodel.businessconfig;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sajeev on 31-Dec-18.
 */

@Getter
@Setter
@AllArgsConstructor

public class ProjectGrade {

    public ProjectGrade() {
    }

    private Integer itemNo;
    private String modelCategory;

    private Double fromScore;
    private Double toScore;
    private String gradeCategory;
    private String commonScaleGrade;
    private Integer gradeAsNumber;
   // private Integer pGrade;

    @Override
    public String toString() {
        return "ProjectGrade{" +
                "itemNo=" + itemNo +
                ", modelCategory='" + modelCategory + '\'' +
                ", fromScore=" + fromScore +
                ", toScore=" + toScore +
                ", gradeCategory='" + gradeCategory + '\'' +
                ", commonScaleGrade='" + commonScaleGrade + '\'' +
                ", gradeAsNumber=" + gradeAsNumber +
                '}';
    }
}
