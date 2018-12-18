package com.pfs.riskmodel.dto;

import com.pfs.riskmodel.domain.ComputingMethod;
import com.pfs.riskmodel.domain.ScoreType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Set;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Getter
@Setter
public class RiskComponentDTO {

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


    private Set<RiskFactorDTO> riskFactors;


    public void addRiskFactorDTO (RiskFactorDTO riskFactorDTO) {

        this.riskFactors.add(riskFactorDTO);
    }
}
