package com.pfs.riskmodel.service.validator;

import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.repository.ComputingMethodRepository;
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
public class RiskComponentValidator {

    @Autowired
    ComputingMethodRepository computingMethodRepository;

    @Autowired
    ScoreTypeRepository scoreTypeRepository;

    @Autowired
    RiskFactorValidator riskFactorValidator;

    public ValidationResult validate(RiskComponent riskComponent) {


        ValidationResult validationResult = new ValidationResult();
        validationResult.setSuccessful(true);

        // Validate header attributes
        if (riskComponent.getDescription() == null) {
            validationResult.setAttributeName("RiskComponent.Description");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskComponent.getComputingMethod() == null) {
            validationResult.setAttributeName("RiskComponent.ComputingMethod");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;

        }else {
            ComputingMethod computingMethod = computingMethodRepository.findByCode(riskComponent.getComputingMethod().getCode());
            if (computingMethod == null) {
                validationResult.setAttributeName("RiskComponent.ComputingMethod");
                validationResult.setValue(riskComponent.getComputingMethod().getCode());
                validationResult.setFailed(true);
                return validationResult;
            }
        }

        if (riskComponent.getScoreType() == null) {
            validationResult.setAttributeName("RiskComponent.ScoreType");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;

        }else {
            ScoreType scoreType = scoreTypeRepository.findByCode(riskComponent.getScoreType().getCode());
            if (scoreType == null) {
                validationResult.setAttributeName("RiskComponent.ScoreType");
                validationResult.setValue(riskComponent.getScoreType().getCode());
                validationResult.setFailed(true);
                return validationResult;
            }
        }


        if (riskComponent.getScore() == null) {
            validationResult.setAttributeName("RiskComponent.Score");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskComponent.getWeightage() == null) {
            validationResult.setAttributeName("RiskComponent.Weightage");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        // Validate Items
        for (RiskFactor r : riskComponent.getRiskFactors() ) {

            validationResult = riskFactorValidator.validate(r);
        }

        return validationResult;
    }
}