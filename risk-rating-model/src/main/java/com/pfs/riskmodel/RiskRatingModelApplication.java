package com.pfs.riskmodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@ComponentScan({"com.pfs","com.pfs.riskmodel.businessconfig",
				"com.pfs.riskmodel",
		         "com.pfs.riskmodel.repository",
		          "com.pfs.riskmodel.service", "com.pfs.riskmodel.pdfservice"})
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
