package com.pfs.riskmodel.dto;

import com.pfs.riskmodel.domain.ComputingMethod;
import com.pfs.riskmodel.domain.RiskSubFactor;
import com.pfs.riskmodel.domain.ScoreType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Getter
@Setter
public class RiskFactorDTO {

    private Long id;
    private Integer itemNo;
    private String description;
    private String computingMethodCode;
    private String computingMethodDescription;
    private String scoreTypeCode;
    private String scoreTypeDescription;
    private Double score;
    private Double weightage;

//    private ComputingMethod computingMethod;
//    private ScoreType scoreType;

    private Set<RiskSubFactorDTO> riskSubFactors;


    public void addRiskSubFactorDTO (RiskSubFactorDTO riskSubFactorDTO) {
        if (riskSubFactors == null){
            riskSubFactors = new HashSet<>();
        }
        this.riskSubFactors.add(riskSubFactorDTO);
    }
}
