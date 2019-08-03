package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.client.LMSEnquiryClient;
import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.RiskPurpose;
import com.pfs.riskmodel.domain.WorkflowAssignment;
import com.pfs.riskmodel.repository.RiskPurposeRepository;
import com.pfs.riskmodel.repository.WorkflowAssignmentRepository;
import com.pfs.riskmodel.resource.*;
import com.pfs.riskmodel.service.IWelcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@ApiController
@RequiredArgsConstructor
public class LoanApplicationController {

    private final LMSEnquiryClient lmsEnquiryClient;

    @Autowired
    private WorkflowAssignmentRepository workflowAssignmentRepository;

    @Autowired
    private RiskPurposeRepository riskPurposeRepository;

    @Autowired
    private IWelcomeService iWelcomeService;

    @GetMapping("/loanApplications/search")
    public ResponseEntity searchLoanApplications(@RequestParam(value = "projectName", required = false) String projectName,
                                                 @RequestParam(value = "enquiryNumberFrom", required = false) Integer loanNumberFrom,
                                                 @RequestParam(value = "enquiryNumberTo", required = false) Integer loanNumberTo,
                                                 HttpServletRequest request) {
        SearchResource resource = new SearchResource();
        if (projectName != null)
            resource.setPartyName(projectName);
        if (loanNumberFrom != null)
            resource.setLoanNumberFrom(loanNumberFrom);
        if (loanNumberTo != null)
            resource.setLoanNumberTo(loanNumberTo);
        ResponseEntity<List<LoanApplicationResource>> resources =
                lmsEnquiryClient.searchEnquiries(resource, getAuthorizationBearer(request.getUserPrincipal()));

        // ResponseEntity<List<LoanApplicationResource>> resources = lmsEnquiryClient.searchEnquiries(resource,"Basic YWRtaW46YWRtaW4");

        return ResponseEntity.ok(resources);
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/loanEnquiry/assignProcessors")
    public ResponseEntity updateProcessors(@RequestBody ProcessorResource processorResource, HttpServletRequest request) {

        // Get Risk Dept. User
        RiskPurpose riskPurpose = riskPurposeRepository.findByCode("01");
        WorkflowAssignment workflowAssignment = workflowAssignmentRepository.findByPurpose(riskPurpose);
        processorResource.setRiskDepartmentHead(workflowAssignment.getThirdLevelApproverEmailId());



        ResponseEntity<LoanApplicationResource> loanAppication =
                lmsEnquiryClient.updateProcessors(processorResource, getAuthorizationBearer(request.getUserPrincipal()));




        return ResponseEntity.ok(loanAppication.getBody());
    }

    public String getAuthorizationBearer(Principal user) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) ((OAuth2Authentication) user).getDetails();
        return "Bearer " + details.getTokenValue();
    }
}
