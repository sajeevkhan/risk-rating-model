package com.pfs.riskmodel.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    private Integer modifierType;
    private Boolean subInvestmentGradeCapping;
    private Integer numberOfNotchesDown;



    private List<RiskRatingModifierAttributeDTO> riskRatingModifierAttributes;

    public void addRiskRatingModifierAttribute(RiskRatingModifierAttributeDTO riskRatingModifierAttributeDTO) {
        if (riskRatingModifierAttributes == null){
            riskRatingModifierAttributes = new ArrayList<>();
        }
        riskRatingModifierAttributes.add(riskRatingModifierAttributeDTO);
    }
}
