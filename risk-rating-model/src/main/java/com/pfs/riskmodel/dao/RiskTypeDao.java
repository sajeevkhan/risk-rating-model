package com.pfs.riskmodel.dao;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface RiskTypeDao extends CrudRepository<RiskType, Long> {
}
