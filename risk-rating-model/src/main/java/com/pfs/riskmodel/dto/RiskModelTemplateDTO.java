package com.pfs.riskmodel.dto;

import com.pfs.riskmodel.domain.ComputingMethod;
import com.pfs.riskmodel.domain.ProjectRiskLevel;
import com.pfs.riskmodel.domain.ProjectType;
import com.pfs.riskmodel.domain.RiskType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Getter
@Setter
public class RiskModelTemplateDTO {

    private Long id;
    private String version;
    private String active;
    private String description;


    /*
    "01", "Renewable Project"
    "02", "Infrastructure Transmission Project"
    "03", "Infrastructure Road Project – Hybrid Annuity"
    "04", "Infrastrucutre Road Project - Toll "
     "05", "Holding Company"
*/
     private String projectTypeCode;


    /*
    "01", "Build Phase "
    "02", "Operational Risk Phase"
    */

     private String projectRiskLevelCode;

    /**
     ("01", "Weighted");
     ("02", "Sum");
     ("03", "Minimum");
     ("04", "Maximum");
     ("05", "Equals");
     ("06", "Multiply")
     */
     private String computingMethodCode;


     private Double score;



    private Set<RiskTypeDTO> riskTypes;


}
