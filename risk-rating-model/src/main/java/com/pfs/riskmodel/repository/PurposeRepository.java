package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.Purpose;
import com.pfs.riskmodel.domain.ScoreType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface PurposeRepository extends JpaRepository<Purpose, Long>{

    Purpose findByCode(String code);
}
