package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.RiskRatingModifier;
import com.pfs.riskmodel.domain.RiskRatingModifierAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface RiskRatingModifierAttributeRepository extends JpaRepository<RiskRatingModifierAttribute, Long> {

}
