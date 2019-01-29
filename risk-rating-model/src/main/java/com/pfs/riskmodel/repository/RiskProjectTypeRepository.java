package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.ComputingMethod;
import com.pfs.riskmodel.domain.RiskProjectType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface RiskProjectTypeRepository extends JpaRepository<RiskProjectType, Long>{

    RiskProjectType findByCode(String code);
}

 