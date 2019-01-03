package com.pfs.riskmodel.ModelTemplates.InfraRoadHAM.RiskComponents;

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
public class IRHAM_PPIR_BusinessRisk {

    RiskAttribute riskAttribute ;
    List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();

    public RiskComponentDTO getBusinessRiskDTO() {


        //Business Risk Component 35.00%

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
        businessRiskFactorDTO.setComputingMethodCode("01");
        businessRiskFactorDTO.setComputingMethodDescription("Weighted");


        // 1.1.1        Risk Sub Factor 1
        //Counter Party Risk   30%
        RiskSubFactorDTO counterPartyRiskSubFactorDTO = new RiskSubFactorDTO();
        counterPartyRiskSubFactorDTO.setId(null);
        counterPartyRiskSubFactorDTO.setItemNo(1);
        counterPartyRiskSubFactorDTO.setDescription("Counterparty Risk");
        counterPartyRiskSubFactorDTO.setWeightage(0.30);
        counterPartyRiskSubFactorDTO.setScore(0D);
        counterPartyRiskSubFactorDTO.setScoreTypeCode("01");
        counterPartyRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.1.1      Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Any other counterparty"));
        riskSubFactorAttributes.add(new RiskAttribute(5D, "State Authority Project/ Any other authority within the state"));
        riskSubFactorAttributes.add(new RiskAttribute(10D, "NHAI Project / Project with multilateral funding"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        counterPartyRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
        businessRiskFactorDTO.addRiskSubFactorDTO(counterPartyRiskSubFactorDTO);


        // 1.1.2        Risk Sub Factor 2
        //Concession Risk 30%
        RiskSubFactorDTO concessionRiskSubFactorDTO = new RiskSubFactorDTO();
        concessionRiskSubFactorDTO.setId(null);
        concessionRiskSubFactorDTO.setItemNo(2);
        concessionRiskSubFactorDTO.setDescription("Concession Risk");
        concessionRiskSubFactorDTO.setWeightage(0.30);
        concessionRiskSubFactorDTO.setScore(0D);
        concessionRiskSubFactorDTO.setScoreTypeCode("01");
        concessionRiskSubFactorDTO.setScoreTypeDescription("Normal");



        // 1.1.2       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Agreement is not robust and can pose challenges for the road developer in case of cancellation and hence to the lender. The relief to the other road developers has not been easy in the past"));
        riskSubFactorAttributes.add(new RiskAttribute(3D, "Concession Agreement does not contain or is unclear on some of the key safeguards / clauses e.g. termination, substitution."));
        riskSubFactorAttributes.add(new RiskAttribute(7D, "Concession Agreement contains most of the standard clauses in similar manner as per the model concession framework for the sector"));
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Concession Agreement is standard as per the model concession framework for the sector."));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        concessionRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        businessRiskFactorDTO.addRiskSubFactorDTO(concessionRiskSubFactorDTO);



        // 1.1.3        Risk Sub Factor 3
        //Operations and Maintenance Risk 40%
        RiskSubFactorDTO operationsAndMaintenanceRiskSubFactorDTO = new RiskSubFactorDTO();
        operationsAndMaintenanceRiskSubFactorDTO.setId(null);
        operationsAndMaintenanceRiskSubFactorDTO.setItemNo(3);
        operationsAndMaintenanceRiskSubFactorDTO.setDescription("Operations and Maintenance Risk");
        operationsAndMaintenanceRiskSubFactorDTO.setWeightage(0.40);
        operationsAndMaintenanceRiskSubFactorDTO.setScore(0D);
        operationsAndMaintenanceRiskSubFactorDTO.setScoreTypeCode("01");
        operationsAndMaintenanceRiskSubFactorDTO.setScoreTypeDescription("Normal");

        // 1.1.3       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
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

         List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS3 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
         operationsAndMaintenanceRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        businessRiskFactorDTO.addRiskSubFactorDTO(operationsAndMaintenanceRiskSubFactorDTO);



         //Repayment Period Multiplier
        RiskSubFactorDTO repaymentPeriodMultiplierRiskSubFactorDTO = new RiskSubFactorDTO();
        repaymentPeriodMultiplierRiskSubFactorDTO.setId(null);
        repaymentPeriodMultiplierRiskSubFactorDTO.setItemNo(4);
        repaymentPeriodMultiplierRiskSubFactorDTO.setDescription("Repayment Period (Multiplier)");
        repaymentPeriodMultiplierRiskSubFactorDTO.setWeightage(1.00); // Does Not Matter
        repaymentPeriodMultiplierRiskSubFactorDTO.setScore(0D);
        repaymentPeriodMultiplierRiskSubFactorDTO.setScoreTypeCode("03");
        repaymentPeriodMultiplierRiskSubFactorDTO.setScoreTypeDescription("Multiplier");

        // Repayment Period -   Risk Sub Factor Attributes
        // -> Two Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(1.1D, "If repayment period less than 85% of operational period in concession agreement"));
        riskSubFactorAttributes.add(new RiskAttribute(1.0D,"If repayment period more than 85% of operational period in concession agreement"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS6 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        repaymentPeriodMultiplierRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS6);
        businessRiskFactorDTO.addRiskSubFactorDTO(repaymentPeriodMultiplierRiskSubFactorDTO);


        riskComponentDTO.addRiskFactorDTO(businessRiskFactorDTO);

        return riskComponentDTO;
    }
}
