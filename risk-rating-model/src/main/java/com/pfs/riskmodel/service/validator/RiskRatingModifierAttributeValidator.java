package com.pfs.riskmodel.service.validator;

import com.pfs.riskmodel.domain.RiskRatingModifierAttribute;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import com.pfs.riskmodel.util.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Created by sajeev on 16-Dec-18.
 */
@Slf4j
@Service
public class RiskRatingModifierAttributeValidator {

    public ValidationResult validate (RiskRatingModifierAttribute riskRatingModifierAttribute) {

        ValidationResult validationResult = new ValidationResult();
        validationResult.setSuccessful(true);
        validationResult.setObject(RiskRatingModifierAttribute.class);

        // Validate header attributes
        if (riskRatingModifierAttribute.getItemNo() == null) {
            validationResult.setAttributeName("RiskRatingModifierAttribute.ItemNo");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskRatingModifierAttribute.getDescription() == null) {
            validationResult.setAttributeName("RiskRatingModifierAttribute.Description");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskRatingModifierAttribute.getYesOrNoIndicator() == null) {
            validationResult.setAttributeName("RiskRatingModifierAttribute.YesNoIndicator");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }


        return validationResult;
    }

}
