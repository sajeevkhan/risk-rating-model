package com.pfs.riskmodel.ModelTemplates.Renewable.RiskComponents;

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
public class ROP_ManagementRiskDTO {



    RiskAttribute riskAttribute ;
    List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();




    public RiskComponentDTO getManagementRiskDTO() {


        // 4.0 Management Risk - 20%

        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        List<RiskFactor> riskFactorList = new ArrayList<>();

        riskComponentDTO.setId(null);
        riskComponentDTO.setItemNo(4);
        riskComponentDTO.setComputingMethodCode("01");
        riskComponentDTO.setComputingMethodDescription("Weighted");
        riskComponentDTO.setDescription("Management Risk");
        riskComponentDTO.setScoreTypeDescription("01");
        riskComponentDTO.setScoreTypeCode("01");
        riskComponentDTO.setScoreTypeDescription("Normal");

        riskComponentDTO.setScore(0D);
        riskComponentDTO.setWeightage(0.20D);

        /*
         --------------------------    Risk Factor 1
            4.1 Management Risk Factor
                 No Concrete Risk Factors - Therefore a dummy Risk Factor called "Management Risk Factor" is added
         */


        RiskFactorDTO managementRiskFactorDTO = new RiskFactorDTO();
        managementRiskFactorDTO.setId(null);
        managementRiskFactorDTO.setItemNo(1);
        managementRiskFactorDTO.setDescription("Management Risk Factor");
        managementRiskFactorDTO.setWeightage(1.000);
        managementRiskFactorDTO.setScore(0D);
        managementRiskFactorDTO.setScoreTypeCode("01");
        managementRiskFactorDTO.setScoreTypeDescription("Normal");
        managementRiskFactorDTO.setComputingMethodCode("05");
        managementRiskFactorDTO.setComputingMethodDescription("Equals");


        // 4.1.1        Risk Sub Factor 1
        // Management Capability/Quality - 30%

        RiskSubFactorDTO managementCapQualitySubFactorDTO = new RiskSubFactorDTO();
        managementCapQualitySubFactorDTO.setId(null);
        managementCapQualitySubFactorDTO.setItemNo(1);
        managementCapQualitySubFactorDTO.setDescription("Management Capability/Quality");
        managementCapQualitySubFactorDTO.setWeightage(0.30D);
        managementCapQualitySubFactorDTO.setScore(0D);
        managementCapQualitySubFactorDTO.setScoreTypeCode("01");
        managementCapQualitySubFactorDTO.setScoreTypeDescription("Normal");

        // 4.1.1        Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Strong comfort on the promoters."));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Good comfort on the promoters."));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"Above average comfort on the management & promoter"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Average comfort on Promoter/ Management"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"Low comfort on Promoter/ Management"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Very low/no comfort on Promoter/ Management"));


        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS1 =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        managementCapQualitySubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
        managementRiskFactorDTO.addRiskSubFactorDTO(managementCapQualitySubFactorDTO);



        // 4.1.2       Risk Sub Factor 2
        // Financial Strength 20%


        RiskSubFactorDTO financialStrengthRiskSubFactorDTO = new RiskSubFactorDTO();
        financialStrengthRiskSubFactorDTO.setId(null);
        financialStrengthRiskSubFactorDTO.setItemNo(2);
        financialStrengthRiskSubFactorDTO.setDescription("Financial Strength");
        financialStrengthRiskSubFactorDTO.setWeightage(0.30D);
        financialStrengthRiskSubFactorDTO.setScore(0D);
        financialStrengthRiskSubFactorDTO.setScoreTypeCode("01");
        financialStrengthRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 4.1.2        Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Company is part of an extremely financially sound group"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"The group has high financial strength"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"The group has above average financial strength"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"The group has low financial strength"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"The group has low financial strength"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"The group has very low financial strength"));


        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS2 =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        financialStrengthRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        managementRiskFactorDTO.addRiskSubFactorDTO(financialStrengthRiskSubFactorDTO);


        // 4.1.3    Risk Sub Factor 3
        // Credit Track Record - 40%
        RiskSubFactorDTO creditTrackRecordSubFactorDTO = new RiskSubFactorDTO();
        creditTrackRecordSubFactorDTO.setId(null);
        creditTrackRecordSubFactorDTO.setItemNo(3);
        creditTrackRecordSubFactorDTO.setDescription("Credit Track Record");
        creditTrackRecordSubFactorDTO.setWeightage(0.40D);
        creditTrackRecordSubFactorDTO.setScore(0D);
        creditTrackRecordSubFactorDTO.setScoreTypeCode("01");
        creditTrackRecordSubFactorDTO.setScoreTypeDescription("Normal");


        // 4.1.3        Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Extremely strong credit track record"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Strong credit track record"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"Above average credit track record"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Average credit track record"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"Below average credit track record"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Poor credit track record"));


        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS3 =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        creditTrackRecordSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
        managementRiskFactorDTO.addRiskSubFactorDTO(creditTrackRecordSubFactorDTO);


        // 4.1.4    Risk Sub Factor 4  (DEFLATOR)
        // Management Structure - Deflator


        RiskSubFactorDTO repaymentStructureDTO = new RiskSubFactorDTO();
        repaymentStructureDTO.setId(null);
        repaymentStructureDTO.setItemNo(4);
        repaymentStructureDTO.setDescription("Management Structure");
        repaymentStructureDTO.setWeightage(0.00);
        repaymentStructureDTO.setScore(0D);
        repaymentStructureDTO.setScoreTypeCode("01");
        repaymentStructureDTO.setScoreTypeDescription("Deflator");


        // 4.1.5        Risk Sub Factor Attributes
        // -> Four Attributes

        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(1.00D, "The Sponsor / Promoter has significant experience in Power Industry and owns multiple projects"));
        riskSubFactorAttributes.add(new RiskAttribute(0.90D,"The Promoter / Sponsor has significant experience in the industry; however, this is the first project "));
        riskSubFactorAttributes.add(new RiskAttribute(0.75D,"The promoter / Sponsor are new entrant into the business"));
        riskSubFactorAttributes.add(new RiskAttribute(0.50D,"The Promoter / Sponsors are only equity investors and the management succession plan is uncertain"));




        riskComponentDTO.addRiskFactorDTO( managementRiskFactorDTO);

//
//
//        riskSubFactorAttributes = new ArrayList<>();
//        riskSubFactorAttributes.add(new RiskAttribute(0D, ArrayList<>()));
//        riskSubFactorAttributes.add(new RiskAttribute(2D,ArrayList<>()));
//        riskSubFactorAttributes.add(new RiskAttribute(4D,ArrayList<>()));
//        riskSubFactorAttributes.add(new RiskAttribute(6D,ArrayList<>()));
//        riskSubFactorAttributes.add(new RiskAttribute(8D,ArrayList<>()));
//        riskSubFactorAttributes.add(new RiskAttribute(10D,ArrayList<>()));





        return riskComponentDTO;

    }
}
