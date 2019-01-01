package com.pfs.riskmodel.Templates.Renewable.RiskComponents;

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
public class ROP_FinancialRiskDTO {


    RiskAttribute riskAttribute ;
    List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();



    public RiskComponentDTO getFinancialRiskDTO() {



        // 3.0 Financial Risk - 32%

        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        List<RiskFactor> riskFactorList = new ArrayList<>();

        riskComponentDTO.setId(null);
        riskComponentDTO.setItemNo(2);
        riskComponentDTO.setComputingMethodCode("01");
        riskComponentDTO.setComputingMethodDescription("Weighted");
        riskComponentDTO.setDescription("Financial Risk");
        riskComponentDTO.setScoreTypeDescription("01");
        riskComponentDTO.setScoreTypeCode("01");
        riskComponentDTO.setScoreTypeDescription("Normal");
        riskComponentDTO.setScore(0D);
        riskComponentDTO.setWeightage(0.32D);

        /*
         --------------------------    Risk Factor 1
            3.1 Financial Risk Factor
                 No Concrete Risk Factors - Therefore a dummy Risk Factor called "Financial Risk Factor" is added
         */


        RiskFactorDTO financialRiskFactorDTO = new RiskFactorDTO();
        financialRiskFactorDTO.setId(null);
        financialRiskFactorDTO.setItemNo(1);
        financialRiskFactorDTO.setDescription("Financial Risk Factor");
        financialRiskFactorDTO.setWeightage(1.000);
        financialRiskFactorDTO.setScore(0D);
        financialRiskFactorDTO.setScoreTypeCode("01");
        financialRiskFactorDTO.setScoreTypeDescription("Normal");
        financialRiskFactorDTO.setComputingMethodCode("05");
        financialRiskFactorDTO.setComputingMethodDescription("Equals");


        //                      Risk Sub Factor 1
        // 3.1.1 Sensitivity to Base Case Revenue 17.65%
        RiskSubFactorDTO sensitivityToBusinessCaseRevenueDTO = new RiskSubFactorDTO();
        sensitivityToBusinessCaseRevenueDTO.setId(null);
        sensitivityToBusinessCaseRevenueDTO.setItemNo(1);
        sensitivityToBusinessCaseRevenueDTO.setDescription("Sensitivity to Base Case Revenue");
        sensitivityToBusinessCaseRevenueDTO.setWeightage(0.1765);
        sensitivityToBusinessCaseRevenueDTO.setScore(0D);
        sensitivityToBusinessCaseRevenueDTO.setScoreTypeCode("01");
        sensitivityToBusinessCaseRevenueDTO.setScoreTypeDescription("Normal");

        // 3.1.1        Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Less than 1"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"Equal to or above 1 but less than 1.05"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Equal to or above 1.05 but less than 1.1"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"Equal to or above 1.1 but less than 1.2"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Equal to or above 1.2 but less than 1.4"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Equal to or above 1.4"));

        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS1 =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        sensitivityToBusinessCaseRevenueDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
        financialRiskFactorDTO.addRiskSubFactorDTO(sensitivityToBusinessCaseRevenueDTO);


        //                      Risk Sub Factor 2
        // 3.1.2 Minimum DSCR - Build Phase (Ratio) 23.53%
        RiskSubFactorDTO minimumDSCRBuildPhaseDTO = new RiskSubFactorDTO();
        minimumDSCRBuildPhaseDTO.setId(null);
        minimumDSCRBuildPhaseDTO.setItemNo(2);
        minimumDSCRBuildPhaseDTO.setDescription("Minimum DSCR - Build Phase (Ratio) ");
        minimumDSCRBuildPhaseDTO.setWeightage(0.2353);
        minimumDSCRBuildPhaseDTO.setScore(0D);
        minimumDSCRBuildPhaseDTO.setScoreTypeCode("01");
        minimumDSCRBuildPhaseDTO.setScoreTypeDescription("Normal");

        // 3.1.2        Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Less than 1"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"Equal to or above 1 but less than 1.01"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Equal to or above 1.1 but less than 1.2"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"Equal to or above 1.2 but less than 1.3"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Equal to or above 1.3 but less than 1.5"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Equal to or above 1.5"));

        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS2 =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        minimumDSCRBuildPhaseDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        financialRiskFactorDTO.addRiskSubFactorDTO(minimumDSCRBuildPhaseDTO);

        //                      Risk Sub Factor 3
        // 3.1.3 Adjusted Debt / Equity ratio    17.65%
        RiskSubFactorDTO adjustedDebtEquityRatioDTO = new RiskSubFactorDTO();
        adjustedDebtEquityRatioDTO.setId(null);
        adjustedDebtEquityRatioDTO.setItemNo(3);
        adjustedDebtEquityRatioDTO.setDescription("Adjusted Debt/Equity Ratio ");
        adjustedDebtEquityRatioDTO.setWeightage(0.1765);
        adjustedDebtEquityRatioDTO.setScore(0D);
        adjustedDebtEquityRatioDTO.setScoreTypeCode("01");
        adjustedDebtEquityRatioDTO.setScoreTypeDescription("Normal");


        // 3.1.3        Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Less than 1.00"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,">1.00 to 1.33"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,">1.33 to 2.33"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,">2.33 to 3.00"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,">3. to 4.00"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"> 4.00"));

        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS3 =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        adjustedDebtEquityRatioDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        financialRiskFactorDTO.addRiskSubFactorDTO(adjustedDebtEquityRatioDTO);


        //              Risk Sub Factor 4
        // 3.1.4 Degree of Exposure to Interest Risk/ Currency Risk - 17.65%

        RiskSubFactorDTO degreeOfExposureToInterestCurrencyRiskDTO = new RiskSubFactorDTO();
        degreeOfExposureToInterestCurrencyRiskDTO.setId(null);
        degreeOfExposureToInterestCurrencyRiskDTO.setItemNo(4);
        degreeOfExposureToInterestCurrencyRiskDTO.setDescription("Degree of Exposure to Interest Risk/ Currency Risk");
        degreeOfExposureToInterestCurrencyRiskDTO.setWeightage(0.1765);
        degreeOfExposureToInterestCurrencyRiskDTO.setScore(0D);
        degreeOfExposureToInterestCurrencyRiskDTO.setScoreTypeCode("01");
        degreeOfExposureToInterestCurrencyRiskDTO.setScoreTypeDescription("Normal");


        // 3.1.4        Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "No exposure absolutely to IRR/CR. All position are hedged fully"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Minimal exposure to IRR/CR. Most of the positions are hedged"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"Exposure to IRR/CR is small"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Average exposure to IRR/CR"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"High Risk exposure to IRR/CR"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Extremely High exposure to IRR/CR"));

        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS4 =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        degreeOfExposureToInterestCurrencyRiskDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS4);
        financialRiskFactorDTO.addRiskSubFactorDTO(degreeOfExposureToInterestCurrencyRiskDTO);


        //              Risk Sub Factor 5
        // 3.1.5 Project Internal Rate of Return    - 23.53%

        RiskSubFactorDTO projectIRRDTO = new RiskSubFactorDTO();
        projectIRRDTO.setId(null);
        projectIRRDTO.setItemNo(5);
        projectIRRDTO.setDescription("Project Internal Rate of Return");
        projectIRRDTO.setWeightage(0.2353);
        projectIRRDTO.setScore(0D);
        projectIRRDTO.setScoreTypeCode("01");
        projectIRRDTO.setScoreTypeDescription("Normal");


        // 3.1.5        Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Less than 10%"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"Equal to or above 10% but less than 13.5%"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Equal to or above 13.5% but less than 17%"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"Equal to or above 17% but less than 20.5%"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Equal to or above 20.5% but less than 24%"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Equal to or above 24%"));

        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS5 =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        projectIRRDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS5);
        financialRiskFactorDTO.addRiskSubFactorDTO(projectIRRDTO);





        //          Risk Sub Factor 6 (DEFLATOR)
        //3.1.6 Repayment Structure - Deflator


        RiskSubFactorDTO repaymentStructureDTO = new RiskSubFactorDTO();
        repaymentStructureDTO.setId(null);
        repaymentStructureDTO.setItemNo(6);
        repaymentStructureDTO.setDescription("Repayment Structure");
        repaymentStructureDTO.setWeightage(0.00);
        repaymentStructureDTO.setScore(0D);
        repaymentStructureDTO.setScoreTypeCode("01");
        repaymentStructureDTO.setScoreTypeDescription("Deflator");


        // 3.1.6        Risk Sub Factor Attributes
        // -> Five Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(1.00D, "Payment structure and liquidity maintained (cash surplus to service debt) is sufficient for cumulative lean months payment"));
        riskSubFactorAttributes.add(new RiskAttribute(0.90D,"Liquidity maintained (cash surplus to service debt) is sufficient only for 2-3 months of cumulative lean months payment; whereas payment is adversely structure"));
        riskSubFactorAttributes.add(new RiskAttribute(0.75D,"Large bullet payment with no firm policy on maintaining liquidity buffer. Back-ended payment, however no current payment issues envisaged"));
        riskSubFactorAttributes.add(new RiskAttribute(0.25D,"Monthly payment however no liquidity buffer maintained for lean season"));
        riskSubFactorAttributes.add(new RiskAttribute(0.50D,"Bullet payment and no sufficient liquidity buffer"));

        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS6 =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        repaymentStructureDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS6);
        financialRiskFactorDTO.addRiskSubFactorDTO(repaymentStructureDTO);



        riskComponentDTO.addRiskFactorDTO(financialRiskFactorDTO);

        return riskComponentDTO;

    }
}
