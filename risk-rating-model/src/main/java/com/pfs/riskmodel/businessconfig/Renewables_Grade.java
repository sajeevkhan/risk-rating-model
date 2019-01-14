package com.pfs.riskmodel.businessconfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 31-Dec-18.
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class Renewables_Grade implements CommandLineRunner {


    public  static  List<ProjectGrade> projectGradeList;

    @Override
    public void run(String... strings) throws Exception {

        if ( projectGradeList == null){
             projectGradeList = new ArrayList<>();
        }

        ProjectGrade p1 = new ProjectGrade(1,"1",0.0D,1.50,"Default","GRADE 10",10);
        ProjectGrade p2 = new ProjectGrade(2,"1",1.50,2.50,"Sub Investment","GRADE 9",9);
        ProjectGrade p3 = new ProjectGrade(3,"1",2.50,3.50,"Sub Investment","GRADE 8",8);
        ProjectGrade p4 = new ProjectGrade(4,"1",3.50,4.25,"Sub Investment","GRADE 7",7);
        ProjectGrade p5 = new ProjectGrade(5,"1",4.25,5.00,"Sub Investment","GRADE 6",6);
        ProjectGrade p6 = new ProjectGrade(6,"1",5.00,5.75,"Investment","GRADE 5",5);
        ProjectGrade p7 = new ProjectGrade(7,"1",5.75,7.00,"Investment","GRADE 4",4);
        ProjectGrade p8 = new ProjectGrade(8,"1",7.00,8.00,"Investment","GRADE 3",3);
        ProjectGrade p9 = new ProjectGrade(9,"1",8.00,9.00,"Investment","GRADE 2",2);
        ProjectGrade p10 = new ProjectGrade(10,"1",9.00,10.0,"Investment","GRADE 1",1);

        projectGradeList.add(p1);
        projectGradeList.add(p2);
        projectGradeList.add(p3);
        projectGradeList.add(p4);
        projectGradeList.add(p5);
        projectGradeList.add(p6);
        projectGradeList.add(p7);
        projectGradeList.add(p8);
        projectGradeList.add(p9);
        projectGradeList.add(p10);

    }

    

}
