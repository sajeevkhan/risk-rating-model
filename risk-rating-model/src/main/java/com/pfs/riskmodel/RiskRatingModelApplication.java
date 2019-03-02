package com.pfs.riskmodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients

public class RiskRatingModelApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		ApplicationContext applicationContext =
				SpringApplication.run(RiskRatingModelApplication.class, args);


//		for (String name : applicationContext.getBeanDefinitionNames()) {
//			System.out.println("BEAN ----- > " +name);
//		}

	}
}
