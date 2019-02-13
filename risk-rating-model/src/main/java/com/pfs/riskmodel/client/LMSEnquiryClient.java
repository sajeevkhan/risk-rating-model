package com.pfs.riskmodel.client;

import com.pfs.riskmodel.resource.LoanApplicationResource;
import com.pfs.riskmodel.resource.SearchResource;
import com.pfs.riskmodel.resource.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "lmsEnquiry", url = "${lmsEnquiry.baseUrl}")
public interface LMSEnquiryClient {

    @RequestMapping(value = "/api/loanApplications/search", method = RequestMethod.PUT)
    ResponseEntity<List<LoanApplicationResource>> searchEnquiries(@RequestBody SearchResource searchResource,@RequestHeader("Authorization") String authorization);

    @RequestMapping(value = "/api/me", method = RequestMethod.GET)
    ResponseEntity<User> getUser(@RequestHeader("Authorization") String authorization);

    // ResponseEntity<List<LoanApplicationResource>> searchEnquiries(@RequestBody SearchResource searchResource, @RequestHeader("Authorization") String authorization);
}
