package com.pfs.riskmodel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
 import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by sajeev on 15-Dec-18.
 */
@Entity
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class RiskSubFactor extends AuditModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 250)
    private String description;

    @NotNull
    private BigDecimal riskSubFactorScore;


    @NotNull
    private BigDecimal weightage;


    @OneToMany(mappedBy = "riskSubFactor", fetch = FetchType.EAGER,  cascade = { CascadeType.ALL })
    private Set<RiskSubFactorAttribute> riskSubFactorAttribute;



}
