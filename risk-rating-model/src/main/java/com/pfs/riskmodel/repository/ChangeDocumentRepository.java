package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.ChangeDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;

/**
 * Created by sajeev on 17-Dec-18.
 */
@RepositoryRestResource

public interface ChangeDocumentRepository extends JpaRepository<ChangeDocument, Long> {

    Page<ChangeDocument> findByLoanNumber(String loanNumber, Pageable pageable);

    Page<ChangeDocument> findByLoanNumberAndDateBetween(String loanNumber, Date dateFrom, Date dateTo, Pageable pageable);


    Page<ChangeDocument> findByRiskModelTemplateId(Long id, Pageable pageable);

    Page<ChangeDocument> findByRiskModelTemplateIdAndLoanNumberAndDateBetween(Long id, String loanNumber, Date dateFrom, Date dateTo, Pageable pageable);


    Page<ChangeDocument> findByRiskModelTemplateIdAndDateBetween(Long id, Date dateFrom, Date dateTo, Pageable pageable);

    Page<ChangeDocument> findByLoanNumberAndDate(String loanNumber, Date date, Pageable pageable);

    Page<ChangeDocument> findByRiskModelTemplateIdAndDate(Long id, Date date, Pageable pageable);


    Page<ChangeDocument> findByRiskModelTemplateIdAndLoanNumberAndDate(Long id, String loaNumber, Date date, Pageable pageable);


}
