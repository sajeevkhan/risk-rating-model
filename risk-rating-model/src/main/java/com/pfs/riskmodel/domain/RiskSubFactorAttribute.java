package com.pfs.riskmodel.domain;

import lombok.*;

import javax.persistence.Entity;

/**
 * Created by sajeev on 05-Dec-18.
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class RiskSubFactorAttribute extends AbstractEntity  {

    private String description;
    private Integer riskSubFactorScore;
    private Integer weightage;
}
