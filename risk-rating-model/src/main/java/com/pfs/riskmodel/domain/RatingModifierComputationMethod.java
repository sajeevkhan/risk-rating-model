package com.pfs.riskmodel.domain;

import lombok.*;

import javax.persistence.Entity;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RatingModifierComputationMethod extends AggregateRoot<RatingModifierComputationMethod> {

// 01 - Modifiers to cap final ratings at sub-investment grade
// 02 - Modifiers having impact on final ratings up to 2 notches
    private String code;
    private String value;
}
