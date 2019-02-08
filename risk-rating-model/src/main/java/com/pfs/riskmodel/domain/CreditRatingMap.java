package com.pfs.riskmodel.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
//@NoArgsConstructor(access = AccessLevel.PRIVATE)

@AllArgsConstructor
public class CreditRatingMap extends AggregateRoot<CreditRatingMap> {
    public CreditRatingMap() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @NotNull
    @ManyToOne
    private CreditRatingSource creditRatingSource;

    @Nullable
    // 0 - Short Term
    // 1 - Long Term
    private Character natureOfRatingOfParentFirm;


    private String creditRating;

    private Integer gradeAsNumber;

}
