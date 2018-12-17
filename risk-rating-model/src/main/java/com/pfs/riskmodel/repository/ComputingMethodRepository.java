package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.ComputingMethod;
import com.pfs.riskmodel.domain.ScoreType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface ComputingMethodRepository extends JpaRepository<ComputingMethod, Long>{

    ComputingMethod findByCode(String code);
}
