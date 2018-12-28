package com.pfs.riskmodel.domain;

import lombok.*;
import org.springframework.lang.Nullable;

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
public class RiskParentalNotchUp extends AuditModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Getter(AccessLevel.PUBLIC)
    private Integer itemNo;

    @NotNull
    @Size(max = 250)
    private String description;


    @Nullable
    private Boolean isParentalNotchUpApplicable;


    @Nullable
    private String sourceOfRatingOfPaentalNotchUp;

    @Nullable
    private String obligorRatingGradeOfParentFirm;

    @Nullable
    private String ratingGradeOfParentEntity;


    @Nullable
    private Boolean parentRatingBetterOrNot;

    @Nullable
    private Boolean isBorrowerRatingAtD;


    @NotNull
    private Double parentalNotchUpScore;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn(name="riskType__id",referencedColumnName = "id")
    private Set<RiskSubFactor> riskSubFactors;


    public RiskSubFactor addRiskSubFactor (RiskSubFactor riskSubFactor) {

        this.getRiskSubFactors().add(riskSubFactor);
        return riskSubFactor;
    }

}
