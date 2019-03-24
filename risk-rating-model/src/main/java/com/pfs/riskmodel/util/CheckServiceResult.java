package com.pfs.riskmodel.util;

import java.util.Map;

/**
 * Created by sajeev on 16-Dec-18.
 */
public class CheckServiceResult {

    public static void checkResult(Map<String, Object> result) {

        // Get Validation result

        ValidationResult validationResult = (ValidationResult) result.get("ValidationResult");
        if (validationResult == null)
            return;

        if (validationResult.isFailed()) {

            Check.raiseError( validationResult.getObject(), "Validation.Error",
                                validationResult.getAttributeName(), validationResult.getValue(), validationResult.getMessage()   );


        }

        if (validationResult.isWorkflowError()) {

            if (validationResult.getAttributeName() == "Workflow.NotStarted") {
                Check.raiseError(validationResult.getObject(), "Workflow.NotStarted",
                        validationResult.getAttributeName(), validationResult.getValue(), validationResult.getMessage());

            }
            if (validationResult.getAttributeName() == "Workflow.Error") {
                Check.raiseError(validationResult.getObject(), "Workflow.Error",
                        validationResult.getAttributeName(), validationResult.getValue(), validationResult.getMessage());

            }

            if (validationResult.getAttributeName() == "Workflow.Completed") {
                Check.raiseError(validationResult.getObject(), "Workflow.Completed",
                        validationResult.getAttributeName(), validationResult.getValue(), validationResult.getMessage());

            }

        }


    }

}
