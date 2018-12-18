package com.pfs.riskmodel.riskratingmodel;

import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.dto.*;

/**
 * Created by sajeev on 18-Dec-18.
 */
public class RenewableProjectBuildData {

    public  static  RiskModelTemplate riskModelTemplate ;

    public static RiskModelTemplate getRenewableProjectBuildPhaseData( RiskModelTemplate riskModelTemplate) {

    //---------------------------------------------------------------------------------//
    // ---------------------------RiskType 1 -----------------------------------------//
    //                      Project Implementation Risk

        RiskTypeDTO riskTypeDTO = new RiskTypeDTO();
        riskTypeDTO.setId(null);
        riskTypeDTO.setItemNo(1);
        riskTypeDTO.setDescription("Project Implementation Risk");
        riskTypeDTO.setScore(0D);


        /**********************************************************************************************************************
         *  Risk Component 1 : Completion Risk
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

        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Completion Risk -> Funding Risk - > Financial Flexibility -> Attributes
        // 1.1.1 -> Four Attributes
        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Sponsors with low ability to raise additional fund from various sources including own sources");
        riskSubFactorAttributeDTO1.setScore(0D);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Sponsors with moderate ability to raise additional fund from various sources including own sources");
        riskSubFactorAttributeDTO2.setScore(3.33D);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Sponsors with high ability to raise additional fund from various sources including own sources");
        riskSubFactorAttributeDTO3.setScore(6.67D);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Sponsors with very high/excellent ability to raise additional fund from various sources including own sources");
        riskSubFactorAttributeDTO4.setScore(10.00D);



        // Collect RiskSubFactorAttrributes
        financialFlexibilityRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        financialFlexibilityRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        financialFlexibilityRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        financialFlexibilityRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);

        // Collect RiskFactor
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


        //                       Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 1 -> Risk Sub Factor Attributes
        //                      Key Completion Risk -> KeyCompletion Risk -> Clearances  -> Attributes
        // 1.2.1 -> Five Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Majority of critical clearances are still to be obtained");
        riskSubFactorAttributeDTO1.setScore(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Project is at the mid-way path as far as obtaining clearances are concerned");
        riskSubFactorAttributeDTO2.setScore(2.5D);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("A few critical clearances are not yet procured but are likely to be obtained in a short period of time.");
        riskSubFactorAttributeDTO3.setScore(5.00D);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("All the critical and most significant clearances are obtained. Very few clearances pending which are in the final stage of being procured");
        riskSubFactorAttributeDTO4.setScore(7.50D);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("The project has obtained all relevant clearance required from all the agencies.");
        riskSubFactorAttributeDTO5.setScore(10.00D);

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


        //                      Key Completion Risk -> KeyCompletion Risk -> Gestation Period   -> Attributes
        // 1.2.2 -> Six Attributes


        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Gestation period above 24 months");
        riskSubFactorAttributeDTO1.setScore(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Gestation period between 18 - 24 months");
        riskSubFactorAttributeDTO2.setScore(2.00D);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Gestation period between 12 - 18 months");
        riskSubFactorAttributeDTO3.setScore(4.00D);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Gestation period between 6 - 12 months");
        riskSubFactorAttributeDTO4.setScore(6.0D);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Gestation Period less than 6 months but some clearance pending");
        riskSubFactorAttributeDTO5.setScore(8.00D);


        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Gestation Period less than 6 months with all clearance in place");
        riskSubFactorAttributeDTO6.setScore(10.00D);

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

        //                      Key Completion Risk -> KeyCompletion Risk -> Land Acquisition  -> Attributes
        // 1.2.3 -> Six Attributes

        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Leasing arrangement with private parties coterminous with earlier envisaged project duration not covered for delays");
        riskSubFactorAttributeDTO1.setScore(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Leasing arrangement with government agencies coterminous with earlier envisaged project duration not covered for delays");
        riskSubFactorAttributeDTO2.setScore(2.00D);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Leasing arrangement with private land owners upto the complete project duration and leasing arrangements are favourable for the borrower");
        riskSubFactorAttributeDTO3.setScore(4.00D);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Combination of leasing arrangement with government agencies and private land owners upto the complete project duration");
        riskSubFactorAttributeDTO4.setScore(6.0D);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Land acquisition is in the advanced stage from private land owners or government agencies");
        riskSubFactorAttributeDTO5.setScore(8.00D);


        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Land acquired from private land owners or government agencies for the project / Land leased from government for the project");
        riskSubFactorAttributeDTO6.setScore(10.00D);


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



        //                      Key Completion Risk -> KeyCompletion Risk -> Evacuation Infrastructure Construction Risk  -> Attributes
        // 1.2.4 -> Six Attributes

        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Evacuation approval is not in place");
        riskSubFactorAttributeDTO1.setScore(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Evacuation approval received, entire work to be completed by state agency");
        riskSubFactorAttributeDTO2.setScore(2.00D);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Evacuation approval received, entire work to be completed by borrower & state agency. Work is yet to commence.");
        riskSubFactorAttributeDTO3.setScore(4.00D);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Evacuation approval received, entire work to be completed by borrower. Work is yet to commence.");
        riskSubFactorAttributeDTO4.setScore(6.0D);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Evacuation approval received, entire work to be completed by borrower. Work has commenced with work not nearing completion.");
        riskSubFactorAttributeDTO5.setScore(8.00D);


        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Evacuation approval received, entire work to be completed by borrower, work nearing completion");
        riskSubFactorAttributeDTO6.setScore(10.00D);


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


        /**********************************************************************************************************************
         *  Risk Component 2 : Execution Risk
         **********************************************************************************************************************/

        RiskComponentDTO executionRiskRiskComponentDTO = new RiskComponentDTO();
        executionRiskRiskComponentDTO.setId(null);
        executionRiskRiskComponentDTO.setItemNo(1);
        executionRiskRiskComponentDTO.setDescription("Execution Risk");
        executionRiskRiskComponentDTO.setWeightage(0.50);  // 50%
        executionRiskRiskComponentDTO.setComputingMethodCode("01");
        executionRiskRiskComponentDTO.setComputingMethodDescription("Weighted");
        executionRiskRiskComponentDTO.setScoreTypeCode("01");
        executionRiskRiskComponentDTO.setScoreTypeDescription("Normal");
        executionRiskRiskComponentDTO.setScore(0D);

        //                       Risk Type 1- Risk Component 2 - Risk Factor 1
        //2.1                             Execution Risk -> Construction Risk

        RiskFactorDTO constructionRiskRiskFactorDTO = new RiskFactorDTO();
        constructionRiskRiskFactorDTO.setId(null);
        constructionRiskRiskFactorDTO.setItemNo(1);
        constructionRiskRiskFactorDTO.setDescription("Construction Risk");
        constructionRiskRiskFactorDTO.setWeightage(1.00); //100%
        constructionRiskRiskFactorDTO.setComputingMethodCode("01");
        constructionRiskRiskFactorDTO.setComputingMethodDescription("Weighted");
        constructionRiskRiskFactorDTO.setScoreTypeCode("01");
        constructionRiskRiskFactorDTO.setScoreTypeDescription("Normal");
        constructionRiskRiskFactorDTO.setScore(0D);





        //                       Risk Type 1 - Risk Component 2 - Risk Factor 1 - Risk SubFactor
        //2.1.1                         Execution Risk  -> Construction Risk -> Capability of Sponsor
        RiskSubFactorDTO capabilityOfSponsorRiskSubFactorDTO = new RiskSubFactorDTO();
        capabilityOfSponsorRiskSubFactorDTO.setId(null);
        capabilityOfSponsorRiskSubFactorDTO.setItemNo(1);
        capabilityOfSponsorRiskSubFactorDTO.setDescription("Capability of Sponsor");
        capabilityOfSponsorRiskSubFactorDTO.setWeightage(0.28); //28%
        capabilityOfSponsorRiskSubFactorDTO.setScore(0D);


        //                      Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 1 -> Risk Sub Factor Attributes
        //                      Execution Risk  -> Construction Risk -> Capability of Sponsor -> Attributes
        // 2.1.1 -> Six Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("New entrant in the area of project management");
        riskSubFactorAttributeDTO1.setScore(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Experience in projects exists but new entrant in infrastructure projects");
        riskSubFactorAttributeDTO2.setScore(2.0D);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Local player with reasonable experience in execution of projects of similar type");
        riskSubFactorAttributeDTO3.setScore(4.00D);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Good experience in projects of similar nature");
        riskSubFactorAttributeDTO4.setScore(6.00D);


        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Very Good experience in projects of similar nature");
        riskSubFactorAttributeDTO5.setScore(8.00D);

        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(5);
        riskSubFactorAttributeDTO6.setDescription("Sponsor with extensive international / national experience");
        riskSubFactorAttributeDTO6.setScore(10.00D);


        //Collect RiskSubFactorAttributes
        capabilityOfSponsorRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        capabilityOfSponsorRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        capabilityOfSponsorRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        capabilityOfSponsorRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        capabilityOfSponsorRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        capabilityOfSponsorRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);



        //                       Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor
        //2.1.2                         Execution Risk  -> Construction Risk -> Capability of Contractor
        RiskSubFactorDTO capabilityOfContractorRiskSubFactorDTO = new RiskSubFactorDTO();
        capabilityOfContractorRiskSubFactorDTO.setId(null);
        capabilityOfContractorRiskSubFactorDTO.setItemNo(1);
        capabilityOfContractorRiskSubFactorDTO.setDescription("Capability of Contractor");
        capabilityOfContractorRiskSubFactorDTO.setWeightage(0.28); //28%
        capabilityOfContractorRiskSubFactorDTO.setScore(0D);


        //                      Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 1 -> Risk Sub Factor Attributes
        //                      Execution Risk  -> Construction Risk -> Capability of Contractor -> Attributes
        // 2.1.2 -> Six Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("New entrant in the area of construction");
        riskSubFactorAttributeDTO1.setScore(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Experience in construction exists but new entrant in infrastructure construction");
        riskSubFactorAttributeDTO2.setScore(2.0D);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Local player with reasonable experience in execution of projects of similar type");
        riskSubFactorAttributeDTO3.setScore(4.00D);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Good experience in projects of similar nature");
        riskSubFactorAttributeDTO4.setScore(6.00D);


        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Very Good experience in projects of similar nature");
        riskSubFactorAttributeDTO5.setScore(8.00D);

        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(5);
        riskSubFactorAttributeDTO6.setDescription("Contractor with extensive international / national experience");
        riskSubFactorAttributeDTO6.setScore(10.00D);


        //Collect RiskSubFactorAttributes
        capabilityOfContractorRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        capabilityOfContractorRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        capabilityOfContractorRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        capabilityOfContractorRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        capabilityOfContractorRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        capabilityOfContractorRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);




        //                       Risk Type 1 - Risk Component 2 - Risk Factor 3 - Risk SubFactor
        //2.1.3                         Execution Risk  -> Construction Risk -> Complexity of the Project
        RiskSubFactorDTO complexityOfProjectRiskSubFactorDTO = new RiskSubFactorDTO();
        complexityOfProjectRiskSubFactorDTO.setId(null);
        complexityOfProjectRiskSubFactorDTO.setItemNo(1);
        complexityOfProjectRiskSubFactorDTO.setDescription("Complexity of the Project");
        complexityOfProjectRiskSubFactorDTO.setWeightage(0.16); //16%
        complexityOfProjectRiskSubFactorDTO.setScore(0D);


        //                      Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 3 -> Risk Sub Factor Attributes
        //                      Execution Risk  -> Construction Risk -> Complexity of the Project-> Attributes
        // 2.1.3 -> Five Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Project is extremely complicated and completion is likely to be affected by them");
        riskSubFactorAttributeDTO1.setScore(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("The project has high level of complexity in terms of project construction");
        riskSubFactorAttributeDTO2.setScore(2.0D);


        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("The project has average level of challenges in terms of project construction");
        riskSubFactorAttributeDTO3.setScore(4.00D);


        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("The project has few complexities and may not involve significant slippages on account of the same");
        riskSubFactorAttributeDTO4.setScore(6.00D);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Extremely low level of difficulty to execute the project (e.g.-Project close to completion)");
        riskSubFactorAttributeDTO5.setScore(8.00D);


        //Collect RiskSubFactorAttributes
        complexityOfProjectRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        complexityOfProjectRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        complexityOfProjectRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        complexityOfProjectRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        complexityOfProjectRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        complexityOfProjectRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);




        //                       Risk Type 1 - Risk Component 2 - Risk Factor 4 - Risk SubFactor
        //2.1.4                         Execution Risk  -> Construction Risk -> Safeguards in Contract
        RiskSubFactorDTO safeguarfsInContractRiskSubFactorDTO = new RiskSubFactorDTO();
        safeguarfsInContractRiskSubFactorDTO.setId(null);
        safeguarfsInContractRiskSubFactorDTO.setItemNo(1);
        safeguarfsInContractRiskSubFactorDTO.setDescription("Safeguards in Contract");
        safeguarfsInContractRiskSubFactorDTO.setWeightage(0.08); //8%
        safeguarfsInContractRiskSubFactorDTO.setScore(0D);


        //                      Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 3 -> Risk Sub Factor Attributes
        //                      Execution Risk  -> Construction Risk -> Safeguards in Contract-> Attributes
        // 2.1.4 -> Six Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("The contract does not mention any kind of assurances of safeguards");
        riskSubFactorAttributeDTO1.setScore(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("The contract contains very poor level of safeguards");
        riskSubFactorAttributeDTO2.setScore(2.0D);


        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("The contract ensures minimal level of safeguards");
        riskSubFactorAttributeDTO3.setScore(4.00D);


        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("The contract ensures moderate level of safeguards");
        riskSubFactorAttributeDTO4.setScore(6.00D);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("The contract ensures high level of safeguards");
        riskSubFactorAttributeDTO5.setScore(8.00D);


        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("The contract ensures very high level of safeguards");
        riskSubFactorAttributeDTO5.setScore(10.00D);

        //Collect RiskSubFactorAttributes
        safeguarfsInContractRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        safeguarfsInContractRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        safeguarfsInContractRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        safeguarfsInContractRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        safeguarfsInContractRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        safeguarfsInContractRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);


        //                       Risk Type 1 - Risk Component 2 - Risk Factor 5 - Risk SubFactor
        //2.1.5                         Execution Risk  -> Construction Risk -> Quality of Equipment
        RiskSubFactorDTO qualityOfEquipmentRiskSubFactorDTO = new RiskSubFactorDTO();
        qualityOfEquipmentRiskSubFactorDTO.setId(null);
        qualityOfEquipmentRiskSubFactorDTO.setItemNo(1);
        qualityOfEquipmentRiskSubFactorDTO.setDescription("Quality of Equipment");
        qualityOfEquipmentRiskSubFactorDTO.setWeightage(0.20); //20%
        qualityOfEquipmentRiskSubFactorDTO.setScore(0D);



//                      Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 3 -> Risk Sub Factor Attributes
        //                      Execution Risk  -> Construction Risk -> Quality of Equipment -> Attributes
        // 2.1.4 -> Six Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Proven technology in world, however yet to be established in India");
        riskSubFactorAttributeDTO1.setScore(2D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Reliable supplier but expected challenges in the Indian conditions");
        riskSubFactorAttributeDTO2.setScore(4.0D);


        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Reliable supplier with proven delivery track record");
        riskSubFactorAttributeDTO3.setScore(6.00D);


        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Supplier with impeccable track record");
        riskSubFactorAttributeDTO4.setScore(8.00D);



        //TODO
        riskTypeDTO.addRiskComponentDTO(completionRiskComponentDTO);
        riskTypeDTO.addRiskComponentDTO(executionRiskRiskComponentDTO);


        // Collect RiskFactors into Risk Components
        executionRiskRiskComponentDTO.addRiskFactorDTO(constructionRiskRiskFactorDTO);



        riskTypeDTO.addRiskComponentDTO(completionRiskComponentDTO);
        riskTypeDTO.addRiskComponentDTO(executionRiskRiskComponentDTO);





        return riskModelTemplate;
    }

}
