package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.ProjectRiskLevel;
import com.pfs.riskmodel.domain.RiskProjectType;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskProjectType;
import com.pfs.riskmodel.domain.RiskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sajeev on 17-Dec-18.
 */

@Repository
@RepositoryRestResource
public interface RiskModelTemplateRepository extends JpaRepository<RiskModelTemplate, Long> {

    List<RiskModelTemplate>
    findByRiskProjectTypeAndProjectRiskLevelAndStatus(RiskProjectType riskProjectType,
                                                  ProjectRiskLevel projectRiskLevel,
                                                  String status);



    List<RiskModelTemplate> findByRiskProjectTypeAndProjectRiskLevelAndModelTypeAndStatus(RiskProjectType riskProjectType,
                                                                                      ProjectRiskLevel projectRiskLevel,
                                                                                      Integer modelType,
                                                                                      String status);

    RiskModelTemplate findByLoanNumberAndStatus(String loanNumber, String status);

    List<RiskModelTemplate> findByLoanNumber(String loanNumber);

    List<RiskModelTemplate> findByLoanEnquiryId(String loanEnquiryId);

    List<RiskModelTemplate> findByStatus(String status);



}
