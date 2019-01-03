package com.pfs.riskmodel.ModelTemplates.Renewable.RiskComponents;

import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskFactorDTO;
import com.pfs.riskmodel.dto.RiskSubFactorAttributeDTO;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;

/**
 * Created by sajeev on 19-Dec-18.
 */
public class RPP_BusinessRiskComponentDTO {
    /**********************************************************************************************************************
     *  Risk Component 1 : Business Risk - 32%
     *  This has only two levels below and therefore the Risk Factor will be a dummy entry (Industry Risk Factor) with same name
     **********************************************************************************************************************/


    public static RiskComponentDTO getBusinessRiskComponentDTO() {

        //RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        /**********************************************************************************************************************
         *  Risk Component 1 : Business Risk - 32%
         **********************************************************************************************************************/

        //                       Risk Type 1 - Risk Component 1
        //  1                       Business Risk
        RiskComponentDTO businessRiskComponentDTO = new RiskComponentDTO();
        businessRiskComponentDTO.setId(null);
        businessRiskComponentDTO.setItemNo(4);
        businessRiskComponentDTO.setDescription("Business Risk");
        businessRiskComponentDTO.setWeightage(0.32);
        businessRiskComponentDTO.setComputingMethodCode("01");
        businessRiskComponentDTO.setComputingMethodDescription("Weighted");
        businessRiskComponentDTO.setScoreTypeCode("01");
        businessRiskComponentDTO.setScoreTypeDescription("Normal");
        businessRiskComponentDTO.setScore(0D);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1
        //1.1                            Business Risk -> Market Position Risk  Factor
        //                                       70%

        RiskFactorDTO marketPositionRiskFactorDTO = new RiskFactorDTO();
        marketPositionRiskFactorDTO.setId(null);
        marketPositionRiskFactorDTO.setItemNo(1);
        marketPositionRiskFactorDTO.setDescription("Market Position Risk Factor");
        marketPositionRiskFactorDTO.setWeightage(0.70);
        marketPositionRiskFactorDTO.setComputingMethodCode("01");
        marketPositionRiskFactorDTO.setComputingMethodDescription("Weighted");
        marketPositionRiskFactorDTO.setScoreTypeCode("01");
        marketPositionRiskFactorDTO.setScoreTypeDescription("Normal");
        marketPositionRiskFactorDTO.setScore(0D);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.1.1                      Business Risk -> Market Position Factor - >  Offtake Risk and Sales Composition
        ///                                     27.80%
        RiskSubFactorDTO offTakeRiskAndSalesSubFactorDTO = new RiskSubFactorDTO();
        offTakeRiskAndSalesSubFactorDTO.setId(null);
        offTakeRiskAndSalesSubFactorDTO.setItemNo(1);
        offTakeRiskAndSalesSubFactorDTO.setDescription("Offtake Risk and Sales Composition");
        offTakeRiskAndSalesSubFactorDTO.setWeightage(0.278D);
        offTakeRiskAndSalesSubFactorDTO.setScore(0D);
        offTakeRiskAndSalesSubFactorDTO.setScoreTypeCode("01");
        offTakeRiskAndSalesSubFactorDTO.setScoreTypeDescription("Normal");

        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Industry Risk -> Business Risk Factor - >  Offtake Risk and Sales -> Attributes
        // 1.1.1 -> Six Attributes
        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("No arrangements with any prospective buyers. Extremely high exposure to market risk.");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Short/Medium term PPAs on competitive bidding to SEBs, Group captive consumers or other industrial consumers");
        riskSubFactorAttributeDTO2.setScore(2.00);
        riskSubFactorAttributeDTO2.setWeightage(00D);riskSubFactorAttributeDTO2.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Long term PPA with 3rd party (open access)");
        riskSubFactorAttributeDTO3.setScore(4.00);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Long term PPA proposed /signed in group captive scheme");
        riskSubFactorAttributeDTO4.setScore(6.00D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Long term PPA not signed but proposed to be signed with State Discom and COD to be achieved within control period of PPA");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(00D);riskSubFactorAttributeDTO5.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Long term PPA signed with State Discoms, NTPC, SEC, Govt. nominated agencies, etc");
        riskSubFactorAttributeDTO6.setScore(10.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);


        offTakeRiskAndSalesSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        offTakeRiskAndSalesSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        offTakeRiskAndSalesSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        offTakeRiskAndSalesSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        offTakeRiskAndSalesSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        offTakeRiskAndSalesSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.1.2                      Business Risk -> Market Position Factor - >  Counterparty Risk - Renewable
        ///                                     27.80%
        RiskSubFactorDTO counterPartySubFactorDTO = new RiskSubFactorDTO();
        counterPartySubFactorDTO.setId(null);
        counterPartySubFactorDTO.setItemNo(2);
        counterPartySubFactorDTO.setDescription("Counterparty Risk - Renewable");
        counterPartySubFactorDTO.setWeightage(0.278D);
        counterPartySubFactorDTO.setScore(0D);
        counterPartySubFactorDTO.setScoreTypeCode("01");
        counterPartySubFactorDTO.setScoreTypeDescription("Normal");


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Business Risk  - >  Market Position Factor -> Counterparty Risk - Renewable -> Attributes
        // 1.1.2 -> Six Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("No off taker as on date (Off taker is Group Captive/Open access consumers) OR Over dues more than 210 days (Off taker is SEB/NTPC/SECI/Other Govt. nominated agency)");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Non listed off taker & non-investment grade borrower (Off taker is Group Captive/Open access consumers) OR Over dues not more than 180 days but less than 210 days (Off taker is SEB/NTPC/SECI/Other Govt. nominated agency)");
        riskSubFactorAttributeDTO2.setScore(2.00);
        riskSubFactorAttributeDTO2.setWeightage(00D);riskSubFactorAttributeDTO2.setIsSelected(false);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Non Listed off taker & long term credit rating not yet assigned (Off taker is Group Captive/Open access consumers) OR Over dues not more than 120 days but less than 90 days (Off taker is SEB/NTPC/SECI/Other Govt. nominated agency)");
        riskSubFactorAttributeDTO3.setScore(4.00);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Non listed off taker & long term investment credit rating not below BBB+ (Off taker is Group Captive/Open access consumers) OR Over dues not more than 90 days but less than 30 days (Off taker is SEB/NTPC/SECI/Other Govt. nominated agency)");
        riskSubFactorAttributeDTO4.setScore(6.00D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Listed off taker & long term investment credit rating not below BBB+ (quantum < 50% of project capacity): Off taker is Group Captive/Open access consumers OR Over dues not more than 30 days but less than 15 days (Off taker is SEB/NTPC/SECI/Other Govt. nominated agency)");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(00D);riskSubFactorAttributeDTO5.setIsSelected(false);


        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Listed off taker & long term investment credit rating not below BBB+ (quantum ≥ 50% of project capacity): Off taker is Group Captive/Open access consumers OR Over dues not more than 15 days (Off taker is SEB/NTPC/SECI/Other Govt. nominated agency)");
        riskSubFactorAttributeDTO6.setScore(10.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);

        counterPartySubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        counterPartySubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        counterPartySubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        counterPartySubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        counterPartySubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        counterPartySubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.1.3                     Business Risk -> Market Risk Factor - >  Cost of generation (per unit cost)
        ///                                     22.20%
        RiskSubFactorDTO costOfGenerationSubFactorDTO = new RiskSubFactorDTO();
        costOfGenerationSubFactorDTO.setId(null);
        costOfGenerationSubFactorDTO.setItemNo(3);
        costOfGenerationSubFactorDTO.setDescription("Cost of generation (per unit cost)");
        costOfGenerationSubFactorDTO.setWeightage(0.222D);
        costOfGenerationSubFactorDTO.setScore(0D);
        costOfGenerationSubFactorDTO.setScoreTypeCode("01");
        costOfGenerationSubFactorDTO.setScoreTypeDescription("Normal");


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Business Risk -> Market Risk Factor - >  Cost of generation (per unit cost) -> Attributes
        // 1.1.3 -> Six Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Cost is extremely non-competitive and amongst the most expensive in the industry");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Cost is non-competitive and the generation cost falls amongst the expensive power plant projects in the industry.");
        riskSubFactorAttributeDTO2.setScore(2.00);
        riskSubFactorAttributeDTO2.setWeightage(00D);riskSubFactorAttributeDTO2.setIsSelected(false);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Generation is not competitive and higher than the average industry cost.");
        riskSubFactorAttributeDTO3.setScore(4.00);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Generation cost is moderately competitive and within the range of average industry cost");
        riskSubFactorAttributeDTO4.setScore(6.00D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Generation cost is competitive and is lower by 10-20% than the industry standards");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(00D);riskSubFactorAttributeDTO5.setIsSelected(false);

        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("The generation cost is extremely competitive and amongst the best in the industry-Lower by at least 20%");
        riskSubFactorAttributeDTO6.setScore(10.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);

        costOfGenerationSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        costOfGenerationSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        costOfGenerationSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        costOfGenerationSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        costOfGenerationSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        costOfGenerationSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.1.4                      Business Risk -> Market Risk Factor - >  Regulatory Risk - Renewable
        ///                                     27.80%
        RiskSubFactorDTO regulatoryRiskRenewable = new RiskSubFactorDTO();
        regulatoryRiskRenewable.setId(null);
        regulatoryRiskRenewable.setItemNo(4);
        regulatoryRiskRenewable.setDescription("Regulatory Risk - Renewable");
        regulatoryRiskRenewable.setWeightage(0.222D);
        regulatoryRiskRenewable.setScore(0D);
        regulatoryRiskRenewable.setScoreTypeCode("01");
        regulatoryRiskRenewable.setScoreTypeDescription("Normal");

        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Business Risk -> Market Risk Factor - > Regulatory Risk - Renewable -> Attributes
        // 1.1.4 -> Six Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Tariff determined through competitive bidding/preferential and project’s CoD is beyond the control period of tariff order/PPA; Impact on tariff is significant");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("" +
                "Tariff determined through competitive bidding/preferential and project’s CoD is beyond the control period of tariff order/PPA; Impact on tariff is not significant");
        riskSubFactorAttributeDTO2.setScore(2.00);
        riskSubFactorAttributeDTO2.setWeightage(00D);riskSubFactorAttributeDTO2.setIsSelected(false);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Tariff determined is through preferential tariff and more than 25% of APPC of the state");
        riskSubFactorAttributeDTO3.setScore(4.00);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Tariff determined through competitive bidding and more than 25% of APPC of the state");
        riskSubFactorAttributeDTO4.setScore(6.00D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Tariff determined is through preferential tariff and less than APPC of the state");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(00D);riskSubFactorAttributeDTO5.setIsSelected(false);

        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Tariff determined through competitive bidding and less than average power procurement cost (APPC) of the state");
        riskSubFactorAttributeDTO6.setScore(10.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);

        regulatoryRiskRenewable.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        regulatoryRiskRenewable.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        regulatoryRiskRenewable.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        regulatoryRiskRenewable.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        regulatoryRiskRenewable.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        regulatoryRiskRenewable.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);

        // Collect Risk Sub Factor Attributes

        //Collect Risk Factors
        marketPositionRiskFactorDTO.addRiskSubFactorDTO(offTakeRiskAndSalesSubFactorDTO);
        marketPositionRiskFactorDTO.addRiskSubFactorDTO(counterPartySubFactorDTO);
        marketPositionRiskFactorDTO.addRiskSubFactorDTO(costOfGenerationSubFactorDTO);
        marketPositionRiskFactorDTO.addRiskSubFactorDTO(regulatoryRiskRenewable);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1
        //1.2                            Business Risk -> Operating Efficiency Risk  Factor
        //                                       30%

        RiskFactorDTO operatingEfficiencyRiskFactorDTO = new RiskFactorDTO();
        operatingEfficiencyRiskFactorDTO.setId(null);
        operatingEfficiencyRiskFactorDTO.setItemNo(2);
        operatingEfficiencyRiskFactorDTO.setDescription("Operating Efficiency Risk Factor");
        operatingEfficiencyRiskFactorDTO.setWeightage(0.30);
        operatingEfficiencyRiskFactorDTO.setComputingMethodCode("01");
        operatingEfficiencyRiskFactorDTO.setComputingMethodDescription("Weighted");
        operatingEfficiencyRiskFactorDTO.setScoreTypeCode("01");
        operatingEfficiencyRiskFactorDTO.setScoreTypeDescription("Normal");
        operatingEfficiencyRiskFactorDTO.setScore(0D);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.2.1       Business  Risk -> Operating Efficiency Risk Factor - >  Operations and Maintenance Risk        ///                                     27.80%
        RiskSubFactorDTO operationsAndMaintenanceRiskSubFactorDTO = new RiskSubFactorDTO();
        operationsAndMaintenanceRiskSubFactorDTO.setId(null);
        operationsAndMaintenanceRiskSubFactorDTO.setItemNo(1);
        operationsAndMaintenanceRiskSubFactorDTO.setDescription("Operations and Maintenance Risk");
        operationsAndMaintenanceRiskSubFactorDTO.setWeightage(0.15D);
        operationsAndMaintenanceRiskSubFactorDTO.setScore(0D);
        operationsAndMaintenanceRiskSubFactorDTO.setScoreTypeCode("01");
        operationsAndMaintenanceRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //             Business  Risk -> Operating Efficiency Risk Factor - >  Operations and Maintenance Risk -> Attributes
        // 1.2.1 -> Six Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Highly Reputed O&M contractors with excellent track record of operating projects of similar scale and elsewhere");
        riskSubFactorAttributeDTO1.setScore(10D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Reputed O& M contractors with very good track record of operating projects of similar type");
        riskSubFactorAttributeDTO2.setScore(8.00);
        riskSubFactorAttributeDTO2.setWeightage(00D);riskSubFactorAttributeDTO2.setIsSelected(false);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Contractors risk is minimal as their past track record is good");
        riskSubFactorAttributeDTO3.setScore(6.00);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("The contractors experience is insufficient for the project");
        riskSubFactorAttributeDTO4.setScore(4.00D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);


        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("No prior experiences of contractors");
        riskSubFactorAttributeDTO5.setScore(2.00D);
        riskSubFactorAttributeDTO5.setWeightage(00D);riskSubFactorAttributeDTO5.setIsSelected(false);


        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Extremely high risk as the O&M contractors have bad reputation");
        riskSubFactorAttributeDTO6.setScore(0.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);

        operationsAndMaintenanceRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        operationsAndMaintenanceRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        operationsAndMaintenanceRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        operationsAndMaintenanceRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        operationsAndMaintenanceRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        operationsAndMaintenanceRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);


        //Collect Risk Sub Factors
        operatingEfficiencyRiskFactorDTO.addRiskSubFactorDTO(operationsAndMaintenanceRiskSubFactorDTO);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1
        //1.2                            Business Risk -> Operating Efficiency -> Key Resources Renewable Risk  Factor
        //                                       85%

        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.2.1       Business  Risk -> Key Resources Risk Factor - >  Operations and Maintenance Risk        ///                                     27.80%
        RiskSubFactorDTO keyResourcesRiskSubFactorDTO = new RiskSubFactorDTO();
        keyResourcesRiskSubFactorDTO.setId(null);
        keyResourcesRiskSubFactorDTO.setItemNo(2);
        keyResourcesRiskSubFactorDTO.setDescription("Key Resources Risk");
        keyResourcesRiskSubFactorDTO.setWeightage(0.85D);
        keyResourcesRiskSubFactorDTO.setScore(0D);
        keyResourcesRiskSubFactorDTO.setScoreTypeCode("01");
        keyResourcesRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //             Business  Risk -> Key Resources Risk Factor - >  Key Resources Risk -> Attributes
        // 1.2.1 -> Six Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Operational project – Availability of key resource is highly favourable and PLF significantly higher than base case.");
        riskSubFactorAttributeDTO1.setScore(10D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);


        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Operational project - Availability of key resource is favourable and PLF is higher than base case.");
        riskSubFactorAttributeDTO2.setScore(7.00);
        riskSubFactorAttributeDTO2.setWeightage(00D);riskSubFactorAttributeDTO2.setIsSelected(false);


        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Operational project - Availability of key resource is moderately favourable and PLF is as per base case.");
        riskSubFactorAttributeDTO3.setScore(3.00);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Operational project - Availability of key resource is below average and PLF is less than base case.");
        riskSubFactorAttributeDTO4.setScore(0.00D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Under construction project - Availability of key resource is expected to be highly favourable and generation assessment is based on more than 2 years resource data at site");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(00D);riskSubFactorAttributeDTO5.setIsSelected(false);

        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Under construction project - Availability of key resource is expected to be moderately favourable generation assessment based on 1 to 2 year resource data at site");
        riskSubFactorAttributeDTO6.setScore(4.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);


        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO7 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO7.setId(null);
        riskSubFactorAttributeDTO7.setItemNo(6);
        riskSubFactorAttributeDTO7.setDescription("Under construction project - Availability of key resource is expected to be below average and generation assessment is based on 0 to 1 year resource data at site");
        riskSubFactorAttributeDTO7.setScore(0.00D);
        riskSubFactorAttributeDTO7.setWeightage(00D);riskSubFactorAttributeDTO7.setIsSelected(false);


        keyResourcesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        keyResourcesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        keyResourcesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        keyResourcesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        keyResourcesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        keyResourcesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);
        keyResourcesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO7);


        operatingEfficiencyRiskFactorDTO.addRiskSubFactorDTO(keyResourcesRiskSubFactorDTO);


        // Collect Risk Factors

        businessRiskComponentDTO.addRiskFactorDTO(operatingEfficiencyRiskFactorDTO);
        businessRiskComponentDTO.addRiskFactorDTO(marketPositionRiskFactorDTO);

        return businessRiskComponentDTO;

    }
}
