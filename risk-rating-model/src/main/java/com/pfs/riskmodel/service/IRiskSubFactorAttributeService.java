package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.RiskSubFactorAttribute;

import java.util.Map;

/**
 * Created by sajeev on 06-Dec-18.
 */
public interface IRiskSubFactorAttributeService {


   public Map<String, Object> createRiskSubFactorAttribute(RiskSubFactorAttribute riskSubFactorAttribute);

   public  Map<String, Object> update(RiskSubFactorAttribute riskSubFactorAttribute);

   public RiskSubFactorAttribute getById(long id);



}
