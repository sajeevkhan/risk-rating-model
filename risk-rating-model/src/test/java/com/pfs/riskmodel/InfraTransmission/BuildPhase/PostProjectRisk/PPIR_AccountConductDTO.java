package com.pfs.riskmodel.InfraTransmission.BuildPhase.PostProjectRisk;

import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskFactorDTO;
import com.pfs.riskmodel.utils.RiskAttribute;
import com.pfs.riskmodel.utils.RiskSubFactorAttributesBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 20-Dec-18.
 */
public class PPIR_AccountConductDTO {



    RiskAttribute riskAttribute ;
    Set<RiskAttribute> riskSubFactorAttributes = new HashSet<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();

    public RiskComponentDTO getAccountConductDTO() {


        //Account Conduct Risk Component 13%

        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
        Set<RiskFactor> riskFactorList = new HashSet<>();

        riskComponentDTO.setId(null);
        riskComponentDTO.setItemNo(1);
        riskComponentDTO.setComputingMethodCode("01");
        riskComponentDTO.setComputingMethodDescription("Weighted");
        riskComponentDTO.setDescription("Business Risk");
        riskComponentDTO.setScoreTypeDescription("01");
        riskComponentDTO.setScoreTypeDescription("Normal");
        riskComponentDTO.setScoreTypeCode("01");
        riskComponentDTO.setScore(0D);
        riskComponentDTO.setWeightage(0.13D);


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
        businessRiskFactorDTO.setComputingMethodCode("05");
        businessRiskFactorDTO.setComputingMethodDescription("Equals");

        return riskComponentDTO;
    }
    }
