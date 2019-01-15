package com.pfs.riskmodel.ModelTemplates.Renewable.RiskComponents;

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
public class ROP_BP_AccountConductRiskDTO {



    RiskAttribute riskAttribute ;
    List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();




    public RiskComponentDTO getAccountConductRiskDTO() {


        // 5.0 Account Conduct Risk - 13%

        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        List<RiskFactor> riskFactorList = new ArrayList<>();

        riskComponentDTO.setId(null);
        riskComponentDTO.setItemNo(5);
        riskComponentDTO.setComputingMethodCode("05");
        riskComponentDTO.setComputingMethodDescription("Equals");
        riskComponentDTO.setDescription("Account Conduct");
        riskComponentDTO.setScoreTypeDescription("01");
        riskComponentDTO.setScoreTypeCode("01");
        riskComponentDTO.setScoreTypeDescription("Normal");

        riskComponentDTO.setScore(0D);
        riskComponentDTO.setWeightage(0.13D);

        riskComponentDTO.setIsApplicable(true);

        /*
         --------------------------    Risk Factor 1
            5.1 Account Conduct
                 No Concrete Risk Factors - Therefore a dummy Risk Factor called "Account Conduct" is added
         */


        RiskFactorDTO accountConductRiskFactorDTO = new RiskFactorDTO();
        accountConductRiskFactorDTO.setId(null);
        accountConductRiskFactorDTO.setItemNo(1);
        accountConductRiskFactorDTO.setDescription("Account Conduct");
        accountConductRiskFactorDTO.setWeightage(1.000);
        accountConductRiskFactorDTO.setScore(0D);
        accountConductRiskFactorDTO.setScoreTypeCode("01");
        accountConductRiskFactorDTO.setScoreTypeDescription("Normal");
        accountConductRiskFactorDTO.setComputingMethodCode("01");
        accountConductRiskFactorDTO.setComputingMethodDescription("Weighted");


        // 5.1.1        Risk Sub Factor 1
        // Compliance with financial covenants including DSRA - 50%

        RiskSubFactorDTO complianceWithFinCovSubFactorDTO = new RiskSubFactorDTO();
        complianceWithFinCovSubFactorDTO.setId(null);
        complianceWithFinCovSubFactorDTO.setItemNo(1);
        complianceWithFinCovSubFactorDTO.setDescription("Compliance with financial covenants including DSRA");
        complianceWithFinCovSubFactorDTO.setWeightage(0.50D);
        complianceWithFinCovSubFactorDTO.setScore(0D);
        complianceWithFinCovSubFactorDTO.setScoreTypeCode("01");
        complianceWithFinCovSubFactorDTO.setScoreTypeDescription("Normal");

        // 5.1.1        Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "No breaches in last 12 months"));
        riskSubFactorAttributes.add(new RiskAttribute(7D, "No breaches in last 12 months, but some breaches were observed earlier"));
        riskSubFactorAttributes.add(new RiskAttribute(5D, "Minor breaches once or twice in last 12 months"));
        riskSubFactorAttributes.add(new RiskAttribute(3D, "Minor breaches once or twice in last 6 months"));
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Multiple breaches of serious nature over the last 12 months."));



        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS1 =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        complianceWithFinCovSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
        accountConductRiskFactorDTO.addRiskSubFactorDTO(complianceWithFinCovSubFactorDTO);



        // 5.1.2       Risk Sub Factor 2
        // Trend in Working capital Drawing Power (DP) limits 50%


        RiskSubFactorDTO trendInWorkCapDPLimitsSubFactorDTO = new RiskSubFactorDTO();
        trendInWorkCapDPLimitsSubFactorDTO.setId(null);
        trendInWorkCapDPLimitsSubFactorDTO.setItemNo(2);
        trendInWorkCapDPLimitsSubFactorDTO.setDescription("Trend in Working capital Drawing Power (DP) limits");
        trendInWorkCapDPLimitsSubFactorDTO.setWeightage(0.50D);
        trendInWorkCapDPLimitsSubFactorDTO.setScore(0D);
        trendInWorkCapDPLimitsSubFactorDTO.setScoreTypeCode("01");
        trendInWorkCapDPLimitsSubFactorDTO.setScoreTypeDescription("Normal");

        // 5.1.2        Risk Sub Factor Attributes
        // -> Six Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "Decrease in the DP limit over last 12 months"));
        riskSubFactorAttributes.add(new RiskAttribute(7D, "No increase"));
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Increase in the DP limit over last 12 months"));


        List<RiskSubFactorAttributeDTO>  riskSubFactorAttributeDTOS2 =   riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        trendInWorkCapDPLimitsSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        accountConductRiskFactorDTO.addRiskSubFactorDTO(trendInWorkCapDPLimitsSubFactorDTO);


 



        riskComponentDTO.addRiskFactorDTO( accountConductRiskFactorDTO);





        return riskComponentDTO;

    }
}
