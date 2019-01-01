package com.pfs.riskmodel.Templates.Renewable.RiskComponents;

import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskFactorDTO;
import com.pfs.riskmodel.dto.RiskSubFactorAttributeDTO;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class RP_CompletionRiskComponentDTO {


    public RiskComponentDTO getCompletionRisk(){




        /**********************************************************************************************************************
         *  Risk Component 1 : Completion Risk
         *      ----- RiskFactor 1:  Funding Risk
         *      ----- RiskFactor 2: Key Completion Risk
         **********************************************************************************************************************/

        //                       Risk Type 1 - Risk Component 1
        //  1                           Completion Risk
        RiskComponentDTO completionRiskComponentDTO = new RiskComponentDTO();
        completionRiskComponentDTO.setId(null);
        completionRiskComponentDTO.setItemNo(1);
        completionRiskComponentDTO.setDescription("Completion Risk");
        completionRiskComponentDTO.setWeightage(0.50);  // 50%
        completionRiskComponentDTO.setComputingMethodCode("01");
        completionRiskComponentDTO.setComputingMethodDescription("Weighted");
        completionRiskComponentDTO.setScoreTypeCode("01");
        completionRiskComponentDTO.setScoreTypeDescription("Normal");
        completionRiskComponentDTO.setScore(0D);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1
        //1.1                             Completion Risk -> Funding Risk

        RiskFactorDTO fundingRiskFactorDTO = new RiskFactorDTO();
        fundingRiskFactorDTO.setId(null);
        fundingRiskFactorDTO.setItemNo(1);
        fundingRiskFactorDTO.setDescription("Funding Risk");
        fundingRiskFactorDTO.setWeightage(0.30); //30%
        fundingRiskFactorDTO.setComputingMethodCode("01");
        fundingRiskFactorDTO.setComputingMethodDescription("Weighted");
        fundingRiskFactorDTO.setScoreTypeCode("01");
        fundingRiskFactorDTO.setScoreTypeDescription("Normal");
        fundingRiskFactorDTO.setScore(0D);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.1.1                      Completion Risk -> Funding Risk - > Financial Flexibility
        RiskSubFactorDTO financialFlexibilityRiskSubFactorDTO = new RiskSubFactorDTO();
        financialFlexibilityRiskSubFactorDTO.setId(null);
        financialFlexibilityRiskSubFactorDTO.setItemNo(1);
        financialFlexibilityRiskSubFactorDTO.setDescription("Financial Flexibility");
        financialFlexibilityRiskSubFactorDTO.setWeightage(1.00);
        financialFlexibilityRiskSubFactorDTO.setScore(0D);
        financialFlexibilityRiskSubFactorDTO.setScoreTypeCode("01");
        financialFlexibilityRiskSubFactorDTO.setScoreTypeDescription("Normal");



        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Completion Risk -> Funding Risk - > Financial Flexibility -> Attributes
        // 1.1.1 -> Four Attributes
        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Sponsors with low ability to raise additional fund from various sources including own sources");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);
        riskSubFactorAttributeDTO1.setIsSelected(false);


        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Sponsors with moderate ability to raise additional fund from various sources including own sources");
        riskSubFactorAttributeDTO2.setScore(3.33D);
        riskSubFactorAttributeDTO2.setWeightage(0D);riskSubFactorAttributeDTO2.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Sponsors with high ability to raise additional fund from various sources including own sources");
        riskSubFactorAttributeDTO3.setScore(6.67D);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);
        
        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Sponsors with very high/excellent ability to raise additional fund from various sources including own sources");
        riskSubFactorAttributeDTO4.setScore(10.00D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);



        // Collect RiskSubFactorAttrributes
        financialFlexibilityRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        financialFlexibilityRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        financialFlexibilityRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        financialFlexibilityRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);

        // Collect FundingRiskFactor
        fundingRiskFactorDTO.addRiskSubFactorDTO(financialFlexibilityRiskSubFactorDTO);







        //                       Risk Type 1 - Risk Component 1 -> Risk Factor 2
        // 1.2                            Key Completion Risk
        RiskFactorDTO keyCompletionRiskFactorDTO = new RiskFactorDTO();
        keyCompletionRiskFactorDTO.setId(null);
        keyCompletionRiskFactorDTO.setItemNo(2);
        keyCompletionRiskFactorDTO.setDescription("Key Completion Risk");
        keyCompletionRiskFactorDTO.setWeightage(0.70); //70%
        keyCompletionRiskFactorDTO.setComputingMethodCode("01");
        keyCompletionRiskFactorDTO.setComputingMethodDescription("Weighted");
        keyCompletionRiskFactorDTO.setScoreTypeCode("01");
        keyCompletionRiskFactorDTO.setScoreTypeDescription("Normal");
        keyCompletionRiskFactorDTO.setScore(0D);



        //                       Risk Type 1 - Risk Component 1 - Risk Factor 2- Risk SubFactor
        //1.2.1                          Key Completion Risk  -> Clearances
        RiskSubFactorDTO clearancesRiskSubFactorDTO = new RiskSubFactorDTO();
        clearancesRiskSubFactorDTO.setId(null);
        clearancesRiskSubFactorDTO.setItemNo(1);
        clearancesRiskSubFactorDTO.setDescription("Clearances");
        clearancesRiskSubFactorDTO.setWeightage(0.35); //35%
        clearancesRiskSubFactorDTO.setScore(0D);
        clearancesRiskSubFactorDTO.setScoreTypeCode("01");
        clearancesRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //                       Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 1 -> Risk Sub Factor Attributes
        //                      Key Completion Risk -> KeyCompletion Risk -> Clearances  -> Attributes
        // 1.2.1 -> Five Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Majority of critical clearances are still to be obtained");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Project is at the mid-way path as far as obtaining clearances are concerned");
        riskSubFactorAttributeDTO2.setScore(2.5D);
        riskSubFactorAttributeDTO2.setWeightage(0D);riskSubFactorAttributeDTO2.setIsSelected(false);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("A few critical clearances are not yet procured but are likely to be obtained in a short period of time.");
        riskSubFactorAttributeDTO3.setScore(5.00D);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);
        
        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("All the critical and most significant clearances are obtained. Very few clearances pending which are in the final stage of being procured");
        riskSubFactorAttributeDTO4.setScore(7.50D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("The project has obtained all relevant clearance required from all the agencies.");
        riskSubFactorAttributeDTO5.setScore(10.00D);
        riskSubFactorAttributeDTO5.setWeightage(0D);riskSubFactorAttributeDTO5.setIsSelected(false);

        //Collect RiskSubFactorAttributes
        clearancesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        clearancesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        clearancesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        clearancesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        clearancesRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);




        //                       Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 2
        //1.2.2                          Key Completion Risk  -> Gestation Period
        RiskSubFactorDTO gestationPeriodRiskSubFactorDTO = new RiskSubFactorDTO();
        gestationPeriodRiskSubFactorDTO.setId(null);
        gestationPeriodRiskSubFactorDTO.setItemNo(2);
        gestationPeriodRiskSubFactorDTO.setDescription("Gestation Period");
        gestationPeriodRiskSubFactorDTO.setWeightage(0.15); //15%
        gestationPeriodRiskSubFactorDTO.setScore(0D);
        gestationPeriodRiskSubFactorDTO.setScoreTypeCode("01");
        gestationPeriodRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //                      Key Completion Risk -> KeyCompletion Risk -> Gestation Period   -> Attributes
        // 1.2.2 -> Six Attributes


        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Gestation period above 24 months");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Gestation period between 18 - 24 months");
        riskSubFactorAttributeDTO2.setScore(2.00D);
        riskSubFactorAttributeDTO2.setWeightage(0D);riskSubFactorAttributeDTO2.setIsSelected(false);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Gestation period between 12 - 18 months");
        riskSubFactorAttributeDTO3.setScore(4.00D);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);riskSubFactorAttributeDTO3.setIsSelected(false);riskSubFactorAttributeDTO3.setIsSelected(false);riskSubFactorAttributeDTO3.setIsSelected(false);riskSubFactorAttributeDTO3.setIsSelected(false);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Gestation period between 6 - 12 months");
        riskSubFactorAttributeDTO4.setScore(6.0D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Gestation Period less than 6 months but some clearance pending");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(0D);


        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Gestation Period less than 6 months with all clearance in place");
        riskSubFactorAttributeDTO6.setScore(10.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);

        //Collect RiskSubFactorAttributes
        gestationPeriodRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        gestationPeriodRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        gestationPeriodRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        gestationPeriodRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        gestationPeriodRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        gestationPeriodRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);




        //                       Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 2
        //1.2.3                         Key Completion Risk  -> Land Acquisition
        RiskSubFactorDTO landAcquistionRiskSubFactorDTO = new RiskSubFactorDTO();
        landAcquistionRiskSubFactorDTO.setId(null);
        landAcquistionRiskSubFactorDTO.setItemNo(3);
        landAcquistionRiskSubFactorDTO.setDescription("Land Acquisition");
        landAcquistionRiskSubFactorDTO.setWeightage(0.30); //30%
        landAcquistionRiskSubFactorDTO.setScore(0D);
        landAcquistionRiskSubFactorDTO.setScoreTypeCode("01");
        landAcquistionRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //                      Key Completion Risk -> KeyCompletion Risk -> Land Acquisition  -> Attributes
        // 1.2.3 -> Six Attributes

        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Leasing arrangement with private parties coterminous with earlier envisaged project duration not covered for delays");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Leasing arrangement with government agencies coterminous with earlier envisaged project duration not covered for delays");
        riskSubFactorAttributeDTO2.setScore(2.00D);
        riskSubFactorAttributeDTO2.setWeightage(0D);riskSubFactorAttributeDTO2.setIsSelected(false);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Leasing arrangement with private land owners upto the complete project duration and leasing arrangements are favourable for the borrower");
        riskSubFactorAttributeDTO3.setScore(4.00D);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Combination of leasing arrangement with government agencies and private land owners upto the complete project duration");
        riskSubFactorAttributeDTO4.setScore(6.0D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Land acquisition is in the advanced stage from private land owners or government agencies");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(00D);riskSubFactorAttributeDTO5.setIsSelected(false);


        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Land acquired from private land owners or government agencies for the project / Land leased from government for the project");
        riskSubFactorAttributeDTO6.setScore(10.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);


        //Collect RiskSubFactorAttributes
        landAcquistionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        landAcquistionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        landAcquistionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        landAcquistionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        landAcquistionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        landAcquistionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);






        //                       Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 2
        //1.2.4                         Key Completion Risk  -> Evacuation Infrastructure Construction Risk
        RiskSubFactorDTO evacuationInfraConstructionRiskSubFactorDTO = new RiskSubFactorDTO();
        evacuationInfraConstructionRiskSubFactorDTO.setId(null);
        evacuationInfraConstructionRiskSubFactorDTO.setItemNo(4);
        evacuationInfraConstructionRiskSubFactorDTO.setDescription("Evacuation Infrastructure Construction Risk");
        evacuationInfraConstructionRiskSubFactorDTO.setWeightage(0.20); //30%
        evacuationInfraConstructionRiskSubFactorDTO.setScore(0D);
        evacuationInfraConstructionRiskSubFactorDTO.setScoreTypeCode("01");
        evacuationInfraConstructionRiskSubFactorDTO.setScoreTypeDescription("Normal");



        //                      Key Completion Risk -> KeyCompletion Risk -> Evacuation Infrastructure Construction Risk  -> Attributes
        // 1.2.4 -> Six Attributes

        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Evacuation approval is not in place");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Evacuation approval received, entire work to be completed by state agency");
        riskSubFactorAttributeDTO2.setScore(2.00D);
        riskSubFactorAttributeDTO2.setWeightage(0D);riskSubFactorAttributeDTO2.setIsSelected(false);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Evacuation approval received, entire work to be completed by borrower & state agency. Work is yet to commence.");
        riskSubFactorAttributeDTO3.setScore(4.00D);
        riskSubFactorAttributeDTO3.setWeightage(0D);riskSubFactorAttributeDTO3.setIsSelected(false);
        
        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Evacuation approval received, entire work to be completed by borrower. Work is yet to commence.");
        riskSubFactorAttributeDTO4.setScore(6.0D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Evacuation approval received, entire work to be completed by borrower. Work has commenced with work not nearing completion.");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(00D);riskSubFactorAttributeDTO5.setIsSelected(false);


        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Evacuation approval received, entire work to be completed by borrower, work nearing completion");
        riskSubFactorAttributeDTO6.setScore(10.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);


        //Collect RiskSubFactorAttributes
        evacuationInfraConstructionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        evacuationInfraConstructionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        evacuationInfraConstructionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        evacuationInfraConstructionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        evacuationInfraConstructionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        evacuationInfraConstructionRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);


        // Collect Risk Sub Factors into RiskFactors
        // --- Completion Risk
        completionRiskComponentDTO.addRiskFactorDTO(fundingRiskFactorDTO);

        // ---- Key Completion Risk
        keyCompletionRiskFactorDTO.addRiskSubFactorDTO(clearancesRiskSubFactorDTO);
        keyCompletionRiskFactorDTO.addRiskSubFactorDTO(gestationPeriodRiskSubFactorDTO);
        keyCompletionRiskFactorDTO.addRiskSubFactorDTO(landAcquistionRiskSubFactorDTO);
        keyCompletionRiskFactorDTO.addRiskSubFactorDTO(evacuationInfraConstructionRiskSubFactorDTO);



        // Collect RiskFactors into Risk Components
        completionRiskComponentDTO.addRiskFactorDTO(fundingRiskFactorDTO);
        completionRiskComponentDTO.addRiskFactorDTO(keyCompletionRiskFactorDTO);


        return completionRiskComponentDTO;



    }
}
