package com.pfs.riskmodel.ModelTemplates.InfraTransmission.ParentalNotchUp;

import com.pfs.riskmodel.dto.RiskParentalNotchUpConditionDTO;
import com.pfs.riskmodel.dto.RiskParentalNotchUpDTO;
import com.pfs.riskmodel.dto.RiskSubFactorAttributeDTO;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;
import com.pfs.riskmodel.utils.RiskAttribute;
import com.pfs.riskmodel.utils.RiskSubFactorAttributesBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 27-Dec-18.
 */
public class InfraTrans_RiskParentalNotchUp {


    RiskAttribute riskAttribute ;
    List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();



    public RiskParentalNotchUpDTO getInfraTransmissonParentalNotchup(){

        RiskParentalNotchUpDTO riskParentalNotchUpDTO = new RiskParentalNotchUpDTO();


        riskParentalNotchUpDTO.setId(null);
        riskParentalNotchUpDTO.setItemNo(1);
        riskParentalNotchUpDTO.setDescription("Parental Notchup");
        riskParentalNotchUpDTO.setIsParentalNotchUpApplicable(true);
        riskParentalNotchUpDTO.setParentalNotchUpScore(0D);

        /*          Condition CATEGORY VALUES
         0 - Rating of Parent Entity
         1 - Source of Rating of Parent Firm
         2 - Nature of Rating of Parent Firm
         3 - Is Parent's rating at GRADE 10
         4 - Is Parent's Rating Better Than Borrower's Rating
      */


         //Notchup Conditions
        List<RiskParentalNotchUpConditionDTO> riskParentalNotchUpConditionDTOS = new ArrayList<>();

        // NotchUp Conditions
        //        1. The parent’s rating is better than the borrower’s rating.
        //        2. The borrower’s rating is not GRADE10 (in default).
        //        3. The notch-up score as a percentage of maximum possible score is higher than 35%.

        RiskParentalNotchUpConditionDTO riskParentalNotchUpConditionDTO = new RiskParentalNotchUpConditionDTO();
        riskParentalNotchUpConditionDTO = new RiskParentalNotchUpConditionDTO();
        riskParentalNotchUpConditionDTO.setId(null);
        riskParentalNotchUpConditionDTO.setItemNo(1);
        riskParentalNotchUpConditionDTO.setCategory(0);
        riskParentalNotchUpConditionDTO.setDescription("Obligor Rating Grade of the Parent Firm as per reference source");
        riskParentalNotchUpConditionDTO.setValueType('1');
        riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('N');
        riskParentalNotchUpConditionDTO.setValue(" ");
        riskParentalNotchUpConditionDTO.setNatureOfRatingOfParentFirm('0');
        riskParentalNotchUpConditionDTOS.add(riskParentalNotchUpConditionDTO);


        riskParentalNotchUpConditionDTO = new RiskParentalNotchUpConditionDTO();
        riskParentalNotchUpConditionDTO.setId(null);
        riskParentalNotchUpConditionDTO.setItemNo(2);
        riskParentalNotchUpConditionDTO.setCategory(0);
        riskParentalNotchUpConditionDTO.setDescription("Borrower Rating Grade of the Parent Firm");
        riskParentalNotchUpConditionDTO.setValueType('1');
        riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('N');
        riskParentalNotchUpConditionDTO.setNatureOfRatingOfParentFirm('1');
        riskParentalNotchUpConditionDTOS.add(riskParentalNotchUpConditionDTO);


        riskParentalNotchUpConditionDTO = new RiskParentalNotchUpConditionDTO();
        riskParentalNotchUpConditionDTO.setId(null);
        riskParentalNotchUpConditionDTO.setItemNo(3);
        riskParentalNotchUpConditionDTO.setCategory(4);  //4 - Is Parent's Rating Better Than Borrower's Rating
        riskParentalNotchUpConditionDTO.setDescription("The parent’s rating is better than the borrower’s rating");
        riskParentalNotchUpConditionDTO.setValueType('0');
        riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('Y');
        riskParentalNotchUpConditionDTO.setValue(" ");
        riskParentalNotchUpConditionDTO.setNatureOfRatingOfParentFirm('0');
        riskParentalNotchUpConditionDTOS.add(riskParentalNotchUpConditionDTO);

        riskParentalNotchUpConditionDTO = new RiskParentalNotchUpConditionDTO();
        riskParentalNotchUpConditionDTO.setId(null);
        riskParentalNotchUpConditionDTO.setItemNo(4);
        riskParentalNotchUpConditionDTO.setCategory(3); //3 - Is Parent's rating at GRADE 10
        riskParentalNotchUpConditionDTO.setDescription("The borrower’s rating is not GRADE10 (in default)");
        riskParentalNotchUpConditionDTO.setValueType('0');
        riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('Y');
        riskParentalNotchUpConditionDTO.setValue(" ");
        riskParentalNotchUpConditionDTO.setNatureOfRatingOfParentFirm('0');
        riskParentalNotchUpConditionDTOS.add(riskParentalNotchUpConditionDTO);

        riskParentalNotchUpDTO.setRiskParentalConditions(riskParentalNotchUpConditionDTOS);




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
        List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(4D, "100% holding by the parent company"));
        riskSubFactorAttributes.add(new RiskAttribute(3D,"76-100% holding by the parent company"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"51-75% holding by the parent company"));
        riskSubFactorAttributes.add(new RiskAttribute(1D,"26-50% holding by the parent company"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Less than 26% holding by the parent company"));

         List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
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
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(4.0D, "The borrower is currently profitable and is projected to remain profitable in the next 2 years"));
        riskSubFactorAttributes.add(new RiskAttribute(2.5D,"The borrower is currently loss making, but is projected to become profitable by the end of next 2 years"));
        riskSubFactorAttributes.add(new RiskAttribute(1.25D,"The borrower is currently profitable, but is projected to make losses in either of the next 2 years "));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"The borrower is currently loss making and is projected to remain loss making at the end of next 2 years "));


        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
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
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(4.0D, "Both the parent and the borrower share a common Board of Directors and a common Treasury"));
        riskSubFactorAttributes.add(new RiskAttribute(2.5D,"Parent and the borrower have separate Boards but they share a common Treasury"));
        riskSubFactorAttributes.add(new RiskAttribute(1.25D,"Both the parent and the borrower share a common Board of Directors, but separate treasuries"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"Both the parent and the borrower have separate Boards and Treasuries"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS3 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        extentOfMgmtControlRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(extentOfMgmtControlRiskSubFactorDTO);


        // 1.1.4        Risk Sub Factor 4
        //    Stated Posture of Parent : 19%
        RiskSubFactorDTO pastHistoryRiskSubFactorDTO = new RiskSubFactorDTO();
        pastHistoryRiskSubFactorDTO.setId(null);
        pastHistoryRiskSubFactorDTO.setItemNo(4);
        pastHistoryRiskSubFactorDTO.setDescription("Stated Posture of Parent");
        pastHistoryRiskSubFactorDTO.setWeightage(0.19);
        pastHistoryRiskSubFactorDTO.setScore(0D);
        pastHistoryRiskSubFactorDTO.setScoreTypeCode("01");
        pastHistoryRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //
        // 1.1.4       Risk Sub Factor Attributes

        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(4.00D, "Legally enforceable provisions such as put options and cross-default provisions are provided by the parent"));
        riskSubFactorAttributes.add(new RiskAttribute(2.50D,"Assurances such as Letters of Credit, maintenance of debt service reserve account and shortfall undertakings are available from the parent"));
        riskSubFactorAttributes.add(new RiskAttribute(1.25D,"Parent has a track record of having provided support in the form of regular equity infusions and unsecured loans"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"None of the above comforts are available"));


        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS4 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
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
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0.0D, "The parent and the subsidiary do not share a common name"));
        riskSubFactorAttributes.add(new RiskAttribute(4.0D,"Both the parent and the subsidiary share a common name"));


        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS5 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
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
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(4.0D, "Parent is a public limited company listed on a stock exchange"));
        riskSubFactorAttributes.add(new RiskAttribute(2.0D,"Parent is an unlisted public limited company"));
        riskSubFactorAttributes.add(new RiskAttribute(1.0D,"Parent is an unlisted private limited company"));
        riskSubFactorAttributes.add(new RiskAttribute(0.0D,"None of the above"));


        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS6 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        listingStatusRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS6);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(listingStatusRiskSubFactorDTO);



        return riskParentalNotchUpDTO;

    }


}
