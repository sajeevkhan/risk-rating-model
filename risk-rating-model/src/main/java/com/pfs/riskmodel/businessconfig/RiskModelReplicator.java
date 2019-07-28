package com.pfs.riskmodel.businessconfig;

import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.repository.RiskModelTemplateRepository;
import com.pfs.riskmodel.resource.RiskEvaluationInSAP;
import com.pfs.riskmodel.service.ISAPRiskModelIntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RiskModelReplicator implements CommandLineRunner {

    @Autowired
    RiskModelTemplateRepository riskModelTemplateRepository;

    @Autowired
    ISAPRiskModelIntegrationService isapRiskModelIntegrationService;

    @Override
    public void run(String... strings) throws Exception {

//        List<RiskModelTemplate> riskModels = riskModelTemplateRepository.findAll();
//
//        for (RiskModelTemplate riskModel : riskModels) {
//
//            if (riskModel.getModelType() == 1) {
//                if (riskModel.getLoanNumber() != null) {
//                    System.out.println("Replicating Loan Contract: " + riskModel.getLoanNumber());
//                    System.out.println("Replicating Risk Model Id: " + riskModel.getId().toString());
//                    RiskEvaluationInSAP riskEvaluationInSAP =
//                            isapRiskModelIntegrationService.mapRiskModelToSAPModel(riskModel);
//                    isapRiskModelIntegrationService.replicateRiskModelInSAP(riskEvaluationInSAP);
//
//                    if (riskEvaluationInSAP == null) {
//                        System.out.println("Replication Failed for Loan Contract: "
//                                + riskModel.getLoanNumber() + "Risk Model Id:" + riskModel.getId());
//                    } else {
//                        System.out.println("Replication Successful for Loan Contract: "
//                                + riskModel.getLoanNumber() + "Risk Model Id:" + riskModel.getId());
//
//                    }
//
//                }
//            }
//
//
//        }
    }

    

}
