package com.pfs.riskmodel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Getter
@Setter
public class WorkflowTaskDTO {

    private String id;
    private String dateAsString;
    private String projectName;
    private String projectType;
    private String riskLevel;
    private String riskModelId;
    private String requestedBy;

}
