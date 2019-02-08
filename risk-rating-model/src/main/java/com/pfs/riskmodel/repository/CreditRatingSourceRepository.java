package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.CreditRatingSource;
import com.pfs.riskmodel.domain.RiskFactor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface CreditRatingSourceRepository extends JpaRepository<CreditRatingSource, Long> {

    CreditRatingSource findByCode(String code);
}
