package com.pfs.riskmodel.domain;

import lombok.*;

import javax.persistence.*;

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
public class RiskModelSummary extends AuditModel { //AggregateRoot<RiskSubFactorAttribute>  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Getter(AccessLevel.PUBLIC)
    private Integer itemNo;

    @Getter(AccessLevel.PUBLIC)

    @Column(columnDefinition = "LONGTEXT")
    private String name;

    @Getter(AccessLevel.PUBLIC)
    private String score;


    @Getter(AccessLevel.PUBLIC)
    private String grade;




}

