package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.resource.LoanApplication;
import com.pfs.riskmodel.resource.LoanApplicationResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface ILoanApplicationService {

    LoanApplication getEnquiryById(String enquiryId);
    LoanApplication getEnquiryByLoanNumber(String loanNumber);
    //LoanApplication fetchEnquiryByLoanNumber(String loanNumber);



}
