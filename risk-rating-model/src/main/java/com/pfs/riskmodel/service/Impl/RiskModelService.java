package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.domain.ChangeDocument;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.WorkflowAssignment;
import com.pfs.riskmodel.repository.*;
import com.pfs.riskmodel.resource.*;
import com.pfs.riskmodel.service.*;
import com.pfs.riskmodel.service.modelvaluator.RiskModelEvaluator;
import com.pfs.riskmodel.service.validator.RiskModelTemplateValidator;
import com.pfs.riskmodel.util.ValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@EnableFeignClients
public class RiskModelService implements IRiskModelService {

    @Autowired
    RiskModelTemplateRepository riskModelTemplateRepository;

    @Autowired
    RiskModelTemplateValidator riskModelTemplateValidator;

    @Autowired
    IRiskTypeService iRiskTypeService;

    @Autowired
    RiskTypeRepository riskTypeRepository;

    @Autowired
    RiskProjectTypeRepository riskProjectTypeRepository;

    @Autowired
    ProjectRiskLevelRepository projectRiskLevelRepository;

    @Autowired
    IWorkflowService iWorkflowService;

    @Autowired
    WorkflowStatusRepository workflowStatusRepository;

    @Autowired
    WorkflowAssignmentRepository workflowAssignmentRepository;

    @Autowired
    IChangeDocumentService changeDocumentService;

    @Autowired
    IWelcomeService welcomeService;

    @Autowired
    ILoanApplicationService loanApplicationService;

    @Autowired
    ISAPRiskModelIntegrationService isapRiskModelIntegrationService;


    @Override
    public Map<String, Object> createRiskModel(RiskModelTemplate riskModelTemplate,
                                               Integer action,
                                               HttpServletRequest httpServletRequest) {

        Map<String, Object> result = new HashMap<>();

        RiskModelTemplate existingRiskModel = new RiskModelTemplate();

       // if (risk)

        User user = welcomeService.getUser();
        String userFullName = user.getFirstName() + " " + user.getLastName();

        if (riskModelTemplate.getCreatedBy() == null)
            riskModelTemplate.setCreatedBy(user.getFirstName() + user.getLastName());

        if (riskModelTemplate.getCreatedByUserId() == null)
            riskModelTemplate.setCreatedByUserId(user.getEmail());

        LoanApplication loanApplication = null; // = new LoanApplicationResource();

        if (riskModelTemplate.getLoanNumber() != null) {
            loanApplication =
                    loanApplicationService.getEnquiryByLoanNumber(riskModelTemplate.getLoanNumber());
        }else if (riskModelTemplate.getLoanEnquiryId() != null) {
            loanApplication =    loanApplicationService.getEnquiryById(riskModelTemplate.getLoanEnquiryId());
        }

        String riskOfficer = null;
        User riskDepartmentOfficer = new User();
        if (loanApplication !=null){
            riskOfficer = loanApplication.getRiskDepartmentInitiator();
            if (riskOfficer != null && riskOfficer != "") {
                riskDepartmentOfficer = welcomeService.getUserByEmail(new EmailId(riskOfficer));

            }
        }


        //TODO - Set Loan Capitals on the Risk Model
        if (loanApplication != null) {
            riskModelTemplate.setLoanContractAmount(loanApplication.getLoanContractAmount());
            riskModelTemplate.setLoanCurrentContractAmount(loanApplication.getLoanCurrentContractAmount());
            riskModelTemplate.setLoanRevisedSanctionAmount(loanApplication.getLoanRevisedSanctionAmount());
            riskModelTemplate.setLoanDisbursedAmount(loanApplication.getLoanDisbursedAmount());
        }


        //Determine Approvers
        WorkflowAssignment workflowAssignment = workflowAssignmentRepository.findByPurpose(riskModelTemplate.getPurpose());
        if (workflowAssignment != null){

            EmailId firstLevelApproverEmail = new EmailId(workflowAssignment.getFirstLevelApproverEmailId());

            // Risk Officer is always the second level approver
            // If a risk officer is assigned to the loan, assign the risk officer as the second level approver
            // ..... If not, get the second level approver from the Workflow Assignment
            EmailId secondLevelApproverEmail = new EmailId();
            if (riskDepartmentOfficer != null) {
                if (riskDepartmentOfficer.getEmail() != null) {
                    secondLevelApproverEmail = new EmailId(riskDepartmentOfficer.getEmail());
                }
            }else {
                secondLevelApproverEmail = new EmailId(workflowAssignment.getSecondLevelApproverEmailId());
            }
            EmailId thirdLevelApproverEmail = new EmailId(workflowAssignment.getThirdLevelApproverEmailId());

            User firstLevelApprover = welcomeService.getUserByEmail(firstLevelApproverEmail);
            User secondLevelApprover = welcomeService.getUserByEmail(secondLevelApproverEmail);
            User thirdLevelApprover = welcomeService.getUserByEmail(thirdLevelApproverEmail);


            if (firstLevelApprover != null)
                riskModelTemplate.setFirstLevelApprover(firstLevelApprover.getFirstName() + " " + firstLevelApprover.getLastName());
            if (secondLevelApprover != null)
                riskModelTemplate.setSecondLevelApprover(secondLevelApprover.getFirstName() + " " + secondLevelApprover.getLastName());
            if (thirdLevelApprover != null)
                riskModelTemplate.setThirdLevelApprover(thirdLevelApprover.getFirstName() + " " + thirdLevelApprover.getLastName());
        }

        if (riskModelTemplate.getCurrentProcessorUserId() == null) {
            riskModelTemplate.setCurrentProcessorUserId(user.getEmail());
        }

        //Set Created By
        if (riskModelTemplate.getCreatedBy() == null) {
            if (httpServletRequest.getUserPrincipal() != null) {
                //   User user = welcomeService.getUser();
                if (user != null)
                    riskModelTemplate.setCreatedBy(user.getFirstName() + " " + user.getLastName());
            }
        }


        if (riskModelTemplate.getId() != null) {
            existingRiskModel = riskModelTemplateRepository.getOne(riskModelTemplate.getId());
            riskModelTemplate.setCurrentWorkFlowLevel(existingRiskModel.getCurrentWorkFlowLevel());
           // existingRiskModel = riskModelTemplate.copy(existingRiskModel);
        }


        ValidationResult validationResult =  riskModelTemplateValidator.validate(riskModelTemplate);
        if (validationResult.isFailed()) {
            result.put("ValidationResult", validationResult);
            return result;
        }

        String userName = new String();
        if (httpServletRequest.getUserPrincipal() != null) {
            userName = httpServletRequest.getUserPrincipal().getName();
        } else {
            userName = "Test User";
        }




        // Evaluate Risk Model
        RiskModelEvaluator riskModelEvaluator = new RiskModelEvaluator();
        riskModelEvaluator.evaluateRiskModel(riskModelTemplate);

        if (riskModelTemplate.getWorkflowStatus() == null) {
            riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode("01"));
            riskModelTemplate.setWorkflowStatusCode("01");
        }



        // Create Change Document
        ChangeDocument changeDocument = new ChangeDocument();
        //if (riskModelTemplate.getId() != null) {
            changeDocument= changeDocumentService.createChangeDocument(existingRiskModel,
                    riskModelTemplate ,
                    action,userName);


        riskModelTemplate =  riskModelTemplateRepository.save(riskModelTemplate);

        if (changeDocument.getRiskModelTemplateId() == null)
            changeDocument.setRiskModelTemplateId(riskModelTemplate.getId());
        changeDocumentService.saveChangeDocument(changeDocument);


        //Process Action
        Map <String, Object> workflowResult = new HashMap<>();
        workflowResult = iWorkflowService.processWorkflowAction(riskModelTemplate,action,httpServletRequest);

        ValidationResult workflowValidationResult  = (ValidationResult) workflowResult.get("ValidationResult");
        if ( workflowValidationResult.isWorkflowError() == true) {
            result.put("ValidationResult",workflowValidationResult);

        }
        else {
            riskModelTemplate = (RiskModelTemplate) workflowResult.get("RiskModel");
            riskModelTemplate = riskModelTemplateRepository.save(riskModelTemplate);
            result.put("RiskModel", riskModelTemplate);
        }

        Long createdRiskModelTemplateId = riskModelTemplate.getId();


        List<RiskModelTemplate> riskModelTemplatesActive =
                riskModelTemplateRepository.findByRiskProjectTypeAndProjectRiskLevelAndModelTypeAndStatus(
                        riskModelTemplate.getRiskProjectType(),
                        riskModelTemplate.getProjectRiskLevel(),
                        1, //Find Valuations Models only
                        "X");


        for (RiskModelTemplate riskModelTemplateActive : riskModelTemplatesActive ) {
            if ( riskModelTemplateActive.getId() != createdRiskModelTemplateId ) {
                riskModelTemplateActive.setStatus(" ");
                riskModelTemplateRepository.save(riskModelTemplateActive);
            }
        }

        changeDocumentService.updateChangeDocument(changeDocument, riskModelTemplate);

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("-----------------> Current Processor:" + riskModelTemplate.getCurrentProcessorUserId());
        System.out.println("-------------------------------------------------------------------------------------");

        // Replicate Risk Model in SAP
        RiskEvaluationInSAP riskEvaluationInSAP = isapRiskModelIntegrationService.mapRiskModelToSAPModel(riskModelTemplate);
        CompletableFuture.runAsync(() -> {
            // method call or code to be asynch.
            isapRiskModelIntegrationService.replicateRiskModelInSAP(riskEvaluationInSAP);


        });

        return result;

    }



    @Override
    public void replicateLoanToBackend(RiskModelTemplate riskModelTemplate) {

        // Replicate Risk Model in SAP
        RiskEvaluationInSAP riskEvaluationInSAP = isapRiskModelIntegrationService.mapRiskModelToSAPModel(riskModelTemplate);
        CompletableFuture.runAsync(() -> {
            // method call or code to be asynch.
            isapRiskModelIntegrationService.replicateRiskModelInSAP(riskEvaluationInSAP);


        });
    }

}
