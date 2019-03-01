package com.pfs.riskmodel.businessconfig;

import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Component
@RequiredArgsConstructor



public class Initializer implements CommandLineRunner{

    @Autowired
    private final ComputingMethodRepository computingMethodRepository;
    @Autowired
    private final ScoreTypeRepository scoreTypeRepository;
    @Autowired
    private final RiskProjectTypeRepository riskProjectTypeRepository;
    @Autowired
    private final ProjectRiskLevelRepository projectRiskLevelRepository;
    @Autowired
    private final ModelCategoryRepository modelCategoryRepository;
    @Autowired
    private final RatingModifierComputingMethodRepository riskRatingComputingMethodRepository;
    @Autowired
    private final RiskPurposeRepository purposeRepository;
    @Autowired
    private final WorkflowStatusRepository workflowStatusRepository;
    @Autowired
    private final WorkflowAssignmentRepository workflowAssignmentRepository;

    @Autowired
    private Environment environment;

    @Override
    public void run(String... strings) throws Exception {



        if(modelCategoryRepository.count() == 0) {
            ModelCategory  c1 = new ModelCategory(1, "Renewable-Build");
            ModelCategory  c2 = new ModelCategory( 2,"Renewable-Operational");
            ModelCategory  c3 = new ModelCategory(  3,"InfraTransmission-Build");
            ModelCategory  c4 = new ModelCategory( 4,"InfraTransmission-Operational");
            ModelCategory  c5 = new ModelCategory( 5,"InfraRoadProjectHybridAnnuity-Build");
            ModelCategory  c6 = new ModelCategory( 6,"InfraRoadProjectHybridAnnuity-Operational");
            ModelCategory  c7 = new ModelCategory( 7,"InfraRoadProjectToll-Build");
            ModelCategory  c8 = new ModelCategory( 8,"InfraRoadProjectToll-Operational");
            ModelCategory  c9 = new ModelCategory( 9,"HoldingCompany-Build");
            ModelCategory  c10 = new ModelCategory( 10,"HoldingCompany-Operational");


            modelCategoryRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10));
            log.info("-------------------------- Added Model Category  data");
        }



        if(computingMethodRepository.count() == 0) {
            ComputingMethod  c1 = new ComputingMethod(null,"01", "Weighted");
            ComputingMethod  c2 = new ComputingMethod(null,"02", "Sum");
            ComputingMethod  c3 = new ComputingMethod(null,"03", "Minimum");
            ComputingMethod  c4 = new ComputingMethod(null,"04", "Maximum");
            ComputingMethod  c5 = new ComputingMethod(null,"05", "Equals");
            ComputingMethod  c6 = new ComputingMethod(null,"06", "Multiply");


            computingMethodRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6));
            log.info("-------------------------- Added Computing Methods data");
        }

        if (scoreTypeRepository.count() == 0) {
            ScoreType s1 = new ScoreType(null,"01","Normal");
            ScoreType s2 = new ScoreType(null,"02","Deflator");
            ScoreType s3 = new ScoreType(null,"03","Multiplier");

            scoreTypeRepository.saveAll(Arrays.asList( s1,s2,s3));
            log.info("-------------------------- Added Score Types data");


        }


        if(riskProjectTypeRepository.count() == 0) {
            RiskProjectType p1 = new RiskProjectType(null,"01", "Renewable Project");
            RiskProjectType p2 = new RiskProjectType(null,"02", "Infrastructure Transmission Project");
            RiskProjectType p3 = new RiskProjectType(null,"03", "Infrastructure Road Project – Hybrid Annuity");
            RiskProjectType p4 = new RiskProjectType(null,"04", "Infrastrucutre Road Project - Toll ");
            RiskProjectType p5 = new RiskProjectType(null,"05", "Holding Company");



            riskProjectTypeRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
            log.info("-------------------------- Added project Type   data");
        }


        if(projectRiskLevelRepository.count() == 0) {
            ProjectRiskLevel p1 = new ProjectRiskLevel(null,"01", "Build Phase ");
            ProjectRiskLevel  p2 = new ProjectRiskLevel(null,"02", "Operational Risk Phase");



            projectRiskLevelRepository.saveAll(Arrays.asList(p1,p2 ));
            log.info("-------------------------- Added project Risk Level (Phases) data");
        }


        if(riskRatingComputingMethodRepository.count() == 0) {

            //Modifiers to cap final ratings at sub-investment grade
            RatingModifierComputationMethod  m1 = new RatingModifierComputationMethod(null,"01", "On Select Any One - Notch Down to Sub Investment Grade");
            // Modifiers having impact on final ratings up to 2 notches
            RatingModifierComputationMethod m2 = new RatingModifierComputationMethod(null,"02", "Notch Down By Selection- OneorTwoBYOne, MoreThanThree By Two");

            riskRatingComputingMethodRepository.saveAll(Arrays.asList(m1,m2 ));
            log.info("-------------------------- Added Risk Rating Modifier Computing Method data");
        }

        if(purposeRepository.count() == 0) {
//
//            RiskPurpose p1 = new RiskPurpose(null, "01", "Project Assessment");
//            RiskPurpose p2 = new RiskPurpose(null, "02", "Risk Assessment");
//            RiskPurpose p3 = new RiskPurpose(null, "03", "Monitoring");


//            purposeRepository.saveAll(Arrays.asList(p1,p2,p3 ));
            log.info("-------------------------- Added Purposes data");
        }


        if(workflowStatusRepository.count() == 0) {

            WorkflowStatus w1 = new WorkflowStatus(null, "01", "Created");
            WorkflowStatus w2 = new WorkflowStatus(null, "02", "Sent for Approval");
            WorkflowStatus w3 = new WorkflowStatus(null, "03", "Approved");
            WorkflowStatus w4 = new WorkflowStatus(null, "04", "Rejected");


            //workflowStatusRepository.saveAll(Arrays.asList(w1,w2,w3,w4 ));
            workflowStatusRepository.saveAndFlush(w1);
            workflowStatusRepository.saveAndFlush(w2);
            workflowStatusRepository.saveAndFlush(w3);
            workflowStatusRepository.saveAndFlush(w4);

            log.info("-------------------------- Added Work Flow Statuses data");
        }

//        workflowAssignmentRepository.deleteAll();
//        workflowAssignmentRepository.flush();


        String[] profiles = environment.getActiveProfiles();
        String activeProfile = profiles[0];




        if(workflowAssignmentRepository.count() == 0) {


            RiskPurpose p1 = new RiskPurpose(null, "01", "Project");
            RiskPurpose p2 = new RiskPurpose(null, "02", "Risk");
            RiskPurpose p3 = new RiskPurpose(null, "03", "Monitoring");

            WorkflowAssignment w1 = new WorkflowAssignment();
            WorkflowAssignment w2 = new WorkflowAssignment();;
            WorkflowAssignment w3 = new WorkflowAssignment();;




            if (activeProfile.equals("oauth")) {
                w1 = new WorkflowAssignment(null, p1, "Sajeev Project", "sajeev.khan@gmail.com");
                w2 = new WorkflowAssignment(null, p2, "Sajeev Risk", "sajeev.khan@gmail.com");
                w3 = new WorkflowAssignment(null, p3, "Sajeev Monitoring", "sajeev.khan@gmail.com");

            } else {
                w1 = new WorkflowAssignment(null, p1, "Neeraj Yadav", "neerajyadav@ptcfinancial.com");
                w2 = new WorkflowAssignment(null, p2, "Neeraj Yadav", "neerajyadav@ptcfinancial.com");
                w3 = new WorkflowAssignment(null, p3, "Neeraj Yadav", "neerajyadav@ptcfinancial.com");

            }
//
//              w1.setPurpose(p1);
//              w1.setApproverEmailId("sajeev.khan@gmail.com");
//              w1.setApproverUserName("SajeevG");

            workflowAssignmentRepository.saveAll(Arrays.asList(w1  ));
            workflowAssignmentRepository.saveAll(Arrays.asList(w2  ));
            workflowAssignmentRepository.saveAll(Arrays.asList(w3  ));


            log.info("-------------------------- Added Workflow Assignments data");
        }





    }
}
