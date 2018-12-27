package com.pfs.riskmodel.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

/**
 * Created by sajeev on 15-Dec-18.
 */
@Getter
@Setter
public class RiskRatingModifierAttributeDTO {

     private Long id;
     private Integer itemNo;

     @Column(columnDefinition = "LONGTEXT")
     private String description;

     private Character yesNoIndicator;

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;

          RiskRatingModifierAttributeDTO that = (RiskRatingModifierAttributeDTO) o;

          if (itemNo != null ? !itemNo.equals(that.itemNo) : that.itemNo != null) return false;
          if (description != null ? !description.equals(that.description) : that.description != null) return false;
          return yesNoIndicator != null ? yesNoIndicator.equals(that.yesNoIndicator) : that.yesNoIndicator == null;
     }

     @Override
     public int hashCode() {
          int result = itemNo != null ? itemNo.hashCode() : 0;
          result = 31 * result + (description != null ? description.hashCode() : 0);
          result = 31 * result + (yesNoIndicator != null ? yesNoIndicator.hashCode() : 0);
          return result;
     }
}
