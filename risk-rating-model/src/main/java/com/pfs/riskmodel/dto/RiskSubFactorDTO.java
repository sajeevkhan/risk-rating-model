package com.pfs.riskmodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by sajeev on 15-Dec-18.
 */
@Getter
@Setter
public class RiskSubFactorDTO {

    private Long id;
    private String description;
    private BigDecimal riskSubFactorScore;
    private BigDecimal weightage;

    private Set<RiskSubFactorAttribute> riskSubFactorAttribute;
}
