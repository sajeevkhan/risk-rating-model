package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.service.IWorkflowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
////
// @Autowired
// private RuntimeService runtimeService;
//
//
// @Autowired
// private TaskService taskService;
//
// @Autowired
// private    RepositoryService repositoryService;


    @Override
    public String startProcess(String assignee) {

//        Map<String, Object> variables = new HashMap<String, Object>();
//        variables.put("projectName", "Test Project");
//
//        runtimeService.startProcessInstanceByKey("TwoLevelApproval", variables);

        return processInfo();
    }

    private String processInfo() {
//        List<Task> tasks = taskService.createTaskQuery().orderByTaskCreateTime().asc().list();
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
