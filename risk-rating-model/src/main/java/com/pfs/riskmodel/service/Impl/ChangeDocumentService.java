package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.repository.*;
import com.pfs.riskmodel.service.IChangeDocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static java.lang.Enum.valueOf;
import static org.javers.core.diff.ListCompareAlgorithm.LEVENSHTEIN_DISTANCE;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ChangeDocumentService implements IChangeDocumentService {

    @Autowired
    RiskModelTemplateRepository riskModelTemplateRepository;

    @Autowired
    RiskTypeRepository riskTypeRepository;

    @Autowired
    ChangeDocumentRepository changeDocumentRepository;

    ChangeDocument changeDocument;

   @Autowired
   RiskModelHeader riskModelHeader;




    @Override
    public ChangeDocument createChangeDocument(RiskModelTemplate oldRiskModel,
                                        RiskModelTemplate newRiskModel,
                                        Integer action,
                                        String userName) {

        changeDocument = new ChangeDocument();

        switch (action) {
            case 1:
                if (oldRiskModel == null) {
                    changeDocument.setAction("New Valuation");
                    changeDocument = prepareCreateChangeDocument(newRiskModel,userName);
                }
                else{
                    changeDocument.setAction("Repeat Valuation");
                    changeDocument = prepareUpdateChangeDocument(oldRiskModel,newRiskModel,userName);
                }
                break;
            case 2:
                changeDocument.setAction("Sent for Approval");
                changeDocument = prepareUpdateChangeDocument(oldRiskModel,newRiskModel,userName);
                break;
            case 3:
                changeDocument.setAction("Rejected by Supervisor");
                changeDocument = prepareUpdateChangeDocument(oldRiskModel,newRiskModel,userName);
                break;
            case 4:
                changeDocument.setAction("Approved by Supervisor ");
                changeDocument = prepareUpdateChangeDocument(oldRiskModel,newRiskModel,userName);
                break;
        }

        return changeDocument;
    }

    @Override
    public ChangeDocument saveChangeDocument(ChangeDocument changeDocument) {
        changeDocument = changeDocumentRepository.save(changeDocument);
        return changeDocument;
    }

    private ChangeDocument prepareCreateChangeDocument(RiskModelTemplate riskModelTemplate, String userName) {

        changeDocument = prepareHeader(riskModelTemplate,userName);
        return  changeDocument;

        //changeDocumentRepository.save(changeDocument);
    }





    private ChangeDocument prepareUpdateChangeDocument(RiskModelTemplate oldRiskModel, RiskModelTemplate newRiskModel, String userName) {

        changeDocument = prepareHeader(newRiskModel,userName);


        // Compare RiskModel Header //given
        Javers javers = JaversBuilder.javers()
                .withListCompareAlgorithm(LEVENSHTEIN_DISTANCE)
                .build();
        RiskModelHeader oldRiskModelHeader = riskModelHeader.getRiskModelHeader(oldRiskModel);
        RiskModelHeader newRiskModelHeader = riskModelHeader.getRiskModelHeader(newRiskModel);

        Diff diff = javers.compare(oldRiskModelHeader,newRiskModelHeader);

        List<ChangeDocumentItem> changeDocumentItems = new ArrayList<>();
        changeDocumentItems = prepareChangeDocumentItems(diff,changeDocumentItems);


        for (RiskType newriskType: newRiskModel.getRiskTypes()) {
            for (RiskType oldRiskType: oldRiskModel.getRiskTypes()) {
                if (oldRiskType.getId().equals(newriskType.getId())) {
                    diff = javers.compare(oldRiskType,newriskType);
                    changeDocumentItems = prepareChangeDocumentItems(diff,changeDocumentItems);
                }
            }
        }

        changeDocument.setChangeDocumentItems(changeDocumentItems);


        for (ChangeDocumentItem changeDocumentItem: changeDocumentItems) {
            System.out.println(changeDocumentItem.toString());
        }

        return changeDocument;
    }


    private List<ChangeDocumentItem> prepareChangeDocumentItems(Diff diff, List<ChangeDocumentItem> changeDocumentItems) {

        //List<ChangeDocumentItem> changeDocumentItems = new ArrayList<>();

        for (Change change: diff.getChanges()) {

            System.out.println(change.getAffectedGlobalId());
            System.out.println(change.getAffectedObject());
            System.out.println(change.getCommitMetadata());

        }

        int i = changeDocumentItems.size() + 1;
        for (ValueChange change: diff.getChangesByType(ValueChange.class)) {

            ChangeDocumentItem changeDocumentItem = new ChangeDocumentItem();
            changeDocumentItem.setItemNo(i);
            if (change.getPropertyName().equals("createdAt") || change.getPropertyName().equals("updatedAt"))
                continue;

            Object object = change.getAffectedObject().get();
            Map<String, String> result = getObjectDetails(object.getClass().getName(),object);

            changeDocumentItem.setEntityName(object.getClass().getName().toString());
            changeDocumentItem.setEntityDescription(result.get("description"));
            changeDocumentItem.setAttributeName(change.getPropertyName());
            changeDocumentItem.setNewValue(change.getRight().toString());
            changeDocumentItem.setOldValue(change.getLeft().toString());
            changeDocumentItem.setTableKey(result.get("id"));

            changeDocumentItems.add(changeDocumentItem);

            i++;

        }


        return changeDocumentItems;

    }

    private Map<String, String>    getObjectDetails(String className, Object object ) {

        Object objectParsed = new Object();
        System.out.println(className);

        Map<String, String> result = new HashMap<>();

        try {

            switch (className) {
                case "com.pfs.riskmodel.domain.RiskType":
                    RiskType riskType = (RiskType) object;
                    result.put("id", riskType.getId().toString());
                    result.put("description", riskType.getDescription());
                    return result;
                case "com.pfs.riskmodel.domain.RiskSubFactorAttribute":
                    RiskSubFactorAttribute riskSubFactorAttribute = (RiskSubFactorAttribute) object;
                    result.put("id", riskSubFactorAttribute.getId().toString());
                    result.put("description", riskSubFactorAttribute.getDescription());
                    return result;
                case "com.pfs.riskmodel.domain.RiskSubFactor":
                    RiskSubFactor riskSubFactor = (RiskSubFactor) object;
                    result.put("id", riskSubFactor.getId().toString());
                    result.put("description", riskSubFactor.getDescription());
                    return result;
                case "com.pfs.riskmodel.domain.RiskFactor":
                    RiskFactor riskFactor = (RiskFactor) object;
                    result.put("id", riskFactor.getId().toString());
                    result.put("description", riskFactor.getDescription());
                    return result;
                case "com.pfs.riskmodel.domain.RiskComponent":
                    RiskComponent riskComponent = (RiskComponent) object;
                    result.put("id", riskComponent.getId().toString());
                    result.put("description", riskComponent.getDescription());
                    return result;
                case "com.pfs.riskmodel.service.Impl.RiskModelHeader":
                    RiskModelHeader riskModelHeader = (RiskModelHeader) object;
                    result.put("id", riskModelHeader.getId().toString());
                    result.put("description", riskModelHeader.getDescription());
                    return result; }

        } catch ( Exception ex) {

            System.out.println(className);
        }
             return null;
    }



    private ChangeDocument prepareHeader (RiskModelTemplate riskModelTemplate, String userName) {

        //ChangeDocument changeDocument = new ChangeDocument();
        changeDocument.setRiskModelTemplateId(riskModelTemplate.getId());
        changeDocument.setLoanNumber(riskModelTemplate.getLoanNumber());
        changeDocument.setDate(new Date());
        changeDocument.setUserName(userName);
        changeDocument.setRiskProjectType(riskModelTemplate.getRiskProjectType());
        changeDocument.setProjectRiskLevel(riskModelTemplate.getProjectRiskLevel());
        return changeDocument;
    }


}
