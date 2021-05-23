package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskProjectType;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.dto.RiskModelReportDTO;
import com.pfs.riskmodel.resource.RiskEvaluationInSAP;

import java.util.List;
import java.util.Map;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface IRiskModelTemplateService {

    public Map<String, Object> createRiskModelTemplate(RiskModelTemplate riskModelTemplate);
    public Map<String, Object>  updateRiskModelTemplate(RiskModelTemplate riskModelTemplate);
    public Map<String , Object> findByProjectTypeAndRiskLevel (String projectType, String projectRiskLevel);

    public RiskModelTemplate getByRiskModelId(Long id);

    public List<RiskModelReportDTO> findByLoanNumberAndRiskProjectTypeAndProjectName(String loanNumber, String riskProjectTypeCode, String projectName);


}
