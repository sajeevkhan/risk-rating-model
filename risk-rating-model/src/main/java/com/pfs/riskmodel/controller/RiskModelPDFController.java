package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.WorkflowAssignment;
import com.pfs.riskmodel.pdfservice.RiskModelPDFBuilder;
import com.pfs.riskmodel.pdfservice.RiskModelPDFBuilderDebugMode;
import com.pfs.riskmodel.repository.RiskModelTemplateRepository;
import com.pfs.riskmodel.repository.WorkflowAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class RiskModelPDFController {


    private final RiskModelTemplateRepository riskModelTemplateRepository;

    private final RiskModelPDFBuilder riskModelPDFBuilder;

    private final RiskModelPDFBuilderDebugMode riskModelPDFBuilderDebugMode;

    private final WorkflowAssignmentRepository workflowAssignmentRepository;

    @Autowired
    ProcessEngine processEngine;
    /**
     * Handle request to download a RISK MODEL Evaluation as a PDF Document
     */

    @GetMapping(value = "/riskModelPDF")
    public ResponseEntity getPdf(@RequestParam(value = "id", required = true) Long id) throws Exception {
        RiskModelTemplate riskModelTemplate = riskModelTemplateRepository.getOne(id);
        WorkflowAssignment workflowAssignment = workflowAssignmentRepository.findByPurpose(riskModelTemplate.getPurpose());

        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().includeProcessVariables().processInstanceId(riskModelTemplate.getProcessInstanceId()).singleResult();

        ByteArrayOutputStream stream = riskModelPDFBuilder.buildPdfDocument(riskModelTemplate,workflowAssignment, task);
        return streamToResponseEntity(stream, riskModelTemplate);
    }


    @GetMapping(value = "/riskModelPDFDebugMode")
    public ResponseEntity getRiskModelAsPDFDebugMode(@RequestParam(value = "id", required = true) Long id, HttpServletRequest httpRequest) throws Exception {
        httpRequest.getUserPrincipal().toString();

        RiskModelTemplate riskModelTemplate = riskModelTemplateRepository.getOne(id);
        WorkflowAssignment workflowAssignment = workflowAssignmentRepository.findByPurpose(riskModelTemplate.getPurpose());

        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().includeProcessVariables().processInstanceId(riskModelTemplate.getProcessInstanceId()).singleResult();

        ByteArrayOutputStream stream = riskModelPDFBuilderDebugMode.buildPdfDocument(riskModelTemplate , workflowAssignment,task);
        return streamToResponseEntity(stream,riskModelTemplate);
    }


    public ResponseEntity streamToResponseEntity(ByteArrayOutputStream stream, RiskModelTemplate riskModelTemplate){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setContentType(MediaType.APPLICATION_PDF);
        responseHeader.add("Expires", "0");
        responseHeader.add("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        responseHeader.add("Pragma", "no-cache");

        String fileName  = riskModelTemplate.getProjectName() + "_" + riskModelTemplate.getProjectRiskLevel().getValue();

        responseHeader.add("Content-disposition", "inline; filename=" + fileName + ".pdf");


        return new ResponseEntity(stream.toByteArray(), responseHeader, HttpStatus.OK);
    }
}