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


    List<ChangeDocument> findByRiskModelTemplateId(Long id);

    List<ChangeDocument> findByRiskModelTemplateIdAAndLoanNumberAndDateBetween(Long id, String loanNumber, Date dateFrom, Date dateTo);

    List<ChangeDocument> findByRiskModelTemplateIdAndDateBetween(Long id, Date dateFrom, Date dateTo);

    List<ChangeDocument> findByLoanNumberAndDate(String loanNumber, Date date);

    List<ChangeDocument> findByRiskModelTemplateIdAndDate(Long id, Date date);


    List<ChangeDocument> findByRiskModelTemplateIdAndLoanNumberAndDate(Long id, String loaNumber, Date date);


}
