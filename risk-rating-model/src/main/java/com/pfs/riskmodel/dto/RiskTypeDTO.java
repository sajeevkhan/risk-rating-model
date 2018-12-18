package com.pfs.riskmodel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Getter
@Setter
public class RiskTypeDTO {

    private Long id;
    private Integer itemNo;
    private String description;
    private Double score;

    private Set<RiskComponentDTO> riskComponents;


}
