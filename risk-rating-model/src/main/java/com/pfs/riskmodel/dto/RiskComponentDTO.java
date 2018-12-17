package com.pfs.riskmodel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Getter
@Setter
public class RiskComponentDTO {

    private Long id;
    private String description;
    private String computingMethodCode;
    private String scoreTypeCode;
    private Double score;
    private Double weightage;

    private Set<RiskFactorDTO> riskFactors;


    public void addRiskFactorDTO (RiskFactorDTO riskFactorDTO) {

        this.riskFactors.add(riskFactorDTO);
    }
}
