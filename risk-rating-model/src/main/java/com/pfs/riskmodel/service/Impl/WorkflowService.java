package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.client.LMSEnquiryClient;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskPurpose;
import com.pfs.riskmodel.domain.WorkflowAssignment;
import com.pfs.riskmodel.repository.WorkflowAssignmentRepository;
import com.pfs.riskmodel.repository.WorkflowStatusRepository;
import com.pfs.riskmodel.resource.EmailId;
import com.pfs.riskmodel.resource.LoanApplication;
import com.pfs.riskmodel.resource.User;
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
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WorkflowService implements IWorkflowService {


    private final LMSEnquiryClient lmsEnquiryClient;

    @Autowired
    private RuntimeService runtimeService;


    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private WorkflowAssignmentRepository workflowAssignmentRepository;

    @Autowired
    private WorkflowStatusRepository workflowStatusRepository;

    @Autowired
    private WelcomeService welcomeService;

    @Autowired
    private LoanApplicationService loanApplicationService;

    private String firstLevelApproverEmailId;
    private String secondLevelApproverEmailId;
    private String thirdLevelApproverEmailId;

    private String firstLevelApproverName;
    private String secondLevelApproverName;
    private String thirdLevelApproverName;


    @Override
    public Map<String, Object> processWorkflowAction(RiskModelTemplate riskModelTemplate,
                                                     Integer action,
                                                     HttpServletRequest httpServletRequest) {


        //Set the Approver Emails
        WorkflowAssignment workflowAssignment = getWorkFlowProcessor(riskModelTemplate.getPurpose());
        firstLevelApproverEmailId = workflowAssignment.getFirstLevelApproverEmailId();
        secondLevelApproverEmailId = workflowAssignment.getSecondLevelApproverEmailId();
        thirdLevelApproverEmailId = workflowAssignment.getThirdLevelApproverEmailId();

        LoanApplication loanApplication = null; // = new LoanApplicationResource();

        if (riskModelTemplate.getLoanNumber() != null) {
            loanApplication =
                    loanApplicationService.getEnquiryByLoanNumber(riskModelTemplate.getLoanNumber());
        }else if (riskModelTemplate.getLoanEnquiryId() != null) {
            loanApplication =    loanApplicationService.getEnquiryById(riskModelTemplate.getLoanEnquiryId());
        }
        // Risk Officer is always the second level approver
        // If a risk officer is assigned to the loan, assign the risk officer as the second level approver
        // ..... If not, it is  second level approver from the Workflow Assignment as assinged already
        if (loanApplication != null) {
            if (loanApplication.getRiskDepartmentInitiator() != null && loanApplication.getRiskDepartmentInitiator() !="" ) {
                secondLevelApproverEmailId = loanApplication.getRiskDepartmentInitiator();
            }
        }

        // Get names of all approvers
         EmailId emailId = new EmailId();
         emailId.setEmailId(firstLevelApproverEmailId);
         //First Level Approver Name
         User firsLevelApprover = welcomeService.getUserByEmail(emailId);
         if (firsLevelApprover != null)
         firstLevelApproverName = firsLevelApprover.getFirstName() + firsLevelApprover.getLastName();

        emailId.setEmailId(secondLevelApproverEmailId);
        //Second Level Approver Name
        User secondLevelApprover = welcomeService.getUserByEmail(emailId);
        if (secondLevelApprover!= null) {
            riskModelTemplate.setSecondLevelApprover(secondLevelApprover.getFirstName() + secondLevelApprover.getLastName());
            secondLevelApproverName = secondLevelApprover.getFirstName() + secondLevelApprover.getLastName();
        }
        emailId.setEmailId(thirdLevelApproverEmailId);
        User thirdLevelApprover = welcomeService.getUserByEmail(emailId);
        thirdLevelApproverName = thirdLevelApprover.getFirstName() + thirdLevelApprover.getLastName();




        Map<String, Object> result = new HashMap<>();
        ValidationResult validationResult = new ValidationResult();

        if (action != 1) {
            validationResult = checkWorkflow(riskModelTemplate);
            if (riskModelTemplate.getProcessInstanceId() == null) {
                validationResult = getWorkflowValidation(true, "Workflow.NotStarted", riskModelTemplate.getId().toString(), "Workflow Poress Instance in NULL for Risk Model");
                result.put("ValidationResult", validationResult);
                return result;
            }
        }

        User user = welcomeService.getUser();

        switch (action) {
            case 1:
                validationResult = getWorkflowValidation(false, "Workflow.NotStarted", riskModelTemplate.getId().toString(), " ");
                result.put("ValidationResult", validationResult);

                if (riskModelTemplate.getWorkflowStatus() == null) {
                    riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode("01"));
                    riskModelTemplate.setWorkflowStatusCode("01");
                }
                /*
                    Set the Status based on the Modifier
                    If Modified by Creator or Reviewer
                */
                else {
                    if (riskModelTemplate.getWorkflowStatus().getCode().equals("01") ||
                            riskModelTemplate.getWorkflowStatus().getCode().equals("02")) {
                        String userFullName = user.getFirstName() + " " + user.getLastName();

                    }
                }

                result.put("RiskModel", riskModelTemplate);
                return result;
            case 2:
                if (riskModelTemplate.getWorkflowStatus().getCode().equals("01") || riskModelTemplate.getCurrentWorkFlowLevel() == null) {
                    riskModelTemplate.setCurrentWorkFlowLevel(1);
                    result = startApprovalProcess(riskModelTemplate, httpServletRequest);
                } else
                    result = approveTask(riskModelTemplate, httpServletRequest);
                break;
            case 3:
                result = approveTask(riskModelTemplate, httpServletRequest);
                break;
            case 4:
                result = rejectTask(riskModelTemplate, httpServletRequest);
                break;
        }

        //result.put("RiskModel", riskModelTemplate);
        return result;


    }

    private ValidationResult checkWorkflow(RiskModelTemplate riskModelTemplate) {

        ValidationResult validationResult = new ValidationResult();

        if (riskModelTemplate.getProcessInstanceId() == null) {
            validationResult.setAttributeName("Workflow.NotStarted");
            validationResult.setValue(riskModelTemplate.getId().toString());
            validationResult.setWorkflowError(true);
            validationResult.setFailed(true);
        } else {
            validationResult.setWorkflowError(false);
        }
        return validationResult;
    }


    // Trigger Workflow
    private Map<String, Object> startApprovalProcess(RiskModelTemplate riskModelTemplate, HttpServletRequest httpServletRequest) {




        Map<String, Object> output = new HashMap<>();
        ValidationResult validationResult = new ValidationResult();

        // Check if an active workflow exists for the risk model.
        // If yes, do not start a new workflow
        List<Task>  tasks = taskService.createTaskQuery()
                .processVariableValueEquals("riskModelId",riskModelTemplate.getId())
                .list();

        if (tasks.size() > 0) {
            output.put("RiskModel", riskModelTemplate);
            validationResult.setWorkflowError(false);
            output.put("ValidationResult", validationResult);
            return output;
        }

        validationResult.setWorkflowError(false);

        WorkflowAssignment workflowAssignment = getWorkFlowProcessor(riskModelTemplate.getPurpose());


        Map<String, Object> variables = new HashMap<>();
        variables = prepareVariables(riskModelTemplate, httpServletRequest);
        riskModelTemplate.setCurrentWorkFlowLevel(1);
        try {
            System.out.println("---------- STARTING WORKFLOW APPROVAL FOR PROJECT : " + riskModelTemplate.getProjectName());

            runtimeService = processEngine.getRuntimeService();
            ProcessInstance processInstance = runtimeService
                    .startProcessInstanceByKey("RiskModel_v3.2", variables);

            riskModelTemplate.setProcessInstanceId(processInstance.getProcessInstanceId());

        } catch (Exception ex) {
            validationResult =
                    getWorkflowValidation(true, "Workflow.Error", riskModelTemplate.getId().toString(), ex.getMessage());
            output.put("ValidationResult", validationResult);
            return output;

        }


        // Set Status as - "Sent for 1st Level Approval"
        riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode("02"));
        riskModelTemplate.setWorkflowStatusCode(workflowStatusRepository.findByCode("02").getCode());

        riskModelTemplate.setCurrentProcessorUserId(firstLevelApproverEmailId);
        output.put("RiskModel", riskModelTemplate);
        output.put("ValidationResult", validationResult);
        return output;

    }

    // Process Task - Approval
    private Map<String, Object> approveTask(RiskModelTemplate riskModelTemplate, HttpServletRequest httpServletRequest) {

        Map<String, Object> output = new HashMap<>();
        ValidationResult validationResult = new ValidationResult();

        WorkflowAssignment workflowAssignment = getWorkFlowProcessor(riskModelTemplate.getPurpose());

        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processInstanceId(riskModelTemplate.getProcessInstanceId()).singleResult();
        Map<String, Object> variables = new HashMap<>();


        variables = prepareVariables1(riskModelTemplate, httpServletRequest);


        switch (riskModelTemplate.getWorkflowStatus().getCode()) {
            case "02": //Sent for First Level Approval
                riskModelTemplate.setCurrentWorkFlowLevel(1);
                riskModelTemplate.setCurrentProcessorUserId(secondLevelApproverEmailId);
                variables.put("rejectedInFirstLevel", " ");
                variables.put("firstLevelApproval", true);
                break;
            case "03": //First Level Approval Completed
            case "05": //Sent for Second Level Approval
                riskModelTemplate.setCurrentWorkFlowLevel(2);
                riskModelTemplate.setCurrentProcessorUserId(secondLevelApproverEmailId);
                variables.put("rejectedInSecondLevel", " ");
                variables.put("secondLevelApproval", true);
                break;
            case "06": //Second Level Approval Completed
            case "07": //Sent for Third Level Approval
                variables.put("rejectedInThirdLevel", " ");
                riskModelTemplate.setCurrentWorkFlowLevel(3);
                riskModelTemplate.setCurrentProcessorUserId(riskModelTemplate.getCreatedByUserId());
                variables.put("thirdLevelApproval", true);
                break;
            case "04":
                if (riskModelTemplate.getCurrentWorkFlowLevel() == 1) {
                    variables.put("rejectedInFirstLevel", " ");
                    variables.put("firstLevelApproval", true);
                    riskModelTemplate.setCurrentProcessorUserId(secondLevelApproverEmailId);
                }
                if (riskModelTemplate.getCurrentWorkFlowLevel() == 2) {
                    variables.put("rejectedInSecondLevel", " ");
                    variables.put("secondLevelApproval", true);
                    riskModelTemplate.setCurrentProcessorUserId(thirdLevelApproverEmailId);

                }
                if (riskModelTemplate.getCurrentWorkFlowLevel() == 3) {
                    variables.put("rejectedInThirdLevel", " ");
                    variables.put("thirdLevelApproval", true);
                    riskModelTemplate.setCurrentProcessorUserId(thirdLevelApproverEmailId);

                }
                break;
        }


        // Set Reviewer Name
        if (httpServletRequest.getUserPrincipal() != null) {
            riskModelTemplate.setReviewedBy(httpServletRequest.getUserPrincipal().getName());
        }

        if (task == null) {
            validationResult =
                    getWorkflowValidation(true, "Workflow.Completed", riskModelTemplate.getId().toString(), "Workflow Task is NULL for RiskModel");
            output.put("ValidationResult", validationResult);
            return output;
        } else {
            validationResult =
                    getWorkflowValidation(false, "Workflow.Completed", riskModelTemplate.getId().toString(), " ");
            output.put("ValidationResult", validationResult);
        }

        System.out.println("--------------- Workflow Task Execution  Started @ : " + DateTime.now());

        try {
            taskService.complete(task.getId(), variables);
        } catch (Exception ex) {
            validationResult =
                    getWorkflowValidation(true, "Workflow.Error", riskModelTemplate.getId().toString(), ex.getMessage());
            output.put("ValidationResult", validationResult);
            return output;

        }
        System.out.println("--------------- Workflow Task Execution Finished @ : " + DateTime.now());


        // Set the New Status After Approval
        switch (riskModelTemplate.getWorkflowStatus().getCode()) {
            case "02": //Sent for 1st Level Approval
                riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode("03")); //First Level Approval Completed
                riskModelTemplate.setCurrentWorkFlowLevel(2);
                riskModelTemplate.setWorkflowStatusCode("03");
                break;
            case "03"://Sent for Second Level Approval
            case "05": //Sent for Second Level Approval
                riskModelTemplate.setCurrentWorkFlowLevel(3);
                riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode("06")); //Second Level Approval Completed
                riskModelTemplate.setWorkflowStatusCode("06");
                break;
            case "06"://Sent for Third Level Approval
            case "07": //Sent for Third Level Approval
                riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode("08")); //Third Level Approval Completed
                riskModelTemplate.setWorkflowStatusCode("08");
                break;
            case "04":
                if (riskModelTemplate.getCurrentWorkFlowLevel() == 1) {
                    riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode("03")); //First Level Approval Completed
                    riskModelTemplate.setCurrentWorkFlowLevel(2);
                    riskModelTemplate.setWorkflowStatusCode("03");
                    break;
                }
                if (riskModelTemplate.getCurrentWorkFlowLevel() == 2) {
                    riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode("06")); //Second Level Approval Completed
                    riskModelTemplate.setWorkflowStatusCode("06");
                    riskModelTemplate.setCurrentWorkFlowLevel(3);
                    break;
                }
                if (riskModelTemplate.getCurrentWorkFlowLevel() == 3) {
                    riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode("08")); //Third Level Approval Completed
                    riskModelTemplate.setWorkflowStatusCode("08");
                    break;
                }

        }


        output.put("RiskModel", riskModelTemplate);
        return output;
    }


    // Process Task - Reject
    private Map<String, Object> rejectTask(RiskModelTemplate riskModelTemplate, HttpServletRequest httpServletRequest) {

        Map<String, Object> output = new HashMap<>();
        ValidationResult validationResult = new ValidationResult();

        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processInstanceId(riskModelTemplate.getProcessInstanceId()).singleResult();
        Map<String, Object> variables = new HashMap<>();
        variables = prepareVariables(riskModelTemplate, httpServletRequest);


        WorkflowAssignment workflowAssignment = getWorkFlowProcessor(riskModelTemplate.getPurpose());


        switch (riskModelTemplate.getWorkflowStatus().getCode()) {
            case "02": // Sent for First Level Approval
                variables.put("firstLevelApproval", false);
                variables.put("rejectedInFirstLevel", "X");
                riskModelTemplate.setCurrentProcessorUserId(riskModelTemplate.getCreatedByUserId());
                //riskModelTemplate.setWorkflowStatusCode("01");
                break;
            case "03":
            case "05": // Sent for Second Level Approval
                variables.put("rejectedInSecondLevel", "X");
                variables.put("secondLevelApproval", false);
                riskModelTemplate.setCurrentProcessorUserId(firstLevelApproverEmailId);

                break;
            case "06":
            case "07": // Sent for Third Level Approval
                variables.put("rejectedInThirdLevel", "X");
                variables.put("thirdLevelApproval", false);
                riskModelTemplate.setCurrentProcessorUserId(secondLevelApproverEmailId);

        }

        // Set Reviewer Name
        if (httpServletRequest.getUserPrincipal() != null) {
            riskModelTemplate.setReviewedBy(httpServletRequest.getUserPrincipal().getName());
        }

        if (task == null) {
            validationResult =
                    getWorkflowValidation(true, "Workflow.Completed", riskModelTemplate.getId().toString(), "Workflow Task is Null");
            output.put("ValidationResult", validationResult);
            return output;
        } else {
            validationResult =
                    getWorkflowValidation(false, "Workflow.Completed", riskModelTemplate.getId().toString(), " ");
            output.put("ValidationResult", validationResult);
        }

        System.out.println("--------------- Workflow Task Execution Started @ " + DateTime.now());
        try {
            taskService.complete(task.getId(), variables);

        } catch (Exception ex) {
            validationResult =
                    getWorkflowValidation(true, "Workflow.Error", riskModelTemplate.getId().toString(), ex.getMessage());
            output.put("ValidationResult", validationResult);
            return output;
        }
        System.out.println("--------------- Workflow Task Execution Finished @ " + DateTime.now());

        riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode("04"));
        riskModelTemplate.setWorkflowStatusCode("04");

        // In case of reject, set the currentWorkflowLevel to one level below

        switch (riskModelTemplate.getCurrentWorkFlowLevel()) {
            case 1:
                riskModelTemplate.setCurrentWorkFlowLevel(null);
                break;
            case 2:
                riskModelTemplate.setCurrentWorkFlowLevel(1);
                break;
            case 3:
                riskModelTemplate.setCurrentWorkFlowLevel(1);
                break;
        }

        output.put("RiskModel", riskModelTemplate);
        return output;
    }


    private WorkflowAssignment getWorkFlowProcessor(RiskPurpose purpose) {
        return workflowAssignmentRepository.findByPurpose(purpose);
    }

    private ValidationResult getWorkflowValidation(Boolean error, String attribute, String id, String message) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setAttributeName(attribute);
        validationResult.setValue(id);
        validationResult.setFailed(false);
        validationResult.setWorkflowError(error);
        validationResult.setMessage(message);
        return validationResult;
    }


    private Map<String, Object> prepareVariables(RiskModelTemplate riskModelTemplate, HttpServletRequest httpServletRequest) {

        Map<String, Object> variables = new HashMap<>();
        variables.put("riskModelId", riskModelTemplate.getId());
        variables.put("projectType", riskModelTemplate.getRiskProjectType().getValue());
        variables.put("riskLevel", riskModelTemplate.getProjectRiskLevel().getValue());
        variables.put("projectName", riskModelTemplate.getProjectName());

        if (riskModelTemplate.getLoanNumber() != null)
            variables.put("loanNumber", riskModelTemplate.getLoanNumber());
        else
            variables.put("loanNumber", "Not assinged yet(In Enquiry Phase)");


        WorkflowAssignment workflowAssignment = getWorkFlowProcessor(riskModelTemplate.getPurpose());

        variables.put("fromEmailId", "pfsriskmodel@gmail.com");
        variables.put("initiatorName", riskModelTemplate.getCreatedBy());
        variables.put("initiatorEmailId", riskModelTemplate.getCreatedByUserId());
        variables.put("firstLevelApproval", false);
        variables.put("secondLevelApproval", false);
        variables.put("thirdLevelApproval", false);

        variables.put("rejectedInFirstLevel", " ");
        variables.put("rejectedInSecondLevel", " ");
        variables.put("rejectedInThirdLevel", " ");

        variables.put("firstLevelApproverName", firstLevelApproverName);
        variables.put("firstLevelApproverEmailId", firstLevelApproverEmailId);
        variables.put("secondLevelApproverName", secondLevelApproverName);
        variables.put("secondLevelApproverEmailId", secondLevelApproverEmailId);
        variables.put("thirdLevelApproverName", thirdLevelApproverName);
        variables.put("thirdLevelApproverEmailId", thirdLevelApproverEmailId);

        return variables;

    }


    private Map<String, Object> prepareVariables1(RiskModelTemplate riskModelTemplate, HttpServletRequest httpServletRequest) {

        Map<String, Object> variables = new HashMap<>();
        variables.put("riskModelId", riskModelTemplate.getId());
        variables.put("projectType", riskModelTemplate.getRiskProjectType().getValue());
        variables.put("riskLevel", riskModelTemplate.getProjectRiskLevel().getValue());
        variables.put("projectName", riskModelTemplate.getProjectName());

        if (riskModelTemplate.getLoanNumber() != null)
            variables.put("loanNumber", riskModelTemplate.getLoanNumber());
        else
            variables.put("loanNumber", "Not assinged yet(In Enquiry Phase)");

        WorkflowAssignment workflowAssignment = getWorkFlowProcessor(riskModelTemplate.getPurpose());

        variables.put("fromEmailId", "pfsriskmodel@gmail.com");
        variables.put("initiatorName", riskModelTemplate.getCreatedBy());
        variables.put("initiatorEmailId", riskModelTemplate.getCreatedByUserId());

        variables.put("firstLevelApproval", false);
        variables.put("secondLevelApproval", false);
        variables.put("thirdLevelApproval", false);

        variables.put("rejectedInFirstLevel", " ");
        variables.put("rejectedInSecondLevel", " ");
        variables.put("rejectedInThirdLevel", " ");

        variables.put("firstLevelApproverName", firstLevelApproverName);
        variables.put("firstLevelApproverEmailId", firstLevelApproverEmailId);
        variables.put("secondLevelApproverName", secondLevelApproverName);
        variables.put("secondLevelApproverEmailId", secondLevelApproverEmailId);
        variables.put("thirdLevelApproverName", thirdLevelApproverName);
        variables.put("thirdLevelApproverEmailId", thirdLevelApproverEmailId);

        return variables;

    }


}


/*
fromEmailId
initiatorName
initiatorEmailId
firstLevelApproval
secondLevelApproval
thirdLevelApproval
firstLevelApproverName
secondLevelApproverName
thirdLevelApproverName
firstLevelApproverEmailId
secondLevelApproverEmailId
thirdLevelApproverEmailId
projectType
projectName
riskLevel
riskModelId
loanNumber



 */
