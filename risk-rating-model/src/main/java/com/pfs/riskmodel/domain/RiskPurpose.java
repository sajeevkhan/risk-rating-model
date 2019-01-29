package com.pfs.riskmodel.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by sajeev on 26-Jan-19.
 */

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RiskPurpose {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    /*
        01 - Project Assessment
        02 - Risk Assessment
        03 - Monitoring
     */
    private String code;
    private String description;
}
