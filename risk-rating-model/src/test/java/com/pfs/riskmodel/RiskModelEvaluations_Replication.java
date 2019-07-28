package com.pfs.riskmodel;

import com.pfs.riskmodel.ModelTemplates.HoldingCompany.HoldingCompanyData;
import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.BuildPhase.InfraRoadHAM_BuildPhaseData;
import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.OperationalPhase.InfraRoadHAM_OperationalPhaseData;
import com.pfs.riskmodel.ModelTemplates.InfraRoadToll.BuildPhase.InfraRoadToll_BuildPhaseData;
import com.pfs.riskmodel.ModelTemplates.InfraRoadToll.OperationalPhase.InfraRoadToll_OperationalPhaseData;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.BuildPhase.InfraTransmissionBuildPhaseData;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.OperationalPhase.InfraTransmissionOperationalPhaseData;
import com.pfs.riskmodel.ModelTemplates.Renewable.BuildPhase.RenewableProjectBuildPhaseData;
import com.pfs.riskmodel.ModelTemplates.Renewable.OperationalPhase.RenewablesOperationalPhaseData;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import com.pfs.riskmodel.repository.RiskModelTemplateRepository;
import com.pfs.riskmodel.resource.RiskEvaluationInSAP;
import com.pfs.riskmodel.service.ISAPRiskModelIntegrationService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sajeev on 18-Dec-18.
 */
public  class RiskModelEvaluations_Replication extends AbstractTest {

    @Autowired
    RiskModelTemplateRepository riskModelTemplateRepository;

    @Autowired
    ISAPRiskModelIntegrationService isapRiskModelIntegrationService;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }


    @Test
    public void test () throws Exception {
    }


    @Test
    public  void replicate() throws Exception {

        this.replicateRiskModels();

    }




    private void replicateRiskModels() throws  Exception {


//        List<RiskModelTemplate> riskModels = riskModelTemplateRepository.findAll();
//
//        for (RiskModelTemplate riskModel: riskModels) {
//
//            if (riskModel.getModelType() == 1) {
//                if (riskModel.getLoanNumber() != null) {
//                    System.out.println("Replicating Loan Contract: " + riskModel.getLoanNumber());
//                    System.out.println("Replicating Risk Model Id: " + riskModel.getId().toString());
//                    RiskEvaluationInSAP riskEvaluationInSAP =
//                            isapRiskModelIntegrationService.replicateRiskModelInSAP(riskModel.getId());
//
//                    if (riskEvaluationInSAP == null) {
//                        System.out.println("Replication Failed for Loan Contract: "
//                                                + riskModel.getLoanNumber() + "Risk Model Id:" + riskModel.getId() );
//                    } else {
//                        System.out.println("Replication Successful for Loan Contract: "
//                                + riskModel.getLoanNumber() + "Risk Model Id:" + riskModel.getId() );
//
//                    }
//
//                }
//            }


    }




}
