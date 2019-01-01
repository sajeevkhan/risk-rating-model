package com.pfs.riskmodel.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by sajeev on 31-Dec-18.
 */

@Getter
@Setter
public class RiskModelSummaryDTO {
    protected Long id;
    private Integer itemNo;
    private String name;
    private String Value;
}
