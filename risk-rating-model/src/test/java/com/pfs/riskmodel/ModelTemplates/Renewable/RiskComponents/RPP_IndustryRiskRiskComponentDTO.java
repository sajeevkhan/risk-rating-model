package com.pfs.riskmodel.ModelTemplates.Renewable.RiskComponents;

import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskFactorDTO;
import com.pfs.riskmodel.dto.RiskSubFactorAttributeDTO;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;

/**
 * Created by sajeev on 19-Dec-18.
 */
public  class RPP_IndustryRiskRiskComponentDTO {


    public static RiskComponentDTO getIndustryRiskComponentDTO () {

        //RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        /**********************************************************************************************************************
         *  Risk Component 1 : Industry Risk - 14%
         *  This has only two levels below and therefore the Risk Factor will be a dummy entry (Industry Risk Factor) with same name
         **********************************************************************************************************************/

        //                       Risk Type 1 - Risk Component 1
        //  1                       Industry Risk
        RiskComponentDTO industryRiskComponentDTO = new RiskComponentDTO();
        industryRiskComponentDTO.setId(null);
        industryRiskComponentDTO.setItemNo(2);
        industryRiskComponentDTO.setDescription("Industry Risk");
        industryRiskComponentDTO.setWeightage(0.14);
        industryRiskComponentDTO.setComputingMethodCode("05");
        industryRiskComponentDTO.setComputingMethodDescription("Equals");
        industryRiskComponentDTO.setScoreTypeCode("01");
        industryRiskComponentDTO.setScoreTypeDescription("Normal");
        industryRiskComponentDTO.setScore(0D);

        industryRiskComponentDTO.setIsApplicable(true);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1
        //1.1                            Industry Risk -> Industry Risk  Factor (DUMMY ENTRY)
        // *  Value Derived from the Risk Sub Factor is just passed on - Therefore Computing Method = EQUALS
        //                                       100%

        RiskFactorDTO industryRiskFactorDTO = new RiskFactorDTO();
        industryRiskFactorDTO.setId(null);
        industryRiskFactorDTO.setItemNo(1);
        industryRiskFactorDTO.setDescription("Industry Risk Factor");
        industryRiskFactorDTO.setWeightage(1.00);
        industryRiskFactorDTO.setComputingMethodCode("01");
        industryRiskFactorDTO.setComputingMethodDescription("Weighted");
        industryRiskFactorDTO.setScoreTypeCode("01");
        industryRiskFactorDTO.setScoreTypeDescription("Normal");
        industryRiskFactorDTO.setScore(0D);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.1.1                      Industry Risk -> Industry Risk Factor - >  Demand Supply gap
        ///                                     25%
        RiskSubFactorDTO demandSupplyGapRiskSubFactorDTO = new RiskSubFactorDTO();
        demandSupplyGapRiskSubFactorDTO.setId(null);
        demandSupplyGapRiskSubFactorDTO.setItemNo(1);
        demandSupplyGapRiskSubFactorDTO.setDescription("Demand Supply Gap");
        demandSupplyGapRiskSubFactorDTO.setWeightage(0.25D);
        demandSupplyGapRiskSubFactorDTO.setScore(0D);
        demandSupplyGapRiskSubFactorDTO.setScoreTypeCode("01");
        demandSupplyGapRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Industry Risk -> Industry Risk Factor - >  Demand Supply gap -> Attributes
        // 1.1.1 -> Six Attributes
        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Extremely favorable demand supply gap. Current production well below the estimated demand. Demand supply gap likely to remain insulated from recessionary trends. Growth potential in foreseeable future is high.");
        riskSubFactorAttributeDTO1.setScore(10D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Highly favorable demand-supply gap. Relative good growth potential in future.");
        riskSubFactorAttributeDTO2.setScore(8.00);
        riskSubFactorAttributeDTO2.setWeightage(00D);riskSubFactorAttributeDTO2.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Past growth rates relatively high and stable. Positive demand-supply gap scenario. Relatively insulated from economic recession. Favorable growth rate likely to continue in the medium term.");
        riskSubFactorAttributeDTO3.setScore(6.00);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Industry characterized by derived demand -strong linkages with overall economic growth. Marginal demand supply gap/ slight overcapacity situation likely to restrict medium term growth, despite positive long term growth prospects");
        riskSubFactorAttributeDTO4.setScore(4.00D);
        riskSubFactorAttributeDTO4.setWeightage(0D);riskSubFactorAttributeDTO4.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Serious oversupply situation in industry. Production levels have been stagnant/declining for an extended period. Installed capacities likely to remain in excess of estimated demand over the medium term. Reversal in oversupply situation in medium term is unlikely");
        riskSubFactorAttributeDTO5.setScore(2.00D);
        riskSubFactorAttributeDTO5.setWeightage(00D);riskSubFactorAttributeDTO5.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Demand showing a clearly declining trend. Industry likely to die out within a few years");
        riskSubFactorAttributeDTO6.setScore(0.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);

        demandSupplyGapRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        demandSupplyGapRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        demandSupplyGapRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        demandSupplyGapRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        demandSupplyGapRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        demandSupplyGapRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.1.2                      Industry Risk -> Industry Risk Factor - >  Impact of Government directives
        ///                                     50%
        RiskSubFactorDTO impactOfGovtDirectivesRiskSubFactorDTO = new RiskSubFactorDTO();
        impactOfGovtDirectivesRiskSubFactorDTO.setId(null);
        impactOfGovtDirectivesRiskSubFactorDTO.setItemNo(2);
        impactOfGovtDirectivesRiskSubFactorDTO.setDescription("Impact of Government directives / Regulations");
        impactOfGovtDirectivesRiskSubFactorDTO.setWeightage(0.50D);
        impactOfGovtDirectivesRiskSubFactorDTO.setScore(0D);
        impactOfGovtDirectivesRiskSubFactorDTO.setScoreTypeCode("01");
        impactOfGovtDirectivesRiskSubFactorDTO.setScoreTypeDescription("Normal");//



        //        Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Industry Risk -> Industry Risk Factor - >  Impact of Government directives / Regulations -> Attributes
        // 1.1.2 -> Six Attributes

         riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Policy is highly favourable and unambiguous, with certain minimum return being made available to the industry. Favourable government policy likely to continue in the foreseeable future");
        riskSubFactorAttributeDTO1.setScore(10D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);


        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Favourable Policy, which has positive impact on industry. No change in the favourable policy is expected");
        riskSubFactorAttributeDTO2.setScore(8.00);
        riskSubFactorAttributeDTO2.setWeightage(00D);riskSubFactorAttributeDTO2.setIsSelected(false);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("A mix of favourable and not so favourable policies. Slight impact of policy on the profitability");
        riskSubFactorAttributeDTO3.setScore(6.00);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("The existing government policies are not significantly favourable/ unfavourable for the industry. Profitability is not particularly influenced by existing/ foreseen regulatory measures");
        riskSubFactorAttributeDTO4.setScore(4.00D);
        riskSubFactorAttributeDTO4.setWeightage(0D);riskSubFactorAttributeDTO4.setIsSelected(false);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Government policy has a significantly negative influence, in the form of high excise burden, inverted import duty structure, unviable price regulation, etc.");
        riskSubFactorAttributeDTO5.setScore(2.00D);
        riskSubFactorAttributeDTO5.setWeightage(00D);riskSubFactorAttributeDTO5.setIsSelected(false);

        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Government policy towards industry is extremely unfavourable");
        riskSubFactorAttributeDTO6.setScore(0.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);



        // Collect Risk Sub Factor Attributes
        impactOfGovtDirectivesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        impactOfGovtDirectivesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        impactOfGovtDirectivesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        impactOfGovtDirectivesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        impactOfGovtDirectivesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        impactOfGovtDirectivesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.1.3                      Industry Risk -> Industry Risk Factor - >  Extent of competition
        ///                                     25%
        RiskSubFactorDTO extentOfCompetetionRiskSubFactorDTO = new RiskSubFactorDTO();
        extentOfCompetetionRiskSubFactorDTO.setId(null);
        extentOfCompetetionRiskSubFactorDTO.setItemNo(3);
        extentOfCompetetionRiskSubFactorDTO.setDescription("Extent of Competition");
        extentOfCompetetionRiskSubFactorDTO.setWeightage(0.25D);
        extentOfCompetetionRiskSubFactorDTO.setScore(0D);
        extentOfCompetetionRiskSubFactorDTO.setScoreTypeCode("01");
        extentOfCompetetionRiskSubFactorDTO.setScoreTypeDescription("Normal");


         // 1.1.3 -> Six Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("The industry has a monopoly structure, with the prospect of new entrants in the medium term being unlikely");
        riskSubFactorAttributeDTO1.setScore(10D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);


        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Oligopoly structure. High chance of the structure to be the same or move into a monopoly structure");
        riskSubFactorAttributeDTO2.setScore(8.00);
        riskSubFactorAttributeDTO2.setWeightage(00D);riskSubFactorAttributeDTO2.setIsSelected(false);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Industry is characterized by a few large players accounting for the bulk of market share. Capital investment involved is likely to discourage significant increase in competition in the medium term. Absence of serious threat from imports");
        riskSubFactorAttributeDTO3.setScore(6.00);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);


        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Industry has a fairly fragmented structure. Moderate entry barriers in the form of technology/ capital investment");
        riskSubFactorAttributeDTO4.setScore(4.00D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Highly fragmented industry. Processes are very easily replicable leading to presence of large, cost-competitive unorganized sector/ Significantly lower cost of imports render domestic producers unviable");
        riskSubFactorAttributeDTO5.setScore(2.00D);
        riskSubFactorAttributeDTO5.setWeightage(00D);riskSubFactorAttributeDTO5.setIsSelected(false);

        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Extremely competitive industry, with a near absence of entry barriers, in the form of investment/ technology etc. No player is capable of building a significant market share in the industry. Majority of players in the industry are loss making");
        riskSubFactorAttributeDTO6.setScore(0.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);


        // Collect Risk Sub Factor Attributes
        extentOfCompetetionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        extentOfCompetetionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        extentOfCompetetionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        extentOfCompetetionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        extentOfCompetetionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        extentOfCompetetionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);

        //Collect Risk Factors
        industryRiskFactorDTO.addRiskSubFactorDTO(demandSupplyGapRiskSubFactorDTO);
        industryRiskFactorDTO.addRiskSubFactorDTO(impactOfGovtDirectivesRiskSubFactorDTO);
        industryRiskFactorDTO.addRiskSubFactorDTO(extentOfCompetetionRiskSubFactorDTO);

        // Collect Risk Factors
        industryRiskComponentDTO.addRiskFactorDTO(industryRiskFactorDTO);

        return  industryRiskComponentDTO;

    }
}
