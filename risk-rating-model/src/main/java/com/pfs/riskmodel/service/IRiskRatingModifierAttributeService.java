package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.RiskRatingModifier;
import com.pfs.riskmodel.domain.RiskRatingModifierAttribute;

import java.util.Map;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface IRiskRatingModifierAttributeService {

    public Map<String, Object> createRiskRatingModifierAttribute(RiskRatingModifierAttribute riskRatingModifierAttribute);
    public Map<String, Object> updateRiskRatingModifierAttribute(RiskRatingModifierAttribute riskRatingModifierAttribute);
}
