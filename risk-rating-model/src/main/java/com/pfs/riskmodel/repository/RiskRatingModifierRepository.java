package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskRatingModifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by sajeev on 17-Dec-18.
 */

@Repository
@RepositoryRestResource
public interface RiskRatingModifierRepository extends JpaRepository<RiskRatingModifier, Long> {
}
