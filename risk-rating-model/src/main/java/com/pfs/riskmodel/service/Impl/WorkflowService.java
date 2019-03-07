package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.client.LMSEnquiryClient;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskPurpose;
import com.pfs.riskmodel.domain.WorkflowAssignment;
import com.pfs.riskmodel.repository.WorkflowAssignmentRepository;
import com.pfs.riskmodel.repository.WorkflowStatusRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Service
@RequiredArgsConstructor
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



    @Override
    public Map<String, Object> processWorkflowAction(RiskModelTemplate riskModelTemplate,
                                                     Integer action,
                                                     HttpServletRequest httpServletRequest) {

        Map<String, Object> result = new HashMap<>();
        ValidationResult validationResult = new ValidationResult();

        if (action != 1) {
            validationResult = checkWorkflow(riskModelTemplate);
            if (riskModelTemplate.getProcessInstanceId() == null) {
                validationResult = getWorkflowValidation(true,"Workflow.NotStarted",riskModelTemplate.getId().toString());
                result.put("ValidationResult", validationResult);
                return result;
            }
        }

        User user = welcomeService.getUser();

        switch (action) {
            case 1:
                if (httpServletRequest.getUserPrincipal() != null) {
                    //User user = welcomeService.getUser();
                    if (user != null)
                    riskModelTemplate.setCreatedBy(user.getFirstName() + " " + user.getLastName());
                }
                validationResult = getWorkflowValidation(false,"Workflow.NotStarted",riskModelTemplate.getId().toString());
                result.put("ValidationResult", validationResult);

                riskModelTemplate.setWorkflowStatus( workflowStatusRepository.findByCode("01") );
                if (riskModelTemplate.getWorkflowStatus() == null) {
                    riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode("01"));
                }
                /*
                    Set the Status based on the Modifier
                    If Modified by Creator or Reviewer
                */
                else {
                if (riskModelTemplate.getWorkflowStatus().getCode().equals("01") ||
                        riskModelTemplate.getWorkflowStatus().getCode().equals("02") ) {
                    String userFullName = user.getFirstName() + " " + user.getLastName();
//                    if (userFullName.equals(riskModelTemplate.getCreatedBy())) {
//                        riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode("05"));
//                    }
//                    else
//                        {
//                            if (userFullName.equals(riskModelTemplate.getReviewedBy())) {
//                                riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode("06"));
//                            }
//                        }
                }
            }

                result.put("RiskModel", riskModelTemplate);
                return result;
            case 2:
                result = startApprovalProcess(riskModelTemplate,httpServletRequest);
                break;
            case 3:
                result = approveTask(riskModelTemplate,httpServletRequest);
                break;
            case 4:
                result = rejectTask(riskModelTemplate,httpServletRequest);
                break;
        }

        //result.put("RiskModel", riskModelTemplate);
        return result;


    }

    private ValidationResult checkWorkflow(RiskModelTemplate riskModelTemplate)  {

        ValidationResult validationResult = new ValidationResult();

        if (riskModelTemplate.getProcessInstanceId() == null) {
            validationResult.setAttributeName("Workflow.NotStarted");
            validationResult.setValue(riskModelTemplate.getId().toString());
            validationResult.setWorkflowError(true);
            validationResult.setFailed(true);
        }
        else {
            validationResult.setWorkflowError(false);
        }
        return validationResult;
    }


    private Map<String, Object> prepareVariables(RiskModelTemplate riskModelTemplate, HttpServletRequest httpServletRequest) {

        Map<String, Object> variables = new HashMap<>();
        variables.put("riskModelId", riskModelTemplate.getId());
        variables.put("projectType", riskModelTemplate.getRiskProjectType().getValue());
        variables.put("riskLevel", riskModelTemplate.getProjectRiskLevel().getValue());
        variables.put("projectName", riskModelTemplate.getProjectName());

        if (httpServletRequest.getUserPrincipal() != null)
            variables.put("senderUser", httpServletRequest.getUserPrincipal().getName());
        else
            variables.put("senderUser", "Tester User");

        // Get User Name from WelcomeService
        if (welcomeService.getUser() != null)
            variables.put("senderUserEmail", welcomeService.getUser().getEmail());


        WorkflowAssignment workflowAssignment = getWorkFlowProcessor(riskModelTemplate.getPurpose());

        variables.put("approverUser", workflowAssignment.getApproverUserName());
        variables.put("approverEmail", workflowAssignment.getApproverEmailId());

        return variables;

    }


    private Map<String, Object> prepareVariables1(RiskModelTemplate riskModelTemplate, HttpServletRequest httpServletRequest) {

        Map<String, Object> variables = new HashMap<>();
        variables.put("riskModelId1", riskModelTemplate.getId());
        variables.put("projectType1", riskModelTemplate.getRiskProjectType().getValue());
        variables.put("riskLevel1", riskModelTemplate.getProjectRiskLevel().getValue());
        variables.put("projectName1", riskModelTemplate.getProjectName());
        if (httpServletRequest.getUserPrincipal() != null)
            variables.put("senderUser1", httpServletRequest.getUserPrincipal().getName());
        else
            variables.put("senderUser1", "Tester User");
        variables.put("senderUserEmail1", welcomeService.getUser().getEmail());


        WorkflowAssignment workflowAssignment = getWorkFlowProcessor(riskModelTemplate.getPurpose());

        variables.put("approverUser1", workflowAssignment.getApproverUserName());
        variables.put("approverEmail1", workflowAssignment.getApproverEmailId());

        return variables;

    }


    // Trigger Workflow
    private Map<String, Object> startApprovalProcess(RiskModelTemplate riskModelTemplate, HttpServletRequest httpServletRequest) {


        Map<String,Object> output =  new HashMap<>();
        ValidationResult validationResult = new ValidationResult();

        validationResult.setWorkflowError(false);

        Map<String, Object> variables = new HashMap<>();

        variables = prepareVariables(riskModelTemplate,httpServletRequest);

//        if (httpServletRequest.getUserPrincipal() != null) {
//            riskModelTemplate.setReviewedBy(httpServletRequest.getUserPrincipal().getName());
//        }


        runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("RiskModelApproval_v2", variables);

        riskModelTemplate.setProcessInstanceId(processInstance.getProcessInstanceId());
        riskModelTemplate.setWorkflowStatus( workflowStatusRepository.findByCode("02") );

        output.put("RiskModel", riskModelTemplate);
        output.put("ValidationResult", validationResult);
        return output;

    }

    // Process Task - Approval
    private  Map<String, Object>  approveTask(RiskModelTemplate riskModelTemplate, HttpServletRequest httpServletRequest) {

        Map<String,Object> output =  new HashMap<>();
        ValidationResult validationResult = new ValidationResult();

        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processInstanceId(riskModelTemplate.getProcessInstanceId()).singleResult();
        Map<String, Object> variables = new HashMap<>();

        variables = prepareVariables1(riskModelTemplate,httpServletRequest);
        variables.put("result", true);

        // Set Reviewer Name
        if (httpServletRequest.getUserPrincipal() != null) {
            riskModelTemplate.setReviewedBy(httpServletRequest.getUserPrincipal().getName());
        }

        if (task == null) {
            validationResult =
                    getWorkflowValidation(true,"Workflow.Completed",riskModelTemplate.getId().toString());
            output.put("ValidationResult", validationResult);
            return output;
        }else {
            validationResult =
                    getWorkflowValidation(false,"Workflow.Completed",riskModelTemplate.getId().toString());
            output.put("ValidationResult", validationResult);
        }

        taskService.complete(task.getId(),variables);
        riskModelTemplate.setWorkflowStatus( workflowStatusRepository.findByCode("03") );

        output.put("RiskModel", riskModelTemplate);
        return output;
    }


    // Process Task - Reject
    private  Map<String,Object> rejectTask(RiskModelTemplate riskModelTemplate, HttpServletRequest httpServletRequest) {

        Map<String,Object> output =  new HashMap<>();
        ValidationResult validationResult = new ValidationResult();

        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().processInstanceId(riskModelTemplate.getProcessInstanceId()).singleResult();
        Map<String, Object> variables = new HashMap<>();
        variables = prepareVariables(riskModelTemplate,httpServletRequest);
        variables.put("result", false);

        // Set Reviewer Name
        if (httpServletRequest.getUserPrincipal() != null) {
            riskModelTemplate.setReviewedBy(httpServletRequest.getUserPrincipal().getName());
        }

        if (task == null) {
            validationResult =
                    getWorkflowValidation(true,"Workflow.Completed",riskModelTemplate.getId().toString());
            output.put("ValidationResult", validationResult);
            return output;
        }else {
            validationResult =
                    getWorkflowValidation(false,"Workflow.Completed",riskModelTemplate.getId().toString());
            output.put("ValidationResult", validationResult);
        }

        taskService.complete(task.getId(),variables);
        riskModelTemplate.setWorkflowStatus( workflowStatusRepository.findByCode("04") );

        output.put("RiskModel", riskModelTemplate);
        return output;
    }



    private WorkflowAssignment getWorkFlowProcessor (RiskPurpose purpose) {
        return workflowAssignmentRepository.findByPurpose(purpose);
    }

    private ValidationResult getWorkflowValidation(Boolean error , String attribute, String id) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setAttributeName(attribute);
        validationResult.setValue(id);
        validationResult.setFailed(false);
        validationResult.setWorkflowError(error);
        return validationResult;
    }


    private String processInfo() {
//
//  List<Task> tasks = taskService.createTaskQuery().orderByTaskCreateTime().asc().list();
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        stringBuilder.append("Number of process definitions : "
//                + repositoryService.createProcessDefinitionQuery().count() + "--> Tasks >> ");
//
//        for (org.activiti.engine.task.Task task : tasks) {
//            stringBuilder
//                    .append(task + " | Assignee: " + task.getAssignee() + " | Description: " + task.getDescription());
//        }
//
//        return stringBuilder.toString();

        return  null;
    }

}
