package com.pfs.riskmodel.service.validator;

import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.repository.ComputingMethodRepository;
import com.pfs.riskmodel.repository.RatingModifierComputingMethodRepository;
import com.pfs.riskmodel.repository.RiskRatingModifierAttributeRepository;
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
public class RiskRatingModifierValidator {

    @Autowired
    RatingModifierComputingMethodRepository ratingModifierComputingMethodRepository;


    @Autowired
    RiskRatingModifierAttributeRepository riskRatingModifierAttributeRepository;

    @Autowired
    RiskRatingModifierAttributeValidator riskRatingModifierAttributeValidator;

    public ValidationResult validate(RiskRatingModifier riskRatingModifier) {


        ValidationResult validationResult = new ValidationResult();
        validationResult.setSuccessful(true);
        validationResult.setObject(RiskRatingModifier.class);

        // Validate header attributes
        if (riskRatingModifier.getItemNo() == null) {
            validationResult.setAttributeName("RiskRatingModifier.itemNo");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskRatingModifier.getDescription() == null) {
            validationResult.setAttributeName("RiskRatingModifier.Description");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }

        if (riskRatingModifier.getComputingMethod() == null) {
            validationResult.setAttributeName("RiskRatingModifier.ComputingMethod");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;

        }else {
            RatingModifierComputationMethod ratingModifierComputationMethod
                    = ratingModifierComputingMethodRepository.findByCode(riskRatingModifier.getComputingMethod().getCode());
            if (ratingModifierComputationMethod == null) {
                validationResult.setAttributeName("RiskRatingModifier.ComputingMethod");
                validationResult.setValue(riskRatingModifier.getComputingMethod().getCode());
                validationResult.setFailed(true);
                return validationResult;
            }
        }

        if (riskRatingModifier.getScore() == null) {
            validationResult.setAttributeName("RiskRatingModifier.Score");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            return validationResult;
        }


        // Validate Items
        for (RiskRatingModifierAttribute r : riskRatingModifier.getRiskRatingModifierAttributes() ) {

            validationResult =  riskRatingModifierAttributeValidator.validate(r);
        }

        return validationResult;
    }
}