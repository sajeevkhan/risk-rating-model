package com.pfs.riskmodel;

import com.pfs.riskmodel.ModelTemplates.HoldingCompany.HoldingCompanyData;
import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.BuildPhase.InfraRoadHAM_BuildPhaseData;
import com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.OperationalPhase.InfraRoadHAM_OperationalPhaseData;
import com.pfs.riskmodel.ModelTemplates.InfraRoadToll.BuildPhase.InfraRoadToll_BuildPhaseData;
import com.pfs.riskmodel.ModelTemplates.InfraRoadToll.OperationalPhase.InfraRoadToll_OperationalPhaseData;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.BuildPhase.InfraTransmissionBuildPhaseData;
import com.pfs.riskmodel.ModelTemplates.InfraTransmission.OperationalPhase.InfraTransmissionOperationalPhaseData;
import com.pfs.riskmodel.ModelTemplates.Renewable.BuildPhase.RenewableProjectBuildPhaseData;
import com.pfs.riskmodel.ModelTemplates.Renewable.OperationalPhase.RenewablesOperationalPhaseData;
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
public  class All_Templates_ControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }


    @Test
    public void test () throws Exception {
    }


    @Test
    public  void createAllTemplates() throws Exception {
//
        holdingCompanyTemplate();
        infraRoadHAM_BUILD_Template();
        infraRoadHAM_OPERATIONAL_Template();
        infraRoadToll_BUILD_Template();
        infraRoadToll_OPERATIONAL_Template();
        infraTrans_BUILD_Template();
        infraTransToll_OPERATIONAL_Template();
        renewables_BUILD_Template();
        renewables_OPERATIONAL_Template();

    }


    private void holdingCompanyTemplate() throws  Exception {
        String uri = "/api/riskModelTemplate";
        HoldingCompanyData holdingCompanyData = new HoldingCompanyData();
        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();
        riskModelTemplateDTO = holdingCompanyData.buildRiskModelTemplate();
        String inputJson = super.mapToJson(riskModelTemplateDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        System.out.println(" ------Holding Company Template Create ");

    }

    private void infraRoadHAM_BUILD_Template() throws Exception {

        String uri = "/api/riskModelTemplate";
        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();
        InfraRoadHAM_BuildPhaseData infraRoadHAM_buildPhaseData = new InfraRoadHAM_BuildPhaseData() ;

        riskModelTemplateDTO = infraRoadHAM_buildPhaseData.getInfraRoadHAM_BuildPhaseData();
        String inputJson = super.mapToJson(riskModelTemplateDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        System.out.println(" ------Infra Road Build Template Create ");

    }

    private void infraRoadHAM_OPERATIONAL_Template() throws Exception {


        String uri = "/api/riskModelTemplate";
        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();
        InfraRoadHAM_OperationalPhaseData infraRoadHAM_operationalPhaseData = new InfraRoadHAM_OperationalPhaseData();
        riskModelTemplateDTO = infraRoadHAM_operationalPhaseData.getInfraRoadHAM_OperationalPhaseData();

        String inputJson = super.mapToJson(riskModelTemplateDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        System.out.println(" ------Infra Road OPERATIONAL Template Create ");

    }

    private void infraRoadToll_BUILD_Template() throws Exception {

        String uri = "/api/riskModelTemplate";
        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();
        InfraRoadToll_BuildPhaseData infraRoadToll_buildPhaseData = new InfraRoadToll_BuildPhaseData() ;
        riskModelTemplateDTO = infraRoadToll_buildPhaseData.getInfraRoadToll_BuildPhaseData();

        String inputJson = super.mapToJson(riskModelTemplateDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        System.out.println(content);
        System.out.println(" ------Infra Road TOLL BUILD Template Create ");

    }


    private void infraRoadToll_OPERATIONAL_Template() throws Exception {

        String uri = "/api/riskModelTemplate";
        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();
        InfraRoadToll_OperationalPhaseData infraRoadToll_operationalPhaseData = new InfraRoadToll_OperationalPhaseData() ;

        riskModelTemplateDTO = infraRoadToll_operationalPhaseData.getInfraRoadToll_OperationalPhaseData();

        String inputJson = super.mapToJson(riskModelTemplateDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        System.out.println(" ------Infra Road TOLL OPERATIONAL Template Create ");

    }



    private void infraTrans_BUILD_Template() throws Exception {
        String uri = "/api/riskModelTemplate";
        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();
        InfraTransmissionBuildPhaseData infraTransmissionBuildPhaseData = new InfraTransmissionBuildPhaseData() ;

        riskModelTemplateDTO = infraTransmissionBuildPhaseData.getInfraTransmissionBuildPhaseData();

        String inputJson = super.mapToJson(riskModelTemplateDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        System.out.println(content);
        System.out.println(" ------Infra TRANSMISSION  Build Template Create ");


    }


    private void infraTransToll_OPERATIONAL_Template() throws Exception {

        String uri = "/api/riskModelTemplate";
        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();
        InfraTransmissionOperationalPhaseData infraTransmissionOperationalPhaseData = new InfraTransmissionOperationalPhaseData() ;

        riskModelTemplateDTO = infraTransmissionOperationalPhaseData.getInfraTransmissionOperationalPhaseData();

        String inputJson = super.mapToJson(riskModelTemplateDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();


        System.out.println(content);
        System.out.println(" ------Infra TRANSMISSION  OPERATIONAL Template Create ");

    }


    private void renewables_BUILD_Template() throws Exception {
        String uri = "/api/riskModelTemplate";
        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();
        RenewableProjectBuildPhaseData renewableProjectBuildPhaseData = new RenewableProjectBuildPhaseData() ;

        riskModelTemplateDTO = renewableProjectBuildPhaseData.getRenewableProjectBuildPhaseData();

        String inputJson = super.mapToJson(riskModelTemplateDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        System.out.println(content);
        System.out.println(" ------RENEWABLES  Build Template Create ");

    }


    private void renewables_OPERATIONAL_Template() throws Exception {


        String uri = "/api/riskModelTemplate";
        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();
        RenewablesOperationalPhaseData renewablesOperationalPhaseData = new RenewablesOperationalPhaseData() ;

        riskModelTemplateDTO = renewablesOperationalPhaseData.buildRiskModelTemplate();
        String inputJson = super.mapToJson(riskModelTemplateDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        System.out.println(content);
        System.out.println(" ------RENEWABLES  OPERATIONAL Template Create ");

    }


}
