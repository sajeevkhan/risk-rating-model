package com.pfs.riskmodel.domain;

import com.pfs.riskmodel.dto.RiskTypeDTO;
import lombok.*;
import org.hibernate.annotations.IndexColumn;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by sajeev on 15-Dec-18.
 */
@Entity
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)



public class RiskModelTemplate extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @NotNull
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private ModelCategory modelCategory;

    @NotNull
    // 0 - Template
    // 1 - Project Valuation
    private Integer modelType;


    @NotNull
    private String version;

    @NotNull
    private String status;

    @Nullable
    private String createdBy;

    @Nullable
    private String createdByUserId;

    @Nullable
    private String reviewedBy;

    @Nullable
    private String firstLevelApprover;

    @Nullable
    private String secondLevelApprover;

    @Nullable
    private String thirdLevelApprover;

    @Nullable //1-First Level, 2 - Second Level, 3 - Third Level
    private Integer currentWorkFlowLevel;



    @NotNull
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private RiskPurpose purpose;

    @NotNull
    private String purposeCode;

    @NotNull
    private String purposeDescription;

    // Work flow Process Instance Id (Activiti)
    @Nullable
    private String processInstanceId;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private WorkflowStatus workflowStatus;

    @Nullable
    private String workflowStatusCode;
    @Nullable
    private String workflowStatusDescription;


    @Nullable
    private Boolean applyRatingModifiers;


    @Nullable
    private Boolean applyParentalNotchup;


    @NotNull
    @Size(max = 250)
    private String description;


    /*
    "01", "Renewable Project"
    "02", "Infrastructure Transmission Project"
    "03", "Infrastructure Road Project – Hybrid Annuity"
    "04", "Infrastrucutre Road Project - Toll "
     "05", "Holding Company"
*/
    @NotNull
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private RiskProjectType riskProjectType;


    /*
    "01", "Build Phase "
    "02", "Operational Risk Phase"
    */

    @NotNull
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private ProjectRiskLevel projectRiskLevel;

    /**
     ("01", "Weighted");
     ("02", "Sum");
     ("03", "Minimum");
     ("04", "Maximum");
     ("05", "Equals");
     ("06", "Multiply")
     */
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    private ComputingMethod computingMethod;


    @Nullable
    private String loanNumber;

    @Nullable
    private String loanEnquiryId;


    @NotNull
    private String projectName;

    @NotNull
    private Double loanAmountInCrores;

    @NotNull
    private Date ratingDate;




    @NotNull
    private Double score;

    @Nullable
    private String overallProjectGrade;
    @Nullable
    private String modifiedProjectGrade;

    private Integer modifiedProjectGradeAsNumber;

    @Nullable
    private String afterParentalNotchUpGrade;
    @Nullable
    private String finalProjectGrade;


    @Nullable
    @IndexColumn (name = "INDEX_COL1")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinColumn(name="riskTypeParentalNotchUp__id",referencedColumnName = "id")
    private List<RiskParentalNotchUp> riskParentalNotchUps;


    public RiskParentalNotchUp addRiskParentalNotchUp (RiskParentalNotchUp riskParentalNotchUp) {

        this.getRiskParentalNotchUps().add(riskParentalNotchUp);
        return riskParentalNotchUp;
    }


    @Nullable
    @IndexColumn (name = "INDEX_COL2")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinColumn(name="riskRatingModifier__id",referencedColumnName = "id")
    private List<RiskRatingModifier> riskRatingModifiers;


    public RiskRatingModifier addRiskRatingModifier (RiskRatingModifier riskRatingModifier) {

        this.getRiskRatingModifiers().add(riskRatingModifier);
        return riskRatingModifier;
    }

    @IndexColumn(name = "INDEX_COL3")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinColumn(name="riskModelTemplate__id",referencedColumnName = "id")
    private List<RiskType> riskTypes;


    public RiskType addRiskType (RiskType riskType) {

        this.getRiskTypes().add(riskType);
        return riskType;

    }

    @IndexColumn (name = "INDEX_COL4")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinColumn(name="riskModelSummary__id",referencedColumnName = "id")
    private List<RiskModelSummary> riskModelSummaries;


    public RiskModelSummary addRiskModelSummary (RiskModelSummary riskModelSummary) {

        this.getRiskModelSummaries().add(riskModelSummary);
        return riskModelSummary;

    }


//    //@Override
//    public RiskModelTemplate clone( RiskModelTemplate riskModelTemplate) throws CloneNotSupportedException {
//        RiskModelTemplate clonedObject =  (RiskModelTemplate) super.clone();
//
//        clonedObject = riskModelTemplate;
//
//        return  clonedObject;
//
//    }

    public RiskModelTemplate copy(RiskModelTemplate original) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.obj"));
             ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"))) {
            out.writeObject(original);
            return (RiskModelTemplate) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
