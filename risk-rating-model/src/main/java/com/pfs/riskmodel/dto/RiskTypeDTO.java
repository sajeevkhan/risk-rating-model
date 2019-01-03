package com.pfs.riskmodel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private String grade;

    private List<RiskComponentDTO> riskComponents;

    public void addRiskComponentDTO (RiskComponentDTO riskComponentDTO) {
        if (riskComponents == null){
            riskComponents = new ArrayList<>();
        }
        riskComponents.add(riskComponentDTO);
    }


}
