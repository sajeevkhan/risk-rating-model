package com.pfs.riskmodel.InfraRoadToll.RiskComponents;

import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskFactorDTO;
import com.pfs.riskmodel.dto.RiskSubFactorAttributeDTO;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;
import com.pfs.riskmodel.utils.RiskAttribute;
import com.pfs.riskmodel.utils.RiskSubFactorAttributesBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class IRToll_PPIR_BusinessRisk {

    RiskAttribute riskAttribute ;
    Set<RiskAttribute> riskSubFactorAttributes = new HashSet<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();

    public RiskComponentDTO getBusinessRiskDTO() {


        //Business Risk Component 50.00%

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
        riskComponentDTO.setWeightage(0.50D);


        // BusinessRisk Factor
        // No Concrete Risk Factors - Therefore a dummy Risk Factor called "Business Risk Factor" is added
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


        // 1.1.1        Risk Sub Factor 1
        //Traffic Risk   25%
        RiskSubFactorDTO counterPartyRiskSubFactorDTO = new RiskSubFactorDTO();
        counterPartyRiskSubFactorDTO.setId(null);
        counterPartyRiskSubFactorDTO.setItemNo(1);
        counterPartyRiskSubFactorDTO.setDescription("Traffic Risk");
        counterPartyRiskSubFactorDTO.setWeightage(0.25);
        counterPartyRiskSubFactorDTO.setScore(0D);
        counterPartyRiskSubFactorDTO.setScoreTypeCode("01");
        counterPartyRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.1.1      Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Any other counterparty"));
        riskSubFactorAttributes.add(new RiskAttribute(5D, "State Authority Project/ Any other authority within the state"));
        riskSubFactorAttributes.add(new RiskAttribute(10D, "NHAI Project / Project with multilateral funding"));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        counterPartyRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
        businessRiskFactorDTO.addRiskSubFactorDTO(counterPartyRiskSubFactorDTO);


        // 1.1.2        Risk Sub Factor 2
        //Concession Agreement 20%
        RiskSubFactorDTO concessionAgmtSubFactorDTO = new RiskSubFactorDTO();
        concessionAgmtSubFactorDTO.setId(null);
        concessionAgmtSubFactorDTO.setItemNo(2);
        concessionAgmtSubFactorDTO.setDescription("Concession Agreement");
        concessionAgmtSubFactorDTO.setWeightage(0.20);
        concessionAgmtSubFactorDTO.setScore(0D);
        concessionAgmtSubFactorDTO.setScoreTypeCode("01");
        concessionAgmtSubFactorDTO.setScoreTypeDescription("Normal");



        // 1.1.2       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Agreement is not robust and can pose challenges for the road developer in case of cancellation and hence to the lender. The relief to the other road developers has not been easy in the past"));
        riskSubFactorAttributes.add(new RiskAttribute(3D, "Concession Agreement does not contain or is unclear on some of the key safeguards / clauses e.g. termination, substitution."));
        riskSubFactorAttributes.add(new RiskAttribute(7D, "Concession Agreement contains most of the standard clauses in similar manner as per the model concession framework for the sector"));
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Concession Agreement is standard as per the model concession framework for the sector."));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        concessionAgmtSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        businessRiskFactorDTO.addRiskSubFactorDTO(concessionAgmtSubFactorDTO);



        // 1.1.3        Risk Sub Factor 3
        //Operations and Maintenance Risk 20%
        RiskSubFactorDTO operationsAndMaintenanceRiskSubFactorDTO = new RiskSubFactorDTO();
        operationsAndMaintenanceRiskSubFactorDTO.setId(null);
        operationsAndMaintenanceRiskSubFactorDTO.setItemNo(3);
        operationsAndMaintenanceRiskSubFactorDTO.setDescription("Operations and Maintenance Risk");
        operationsAndMaintenanceRiskSubFactorDTO.setWeightage(0.20);
        operationsAndMaintenanceRiskSubFactorDTO.setScore(0D);
        operationsAndMaintenanceRiskSubFactorDTO.setScoreTypeCode("01");
        operationsAndMaintenanceRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.1.3       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "O&M contractor has not very good reputation or any Operational project with no or little provisions for Monthly Maintenance Reserve (MMR) and project faces significant continuity risk"));
        riskSubFactorAttributes.add(new RiskAttribute(3D, "No prior experiences of contractors OR Contractor yet to be appointed\n" +
                "OR\n" +
                "Operational project with inadequate provisions to cover the MMR costs hampering continuity of the road project"));
        riskSubFactorAttributes.add(new RiskAttribute(7D, "Contractors risk is minimal as their past track record is good\n" +
                "OR\n" +
                "operational project with just adequate provisions/reserve (1x) to cover the MMR costs"));
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Reputed O& M contractors with good track record of operating projects of similar type\n" +
                "OR\n" +
                "Operational project with more than adequate provisions/reserve to cover the MMR costs"));

         Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS3 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
         operationsAndMaintenanceRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        businessRiskFactorDTO.addRiskSubFactorDTO(operationsAndMaintenanceRiskSubFactorDTO);


        // 1.1.4        Risk Sub Factor 4
        //Geopolitical risks to toll collection 15%
        RiskSubFactorDTO geoPolicticalRisksTollCollectionRiskSubFactorDTO = new RiskSubFactorDTO();
        geoPolicticalRisksTollCollectionRiskSubFactorDTO.setId(null);
        geoPolicticalRisksTollCollectionRiskSubFactorDTO.setItemNo(4);
        geoPolicticalRisksTollCollectionRiskSubFactorDTO.setDescription("Geopolitical risks to toll collection");
        geoPolicticalRisksTollCollectionRiskSubFactorDTO.setWeightage(0.15);
        geoPolicticalRisksTollCollectionRiskSubFactorDTO.setScore(0D);
        geoPolicticalRisksTollCollectionRiskSubFactorDTO.setScoreTypeCode("01");
        geoPolicticalRisksTollCollectionRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.1.4       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Presence of leakage route to avoid the toll plaza"));
        riskSubFactorAttributes.add(new RiskAttribute(2D, "Region is difficult for toll collection i.e. historically it has been observed that either the people are hostile towards toll payment or the political exigencies hamper the toll collection."));
        riskSubFactorAttributes.add(new RiskAttribute(4D, "Toll rates are very high compared to length of project stretch due to presence of large numbers of long structures (length more than 60m) and bypasses"));
        riskSubFactorAttributes.add(new RiskAttribute(6D, "Share of local traffic is very high"));
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Average toll rates, limited risks around toll collection i.e. historically it has been observed that the toll collection have been responded positively by the people"));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS4 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        geoPolicticalRisksTollCollectionRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS4);
        businessRiskFactorDTO.addRiskSubFactorDTO(geoPolicticalRisksTollCollectionRiskSubFactorDTO);



        // 1.1.5        Risk Sub Factor 5
        // Tolling Operation Efficiency 15%
        RiskSubFactorDTO tollingOperationEfficiencyRiskSubFactorDTO = new RiskSubFactorDTO();
        tollingOperationEfficiencyRiskSubFactorDTO.setId(null);
        tollingOperationEfficiencyRiskSubFactorDTO.setItemNo(5);
        tollingOperationEfficiencyRiskSubFactorDTO.setDescription("Tolling Operation Efficiency");
        tollingOperationEfficiencyRiskSubFactorDTO.setWeightage(0.20);
        tollingOperationEfficiencyRiskSubFactorDTO.setScore(0D);
        tollingOperationEfficiencyRiskSubFactorDTO.setScoreTypeCode("01");
        tollingOperationEfficiencyRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.1.5      Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Toll Collection Agency has bad reputation, Inadequate toll management system (TMS), Absence of Standard Operating Procedure (SOP) document for tolling operations"));
        riskSubFactorAttributes.add(new RiskAttribute(4D, "Reputed Toll Collection Agency, inadequate toll management system, absence of Standard Operating Procedure (SOP) document for tolling operations"));
        riskSubFactorAttributes.add(new RiskAttribute(8D, "Reputed Toll Collection Agency, Pilferage-free toll management system, Availability of Standard Operating Procedure (SOP) document for tolling operations"));
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Pilferage-free toll management system, Provision of data management centre at HQ, Availability of Standard Operating Procedure (SOP) document for tolling operations"));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS5 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        tollingOperationEfficiencyRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS5);
        businessRiskFactorDTO.addRiskSubFactorDTO(tollingOperationEfficiencyRiskSubFactorDTO);


        //Single commodity dependent Road (Multiplier)
        RiskSubFactorDTO singleCommodityDepRoadMultiplierRiskSubFactorDTO = new RiskSubFactorDTO();
        singleCommodityDepRoadMultiplierRiskSubFactorDTO.setId(null);
        singleCommodityDepRoadMultiplierRiskSubFactorDTO.setItemNo(6);
        singleCommodityDepRoadMultiplierRiskSubFactorDTO.setDescription("Single commodity dependent Road (Multiplier)");
        singleCommodityDepRoadMultiplierRiskSubFactorDTO.setWeightage(1.00); // Does Not Matter
        singleCommodityDepRoadMultiplierRiskSubFactorDTO.setScore(0D);
        singleCommodityDepRoadMultiplierRiskSubFactorDTO.setScoreTypeCode("03");
        singleCommodityDepRoadMultiplierRiskSubFactorDTO.setScoreTypeDescription("Multiplier");

        // Repayment Period -   Risk Sub Factor Attributes
        // -> Two Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(0.5D, "Traffic highly dependent on a single commodity which is highly depended on Policy decision"));
        riskSubFactorAttributes.add(new RiskAttribute(1.0D,"Traffic not dependent on one commodity"));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS6 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        singleCommodityDepRoadMultiplierRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS6);
        businessRiskFactorDTO.addRiskSubFactorDTO(singleCommodityDepRoadMultiplierRiskSubFactorDTO);



        //Repayment Period Multiplier
        RiskSubFactorDTO repaymentPeriodMultiplierRiskSubFactorDTO = new RiskSubFactorDTO();
        repaymentPeriodMultiplierRiskSubFactorDTO.setId(null);
        repaymentPeriodMultiplierRiskSubFactorDTO.setItemNo(7);
        repaymentPeriodMultiplierRiskSubFactorDTO.setDescription("Repayment Period (Multiplier)");
        repaymentPeriodMultiplierRiskSubFactorDTO.setWeightage(1.00); // Does Not Matter
        repaymentPeriodMultiplierRiskSubFactorDTO.setScore(0D);
        repaymentPeriodMultiplierRiskSubFactorDTO.setScoreTypeCode("03");
        repaymentPeriodMultiplierRiskSubFactorDTO.setScoreTypeDescription("Multiplier");

        // Repayment Period -   Risk Sub Factor Attributes
        // -> Two Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(1.1D, "If repayment period less than 85% of operational period in concession agreement"));
        riskSubFactorAttributes.add(new RiskAttribute(1.0D,"If repayment period more than 85% of operational period in concession agreement"));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS7 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        repaymentPeriodMultiplierRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS7);
        businessRiskFactorDTO.addRiskSubFactorDTO(repaymentPeriodMultiplierRiskSubFactorDTO);


        riskComponentDTO.addRiskFactorDTO(businessRiskFactorDTO);

        return riskComponentDTO;
    }
}
