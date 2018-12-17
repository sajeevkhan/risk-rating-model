package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface RiskModelTemplateRepository extends JpaRepository<RiskModelTemplate, Long> {
}
