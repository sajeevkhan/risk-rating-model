package com.pfs.riskmodel.ModelTemplates.Renewable.RiskComponents;

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
public class ROP_IndustryRiskDTO {

    public RiskComponentDTO getIndustryRiskDTO() {


        RiskAttribute riskAttribute ;
        List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
        RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();



        // 2.0 Industry Risk - 16%

        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();

        riskComponentDTO.setId(null);
        riskComponentDTO.setItemNo(3);
        riskComponentDTO.setComputingMethodCode("05");
        riskComponentDTO.setComputingMethodDescription("Equals");
        riskComponentDTO.setDescription("Industry Risk");
        riskComponentDTO.setScoreTypeDescription("01");
        riskComponentDTO.setScoreTypeCode("01");
        riskComponentDTO.setScoreTypeDescription("Normal");

        riskComponentDTO.setScore(0D);
        riskComponentDTO.setWeightage(0.16D);

        riskComponentDTO.setIsApplicable(true);

        /*
         --------------------------    Risk Factor 1
            2.1 Industry Risk Factor
                 No Concrete Risk Factors - Therefore a dummy Risk Factor called "Industry Risk Factor" is added
         */

        RiskFactorDTO industryRiskFactorDTO = new RiskFactorDTO();
        industryRiskFactorDTO.setId(null);
        industryRiskFactorDTO.setItemNo(1);
        industryRiskFactorDTO.setDescription("Industry Risk Factor");
        industryRiskFactorDTO.setWeightage(1.000);
        industryRiskFactorDTO.setScore(0D);
        industryRiskFactorDTO.setScoreTypeCode("01");
        industryRiskFactorDTO.setScoreTypeDescription("Normal");
        industryRiskFactorDTO.setComputingMethodCode("01");
        industryRiskFactorDTO.setComputingMethodDescription("Weighted");


        //  ------------- Risk Sub Factor 1
        // 2.1.1 Demand Supply gap 25%
        RiskSubFactorDTO demandSupplyGapDTO = new RiskSubFactorDTO();
        demandSupplyGapDTO.setId(null);
        demandSupplyGapDTO.setItemNo(1);
        demandSupplyGapDTO.setDescription("Demand Supply Gap");
        demandSupplyGapDTO.setWeightage(0.25);
        demandSupplyGapDTO.setScore(0D);
        demandSupplyGapDTO.setScoreTypeCode("01");
        demandSupplyGapDTO.setScoreTypeDescription("Normal");

        // 2.1.1        Risk Sub Factor Attributes
        // -> Six Attributes

        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Demand showing a clearly declining trend. Industry likely to die out within a few years"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"Serious oversupply situation in industry. Production levels have been stagnant/declining for an extended period. Installed capacities likely to remain in excess of estimated demand over the medium term. Reversal in oversupply situation in medium term is unlikely"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Industry characterized by derived demand-strong linkages with overall economic growth. Marginal demand supply gap/ slight overcapacity situation likely to restrict medium term growth, despite positive long term growth prospects"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"Past growth rates relatively high and stable. Positive demand-supply gap scenario. Relatively insulated from economic recession. Favorable growth rate likely to continue in the medium term."));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Highly favorable demand-supply gap. Relative good growth potential in future."));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Extremely favorable demand supply gap. Current production well below the estimated demand. Demand supply gap likely to remain insulated from recessionary trends. Growth potential in foreseeable future is high."));

        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS1 =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        demandSupplyGapDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
        industryRiskFactorDTO.addRiskSubFactorDTO(demandSupplyGapDTO);




        //  ------------- Risk Sub Factor 2
        // 2.1.2 Impact of Government directives 50%

        RiskSubFactorDTO impactOfGovtDirectivesDTO = new RiskSubFactorDTO();
        impactOfGovtDirectivesDTO.setId(null);
        impactOfGovtDirectivesDTO.setItemNo(2);
        impactOfGovtDirectivesDTO.setDescription("Impact of Government Directives");
        impactOfGovtDirectivesDTO.setWeightage(0.50);
        impactOfGovtDirectivesDTO.setScore(0D);
        impactOfGovtDirectivesDTO.setScoreTypeCode("01");
        impactOfGovtDirectivesDTO.setScoreTypeDescription("Normal");

        // 2.1.1        Risk Sub Factor Attributes
        // -> Six Attributes


        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Policy is highly favourable and unambiguous, with certain minimum return being made available to the industry. Favourable government policy likely to continue in the foreseeable future"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Favourable Policy, which has positive impact on industry. No change in the favourable policy is expected"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"A mix of favourable and not so favourable policies. Slight impact of policy on the profitability"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"The existing government policies are not significantly favourable/ unfavourable for the industry. Profitability is not particularly influenced by existing/ foreseen regulatory measures"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"Government policy has a significantly negative influence, in the form of high excise burden, inverted import duty structure, unviable price regulation, etc"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Government policy towards industry is extremely unfavourable"));

        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS2 =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        impactOfGovtDirectivesDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        industryRiskFactorDTO.addRiskSubFactorDTO(impactOfGovtDirectivesDTO);


        //  ------------- Risk Sub Factor 3
        // 2.1.3  Extent of competition 25%

        RiskSubFactorDTO extentOfCompetetionDTO = new RiskSubFactorDTO();
        extentOfCompetetionDTO.setId(null);
        extentOfCompetetionDTO.setItemNo(3);
        extentOfCompetetionDTO.setDescription("Extent of Competetion");
        extentOfCompetetionDTO.setWeightage(0.25);
        extentOfCompetetionDTO.setScore(0D);
        extentOfCompetetionDTO.setScoreTypeCode("01");
        extentOfCompetetionDTO.setScoreTypeDescription("Normal");

        // 2.1.3        Risk Sub Factor Attributes
        // -> Six Attributes


        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "The industry has a monopoly structure, with the prospect of new entrants in the medium term being unlikely"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Oligopoly structure. High chance of the structure to be the same or move into a monopoly structure"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"Industry is characterized by a few large players accounting for the bulk of market share. Capital investment involved is likely to discourage significant increase in competition in the medium term. Absence of serious threat from imports"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Industry has a fairly fragmented structure. Moderate entry barriers in the form of technology/ capital investment"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"Highly fragmented industry. Processes are very easily replicable leading to presence of large, cost-competitive unorganized sector/ Significantly lower cost of imports render domestic producers unviable"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Extremely competitive industry, with a near absence of entry barriers, in the form of investment/ technology etc. No player is capable of building a significant market share in the industry. Majority of players in the industry are loss making"));


        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS3 =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        extentOfCompetetionDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        industryRiskFactorDTO.addRiskSubFactorDTO(extentOfCompetetionDTO);


        riskComponentDTO.addRiskFactorDTO(industryRiskFactorDTO);



        return riskComponentDTO;


//
//
//        riskSubFactorAttributes = new ArrayList<>();
//        riskSubFactorAttributes.add(new RiskAttribute(0D, ArrayList<>()));
//        riskSubFactorAttributes.add(new RiskAttribute(2D,ArrayList<>()));
//        riskSubFactorAttributes.add(new RiskAttribute(4D,ArrayList<>()));
//        riskSubFactorAttributes.add(new RiskAttribute(6D,ArrayList<>()));
//        riskSubFactorAttributes.add(new RiskAttribute(8D,ArrayList<>()));
//        riskSubFactorAttributes.add(new RiskAttribute(10D,ArrayList<>()));

    }
}
