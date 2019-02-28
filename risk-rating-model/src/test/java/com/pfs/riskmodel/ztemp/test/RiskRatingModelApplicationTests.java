package com.pfs.riskmodel.ztemp.test;

import com.pfs.riskmodel.repository.RiskSubFactorAttributeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
@WebMvcTest
@ImportAutoConfiguration({RibbonAutoConfiguration.class, FeignRibbonClientAutoConfiguration.class, FeignAutoConfiguration.class})
public class RiskRatingModelApplicationTests {



	@Autowired
	MockMvc mockMvc;


	@MockBean
	RiskSubFactorAttributeRepository riskSubFactorAttributeRepository;


	@Test
	public void test () throws Exception {
		System.out.println("RiskRatingModelApplicationTests");

	}
//
//		@Test
//	public void contextLoads() throws Exception {
//
//
//		Mockito.when(riskSubFactorAttributeRepository.findAll()).thenReturn(
//				Collections.emptyList()
//		);
//
//
//
//		MvcResult mvcResult = mockMvc.perform(
//				MockMvcRequestBuilders.get("/api/riskSubFactorAttribute/all")
//					.accept(MediaType.APPLICATION_JSON)
//		).andReturn();
//
//		System.out.println("Mvc reponse : " + mvcResult.getResponse());
//
//		Mockito.verify(riskSubFactorAttributeRepository).findAll();
//
//	}

}
