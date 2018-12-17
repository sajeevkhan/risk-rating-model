package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.RiskFactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface RiskFactorRepository extends JpaRepository<RiskFactor, Long> {
}
