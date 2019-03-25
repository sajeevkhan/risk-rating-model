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

            RiskPurpose p1 = riskPurposeRepository.findByCode("01");
            if (p1 == null)
                p1 = new RiskPurpose(null, "01", "Project");

            RiskPurpose p2 = riskPurposeRepository.findByCode("02");
            if (p2 == null) {
                p2 = new RiskPurpose(null, "02", "Risk");
            }
            RiskPurpose p3 = riskPurposeRepository.findByCode("03");
            if (p3 == null) {
                p3 = new RiskPurpose(null, "03", "Monitoring");
            }
            RiskPurpose p4 = riskPurposeRepository.findByCode("04");
            if (p4 == null) {
                p4 = new RiskPurpose(null, "04", "IT Department");
            }

//            riskPurposeRepository.save(p1);
//            riskPurposeRepository.save(p2);
//            riskPurposeRepository.save(p3);
//            riskPurposeRepository.save(p4);



        WorkflowAssignment wa1 = new WorkflowAssignment();
            WorkflowAssignment wa2 = new WorkflowAssignment();;
            WorkflowAssignment wa3 = new WorkflowAssignment();;


            wa1 = workflowAssignmentRepository.findByPurpose(p1);
            if (wa1 == null) {
                wa1 = new WorkflowAssignment(null, p1, "Sitesh Kumar Sinha", "sksinha@ptcfinancial.com",
                        "Neeraj Yadav", "neerajyadav@ptcfinancial.com",
                        "Devesh Singh", "devesh@ptcfinancial.com");

            }else {
                wa1.setFirstLevelApproverEmailId("sksinha@ptcfinancial.com");
                wa1.setFirstLevelApproverName("Sitesh Kumar Sinha");
                wa1.setSecondLevelApproverEmailId("neerajyadav@ptcfinancial.com");
                wa1.setSecondLevelApproverName("Neeraj Yadav");
                wa1.setThirdLevelApproverEmailId("devesh@ptcfinancial.com");
                wa1.setThirdLevelApproverName("Devesh Singh");
            }

            workflowAssignmentRepository.save(wa1);

            wa2 = workflowAssignmentRepository.findByPurpose(p2);
            if (wa2 == null) {
                wa2 = new WorkflowAssignment(null, p2, "Sanjay Rustagi", "sksinha@ptcfinancial.com",
                        "Neeraj Yadav", "neerajyadav@ptcfinancial.com",
                        "Devesh Singh", "devesh@ptcfinancial.com");

            }else {
                wa2.setFirstLevelApproverEmailId("sanjay.rustagi@ptcfinancial.com");
                wa2.setFirstLevelApproverName("Sanjay Rustagi");
                wa2.setSecondLevelApproverEmailId("neerajyadav@ptcfinancial.com");
                wa2.setSecondLevelApproverName("Neeraj Yadav");
                wa2.setThirdLevelApproverEmailId("devesh@ptcfinancial.com");
                wa2.setThirdLevelApproverName("Devesh Singh");
            }

            workflowAssignmentRepository.save(wa2);

//            wa3 = workflowAssignmentRepository.findByPurpose(p4);
//                if (wa3 == null) {
//                    wa3 = new WorkflowAssignment(null, p4, "Sanjay Rustagi", "sksinha@ptcfinancial.com",
//                            "Neeraj Yadav", "neerajyadav@ptcfinancial.com",
//                            "Devesh Singh", "devesh@ptcfinancial.com");
//
//                }else {
//            wa3.setFirstLevelApproverEmailId("sksinha@ptcfinancial.com");
//            wa3.setFirstLevelApproverName("Sanjay Rustagi");
//            wa3.setSecondLevelApproverEmailId("neerajyadav@ptcfinancial.com");
//            wa3.setSecondLevelApproverName("Neeraj Yadav");
//            wa3.setThirdLevelApproverEmailId("devesh@ptcfinancial.com");
////            wa3.setThirdLevelApproverName("Devesh Singh");
//        }

        //   workflowAssignmentRepository.save(wa3);


            log.info("-------------------------PFS SPECIFIC- Added Workflow Assignments data");
        }





   // }
}
