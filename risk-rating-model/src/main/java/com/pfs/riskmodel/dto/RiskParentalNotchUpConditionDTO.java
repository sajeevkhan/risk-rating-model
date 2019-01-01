package com.pfs.riskmodel.dto;

import com.pfs.riskmodel.domain.AggregateRoot;
import com.pfs.riskmodel.domain.RiskParentalNotchUpCondition;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * Created by sajeev on 05-Dec-18.
 */

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class RiskParentalNotchUpConditionDTO   {

     private Long id;
     @NotNull
     private Integer itemNo;
     @NotNull
     private String description;



     @NotNull
     // 0 - Yes/No Indicator
     // 1 - String Input
     // 2 - Fill Nature of Rating of Parent Firm
     private Character valueType;
     @Nullable
     private Character yesNoIndicatorValue  ;
     @Nullable
     private String value;

     // 0 - Short Term
     // 1 - Long Term
     private Character natureOfRatingOfParentFirm;



     private List<RiskParentalNotchUpConditionDTO> riskParentalConditions;

     public RiskParentalNotchUpConditionDTO addRiskSubFactor (RiskParentalNotchUpConditionDTO riskParentalNotchUpConditionDTO) {

        this.riskParentalConditions.add(riskParentalNotchUpConditionDTO);
        return riskParentalNotchUpConditionDTO;
     }


}

