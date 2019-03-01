package com.pfs.riskmodel.dev;

import com.pfs.riskmodel.client.LMSEnquiryClient;
import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.resource.User;
import com.pfs.riskmodel.service.IWelcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@ApiController
@RequiredArgsConstructor
@Profile("localdev")
public class DevWelcomeController {

   private final LMSEnquiryClient lmsEnquiryClient;

   private final IWelcomeService welcomeService;

    @GetMapping("/welcome")
    public ResponseEntity<User> welcome() {
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setFirstName("PFS");
        user.setLastName("Admin");
        user.setRiskDepartment("03");
        return ResponseEntity.ok(user);
    }
}
