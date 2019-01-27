package com.pfs.riskmodel.ModelTemplates.HoldingCompany;

import com.pfs.riskmodel.domain.RiskModelSummary;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.dto.RiskModelSummaryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 02-Jan-19.
 */
public class HoldingCompanyRiskModelSummary {
    
    
    public List<RiskModelSummaryDTO> getRiskModelSummary(){

        // Prepare Summary
        RiskModelSummaryDTO riskModelSummary = new RiskModelSummaryDTO();
        List<RiskModelSummaryDTO> riskModelSummaries = new ArrayList<>();
        Integer itemNo = 1;

        riskModelSummary.setId(null);
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Project Implementation Risk Score");
        riskModelSummary.setScore(" ");
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummaryDTO(); itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Project Implementation Risk Grade");
        riskModelSummary.setScore(" ");
        riskModelSummaries.add(riskModelSummary);


        riskModelSummary = new RiskModelSummaryDTO();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Post Project Implementation Risk Score");
        riskModelSummary.setScore(" ");
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummaryDTO();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Post Project Implementation Risk Grade");
        riskModelSummary.setScore(" ");
        riskModelSummaries.add(riskModelSummary);


        riskModelSummary = new RiskModelSummaryDTO();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Overall Project Score");
        riskModelSummary.setScore(" ");
        riskModelSummaries.add(riskModelSummary);


        riskModelSummary = new RiskModelSummaryDTO();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Overall Project Grade");
        riskModelSummary.setScore(" ");
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummaryDTO();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Modified Project Grade");
        riskModelSummary.setScore(" ");
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummaryDTO();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Grade after Parental Notchup");
        riskModelSummary.setScore(" ");
        riskModelSummaries.add(riskModelSummary);



        riskModelSummary = new RiskModelSummaryDTO();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Final Project Grade");
        riskModelSummary.setScore(" ");
        riskModelSummaries.add(riskModelSummary);


        return riskModelSummaries;
    }
}
