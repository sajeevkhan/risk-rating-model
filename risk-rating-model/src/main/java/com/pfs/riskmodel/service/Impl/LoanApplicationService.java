package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.client.LMSEnquiryClient;
import com.pfs.riskmodel.resource.*;
import com.pfs.riskmodel.service.ILoanApplicationService;
import com.pfs.riskmodel.service.IWelcomeService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPException;

@Service
@EnableFeignClients
public class LoanApplicationService implements ILoanApplicationService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    LMSEnquiryClient lmsEnquiryClient;




    private String getAuthorizationBearer() {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) ((OAuth2Authentication) request.getUserPrincipal()).getDetails();
        return "Bearer " + details.getTokenValue();
    }

    @Override
    public LoanApplication getEnquiryById(String enquiryId) {

        Long enquiryIdAsLong = Long.parseLong(enquiryId);

        ResponseEntity<LoanApplication> loanApplicationResponseEntity;
        ResponseEntity responseEntity;


        try {
            responseEntity =  lmsEnquiryClient.getLoanAppById(enquiryIdAsLong, getAuthorizationBearer());
        } catch (  HTTPException httpException) {
            System.out.println("HTTP Exception -> Get Loan by Enquiry Id:" + enquiryId + ": " + httpException.getMessage() );
            return null;
        } catch (FeignException feignException) {
            System.out.println("Feign Exception -> Get Loan by Enquiry Id:" + enquiryId  +": " +  feignException.getMessage() );
            return null;
        }

        LoanApplication loanApplication = (LoanApplication)responseEntity.getBody();

        return loanApplication;
    }

    @Override
    public LoanApplication getEnquiryByLoanNumber(String loanNumber) {


        ResponseEntity<LoanApplication> loanApplicationResponseEntity;
        LoanNumberResource loanNumberResource = new LoanNumberResource();
        loanNumberResource.setLoanNumber(loanNumber);
        LoanApplication loanApplication;

        try {
            loanApplicationResponseEntity = lmsEnquiryClient.getLoanApp(loanNumberResource, getAuthorizationBearer());
            loanApplication = loanApplicationResponseEntity.getBody();
        } catch (  HTTPException httpException) {
            System.out.println("HTTP Exception -> Get Loan by Loan Number:" + loanNumber + ": " + httpException.getMessage() );
            return null;
        } catch (FeignException feignException) {
            System.out.println("Feign Exception -> Get Loan by Loan Number:" + loanNumber  +": " +  feignException.getMessage() );
            return null;
        }

        return  loanApplication;

    }


}
