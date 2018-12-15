package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.RiskSubFactorAttribute;

/**
 * Created by sajeev on 06-Dec-18.
 */
public interface IRiskSubFactorAttributeService {


   public RiskSubFactorAttribute createRiskSubFactorAttribute(RiskSubFactorAttribute riskSubFactorAttribute);

   public RiskSubFactorAttribute update(RiskSubFactorAttribute riskSubFactorAttribute);

   public RiskSubFactorAttribute getById(long id);



}
