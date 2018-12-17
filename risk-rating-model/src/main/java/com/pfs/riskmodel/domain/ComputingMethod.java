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
public class ComputingMethod extends AggregateRoot<ComputingMethod> {
    private String code;

    private String value;
}
