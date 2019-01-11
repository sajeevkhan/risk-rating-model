package com.pfs.riskmodel.ModelTemplates.InfraRoadToll.RiskComponents;

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
public class IRToll_PPIR_AccountConductDTO {



    RiskAttribute riskAttribute ;
    List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();

    public RiskComponentDTO getAccountConductDTO() {


        //Account Conduct Risk Component 10%

        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        List<RiskFactor> riskFactorList = new ArrayList<>();

        riskComponentDTO.setId(null);
        riskComponentDTO.setItemNo(4);
        riskComponentDTO.setComputingMethodCode("01");
        riskComponentDTO.setComputingMethodDescription("Weighted");
        riskComponentDTO.setDescription("Account Conduct Risk");
        riskComponentDTO.setScoreTypeDescription("01");
        riskComponentDTO.setScoreTypeDescription("Normal");
        riskComponentDTO.setScoreTypeCode("01");
        riskComponentDTO.setScore(0D);
        riskComponentDTO.setWeightage(0.10D);

        riskComponentDTO.setIsApplicable(true);

        // Account Conduct Factor
        // No Concrete Risk Factors - Therefore a dummy Risk Factor called "Account Conduct Risk Factor" is added
        RiskFactorDTO accountConductRiskFactorDTO = new RiskFactorDTO();
        accountConductRiskFactorDTO.setId(null);
        accountConductRiskFactorDTO.setItemNo(1);
        accountConductRiskFactorDTO.setDescription("Account Conduct Risk Factor");
        accountConductRiskFactorDTO.setWeightage(1.000);
        accountConductRiskFactorDTO.setScore(0D);
        accountConductRiskFactorDTO.setScoreTypeCode("01");
        accountConductRiskFactorDTO.setScoreTypeDescription("Normal");
        accountConductRiskFactorDTO.setComputingMethodCode("05");
        accountConductRiskFactorDTO.setComputingMethodDescription("Equals");



        // 1.1.1        Risk Sub Factor 1
        //  "Compliance with financial covenants including DSRA"  - 60%
        RiskSubFactorDTO complianceWithFinCovInclSRSARiskSubFactorDTO = new RiskSubFactorDTO();
        complianceWithFinCovInclSRSARiskSubFactorDTO.setId(null);
        complianceWithFinCovInclSRSARiskSubFactorDTO.setItemNo(1);
        complianceWithFinCovInclSRSARiskSubFactorDTO.setDescription("Compliance with financial covenants including DSRA");
        complianceWithFinCovInclSRSARiskSubFactorDTO.setWeightage(0.60);
        complianceWithFinCovInclSRSARiskSubFactorDTO.setScore(0D);
        complianceWithFinCovInclSRSARiskSubFactorDTO.setScoreTypeCode("01");
        complianceWithFinCovInclSRSARiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.1       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "No breaches in last 12 months"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"No breaches in last 12 months, but some breaches were observed earlier"));
        riskSubFactorAttributes.add(new RiskAttribute(5D,"Minor breaches once or twice in last 12 months"));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"Minor breaches once or twice in last 6 months"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Multiple breaches of serious nature over the last 12 months."));

         List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        complianceWithFinCovInclSRSARiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
        accountConductRiskFactorDTO.addRiskSubFactorDTO(complianceWithFinCovInclSRSARiskSubFactorDTO);




        // 1.1.2       Risk Sub Factor 2
        //  "Trend in Working capital Drawing power limits"  - 40%
        RiskSubFactorDTO trendInWorkCapitalRiskSubFactorDTO = new RiskSubFactorDTO(); //TODO Attr. Name
        trendInWorkCapitalRiskSubFactorDTO.setId(null);
        trendInWorkCapitalRiskSubFactorDTO.setItemNo(2);  //TODO Item Number
        trendInWorkCapitalRiskSubFactorDTO.setDescription("Trend in Working capital Drawing power limits"); //TODO Desc
        trendInWorkCapitalRiskSubFactorDTO.setWeightage(0.60); //TODO  Weightage
        trendInWorkCapitalRiskSubFactorDTO.setScore(0D);
        trendInWorkCapitalRiskSubFactorDTO.setScoreTypeCode("01");
        trendInWorkCapitalRiskSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.2     Risk Sub Factor Attributes
        // -> Three Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Decrease in the DP limit over last 12 months"));
        riskSubFactorAttributes.add(new RiskAttribute(7D,"No increase  "));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Increase in the DP limit over last 12 months"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        trendInWorkCapitalRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        accountConductRiskFactorDTO.addRiskSubFactorDTO(trendInWorkCapitalRiskSubFactorDTO);


        riskComponentDTO.addRiskFactorDTO(accountConductRiskFactorDTO);


        return riskComponentDTO;
    }
    }
