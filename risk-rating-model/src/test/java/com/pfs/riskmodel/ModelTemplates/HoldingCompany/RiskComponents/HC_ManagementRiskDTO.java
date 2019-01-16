package com.pfs.riskmodel.ModelTemplates.HoldingCompany.RiskComponents;

import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskFactorDTO;
import com.pfs.riskmodel.dto.RiskSubFactorAttributeDTO;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;
import com.pfs.riskmodel.utils.RiskAttribute;
import com.pfs.riskmodel.utils.RiskSubFactorAttributesBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 25-Dec-18.
 */
public class HC_ManagementRiskDTO {


    RiskAttribute riskAttribute ;
    List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();



    public RiskComponentDTO getManagementRiskSubFactorDTO() {


        //Management Risk Component 10.00%

        RiskComponentDTO managementRiskComponentDTO = new RiskComponentDTO();
        List<RiskFactor> riskFactorList = new ArrayList<>();

        managementRiskComponentDTO.setId(null);
        managementRiskComponentDTO.setItemNo(1);
        managementRiskComponentDTO.setComputingMethodCode("01");
        managementRiskComponentDTO.setComputingMethodDescription("Weighted");
        managementRiskComponentDTO.setDescription("Management Risk");
        managementRiskComponentDTO.setScoreTypeDescription("01");
        managementRiskComponentDTO.setScoreTypeDescription("Normal");
        managementRiskComponentDTO.setScoreTypeCode("01");
        managementRiskComponentDTO.setScore(0D);
        managementRiskComponentDTO.setWeightage(0.10D);

        managementRiskComponentDTO.setIsApplicable(true);

        // Management Risk Factor

            /*
             --------------------------    Risk Factor 1
                1.1.1 Business  Risk Factor
                      No Concrete Risk Factors - Therefore a dummy Risk Factor called "Business Risk Factor" is added
             */
        RiskFactorDTO managementRiskFactorDTO = new RiskFactorDTO();
        managementRiskFactorDTO.setId(null);
        managementRiskFactorDTO.setItemNo(1);
        managementRiskFactorDTO.setDescription("Management Risk Factor");
        managementRiskFactorDTO.setWeightage(1.000);
        managementRiskFactorDTO.setScore(0D);
        managementRiskFactorDTO.setScoreTypeCode("01");
        managementRiskFactorDTO.setScoreTypeDescription("Normal");
        managementRiskFactorDTO.setComputingMethodCode("01");
        managementRiskFactorDTO.setComputingMethodDescription("Weighted");




        // 1.1.1        Risk Sub Factor 1
        //  ArrayList<>()  Management Integrity - 50%
        RiskSubFactorDTO managementIntegrityRiskSubFactorDTO = new RiskSubFactorDTO();
        managementIntegrityRiskSubFactorDTO.setId(null);
        managementIntegrityRiskSubFactorDTO.setItemNo(1);
        managementIntegrityRiskSubFactorDTO.setDescription("Management Integrity");
        managementIntegrityRiskSubFactorDTO.setWeightage(0.50);
        managementIntegrityRiskSubFactorDTO.setScore(0D);
        managementIntegrityRiskSubFactorDTO.setScoreTypeCode("01");
        managementIntegrityRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.1       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Top management is regarded as having the highest degree of integrity with extremely robust corporate governance standards. A very high degree of compliance in its financial and regulatory related dealings and maintains well above minimum compliance standards. They hold themselves to a high level of stringent scrutiny through their audit/due diligence/ other related processes evaluated by companies of strong repute"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"Management is perceived to be transparent & maintains high degree of compliance in its financial and regulatory disclosures."));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Management is perceived to be transparent. Company is not known to have indulged in suspicious transactions. However, transparency and disclosures are not of a high order"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"There is a level of discomfort with regard to information submitted/financials and other disclosures made by the company."));


        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        managementIntegrityRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
        managementRiskFactorDTO.addRiskSubFactorDTO(managementIntegrityRiskSubFactorDTO);


        // 1.1.2       Risk Sub Factor 2
        //  "Corporate governance and Risk Analysis"  - 50%
        RiskSubFactorDTO corpGovAndRiskAnalysisRiskSubFactorDTO = new RiskSubFactorDTO();
        corpGovAndRiskAnalysisRiskSubFactorDTO.setId(null);
        corpGovAndRiskAnalysisRiskSubFactorDTO.setItemNo(2);
        corpGovAndRiskAnalysisRiskSubFactorDTO.setDescription("Corporate governance and Risk Analysis");
        corpGovAndRiskAnalysisRiskSubFactorDTO.setWeightage(0.50);
        corpGovAndRiskAnalysisRiskSubFactorDTO.setScore(0D);
        corpGovAndRiskAnalysisRiskSubFactorDTO.setScoreTypeCode("01");
        corpGovAndRiskAnalysisRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.2      Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Top management is regarded as having the highest degree of integrity with extremely robust corporate governance standards. A very high degree of compliance in its financial and regulatory related dealings and maintains well above minimum compliance standards. They hold themselves to a high level of stringent scrutiny through their audit/due diligence/ other related processes evaluated by companies of strong repute"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"Management is perceived to be transparent & maintains high degree of compliance in its financial and regulatory disclosures."));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Management is perceived to be transparent. Company is not known to have indulged in suspicious transactions. However, transparency and disclosures are not of a high order"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"There is a level of discomfort with regard to information submitted/financials and other disclosures made by the company."));



        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        corpGovAndRiskAnalysisRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        managementRiskFactorDTO.addRiskSubFactorDTO(corpGovAndRiskAnalysisRiskSubFactorDTO);


        managementRiskComponentDTO.addRiskFactorDTO(managementRiskFactorDTO);
        return managementRiskComponentDTO;
    }
}
