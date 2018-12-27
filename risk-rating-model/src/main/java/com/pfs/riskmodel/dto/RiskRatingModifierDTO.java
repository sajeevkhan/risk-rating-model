package com.pfs.riskmodel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 15-Dec-18.
 */
@Getter
@Setter
public class RiskRatingModifierDTO {

    private Long id;
    private Integer itemNo;
    private String description;
    private Double score;
    private String computingMethodCode;
    private String computingMethodDescription;



    private Set<RiskRatingModifierAttributeDTO> riskRatingModifierAttributes;

    public void addRiskRatingModifierAttribute(RiskRatingModifierAttributeDTO riskRatingModifierAttributeDTO) {
        if (riskRatingModifierAttributes == null){
            riskRatingModifierAttributes = new HashSet<>();
        }
        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);
    }
}
