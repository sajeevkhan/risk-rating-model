package com.pfs.riskmodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
public class RiskRatingModelApplication implements WebMvcConfigurer {

	public static void main(String[] args) {

		SpringApplication.run(RiskRatingModelApplication.class, args);
	}


}
