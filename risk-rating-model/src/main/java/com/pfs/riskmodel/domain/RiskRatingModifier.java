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
public class RiskRatingModifier extends AuditModel  {

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



    /**
     // 01 - Modifiers to cap final ratings at sub-investment grade
     // 02 - Modifiers having impact on final ratings up to 2 notches   OneOrTwoByONE ThreeOrMoreByTWO*/

    @NotNull
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     private RatingModifierComputationMethod computingMethod;




    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn(name="riskRatingModifier__id",referencedColumnName = "id")
    private Set<RiskRatingModifierAttribute> riskRatingModifierAttributes;


    public RiskRatingModifierAttribute addRiskRatingModifierAttribute (RiskRatingModifierAttribute riskRatingModifierAttribute) {

        this.getRiskRatingModifierAttributes().add(riskRatingModifierAttribute);
        return riskRatingModifierAttribute;
    }

}
