package com.pfs.riskmodel.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class RiskComponent extends AuditModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Getter(AccessLevel.PUBLIC)
    private Integer itemNo;


    @NotNull
    @Size(max = 250)
    private String description;

    /**
     ("01", "Weighted");
     ("02", "Sum");
     ("03", "Minimum");
     ("04", "Maximum");
     ("05", "Equals");
     ("06", "Multiply")
    */

    @NotNull
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     private ComputingMethod computingMethod;

    /**
     ("01", "Normal");
     ("02", "Deflator");
     ("03", "Multiplier");
     */
    @NotNull
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private ScoreType scoreType;


    private String computingMethodCode;
    private String scoreTypeCode;


    @NotNull
    private Double score;


    @NotNull
    private Double weightage;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn(name="riskComponent__id",referencedColumnName = "id")
    private Set<RiskFactor> riskFactors;


    public RiskFactor addRiskFactor (RiskFactor riskFactor) {

        this.getRiskFactors().add(riskFactor);
        return riskFactor;
    }

}
