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
public class PIR_CompletionRiskDTO {

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

        //  Risk Factor
        //1.1 Funding Risk 40%  (Calculation Method = Sum of Risk Sub Factors)
        RiskFactorDTO fundingRiskFactorDTO = new RiskFactorDTO();
        fundingRiskFactorDTO.setId(null);
        fundingRiskFactorDTO.setItemNo(1);
        fundingRiskFactorDTO.setDescription("Funding Risk Factor");
        fundingRiskFactorDTO.setWeightage(0.40D);
        fundingRiskFactorDTO.setScore(0D);
        fundingRiskFactorDTO.setScoreTypeCode("01");
        fundingRiskFactorDTO.setScoreTypeDescription("Normal");
        fundingRiskFactorDTO.setComputingMethodCode("02");
        fundingRiskFactorDTO.setComputingMethodDescription("Sum");


        // Risk Sub Factor 1
        // 1.1.1 Financial Flexibility - 12% ( This is 12% of 40%)
        RiskSubFactorDTO financialFlexibilityRiskSubFactorDTO = new RiskSubFactorDTO();
        financialFlexibilityRiskSubFactorDTO.setId(null);
        financialFlexibilityRiskSubFactorDTO.setItemNo(1);
        financialFlexibilityRiskSubFactorDTO.setDescription("Financial Flexibility");
        financialFlexibilityRiskSubFactorDTO.setWeightage(1.00); // Does not matter, Comp.Method of Risk Factor = Sum
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
        // 1.1.2 Financial Closure 28%  (this is 28% of 40%)
        RiskSubFactorDTO financialClosureRiskSubFactorDTO = new RiskSubFactorDTO();
        financialClosureRiskSubFactorDTO.setId(null);
        financialClosureRiskSubFactorDTO.setItemNo(2);
        financialClosureRiskSubFactorDTO.setDescription("Financial Closure");
        financialClosureRiskSubFactorDTO.setWeightage(1.00); // Does not matter, Comp.Method of Risk Factor = Sum
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

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        financialClosureRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS);
        fundingRiskFactorDTO.addRiskSubFactorDTO(financialClosureRiskSubFactorDTO);





        //Risk Factor 2
        //  1.2 Key Completion Risks -   60%
        RiskFactorDTO keyCompletionRiskFactorDTO = new RiskFactorDTO();
        keyCompletionRiskFactorDTO.setId(null);
        keyCompletionRiskFactorDTO.setItemNo(1);
        keyCompletionRiskFactorDTO.setDescription("Key Completion Risks");
        keyCompletionRiskFactorDTO.setWeightage(0.60);
        keyCompletionRiskFactorDTO.setScore(0D);
        keyCompletionRiskFactorDTO.setScoreTypeCode("01");
        keyCompletionRiskFactorDTO.setScoreTypeDescription("Normal");
        keyCompletionRiskFactorDTO.setComputingMethodCode("02");
        keyCompletionRiskFactorDTO.setComputingMethodDescription("Sum");


        // 1.2.1        Risk Sub Factor 1
        //  "1.2.1 Clearances  - 25%  (This is 25% of 60%)
        RiskSubFactorDTO clearancesRiskSubFactorDTO = new RiskSubFactorDTO();
        clearancesRiskSubFactorDTO.setId(null);
        clearancesRiskSubFactorDTO.setItemNo(1);
        clearancesRiskSubFactorDTO.setDescription("Clearances");
        clearancesRiskSubFactorDTO.setWeightage(1.00);  //Does not matter, Comp.Method of Risk Factor = Sum
        clearancesRiskSubFactorDTO.setScore(0D);
        clearancesRiskSubFactorDTO.setScoreTypeCode("01");
        clearancesRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.2.1       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "None of the clearances obtained. Expected to face extreme difficulties in obtaining clearances."));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"Company has received LOI for implementation of Project, however, Majority of Critical clearances (CERC approval, execution of TSA, approval under section 68 of EA 2003, etc.) are still to be obtained"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"All the critical and most significant clearances are obtained. Very few clearances such as approval of Section 164 of EA 2003) pending which are in the final stage of being procured"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"The project has obtained all relevant clearance required such as approval under section 68 of EA 2003, CERC approval for adoptation of transmission charges, approval of Section 164 of EA 2003 etc."));


        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS4 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        clearancesRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS);
        keyCompletionRiskFactorDTO.addRiskSubFactorDTO(clearancesRiskSubFactorDTO);


        // 1.2.2        Risk Sub Factor 2
        //1.2.2 Land Acquisition - 35% (This is 35% of 60%)
        RiskSubFactorDTO landAcqusitionRiskSubFactorDTO = new RiskSubFactorDTO();
        landAcqusitionRiskSubFactorDTO.setId(null);
        landAcqusitionRiskSubFactorDTO.setItemNo(2);
        landAcqusitionRiskSubFactorDTO.setDescription("Land Acquisition/ Right of Way (RoW)");
        landAcqusitionRiskSubFactorDTO.setWeightage(1.00);  // Does not matter
        landAcqusitionRiskSubFactorDTO.setScore(0D);
        landAcqusitionRiskSubFactorDTO.setScoreTypeCode("01");
        landAcqusitionRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.2.2       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Land for sub-station has been identified and acquisition yet to start; Preliminary Route survey has been completed and R0W process is yet to start"));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"Land for sub-station has been identified and acquisition process has been started; Detailed rout survey has been completed and RoW for less than 10% tower location has been obtained"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"More than 50% of Land for sub-station has been in possession of the company; Detailed rout survey has been completed and RoW for 10%-25% tower location has been obtained"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"100% land for substation has been acquired (or no land acquisition involved); RoW for more than 25% tower location has been obtained"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS5 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        landAcqusitionRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS);
        keyCompletionRiskFactorDTO.addRiskSubFactorDTO(landAcqusitionRiskSubFactorDTO);




        riskComponentDTO.addRiskFactorDTO(fundingRiskFactorDTO);
        riskComponentDTO.addRiskFactorDTO(keyCompletionRiskFactorDTO);

        return riskComponentDTO;

    }

    }
