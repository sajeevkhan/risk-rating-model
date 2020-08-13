package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.client.LMSEnquiryClient;
import com.pfs.riskmodel.resource.EmailId;
import com.pfs.riskmodel.resource.User;
import com.pfs.riskmodel.service.IWelcomeService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPException;
@Slf4j
@Service
public class WelcomeService implements IWelcomeService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    LMSEnquiryClient lmsEnquiryClient;

    @Override
    public User     getUser() {
        log.info("WELCOME SERVICE : GetUser.........................");
        ResponseEntity<User> user = lmsEnquiryClient.getUser(getAuthorizationBearer());
        return user.getBody();
    }

    @Override
    public User getUserByEmail(EmailId emailId) {
        ResponseEntity<User> user;

        try {
            user = lmsEnquiryClient.getUserByEmail(emailId, getAuthorizationBearer());
        } catch ( HTTPException httpException) {
            System.out.println("HTTP Exception -> Get User By Email:" +emailId.getEmailId() + ": " + httpException.getMessage() );
            return null;
        } catch (FeignException feignException) {
            System.out.println("Feign Exception -> Get User By Email:" + emailId.getEmailId() +": " +  feignException.getMessage() );
            return null;
        }
       return user.getBody();

       // return null;
    }


    private String getAuthorizationBearer() {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) ((OAuth2Authentication) request.getUserPrincipal()).getDetails();
        return "Bearer " + details.getTokenValue();
    }
}
