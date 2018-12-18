package com.pfs.riskmodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Integer itemNo;
    private String description;
    private Double score;
    private Double weightage;


    private Set<RiskSubFactorAttributeDTO> riskSubFactorAttributes;

    public void addRiskSubFactorAttribute(RiskSubFactorAttributeDTO riskSubFactorAttributeDTO) {
        riskSubFactorAttributes.add(riskSubFactorAttributeDTO);
    }
}
