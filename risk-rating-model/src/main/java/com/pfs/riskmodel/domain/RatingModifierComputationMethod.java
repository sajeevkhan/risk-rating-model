package com.pfs.riskmodel.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

// 01 - Modifiers to cap final ratings at sub-investment grade
// 02 - Modifiers having impact on final ratings up to 2 notches
    private String code;
    private String value;
}
