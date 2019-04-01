package com.pfs.riskmodel.client;

import com.pfs.riskmodel.resource.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "lmsEnquiry", url = "${lmsEnquiry.baseUrl}")
public interface LMSEnquiryClient {

    @RequestMapping(value = "/api/loanApplications/search", method = RequestMethod.PUT)
    ResponseEntity<List<LoanApplicationResource>> searchEnquiries(@RequestBody SearchResource searchResource,
                                                                  @RequestHeader("Authorization") String authorization);

    @RequestMapping(value = "/api/me", method = RequestMethod.GET)
    ResponseEntity<User> getUser(@RequestHeader("Authorization") String authorization);

    @RequestMapping(value = "/api/users/search/findByRiskDepartmentContainingIgnoreCase", method = RequestMethod.GET)
    ResponseEntity<PagedResources<User>> getUsersByDepartment(@RequestParam("riskDepartment") String departmentCode,
                                                              @RequestHeader("Authorization") String authorization);

    @RequestMapping(value = "/api/user/email", method = RequestMethod.PUT)
    ResponseEntity<User> getUserByEmail(@RequestBody EmailId emailId, @RequestHeader("Authorization") String authorization);

    @RequestMapping(value = "/api/loanEnquiry/assignProcessors", method = RequestMethod.PUT)
    ResponseEntity<LoanApplicationResource> updateProcessors(@RequestBody ProcessorResource processorResource, @RequestHeader("Authorization") String authorization);

    @PutMapping("/api/password/modify")
    ResponseEntity modifyPassword(@RequestBody SignupResource signupResource, @RequestHeader("Authorization") String authorization);

    // ResponseEntity<List<LoanApplicationResource>> searchEnquiries(@RequestBody SearchResource searchResource, @RequestHeader("Authorization") String authorization);
}
