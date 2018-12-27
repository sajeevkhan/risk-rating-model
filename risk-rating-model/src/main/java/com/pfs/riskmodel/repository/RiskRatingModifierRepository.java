package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskRatingModifier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface RiskRatingModifierRepository extends JpaRepository<RiskRatingModifier, Long> {
}
