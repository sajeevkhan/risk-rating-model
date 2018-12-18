package com.pfs.riskmodel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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


    @Getter(AccessLevel.PUBLIC)
    private Integer itemNo;

    @NotNull
    @Size(max = 250)
    private String description;

    @NotNull
    private Double score;


    @NotNull
    private Double weightage;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinColumn(name="riskSubFactor__id",referencedColumnName = "id")
    private Set<RiskSubFactorAttribute> riskSubFactorAttribute;


    public RiskSubFactorAttribute addRiskSubFactorAttribute (RiskSubFactorAttribute riskSubFactorAttribute) {

        this.getRiskSubFactorAttribute().add(riskSubFactorAttribute);
        return riskSubFactorAttribute;
    }

}
