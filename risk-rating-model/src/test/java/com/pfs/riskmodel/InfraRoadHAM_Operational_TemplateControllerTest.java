package com.pfs.riskmodel;

import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.OperationalPhase.InfraRoadHAM_OperationalPhaseData;
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
public class InfraRoadHAM_Operational_TemplateControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }



    //@Test
    public void createRiskModelTemplate() throws Exception {
        String uri = "/api/riskModelTemplate";



        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();

        InfraRoadHAM_OperationalPhaseData infraRoadHAM_operationalPhaseData = new InfraRoadHAM_OperationalPhaseData() ;


        riskModelTemplateDTO = infraRoadHAM_operationalPhaseData.getInfraRoadHAM_OperationalPhaseData();

        String inputJson = super.mapToJson(riskModelTemplateDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();



        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        //String jsonOutput = mapToJSON(content);

        //System.out.println(jsonOutput);

      //  RiskSubFactorAttribute[] riskSubFactorAttributes = super.mapFromJson(content, RiskSubFactorAttribute[].class );



    }




}
