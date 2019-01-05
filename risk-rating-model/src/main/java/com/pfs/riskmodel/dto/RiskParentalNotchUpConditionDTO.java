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
     private Integer itemNo;
     private String description;
     /*
         0 - Rating of Parent Entity
         1 - Source of Rating of Parent Firm
         2 - Nature of Rating of Parent Firm
         3 - Is Parent's rating at GRADE 10
         4 - Is Parent's Rating Better Than Borrower's Rating
      */
     private Integer category;

     // 0 - Yes/No Indicator
     // 1 - String Input
     // 2 - Fill Nature of Rating of Parent Firm
     private Character valueType;

     private Character yesNoIndicatorValue;

     // 0 - Short Term
     // 1 - Long Term
     private Character natureOfRatingOfParentFirm;

     private String value;




}

