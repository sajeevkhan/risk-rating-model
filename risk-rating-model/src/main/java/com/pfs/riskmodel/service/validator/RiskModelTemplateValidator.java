package com.pfs.riskmodel.service.validator;

import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.dto.RiskParentalNotchUpDTO;
import com.pfs.riskmodel.repository.*;
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

    @Autowired
    ModelCategoryRepository modelCategoryRepository;

    @Autowired
    RiskRatingModifierValidator riskRatingModifierValidator;

    @Autowired
    RiskParentalNotchUpValidator riskParentalNotchUpValidator;

    public ValidationResult validate(RiskModelTemplate riskModelTemplate) {


        ValidationResult validationResult = new ValidationResult();
        validationResult.setSuccessful(true);
        validationResult.setObject(RiskModelTemplate.class);


        // Validate header attributes
        if (riskModelTemplate.getVersion() == null) {
            validationResult.setAttributeName("RiskModelTemplate.Version");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskModelTemplate.getModelCategory() == null) {
            validationResult.setAttributeName("RiskModelTemplate.ModelCategory");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        } else  {

            ModelCategory modelCategory = modelCategoryRepository.getOne(riskModelTemplate.getModelCategory().getId());

            validationResult.setAttributeName("RiskModelTemplate.ModelCategory");
            validationResult.setValue(riskModelTemplate.getModelCategory().getId().toString());
            validationResult.setFailed(true);
        }


        if (riskModelTemplate.getDescription() == null) {
            validationResult.setAttributeName("RiskModelTemplate.Description");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskModelTemplate.getStatus() == null) {
            validationResult.setAttributeName("RiskModelTemplate.Status");
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


        // Validate Parental NotchUp
        if (riskModelTemplate.getRiskRatingModifiers() != null) {
            for (RiskRatingModifier r : riskModelTemplate.getRiskRatingModifiers()) {
                validationResult = riskRatingModifierValidator.validate(r);
            }
        }


        if (riskModelTemplate.getRiskParentalNotchUps() != null) {
            for (RiskParentalNotchUp r : riskModelTemplate.getRiskParentalNotchUps()) {
                validationResult = riskParentalNotchUpValidator.validate(r);
            }
        }

        return validationResult;


    }
}