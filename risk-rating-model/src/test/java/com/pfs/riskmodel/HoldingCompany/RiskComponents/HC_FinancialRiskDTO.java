package com.pfs.riskmodel.HoldingCompany.RiskComponents;

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
 * Created by sajeev on 25-Dec-18.
 */
public class HC_FinancialRiskDTO {



    RiskAttribute riskAttribute ;
    Set<RiskAttribute> riskSubFactorAttributes = new HashSet<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();



    public RiskComponentDTO getFinancialRiskDTO() {


        //Financial Risk Component 55.00%

        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        Set<RiskFactor> riskFactorList = new HashSet<>();

        riskComponentDTO.setId(null);
        riskComponentDTO.setItemNo(1);
        riskComponentDTO.setComputingMethodCode("01");
        riskComponentDTO.setComputingMethodDescription("Weighted");
        riskComponentDTO.setDescription("Financial Risk");
        riskComponentDTO.setScoreTypeDescription("01");
        riskComponentDTO.setScoreTypeDescription("Normal");
        riskComponentDTO.setScoreTypeCode("01");
        riskComponentDTO.setScore(0D);
        riskComponentDTO.setWeightage(0.55D);


//                Interest Coverage Ratio  20%
//
//                Leverage 15%
//                Investment Discipline 15%
//                Intercompany Guarantees 15%
//                Past History of Cash Flow Upstreaming 15%
//
        // Financial Risk Factor

            /*
             --------------------------    Risk Factor 1
                1.1.1 Financial Risk Factor
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




        // 1.1.1        Risk Sub Factor 1
        //  "Interest Coverage Ratio"  - 20% ; Interest Coverage Ratio = EBITDA / Interest
        RiskSubFactorDTO interestCovRatioRiskSubFactorDTO = new RiskSubFactorDTO();
        interestCovRatioRiskSubFactorDTO.setId(null);
        interestCovRatioRiskSubFactorDTO.setItemNo(1);
        interestCovRatioRiskSubFactorDTO.setDescription("Interest Coverage Ratio");
        interestCovRatioRiskSubFactorDTO.setWeightage(0.20);
        interestCovRatioRiskSubFactorDTO.setScore(0D);
        interestCovRatioRiskSubFactorDTO.setScoreTypeCode("01");
        interestCovRatioRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.1       Risk Sub Factor Attributes
        // -> Five Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, " 0 to Less Than 1.12"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"From 1.12 to Less Than 2"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"From 2 to Less Than 3"));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"From 3 to Less Than 5"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"From 5 to 10"));


         Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
         interestCovRatioRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
         financialRiskFactorDTO.addRiskSubFactorDTO(interestCovRatioRiskSubFactorDTO);



        // 1.1.2        Risk Sub Factor 2
        //  ""  Cash Flow Mismatch  20%
        RiskSubFactorDTO cashFlowMismatchRiskSubFactorDTO = new RiskSubFactorDTO();
        cashFlowMismatchRiskSubFactorDTO.setId(null);
        cashFlowMismatchRiskSubFactorDTO.setItemNo(2);
        cashFlowMismatchRiskSubFactorDTO.setDescription("Cash Flow Mismatch");
        cashFlowMismatchRiskSubFactorDTO.setWeightage(2.00);
        cashFlowMismatchRiskSubFactorDTO.setScore(0D);
        cashFlowMismatchRiskSubFactorDTO.setScoreTypeCode("01");
        cashFlowMismatchRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.2       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "From 0 to Less Than 1.15"));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"From 1.15 to Less Than 1.35"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"From 1.35 to Less Than 1.50"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"From 1.50 to 5"));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        cashFlowMismatchRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        financialRiskFactorDTO.addRiskSubFactorDTO(cashFlowMismatchRiskSubFactorDTO);



        // 1.1.3        Risk Sub Factor 3
        //  "Leverage 15%"
        RiskSubFactorDTO leverageRiskSubFactorDTO = new RiskSubFactorDTO();
        leverageRiskSubFactorDTO.setId(null);
        leverageRiskSubFactorDTO.setItemNo(3);
        leverageRiskSubFactorDTO.setDescription("Leverage");
        leverageRiskSubFactorDTO.setWeightage(0.15);
        leverageRiskSubFactorDTO.setScore(0D);
        leverageRiskSubFactorDTO.setScoreTypeCode("01");
        leverageRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.3       Risk Sub Factor Attributes
        // -> Five Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(10.00D, "From 0 to Less Than 2"));
        riskSubFactorAttributes.add(new RiskAttribute(7.50D,"From 2 to Less Than 3.5"));
        riskSubFactorAttributes.add(new RiskAttribute(5.00D,"From 3.5 to Less Than 5"));
        riskSubFactorAttributes.add(new RiskAttribute(2.50D,"From 5 to Less Than 6 "));
        riskSubFactorAttributes.add(new RiskAttribute(0.00D,"From 6 to 10"));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS3 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        leverageRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        financialRiskFactorDTO.addRiskSubFactorDTO(leverageRiskSubFactorDTO);



        // 1.1.4        Risk Sub Factor 4
        //  "Investment Discipline 15%"
        RiskSubFactorDTO investmentDisciplineRiskSubFactorDTO = new RiskSubFactorDTO();
        investmentDisciplineRiskSubFactorDTO.setId(null);
        investmentDisciplineRiskSubFactorDTO.setItemNo(4);
        investmentDisciplineRiskSubFactorDTO.setDescription("Investment Discipline");
        investmentDisciplineRiskSubFactorDTO.setWeightage(0.15);
        investmentDisciplineRiskSubFactorDTO.setScore(0D);
        investmentDisciplineRiskSubFactorDTO.setScoreTypeCode("01");
        investmentDisciplineRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.4      Risk Sub Factor Attributes
        // -> Five Attributes
        riskSubFactorAttributes = new HashSet<>(); //TODO - Check Scores
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Holdco is infusing 80% capital (contribution) in the form of pure equity in the SPVs by value and not by count"));
        riskSubFactorAttributes.add(new RiskAttribute(7.5D,"Holdco is infusing 60%- 80% capital (contribution) in the form of pure equity in the SPVs by value and not by count"));
        riskSubFactorAttributes.add(new RiskAttribute(5D,"Holdco is infusing 40%- 60% capital (contribution) in the form of pure equity in the SPVs by value and not by count"));
        riskSubFactorAttributes.add(new RiskAttribute(2.5D,"Holdco is infusing 25%- 40% capital (contribution) in the form of pure equity in the SPVs by value and not by count"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Holdco is infusing less than 25% capital (contribution) in the form of pure equity in the SPVs by value and not by count"));

         Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS4 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        investmentDisciplineRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS4);
        financialRiskFactorDTO.addRiskSubFactorDTO(investmentDisciplineRiskSubFactorDTO);



        // 1.1.5        Risk Sub Factor 5
        //  "Intercompany Guarantees 15%"
        RiskSubFactorDTO interCompanyGuaranteesRiskSubFactorDTO = new RiskSubFactorDTO();
        interCompanyGuaranteesRiskSubFactorDTO.setId(null);
        interCompanyGuaranteesRiskSubFactorDTO.setItemNo(5);
        interCompanyGuaranteesRiskSubFactorDTO.setDescription("Intercompany Guarantees");
        interCompanyGuaranteesRiskSubFactorDTO.setWeightage(0.15);
        interCompanyGuaranteesRiskSubFactorDTO.setScore(0D);
        interCompanyGuaranteesRiskSubFactorDTO.setScoreTypeCode("01");
        interCompanyGuaranteesRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.5       Risk Sub Factor Attributes
        // -> Three Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Less than 20% aggregated debt of all group SPVs guaranteed by holdco"));
        riskSubFactorAttributes.add(new RiskAttribute(5D,"20%- 50% aggregated debt of all group SPVs guaranteed by holdco"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"More than 50% aggregated debt of all group SPVs guaranteed by holdco"));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS5 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        interCompanyGuaranteesRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS5);
        financialRiskFactorDTO.addRiskSubFactorDTO(interCompanyGuaranteesRiskSubFactorDTO);




        // 1.1.6      Risk Sub Factor 6
        //  "Past History of Cash Flow Upstreaming 15%
        RiskSubFactorDTO pastHistoryOfCashFlowUpRiskSubFactorDTO = new RiskSubFactorDTO();
        pastHistoryOfCashFlowUpRiskSubFactorDTO.setId(null);
        pastHistoryOfCashFlowUpRiskSubFactorDTO.setItemNo(6);
        pastHistoryOfCashFlowUpRiskSubFactorDTO.setDescription("Past History of Cashflow Upstreaming");
        pastHistoryOfCashFlowUpRiskSubFactorDTO.setWeightage(0.15);
        pastHistoryOfCashFlowUpRiskSubFactorDTO.setScore(0D);
        pastHistoryOfCashFlowUpRiskSubFactorDTO.setScoreTypeCode("01");
        pastHistoryOfCashFlowUpRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.6       Risk Sub Factor Attributes
        // -> Five Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Consistent cash flow upstreaming all the SPVs over the last 2 years or operating period (whichever is lower)"));
        riskSubFactorAttributes.add(new RiskAttribute(7.5D,"Consistent cash flow upstreaming in over 80% of the SPVs (by count) over the last 2 years or operating period (whichever is lower)"));
        riskSubFactorAttributes.add(new RiskAttribute(5D,"Consistent cash flow upstreaming in 60%-80% of the SPVs (by count) over the last 2 years or operating period (whichever is lower)"));
        riskSubFactorAttributes.add(new RiskAttribute(2.5D,"Consistent cash flow upstreaming in 40%-60% of the SPVs (by count) over the last 2 years or operating period (whichever is lower)"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Consistent cash flow upstreaming in less than 40% of the SPVs (by count) over the last 2 years or operating period (whichever is lower)"));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        pastHistoryOfCashFlowUpRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS);
        financialRiskFactorDTO.addRiskSubFactorDTO(pastHistoryOfCashFlowUpRiskSubFactorDTO);


        riskComponentDTO.addRiskFactorDTO(financialRiskFactorDTO);
        return riskComponentDTO;
    }

}
