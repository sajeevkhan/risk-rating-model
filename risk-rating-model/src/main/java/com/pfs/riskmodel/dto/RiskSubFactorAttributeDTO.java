package com.pfs.riskmodel.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * Created by sajeev on 15-Dec-18.
 */
@Getter
@Setter
public class RiskSubFactorAttributeDTO {

     private Long id;
     private Integer itemNo;

     @Column(columnDefinition = "LONGTEXT")
     private String description;

     private Double score;

     private Double weightage;

     private Character deflatorIndicator;


     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;

          RiskSubFactorAttributeDTO that = (RiskSubFactorAttributeDTO) o;

          if (description != null ? !description.equals(that.description) : that.description != null) return false;
          if (score != null ? !score.equals(that.score) : that.score != null)
               return false;
          return weightage != null ? weightage.equals(that.weightage) : that.weightage == null;
     }

     @Override
     public int hashCode() {
          int result = description != null ? description.hashCode() : 0;
          result = 31 * result + (score != null ? score.hashCode() : 0);
          result = 31 * result + (weightage != null ? weightage.hashCode() : 0);
          return result;
     }
}
