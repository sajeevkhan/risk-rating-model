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
public class RiskFactorValidator {

    @Autowired
    ComputingMethodRepository computingMethodRepository;

    @Autowired
    ScoreTypeRepository scoreTypeRepository;

    @Autowired
    RiskSubFactorValidator riskSubFactorValidator;

    public ValidationResult validate(RiskFactor riskFactor) {


        ValidationResult validationResult = new ValidationResult();
        validationResult.setSuccessful(true);

        // Validate header attributes
        if (riskFactor.getDescription() == null) {
            validationResult.setAttributeName("RiskFactor.Description");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if(riskFactor.getComputingMethod() == null){
            validationResult.setAttributeName("RiskFactor.ComputingMethod");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;

        }else {
            ComputingMethod computingMethod = computingMethodRepository.findByCode(riskFactor.getComputingMethod().getCode());
            if (computingMethod == null) {
                validationResult.setAttributeName("RiskFactor.ComputingMethod");
                validationResult.setValue(riskFactor.getComputingMethod().getCode());
                validationResult.setFailed(true);
                return validationResult;
            }
        }

        if ( riskFactor.getScoreType() == null) {
            validationResult.setAttributeName("RiskFactor.ScoreType");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;

        }else {
            ScoreType scoreType = scoreTypeRepository.findByCode(riskFactor.getScoreType().getCode());
            if (scoreType == null) {
                validationResult.setAttributeName("RiskFactor.ScoreType");
                validationResult.setValue(riskFactor.getScoreType().getCode());
                validationResult.setFailed(true);
                return validationResult;
            }
        }


        if (riskFactor.getScore() == null) {
            validationResult.setAttributeName("RiskFactor.Score");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskFactor.getWeightage() == null) {
            validationResult.setAttributeName("RiskFactor.Weightage");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        // Validate Items
        for (RiskSubFactor r : riskFactor.getRiskSubFactors() ) {

            validationResult = riskSubFactorValidator.validate(r);
        }

        return validationResult;
    }
}