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
public class InfraRoadHAM_Operational_ModelControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }



    @Test
    public void evaluate_INFRA_ROAD_HAM () throws Exception {
        String uri = "/api/riskModel";



        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();

        Evaluate_InfraRoadHAM_OperationalPhaseData evaluate_infraRoadHAM_operationalPhaseData
                = new Evaluate_InfraRoadHAM_OperationalPhaseData() ;


        riskModelTemplateDTO = evaluate_infraRoadHAM_operationalPhaseData.getInfraRoadHAM_OperationalPhaseData();

        String inputJson = super.mapToJson(riskModelTemplateDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();



        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        System.out.println(content);
        //String jsonOutput = mapToJSON(content);

        //System.out.println(jsonOutput);

      //  RiskSubFactorAttribute[] riskSubFactorAttributes = super.mapFromJson(content, RiskSubFactorAttribute[].class );



    }




}
