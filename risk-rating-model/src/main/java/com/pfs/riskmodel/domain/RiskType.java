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
public class RiskType extends AuditModel  {

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



    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn(name="riskType__id",referencedColumnName = "id")
    private Set<RiskComponent> riskComponents;


    public RiskComponent addRiskComponent (RiskComponent riskComponent) {

        this.getRiskComponents().add(riskComponent);
        return riskComponent;
    }

}
