package com.pfs.riskmodel.Templates.InfraTransmission.RiskComponents;

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
 * Created by sajeev on 20-Dec-18.
 */
public class PPIR_ManagementRiskDTO {





    RiskAttribute riskAttribute ;
    List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();



    public RiskComponentDTO getManagementRiskDTO() {


        //Management Risk Component 12.00%

        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        List<RiskFactor> riskFactorList = new ArrayList<>();

        riskComponentDTO.setId(null);
        riskComponentDTO.setItemNo(3);
        riskComponentDTO.setComputingMethodCode("01");
        riskComponentDTO.setComputingMethodDescription("Weighted");
        riskComponentDTO.setDescription("Management Risk");
        riskComponentDTO.setScoreTypeDescription("01");
        riskComponentDTO.setScoreTypeDescription("Normal");
        riskComponentDTO.setScoreTypeCode("01");
        riskComponentDTO.setScore(0D);
        riskComponentDTO.setWeightage(0.12D);

        // 1.1 Management Risk Factor
        //  No Concrete Risk Factors - Therefore a dummy Risk Factor called "Management Risk Factor" is added

        RiskFactorDTO managementRiskFactorDTO = new RiskFactorDTO();
        managementRiskFactorDTO.setId(null);
        managementRiskFactorDTO.setItemNo(1);
        managementRiskFactorDTO.setDescription("Business Risk Factor");
        managementRiskFactorDTO.setWeightage(1.000);
        managementRiskFactorDTO.setScore(0D);
        managementRiskFactorDTO.setScoreTypeCode("01");
        managementRiskFactorDTO.setScoreTypeDescription("Normal");
        managementRiskFactorDTO.setComputingMethodCode("05");
        managementRiskFactorDTO.setComputingMethodDescription("Equals");





        // Risk Sub Factor 1
        // 1.1.1 Management competence and experience 50%
        RiskSubFactorDTO managementCompAndExpRiskSubFactorDTO = new RiskSubFactorDTO();
        managementCompAndExpRiskSubFactorDTO.setId(null);
        managementCompAndExpRiskSubFactorDTO.setItemNo(1);
        managementCompAndExpRiskSubFactorDTO.setDescription("Management competence and experience ");
        managementCompAndExpRiskSubFactorDTO.setWeightage(0.50);
        managementCompAndExpRiskSubFactorDTO.setScore(0D);
        managementCompAndExpRiskSubFactorDTO.setScoreTypeCode("01");
        managementCompAndExpRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.1       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "The senior management has successfully executed more than two similar or larger sized projects in the past and successfully operating in the sector in which the project is envisaged."));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"The senior management has successfully executed at-least one similar or larger sized project in the past and has been successfully operating in the sector in which the project is envisaged."));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"The senior management has successfully executed similar projects in the past although the size of the project was smaller and has not operated in the sector in which the project is envisaged but has significant experience in allied sectors."));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"The senior management has nil or unsatisfactory track record in executing similar projects and does not have any experience in the sector and is making its entry."));

         List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
         managementCompAndExpRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
         managementRiskFactorDTO.addRiskSubFactorDTO(managementCompAndExpRiskSubFactorDTO);



        //  Risk Sub Factor 2
        //1.1.2 Management integrity and corporate governance 50%
        RiskSubFactorDTO managementIntgAndCorpGovRiskSubFactorDTO = new RiskSubFactorDTO();
        managementIntgAndCorpGovRiskSubFactorDTO.setId(null);
        managementIntgAndCorpGovRiskSubFactorDTO.setItemNo(2);
        managementIntgAndCorpGovRiskSubFactorDTO.setDescription("Management integrity and corporate governance");
        managementIntgAndCorpGovRiskSubFactorDTO.setWeightage(0.50);
        managementIntgAndCorpGovRiskSubFactorDTO.setScore(0D);
        managementIntgAndCorpGovRiskSubFactorDTO.setScoreTypeCode("01");
        managementIntgAndCorpGovRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.1       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>(); //TODO - Check Scores
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Top management is regarded as having the highest degree of integrity with extremely robust corporate governance standards. A very high degree of compliance in its financial and regulatory related dealings and maintains well above minimum compliance standards. They hold themselves to a high level of stringent scrutiny through their audit/due diligence/ other related processes evaluated by companies of strong repute"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"Management is perceived to be transparent & maintains high degree of compliance in its financial and regulatory disclosures. Maintains fairly good of corporate governance standards"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Management is perceived to be transparent and follow corporate governance standards. Company is not known to have indulged in suspicious transactions. However, transparency and disclosures are not of a high order"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"There is a level of discomfort with regard to information submitted/financials and other disclosures made by the company. Inadequate Corporate Governance standards"));

         List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
         managementIntgAndCorpGovRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
         managementRiskFactorDTO.addRiskSubFactorDTO(managementIntgAndCorpGovRiskSubFactorDTO);


         riskComponentDTO.addRiskFactorDTO(managementRiskFactorDTO);


        return riskComponentDTO;
    }

    }
