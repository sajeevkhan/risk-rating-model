package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.RiskRatingModifier;
import com.pfs.riskmodel.domain.RiskSubFactor;

import java.util.Map;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface IRiskRatingModifierService {

    public Map<String, Object> createRiskRatingModifier(RiskRatingModifier riskRatingModifier);
    public Map<String, Object> updateRiskRatingModifier(RiskRatingModifier riskRatingModifier);
}
