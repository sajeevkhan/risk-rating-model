package com.pfs.riskmodel.client;

import com.pfs.riskmodel.resource.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@FeignClient(name = "lmsEnquiry", url = "${lmsEnquiry.baseUrl}")
public interface LMSEnquiryClient {

    @RequestMapping(value = "/api/loanApplications/search", method = RequestMethod.PUT)
    ResponseEntity<List<LoanApplicationResource>> searchEnquiries(@RequestBody SearchResource searchResource,
                                                                  @RequestHeader("Authorization") String authorization);

    // Get Loan Application by Loan Enquiry Id
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/api/loanApplicationEnquiryId", method = RequestMethod.PUT)
    ResponseEntity<LoanApplicationResource> getEnquiryById(@RequestBody String id,
                                                                  @RequestHeader("Authorization") String authorization);

    // Get Loan Application by Loan Contract Number
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/api/loanApplicationLoanNumber", method = RequestMethod.PUT)
    ResponseEntity<LoanApplicationResource> getEnquiryByLoanNumber(@RequestBody LoanNumberResource loanNumber,
                                                           @RequestHeader("Authorization") String authorization);

    // Fetch Loan Application by Loan Contract Number
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/api/loanApplicationByLoanNumber", method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    ResponseEntity<LoanApplicationResource> fetchEnquiryByLoanNumber(@RequestParam("loanNumber") String loanNumber,
                                                                   @RequestHeader("Authorization") String authorization);

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/api/me", method = RequestMethod.GET)
    ResponseEntity<User> getUser(@RequestHeader("Authorization") String authorization);



    @RequestMapping(value = "/api/users/search/findByRiskDepartmentContainingIgnoreCase", method = RequestMethod.GET)
    ResponseEntity<PagedResources<User>> getUsersByDepartment(@RequestParam("riskDepartment") String departmentCode,
                                                              @RequestHeader("Authorization") String authorization);

    @RequestMapping(value = "/api/user/email", method = RequestMethod.PUT)
    ResponseEntity<User> getUserByEmail(@RequestBody EmailId emailId, @RequestHeader("Authorization") String authorization);

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/api/loanEnquiry/assignProcessors", method = RequestMethod.PUT)
    ResponseEntity<LoanApplicationResource> updateProcessors(@RequestBody ProcessorResource processorResource, @RequestHeader("Authorization") String authorization);

    @PutMapping("/api/password/modify")
    ResponseEntity modifyPassword(@RequestBody SignupResource signupResource, @RequestHeader("Authorization") String authorization);

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/api/loanApp")
    ResponseEntity<LoanApplication> getLoanApp(@RequestBody LoanNumberResource loanNumberResource, @RequestHeader("Authorization") String authorization) ;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/api/loanApp/id")
    ResponseEntity<LoanApplication> getLoanAppById(@RequestBody Long id, @RequestHeader("Authorization") String authorization) ;


    // ResponseEntity<List<LoanApplicationResource>> searchEnquiries(@RequestBody SearchResource searchResource, @RequestHeader("Authorization") String authorization);
}
