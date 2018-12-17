package com.pfs.riskmodel.dao;

import com.pfs.riskmodel.domain.RiskFactor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface RiskFactorDao extends CrudRepository<RiskFactor, Long> {
}
