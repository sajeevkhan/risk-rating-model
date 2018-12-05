package com.pfs.riskmodel.domain;

import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;

/**
 * Created by sajeev on 05-Dec-18.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class RiskSubFactorAttribute extends AbstractEntity  {

    private String  description;
    private Integer riskSubFactorScore;
    private Integer weightage;



}
