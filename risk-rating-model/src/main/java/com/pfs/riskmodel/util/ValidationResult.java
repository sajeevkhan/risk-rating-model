package com.pfs.riskmodel.util;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sajeev on 16-Dec-18.
 */
@Getter
@Setter
public class ValidationResult {

    private Object object;
    private String attributeName;
    private String value;
    private boolean successful;
    private boolean failed;

    private boolean notFound;

}
