package com.pfs.riskmodel.InfraTransmission.RiskRatingModifiers;

import com.pfs.riskmodel.dto.RiskRatingModifierAttributeDTO;
import com.pfs.riskmodel.dto.RiskRatingModifierDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 25-Dec-18.
 */
public class InfraTrans_RatingModifierDTO {


    public Set<RiskRatingModifierDTO> getRiskRatingModifierDTOs () {


        Set<RiskRatingModifierDTO> riskRatingModifierDTOSet = new HashSet<>();


        // First Modifier - Modifiers considered for capping Final ratings at Sub-Investment grade
        RiskRatingModifierDTO riskRatingModifierDTO1 = new RiskRatingModifierDTO();

        riskRatingModifierDTO1.setId(null);
        riskRatingModifierDTO1.setItemNo(1);
        riskRatingModifierDTO1.setDescription("Modifiers considered for capping Final ratings at Sub-Investment grade");
        riskRatingModifierDTO1.setComputingMethodCode("01");
        riskRatingModifierDTO1.setComputingMethodDescription("On Select Any One - Notch Down to Sub Investment Grade");
        riskRatingModifierDTO1.setScore(0D);

        Set<RiskRatingModifierAttributeDTO> riskRatingModifierAttributes = new HashSet<>();

        riskRatingModifierAttributes = new HashSet<>();

        RiskRatingModifierAttributeDTO riskRatingModifierAttributeDTO = new RiskRatingModifierAttributeDTO();
        riskRatingModifierAttributeDTO.setId(null);
        riskRatingModifierAttributeDTO.setItemNo(1);
        riskRatingModifierAttributeDTO.setDescription("Account of the entity has been into the NPA Category in the past 12 months as on rating date");
        riskRatingModifierAttributeDTO.setYesNoIndicator(' ');

        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);

        riskRatingModifierAttributeDTO = new RiskRatingModifierAttributeDTO();
        riskRatingModifierAttributeDTO.setId(null);
        riskRatingModifierAttributeDTO.setItemNo(2);
        riskRatingModifierAttributeDTO.setDescription("Entity has undergone stress restructuring in past 12 months");
        riskRatingModifierAttributeDTO.setYesNoIndicator(' ');
        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);

        riskRatingModifierAttributeDTO = new RiskRatingModifierAttributeDTO();
        riskRatingModifierAttributeDTO.setId(null);
        riskRatingModifierAttributeDTO.setItemNo(3);
        riskRatingModifierAttributeDTO.setDescription("Promoters/Whole-time Directors are currently in the RBI or CIBIL list of defaulters");
        riskRatingModifierAttributeDTO.setYesNoIndicator(' ');
        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);

        riskRatingModifierAttributeDTO = new RiskRatingModifierAttributeDTO();
        riskRatingModifierAttributeDTO.setId(null);
        riskRatingModifierAttributeDTO.setItemNo(4);
        riskRatingModifierAttributeDTO.setDescription("Any group / associated entity has been classified as NPA within last 1 year");
        riskRatingModifierAttributeDTO.setYesNoIndicator(' ');
        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);

        riskRatingModifierAttributeDTO = new RiskRatingModifierAttributeDTO();
        riskRatingModifierAttributeDTO.setId(null);
        riskRatingModifierAttributeDTO.setItemNo(5);
        riskRatingModifierAttributeDTO.setDescription("Account of the entity/group companies is in the SMA 2 Category as on rating date");
        riskRatingModifierAttributeDTO.setYesNoIndicator(' ');
        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);

        riskRatingModifierAttributeDTO = new RiskRatingModifierAttributeDTO();
        riskRatingModifierAttributeDTO.setId(null);
        riskRatingModifierAttributeDTO.setItemNo(6);
        riskRatingModifierAttributeDTO.setDescription("If entity/promoters have been declared fraudulent (proven case) by Government entity/Regulatory authority/CBI/ED/RBI/Banks/Key Stakeholders etc. and Lender views as having material impact on repayment capability");
        riskRatingModifierAttributeDTO.setYesNoIndicator(' ');
        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);

        riskRatingModifierAttributeDTO = new RiskRatingModifierAttributeDTO();
        riskRatingModifierAttributeDTO.setId(null);
        riskRatingModifierAttributeDTO.setItemNo(7);
        riskRatingModifierAttributeDTO.setDescription("Negative Net Worth");
        riskRatingModifierAttributeDTO.setYesNoIndicator(' ');
        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);

        riskRatingModifierDTO1.setRiskRatingModifierAttributes(riskRatingModifierAttributes);

        //------- Modifier 2 Modifiers considered for Notch-down of Final Ratings
        RiskRatingModifierDTO riskRatingModifierDTO2 = new RiskRatingModifierDTO();
        riskRatingModifierAttributes = new HashSet<>();

        riskRatingModifierDTO2.setId(null);
        riskRatingModifierDTO2.setItemNo(2);
        riskRatingModifierDTO2.setDescription("Modifiers considered for Notch-down of Final Ratings");
        riskRatingModifierDTO2.setComputingMethodCode("02");
        riskRatingModifierDTO2.setComputingMethodDescription("Notch Down By Selection- OneorTwoBYOne, MoreThanThree By Two");
        riskRatingModifierDTO2.setScore(0D);


        riskRatingModifierAttributeDTO = new RiskRatingModifierAttributeDTO();
        riskRatingModifierAttributeDTO.setId(null);
        riskRatingModifierAttributeDTO.setItemNo(1);
        riskRatingModifierAttributeDTO.setDescription("History of legal issues or cases pending appeal with penalty amount above 10% of current EBITDA (Cases of VAT / excise / tax related matters not to be considered) for group entities");
        riskRatingModifierAttributeDTO.setYesNoIndicator('N');
        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);

        riskRatingModifierAttributeDTO = new RiskRatingModifierAttributeDTO();
        riskRatingModifierAttributeDTO.setId(null);
        riskRatingModifierAttributeDTO.setItemNo(2);
        riskRatingModifierAttributeDTO.setDescription("Account of the entity/group companies has been in the SMA 2 Category in last 12 months");
        riskRatingModifierAttributeDTO.setYesNoIndicator('N');
        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);

        riskRatingModifierAttributeDTO = new RiskRatingModifierAttributeDTO();
        riskRatingModifierAttributeDTO.setId(null);
        riskRatingModifierAttributeDTO.setItemNo(3);
        riskRatingModifierAttributeDTO.setDescription("Non-compliance with regulatory requirements and / or violations reported by regulators (RBI, SEBI, FEMA) for group entities");
        riskRatingModifierAttributeDTO.setYesNoIndicator('N');
        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);

        riskRatingModifierAttributeDTO = new RiskRatingModifierAttributeDTO();
        riskRatingModifierAttributeDTO.setId(null);
        riskRatingModifierAttributeDTO.setItemNo(4);
        riskRatingModifierAttributeDTO.setDescription("Adverse liquidity position vis-à-vis loan repayment evidenced by adverse liquidity position arising due to stretched receivables/slow realization of debtors/excess inventory levels/inability to meet large repayment in near term/inability to raise funds from external or internal sources/substantial cash flow mismatch for the entity");
        riskRatingModifierAttributeDTO.setYesNoIndicator('N');
        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);

        riskRatingModifierAttributeDTO = new RiskRatingModifierAttributeDTO();
        riskRatingModifierAttributeDTO.setId(null);
        riskRatingModifierAttributeDTO.setItemNo(5);
        riskRatingModifierAttributeDTO.setDescription("Adverse Labor Relations for group entities");
        riskRatingModifierAttributeDTO.setYesNoIndicator('N');
        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);

        riskRatingModifierAttributeDTO = new RiskRatingModifierAttributeDTO();
        riskRatingModifierAttributeDTO.setId(null);
        riskRatingModifierAttributeDTO.setItemNo(6);
        riskRatingModifierAttributeDTO.setDescription("Insufficiency of Insurance Cover as proposed by Independent Engineers for the entity");
        riskRatingModifierAttributeDTO.setYesNoIndicator('N');
        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);


        riskRatingModifierDTO2.setRiskRatingModifierAttributes(riskRatingModifierAttributes);

        riskRatingModifierDTOSet.add(riskRatingModifierDTO1);
        riskRatingModifierDTOSet.add(riskRatingModifierDTO2);

        return riskRatingModifierDTOSet;
    }

}
