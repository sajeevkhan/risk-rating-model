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
public class IRHAM_PIR_CompletionRiskDTO {

    RiskAttribute riskAttribute ;
    List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();

    public RiskComponentDTO getCompletionRiskDTO() {


        //Completion Risk Component 30.00%

        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        List<RiskFactor> riskFactorList = new ArrayList<>();

        riskComponentDTO.setId(null);
        riskComponentDTO.setItemNo(1);
        riskComponentDTO.setComputingMethodCode("01");
        riskComponentDTO.setComputingMethodDescription("Weighted");
        riskComponentDTO.setDescription("Completion Risk");
        riskComponentDTO.setScoreTypeDescription("01");
        riskComponentDTO.setScoreTypeDescription("Normal");
        riskComponentDTO.setScoreTypeCode("01");
        riskComponentDTO.setScore(0D);
        riskComponentDTO.setWeightage(0.30D);

        riskComponentDTO.setIsApplicable(true);

        //  Risk Factor
        //1.1 Funding Risk 40%  (Calculation Method = Weightage)
        RiskFactorDTO fundingRiskFactorDTO = new RiskFactorDTO();
        fundingRiskFactorDTO.setId(null);
        fundingRiskFactorDTO.setItemNo(1);
        fundingRiskFactorDTO.setDescription("Funding Risk Factor");
        fundingRiskFactorDTO.setWeightage(0.40D);
        fundingRiskFactorDTO.setScore(0D);
        fundingRiskFactorDTO.setScoreTypeCode("01");
        fundingRiskFactorDTO.setScoreTypeDescription("Normal");
        fundingRiskFactorDTO.setComputingMethodCode("01");
        fundingRiskFactorDTO.setComputingMethodDescription("Weightage");


        // Risk Sub Factor 1
        // 1.1.1 Financial Flexibility - 70%
        RiskSubFactorDTO financialFlexibilityRiskSubFactorDTO = new RiskSubFactorDTO();
        financialFlexibilityRiskSubFactorDTO.setId(null);
        financialFlexibilityRiskSubFactorDTO.setItemNo(1);
        financialFlexibilityRiskSubFactorDTO.setDescription("Financial Flexibility");
        financialFlexibilityRiskSubFactorDTO.setWeightage(0.70);
        financialFlexibilityRiskSubFactorDTO.setScore(0D);
        financialFlexibilityRiskSubFactorDTO.setScoreTypeCode("01");
        financialFlexibilityRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.1       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>(); //
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Limited visibility of infusing sponsor contribution in terms of financials of holding & subsidiary companies. No visibility of growth in operations in near future. Inability to mobilize equity through various means including IPO/FPO, strategic investor, credit support, structured debt raising from market."));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"Sponsor is infusing its contribution by issuance of debt instruments like NCDs/unsecured loan/bridge loan from external sources."));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Sponsors with moderate ability to raise additional fund from various sources including own sources"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"Sponsor is infusing its contribution through divestment of its shareholding of its own/group companies/associate companies in the required time frame (including IPO/FPO etc.)."));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Sponsor is infusing its contribution through generation of internal cash flow including upstreaming of surplus cash flows from group companies/associate companies."));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Sponsor is financially strong enough to facilitate equity infusion."));


        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        financialFlexibilityRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
        fundingRiskFactorDTO.addRiskSubFactorDTO(financialFlexibilityRiskSubFactorDTO);



        // Risk Sub Factor 2
        // 1.1.2 Financial Closure 30%
        RiskSubFactorDTO financialClosureRiskSubFactorDTO = new RiskSubFactorDTO();
        financialClosureRiskSubFactorDTO.setId(null);
        financialClosureRiskSubFactorDTO.setItemNo(2);
        financialClosureRiskSubFactorDTO.setDescription("Financial Closure");
        financialClosureRiskSubFactorDTO.setWeightage(0.30);
        financialClosureRiskSubFactorDTO.setScore(0D);
        financialClosureRiskSubFactorDTO.setScoreTypeCode("01");
        financialClosureRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.1       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "No funds tied up"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"Less than 50% funds tied up"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"50-70% of required funds tied up (e.g. In the form of Equity share capital/CCDs/CCPs/NCDs etc.)"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"Moderate hurdles being faced / expected for tie up of balance funds"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Majority of funds tied up (i.e. > 70%). Very minor hurdles / problems expected in achieving closure beyond 70%"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Funds totally tied up. Sanction letter may be submitted"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        financialClosureRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        fundingRiskFactorDTO.addRiskSubFactorDTO(financialClosureRiskSubFactorDTO);





        //Risk Factor 2
        //  1.2 Key Completion Risks -   60%
        RiskFactorDTO keyCompletionRiskFactorDTO = new RiskFactorDTO();
        keyCompletionRiskFactorDTO.setId(null);
        keyCompletionRiskFactorDTO.setItemNo(2);
        keyCompletionRiskFactorDTO.setDescription("Key Completion Risks");
        keyCompletionRiskFactorDTO.setWeightage(0.60);
        keyCompletionRiskFactorDTO.setScore(0D);
        keyCompletionRiskFactorDTO.setScoreTypeCode("01");
        keyCompletionRiskFactorDTO.setScoreTypeDescription("Normal");
        keyCompletionRiskFactorDTO.setComputingMethodCode("01");
        keyCompletionRiskFactorDTO.setComputingMethodDescription("Weightage");


        // 1.2.1        Risk Sub Factor 1
        //  "1.2.1 Clearances  - 42%
        RiskSubFactorDTO clearancesRiskSubFactorDTO = new RiskSubFactorDTO();
        clearancesRiskSubFactorDTO.setId(null);
        clearancesRiskSubFactorDTO.setItemNo(1);
        clearancesRiskSubFactorDTO.setDescription("Clearances");
        clearancesRiskSubFactorDTO.setWeightage(0.42);
        clearancesRiskSubFactorDTO.setScore(0D);
        clearancesRiskSubFactorDTO.setScoreTypeCode("01");
        clearancesRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.2.1       Risk Sub Factor Attributes
        // -> Five Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "None of the clearances obtained. Expected to face extreme difficulties in obtaining clearances."));
        riskSubFactorAttributes.add(new RiskAttribute(2.5D,"Majority of Critical clearances (Forest department, environment, transferring utilities, etc.) are still to be obtained"));
        riskSubFactorAttributes.add(new RiskAttribute(5D,"A few Critical clearances (Forest department, environment, transferring utilities, etc.) are not yet procured but are likely to be obtained in a short period of time."));
        riskSubFactorAttributes.add(new RiskAttribute(7.5D,"All the critical and most significant clearances are obtained. Very few clearances pending which are in the final stage of being procured"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"The project has obtained all relevant clearance required from all the agencies or Such relevant clearances are identified as Condition Precedents (CPs) to attain Appointed Date as per Concession Agreement (CA)."));


        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS3 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        clearancesRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        keyCompletionRiskFactorDTO.addRiskSubFactorDTO(clearancesRiskSubFactorDTO);


        // 1.2.2        Risk Sub Factor 2
        //1.2.2 Land Acquisition - 58%
        RiskSubFactorDTO landAcqusitionRiskSubFactorDTO = new RiskSubFactorDTO();
        landAcqusitionRiskSubFactorDTO.setId(null);
        landAcqusitionRiskSubFactorDTO.setItemNo(2);
        landAcqusitionRiskSubFactorDTO.setDescription("Land Acquisition/ Right of Way (RoW)");
        landAcqusitionRiskSubFactorDTO.setWeightage(0.58);  // Does not matter
        landAcqusitionRiskSubFactorDTO.setScore(0D);
        landAcqusitionRiskSubFactorDTO.setScoreTypeCode("01");
        landAcqusitionRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.2.2       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "100% of the remaining land is yet to be acquired by competent authority and >50% & <60% of total length (in km) of the project can be constructed."));
        riskSubFactorAttributes.add(new RiskAttribute(3D, "75% of the remaining land is yet to be acquired by competent authority and >60% & <75% of total length (in km) of the project can be constructed."));
        riskSubFactorAttributes.add(new RiskAttribute(7D, "50% of the remaining land is yet to be acquired by competent authority and >75% & <100% of total length (in km) of the project can be constructed"));
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Entire remaining land has been acquired and 100% of total length (in km) of the project can be constructed"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS4 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        landAcqusitionRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS4);
        keyCompletionRiskFactorDTO.addRiskSubFactorDTO(landAcqusitionRiskSubFactorDTO);




        riskComponentDTO.addRiskFactorDTO(fundingRiskFactorDTO);
        riskComponentDTO.addRiskFactorDTO(keyCompletionRiskFactorDTO);

        return riskComponentDTO;

    }

    }
