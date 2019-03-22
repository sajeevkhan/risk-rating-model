package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.dto.WorkflowTaskDTO;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

//
//import org.activiti.engine.RuntimeService;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class WorkflowController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessEngine processEngine;


    @GetMapping("/tasklist")
    public ResponseEntity<List<WorkflowTaskDTO>> getTasks(HttpServletRequest httpServletRequest) {

        List<WorkflowTaskDTO> workflowTaskDTOList = new ArrayList<>();

        TaskService taskService = processEngine.getTaskService();
        String userName = httpServletRequest.getUserPrincipal().getName();

        System.out.println("USER NAME: " + httpServletRequest.getUserPrincipal().getName());

        List<Task>  tasks = taskService.createTaskQuery()
                                        .taskAssignee(userName)
                                        .includeProcessVariables()
                                        .orderByTaskCreateTime()
                                        .desc()
                                        .list();

        for (Task task: tasks) {

           // System.out.println(task.getAssignee() + task.getId() + task.getProcessDefinitionId() );
            Map<String, Object> variables = task.getProcessVariables();

            WorkflowTaskDTO workflowTaskDTO = prepareWorkflowTask(task,variables);
            workflowTaskDTOList.add(workflowTaskDTO);

        }


        return ResponseEntity.ok(workflowTaskDTOList);

    }


    private WorkflowTaskDTO prepareWorkflowTask ( Task task, Map<String, Object> variables) {

        WorkflowTaskDTO workflowTaskDTO = new WorkflowTaskDTO();
        workflowTaskDTO.setId(task.getId().toString());

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        String dateAsString = sdf.format(task.getCreateTime());

        workflowTaskDTO.setDateAsString(dateAsString);
        workflowTaskDTO.setProjectName(variables.get("projectName").toString());
        workflowTaskDTO.setProjectType(variables.get("projectType").toString());
        workflowTaskDTO.setRiskLevel(variables.get("riskLevel").toString());
        workflowTaskDTO.setRiskModelId(variables.get("riskModelId").toString());
        workflowTaskDTO.setRequestedBy(variables.get("initiatorName").toString());

        return workflowTaskDTO;

    }


    }
