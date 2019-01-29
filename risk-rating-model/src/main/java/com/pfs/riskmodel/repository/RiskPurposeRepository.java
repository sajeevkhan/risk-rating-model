package com.pfs.riskmodel.repository;

 import com.pfs.riskmodel.domain.RiskPurpose;
import com.pfs.riskmodel.domain.ScoreType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface RiskPurposeRepository extends JpaRepository<RiskPurpose, Long>{

    RiskPurpose findByCode(String code);
}
