package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.client.LMSEnquiryClient;
import com.pfs.riskmodel.config.ApiController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class WelcomeController {

    private final LMSEnquiryClient lmsEnquiryClient;

    @GetMapping("/welcome")
    public ResponseEntity hello(Principal principal) {
        return ResponseEntity.ok(lmsEnquiryClient.getUser(getAuthorizationBearer(principal)));
    }

    public String getAuthorizationBearer(Principal user) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) ((OAuth2Authentication) user).getDetails();
        return "Bearer " + details.getTokenValue();
    }
}
