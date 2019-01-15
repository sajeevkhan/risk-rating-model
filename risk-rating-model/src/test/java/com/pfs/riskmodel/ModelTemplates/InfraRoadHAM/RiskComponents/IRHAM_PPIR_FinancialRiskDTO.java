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
public class IRHAM_PPIR_FinancialRiskDTO {


    RiskAttribute riskAttribute;
    List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();

    public RiskComponentDTO getFinancialRiskDTO() {


        //Financial Risk Component 40%

        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        List<RiskFactor> riskFactorList = new ArrayList<>();

        riskComponentDTO.setId(null);
        riskComponentDTO.setItemNo(3);
        riskComponentDTO.setComputingMethodCode("01");
        riskComponentDTO.setComputingMethodDescription("Weighted");
        riskComponentDTO.setDescription("Financial Risk");
        riskComponentDTO.setScoreTypeDescription("01");
        riskComponentDTO.setScoreTypeDescription("Normal");
        riskComponentDTO.setScoreTypeCode("01");
        riskComponentDTO.setScore(0D);
        riskComponentDTO.setWeightage(0.40D);

        riskComponentDTO.setIsApplicable(true);

        // 1.1 Financial Risk Factor
        // No Concrete Risk Factors - Therefore a dummy Risk Factor called "Financial Risk Factor" is added
        RiskFactorDTO financialRiskFactorDTO = new RiskFactorDTO();
        financialRiskFactorDTO.setId(null);
        financialRiskFactorDTO.setItemNo(1);
        financialRiskFactorDTO.setDescription("Financial Risk Factor");
        financialRiskFactorDTO.setWeightage(1.000);
        financialRiskFactorDTO.setScore(0D);
        financialRiskFactorDTO.setScoreTypeCode("01");
        financialRiskFactorDTO.setScoreTypeDescription("Normal");
        financialRiskFactorDTO.setComputingMethodCode("01");
        financialRiskFactorDTO.setComputingMethodDescription("Weighted");


        // 1.1.1        Risk Sub Factor 1
        // 1.1.1     Minimum DSCR 30%
        RiskSubFactorDTO minimumDSCRRiskSubFactorDTO = new RiskSubFactorDTO();
        minimumDSCRRiskSubFactorDTO.setId(null);
        minimumDSCRRiskSubFactorDTO.setItemNo(1);
        minimumDSCRRiskSubFactorDTO.setDescription("Minimum DSCR (Debt Service Coverage Ratio)");
        minimumDSCRRiskSubFactorDTO.setWeightage(0.30);
        minimumDSCRRiskSubFactorDTO.setScore(0D);
        minimumDSCRRiskSubFactorDTO.setScoreTypeCode("01");
        minimumDSCRRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.1       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Less than 1"));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"Equal to or above 1 but less than 1.1"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"Equal to or above 1.1 but less than 1.2"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Equal to or above 1.2"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        minimumDSCRRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
        financialRiskFactorDTO.addRiskSubFactorDTO(minimumDSCRRiskSubFactorDTO);


        // 1.1.2        Risk Sub Factor 2
        // 1.1.2 Debt / Equity ratio 30%
        // Debt / Equity ratio - Build Phase (Ratio) without NHAI/Authority grant

        RiskSubFactorDTO debtEquityRatioRiskSubFactorDTO = new RiskSubFactorDTO();
        debtEquityRatioRiskSubFactorDTO.setId(null);
        debtEquityRatioRiskSubFactorDTO.setItemNo(2);
        debtEquityRatioRiskSubFactorDTO.setDescription("Debt / Equity ratio - Build Phase (Ratio) without NHAI/Authority grant");
        debtEquityRatioRiskSubFactorDTO.setWeightage(0.30);
        debtEquityRatioRiskSubFactorDTO.setScore(0D);
        debtEquityRatioRiskSubFactorDTO.setScoreTypeCode("01");
        debtEquityRatioRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.2      Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "More than 4"));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"Equal to or above 3 but less than or equal to 4"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"Equal to or above 2.33 but less than 3"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Less than 2.33"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        debtEquityRatioRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        financialRiskFactorDTO.addRiskSubFactorDTO(debtEquityRatioRiskSubFactorDTO);


        // 1.1.3        Risk Sub Factor 3
        // 1.1.3 Difference between WACC and Internal Rate of return (%) 30%

        RiskSubFactorDTO differenceWACCAndIRRRiskSubFactorDTO = new RiskSubFactorDTO();
        differenceWACCAndIRRRiskSubFactorDTO.setId(null);
        differenceWACCAndIRRRiskSubFactorDTO.setItemNo(3);
        differenceWACCAndIRRRiskSubFactorDTO.setDescription("Difference between WACC and Internal Rate of return (%)");
        differenceWACCAndIRRRiskSubFactorDTO.setWeightage(0.30);
        differenceWACCAndIRRRiskSubFactorDTO.setScore(0D);
        differenceWACCAndIRRRiskSubFactorDTO.setScoreTypeCode("01");
        differenceWACCAndIRRRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.1       Risk Sub Factor Attributes
        // -> Three Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(00D,"WACC-IRR greater than or equal to 2%"));
        riskSubFactorAttributes.add(new RiskAttribute(05D,"WACC-IRR between 0 and 2%"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"WACC-IRR below  0%"));

         List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS3 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        differenceWACCAndIRRRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        financialRiskFactorDTO.addRiskSubFactorDTO(differenceWACCAndIRRRiskSubFactorDTO);


        // 1.1.4        Risk Sub Factor 4
        // 1.1.4 Average DSCR


        RiskSubFactorDTO averageDSCRRiskSubFactorDTO = new RiskSubFactorDTO();
        averageDSCRRiskSubFactorDTO.setId(null);
        averageDSCRRiskSubFactorDTO.setItemNo(4);
        averageDSCRRiskSubFactorDTO.setDescription("Average DSCR");
        averageDSCRRiskSubFactorDTO.setWeightage(0.10);
        averageDSCRRiskSubFactorDTO.setScore(0D);
        averageDSCRRiskSubFactorDTO.setScoreTypeCode("01");
        averageDSCRRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.4      Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Less than 1"));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"Equal to or above 1 but less than 1.1"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"Equal to or above 1.1 but less than 1.2"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Equal to or above 1.2"));

         List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS4 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
         averageDSCRRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS4);
        financialRiskFactorDTO.addRiskSubFactorDTO(averageDSCRRiskSubFactorDTO);


        riskComponentDTO.addRiskFactorDTO(financialRiskFactorDTO);

        return riskComponentDTO;
    }
}