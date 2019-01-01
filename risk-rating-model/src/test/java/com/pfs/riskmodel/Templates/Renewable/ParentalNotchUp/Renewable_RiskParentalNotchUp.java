package com.pfs.riskmodel.Templates.Renewable.ParentalNotchUp;

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
public class Renewable_RiskParentalNotchUp {


    RiskAttribute riskAttribute ;
    List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
    RiskSubFactorAttributesBuilder riskSubFactorAttributesBuilder = new RiskSubFactorAttributesBuilder();

    //TODO - Risk Subfactors - Clarify with PFS

    public RiskParentalNotchUpDTO getParentalNotchUp(){

        RiskParentalNotchUpDTO riskParentalNotchUpDTO = new RiskParentalNotchUpDTO();


        riskParentalNotchUpDTO.setId(null);
        riskParentalNotchUpDTO.setItemNo(1);
        riskParentalNotchUpDTO.setDescription("Parental Notchup");

//        Rating of Parent Entity
//        Is Parent's Rating Better than Company?
//        Is Borrower's Rating at D?


        //        riskParentalNotchUpDTO.setIsParentalNotchUpApplicable(true);
        //        riskParentalNotchUpDTO.setObligorRatingGradeOfParentFirm(null);
        //        riskParentalNotchUpDTO.setIsBorrowerRatingAtD(null);
        //        riskParentalNotchUpDTO.setParentRatingBetterOrNot(null);
        //        riskParentalNotchUpDTO.setRatingGradeOfParentEntity(null);

        riskParentalNotchUpDTO.setIsParentalNotchUpApplicable(true);
        riskParentalNotchUpDTO.setParentalNotchUpScore(0D);



        //Notchup Conditions
        List<RiskParentalNotchUpConditionDTO> riskParentalNotchUpConditionDTOS = new ArrayList<>();




        // NotchUp Conditions
        //        Source of Rating of the Parent Firm
        //        Nature of Rating of Parent Firm - Short Term or Long Term
        //        Rating of Parent Entity
        //        Is Parent's Rating Better than Company?
        //        Is Borrower's Rating at D?


        RiskParentalNotchUpConditionDTO riskParentalNotchUpConditionDTO = new RiskParentalNotchUpConditionDTO();
        riskParentalNotchUpConditionDTO.setId(null);
        riskParentalNotchUpConditionDTO.setItemNo(1);
        riskParentalNotchUpConditionDTO.setDescription("Source of Rating of Parent Firm");
        riskParentalNotchUpConditionDTO.setValueType('1');
        riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('N');
        riskParentalNotchUpConditionDTO.setValue("");
        riskParentalNotchUpConditionDTOS.add(riskParentalNotchUpConditionDTO);

        riskParentalNotchUpConditionDTO = new RiskParentalNotchUpConditionDTO();
        riskParentalNotchUpConditionDTO.setId(null);
        riskParentalNotchUpConditionDTO.setItemNo(2);
        riskParentalNotchUpConditionDTO.setDescription("Nature of Rating of Parent Firm");
        riskParentalNotchUpConditionDTO.setValueType('2');
        riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('N');
        riskParentalNotchUpConditionDTO.setValue("");
        riskParentalNotchUpConditionDTO.setNatureOfRatingOfParentFirm('1');
        riskParentalNotchUpConditionDTOS.add(riskParentalNotchUpConditionDTO);

         riskParentalNotchUpConditionDTO = new RiskParentalNotchUpConditionDTO();
        riskParentalNotchUpConditionDTO.setId(null);
        riskParentalNotchUpConditionDTO.setItemNo(3);
        riskParentalNotchUpConditionDTO.setDescription("Obligor Rating Grade of the Parent Firm as per reference source");
        riskParentalNotchUpConditionDTO.setValueType('1');
        riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('N');
        riskParentalNotchUpConditionDTO.setValue("");
        riskParentalNotchUpConditionDTO.setNatureOfRatingOfParentFirm('1');
        riskParentalNotchUpConditionDTOS.add(riskParentalNotchUpConditionDTO);


        riskParentalNotchUpConditionDTO = new RiskParentalNotchUpConditionDTO();
        riskParentalNotchUpConditionDTO.setId(null);
        riskParentalNotchUpConditionDTO.setItemNo(4);
        riskParentalNotchUpConditionDTO.setDescription("Borrower Rating Grade of the Parent Firm");
        riskParentalNotchUpConditionDTO.setValueType('1');
        riskParentalNotchUpConditionDTO.setYesNoIndicatorValue('N');
        riskParentalNotchUpConditionDTO.setNatureOfRatingOfParentFirm('1');
        riskParentalNotchUpConditionDTOS.add(riskParentalNotchUpConditionDTO);


        riskParentalNotchUpDTO.setRiskParentalConditions(riskParentalNotchUpConditionDTOS);



        // 1.1.1        Risk Sub Factor 1
        //  "Ownership/ShareHolding Strucuture - 25%
        RiskSubFactorDTO parentFinancialDebtEquityRatioSubFactorDTO = new RiskSubFactorDTO();
        parentFinancialDebtEquityRatioSubFactorDTO.setId(null);
        parentFinancialDebtEquityRatioSubFactorDTO.setItemNo(1);
        parentFinancialDebtEquityRatioSubFactorDTO.setDescription("Ownership/ShareHolding Strucuture");
        parentFinancialDebtEquityRatioSubFactorDTO.setWeightage(0.25);
        parentFinancialDebtEquityRatioSubFactorDTO.setScore(0D);
        parentFinancialDebtEquityRatioSubFactorDTO.setScoreTypeCode("01");
        parentFinancialDebtEquityRatioSubFactorDTO.setScoreTypeDescription("Normal");

        //
        // 1.1.1       Risk Sub Factor Attributes
        // -> Four Attributes
        List<RiskAttribute> riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, " "));
        riskSubFactorAttributes.add(new RiskAttribute(0D,""));

         List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS1 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
         parentFinancialDebtEquityRatioSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS1);
         riskParentalNotchUpDTO.addRiskSubFactorDTO(parentFinancialDebtEquityRatioSubFactorDTO);




        // 1.1.2        Risk Sub Factor 2
        //  "Management Control  - 25%
        RiskSubFactorDTO parentFinancialIntCovRationSubFactorDTO = new RiskSubFactorDTO();
        parentFinancialIntCovRationSubFactorDTO.setId(null);
        parentFinancialIntCovRationSubFactorDTO.setItemNo(2);
        parentFinancialIntCovRationSubFactorDTO.setDescription("Management Control");
        parentFinancialIntCovRationSubFactorDTO.setWeightage(0.25);
        parentFinancialIntCovRationSubFactorDTO.setScore(0D);
        parentFinancialIntCovRationSubFactorDTO.setScoreTypeCode("01");
        parentFinancialIntCovRationSubFactorDTO.setScoreTypeDescription("Normal");


        //
        // 1.1.2       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, " "));
        riskSubFactorAttributes.add(new RiskAttribute(2D," "));


        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS2 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        parentFinancialIntCovRationSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS2);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(parentFinancialIntCovRationSubFactorDTO);





        // 1.1.3       Risk Sub Factor 3
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
        // 1.1.3       Risk Sub Factor Attributes

        riskSubFactorAttributes = new ArrayList<>(); //TODO - Check Scores
        riskSubFactorAttributes.add(new RiskAttribute(10D, "Legally enforceable provisions such as put options and cross-default provisions are provided by the parent"));
        riskSubFactorAttributes.add(new RiskAttribute(8D,"Assurances such as Letters of Credit, maintenance of debt service reserve account and shortfall undertakings are available from the parent"));
        riskSubFactorAttributes.add(new RiskAttribute(4D,"Parent has a track record of having provided support in the form of regular equity infusions and unsecured loans"));
        riskSubFactorAttributes.add(new RiskAttribute(0D,"None of the above comforts are available"));


        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS4 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        pastHistoryRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS4);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(pastHistoryRiskSubFactorDTO);





        // 1.1.4        Risk Sub Factor 5
        //    Past Track Record Name - 15%
        RiskSubFactorDTO pastTrackRecordRiskSubFactorDTO = new RiskSubFactorDTO();
        pastTrackRecordRiskSubFactorDTO.setId(null);
        pastTrackRecordRiskSubFactorDTO.setItemNo(5);
        pastTrackRecordRiskSubFactorDTO.setDescription("Past Track Record Name");
        pastTrackRecordRiskSubFactorDTO.setWeightage(0.15);
        pastTrackRecordRiskSubFactorDTO.setScore(0D);
        pastTrackRecordRiskSubFactorDTO.setScoreTypeCode("01");
        pastTrackRecordRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //
        // 1.1.5       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(10.0D, "Parent is a public limited company listed on a stock exchange"));
        riskSubFactorAttributes.add(new RiskAttribute(5.0D,"Parent is an unlisted public limited company"));
        riskSubFactorAttributes.add(new RiskAttribute(2.5D,"Parent is an unlisted private limited company"));
        riskSubFactorAttributes.add(new RiskAttribute(0.0D,"None of the above"));


        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS5 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        pastTrackRecordRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS5);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(pastTrackRecordRiskSubFactorDTO);




        // 1.1.5        Risk Sub Factor 5
        //    Brand name  - 15%
        RiskSubFactorDTO brandNameRiskSubFactorDTO = new RiskSubFactorDTO();
        brandNameRiskSubFactorDTO.setId(null);
        brandNameRiskSubFactorDTO.setItemNo(3);
        brandNameRiskSubFactorDTO.setDescription("Shared Name");
        brandNameRiskSubFactorDTO.setWeightage(0.15);
        brandNameRiskSubFactorDTO.setScore(0D);
        brandNameRiskSubFactorDTO.setScoreTypeCode("01");
        brandNameRiskSubFactorDTO.setScoreTypeDescription("Normal");


        //
        // 1.1.5       Risk Sub Factor Attributes
        // -> Four Attributes
        riskSubFactorAttributes = new ArrayList<>();
        riskSubFactorAttributes.add(new RiskAttribute(0D, "The parent and the borrower do not share a common name"));
        riskSubFactorAttributes.add(new RiskAttribute(10D,"Both the parent and the borrower share a common name"));

        List<RiskSubFactorAttributeDTO> riskSubFactorAttributeDTOS3 = riskSubFactorAttributesBuilder.buildRiskSubFactorAttributes(riskSubFactorAttributes);
        brandNameRiskSubFactorDTO.setRiskSubFactorAttributes(riskSubFactorAttributeDTOS3);
        riskParentalNotchUpDTO.addRiskSubFactorDTO(brandNameRiskSubFactorDTO);


        return riskParentalNotchUpDTO;

    }


}
