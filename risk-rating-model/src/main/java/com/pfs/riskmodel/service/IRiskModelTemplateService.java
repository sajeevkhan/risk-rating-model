package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;

import java.util.Map;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface IRiskModelTemplateService {

    public Map<String, Object> createRiskModelTemplate(RiskModelTemplate riskModelTemplate);

    public Map<String, Object>  updateRiskModelTemplate(RiskModelTemplate riskModelTemplate);
}
