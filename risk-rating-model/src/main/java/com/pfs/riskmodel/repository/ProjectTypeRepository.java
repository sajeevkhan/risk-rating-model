package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.ComputingMethod;
import com.pfs.riskmodel.domain.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface ProjectTypeRepository extends JpaRepository<ProjectType, Long>{

    ProjectType findByCode(String code);
}

 