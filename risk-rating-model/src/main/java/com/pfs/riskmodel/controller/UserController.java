package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.client.LMSEnquiryClient;
import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.resource.SignupResource;
import com.pfs.riskmodel.resource.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@ApiController
@RequiredArgsConstructor




public class UserController {

    private final LMSEnquiryClient lmsEnquiryClient;

    private final HttpServletRequest request;

    @GetMapping("/users/department/{departmentCode}")
    public ResponseEntity<Collection<User>> getUsersByDepartment(@PathVariable("departmentCode") String departmentCode) {
        Collection<User> users = lmsEnquiryClient.getUsersByDepartment(departmentCode, getAuthorizationBearer()).getBody().getContent();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/password/modify")
    public ResponseEntity modifyPassword(@RequestBody SignupResource signupResource) {
        lmsEnquiryClient.modifyPassword(signupResource, getAuthorizationBearer());
        return ResponseEntity.ok().build();
    }

    private String getAuthorizationBearer() {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) ((OAuth2Authentication)
                request.getUserPrincipal()).getDetails();
        return "Bearer " + details.getTokenValue();
    }
}