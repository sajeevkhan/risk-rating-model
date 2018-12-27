package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.ComputingMethod;
import com.pfs.riskmodel.domain.RatingModifierComputationMethod;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface RatingModifierComputingMethodRepository extends JpaRepository<RatingModifierComputationMethod, Long>{

    RatingModifierComputationMethod findByCode(String code);
}
