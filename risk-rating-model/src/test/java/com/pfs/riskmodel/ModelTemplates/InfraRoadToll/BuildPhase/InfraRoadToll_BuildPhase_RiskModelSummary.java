package com.pfs.riskmodel.ModelTemplates.InfraRoadToll.BuildPhase;

import com.pfs.riskmodel.dto.RiskModelSummaryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 02-Jan-19.
 */
public class InfraRoadToll_BuildPhase_RiskModelSummary {
    
    
    public List<RiskModelSummaryDTO> getRiskModelSummary(){

        // Prepare Summary
        RiskModelSummaryDTO riskModelSummary = new RiskModelSummaryDTO();
        List<RiskModelSummaryDTO> riskModelSummaries = new ArrayList<>();
        Integer itemNo = 1;

        riskModelSummary.setId(null);
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Project Implementation Risk Score");
        riskModelSummary.setValue("");
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummaryDTO(); itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Project Implementation Risk Grade");
        riskModelSummary.setValue("");
        riskModelSummaries.add(riskModelSummary);


        riskModelSummary = new RiskModelSummaryDTO();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Post Project Implementation Risk Score");
        riskModelSummary.setValue("");
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummaryDTO();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Post Project Implementation Risk Grade");
        riskModelSummary.setValue("");
        riskModelSummaries.add(riskModelSummary);


        riskModelSummary = new RiskModelSummaryDTO();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Overall Project Score");
        riskModelSummary.setValue("");
        riskModelSummaries.add(riskModelSummary);


        riskModelSummary = new RiskModelSummaryDTO();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Overall Project Grade");
        riskModelSummary.setValue("");
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummaryDTO();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Modified Project Grade");
        riskModelSummary.setValue("");
        riskModelSummaries.add(riskModelSummary);

        riskModelSummary = new RiskModelSummaryDTO();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Grade after Parental Notchup");
        riskModelSummary.setValue("");
        riskModelSummaries.add(riskModelSummary);



        riskModelSummary = new RiskModelSummaryDTO();itemNo++;
        riskModelSummary.setItemNo(itemNo);
        riskModelSummary.setName("Final Project Grade");
        riskModelSummary.setValue("");
        riskModelSummaries.add(riskModelSummary);


        return riskModelSummaries;
    }
}
