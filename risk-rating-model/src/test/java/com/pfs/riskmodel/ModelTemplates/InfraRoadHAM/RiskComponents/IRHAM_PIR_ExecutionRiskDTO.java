package com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.RiskComponents;

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
public class IRHAM_PIR_ExecutionRiskDTO {



    RiskAttribute riskAttribute ;
    List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();



    public RiskComponentDTO getExecutionRiskDTO() {


        //Execution Risk Component 70.00%

        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        List<RiskFactor> riskFactorList = new ArrayList<>();

        riskComponentDTO.setId(null);
        riskComponentDTO.setItemNo(2);
        riskComponentDTO.setComputingMethodCode("01");
        riskComponentDTO.setComputingMethodDescription("Weighted");
        riskComponentDTO.setDescription("Execution Risk");
        riskComponentDTO.setScoreTypeDescription("01");
        riskComponentDTO.setScoreTypeDescription("Normal");
        riskComponentDTO.setScoreTypeCode("01");
        riskComponentDTO.setScore(0D);
        riskComponentDTO.setWeightage(0.70D);


        // DEFLATOR
        // Social Risk - Risk Factor - DEFLATOR
        // ------------- No Risk Sub Factor - Add a Dummy Risk Factor
        //Areas with very high risk of insurgency / terrorism (J&K, Manipur, Nagaland)  0.50
        //Areas with moderately high risk of insurgency / terrorism (Mizoram, Assam, Sikkim, Chhattisgarh, Jharkhand, West Bengal) 0.75
        //Areas with low risk of insurgency / terrorism (All other states) 1.00

        RiskFactorDTO socialRiskDeflatorRiskFactorDTO = new RiskFactorDTO();
        socialRiskDeflatorRiskFactorDTO.setId(null);
        socialRiskDeflatorRiskFactorDTO.setItemNo(1);
        socialRiskDeflatorRiskFactorDTO.setDescription("Social Risk - DEFLATOR");
        socialRiskDeflatorRiskFactorDTO.setWeightage(1.000); // Does not matter, as it is a deflator
        socialRiskDeflatorRiskFactorDTO.setScore(0D);
        socialRiskDeflatorRiskFactorDTO.setScoreTypeCode("02");
        socialRiskDeflatorRiskFactorDTO.setScoreTypeDescription("Deflator");
        socialRiskDeflatorRiskFactorDTO.setComputingMethodCode("06");
        socialRiskDeflatorRiskFactorDTO.setComputingMethodDescription("Multiply");


        // Social Risk DEFLATOR
        // Dummy Risk Sub Factor
        RiskSubFactorDTO socialRiskRiskSubFactorDTO = new RiskSubFactorDTO();
        socialRiskRiskSubFactorDTO.setId(null);
        socialRiskRiskSubFactorDTO.setItemNo(1);
        socialRiskRiskSubFactorDTO.setDescription("Social Risk Sub Factor");
        socialRiskRiskSubFactorDTO.setWeightage(0.00);
        socialRiskRiskSubFactorDTO.setScore(0D);
        socialRiskRiskSubFactorDTO.setScoreTypeCode("02");
        socialRiskRiskSubFactorDTO.setScoreTypeDescription("Deflator");


        //
        //  Social Risk Defalator      Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0.50D, "Areas with very high risk of insurgency / terrorism (J&K, Manipur, Nagaland)"));
        riskSubFactorAttributes.add(new RiskAttribute(0.75D,"Areas with moderately high risk of insurgency / terrorism (Mizoram, Assam, Sikkim, Chhattisgarh, Jharkhand, West Bengal)"));
        riskSubFactorAttributes.add(new RiskAttribute(1.00D,"Areas with low risk of insurgency / terrorism (All other states)"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        socialRiskRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS);
        socialRiskDeflatorRiskFactorDTO.addRiskSubFactorDTO(socialRiskRiskSubFactorDTO);




        // 1.1
        // Risk Factor - Construction Risk - 100 %
        RiskFactorDTO constructionRiskFactorDTO = new RiskFactorDTO();
        constructionRiskFactorDTO.setId(null);
        constructionRiskFactorDTO.setItemNo(2);
        constructionRiskFactorDTO.setDescription("Construction Risk ");
        constructionRiskFactorDTO.setWeightage(1.000); // 100%
        constructionRiskFactorDTO.setScore(0D);
        constructionRiskFactorDTO.setScoreTypeCode("01");
        constructionRiskFactorDTO.setScoreTypeDescription("Normal");
        constructionRiskFactorDTO.setComputingMethodCode("01");
        constructionRiskFactorDTO.setComputingMethodDescription("Weighted");



        // 1.1.1        Risk Sub Factor 1
        // Capability of Sponsor as contractor/developer ArrayList<>()  - 20%
        RiskSubFactorDTO capabilityOfSponsorRiskSubFactorDTO = new RiskSubFactorDTO();
        capabilityOfSponsorRiskSubFactorDTO.setId(null);
        capabilityOfSponsorRiskSubFactorDTO.setItemNo(1);
        capabilityOfSponsorRiskSubFactorDTO.setDescription("Capability of Sponsor as contractor/developer");
        capabilityOfSponsorRiskSubFactorDTO.setWeightage(0.20);
        capabilityOfSponsorRiskSubFactorDTO.setScore(0D);
        capabilityOfSponsorRiskSubFactorDTO.setScoreTypeCode("01");
        capabilityOfSponsorRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.1       Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "New entrant in the area of construction"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"Experience in infrastructure construction exists but new entrant in road construction"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Group with reasonable experience in construction of projects of similar type in limited region. Cumulative work experience upto 250 km."));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"Good experience in projects of similar nature. Cumulative road built upto 750 km."));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Very good experience in projects of similar nature. Cumulative road built upto 1000 km."));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Sponsor/Contractor with extensive international / national experience with cumulative road built of more than 1000 km"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        capabilityOfSponsorRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
        constructionRiskFactorDTO.addRiskSubFactorDTO(capabilityOfSponsorRiskSubFactorDTO);




        //1.1.2        Risk Sub Factor 2
        //1.1.2 Track record of Contractor - 30%
        RiskSubFactorDTO trackRecordOfContractorRiskSubFactorDTO = new RiskSubFactorDTO();
        trackRecordOfContractorRiskSubFactorDTO.setId(null);
        trackRecordOfContractorRiskSubFactorDTO.setItemNo(2);
        trackRecordOfContractorRiskSubFactorDTO.setDescription("Track record of Contractor - With or Without ISO Quality Certification");
        trackRecordOfContractorRiskSubFactorDTO.setWeightage(0.30);
        trackRecordOfContractorRiskSubFactorDTO.setScore(0D);
        trackRecordOfContractorRiskSubFactorDTO.setScoreTypeCode("01");
        trackRecordOfContractorRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 8 Attributes - With or without ISO
        riskSubFactorAttributes = new ArrayList<>(); //TODO - Check Scores
        riskSubFactorAttributes.add(new RiskAttribute(2D, "With ISO - Timelines were significantly breached during previously undertaken projects viz time over run of 1-2 years"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Without ISO - Timelines were significantly breached during previously undertaken projects viz time over run of 1-2 years"));

        riskSubFactorAttributes.add(new RiskAttribute(5D,"With ISO - Timelines were Moderately breached during previously undertaken projects viz time over run of 6 months to 1 years"));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"Without ISO - Timelines were Moderately breached during previously undertaken projects viz time over run of 6 months to 1 years"));

        riskSubFactorAttributes.add(new RiskAttribute(8D,"With ISO - Timelines were breached (by a low margin) during previously undertaken projects viz time over run of up to 6 months"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"Without ISO - Timelines were breached (by a low margin) during previously undertaken projects viz time over run of up to 6 months"));

        riskSubFactorAttributes.add(new RiskAttribute(10D,"With ISO - Timelines were not breached during previously undertaken projects"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Without ISO - Timelines were not breached during previously undertaken projects"));



        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        trackRecordOfContractorRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        constructionRiskFactorDTO.addRiskSubFactorDTO(trackRecordOfContractorRiskSubFactorDTO);



        //1.1.3       Risk Sub Factor 3
        //1.1.3 Financial strength of contractor - 20%
        RiskSubFactorDTO financialStrengthOfContractorRiskSubFactorDTO = new RiskSubFactorDTO();
        financialStrengthOfContractorRiskSubFactorDTO.setId(null);
        financialStrengthOfContractorRiskSubFactorDTO.setItemNo(3);
        financialStrengthOfContractorRiskSubFactorDTO.setDescription("Financial strength of contractor ");
        financialStrengthOfContractorRiskSubFactorDTO.setWeightage(0.20);
        financialStrengthOfContractorRiskSubFactorDTO.setScore(0D);
        financialStrengthOfContractorRiskSubFactorDTO.setScoreTypeCode("01");
        financialStrengthOfContractorRiskSubFactorDTO.setScoreTypeDescription("Normal");


        // 1.1.1       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>(); //TODO - Check Scores
        riskSubFactorAttributes.add(new RiskAttribute(0D, "On standalone basis, TOL/TNW is more than 4.5\n" +
                "OR External credit rating below BBB- (In case of 3rd party contractor)\n" +
                "OR\n" +
                "Unrated borrower"));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"On standalone basis, TOL/TNW is between 3.5 & 4.5\n" +
                "OR External credit rating between BBB+ to BBB- (In case of 3rd party contractor"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"On standalone basis, TOL/TNW is between 2.5 & 3.5\n" +
                "OR External credit rating A- to A+ (In case of 3rd party contractor"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"On standalone basis, TOL/TNW is below 2.5\n" +
                "OR External credit rating more than A+ (In case of 3rd party contractor"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS3 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        financialStrengthOfContractorRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        constructionRiskFactorDTO.addRiskSubFactorDTO(financialStrengthOfContractorRiskSubFactorDTO);




        //1.1.4        Risk Sub Factor 4
        //1.1.4 Complexity of the project  20%
        RiskSubFactorDTO complexityOfProjectRiskSubFactorDTO = new RiskSubFactorDTO();
        complexityOfProjectRiskSubFactorDTO.setId(null);
        complexityOfProjectRiskSubFactorDTO.setItemNo(4);
        complexityOfProjectRiskSubFactorDTO.setDescription("Complexity of the project");
        complexityOfProjectRiskSubFactorDTO.setWeightage(0.20);
        complexityOfProjectRiskSubFactorDTO.setScore(0D);
        complexityOfProjectRiskSubFactorDTO.setScoreTypeCode("01");
        complexityOfProjectRiskSubFactorDTO.setScoreTypeDescription("Normal");


        // 1.1.4      Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Numerous difficulties of terrain (high altitude) Extreme weather (Rainy/Snow fall area), High Seismic zone, structure intensive (long bridges/tunnels/culverts) m"));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"Difficulties of terrain, structure intensive (long bridges/tunnels) make the project High level of complexity and completion is likely to be affected by them."));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"Difficulties of large number of Rail over bridges, habitation & high intensity traffic corridor (if any) structure intensive, make the project average level of complexity and completion is likely to be affected by them."));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Very low level of difficulty to execute the project (e.g.- minimal structures, low Habitation & low intensity traffic corridor)"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS4 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        complexityOfProjectRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS4);
        constructionRiskFactorDTO.addRiskSubFactorDTO(complexityOfProjectRiskSubFactorDTO);




        //1.1.5        Risk Sub Factor 5
        //1.1.5 Safeguards in EPC contracts 10%
        RiskSubFactorDTO safeGuardsInEPCContractsRiskSubFactorDTO = new RiskSubFactorDTO();
        safeGuardsInEPCContractsRiskSubFactorDTO.setId(null);
        safeGuardsInEPCContractsRiskSubFactorDTO.setItemNo(5);
        safeGuardsInEPCContractsRiskSubFactorDTO.setDescription("Safeguards in EPC contracts");
        safeGuardsInEPCContractsRiskSubFactorDTO.setWeightage(0.10);
        safeGuardsInEPCContractsRiskSubFactorDTO.setScore(0D);
        safeGuardsInEPCContractsRiskSubFactorDTO.setScoreTypeCode("01");
        safeGuardsInEPCContractsRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.1.5       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Performance guarantee, Liquidate damages & curing methods/periods, etc. not stipulated in contract."));
        riskSubFactorAttributes.add(new RiskAttribute(3D, "Performance guarantee & Liquidate damages are as per industry practice and Partially covers developer's liability (less than 50% payable to relevant authority by the concerned developer) arising due to delay in project execution, as stipulated in CA"));
        riskSubFactorAttributes.add(new RiskAttribute(7D, "Performance guarantee & Liquidate damages are as per industry practice and covers developer's liability (50% to 100%, payable to relevant authority by the concerned developer) arising due to delay in project execution, as stipulated in CA."));
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Performance guarantee & Liquidate damages are as per industry practice and adequately 100% or more, payable to relevant authority by the concerned developer covers developer's liability arising due to delay in project execution, as stipulated in CA"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS5 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        safeGuardsInEPCContractsRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS5);
        constructionRiskFactorDTO.addRiskSubFactorDTO(safeGuardsInEPCContractsRiskSubFactorDTO);



        riskComponentDTO.addRiskFactorDTO(socialRiskDeflatorRiskFactorDTO);
        riskComponentDTO.addRiskFactorDTO(constructionRiskFactorDTO);

        return riskComponentDTO;
    }


    }
