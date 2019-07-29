package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.client.LMSEnquiryClient;
import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.resource.LoanApplicationResource;
import com.pfs.riskmodel.resource.ProcessorResource;
import com.pfs.riskmodel.resource.SearchResource;
import com.pfs.riskmodel.service.IBatchReplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@ApiController
@RequiredArgsConstructor
public class RiskModelBatchReplicationController {

    private final LMSEnquiryClient lmsEnquiryClient;

    IBatchReplicationService iBatchReplicationService;

    @GetMapping("/replicator")
    public ResponseEntity searchLoanApplications(@RequestParam(value = "enquiryNumberFrom", required = false) Integer loanNumberFrom,
                                                 @RequestParam(value = "enquiryNumberTo", required = false) Integer loanNumberTo,
                                                 HttpServletRequest request) {
        SearchResource resource = new SearchResource();

        if (loanNumberFrom != null)
            resource.setLoanNumberFrom(loanNumberFrom);
        if (loanNumberTo != null)
            resource.setLoanNumberTo(loanNumberTo);

        iBatchReplicationService.replicationService();

        ResponseEntity<List<LoanApplicationResource>> resources = lmsEnquiryClient.searchEnquiries(resource, getAuthorizationBearer(request.getUserPrincipal()));


        return ResponseEntity.ok("Done");
    }


//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PutMapping("/loanEnquiry/assignProcessors")
//    public ResponseEntity updateProcessors(@RequestBody ProcessorResource processorResource, HttpServletRequest request) {
//        ResponseEntity<LoanApplicationResource> loanAppication = lmsEnquiryClient.updateProcessors(processorResource, getAuthorizationBearer(request.getUserPrincipal()));
//        return ResponseEntity.ok(loanAppication.getBody());
//    }

    public String getAuthorizationBearer(Principal user) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) ((OAuth2Authentication) user).getDetails();
        return "Bearer " + details.getTokenValue();
    }
}
