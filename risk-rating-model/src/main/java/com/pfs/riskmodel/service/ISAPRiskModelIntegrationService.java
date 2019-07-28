package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.resource.RiskEvaluationInSAP;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface ISAPRiskModelIntegrationService {

   //public void createRiskModelInSAP(RiskModelTemplate riskModelTemplate) ;

    public RiskEvaluationInSAP postRiskModelInSAP(RiskModelTemplate riskModelTemplate) ;

    public void putRiskModelInSAP(RiskModelTemplate riskModelTemplate) ;

    public RiskEvaluationInSAP replicateRiskModelInSAP(RiskEvaluationInSAP riskEvaluationInSAP);

    public RiskEvaluationInSAP mapRiskModelToSAPModel(RiskModelTemplate riskModel);

}
