package com.pfs.riskmodel.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by sajeev on 15-Dec-18.
 */
@Getter
@Setter
public class RiskSubFactorAttributeDTO {

     private Long id;
     private String description;

     private BigDecimal riskSubFactorScore;

     private BigDecimal weightage;

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;

          RiskSubFactorAttributeDTO that = (RiskSubFactorAttributeDTO) o;

          if (description != null ? !description.equals(that.description) : that.description != null) return false;
          if (riskSubFactorScore != null ? !riskSubFactorScore.equals(that.riskSubFactorScore) : that.riskSubFactorScore != null)
               return false;
          return weightage != null ? weightage.equals(that.weightage) : that.weightage == null;
     }

     @Override
     public int hashCode() {
          int result = description != null ? description.hashCode() : 0;
          result = 31 * result + (riskSubFactorScore != null ? riskSubFactorScore.hashCode() : 0);
          result = 31 * result + (weightage != null ? weightage.hashCode() : 0);
          return result;
     }
}
