package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.ProjectRiskLevel;
import com.pfs.riskmodel.domain.ProjectType;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface RiskModelTemplateRepository extends JpaRepository<RiskModelTemplate, Long> {

    List<RiskModelTemplate>
    findByProjectTypeAndProjectRiskLevelAndStatus(ProjectType projectType,
                                                  ProjectRiskLevel projectRiskLevel,
                                                  String status);



    List<RiskModelTemplate> findByProjectTypeAndProjectRiskLevelAndModelTypeAndStatus(ProjectType projectType,
                                                                                      ProjectRiskLevel projectRiskLevel,
                                                                                      Integer modelType,
                                                                                      String status);

    RiskModelTemplate findByLoanNumberAndStatus(String loanNumber, String status);

    List<RiskModelTemplate> findByLoanNumber(String loanNumber);


    List<RiskModelTemplate> findByStatus(String status);



}
