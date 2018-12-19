package com.pfs.riskmodel.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private Integer itemNo;

    @Getter(AccessLevel.PUBLIC)
    private String description;

    @Getter(AccessLevel.PUBLIC)
    private Double score;

    @Getter(AccessLevel.PUBLIC)
    private Double weightage;


//    @NotNull
//    //@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name="riskSubFactor__id",referencedColumnName = "id")
//    RiskSubFactor riskSubFactor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RiskSubFactorAttribute that = (RiskSubFactorAttribute) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null)
            return false;
        return weightage != null ? weightage.equals(that.weightage) : that.weightage == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (weightage != null ? weightage.hashCode() : 0);
        return result;
    }
}

