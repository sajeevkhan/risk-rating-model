package com.pfs.riskmodel.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by sajeev on 15-Dec-18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RiskSubFactorAttributeResource {
    private RiskSubFactorAttribute riskSubFactorAttribute;
}
