package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskModelTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface IWorkflowService {

    public Map<String, Object> processWorkflowAction(RiskModelTemplate riskModelTemplate,
                                                     Integer action,
                                                     HttpServletRequest httpServletRequest) ;

}
