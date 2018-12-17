package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.ProjectRiskLevel;
import com.pfs.riskmodel.domain.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface ProjectRiskLevelRepository extends JpaRepository<ProjectRiskLevel, Long>{

    ProjectRiskLevel findByCode(String code);
}
