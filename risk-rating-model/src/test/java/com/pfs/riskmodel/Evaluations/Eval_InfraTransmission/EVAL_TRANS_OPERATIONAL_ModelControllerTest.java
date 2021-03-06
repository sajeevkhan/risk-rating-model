package com.pfs.riskmodel.Evaluations.Eval_InfraTransmission;

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
public class EVAL_TRANS_OPERATIONAL_ModelControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }



   // @Test
    public void evaluate_INFRA_TRANS () throws Exception {
        String uri = "/api/riskModel?action=1";


        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();

        Evaluate_Transmission_OperationalPhaseData evaluate_transmission_operationalPhaseData =
                new Evaluate_Transmission_OperationalPhaseData() ;


        riskModelTemplateDTO = evaluate_transmission_operationalPhaseData.getInfraTrans_OperationalPhaseData();

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
