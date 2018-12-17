package com.pfs.riskmodel.service.validator;

import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import com.pfs.riskmodel.util.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Created by sajeev on 16-Dec-18.
 */
@Slf4j
@Service
public class RiskSubFactorAttributeValidator {

    public ValidationResult validate (RiskSubFactorAttribute riskSubFactorAttribute) {

        ValidationResult validationResult = new ValidationResult();
        validationResult.setSuccessful(true);
        validationResult.setObject(RiskSubFactorAttribute.class);

        // Validate header attributes
        if (riskSubFactorAttribute.getDescription() == null) {
            validationResult.setAttributeName("RiskSubFactorAttribute.Description");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskSubFactorAttribute.getScore() == null) {
            validationResult.setAttributeName("RiskSubFactorAttribute.Score");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskSubFactorAttribute.getWeightage() == null) {
            validationResult.setAttributeName("RiskSubFactorAttribute.Weightage");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }


        return validationResult;
    }

}
