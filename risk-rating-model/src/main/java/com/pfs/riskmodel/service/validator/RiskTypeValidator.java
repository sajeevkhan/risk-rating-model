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
public class RiskTypeValidator {



    @Autowired
    RiskComponentValidator riskComponentValidator;

    public ValidationResult validate(RiskType riskType) {


        ValidationResult validationResult = new ValidationResult();
        validationResult.setSuccessful(true);

        // Validate header attributes
        if (riskType.getDescription() == null) {
            validationResult.setAttributeName("RiskType.Description");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }




        if (riskType.getScore() == null) {
            validationResult.setAttributeName("RiskType.Score");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

                // Validate Component Items
        for (RiskComponent r : riskType.getRiskComponents() ) {

            validationResult = riskComponentValidator.validate(r);
        }

        return validationResult;
    }
}