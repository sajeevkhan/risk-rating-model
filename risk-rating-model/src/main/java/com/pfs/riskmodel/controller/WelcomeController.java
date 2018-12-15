package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class WelcomeController {

    @GetMapping("/welcome")
    public String hello() {
        return "Welcome";
    }
}
