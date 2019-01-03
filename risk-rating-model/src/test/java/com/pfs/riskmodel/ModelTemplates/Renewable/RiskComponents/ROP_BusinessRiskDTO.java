package com.pfs.riskmodel.ModelTemplates.Renewable.RiskComponents;

import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.dto.*;
import com.pfs.riskmodel.utils.RiskAttribute;
import com.pfs.riskmodel.utils.RiskSubFactorAttributesBuilder;

import java.util.*;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class ROP_BusinessRiskDTO {

    RiskAttribute riskAttribute ;
    List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();



    public RiskComponentDTO getBusinessRiskDTO() {

        // 1.0 Business Risk - 32%

        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        List<RiskFactor> riskFactorList = new ArrayList<>();

        riskComponentDTO.setId(null);
        riskComponentDTO.setItemNo(1);
        riskComponentDTO.setComputingMethodCode("01");
        riskComponentDTO.setComputingMethodDescription("Weighted");
        riskComponentDTO.setDescription("Business Risk");
        riskComponentDTO.setScoreTypeDescription("01");
        riskComponentDTO.setScoreTypeDescription("Normal");
        riskComponentDTO.setScoreTypeCode("01");
        riskComponentDTO.setScore(0D);
        riskComponentDTO.setWeightage(0.32D);

        /*
         --------------------------    Risk Factor 1
            1.1 Market Position 70%
                 4 Risk Sub Factors
         */

        RiskFactorDTO marketPositionRiskFactorDTO = new RiskFactorDTO();
        marketPositionRiskFactorDTO.setId(null);
        marketPositionRiskFactorDTO.setItemNo(1);
        marketPositionRiskFactorDTO.setDescription("Market Position");
        marketPositionRiskFactorDTO.setWeightage(0.70);
        marketPositionRiskFactorDTO.setScore(0D);
        marketPositionRiskFactorDTO.setScoreTypeCode("01");
        marketPositionRiskFactorDTO.setScoreTypeDescription("Normal");
        marketPositionRiskFactorDTO.setComputingMethodCode("01");
        marketPositionRiskFactorDTO.setComputingMethodDescription("Weightage");



        // 1.1.1        Risk Sub Factor 1
        //  Offtake Risk and Sales Composition -  27.80%

        RiskSubFactorDTO offTakeRiskandSalesCompositionDTO = new RiskSubFactorDTO();
        offTakeRiskandSalesCompositionDTO.setId(null);
        offTakeRiskandSalesCompositionDTO.setItemNo(1);
        offTakeRiskandSalesCompositionDTO.setDescription("Offtake Risk and Sales Composition ");
        offTakeRiskandSalesCompositionDTO.setWeightage(0.278); //28%
        offTakeRiskandSalesCompositionDTO.setScore(0D);
        offTakeRiskandSalesCompositionDTO.setScoreTypeCode("01");
        offTakeRiskandSalesCompositionDTO.setScoreTypeDescription("Normal");

        // 1.1.1        Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "No arrangements with any prospective buyers. Extremely high exposure to market risk."));
        riskSubFactorAttributes.add(new RiskAttribute(2D , "Short/Medium term PPAs on competitive bidding to SEBs, Group captive consumers or other industrial consumers" ));
        riskSubFactorAttributes.add(new RiskAttribute(4D , "Long term PPA with 3rd party (open access)"));
        riskSubFactorAttributes.add(new RiskAttribute(6D , "Long term PPA proposed /signed in group captive scheme"));
        riskSubFactorAttributes.add(new RiskAttribute(8D , "Long term PPA not signed but proposed to be signed with State Discom and COD to be achieved within control period of PPA"));
        riskSubFactorAttributes.add(new RiskAttribute(10D , "Long term PPA signed with State Discoms, NTPC, SEC, Govt. nominated agencies, etc."));

        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        offTakeRiskandSalesCompositionDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS);
        marketPositionRiskFactorDTO.addRiskSubFactorDTO(offTakeRiskandSalesCompositionDTO);


        // 1.1.2        Risk Sub Factor 2
        // Counterparty Risk - Renewable    -   27.80%
        RiskSubFactorDTO counterPartyRiskRenewableDTO = new RiskSubFactorDTO();
        counterPartyRiskRenewableDTO.setId(null);
        counterPartyRiskRenewableDTO.setItemNo(2);
        counterPartyRiskRenewableDTO.setDescription("Counterparty Risk - Renewable");
        counterPartyRiskRenewableDTO.setWeightage(0.278);
        counterPartyRiskRenewableDTO.setScore(0D);
        counterPartyRiskRenewableDTO.setScoreTypeCode("01");
        counterPartyRiskRenewableDTO.setScoreTypeDescription("Normal");



        // 1.1.2        Risk Sub Factor Attributes
        // -> Five Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "No off taker as on date (Off taker is Group Captive/Open access consumers) OR Over dues more than 210 days (Off taker is SEB/NTPC/SECI/Other Govt. nominated agency) "));
        riskSubFactorAttributes.add(new RiskAttribute(2D, "Non listed off taker & non-investment grade borrower (Off taker is Group Captive/Open access consumers) OR Over dues not more than 180 days but less than 210 days (Off taker is SEB/NTPC/SECI/Other Govt. nominated agency)"));
        riskSubFactorAttributes.add(new RiskAttribute(4D, "Non Listed off taker & long term credit rating not yet assigned (Off taker is Group Captive/Open access consumers) OR Over dues not more than 120 days but less than 90 days (Off taker is SEB/NTPC/SECI/Other Govt. nominated agency)"));
        riskSubFactorAttributes.add(new RiskAttribute(6D, " Non listed off taker & long term investment credit rating not below BBB+ (Off taker is Group Captive/Open access consumers) OR Over dues not more than 90 days but less than 30 days (Off taker is SEB/NTPC/SECI/Other Govt. nominated agency)"));
        riskSubFactorAttributes.add(new RiskAttribute(8D, "Listed off taker & long term investment credit rating not below BBB+ (quantum < 50% of project capacity): Off taker is Group Captive/Open access consumers OR Over dues not more than 30 days but less than 15 days (Off taker is SEB/NTPC/SECI/Other Govt. nominated agency)"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Listed off taker & long term investment credit rating not below BBB+ (quantum ≥ 50% of project capacity): Off taker is Group Captive/Open access consumers OR Over dues not more than 15 days (Off taker is SEB/NTPC/SECI/Other Govt. nominated agency)"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        counterPartyRiskRenewableDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS);
        marketPositionRiskFactorDTO.addRiskSubFactorDTO(counterPartyRiskRenewableDTO);


        // 1.1.3        Risk Sub Factor 3
        //  Cost of generation (per unit cost) - 22.20%
        RiskSubFactorDTO costOfGenerationDTO = new RiskSubFactorDTO();
        costOfGenerationDTO.setId(null);
        costOfGenerationDTO.setItemNo(3);
        costOfGenerationDTO.setDescription("Cost of generation (per unit cost)");
        costOfGenerationDTO.setWeightage(0.222);
        costOfGenerationDTO.setScore(0D);
        costOfGenerationDTO.setScoreTypeCode("01");
        costOfGenerationDTO.setScoreTypeDescription("Normal");

        // 1.1.3       Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute( 0D,"Cost is extremely non-competitive and amongst the most expensive in the industry"));
        riskSubFactorAttributes.add(new RiskAttribute(2D, "Cost is non-competitive and the generation cost falls amongst the expensive power plant projects in the industry."));
        riskSubFactorAttributes.add(new RiskAttribute(4D, "Generation is not competitive and higher than the average industry cost."));
        riskSubFactorAttributes.add(new RiskAttribute(6D , "Generation cost is moderately competitive and within the range of average industry cost"));
        riskSubFactorAttributes.add(new RiskAttribute(8D, "Generation cost is competitive and is lower by 10-20% than the industry standards"));
        riskSubFactorAttributes.add(new RiskAttribute(10D, "The generation cost is extremely competitive and amongst the best in the industry-Lower by atleast 20%"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        costOfGenerationDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        marketPositionRiskFactorDTO.addRiskSubFactorDTO(costOfGenerationDTO);


        // 1.1.4    Risk Sub Factor 4
        // Price Risk - Renewable   -   22.20%

        RiskSubFactorDTO priceRiskRenewableSubFactorDTO = new RiskSubFactorDTO();
        priceRiskRenewableSubFactorDTO.setId(null);
        priceRiskRenewableSubFactorDTO.setItemNo(4);
        priceRiskRenewableSubFactorDTO.setDescription("Price Risk Renewable");
        priceRiskRenewableSubFactorDTO.setWeightage(0.222);
        priceRiskRenewableSubFactorDTO.setScore(0D);
        priceRiskRenewableSubFactorDTO.setScoreTypeCode("01");
        priceRiskRenewableSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.1.4       Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Tariff determined through competitive bidding/preferential and project’s CoD is beyond the control period of tariff order/PPA; Impact on tariff is significant "));
        riskSubFactorAttributes.add(new RiskAttribute(2D, "Tariff determined through competitive bidding/preferential and project’s CoD is beyond the control period of tariff order/PPA; Impact on tariff is not significant"));
        riskSubFactorAttributes.add(new RiskAttribute(4D, "Tariff determined is through preferential tariff and more than 25% of APPC of the state"));
        riskSubFactorAttributes.add(new RiskAttribute(6D, "Tariff determined through competitive bidding and more than 25% of APPC of the state"));
        riskSubFactorAttributes.add(new RiskAttribute(8D, "Tariff determined is through preferential tariff and less than APPC of the state"));
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Tariff determined through competitive bidding and less than average power procurement cost (APPC) of the state"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS3 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        priceRiskRenewableSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        marketPositionRiskFactorDTO.addRiskSubFactorDTO(priceRiskRenewableSubFactorDTO);


        /*
         --------------------------    Risk Factor 2
            1.2 Operating Efficiency    - 30 %
            Two Risk Sub Factors
         */

        RiskFactorDTO operationEfficiencyRiskFactorDTO  =  new RiskFactorDTO();
        operationEfficiencyRiskFactorDTO.setId(null);
        operationEfficiencyRiskFactorDTO.setItemNo(1);
        operationEfficiencyRiskFactorDTO.setDescription("Operating Efficiency");
        operationEfficiencyRiskFactorDTO.setWeightage(0.30);
        operationEfficiencyRiskFactorDTO.setScore(0D);
        operationEfficiencyRiskFactorDTO.setScoreTypeCode("01");
        operationEfficiencyRiskFactorDTO.setScoreTypeDescription("Normal");
        operationEfficiencyRiskFactorDTO.setComputingMethodCode("01");
        operationEfficiencyRiskFactorDTO.setComputingMethodDescription("Weightage");


        //                  Risk Sub Factor 1
        // 1.2.1    Operations and Maintenance Risk - 15%
        //

        RiskSubFactorDTO operationsAndMaintenanceRiskSubFactorDTO = new RiskSubFactorDTO();
        operationsAndMaintenanceRiskSubFactorDTO.setId(null);
        operationsAndMaintenanceRiskSubFactorDTO.setItemNo(1);
        operationsAndMaintenanceRiskSubFactorDTO.setDescription("Operations and Maintenance Risk");
        operationsAndMaintenanceRiskSubFactorDTO.setWeightage(0.15); //28%
        operationsAndMaintenanceRiskSubFactorDTO.setScore(0D);
        operationsAndMaintenanceRiskSubFactorDTO.setScoreTypeCode("01");
        operationsAndMaintenanceRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.2.1        Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Highly Reputed O&M contractors with excellent track record of operating projects of similar scale and elsewhere"));
        riskSubFactorAttributes.add(new RiskAttribute( 8D, "Reputed O& M contractors with very good track record of operating projects of similar type"));
        riskSubFactorAttributes.add(new RiskAttribute( 6D, "Contractors risk is minimal as their past track record is good"));
        riskSubFactorAttributes.add(new RiskAttribute( 4D, "The contractors experience is insufficient for the project"));
        riskSubFactorAttributes.add(new RiskAttribute(2D, "No prior experiences of contractors"));
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Extremely high risk as the O&M contractors have bad reputation"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS4 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        operationsAndMaintenanceRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS4);
        operationEfficiencyRiskFactorDTO.addRiskSubFactorDTO(operationsAndMaintenanceRiskSubFactorDTO);


        //              Risk Sub Factor 2
        //1.2.2    Key Resources Risk - 85%
        RiskSubFactorDTO keyResourcesRiskSubFactorDTO = new RiskSubFactorDTO();
        keyResourcesRiskSubFactorDTO.setId(null);
        keyResourcesRiskSubFactorDTO.setItemNo(2);
        keyResourcesRiskSubFactorDTO.setDescription("Key Resources Risk");
        keyResourcesRiskSubFactorDTO.setWeightage(0.85);
        keyResourcesRiskSubFactorDTO.setScore(0D);
        keyResourcesRiskSubFactorDTO.setScoreTypeCode("01");
        keyResourcesRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.2.2        Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Operational project - PLF significantly higher than base case. "));
        riskSubFactorAttributes.add(new RiskAttribute( 8D, "Operational project - PLF higher than base case."));
        riskSubFactorAttributes.add(new RiskAttribute(6D, "Operational project - PLF as per base case."));
        riskSubFactorAttributes.add(new RiskAttribute(4D, "Operational project - PLF less than base case. Under construction project - generation assessment based on more than 2 years resource data at site"));
        riskSubFactorAttributes.add(new RiskAttribute(2D, "Under construction project - generation assessment based on 1 to 2 year resource data at site"));
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Under construction project - generation assessment based on 0 to 1 year resource data at site"));



        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS5 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        keyResourcesRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS5);
        operationEfficiencyRiskFactorDTO.addRiskSubFactorDTO(keyResourcesRiskSubFactorDTO);

        riskComponentDTO.addRiskFactorDTO(marketPositionRiskFactorDTO);
        riskComponentDTO.addRiskFactorDTO(operationEfficiencyRiskFactorDTO);

        return riskComponentDTO;

    }

}
