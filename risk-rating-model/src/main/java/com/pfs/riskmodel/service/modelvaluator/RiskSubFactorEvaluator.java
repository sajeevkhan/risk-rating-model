package com.pfs.riskmodel.service.modelvaluator;

import com.pfs.riskmodel.domain.RiskSubFactor;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;

/**
 * Created by sajeev on 30-Dec-18.
 */
public class RiskSubFactorEvaluator {



    public RiskSubFactor evaluateRiskSubFactor(RiskSubFactor riskSubFactor) {


        // Get Score Type Code
        String scoreTypeCode      = riskSubFactor.getScoreType().getCode();

        // Initialize Score
        Double riskSubFactorScore = riskSubFactor.getScore();
        riskSubFactorScore = 0D;

        for (RiskSubFactorAttribute riskSubFactorAttribute: riskSubFactor.getRiskSubFactorAttributes()) {

            if (riskSubFactorAttribute.getIsSelected() == true) {
                riskSubFactorScore = riskSubFactorAttribute.getScore();
                break;
            }
        }


        riskSubFactor.setScore(riskSubFactorScore);
        return riskSubFactor;
    }

}
