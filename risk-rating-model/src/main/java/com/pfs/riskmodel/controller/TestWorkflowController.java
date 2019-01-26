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

        runtimeService.startProcessInstanceByKey("RiskModelApproval_v1");

        String result = "Process started. Number of currently running"
                + "process instances = "
                + runtimeService.createProcessInstanceQuery().count();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/start-workflow")
    public ResponseEntity startWorkflow(HttpServletRequest httpServletRequest) {

        //runtimeService.startProcessInstanceByKey("TwoLevelApproval");

        Map<String, Object> variables = new HashMap<>();
        variables.put("riskModelId", "1");
        variables.put("projectType", "Infra. Road HAM ");
        variables.put("riskLevel", "Build Phase");
        variables.put("projectName", "ABC Road Infra Company");
        variables.put("senderUser", httpServletRequest.getUserPrincipal().getName());


        runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("RiskModelApproval_v1", variables);



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

        Task task = taskService.createTaskQuery().processInstanceId("040a6b25-20bd-11e9-997b-dc5360927c19").singleResult();


        return ResponseEntity.ok(task.getId() + task.getDescription() + task.getAssignee());

    }

    @GetMapping("/executeTask")
    public ResponseEntity executeTask(@RequestParam String processInstanceId, HttpServletRequest httpServletRequest) {

        System.out.println(httpServletRequest.getRequestURI());
        System.out.println(httpServletRequest.getRequestURL());
        System.out.println(httpServletRequest.getPathInfo());




        TaskService taskService = processEngine.getTaskService();

        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();

        task.setAssignee(httpServletRequest.getUserPrincipal().getName());


        Map<String, Object> variables = new HashMap<>();
        //variables.put("status", "approved");
        variables.put("result", false);

        taskService.complete(task.getId(),variables);


        List<Task> tasksList = taskService
                                        .createTaskQuery()
                                        .processInstanceId(processInstanceId)
                                        .orderByTaskName()
                                        .asc()
                                        .list();

        tasksList = taskService.createTaskQuery().taskAssignee("kermitt").list();



        for (Task task1 : tasksList) {

            System.out.println("Task Name :" + task1.getName());
            System.out.println("Task Description   :" + task1.getDescription() );
        }

        runtimeService.deleteProcessInstance(processInstanceId, "Deletion Reason");

        return ResponseEntity.ok(task.getName());

    }



    }
