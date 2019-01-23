package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.repository.RiskModelTemplateRepository;
import com.pfs.riskmodel.util.Check;
import lombok.RequiredArgsConstructor;
//
//import org.activiti.engine.RuntimeService;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class TestWorkflowController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessEngine processEngine;

    @GetMapping("/start-process")
    public ResponseEntity startProcess() {

        runtimeService.startProcessInstanceByKey("TwoLevelApproval");

        String result = "Process started. Number of currently running"
                + "process instances = "
                + runtimeService.createProcessInstanceQuery().count();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/start-workflow")
    public ResponseEntity startWorkflow() {

        //runtimeService.startProcessInstanceByKey("TwoLevelApproval");

        Map<String, Object> variables = new HashMap<>();
        variables.put("id", "1");
        variables.put("projectType", "Infra. Road HAM ");
        variables.put("riskLevel", "Build Phase");
        variables.put("projectName", "ABC Road Infra Company");

        runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("TwoLevelApproval", variables);



        processInstance.getBusinessKey();
        processInstance.getDescription();
        processInstance.getName();

        Long count = runtimeService.createProcessInstanceQuery().count();

        processInstance.getProcessInstanceId();



        String result = "Count : " +count + "   "  + processInstance.getProcessInstanceId();

        // + processInstance.getBusinessKey() + processInstance.getDescription() + processInstance.getName();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/tasks")
    public ResponseEntity getTasks() {

        TaskService taskService = processEngine.getTaskService();
        List<org.activiti.engine.task.Task> tasks = taskService.createTaskQuery()
                .taskCandidateGroup("approver").list();

         tasks = taskService.createTaskQuery().taskUnassigned().list();

         tasks = taskService.createTaskQuery().list();


        Task task = tasks.get(0);


        return ResponseEntity.ok(task);

    }





}
