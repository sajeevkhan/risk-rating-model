package com.pfs.riskmodel;

import com.pfs.riskmodel.ModelTemplates.HoldingCompany.HoldingCompanyData;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

/**
 * Created by sajeev on 18-Dec-18.
 */



@RunWith(Suite.class)
@Suite.SuiteClasses({
        HoldingCompany_TemplateControllerTest.class,
        InfraRoadHAM_Build_TemplateControllerTest.class
})
public class All_Templates_ControllerTest {
    @Test
    public void contextLoads() {


    }
}




