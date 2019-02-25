package com.pfs.riskmodel.Evaluations.Eval_InfraRoadHAM;

import com.pfs.riskmodel.AbstractTest;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

/**
 * Created by sajeev on 18-Dec-18.
 */
public class InfraRoadHAM_Build_ModelControllerTest_Start_Workflow extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public  void test() throws Exception {
    }

   // @Test
    public void evaluate_INFRA_ROAD_HAM () throws Exception {


        String uriGet = "/api/riskModelTemplate/id/14";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uriGet)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();


        System.out.println(content);


        // START WORKFLOW
        String uri = "/api/riskModel?action=2";


        mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)).andReturn();


        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        content = mvcResult.getResponse().getContentAsString();

        System.out.println("OUTPUT :   " +content);

        //String jsonOutput = mapToJSON(content);

        //System.out.println(jsonOutput);

      //  RiskSubFactorAttribute[] riskSubFactorAttributes = super.mapFromJson(content, RiskSubFactorAttribute[].class );



    }




}
