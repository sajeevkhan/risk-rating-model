package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.ChangeDocument;
import com.pfs.riskmodel.domain.ProjectRiskLevel;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskProjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 17-Dec-18.
 */
@RepositoryRestResource

public interface ChangeDocumentRepository extends JpaRepository<ChangeDocument, Long> {

    List<ChangeDocument> findByLoanNumber(String loanNumber);

    List<ChangeDocument> findByLoanNumberAndDateBetween(String loanNumber, Date dateFrom, Date dateTo);

    List<ChangeDocument> findByLoanNumberAndDate(String loanNumber, Date date);

    List<ChangeDocument> findByRiskModelTemplateId(Long id);


}
