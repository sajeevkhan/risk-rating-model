package com.pfs.riskmodel.dto;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskParentalNotchUpCondition;
import com.pfs.riskmodel.domain.RiskSubFactor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
//    private String  sourceOfRatingOfPaentalNotchUp;
//    private String  obligorRatingGradeOfParentFirm;
//    private String  ratingGradeOfParentEntity;
//    private Boolean parentRatingBetterOrNot;
//    private Boolean isBorrowerRatingAtD;

    private Boolean isParentalNotchUpApplicable;
    private Double  parentalNotchUpScore;

    private List<RiskSubFactorDTO> riskSubFactors;

    public void addRiskSubFactorDTO(RiskSubFactorDTO riskSubFactorDTO) {
        if (riskSubFactors == null) {
            riskSubFactors = new ArrayList<>();
        }
        riskSubFactors.add(riskSubFactorDTO);
    }


    private List<RiskParentalNotchUpConditionDTO> riskParentalConditions;
    public RiskParentalNotchUpConditionDTO addRiskSubFactor (RiskParentalNotchUpConditionDTO riskParentalNotchUpConditionDTO) {

        if (riskParentalConditions == null) {
            riskParentalConditions = new ArrayList<>();
        }

        this.riskParentalConditions.add(riskParentalNotchUpConditionDTO);
        return riskParentalNotchUpConditionDTO;
    }



}
