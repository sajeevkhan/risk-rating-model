package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.domain.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by sajeev on 03-Feb-19.
 */
@Getter
@Setter
@Component
//ransactional

public class RiskModelHeader {

    private Long id;
    private ModelCategory modelCategory;
    private Integer modelType;
    private String version;
    private String status;
    private String createdBy;
    private String reviewedBy;
    private RiskPurpose purpose;
    private String purposeCode;
    private String purposeDescription;
    private String processInstanceId;
    private WorkflowStatus workflowStatus;
    private String workflowStatusCode;
    private String workflowStatusDescription;
    private String description;
    private RiskProjectType riskProjectType;
    private ProjectRiskLevel projectRiskLevel;
    private ComputingMethod computingMethod;
    private String loanNumber;
    private String projectName;
    private Double loanAmountInCrores;
    private Date ratingDate;
    private Double score;
    private String overallProjectGrade;
    private String modifiedProjectGrade;
    private Integer modifiedProjectGradeAsNumber;
    private String afterParentalNotchUpGrade;
    private String finalProjectGrade;


public RiskModelHeader getRiskModelHeader(RiskModelTemplate riskModelTemplate) {

    RiskModelHeader riskModelHeader = new RiskModelHeader();

    riskModelHeader.setId(riskModelTemplate.getId());
    riskModelHeader.setModelCategory(riskModelTemplate.getModelCategory());
    riskModelHeader.setModelType(riskModelTemplate.getModelType());
    riskModelHeader.setVersion(riskModelTemplate.getVersion());
    riskModelHeader.setStatus(riskModelTemplate.getStatus());
    riskModelHeader.setCreatedBy(riskModelTemplate.getCreatedBy());
    riskModelHeader.setReviewedBy(riskModelTemplate.getReviewedBy());
    riskModelHeader.setPurpose(riskModelTemplate.getPurpose());
    riskModelHeader.setPurposeCode(riskModelTemplate.getPurposeCode());
    riskModelHeader.setPurposeDescription(riskModelTemplate.getPurposeDescription());
    riskModelHeader.setProcessInstanceId(riskModelTemplate.getProcessInstanceId());

    riskModelHeader.setWorkflowStatus(riskModelTemplate.getWorkflowStatus());
    riskModelHeader.setWorkflowStatusCode(riskModelTemplate.getWorkflowStatusCode());
    riskModelHeader.setWorkflowStatusDescription(riskModelTemplate.getWorkflowStatusDescription());
    riskModelHeader.setDescription(riskModelTemplate.getDescription());
    riskModelHeader.setRiskProjectType(riskModelTemplate.getRiskProjectType());
    riskModelHeader.setProjectRiskLevel(riskModelTemplate.getProjectRiskLevel());

    riskModelHeader.setComputingMethod(riskModelTemplate.getComputingMethod());
    riskModelHeader.setLoanNumber(riskModelTemplate.getLoanNumber());
    riskModelHeader.setProjectName(riskModelTemplate.getProjectName());

    riskModelHeader.setLoanAmountInCrores(riskModelTemplate.getLoanAmountInCrores());
    riskModelHeader.setRatingDate(riskModelTemplate.getRatingDate());
    riskModelHeader.setScore(riskModelTemplate.getScore());

    riskModelHeader.setOverallProjectGrade(riskModelTemplate.getOverallProjectGrade());
    riskModelHeader.setModifiedProjectGrade(riskModelTemplate.getModifiedProjectGrade());
    riskModelHeader.setModifiedProjectGradeAsNumber(riskModelTemplate.getModifiedProjectGradeAsNumber());

    riskModelHeader.setAfterParentalNotchUpGrade(riskModelTemplate.getAfterParentalNotchUpGrade());
    riskModelHeader.setFinalProjectGrade(riskModelTemplate.getFinalProjectGrade());

    return riskModelHeader;
}

}
