package com.pfs.riskmodel.Renewable.BuildPhase.ProjectRisks;

import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskFactorDTO;
import com.pfs.riskmodel.dto.RiskSubFactorAttributeDTO;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class RP_ExecutionRiskDTO {


    public RiskComponentDTO getExecutionRiskComponentDTO(){
        /**********************************************************************************************************************
         *  Risk Component 2 : Execution Risk
         **********************************************************************************************************************/

        RiskComponentDTO executionRiskRiskComponentDTO = new RiskComponentDTO();
        executionRiskRiskComponentDTO.setId(null);
        executionRiskRiskComponentDTO.setItemNo(2);
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
        capabilityOfSponsorRiskSubFactorDTO.setScoreTypeCode("01");
        capabilityOfSponsorRiskSubFactorDTO.setScoreTypeDescription("Normal");




        //                      Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 1 -> Risk Sub Factor Attributes
        //                      Execution Risk  -> Construction Risk -> Capability of Sponsor -> Attributes
        // 2.1.1 -> Six Attributes
        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("New entrant in the area of project management");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(0D);


        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Experience in projects exists but new entrant in infrastructure projects");
        riskSubFactorAttributeDTO2.setScore(2.0D);
        riskSubFactorAttributeDTO2.setWeightage(0D);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Local player with reasonable experience in execution of projects of similar type");
        riskSubFactorAttributeDTO3.setScore(4.00D);
        riskSubFactorAttributeDTO3.setWeightage(0D);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Good experience in projects of similar nature");
        riskSubFactorAttributeDTO4.setScore(6.00D);
        riskSubFactorAttributeDTO4.setWeightage(0D);


        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Very Good experience in projects of similar nature");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(0D);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(5);
        riskSubFactorAttributeDTO6.setDescription("Sponsor with extensive international / national experience");
        riskSubFactorAttributeDTO6.setScore(10.00D);
        riskSubFactorAttributeDTO6.setWeightage(0D);


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
        capabilityOfContractorRiskSubFactorDTO.setScoreTypeCode("01");
        capabilityOfContractorRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //                      Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 1 -> Risk Sub Factor Attributes
        //                      Execution Risk  -> Construction Risk -> Capability of Contractor -> Attributes
        // 2.1.2 -> Six Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("New entrant in the area of construction");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Experience in construction exists but new entrant in infrastructure construction");
        riskSubFactorAttributeDTO2.setScore(2.0D);
        riskSubFactorAttributeDTO2.setWeightage(0D);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Local player with reasonable experience in execution of projects of similar type");
        riskSubFactorAttributeDTO3.setScore(4.00D);
        riskSubFactorAttributeDTO3.setWeightage(0D);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Good experience in projects of similar nature");
        riskSubFactorAttributeDTO4.setScore(6.00D);
        riskSubFactorAttributeDTO4.setWeightage(0D);


        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Very Good experience in projects of similar nature");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(0D);

        riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(5);
        riskSubFactorAttributeDTO6.setDescription("Contractor with extensive international / national experience");
        riskSubFactorAttributeDTO6.setScore(10.00D);
        riskSubFactorAttributeDTO6.setWeightage(0D);


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
        complexityOfProjectRiskSubFactorDTO.setScoreTypeCode("01");
        complexityOfProjectRiskSubFactorDTO.setScoreTypeDescription("Normal");



        //                      Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 3 -> Risk Sub Factor Attributes
        //                      Execution Risk  -> Construction Risk -> Complexity of the Project-> Attributes
        // 2.1.3 -> Five Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Project is extremely complicated and completion is likely to be affected by them");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("The project has high level of complexity in terms of project construction");
        riskSubFactorAttributeDTO2.setScore(2.0D);
        riskSubFactorAttributeDTO2.setWeightage(0D);


        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("The project has average level of challenges in terms of project construction");
        riskSubFactorAttributeDTO3.setScore(4.00D);
        riskSubFactorAttributeDTO3.setWeightage(0D);


        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("The project has few complexities and may not involve significant slippages on account of the same");
        riskSubFactorAttributeDTO4.setScore(6.00D);
        riskSubFactorAttributeDTO4.setWeightage(0D);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Extremely low level of difficulty to execute the project (e.g.-Project close to completion)");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(0D);


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
        safeguarfsInContractRiskSubFactorDTO.setScoreTypeCode("01");
        safeguarfsInContractRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //                      Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 3 -> Risk Sub Factor Attributes
        //                      Execution Risk  -> Construction Risk -> Safeguards in Contract-> Attributes
        // 2.1.4 -> Six Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("The contract does not mention any kind of assurances of safeguards");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("The contract contains very poor level of safeguards");
        riskSubFactorAttributeDTO2.setScore(2.0D);
        riskSubFactorAttributeDTO2.setWeightage(0D);

        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("The contract ensures minimal level of safeguards");
        riskSubFactorAttributeDTO3.setScore(4.00D);
        riskSubFactorAttributeDTO3.setWeightage(0D);

        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("The contract ensures moderate level of safeguards");
        riskSubFactorAttributeDTO4.setScore(6.00D);
        riskSubFactorAttributeDTO4.setWeightage(0D);

        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("The contract ensures high level of safeguards");
        riskSubFactorAttributeDTO5.setScore(8.00D);
        riskSubFactorAttributeDTO5.setWeightage(0D);


        riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("The contract ensures very high level of safeguards");
        riskSubFactorAttributeDTO5.setScore(10.00D);
        riskSubFactorAttributeDTO5.setWeightage(0D);

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
        qualityOfEquipmentRiskSubFactorDTO.setScoreTypeCode("01");
        qualityOfEquipmentRiskSubFactorDTO.setScoreTypeDescription("Normal");

//                      Risk Type 1 - Risk Component 2 - Risk Factor 2 - Risk SubFactor 3 -> Risk Sub Factor Attributes
        //                      Execution Risk  -> Construction Risk -> Quality of Equipment -> Attributes
        // 2.1.4 -> Six Attributes
        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Proven technology in world, however yet to be established in India");
        riskSubFactorAttributeDTO1.setScore(2D);
        riskSubFactorAttributeDTO1.setWeightage(0D);

        riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Reliable supplier but expected challenges in the Indian conditions");
        riskSubFactorAttributeDTO2.setScore(4.0D);
        riskSubFactorAttributeDTO2.setWeightage(0D);


        riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Reliable supplier with proven delivery track record");
        riskSubFactorAttributeDTO3.setScore(6.00D);
        riskSubFactorAttributeDTO3.setWeightage(0D);


        riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Supplier with impeccable track record");
        riskSubFactorAttributeDTO4.setScore(8.00D);
        riskSubFactorAttributeDTO4.setWeightage(0D);

        qualityOfEquipmentRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        qualityOfEquipmentRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        qualityOfEquipmentRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        qualityOfEquipmentRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);



        //Collection Risk Sub Factor
        constructionRiskRiskFactorDTO.addRiskSubFactorDTO(capabilityOfSponsorRiskSubFactorDTO);
        constructionRiskRiskFactorDTO.addRiskSubFactorDTO(capabilityOfContractorRiskSubFactorDTO);
        constructionRiskRiskFactorDTO.addRiskSubFactorDTO(complexityOfProjectRiskSubFactorDTO);
        constructionRiskRiskFactorDTO.addRiskSubFactorDTO(qualityOfEquipmentRiskSubFactorDTO);

        // Collect RiskFactors into Risk Components
        executionRiskRiskComponentDTO.addRiskFactorDTO(constructionRiskRiskFactorDTO);

        return executionRiskRiskComponentDTO;

    }

}
