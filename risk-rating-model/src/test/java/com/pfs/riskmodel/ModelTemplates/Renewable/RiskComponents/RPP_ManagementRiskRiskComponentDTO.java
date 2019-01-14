package com.pfs.riskmodel.ModelTemplates.Renewable.RiskComponents;

import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskFactorDTO;
import com.pfs.riskmodel.dto.RiskSubFactorAttributeDTO;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;

/**
 * Created by sajeev on 19-Dec-18.
 */
public   class RPP_ManagementRiskRiskComponentDTO {


    //TODO - Review this Fully

    public static RiskComponentDTO managementRiskComponentDTO () {


         /**********************************************************************************************************************
         *  Risk Component 2 : Management Risk - 20%
         *  This has only two levels below and therefore the Risk Factor will be a dummy entry (Management Risk Factor)
         **********************************************************************************************************************/

        //                       Risk Type 1 - Risk Component 1
        //  1                       Management Risk
        RiskComponentDTO managementRiskComponentDTO = new RiskComponentDTO();
        managementRiskComponentDTO.setId(null);
        managementRiskComponentDTO.setItemNo(4);
        managementRiskComponentDTO.setDescription("Management Risk");
        managementRiskComponentDTO.setWeightage(0.17D);
        managementRiskComponentDTO.setComputingMethodCode("05");
        managementRiskComponentDTO.setComputingMethodDescription("Equals");
        managementRiskComponentDTO.setScoreTypeCode("01");
        managementRiskComponentDTO.setScoreTypeDescription("Normal");
        managementRiskComponentDTO.setScore(0D);

        managementRiskComponentDTO.setIsApplicable(true);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1
        //1.1                            Management Risk -> Management Risk Factor (DUMMY ENTRY)
        // *  Value Derived from the Risk Sub Factor is just passed on - Therefore Computing Method = EQUALS
        //                                       100%

        RiskFactorDTO managementRiskFactorDTO = new RiskFactorDTO();
        managementRiskFactorDTO.setId(null);
        managementRiskFactorDTO.setItemNo(1);
        managementRiskFactorDTO.setDescription("Management Risk Factor");
        managementRiskFactorDTO.setWeightage(1.00);
        managementRiskFactorDTO.setComputingMethodCode("01");
        managementRiskFactorDTO.setComputingMethodDescription("Weighted");
        managementRiskFactorDTO.setScoreTypeCode("01");
        managementRiskFactorDTO.setScoreTypeDescription("Normal");
        managementRiskFactorDTO.setScore(0D);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.1.1                      Management Risk -> Management Risk Factor - > Management Capability/Quality
        ///                                     30%
        RiskSubFactorDTO managementCapabilityRiskSubFactorDTO = new RiskSubFactorDTO();
        managementCapabilityRiskSubFactorDTO.setId(null);
        managementCapabilityRiskSubFactorDTO.setItemNo(1);
        managementCapabilityRiskSubFactorDTO.setDescription("Management Capability/Quality");
        managementCapabilityRiskSubFactorDTO.setWeightage(0.30D);
        managementCapabilityRiskSubFactorDTO.setScore(0D);
        managementCapabilityRiskSubFactorDTO.setScoreTypeCode("01");
        managementCapabilityRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Financial Risk -> Financial Risk Factor - > Management Capability/Quality -> Attributes
        // 1.1.1 -> Six Attributes

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Strong comfort on the promoters.");
        riskSubFactorAttributeDTO1.setScore(10D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Good comfort on the promoters.");
        riskSubFactorAttributeDTO2.setScore(8.00);
        riskSubFactorAttributeDTO2.setWeightage(00D);riskSubFactorAttributeDTO2.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Above average comfort on the management & promoter");
        riskSubFactorAttributeDTO3.setScore(6.00);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Average comfort on Promoter/ Management");
        riskSubFactorAttributeDTO4.setScore(4.00D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Low comfort on Promoter/ Management");
        riskSubFactorAttributeDTO5.setScore(2.00D);
        riskSubFactorAttributeDTO5.setWeightage(00D);riskSubFactorAttributeDTO5.setIsSelected(false);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Very low/no comfort on Promoter/ Management");
        riskSubFactorAttributeDTO6.setScore(00.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);

        //Collect Risk Sub Factor Attributes
        managementCapabilityRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        managementCapabilityRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        managementCapabilityRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        managementCapabilityRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        managementCapabilityRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        managementCapabilityRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.1.2                      Management Risk -> Management Risk Factor - > Financial Strength
        ///                                     30%
        RiskSubFactorDTO financialStrengthRiskSubFactorDTO = new RiskSubFactorDTO();
        financialStrengthRiskSubFactorDTO.setId(null);
        financialStrengthRiskSubFactorDTO.setItemNo(2);
        financialStrengthRiskSubFactorDTO.setDescription("Financial Strength");
        financialStrengthRiskSubFactorDTO.setWeightage(0.30D);
        financialStrengthRiskSubFactorDTO.setScore(0D);
        financialStrengthRiskSubFactorDTO.setScoreTypeCode("01");
        financialStrengthRiskSubFactorDTO.setScoreTypeDescription("Normal");


        riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Company is part of an extremely financially sound group");
        riskSubFactorAttributeDTO1.setScore(10D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);

          riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("The group has high financial strength");
        riskSubFactorAttributeDTO2.setScore(8.00);
        riskSubFactorAttributeDTO2.setWeightage(00D);riskSubFactorAttributeDTO2.setIsSelected(false);

          riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("The group has above average financial strength");
        riskSubFactorAttributeDTO3.setScore(6.00);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);

         riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("The group has average financial strength");
        riskSubFactorAttributeDTO4.setScore(4.00D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);

         riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("The group has low financial strength");
        riskSubFactorAttributeDTO5.setScore(2.00D);
        riskSubFactorAttributeDTO5.setWeightage(00D);riskSubFactorAttributeDTO5.setIsSelected(false);

         riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("The group has very low financial strength");
        riskSubFactorAttributeDTO6.setScore(00.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);


        //Collect Risk Sub Factor Attributes
        financialStrengthRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        financialStrengthRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        financialStrengthRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        financialStrengthRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        financialStrengthRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        financialStrengthRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);

        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.1.3                     Management Risk -> Management Risk Factor - > Credit Track Record
        ///                                     40%
        RiskSubFactorDTO creditTrackRecordRiskSubFactorDTO = new RiskSubFactorDTO();
        creditTrackRecordRiskSubFactorDTO.setId(null);
        creditTrackRecordRiskSubFactorDTO.setItemNo(3);
        creditTrackRecordRiskSubFactorDTO.setDescription("Credit Track Record");
        creditTrackRecordRiskSubFactorDTO.setWeightage(0.40D);
        creditTrackRecordRiskSubFactorDTO.setScore(0D);
        creditTrackRecordRiskSubFactorDTO.setScoreTypeCode("01");
        creditTrackRecordRiskSubFactorDTO.setScoreTypeDescription("Normal");


          riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Extremely strong credit track record");
        riskSubFactorAttributeDTO1.setScore(10D);
        riskSubFactorAttributeDTO1.setWeightage(00D);riskSubFactorAttributeDTO1.setIsSelected(false);

          riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("Strong credit track record");
        riskSubFactorAttributeDTO2.setScore(8.00);
        riskSubFactorAttributeDTO2.setWeightage(00D);riskSubFactorAttributeDTO2.setIsSelected(false);

          riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("Above average credit track record");
        riskSubFactorAttributeDTO3.setScore(6.00);
        riskSubFactorAttributeDTO3.setWeightage(00D);riskSubFactorAttributeDTO3.setIsSelected(false);

          riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("Average credit track record");
        riskSubFactorAttributeDTO4.setScore(4.00D);
        riskSubFactorAttributeDTO4.setWeightage(00D);riskSubFactorAttributeDTO4.setIsSelected(false);

          riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("Below average credit track record");
        riskSubFactorAttributeDTO5.setScore(2.00D);
        riskSubFactorAttributeDTO5.setWeightage(00D);riskSubFactorAttributeDTO5.setIsSelected(false);

          riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("Poor credit track record");
        riskSubFactorAttributeDTO6.setScore(0.00D);
        riskSubFactorAttributeDTO6.setWeightage(00D);riskSubFactorAttributeDTO6.setIsSelected(false);


        //Collect Risk Sub Factor Attributes
        creditTrackRecordRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        creditTrackRecordRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        creditTrackRecordRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        creditTrackRecordRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        creditTrackRecordRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        creditTrackRecordRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.1.4                     Management Risk -> Management Risk Factor - > Management Structure
        ///                                     DEFLATOR
        RiskSubFactorDTO managementStructureRiskSubFactorDTO = new RiskSubFactorDTO();
        managementStructureRiskSubFactorDTO.setId(null);
        managementStructureRiskSubFactorDTO.setItemNo(4);
        managementStructureRiskSubFactorDTO.setDescription("Management Structure");
        managementStructureRiskSubFactorDTO.setWeightage(0.00D);
        managementStructureRiskSubFactorDTO.setScore(0D);
        managementStructureRiskSubFactorDTO.setScoreTypeCode("02");
        managementStructureRiskSubFactorDTO.setScoreTypeDescription("Deflator");


          riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("The Sponsor / Promoter has significant experience in Power Industry and owns multiple projects");
        riskSubFactorAttributeDTO1.setScore(1.00D);
        riskSubFactorAttributeDTO1.setWeightage(0.00D);riskSubFactorAttributeDTO1.setIsSelected(false);

          riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("The Promoter / Sponsor has significant experience in the industry; however, this is the first project");
        riskSubFactorAttributeDTO2.setScore(0.90);
        riskSubFactorAttributeDTO2.setWeightage(0.00D);riskSubFactorAttributeDTO2.setIsSelected(false);

          riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("The promoter / Sponsor are new entrant into the business");
        riskSubFactorAttributeDTO3.setScore(0.75);
        riskSubFactorAttributeDTO3.setWeightage(0.00D);riskSubFactorAttributeDTO3.setIsSelected(false);

          riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("The Promoter / Sponsors are only equity investors and the management succession plan is uncertain");
        riskSubFactorAttributeDTO4.setScore(0.50D);
        riskSubFactorAttributeDTO4.setWeightage(0.00D);riskSubFactorAttributeDTO4.setIsSelected(false);


        //Collect Risk Sub Factor Attributes
        managementStructureRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        managementStructureRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        managementStructureRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        managementStructureRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);




        // Collect Risk Sub Factors
        managementRiskFactorDTO.addRiskSubFactorDTO(managementCapabilityRiskSubFactorDTO);
        managementRiskFactorDTO.addRiskSubFactorDTO(financialStrengthRiskSubFactorDTO);
        managementRiskFactorDTO.addRiskSubFactorDTO(creditTrackRecordRiskSubFactorDTO);
        managementRiskFactorDTO.addRiskSubFactorDTO(managementStructureRiskSubFactorDTO);


        // Collect Risk Factor
        managementRiskComponentDTO.addRiskFactorDTO(managementRiskFactorDTO);



        return  managementRiskComponentDTO;
    }
}
