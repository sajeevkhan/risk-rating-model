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
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class RiskProjectType extends AggregateRoot<RiskProjectType> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    private String code;

    private String value;
}
