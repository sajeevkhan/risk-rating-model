package com.pfs.riskmodel.ModelTemplates.HoldingCompany.ParentalNotchup;

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
public class HC_RiskParentalNotchUp {


    RiskAttribute riskAttribute ;
    List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();



    public RiskParentalNotchUpDTO getParentalNotchUp(){

        RiskParentalNotchUpDTO riskParentalNotchUpDTO = new RiskParentalNotchUpDTO();


        riskParentalNotchUpDTO.setId(null);
        riskParentalNotchUpDTO.setItemNo(1);
        riskParentalNotchUpDTO.setDescription("Parental Notchup");

        riskParentalNotchUpDTO.setIsParentalNotchUpApplicable(true);
        riskParentalNotchUpDTO.setParentalNotchUpScore(0D);


      /* Condition Category
         0 - Rating of Parent Entity
         1 - Source of Rating of Parent Firm
         2 - Nature of Rating of Parent Firm
         3 - Is Parent's rating at GRADE 10
         4 - Is Parent's Rating Better Than Borrower's Rating
      */
        //Notchup Conditions
        List<RiskParentalNotchUpConditionDTO> riskParentalNotchUpConditionDTOS = new ArrayList<>();

        RiskParentalNotchUpConditionDTO riskParentalNotchUpConditionDTO = new RiskParentalNotchUpConditionDTO();
        riskParentalNotchUpConditionDTO.setId(null);
        riskParentalNotchUpConditionDTO.setItemNo(1);
        riskParentalNotchUpConditionDTO.setCategory(0);
        riskParentalNotchUpConditionDTO.setDescription("Rating of Parent Entity");
        riskParentalNotchUpConditionDTO.setValueType('1');
        riskParentalNotchUpConditionDTO.setYesNoIndicatorValue(null);
        riskParentalNotchUpConditionDTO.setValue(" ");
        riskParentalNotchUpConditionDTO.setNatureOfRatingOfParentFirm('0');

        riskParentalNotchUpConditionDTOS.add(riskParentalNotchUpConditionDTO);


        riskParentalNotchUpConditionDTO = new RiskParentalNotchUpConditionDTO();
        riskParentalNotchUpConditionDTO.setId(null);
        riskParentalNotchUpConditionDTO.setItemNo(2);
        riskParentalNotchUpConditionDTO.setCategory(4);
        riskParentalNotchUpConditionDTO.setDescription("The parent’s rating is better than the borrower’s rating");
        riskParentalNotchUpConditionDTO.setValueType('0');
        riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('N');
        riskParentalNotchUpConditionDTO.setValue(" ");
        riskParentalNotchUpConditionDTO.setNatureOfRatingOfParentFirm('0');

        riskParentalNotchUpConditionDTOS.add(riskParentalNotchUpConditionDTO);

        riskParentalNotchUpConditionDTO = new RiskParentalNotchUpConditionDTO();
        riskParentalNotchUpConditionDTO.setId(null);
        riskParentalNotchUpConditionDTO.setItemNo(3);
        riskParentalNotchUpConditionDTO.setCategory(3);
        riskParentalNotchUpConditionDTO.setDescription("Is Borrower's Rating at D");
        riskParentalNotchUpConditionDTO.setValueType('0');
        riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('N');
        riskParentalNotchUpConditionDTO.setValue(" ");
        riskParentalNotchUpConditionDTO.setNatureOfRatingOfParentFirm('0');

        riskParentalNotchUpConditionDTOS.add(riskParentalNotchUpConditionDTO);


        riskParentalNotchUpDTO.setRiskParentalConditions(riskParentalNotchUpConditionDTOS);



        // 1.1.1        Risk Sub Factor 1
        //  "Parent Financials – Debt/Equity Ratio  - 25%
        RiskSubFactorDTO parentFinancialDebtEquityRatioSubFactorDTO = new RiskSubFactorDTO();
        parentFinancialDebtEquityRatioSubFactorDTO.setId(null);
        parentFinancialDebtEquityRatioSubFactorDTO.setItemNo(1);
        parentFinancialDebtEquityRatioSubFactorDTO.setDescription("Parent Financials – Debt/Equity Ratio");
        parentFinancialDebtEquityRatioSubFactorDTO.setWeightage(0.25);
        parentFinancialDebtEquityRatioSubFactorDTO.setScore(0D);
        parentFinancialDebtEquityRatioSubFactorDTO.setScoreTypeCode("01");
        parentFinancialDebtEquityRatioSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.1       Risk Sub Factor Attributes
        // -> Four Attributes
        List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10D, "From 0 to Less Than 2"));
        riskSubFactorAttributes.add(new RiskAttribute(7.5D,"From 2 to Less Than 3"));
        riskSubFactorAttributes.add(new RiskAttribute(5D,"From 3 to Less Than 4"));
        riskSubFactorAttributes.add(new RiskAttribute(2.5D,"From 4 to Less Than 5"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"From 5 to 100"));

         List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
         parentFinancialDebtEquityRatioSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
         riskParentalNotchUpDTO.addRiskSubFactorDTO(parentFinancialDebtEquityRatioSubFactorDTO);




        // 1.1.2        Risk Sub Factor 2
        //  "Parent Financials – Interest Coverage Ratio  - 25%
        RiskSubFactorDTO parentFinancialIntCovRationSubFactorDTO = new RiskSubFactorDTO();
        parentFinancialIntCovRationSubFactorDTO.setId(null);
        parentFinancialIntCovRationSubFactorDTO.setItemNo(2);
        parentFinancialIntCovRationSubFactorDTO.setDescription("Parent Financials – Interest Coverage Ratio");
        parentFinancialIntCovRationSubFactorDTO.setWeightage(0.25);
        parentFinancialIntCovRationSubFactorDTO.setScore(0D);
        parentFinancialIntCovRationSubFactorDTO.setScoreTypeCode("01");
        parentFinancialIntCovRationSubFactorDTO.setScoreTypeDescription("Normal");


        //
        // 1.1.2       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "From  0 to Less Than   1.8"));
        riskSubFactorAttributes.add(new RiskAttribute(2D,"From  1.8 to Less Than  3.5"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"From 3.5 to Less Than 5 "));
        riskSubFactorAttributes.add(new RiskAttribute(6D,"From 5 to Less Than 8 "));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"From  8 to Less Than 10 "));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"From 10  to Less Than100  "));


        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        parentFinancialIntCovRationSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(parentFinancialIntCovRationSubFactorDTO);


        // 1.1.3        Risk Sub Factor 3
        //    Shared name  - 15%
        RiskSubFactorDTO sharedNameRiskSubFactorDTO = new RiskSubFactorDTO();
        sharedNameRiskSubFactorDTO.setId(null);
        sharedNameRiskSubFactorDTO.setItemNo(3);
        sharedNameRiskSubFactorDTO.setDescription("Shared Name");
        sharedNameRiskSubFactorDTO.setWeightage(0.15);
        sharedNameRiskSubFactorDTO.setScore(0D);
        sharedNameRiskSubFactorDTO.setScoreTypeCode("01");
        sharedNameRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //
        // 1.1.3       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "The parent and the borrower do not share a common name"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Both the parent and the borrower share a common name"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS3 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        sharedNameRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(sharedNameRiskSubFactorDTO);




        // 1.1.4        Risk Sub Factor 4
        //    Stated Posture of Parent : Past History of Support  - 20%
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

        riskSubFactorAttributes = new ArrayList<>(); //TODO - Check Scores
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Legally enforceable provisions such as put options and cross-default provisions are provided by the parent"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Assurances such as Letters of Credit, maintenance of debt service reserve account and shortfall undertakings are available from the parent"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Parent has a track record of having provided support in the form of regular equity infusions and unsecured loans"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"None of the above comforts are available"));


        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS4 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        pastHistoryRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS4);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(pastHistoryRiskSubFactorDTO);


        // 1.1.5        Risk Sub Factor 5
        //    Listing status of the parent  - 15%
        RiskSubFactorDTO listingStatusRiskSubFactorDTO = new RiskSubFactorDTO();
        listingStatusRiskSubFactorDTO.setId(null);
        listingStatusRiskSubFactorDTO.setItemNo(5);
        listingStatusRiskSubFactorDTO.setDescription("Listing Status of the Parent");
        listingStatusRiskSubFactorDTO.setWeightage(0.15);
        listingStatusRiskSubFactorDTO.setScore(0D);
        listingStatusRiskSubFactorDTO.setScoreTypeCode("01");
        listingStatusRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //
        // 1.1.5       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10.0D, "Parent is a public limited company listed on a stock exchange"));
        riskSubFactorAttributes.add(new RiskAttribute(5.0D,"Parent is an unlisted public limited company"));
        riskSubFactorAttributes.add(new RiskAttribute(2.5D,"Parent is an unlisted private limited company"));
        riskSubFactorAttributes.add(new RiskAttribute(0.0D,"None of the above"));


        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS5 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        listingStatusRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS5);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(listingStatusRiskSubFactorDTO);


        return riskParentalNotchUpDTO;

    }


}
