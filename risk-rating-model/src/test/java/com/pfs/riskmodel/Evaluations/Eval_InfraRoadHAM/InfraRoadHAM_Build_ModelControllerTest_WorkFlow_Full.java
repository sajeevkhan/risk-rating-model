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
public class InfraRoadHAM_Build_ModelControllerTest_WorkFlow_Full extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }



    @Test
    public void evaluate_INFRA_ROAD_HAM_APPROVE () throws Exception {

        String content = new String();
        //Create
        content = create();
        //Start Workflow
        content = startWorkflow(content);
        //Approve Workflow
        content = approveWorkflow(content);
    }


    @Test
    public void evaluate_INFRA_ROAD_HAM_REJECT () throws Exception {

        String content = new String();
        //Create
        content = create();
        //Start Workflow
        content = startWorkflow(content);
        //Reject Workflow
        content = rejectWorkflow(content);
    }



    private String create() throws  Exception{

        String uri = "/api/riskModel?action=1";

        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();

        Evaluate_InfraRoadHAM_BuildPhaseData evaluate_infraRoadHAM_buildPhaseData = new Evaluate_InfraRoadHAM_BuildPhaseData() ;
        riskModelTemplateDTO = evaluate_infraRoadHAM_buildPhaseData.getInfraRoadHAM_BuildPhaseData();

        String inputJson = super.mapToJson(riskModelTemplateDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();


        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        System.out.println("CREATE OUTPUT :   " +content);
        return content;
    }

    private String startWorkflow(String content) throws  Exception {

        // START
        String uri = "/api/riskModel?action=2";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        content = mvcResult.getResponse().getContentAsString();

        System.out.println("START W/F OUTPUT :   " +content);
        return content;
    }


    private String approveWorkflow(String content) throws  Exception {

        // APPROVE
        String uri = "/api/riskModel?action=3";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        content = mvcResult.getResponse().getContentAsString();

        System.out.println("APPROVAL W/F OUTPUT :   " +content);
        return content;
    }

    private String rejectWorkflow(String content) throws  Exception {

        // REJECT
        String uri = "/api/riskModel?action=4";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        content = mvcResult.getResponse().getContentAsString();

        System.out.println("REJECT W/F OUTPUT :   " +content);
        return content;
    }




}
