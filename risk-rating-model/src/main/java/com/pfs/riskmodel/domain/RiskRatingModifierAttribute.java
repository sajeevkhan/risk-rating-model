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
public class RiskRatingModifierAttribute extends AuditModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Getter(AccessLevel.PUBLIC)
    private Integer itemNo;

    @NotNull
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    /**
     ("N", "No");
     ("Y", "Yes");
     */
    private Character yesOrNoIndicator;


    @Nullable
    private boolean applicableForMonitoring;



}
