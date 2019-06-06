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
import java.util.HashMap;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Component
@RequiredArgsConstructor



public class WorkflowConfig implements CommandLineRunner{

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
    private final RiskPurposeRepository riskPurposeRepository;

    @Autowired
    private Environment environment;

    @Override
    public void run(String... strings) throws Exception {

        
//        if (workflowAssignmentRepository.count() != 0)
//            return;
//
//
//            RiskPurpose p1 = riskPurposeRepository.findByCode("01");
//            if (p1 == null)
//                p1 = new RiskPurpose(null, "01", "Project");
//
//            System.out.println("Risk Purpose p1 : " + p1.toString());
//
//
//
//            RiskPurpose p3 = riskPurposeRepository.findByCode("03");
//            if (p3 == null) {
//                p3 = new RiskPurpose(null, "03", "Monitoring");
//            }
//
//            System.out.println("Risk Purpose p3 : " + p3.toString());
//
//
//
//            WorkflowAssignment wa1 = new WorkflowAssignment();
//            WorkflowAssignment wa2 = new WorkflowAssignment();;
//
//
//            wa1 = workflowAssignmentRepository.findByPurpose(p1);
//            if (wa1 != null) {
//
//                System.out.println("Workflow Assignment wa1: " + wa1.toString());
//            }
//
//            if (wa1 == null) {
//
//                wa1 = new WorkflowAssignment(null, p1, "Sitesh Kumar Sinha", "sksinha@ptcfinancial.com",
//                        "Neeraj Yadav", "neerajyadav@ptcfinancial.com",
//                        "Devesh Singh", "devesh@ptcfinancial.com");
//
//
//                System.out.println("Workflow Assignment wa1 BEFORE SAVE : " + wa1.toString());
//                workflowAssignmentRepository.save(wa1);
//
//            }else {
//                wa1.setPurpose(p1);
//                wa1.setFirstLevelApproverEmailId("sksinha@ptcfinancial.com");
//                wa1.setFirstLevelApproverName("Sitesh Kumar Sinha");
//                wa1.setSecondLevelApproverEmailId("neerajyadav@ptcfinancial.com");
//                wa1.setSecondLevelApproverName("Neeraj Yadav");
//                wa1.setThirdLevelApproverEmailId("devesh@ptcfinancial.com");
//                wa1.setThirdLevelApproverName("Devesh Singh");
//
//                System.out.println("Workflow Assignment wa1 BEFORE SAVE : " + wa1.toString());
//                workflowAssignmentRepository.save(wa1);
//            }
//
//
//
//
//        wa2 = workflowAssignmentRepository.findByPurpose(p3);
//
//
//        if (wa2 != null)
//            System.out.println("Workflow Assignment wa2: " + wa2.toString());
//
//            if (wa2 == null) {
//
//                wa2 = new WorkflowAssignment(null, p3, "Sanjay Rustagi", "sksinha@ptcfinancial.com",
//                        "Neeraj Yadav", "neerajyadav@ptcfinancial.com",
//                        "Devesh Singh", "devesh@ptcfinancial.com");
//
//
//                System.out.println("Workflow Assignment wa2BEFORE SAVE : " + wa2.toString());
//                workflowAssignmentRepository.save(wa2);
//
//            }else {
//                wa2.setPurpose(p3);
//                wa2.setFirstLevelApproverEmailId("sanjay.rustagi@ptcfinancial.com");
//                wa2.setFirstLevelApproverName("Sanjay Rustagi");
//                wa2.setSecondLevelApproverEmailId("neerajyadav@ptcfinancial.com");
//                wa2.setSecondLevelApproverName("Neeraj Yadav");
//                wa2.setThirdLevelApproverEmailId("devesh@ptcfinancial.com");
//                wa2.setThirdLevelApproverName("Devesh Singh");
//
//                System.out.println("Workflow Assignment wa2BEFORE SAVE : " + wa2.toString());
//                workflowAssignmentRepository.save(wa2);
//            }
//
//
//
//
//
//            log.info("-------------------------PFS SPECIFIC- Added Workflow Assignments data");
//        }


    }


}
