package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.ModelCategory;
import com.pfs.riskmodel.domain.RiskProjectType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface ModelCategoryRepository extends JpaRepository<ModelCategory, Long>{

    ModelCategory findByCode(Integer code);
}

 