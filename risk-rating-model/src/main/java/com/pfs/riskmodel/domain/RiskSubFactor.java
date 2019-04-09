package com.pfs.riskmodel.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
 import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by sajeev on 15-Dec-18.
 */
@Entity
@ToString
@EqualsAndHashCode(callSuper = false)
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


    /**
     ("01", "Normal");
     ("02", "Deflator");
     ("03", "Multiplier");
     */
    @NotNull
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private ScoreType scoreType;

    private String scoreTypeCode;
    private String scoreTypeDescription;

    @Nullable
    private String riskSubFactorCalculation;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinColumn(name="riskSubFactor__id",referencedColumnName = "id")
    private List<RiskSubFactorAttribute> riskSubFactorAttributes;


    public RiskSubFactorAttribute addRiskSubFactorAttribute (RiskSubFactorAttribute riskSubFactorAttribute) {

        this.getRiskSubFactorAttributes().add(riskSubFactorAttribute);
        return riskSubFactorAttribute;
    }

}
