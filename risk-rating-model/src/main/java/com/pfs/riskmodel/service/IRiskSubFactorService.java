package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.RiskSubFactor;

import java.util.Map;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface IRiskSubFactorService {

    public Map<String, Object> createRiskSubFactor (RiskSubFactor riskSubFactor);

    public Map<String, Object>  updateRiskSubFactor (RiskSubFactor riskSubFactor);
}
