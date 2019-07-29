package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.client.LMSEnquiryClient;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskPurpose;
import com.pfs.riskmodel.domain.WorkflowAssignment;
import com.pfs.riskmodel.repository.RiskModelTemplateRepository;
import com.pfs.riskmodel.repository.WorkflowAssignmentRepository;
import com.pfs.riskmodel.repository.WorkflowStatusRepository;
import com.pfs.riskmodel.resource.LoanApplicationResource;
import com.pfs.riskmodel.resource.RiskEvaluationInSAP;
import com.pfs.riskmodel.resource.SearchResource;
import com.pfs.riskmodel.resource.User;
import com.pfs.riskmodel.service.IBatchReplicationService;
import com.pfs.riskmodel.service.ISAPRiskModelIntegrationService;
import com.pfs.riskmodel.service.IWorkflowService;
import com.pfs.riskmodel.util.ValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BatchReplicationService implements IBatchReplicationService {

    @Autowired
    RiskModelTemplateRepository riskModelTemplateRepository;

    @Autowired
    ISAPRiskModelIntegrationService isapRiskModelIntegrationService;

    private final LMSEnquiryClient lmsEnquiryClient;


    @Override
    public void replicationService(HttpServletRequest request, Integer loanNumberFrom, Integer loanNumberTo) {

        if (loanNumberFrom == null)  loanNumberFrom = 0;
        if (loanNumberFrom == null)  loanNumberTo = 99999;

        SearchResource resource = new SearchResource();
        if (loanNumberFrom != null)
            resource.setLoanNumberFrom(loanNumberFrom);
        if (loanNumberTo != null)
            resource.setLoanNumberTo(loanNumberTo);
        ResponseEntity<List<LoanApplicationResource>> resources =
                lmsEnquiryClient.searchEnquiries(resource, getAuthorizationBearer(request.getUserPrincipal()));

        List<RiskModelTemplate> riskModels = riskModelTemplateRepository.findAll();

        for (RiskModelTemplate riskModel : riskModels) {

            if (riskModel.getModelType() == 1) {
                if (riskModel.getLoanNumber() != null) {
                    System.out.println("Replicating Loan Contract: " + riskModel.getLoanNumber());
                    System.out.println("Replicating Risk Model Id: " + riskModel.getId().toString());

                    List<RiskModelTemplate> riskModelsForLoan
                            = riskModelTemplateRepository.findByLoanNumber(riskModel.getLoanNumber());


                    RiskEvaluationInSAP riskEvaluationInSAP =
                            isapRiskModelIntegrationService.mapRiskModelToSAPModel(riskModel);
                    isapRiskModelIntegrationService.replicateRiskModelInSAP(riskEvaluationInSAP);

                    if (riskEvaluationInSAP == null) {
                        System.out.println("Replication Failed for Loan Contract: "
                                + riskModel.getLoanNumber() + "Risk Model Id:" + riskModel.getId());
                    } else {
                        System.out.println("Replication Successful for Loan Contract: "
                                + riskModel.getLoanNumber() + "Risk Model Id:" + riskModel.getId());

                    }

                }
            }
        }

    }

    public String getAuthorizationBearer(Principal user) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) ((OAuth2Authentication) user).getDetails();
        return "Bearer " + details.getTokenValue();
    }
}



