package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.resource.User;
import com.pfs.riskmodel.service.IWelcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@ApiController
@RequiredArgsConstructor
@Profile("!localdev")
public class WelcomeController {

    private final IWelcomeService welcomeService;

    @GetMapping("/welcome")
    public ResponseEntity<User> welcome() {
        User user = welcomeService.getUser();

        return ResponseEntity.ok(user);
    }
}
