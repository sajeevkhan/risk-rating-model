package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.domain.RiskSubFactor;

import java.util.Map;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface IRiskFactorService {

    public Map<String, Object> createRiskFactor(RiskFactor riskFactor);

    public Map<String, Object>  updateRiskFactor(RiskFactor riskFactor);
}
