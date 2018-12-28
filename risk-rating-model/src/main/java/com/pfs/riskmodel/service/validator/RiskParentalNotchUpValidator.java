package com.pfs.riskmodel.service.validator;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskParentalNotchUp;
import com.pfs.riskmodel.domain.RiskSubFactor;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.util.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Service
public class RiskParentalNotchUpValidator {



    @Autowired
    RiskSubFactorValidator riskSubFactorValidator;


    public ValidationResult validate(RiskParentalNotchUp riskParentalNotchUp) {


        ValidationResult validationResult = new ValidationResult();
        validationResult.setSuccessful(true);
        validationResult.setObject(RiskParentalNotchUp.class);

        //Check if Parental NotchUp is Applicable - If not Skip Entire Check
        if(riskParentalNotchUp.getIsParentalNotchUpApplicable() == false) {
            validationResult.setAttributeName("RiskParentalNotchUp.IsParentalNotchUpApplicable");
            validationResult.setValue(null);
            validationResult.setFailed(false);
            return validationResult;
        }


        // Validate header attributes
        if (riskParentalNotchUp.getItemNo() == null) {
            validationResult.setAttributeName("RiskParentalNotchUp.ItemNo");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }


        if (riskParentalNotchUp.getDescription() == null) {
            validationResult.setAttributeName("RiskParentalNotchUp.Description");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }


        //Remaining Attributes of the Parental NotchUp is Optional


        // Validate Component Items
        for (RiskSubFactor r : riskParentalNotchUp.getRiskSubFactors() ) {

            validationResult = riskSubFactorValidator.validate(r);
        }



        return validationResult;
    }
}