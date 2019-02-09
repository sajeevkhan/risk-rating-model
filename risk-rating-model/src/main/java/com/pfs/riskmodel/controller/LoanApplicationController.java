package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.client.LMSEnquiryClient;
import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.resource.LoanApplicationResource;
import com.pfs.riskmodel.resource.SearchResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ApiController
@RequiredArgsConstructor
public class LoanApplicationController {

    private final LMSEnquiryClient lmsEnquiryClient;

    @GetMapping("/loanApplications/search")
    public ResponseEntity searchLoanApplications(@RequestParam("projectName") String projectName, HttpServletRequest request) {
        SearchResource resource = new SearchResource();
        if (!projectName.equals("undefined"))
            resource.setPartyName(projectName);
        ResponseEntity<List<LoanApplicationResource>> resources = lmsEnquiryClient.searchEnquiries(resource);

        // ResponseEntity<List<LoanApplicationResource>> resources = lmsEnquiryClient.searchEnquiries(resource,"Basic YWRtaW46YWRtaW4");

        return ResponseEntity.ok(resources);
    }
}
