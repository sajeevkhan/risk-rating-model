package com.pfs.riskmodel.batch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.repository.RiskModelTemplateRepository;
import com.pfs.riskmodel.resource.RiskEvaluationInSAP;
import com.pfs.riskmodel.service.ISAPRiskModelIntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by sajeev on 28-Jul-19.
 */
@Service
public class ScheduledTasks {

@Autowired
private RiskModelTemplateRepository riskModelTemplateRepository;

@Autowired
private ISAPRiskModelIntegrationService isapRiskModelIntegrationService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() {
//        log.info("The time is now {}", dateFormat.format(new Date()));
//        System.out.println("The time is now :" + dateFormat.format(new Date()));
//    }

//    @Scheduled(fixedRate = 100000)
//    public void replicateRiskModelsInBackend(){
//
//        List<RiskModelTemplate> riskModels = riskModelTemplateRepository.findAll();
//
//        for (RiskModelTemplate riskModel : riskModels) {
//
//            if (riskModel.getModelType() == 1) {
//                if (riskModel.getLoanNumber() != null) {
//                    System.out.println("Replicating Loan Contract: " + riskModel.getLoanNumber());
//                    System.out.println("Replicating Risk Model Id: " + riskModel.getId().toString());
//
//                    riskModel = riskModelTemplateRepository.getOne(riskModel.getId());
//
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
//    }
}
