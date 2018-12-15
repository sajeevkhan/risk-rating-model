package com.pfs.riskmodel.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by sajeev on 05-Dec-18.
 */
@Entity
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class RiskSubFactorAttribute extends AggregateRoot<RiskSubFactorAttribute>  {

    @Getter(AccessLevel.PUBLIC)
    private String description;

    @Getter(AccessLevel.PUBLIC)
    private BigDecimal riskSubFactorScore;

    @Getter(AccessLevel.PUBLIC)
    private BigDecimal weightage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({ @JoinColumn(name = "riskSubFactor__ID", referencedColumnName = "ID", nullable = true) })
    RiskSubFactor riskSubFactor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RiskSubFactorAttribute that = (RiskSubFactorAttribute) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (riskSubFactorScore != null ? !riskSubFactorScore.equals(that.riskSubFactorScore) : that.riskSubFactorScore != null)
            return false;
        return weightage != null ? weightage.equals(that.weightage) : that.weightage == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (riskSubFactorScore != null ? riskSubFactorScore.hashCode() : 0);
        result = 31 * result + (weightage != null ? weightage.hashCode() : 0);
        return result;
    }
}

