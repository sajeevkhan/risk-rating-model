package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.ChangeDocument;
import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface IChangeDocumentService {

    public ChangeDocument createChangeDocument (RiskModelTemplate oldRiskModel,
                                                RiskModelTemplate newRiskModel,
                                                Integer action,
                                                String userName);


    public ChangeDocument saveChangeDocument (ChangeDocument changeDocument);



    Page<ChangeDocument> findByLoanNumber(String loanNumber, Pageable pageable);
    Page<ChangeDocument> findByLoanNumberAndDateBetween(String loanNumber, Date dateFrom, Date dateTo, Pageable pageable);
    Page<ChangeDocument> findByRiskModelTemplateId(Long id, Pageable pageable);
    Page<ChangeDocument> findByRiskModelTemplateIdAndLoanNumberAndDateBetween(Long id, String loanNumber, Date dateFrom, Date dateTo, Pageable pageable);
    Page<ChangeDocument> findByRiskModelTemplateIdAndDateBetween(Long id, Date dateFrom, Date dateTo, Pageable pageable);
    List<ChangeDocument> findByRiskModelTemplateIdAndDateBetween(Long id, Date dateFrom, Date dateTo);
    Page<ChangeDocument> findByLoanNumberAndDate(String loanNumber, Date date, Pageable pageable);
    Page<ChangeDocument> findByRiskModelTemplateIdAndDate(Long id, Date date, Pageable pageable);
    Page<ChangeDocument> findByRiskModelTemplateIdAndLoanNumberAndDate(Long id, String loaNumber, Date date, Pageable pageable);

}
