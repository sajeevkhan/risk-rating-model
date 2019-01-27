package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.domain.ProjectRiskLevel;
import com.pfs.riskmodel.domain.ProjectType;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.repository.*;
import com.pfs.riskmodel.service.IRiskModelService;
import com.pfs.riskmodel.service.IRiskModelTemplateService;
import com.pfs.riskmodel.service.IRiskTypeService;
import com.pfs.riskmodel.service.IWorkflowService;
import com.pfs.riskmodel.service.modelvaluator.RiskModelEvaluator;
import com.pfs.riskmodel.service.validator.RiskModelTemplateValidator;
import com.pfs.riskmodel.util.ValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Service
@RequiredArgsConstructor
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
    ProjectTypeRepository projectTypeRepository;

    @Autowired
    ProjectRiskLevelRepository projectRiskLevelRepository;

    @Autowired
    IWorkflowService iWorkflowService;

    @Autowired
    WorkflowStatusRepository workflowStatusRepository;

    @Override
    public Map<String, Object> createRiskModel(RiskModelTemplate riskModelTemplate,
                                               Integer action,
                                               HttpServletRequest httpServletRequest) {

        Map<String, Object> result = new HashMap<>();

        ValidationResult validationResult =  riskModelTemplateValidator.validate(riskModelTemplate);
        if (validationResult.isFailed()) {
            result.put("ValidationResult", validationResult);
            return result;
        }


        // Evaluate Risk Model
        RiskModelEvaluator riskModelEvaluator = new RiskModelEvaluator();
        riskModelEvaluator.evaluateRiskModel(riskModelTemplate);

        if (httpServletRequest.getUserPrincipal() != null)
            riskModelTemplate.setCreatedBy(httpServletRequest.getUserPrincipal().getName());
        if (riskModelTemplate.getWorkflowStatus() == null) {
            riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode("01"));
        }

        riskModelTemplate =  riskModelTemplateRepository.save(riskModelTemplate);


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
                riskModelTemplateRepository.findByProjectTypeAndProjectRiskLevelAndModelTypeAndStatus(
                        riskModelTemplate.getProjectType(),
                        riskModelTemplate.getProjectRiskLevel(),
                        1, //Find Valuations Models only
                        "X");


        for (RiskModelTemplate riskModelTemplateActive : riskModelTemplatesActive ) {
            if ( riskModelTemplateActive.getId() != createdRiskModelTemplateId ) {
                riskModelTemplateActive.setStatus(" ");
                riskModelTemplateRepository.save(riskModelTemplateActive);
            }
        }

        return result;




    }
}
