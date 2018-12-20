package com.pfs.riskmodel.Renewable.BuildPhase.PostProjectRisk;

import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskFactorDTO;
import com.pfs.riskmodel.dto.RiskSubFactorAttributeDTO;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;

/**
 * Created by sajeev on 19-Dec-18.
 */
public  class RPP_FinancialRiskRiskComponentDTO {
    /**********************************************************************************************************************
     *  Risk Component 1 : Financial Risk - 32%
     *  This has only two levels below and therefore the Risk Factor will be a dummy entry (Financial Risk Factor) with same name
     **********************************************************************************************************************/
    public static RiskComponentDTO getFinancialRiskComponentDTO () {

     //   RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        /**********************************************************************************************************************
         *  Risk Component 1 : Financial Risk - 32%
         *  This has only two levels below and therefore the Risk Factor will be a dummy entry (Financial Risk Factor) with same name
         **********************************************************************************************************************/

        //                       Risk Type 1 - Risk Component 1
        //  1                       Financial Risk
        RiskComponentDTO financialRiskComponentDTO = new RiskComponentDTO();
        financialRiskComponentDTO.setId(null);
        financialRiskComponentDTO.setItemNo(1);
        financialRiskComponentDTO.setDescription("Financial Risk");
        financialRiskComponentDTO.setWeightage(0.32D);
        financialRiskComponentDTO.setComputingMethodCode("01");
        financialRiskComponentDTO.setComputingMethodDescription("Weighted");
        financialRiskComponentDTO.setScoreTypeCode("01");
        financialRiskComponentDTO.setScoreTypeDescription("Normal");
        financialRiskComponentDTO.setScore(0D);



        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1
        //1.1                            Financial Risk -> Financial Risk Factor (DUMMY ENTRY)
        // *  Value Derived from the Risk Sub Factor is just passed on - Therefore Computing Method = EQUALS
        //                                       100%

        RiskFactorDTO financialRiskFactorDTO = new RiskFactorDTO();
        financialRiskFactorDTO.setId(null);
        financialRiskFactorDTO.setItemNo(1);
        financialRiskFactorDTO.setDescription("Financial Risk Factor");
        financialRiskFactorDTO.setWeightage(1.00);
        financialRiskFactorDTO.setComputingMethodCode("05");
        financialRiskFactorDTO.setComputingMethodDescription("Equals");
        financialRiskFactorDTO.setScoreTypeCode("01");
        financialRiskFactorDTO.setScoreTypeDescription("Normal");
        financialRiskFactorDTO.setScore(0D);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.1.1                      Financial Risk -> Financial Risk Factor - > Sensitivity to Base Case Revenue
        ///                                     15%
        RiskSubFactorDTO sensitivityToBusinessCaseRevenueRiskSubFactorDTO = new RiskSubFactorDTO();
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.setId(null);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.setItemNo(1);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.setDescription("Sensitivity to Base Case Revenue");
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.setWeightage(0.15D);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.setScore(0D);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.setScoreTypeCode("01");
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Financial Risk -> Financial Risk Factor - > Sensitivity to Base Case Revenue -> Attributes
        // 1.1.1 -> Four Attributes

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Less than 1");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(0D);


        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Equal to or above 1 but less than 1.05");
        riskSubFactorAttributeDTO2.setScore(2.00);
        riskSubFactorAttributeDTO2.setWeightage(0D);


        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Equal to or above 1.05 but less than 1.1");
        riskSubFactorAttributeDTO3.setScore(4.00);
        riskSubFactorAttributeDTO3.setWeightage(0D);


        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Equal to or above 1.1 but less than 1.2");
        riskSubFactorAttributeDTO4.setScore(6.00D);
        riskSubFactorAttributeDTO4.setWeightage(0D);


        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Equal to or above 1.2 but less than 1.4");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(0D);


        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(5);
        riskSubFactorAttributeDTO6.setDescription("Equal to or above 1.4");
        riskSubFactorAttributeDTO6.setScore(10.00D);
        riskSubFactorAttributeDTO6.setWeightage(0D);


        // Collect Risk Sub Factor Attributes
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);


        //                           Risk Component 1 - Risk Factor 1 - Risk SubFactor 2
        // 1.1.2                      Financial Risk -> Financial Risk Factor - > Minimum DSCR (Debt Service Coverage Ratio)
        ///                                     15%
        RiskSubFactorDTO minimumDSCRRiskSubFactorDTO = new RiskSubFactorDTO();
        minimumDSCRRiskSubFactorDTO.setId(null);
        minimumDSCRRiskSubFactorDTO.setItemNo(1);
        minimumDSCRRiskSubFactorDTO.setDescription("Minimum DSCR");
        minimumDSCRRiskSubFactorDTO.setWeightage(0.15D);
        minimumDSCRRiskSubFactorDTO.setScore(0D);
        minimumDSCRRiskSubFactorDTO.setScoreTypeCode("01");
        minimumDSCRRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //                       Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Financial Risk -> Financial Risk Factor - > Minimum DSCR (Debt Service Coverage Ratio) -> Attributes
        // 1.1.2 -> Six Attributes

        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Less than 1");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(0D);


        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Equal to or above 1 but less than 1.1");
        riskSubFactorAttributeDTO2.setScore(2.00);
        riskSubFactorAttributeDTO2.setWeightage(0D);


        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Equal to or above 1.1 but less than 1.2");
        riskSubFactorAttributeDTO3.setScore(4.00);
        riskSubFactorAttributeDTO3.setWeightage(0D);


        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Equal to or above 1.2 but less than 1.3");
        riskSubFactorAttributeDTO4.setScore(6.00D);
        riskSubFactorAttributeDTO4.setWeightage(0D);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Equal to or above 1.3 but less than 1.5");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(0D);


        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Equal to or above 1.5");
        riskSubFactorAttributeDTO6.setScore(10.00D);
        riskSubFactorAttributeDTO6.setWeightage(0D);

        // Collect Risk Sub Factor Attributes
        minimumDSCRRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        minimumDSCRRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        minimumDSCRRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        minimumDSCRRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        minimumDSCRRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        minimumDSCRRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);



        //                           Risk Component 1 - Risk Factor 1 - Risk SubFactor 3
        // 1.1.3                     Financial Risk -> Financial Risk Factor - > Adjusted Debt / Equity ratio
        ///                                     15%
        RiskSubFactorDTO adjustedDebtEquityRatioRiskSubFactorDTO = new RiskSubFactorDTO();
        adjustedDebtEquityRatioRiskSubFactorDTO.setId(null);
        adjustedDebtEquityRatioRiskSubFactorDTO.setItemNo(1);
        adjustedDebtEquityRatioRiskSubFactorDTO.setDescription("Adjust Debt/Equity Ratio");
        adjustedDebtEquityRatioRiskSubFactorDTO.setWeightage(0.20D);
        adjustedDebtEquityRatioRiskSubFactorDTO.setScore(0D);
        adjustedDebtEquityRatioRiskSubFactorDTO.setScoreTypeCode("01");
        adjustedDebtEquityRatioRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //                       Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Financial Risk -> Financial Risk Factor - >Adjusted Debt Equity Ratio------> Attributes
        // 1.1.3 -> Six Attributes

        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Less than 1");
        riskSubFactorAttributeDTO1.setScore(10D);
        riskSubFactorAttributeDTO1.setWeightage(0D);


        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription(">1.00 to 1.33");
        riskSubFactorAttributeDTO2.setScore(8.00);
        riskSubFactorAttributeDTO2.setWeightage(0D);


        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription(">1.33 to 2.33");
        riskSubFactorAttributeDTO3.setScore(6.00);
        riskSubFactorAttributeDTO3.setWeightage(0D);


        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription(">2.33 to 3.00");
        riskSubFactorAttributeDTO4.setScore(4.00D);
        riskSubFactorAttributeDTO4.setWeightage(0D);


        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription(">3. to 4.00");
        riskSubFactorAttributeDTO5.setScore(2.00D);
        riskSubFactorAttributeDTO5.setWeightage(0D);


        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("> 4.00");
        riskSubFactorAttributeDTO6.setScore(0.00D);
        riskSubFactorAttributeDTO6.setWeightage(0D);


        // Collect Risk Sub Factor Attributes
        adjustedDebtEquityRatioRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        adjustedDebtEquityRatioRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        adjustedDebtEquityRatioRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        adjustedDebtEquityRatioRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        adjustedDebtEquityRatioRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        adjustedDebtEquityRatioRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);


        //                           Risk Component 1 - Risk Factor 1 - Risk SubFactor 4
        // 1.1.4                    Financial Risk -> Financial Risk Factor - > Sensitivity to Project Cost – DSCR
        ///                                     15%
        RiskSubFactorDTO sensitivityToProjectCostDSCRRiskSubFactorDTO = new RiskSubFactorDTO();
        sensitivityToProjectCostDSCRRiskSubFactorDTO.setId(null);
        sensitivityToProjectCostDSCRRiskSubFactorDTO.setItemNo(1);
        sensitivityToProjectCostDSCRRiskSubFactorDTO.setDescription("Sensitivity to Project Cost – DSCR");
        sensitivityToProjectCostDSCRRiskSubFactorDTO.setWeightage(0.15D);
        sensitivityToProjectCostDSCRRiskSubFactorDTO.setScore(0D);
        sensitivityToProjectCostDSCRRiskSubFactorDTO.setScoreTypeCode("01");
        sensitivityToProjectCostDSCRRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //                       Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Financial Risk -> Financial Risk Factor - > Sensitivity to Project Cost – DSCR ------> Attributes
        // 1.1.4 -> Six Attributes

        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Less than 1");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Equal to or above 1 but less than 1.05");
        riskSubFactorAttributeDTO2.setScore(2.00);
        riskSubFactorAttributeDTO2.setWeightage(0D);


        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Equal to or above 1.05 but less than 1.1");
        riskSubFactorAttributeDTO3.setScore(4.00);
        riskSubFactorAttributeDTO3.setWeightage(0D);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Equal to or above 1.1 but less than 1.2");
        riskSubFactorAttributeDTO4.setScore(6.00D);
        riskSubFactorAttributeDTO4.setWeightage(0D);


        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Equal to or above 1.2 but less than 1.4");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(0D);


        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Equal to or above 1.4");
        riskSubFactorAttributeDTO6.setScore(10.00D);
        riskSubFactorAttributeDTO6.setWeightage(0D);

        // Collect Risk Sub Factor Attributes
        sensitivityToProjectCostDSCRRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        sensitivityToProjectCostDSCRRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        sensitivityToProjectCostDSCRRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        sensitivityToProjectCostDSCRRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        sensitivityToProjectCostDSCRRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        sensitivityToProjectCostDSCRRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);



        //                           Risk Component 1 - Risk Factor 1 - Risk SubFactor 4
        // 1.1.5                    Financial Risk -> Financial Risk Factor - > Degree of Exposure to Interest Rate Risk / Currency Risk
        ///                                     15%
        RiskSubFactorDTO degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO = new RiskSubFactorDTO();
        degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO.setId(null);
        degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO.setItemNo(1);
        degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO.setDescription("Degree of Exposure to Interest Rate Risk / Currency Risk");
        degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO.setWeightage(0.15D);
        degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO.setScore(0D);
        degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO.setScoreTypeCode("01");
        degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //                       Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Financial Risk -> Financial Risk Factor - >Degree of Exposure to Interest Rate Risk / Currency Risk ------> Attributes
        // 1.1.5 -> Six Attributes

        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("No exposure absolutely to IRR/CR. All position are hedged fully");
        riskSubFactorAttributeDTO1.setScore(10D);
        riskSubFactorAttributeDTO1.setWeightage(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Minimal exposure to IRR/CR. Most of the positions are hedged");
        riskSubFactorAttributeDTO2.setScore(8.00);
        riskSubFactorAttributeDTO2.setWeightage(0D);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Exposure to IRR/CR is small");
        riskSubFactorAttributeDTO3.setScore(6.00);
        riskSubFactorAttributeDTO3.setWeightage(0D);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription(">Average exposure to IRR/CR");
        riskSubFactorAttributeDTO4.setScore(4.00D);
        riskSubFactorAttributeDTO4.setWeightage(0D);


        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("High Risk exposure to IRR/CR");
        riskSubFactorAttributeDTO5.setScore(2.00D);
        riskSubFactorAttributeDTO5.setWeightage(0D);


        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Extremely High exposure to IRR/CR");
        riskSubFactorAttributeDTO6.setScore(0.00D);
        riskSubFactorAttributeDTO6.setWeightage(0D);

        // Collect Risk Sub Factor Attributes
        degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);



        //                           Risk Component 1 - Risk Factor 1 - Risk SubFactor 4
        // 1.1.6                    Financial Risk -> Financial Risk Factor - > Internal Rate of Return
        ///                                     15%
        RiskSubFactorDTO internalRateOfReturnRiskSubFactorDTO = new RiskSubFactorDTO();
        internalRateOfReturnRiskSubFactorDTO.setId(null);
        internalRateOfReturnRiskSubFactorDTO.setItemNo(1);
        internalRateOfReturnRiskSubFactorDTO.setDescription("Internal Rate of Return - Build Phase");
        internalRateOfReturnRiskSubFactorDTO.setWeightage(0.20D);
        internalRateOfReturnRiskSubFactorDTO.setScore(0D);
        internalRateOfReturnRiskSubFactorDTO.setScoreTypeCode("01");
        internalRateOfReturnRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //                       Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Financial Risk -> Financial Risk Factor - >Internal Rate of Return - Build Phase -> Attributes
        // 1.1.6 -> Six Attributes

        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Less than 10%");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Equal to or above 10% but less than 13.5%");
        riskSubFactorAttributeDTO2.setScore(2.00);
        riskSubFactorAttributeDTO2.setWeightage(0D);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Equal to or above 13.5% but less than 17%");
        riskSubFactorAttributeDTO3.setScore(4.00);
        riskSubFactorAttributeDTO3.setWeightage(0D);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Equal to or above 17% but less than 20.5%");
        riskSubFactorAttributeDTO4.setScore(6.00D);
        riskSubFactorAttributeDTO4.setWeightage(0D);


        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Equal to or above 20.5% but less than 24%");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(0D);


        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Equal to or above 24%");
        riskSubFactorAttributeDTO6.setScore(10.00D);
        riskSubFactorAttributeDTO6.setWeightage(0D);

        // Collect Risk Sub Factor Attributes
        internalRateOfReturnRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        internalRateOfReturnRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        internalRateOfReturnRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        internalRateOfReturnRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        internalRateOfReturnRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        internalRateOfReturnRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);




        //                           Risk Component 1 - Risk Factor 1 - Risk SubFactor 7
        // 1.1.7                    Financial Risk -> Financial Risk Factor - > Repayment Structure
        ///

        RiskSubFactorDTO repaymentStructureSubFactorDTO = new RiskSubFactorDTO();
        repaymentStructureSubFactorDTO.setId(null);
        repaymentStructureSubFactorDTO.setItemNo(1);
        repaymentStructureSubFactorDTO.setDescription("Repayment Structure");
        repaymentStructureSubFactorDTO.setWeightage(0.00D);
        repaymentStructureSubFactorDTO.setScore(0D);
        repaymentStructureSubFactorDTO.setScoreTypeCode("02");
        repaymentStructureSubFactorDTO.setScoreTypeDescription("Deflator");




        //                       Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Financial Risk -> Financial Risk Factor - > Repayment Structure -> Attributes
        // 1.1.7 -> Five Attributes

        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Payment structure and liquidity maintained (cash surplus to service debt) is sufficient for cumulative lean months payment");
        riskSubFactorAttributeDTO1.setScore(1.00);
        riskSubFactorAttributeDTO1.setWeightage(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Liquidity maintained (cash surplus to service debt) is sufficient only for 2-3 months of cumulative lean months payment; whereas payment is adversely structured");
        riskSubFactorAttributeDTO2.setScore(0.90);
        riskSubFactorAttributeDTO2.setWeightage(0D);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Large bullet payment with no firm policy on maintaining liquidity buffer. Back-ended payment, however no current payment issues envisaged");
        riskSubFactorAttributeDTO3.setScore(0.75);
        riskSubFactorAttributeDTO3.setWeightage(0D);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Monthly payment however no liquidity buffer maintained for lean season");
        riskSubFactorAttributeDTO4.setScore(0.25);
        riskSubFactorAttributeDTO4.setWeightage(0D);


        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Bullet payment and no sufficient liquidity buffer");
        riskSubFactorAttributeDTO5.setScore(0.50);
        riskSubFactorAttributeDTO5.setWeightage(0D);

        // Collect Risk Sub Factor Attributes
        repaymentStructureSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        repaymentStructureSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        repaymentStructureSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        repaymentStructureSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        repaymentStructureSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        repaymentStructureSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);


        //Collect RiskFactor
        financialRiskFactorDTO.addRiskSubFactorDTO(sensitivityToBusinessCaseRevenueRiskSubFactorDTO);
        financialRiskFactorDTO.addRiskSubFactorDTO(minimumDSCRRiskSubFactorDTO);
        financialRiskFactorDTO.addRiskSubFactorDTO(adjustedDebtEquityRatioRiskSubFactorDTO);
        financialRiskFactorDTO.addRiskSubFactorDTO(sensitivityToProjectCostDSCRRiskSubFactorDTO);
        financialRiskFactorDTO.addRiskSubFactorDTO(degreeOfExposureToIRRAndCurrencyRiskRiskSubFactorDTO);
        financialRiskFactorDTO.addRiskSubFactorDTO(internalRateOfReturnRiskSubFactorDTO);
        financialRiskFactorDTO.addRiskSubFactorDTO(repaymentStructureSubFactorDTO);

        //Collect Risk Factors
        financialRiskComponentDTO.addRiskFactorDTO(financialRiskFactorDTO);
        return  financialRiskComponentDTO;

    }
}
