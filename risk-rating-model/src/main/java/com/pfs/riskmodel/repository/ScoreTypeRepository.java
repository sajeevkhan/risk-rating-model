package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.ScoreType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface ScoreTypeRepository extends JpaRepository<ScoreType, Long>{

    ScoreType findByCode (String code);
}
