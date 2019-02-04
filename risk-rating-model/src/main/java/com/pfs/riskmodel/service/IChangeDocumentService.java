package com.pfs.riskmodel.service;

import com.pfs.riskmodel.domain.ChangeDocument;
import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskModelTemplate;

import java.util.Map;

/**
 * Created by sajeev on 15-Dec-18.
 */
public interface IChangeDocumentService {

    public ChangeDocument createChangeDocument (RiskModelTemplate oldRiskModel,
                                                RiskModelTemplate newRiskModel,
                                                Integer action,
                                                String userName);


    public ChangeDocument saveChangeDocument (ChangeDocument changeDocument);
}
