package com.pfs.riskmodel.service.validator;

import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.repository.ComputingMethodRepository;
import com.pfs.riskmodel.repository.ProjectRiskLevelRepository;
import com.pfs.riskmodel.repository.ProjectTypeRepository;
import com.pfs.riskmodel.repository.ScoreTypeRepository;
import com.pfs.riskmodel.util.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Service
public class RiskModelTemplateValidator {



    @Autowired
    RiskTypeValidator riskTypeValidator;

    @Autowired
    ComputingMethodRepository computingMethodRepository;

    @Autowired
    ProjectTypeRepository projectTypeRepository;

    @Autowired
    ProjectRiskLevelRepository projectRiskLevelRepository;

    @Autowired
    ScoreTypeRepository scoreTypeRepository;


    public ValidationResult validate(RiskModelTemplate riskModelTemplate) {


        ValidationResult validationResult = new ValidationResult();
        validationResult.setSuccessful(true);


        // Validate header attributes
        if (riskModelTemplate.getVersion() == null) {
            validationResult.setAttributeName("RiskModelTemplate.Version");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskModelTemplate.getDescription() == null) {
            validationResult.setAttributeName("RiskModelTemplate.Description");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskModelTemplate.getActive() == null) {
            validationResult.setAttributeName("RiskModelTemplate.Active");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskModelTemplate.getProjectType() == null) {
            validationResult.setAttributeName("RiskModelTemplate.ProjectType");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }
        else {
            ProjectType projectType = projectTypeRepository.findByCode(riskModelTemplate.getProjectType().getCode());
            if (projectTypeRepository == null) {
                validationResult.setAttributeName("RiskModelTemplate.ProjectType");
                validationResult.setValue(null);
                validationResult.setFailed(true);
                return validationResult;
            }
        }

        if (riskModelTemplate.getProjectRiskLevel() == null) {
            validationResult.setAttributeName("RiskModelTemplate.projectRiskLevel");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }
        else {
            ProjectRiskLevel projectRiskLevel = projectRiskLevelRepository.findByCode(riskModelTemplate.getProjectRiskLevel().getCode());
            if (projectRiskLevel == null) {
                validationResult.setAttributeName("RiskModelTemplate.projectRiskLevel");
                validationResult.setValue(riskModelTemplate.getProjectRiskLevel().getCode());
                validationResult.setFailed(true);
                return validationResult;
            }
        }

        if (riskModelTemplate.getComputingMethod() == null) {
            validationResult.setAttributeName("RiskModelTemplate.ComputingMethod");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;

        }else {
            ComputingMethod computingMethod = computingMethodRepository.findByCode(riskModelTemplate.getComputingMethod().getCode());
            if (computingMethod == null) {
                validationResult.setAttributeName("RiskModelTemplate.ComputingMethod");
                validationResult.setValue(riskModelTemplate.getComputingMethod().getCode());
                validationResult.setFailed(true);
                return validationResult;
            }
        }

        if (riskModelTemplate.getScore() == null) {
            validationResult.setAttributeName("RiskModelTemplate.Score");
            validationResult.setValue(riskModelTemplate.getScore().toString());
            validationResult.setFailed(true);
            return validationResult;
        }

                // Validate Component Items
        for (RiskType r : riskModelTemplate.getRiskTypes() ) {

            validationResult = riskTypeValidator.validate(r);
        }

        return validationResult;
    }
}