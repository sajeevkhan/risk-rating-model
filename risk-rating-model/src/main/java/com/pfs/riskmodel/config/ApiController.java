package com.pfs.riskmodel.config;

/**
 * Created by sajeev on 06-Dec-18.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@RestController
@Controller
@RequestMapping("/api")
public @interface ApiController {
}

