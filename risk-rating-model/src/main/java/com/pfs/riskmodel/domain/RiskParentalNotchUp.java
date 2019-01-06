package com.pfs.riskmodel.domain;

import lombok.*;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

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
public class RiskParentalNotchUp extends AuditModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Getter(AccessLevel.PUBLIC)
    private Integer itemNo;

    @NotNull

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @NotNull
    private Boolean isParentalNotchUpApplicable;

    @NotNull
    private Double parentalNotchUpScore;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn(name="riskType__id",referencedColumnName = "id")
    private List<RiskSubFactor> riskSubFactors;


    public RiskSubFactor addRiskSubFactor (RiskSubFactor riskSubFactor) {
        this.getRiskSubFactors().add(riskSubFactor);
        return riskSubFactor;
    }

    @IndexColumn(name = "INDEX_COL")
     @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
     @JoinColumn(name="riskParentalConditions__id",referencedColumnName = "id")
    private List<RiskParentalNotchUpCondition> riskParentalConditions;


    public RiskParentalNotchUpCondition addRiskSubFactor (RiskParentalNotchUpCondition riskParentalNotchUpCondition) {

        this.riskParentalConditions.add(riskParentalNotchUpCondition);
        return riskParentalNotchUpCondition;
    }

}
