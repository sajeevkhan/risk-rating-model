package com.pfs.riskmodel.service.validator;

import com.pfs.riskmodel.domain.RiskSubFactor;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import com.pfs.riskmodel.util.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sajeev on 16-Dec-18.
 */
@Slf4j
@Service
public class RiskSubFactorValidator {

    @Autowired
    private RiskSubFactorAttributeValidator riskSubFactorAttributeValidator;

    public ValidationResult validate(RiskSubFactor riskSubFactor) {


        ValidationResult validationResult = new ValidationResult();
        validationResult.setSuccessful(true);

        // Validate header attributes
        if (riskSubFactor.getDescription() == null) {
            validationResult.setAttributeName("RiskSubFactor.Description");
            validationResult.setValue(riskSubFactor.getDescription());
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskSubFactor.getScore() == null) {
            validationResult.setAttributeName("RiskSubFactor.Score");
            validationResult.setValue(riskSubFactor.getScore().toString());
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskSubFactor.getWeightage() == null) {
            validationResult.setAttributeName("RiskSubFactor.Weightage");
            validationResult.setValue(riskSubFactor.getWeightage().toString());
            validationResult.setFailed(true);
            return validationResult;
        }


        // Validate Items
        for (RiskSubFactorAttribute r : riskSubFactor.getRiskSubFactorAttribute() ) {

            validationResult = riskSubFactorAttributeValidator.validate(r);
        }



        return  validationResult;
    }
}
