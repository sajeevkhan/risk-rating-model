package com.pfs.riskmodel.dao;

import com.pfs.riskmodel.domain.RiskParentalNotchUp;
import com.pfs.riskmodel.domain.RiskParentalNotchUpCondition;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface RiskParentalNotchUpConditionDao extends CrudRepository<RiskParentalNotchUpCondition, Long> {
}
