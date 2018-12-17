package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskType;

import java.util.Map;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface IRiskTypeService {

    public Map<String, Object> createRiskType(RiskType riskType);

    public Map<String, Object>  updateRiskType(RiskType riskType);
}
