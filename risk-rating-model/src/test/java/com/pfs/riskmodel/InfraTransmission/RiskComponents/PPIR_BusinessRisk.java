package com.pfs.riskmodel.InfraTransmission.RiskComponents;

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
public class PPIR_BusinessRisk {

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
        //Transmission Service Agreement 25%
        RiskSubFactorDTO transmissionRiskRiskSubFactorDTO = new RiskSubFactorDTO();
        transmissionRiskRiskSubFactorDTO.setId(null);
        transmissionRiskRiskSubFactorDTO.setItemNo(1);
        transmissionRiskRiskSubFactorDTO.setDescription("Transmission Service Agreement");
        transmissionRiskRiskSubFactorDTO.setWeightage(0.25);
        transmissionRiskRiskSubFactorDTO.setScore(0D);
        transmissionRiskRiskSubFactorDTO.setScoreTypeCode("01");
        transmissionRiskRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.1.1      Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Transmission Service Agreement is not robust and can pose challenges for the developer in case of cancellation and hence to the lender."));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"Transmission Service Agreement does not contain or is unclear on some of the key safeguards / clauses e.g. termination, substitution."));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"Transmission Service Agreement contains most of the standard clauses in similar manner as per the model concession framework for the sector."));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Transmission Service Agreement is standard as per the model concession framework for the sector."));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        transmissionRiskRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
        businessRiskFactorDTO.addRiskSubFactorDTO(transmissionRiskRiskSubFactorDTO);


        // 1.1.2        Risk Sub Factor 2
        //Counterparty Risk 30%
        RiskSubFactorDTO counterPartyRiskSubFactorDTO = new RiskSubFactorDTO();
        counterPartyRiskSubFactorDTO.setId(null);
        counterPartyRiskSubFactorDTO.setItemNo(2);
        counterPartyRiskSubFactorDTO.setDescription("Counterparty Risk");
        counterPartyRiskSubFactorDTO.setWeightage(0.30);
        counterPartyRiskSubFactorDTO.setScore(0D);
        counterPartyRiskSubFactorDTO.setScoreTypeCode("01");
        counterPartyRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.1.2       Risk Sub Factor Attributes
        // -> Five Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Pooling of counterparties which has significant portion of PGCIL"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"Pooling of counterparties which does not include PGCIL"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"Single DISCOM is a counterparty, but the DISCOM health is good and has consistently been paying its dues on time"));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"Single DISCOM is a counterparty, but the DISCOM health is good, but there have been intermittent delays in payment"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Single DISCOM is a counterparty, DISCOM health is a concern and has a poor past payment track record with extended delays."));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        counterPartyRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        businessRiskFactorDTO.addRiskSubFactorDTO(counterPartyRiskSubFactorDTO);



        // 1.1.3        Risk Sub Factor 3
        //Operations and Maintenance Risk 10%
        RiskSubFactorDTO operationsAndMaintenanceRiskSubFactorDTO = new RiskSubFactorDTO();
        operationsAndMaintenanceRiskSubFactorDTO.setId(null);
        operationsAndMaintenanceRiskSubFactorDTO.setItemNo(3);
        operationsAndMaintenanceRiskSubFactorDTO.setDescription("Operations and Maintenance Risk");
        operationsAndMaintenanceRiskSubFactorDTO.setWeightage(0.10);
        operationsAndMaintenanceRiskSubFactorDTO.setScore(0D);
        operationsAndMaintenanceRiskSubFactorDTO.setScoreTypeCode("01");
        operationsAndMaintenanceRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.1.3       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "O&M contractor has not very good reputation or any Operational project with"));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"No prior experiences of contractors OR Contractor yet to be appointed"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"Contractors risk is minimal as their past track record is good"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Reputed O& M contractors with good track record of operating projects of similar type"));

         Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS3 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
         operationsAndMaintenanceRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        businessRiskFactorDTO.addRiskSubFactorDTO(operationsAndMaintenanceRiskSubFactorDTO);


        // 1.1.4        Risk Sub Factor 4
        //Line Availability 35%
        RiskSubFactorDTO lineAvailabilityRiskSubFactorDTO = new RiskSubFactorDTO();
        lineAvailabilityRiskSubFactorDTO.setId(null);
        lineAvailabilityRiskSubFactorDTO.setItemNo(4);
        lineAvailabilityRiskSubFactorDTO.setDescription("Line Availability");
        lineAvailabilityRiskSubFactorDTO.setWeightage(0.35);
        lineAvailabilityRiskSubFactorDTO.setScore(0D);
        lineAvailabilityRiskSubFactorDTO.setScoreTypeCode("01");
        lineAvailabilityRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.1.4       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new HashSet<>(); //TODO - Check Scores
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Line availability over 99%"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"Line availability over the upper threshold (generally 98%) and below 99%"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Line availability below upper threshold mentioned in the agreement leading to lower than base charges (normally 98%)"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Line availability below lower threshold mentioned in the agreement leading to penalties (normally 95%)"));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS4 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        lineAvailabilityRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS4);
        businessRiskFactorDTO.addRiskSubFactorDTO(lineAvailabilityRiskSubFactorDTO);

        //Disruption risk Multiplier
        RiskSubFactorDTO disruptionRiskMultiplierRiskSubFactorDTO = new RiskSubFactorDTO();
        disruptionRiskMultiplierRiskSubFactorDTO.setId(null);
        disruptionRiskMultiplierRiskSubFactorDTO.setItemNo(5);
        disruptionRiskMultiplierRiskSubFactorDTO.setDescription("Disruption risk (Multiplier)");
        disruptionRiskMultiplierRiskSubFactorDTO.setWeightage(1.00);  // Does Not Matter
        disruptionRiskMultiplierRiskSubFactorDTO.setScore(0D);
        disruptionRiskMultiplierRiskSubFactorDTO.setScoreTypeCode("03");
        disruptionRiskMultiplierRiskSubFactorDTO.setScoreTypeDescription("Multiplier");





        //  Disruption Risk Multiplier - Risk Sub Factor Attributes
        // -> Two Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(0.85D, "Geographies with abnormal wind conditions, cyclones, thunderstorms. As this can cause line tripping and may lead to wear-and-tear of insulators and other hardware fittings. Limited insurance factored in for such damages."));
        riskSubFactorAttributes.add(new RiskAttribute(1.00D,"Geographies not prone to extreme weather conditions or sufficient insurance cover in extreme geographies"));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS5 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        disruptionRiskMultiplierRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS5);
        businessRiskFactorDTO.addRiskSubFactorDTO(disruptionRiskMultiplierRiskSubFactorDTO);


        //Repayment Period Multiplier
        RiskSubFactorDTO repaymentPeriodMultiplierRiskSubFactorDTO = new RiskSubFactorDTO();
        repaymentPeriodMultiplierRiskSubFactorDTO.setId(null);
        repaymentPeriodMultiplierRiskSubFactorDTO.setItemNo(6);
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

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS6 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        repaymentPeriodMultiplierRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS6);
        businessRiskFactorDTO.addRiskSubFactorDTO(repaymentPeriodMultiplierRiskSubFactorDTO);


        riskComponentDTO.addRiskFactorDTO(businessRiskFactorDTO);

        return riskComponentDTO;
    }
}
