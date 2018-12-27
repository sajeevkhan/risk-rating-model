package com.pfs.riskmodel.utils;

import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskFactorDTO;
import com.pfs.riskmodel.dto.RiskSubFactorAttributeDTO;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 25-Dec-18.
 */
public class RiskComponentTemplate {



        RiskAttribute riskAttribute ;
        Set<RiskAttribute> riskSubFactorAttributes = new HashSet<>();
        RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();



        public RiskComponentDTO getBusinessRiskDTO() {


            //Business Risk Component 35.00%

            RiskComponentDTO riskComponentDTO = new RiskComponentDTO();
            Set<RiskFactor> riskFactorList = new HashSet<>();

            riskComponentDTO.setId(null);
            riskComponentDTO.setItemNo(1);
            riskComponentDTO.setComputingMethodCode("01");
            riskComponentDTO.setComputingMethodDescription("Weighted");
            riskComponentDTO.setDescription("Business Risk"); //TODO Check Desc.
            riskComponentDTO.setScoreTypeDescription("01");
            riskComponentDTO.setScoreTypeDescription("Normal");
            riskComponentDTO.setScoreTypeCode("01");
            riskComponentDTO.setScore(0D);
            riskComponentDTO.setWeightage(0.35D);  //TODO


            // BusinessRisk Factor

            /*
             --------------------------    Risk Factor 1
                1.1.1 Business  Risk Factor
                      No Concrete Risk Factors - Therefore a dummy Risk Factor called "Business Risk Factor" is added
             */
            RiskFactorDTO businessRiskFactorDTO = new RiskFactorDTO();
            businessRiskFactorDTO.setId(null);
            businessRiskFactorDTO.setItemNo(1);
            businessRiskFactorDTO.setDescription("Business Risk Factor"); //TODO
            businessRiskFactorDTO.setWeightage(1.000); //TODO
            businessRiskFactorDTO.setScore(0D);
            businessRiskFactorDTO.setScoreTypeCode("01");
            businessRiskFactorDTO.setScoreTypeDescription("Normal");
            businessRiskFactorDTO.setComputingMethodCode("05");  //TODO
            businessRiskFactorDTO.setComputingMethodDescription("Equals"); //TODO




            // 1.1.1        Risk Sub Factor 1
            //  ""  - 10%
            RiskSubFactorDTO RiskSubFactorDTO = new RiskSubFactorDTO(); //TODO Attr. Name
            RiskSubFactorDTO.setId(null);
            RiskSubFactorDTO.setItemNo(1);  //TODO Item Number
            RiskSubFactorDTO.setDescription(""); //TODO Desc
            RiskSubFactorDTO.setWeightage(0.00); //TODO  Weightage
            RiskSubFactorDTO.setScore(0D);
            RiskSubFactorDTO.setScoreTypeCode("01");
            RiskSubFactorDTO.setScoreTypeDescription("Normal");

            //
            // 1.1.1       Risk Sub Factor Attributes
            // -> Four Attributes
            riskSubFactorAttributes = new HashSet<>(); //TODO - Check Scores
            riskSubFactorAttributes.add(new RiskAttribute(0D, ""));
            riskSubFactorAttributes.add(new RiskAttribute(2D,""));
            riskSubFactorAttributes.add(new RiskAttribute(4D,""));
            riskSubFactorAttributes.add(new RiskAttribute(6D,""));
            riskSubFactorAttributes.add(new RiskAttribute(8D,""));
            riskSubFactorAttributes.add(new RiskAttribute(10D,""));

            //TODO Set Correct Risk Sub Factor Name
            Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
            // TODO - Set Correct RiskFactorDTO Name
            // TODO - Set Correct Risk SubFactor
            RiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS);
            businessRiskFactorDTO.addRiskSubFactorDTO(RiskSubFactorDTO);






            return null;
        }

    }


