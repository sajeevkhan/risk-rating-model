package com.pfs.riskmodel.util;

import java.util.Map;

/**
 * Created by sajeev on 16-Dec-18.
 */
public class CheckServiceResult {

    public static void checkResult(Map<String, Object> result) {

        // Get Validation result

        ValidationResult validationResult = (ValidationResult) result.get("ValidationResult");

        if (validationResult.isFailed()) {

            Check.raiseError( validationResult.getObject(), "Validation.Error", validationResult.getAttributeName(), validationResult.getValue()   );


        }


    }

}
