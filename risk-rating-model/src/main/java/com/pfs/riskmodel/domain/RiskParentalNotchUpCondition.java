package com.pfs.riskmodel.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;

/**
 * Created by sajeev on 05-Dec-18.
 */
@Entity
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class RiskParentalNotchUpCondition extends AuditModel { //AggregateRoot<RiskParentalNotchUpCondition>  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Nullable
    @Getter(AccessLevel.PUBLIC)
    private Integer itemNo;

    @Getter(AccessLevel.PUBLIC)
    @Nullable
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Nullable
    // 0 - Yes/No Indicator
    // 1 - String Input
    // 2 - Nature of Rating of Parent Firm
    @Getter
    private Character valueType;

    /*
        0 - Rating of Parent Entity
        1 - Source of Rating of Parent Firm
        2 - Nature of Rating of Parent Firm
        3 - Is Parent's rating at GRADE 10
        4 - Is Parent's Rating Better Than Borrower's Rating
     */
    @Column
    private Integer category;


    @Nullable
    @Getter(AccessLevel.PUBLIC)
    private Character yesNoIndicatorValue  ;

    @Nullable
    @Getter(AccessLevel.PUBLIC)
    private String value;

    @Nullable
    @Getter
    // 0 - Short Term
    // 1 - Long Term
    private Character natureOfRatingOfParentFirm;




}

