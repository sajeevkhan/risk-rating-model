package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskFactor;

import java.util.Map;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface IRiskComponentService {

    public Map<String, Object> createRiskComponent(RiskComponent riskComponent);

    public Map<String, Object>  updateRiskComponent(RiskComponent riskComponent);
}
