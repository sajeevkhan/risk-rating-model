package com.pfs.riskmodel.dao;

import com.pfs.riskmodel.domain.RiskSubFactor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface  RiskSubFactorDao extends CrudRepository<RiskSubFactor, Long> {
}
