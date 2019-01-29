package com.pfs.riskmodel.ztemp.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfs.riskmodel.AbstractTest;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by sajeev on 18-Dec-18.
 */
public class RiskSubFactorAttributeControllerAPITest  extends AbstractTest {


    @Override
    @Before
    public void setUp() {
        super.setUp();
    }


  //  @Test
    public void getRiskSubFactorAttributes() throws Exception {

        String uri = "/api/riskSubFactorAttributes";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        String jsonOutput = mapToJSON(content);

        System.out.println(jsonOutput);

        RiskSubFactorAttribute[] riskSubFactorAttributes = super.mapFromJson(content, RiskSubFactorAttribute[].class );

//        RiskSubFactorAttribute[] riskSubFactorAttributes = super.mapFromJson(content, RiskSubFactorAttribute[].class);
//
//        assertTrue(riskSubFactorAttributes.length > 0);

    }


//    @Test
    public void createRiskSubFactorAttribute() throws Exception {
        String uri = "/api/riskSubFactorAttribute";
        RiskSubFactorAttribute mockRiskSubFactorAttribute = new RiskSubFactorAttribute();

        mockRiskSubFactorAttribute.setItemNo(1);
        mockRiskSubFactorAttribute.setDescription("First Mock Risk Sub Factor Attribute");
        mockRiskSubFactorAttribute.setScore(1D);
        mockRiskSubFactorAttribute.setWeightage(10D);


        String inputJson = super.mapToJson(mockRiskSubFactorAttribute);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(content, "riskSubFactorAttribute is created successfully");
    }




    // Maps an Object into JSON using Jackson Object Mapper
    private String mapToJSON (Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.writeValueAsString(object);
    }
}
