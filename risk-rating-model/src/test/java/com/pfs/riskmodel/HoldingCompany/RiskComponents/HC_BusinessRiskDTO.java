package com.pfs.riskmodel.HoldingCompany.RiskComponents;

import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskFactorDTO;
import com.pfs.riskmodel.dto.RiskSubFactorAttributeDTO;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;
import com.pfs.riskmodel.utils.RiskAttribute;
import com.pfs.riskmodel.utils.RiskSubFactorAttributesBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 25-Dec-18.
 */
public class HC_BusinessRiskDTO {


    RiskAttribute riskAttribute ;
    Set<RiskAttribute> riskSubFactorAttributes = new HashSet<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();



    public RiskComponentDTO getBusinessRiskDTO() {


        //Business Risk Component 35.00%

        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        Set<RiskFactor> riskFactorList = new HashSet<>();

        riskComponentDTO.setId(null);
        riskComponentDTO.setItemNo(1);
        riskComponentDTO.setComputingMethodCode("01");
        riskComponentDTO.setComputingMethodDescription("Weighted");
        riskComponentDTO.setDescription("Business Risk");
        riskComponentDTO.setScoreTypeDescription("01");
        riskComponentDTO.setScoreTypeDescription("Normal");
        riskComponentDTO.setScoreTypeCode("01");
        riskComponentDTO.setScore(0D);
        riskComponentDTO.setWeightage(0.35D);


        // BusinessRisk Factor

        /*
         --------------------------    Risk Factor 1
            1.1.1 Business  Risk Factor
                  No Concrete Risk Factors - Therefore a dummy Risk Factor called "Business Risk Factor" is added
         */
        RiskFactorDTO businessRiskFactorDTO = new RiskFactorDTO();
        businessRiskFactorDTO.setId(null);
        businessRiskFactorDTO.setItemNo(1);
        businessRiskFactorDTO.setDescription("Business Risk Factor");
        businessRiskFactorDTO.setWeightage(1.000);
        businessRiskFactorDTO.setScore(0D);
        businessRiskFactorDTO.setScoreTypeCode("01");
        businessRiskFactorDTO.setScoreTypeDescription("Normal");
        businessRiskFactorDTO.setComputingMethodCode("05");
        businessRiskFactorDTO.setComputingMethodDescription("Equals");

        //


        // 1.1.2        Risk Sub Factor 1
        //  Cyclicality or seasonality of the business/Industry  - 10%
        RiskSubFactorDTO cyclicailityOfBusIndSubFactorDTO = new RiskSubFactorDTO();
        cyclicailityOfBusIndSubFactorDTO.setId(null);
        cyclicailityOfBusIndSubFactorDTO.setItemNo(1);
        cyclicailityOfBusIndSubFactorDTO.setDescription("Cyclicality or seasonality of the business/Industry");
        cyclicailityOfBusIndSubFactorDTO.setWeightage(0.10);
        cyclicailityOfBusIndSubFactorDTO.setScore(0D);
        cyclicailityOfBusIndSubFactorDTO.setScoreTypeCode("01");
        cyclicailityOfBusIndSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.1.1        Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Over 80% of cash flows coming from segments which are highly cyclical/seasonal in nature. (Wind, Real Estate, Commodities, etc.)"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"50% - 80% of cash flows coming from segments which are highly cyclical/seasonal in nature. (Wind, Real Estate, Commodities, etc.)"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"20% - 50% of cash flows coming from segments which are highly cyclical/seasonal in nature. (Wind, Real Estate, Commodities, etc.)"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Cash flows coming from non-cyclical or non-seasonal industries"));


        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        cyclicailityOfBusIndSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
        businessRiskFactorDTO.addRiskSubFactorDTO(cyclicailityOfBusIndSubFactorDTO);

        // 1.1.2        Risk Sub Factor 2
        //          Asset Concentration - 25%
        RiskSubFactorDTO assetConcentrationRiskSubFactorDTO = new RiskSubFactorDTO();
        assetConcentrationRiskSubFactorDTO.setId(null);
        assetConcentrationRiskSubFactorDTO.setItemNo(2);
        assetConcentrationRiskSubFactorDTO.setDescription("Asset Concentration ");
        assetConcentrationRiskSubFactorDTO.setWeightage(0.25);
        assetConcentrationRiskSubFactorDTO.setScore(0D);
        assetConcentrationRiskSubFactorDTO.setScoreTypeCode("01");
        assetConcentrationRiskSubFactorDTO.setScoreTypeDescription("Normal");

        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "No single asset constitutes more than 10% of total Revenue and the three largest assets account for less than 20% of total Revenue"));
        riskSubFactorAttributes.add(new RiskAttribute(7.5D,"No single asset constitutes more than 20% of total Revenue and the three largest assets account for less than 35% of total Revenue"));
        riskSubFactorAttributes.add(new RiskAttribute(5D,"No single asset constitutes more than 30% of total Revenue and the three largest assets account for less than 50% of total Revenue"));
        riskSubFactorAttributes.add(new RiskAttribute(2.5D,"No single asset represents more than 40% of total Revenue and the three largest assets account for less than 80% of total Revenue"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"There is a dominant asset in the portfolio that accounts for more than 40% of the Revenue; or three or fewer assets account for more than 80% of the Revenue"));


        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        assetConcentrationRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        businessRiskFactorDTO.addRiskSubFactorDTO(assetConcentrationRiskSubFactorDTO);


        // 1.1.3        Risk Sub Factor 3
        //          Asset Performance  - 20%
        RiskSubFactorDTO assetPerformanceRiskSubFactorDTO = new RiskSubFactorDTO();
        assetPerformanceRiskSubFactorDTO.setId(null);
        assetPerformanceRiskSubFactorDTO.setItemNo(3);
        assetPerformanceRiskSubFactorDTO.setDescription("Asset Performance ");
        assetPerformanceRiskSubFactorDTO.setWeightage(0.20);
        assetPerformanceRiskSubFactorDTO.setScore(0D);
        assetPerformanceRiskSubFactorDTO.setScoreTypeCode("01");
        assetPerformanceRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Assets are performing better than the base case cash flows by over 20%"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Assets are performing better than the base case cash flows by less than 20%"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"Assets are performing in line with the base case cash flows"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Assets are performing weaker than the base case cash flows by less than 20%"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Assets are performing weaker than the base case cash flows by over 20%"));


        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS3 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        assetPerformanceRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        businessRiskFactorDTO.addRiskSubFactorDTO(assetPerformanceRiskSubFactorDTO);



        // 1.1.3        Risk Sub Factor 4
        //          Geographical Diversity  - 5%
        RiskSubFactorDTO geographicDivRiskSubFactorDTO = new RiskSubFactorDTO();
        geographicDivRiskSubFactorDTO.setId(null);
        geographicDivRiskSubFactorDTO.setItemNo(4);
        geographicDivRiskSubFactorDTO.setDescription("Geographical Diversity ");
        geographicDivRiskSubFactorDTO.setWeightage(0.05);
        geographicDivRiskSubFactorDTO.setScore(0D);
        geographicDivRiskSubFactorDTO.setScoreTypeCode("01");
        geographicDivRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Highly diversified and present in more than 12 states"));
        riskSubFactorAttributes.add(new RiskAttribute(7.5D,"Strong diversification and present in 8 to 12 states"));
        riskSubFactorAttributes.add(new RiskAttribute(5D,"Moderate diversification and present in 5 to 7 states"));
        riskSubFactorAttributes.add(new RiskAttribute(2.5D,"Weak Diversification with presence in 3 or 4 states"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Minimal diversification, with presence in 1 - 2 States"));


        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS4 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        geographicDivRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS4);
        businessRiskFactorDTO.addRiskSubFactorDTO(geographicDivRiskSubFactorDTO);

        // 1.1.4        Risk Sub Factor 5
        //         Sectoral Diversity  - 10%
        RiskSubFactorDTO sectoralDivRiskSubFactorDTO = new RiskSubFactorDTO();
        sectoralDivRiskSubFactorDTO.setId(null);
        sectoralDivRiskSubFactorDTO.setItemNo(5);
        sectoralDivRiskSubFactorDTO.setDescription("Sectoral Diversity ");
        sectoralDivRiskSubFactorDTO.setWeightage(0.10);
        sectoralDivRiskSubFactorDTO.setScore(0D);
        sectoralDivRiskSubFactorDTO.setScoreTypeCode("01");
        sectoralDivRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Highly diversified and present in more than 7 sectors/industries"));
        riskSubFactorAttributes.add(new RiskAttribute(7.5D,"Strong diversification and present in 6 to 7 sectors/industries"));
        riskSubFactorAttributes.add(new RiskAttribute(5D,"Moderate diversification and present in 4 to 5 sectors/industries"));
        riskSubFactorAttributes.add(new RiskAttribute(2.5D,"Weak Diversification with presence in 2 or 3 sectors/industries"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Minimal diversification, with presence in 1 sectors/industry"));



        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS5 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        sectoralDivRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS5);
        businessRiskFactorDTO.addRiskSubFactorDTO(sectoralDivRiskSubFactorDTO);

        // 1.1.5        Risk Sub Factor 6
        //          Asset Credit Quality  - 30%
        RiskSubFactorDTO assetCreditQualRiskSubFactorDTO = new RiskSubFactorDTO();
        assetCreditQualRiskSubFactorDTO.setId(null);
        assetCreditQualRiskSubFactorDTO.setItemNo(6);
        assetCreditQualRiskSubFactorDTO.setDescription("Asset Credit Quality Diversity ");
        assetCreditQualRiskSubFactorDTO.setWeightage(0.30);
        assetCreditQualRiskSubFactorDTO.setScore(0D);
        assetCreditQualRiskSubFactorDTO.setScoreTypeCode("01");
        assetCreditQualRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "More than 75% of the long term credit facilities of operating companies (including SPVs) are in the 'A+' or above rating and rest are in investment grade"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"50%-75% of the long term credit facilities of operating companies (including SPVs) are in the 'A+' or above rating and rest are in investment grade"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"25%-50% of the long term credit facilities of operating companies (including SPVs) are in the 'A+' or above rating and rest are in investment grade"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Less than 25% of the long term credit facilities of operating companies (including SPVs) are in the 'A+' or above rating"));



        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS6 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        assetCreditQualRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS6);
        businessRiskFactorDTO.addRiskSubFactorDTO(assetCreditQualRiskSubFactorDTO);

        riskComponentDTO.addRiskFactorDTO(businessRiskFactorDTO);

        return riskComponentDTO;
    }

    }
