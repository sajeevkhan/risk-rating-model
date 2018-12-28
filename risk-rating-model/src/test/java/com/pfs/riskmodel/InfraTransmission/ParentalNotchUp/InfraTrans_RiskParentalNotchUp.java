package com.pfs.riskmodel.InfraTransmission.ParentalNotchUp;

import com.pfs.riskmodel.dto.RiskParentalNotchUpDTO;
import com.pfs.riskmodel.dto.RiskSubFactorAttributeDTO;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;
import com.pfs.riskmodel.utils.RiskAttribute;
import com.pfs.riskmodel.utils.RiskSubFactorAttributesBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 27-Dec-18.
 */
public class InfraTrans_RiskParentalNotchUp {


    RiskAttribute riskAttribute ;
    Set<RiskAttribute> riskSubFactorAttributes = new HashSet<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();



    public RiskParentalNotchUpDTO getInfraTransmissonParentalNotchup(){

        RiskParentalNotchUpDTO riskParentalNotchUpDTO = new RiskParentalNotchUpDTO();


        riskParentalNotchUpDTO.setId(null);
        riskParentalNotchUpDTO.setItemNo(1);
        riskParentalNotchUpDTO.setDescription("Parental Notchup");

        riskParentalNotchUpDTO.setIsParentalNotchUpApplicable(true);

        riskParentalNotchUpDTO.setObligorRatingGradeOfParentFirm(null);

        riskParentalNotchUpDTO.setIsBorrowerRatingAtD(null);

        riskParentalNotchUpDTO.setParentRatingBetterOrNot(null);

        riskParentalNotchUpDTO.setRatingGradeOfParentEntity(null);

        riskParentalNotchUpDTO.setParentalNotchUpScore(0D);


        // TODO
        //Notchup Criteria
        //        1. The parent’s rating is better than the borrower’s rating.
        //        2. The borrower’s rating is not GRADE10 (in default).
        //        3. The notch-up score as a percentage of maximum possible score is higher than 35%.


        // 1.1.1        Risk Sub Factor 1
        //  Extent of parent holding  - 23%
        RiskSubFactorDTO extentOfParentHoldingSubFactorDTO = new RiskSubFactorDTO();
        extentOfParentHoldingSubFactorDTO.setId(null);
        extentOfParentHoldingSubFactorDTO.setItemNo(1);
        extentOfParentHoldingSubFactorDTO.setDescription("Extent of parent holding");
        extentOfParentHoldingSubFactorDTO.setWeightage(0.23);
        extentOfParentHoldingSubFactorDTO.setScore(0D);
        extentOfParentHoldingSubFactorDTO.setScoreTypeCode("01");
        extentOfParentHoldingSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.1       Risk Sub Factor Attributes
        // -> Four Attributes
        Set<RiskAttribute> riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(4D, "100% holding by the parent company"));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"76-100% holding by the parent company"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"51-75% holding by the parent company"));
        riskSubFactorAttributes.add(new RiskAttribute(1D,"26-50% holding by the parent company"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Less than 26% holding by the parent company"));

         Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
         extentOfParentHoldingSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
         riskParentalNotchUpDTO.addRiskSubFactorDTO(extentOfParentHoldingSubFactorDTO);




        // 1.1.2        Risk Sub Factor 2
        //  Economic incentive to parent (current and prospective) - 13%
        RiskSubFactorDTO economicIncentiveToParentRiskSubFactorDTO = new RiskSubFactorDTO();
        economicIncentiveToParentRiskSubFactorDTO.setId(null);
        economicIncentiveToParentRiskSubFactorDTO.setItemNo(2);
        economicIncentiveToParentRiskSubFactorDTO.setDescription("Economic incentive to parent (current and prospective)");
        economicIncentiveToParentRiskSubFactorDTO.setWeightage(0.13);
        economicIncentiveToParentRiskSubFactorDTO.setScore(0D);
        economicIncentiveToParentRiskSubFactorDTO.setScoreTypeCode("01");
        economicIncentiveToParentRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //
        // 1.1.2       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(4.0D, "The subsidiary is currently profitable and is projected to remain profitable in the next 2 years"));
        riskSubFactorAttributes.add(new RiskAttribute(2.5D,"The subsidiary is currently loss making, but is projected to become profitable by the end of next 2 years"));
        riskSubFactorAttributes.add(new RiskAttribute(1.25D,"The subsidiary is currently profitable, but is projected to make losses in either of the next 2 years "));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"The subsidiary is currently loss making and is projected to remain loss making at the end of next 2 years "));


        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        economicIncentiveToParentRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(economicIncentiveToParentRiskSubFactorDTO);


        // 1.1.3        Risk Sub Factor 3
        //    Extent of Management Control  - 19%
        RiskSubFactorDTO extentOfMgmtControlRiskSubFactorDTO = new RiskSubFactorDTO();
        extentOfMgmtControlRiskSubFactorDTO.setId(null);
        extentOfMgmtControlRiskSubFactorDTO.setItemNo(3);
        extentOfMgmtControlRiskSubFactorDTO.setDescription("Extent of Management Control");
        extentOfMgmtControlRiskSubFactorDTO.setWeightage(0.19);
        extentOfMgmtControlRiskSubFactorDTO.setScore(0D);
        extentOfMgmtControlRiskSubFactorDTO.setScoreTypeCode("01");
        extentOfMgmtControlRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //
        // 1.1.3       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(4.0D, "Both the parent and the subsidiary share a common Board of Directors and a common Treasury"));
        riskSubFactorAttributes.add(new RiskAttribute(2.5D,"Parent and the subsidiary have separate Boards but they share a common Treasury"));
        riskSubFactorAttributes.add(new RiskAttribute(1.25D,"Both the parent and the subsidiary share a common Board of Directors, but separate treasuries"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Both the parent and the subsidiary have separate Boards and Treasuries"));

        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS3 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        extentOfMgmtControlRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(extentOfMgmtControlRiskSubFactorDTO);


        // 1.1.4        Risk Sub Factor 4
        //    Stated Posture of Parent : 19%
        RiskSubFactorDTO pastHistoryRiskSubFactorDTO = new RiskSubFactorDTO();
        pastHistoryRiskSubFactorDTO.setId(null);
        pastHistoryRiskSubFactorDTO.setItemNo(4);
        pastHistoryRiskSubFactorDTO.setDescription("Stated Posture of Parent");
        pastHistoryRiskSubFactorDTO.setWeightage(0.20);
        pastHistoryRiskSubFactorDTO.setScore(0D);
        pastHistoryRiskSubFactorDTO.setScoreTypeCode("01");
        pastHistoryRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //
        // 1.1.4       Risk Sub Factor Attributes

        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Legally enforceable provisions such as put options and cross-default provisions are provided by the parent"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Assurances such as Letters of Credit, maintenance of debt service reserve account and shortfall undertakings are available from the parent"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Parent has a track record of having provided support in the form of regular equity infusions and unsecured loans"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"None of the above comforts are available"));


        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS4 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        pastHistoryRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS4);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(pastHistoryRiskSubFactorDTO);


        // 1.1.5        Risk Sub Factor 5
        //    Shared name  - 19%
        RiskSubFactorDTO sharedNameRiskSubFactorDTO = new RiskSubFactorDTO();
        sharedNameRiskSubFactorDTO.setId(null);
        sharedNameRiskSubFactorDTO.setItemNo(5);
        sharedNameRiskSubFactorDTO.setDescription("Shared Name");
        sharedNameRiskSubFactorDTO.setWeightage(0.19);
        sharedNameRiskSubFactorDTO.setScore(0D);
        sharedNameRiskSubFactorDTO.setScoreTypeCode("01");
        sharedNameRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //
        // 1.1.5       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(0.0D, "The parent and the subsidiary do not share a common name"));
        riskSubFactorAttributes.add(new RiskAttribute(4.0D,"Both the parent and the subsidiary share a common name"));


        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS5 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        sharedNameRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS5);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(sharedNameRiskSubFactorDTO);



        // 1.1.5        Risk Sub Factor 6
        //    Listing status of the parent  - 7%
        RiskSubFactorDTO listingStatusRiskSubFactorDTO = new RiskSubFactorDTO();
        listingStatusRiskSubFactorDTO.setId(null);
        listingStatusRiskSubFactorDTO.setItemNo(6);
        listingStatusRiskSubFactorDTO.setDescription("Listing Status of the Parent");
        listingStatusRiskSubFactorDTO.setWeightage(0.07);
        listingStatusRiskSubFactorDTO.setScore(0D);
        listingStatusRiskSubFactorDTO.setScoreTypeCode("01");
        listingStatusRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //
        // 1.1.5       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new HashSet<>();
        riskSubFactorAttributes.add(new RiskAttribute(4.0D, "Parent is a public limited company listed on a stock exchange"));
        riskSubFactorAttributes.add(new RiskAttribute(2.0D,"Parent is an unlisted public limited company"));
        riskSubFactorAttributes.add(new RiskAttribute(1.0D,"Parent is an unlisted private limited company"));
        riskSubFactorAttributes.add(new RiskAttribute(0.0D,"None of the above"));


        Set<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS6 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        listingStatusRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS5);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(listingStatusRiskSubFactorDTO);



        return riskParentalNotchUpDTO;

    }


}
