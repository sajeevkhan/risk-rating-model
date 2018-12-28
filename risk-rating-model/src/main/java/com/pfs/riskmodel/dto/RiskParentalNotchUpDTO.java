package com.pfs.riskmodel.dto;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskSubFactor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Getter
@Setter
public class RiskParentalNotchUpDTO {

    private Long    id;
    private Integer itemNo;
    private String  description;
    private Boolean isParentalNotchUpApplicable;
    private String  sourceOfRatingOfPaentalNotchUp;
    private String  obligorRatingGradeOfParentFirm;
    private String  ratingGradeOfParentEntity;
    private Boolean parentRatingBetterOrNot;
    private Boolean isBorrowerRatingAtD;
    private Double  parentalNotchUpScore;

    private Set<RiskSubFactorDTO> riskSubFactors;

    public void addRiskSubFactorDTO(RiskSubFactorDTO riskSubFactorDTO) {
        if (riskSubFactors == null) {
            riskSubFactors = new HashSet<>();
        }
        riskSubFactors.add(riskSubFactorDTO);
    }



}
