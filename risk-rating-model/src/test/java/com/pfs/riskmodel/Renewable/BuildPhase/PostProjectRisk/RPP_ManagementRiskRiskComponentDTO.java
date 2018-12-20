package com.pfs.riskmodel.Renewable.BuildPhase.PostProjectRisk;

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

        //RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        /**********************************************************************************************************************
         *  Risk Component 2 : Management Risk - 20%
         *  This has only two levels below and therefore the Risk Factor will be a dummy entry (Management Risk Factor)
         **********************************************************************************************************************/

        //                       Risk Type 1 - Risk Component 1
        //  1                       Management Risk
        RiskComponentDTO managementRiskComponentDTO = new RiskComponentDTO();
        managementRiskComponentDTO.setId(null);
        managementRiskComponentDTO.setItemNo(1);
        managementRiskComponentDTO.setDescription("Management Risk");
        managementRiskComponentDTO.setWeightage(0.32D);
        managementRiskComponentDTO.setComputingMethodCode("01");
        managementRiskComponentDTO.setComputingMethodDescription("Weighted");
        managementRiskComponentDTO.setScoreTypeCode("01");
        managementRiskComponentDTO.setScoreTypeDescription("Normal");
        managementRiskComponentDTO.setScore(0D);



        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1
        //1.1                            Management Risk -> Management Risk Factor (DUMMY ENTRY)
        // *  Value Derived from the Risk Sub Factor is just passed on - Therefore Computing Method = EQUALS
        //                                       100%

        RiskFactorDTO managementRiskFactorDTO = new RiskFactorDTO();
        managementRiskFactorDTO.setId(null);
        managementRiskFactorDTO.setItemNo(1);
        managementRiskFactorDTO.setDescription("Management Risk Factor");
        managementRiskFactorDTO.setWeightage(1.00);
        managementRiskFactorDTO.setComputingMethodCode("05");
        managementRiskFactorDTO.setComputingMethodDescription("Equals");
        managementRiskFactorDTO.setScoreTypeCode("01");
        managementRiskFactorDTO.setScoreTypeDescription("Normal");
        managementRiskFactorDTO.setScore(0D);


        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1
        // 1.1.1                      Financial Risk -> Financial Risk Factor - > Sensitivity to Base Case Revenue
        ///                                     15%
        RiskSubFactorDTO sensitivityToBusinessCaseRevenueRiskSubFactorDTO = new RiskSubFactorDTO();
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.setId(null);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.setItemNo(1);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.setDescription("Sensitivity to Base Case Revenue");
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.setWeightage(0.15D);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.setScore(0D);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.setScoreTypeCode("01");
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //                       Risk Type 1 - Risk Component 1 - Risk Factor 1 - Risk SubFactor 1 ->Risk Sub Factor Attributes
        //                       Financial Risk -> Financial Risk Factor - > Sensitivity to Base Case Revenue -> Attributes
        // 1.1.1 -> Four Attributes

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO1 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO1.setId(null);
        riskSubFactorAttributeDTO1.setItemNo(1);
        riskSubFactorAttributeDTO1.setDescription("Less than 1");
        riskSubFactorAttributeDTO1.setScore(0D);
        riskSubFactorAttributeDTO1.setWeightage(0D);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO2 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO2.setId(null);
        riskSubFactorAttributeDTO2.setItemNo(2);
        riskSubFactorAttributeDTO2.setDescription("");
        riskSubFactorAttributeDTO2.setScore(0.00);
        riskSubFactorAttributeDTO2.setWeightage(0D);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO3 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO3.setId(null);
        riskSubFactorAttributeDTO3.setItemNo(3);
        riskSubFactorAttributeDTO3.setDescription("");
        riskSubFactorAttributeDTO3.setScore(0.00);
        riskSubFactorAttributeDTO3.setWeightage(0D);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO4 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO4.setId(null);
        riskSubFactorAttributeDTO4.setItemNo(4);
        riskSubFactorAttributeDTO4.setDescription("");
        riskSubFactorAttributeDTO4.setScore(0.00D);
        riskSubFactorAttributeDTO4.setWeightage(0D);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO5 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO5.setId(null);
        riskSubFactorAttributeDTO5.setItemNo(5);
        riskSubFactorAttributeDTO5.setDescription("");
        riskSubFactorAttributeDTO5.setScore(0.00D);
        riskSubFactorAttributeDTO5.setWeightage(0D);

        RiskSubFactorAttributeDTO riskSubFactorAttributeDTO6 = new RiskSubFactorAttributeDTO();
        riskSubFactorAttributeDTO6.setId(null);
        riskSubFactorAttributeDTO6.setItemNo(6);
        riskSubFactorAttributeDTO6.setDescription("");
        riskSubFactorAttributeDTO6.setScore(00.00D);
        riskSubFactorAttributeDTO6.setWeightage(0D);

        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO1);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO2);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO3);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO4);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO5);
        sensitivityToBusinessCaseRevenueRiskSubFactorDTO.addRiskSubFactorAttribute(riskSubFactorAttributeDTO6);


        //TODO This is NOT COMPLETE
        managementRiskFactorDTO.addRiskSubFactorDTO(sensitivityToBusinessCaseRevenueRiskSubFactorDTO);
        managementRiskComponentDTO.addRiskFactorDTO(managementRiskFactorDTO);



        return  managementRiskComponentDTO;
    }
}
