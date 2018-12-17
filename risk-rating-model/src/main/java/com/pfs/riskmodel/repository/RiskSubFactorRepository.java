package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.RiskSubFactor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.interfaces.RSAKey;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface RiskSubFactorRepository extends JpaRepository<RiskSubFactor, Long> {

    //RiskSubFactor findById(Long id);


}
