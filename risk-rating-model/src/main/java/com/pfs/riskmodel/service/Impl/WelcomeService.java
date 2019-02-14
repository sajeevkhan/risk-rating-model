package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.client.LMSEnquiryClient;
import com.pfs.riskmodel.resource.User;
import com.pfs.riskmodel.service.IWelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class WelcomeService implements IWelcomeService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    LMSEnquiryClient lmsEnquiryClient;

    @Override
    public User getUser() {
        ResponseEntity<User> user = lmsEnquiryClient.getUser(getAuthorizationBearer());
        return user.getBody();
    }

    private String getAuthorizationBearer() {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) ((OAuth2Authentication) request.getUserPrincipal()).getDetails();
        return "Bearer " + details.getTokenValue();
    }
}
