package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.repository.*;
import com.pfs.riskmodel.service.IChangeDocumentService;
import com.pfs.riskmodel.service.IRiskComponentService;
import com.pfs.riskmodel.service.IRiskFactorService;
import com.pfs.riskmodel.service.validator.RiskComponentValidator;
import com.pfs.riskmodel.util.ValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.javers.core.diff.ListCompareAlgorithm.LEVENSHTEIN_DISTANCE;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChangeDocumentService implements IChangeDocumentService {

    @Autowired
    RiskModelTemplateRepository riskModelTemplateRepository;

    @Autowired
    RiskTypeRepository riskTypeRepository;

    @Override
    public boolean compareEntities(RiskModelTemplate riskModel, Long id) {

//        RiskModelTemplate originalRiskModel = riskModelTemplateRepository.getOne(id);
//        //given
//        Javers javers = JaversBuilder.javers()
//                .withListCompareAlgorithm(LEVENSHTEIN_DISTANCE)
//                .build();
//        originalRiskModel.setId(1L);
//        riskModel.setId(1L);
//
//        for (RiskType riskType: riskModel.getRiskTypes()) {
//            riskType.setGrade("Changed GRADE ");
//            riskType.setDescription("Changed DESCR");
//        }
//
//
//        for (RiskType riskType: riskModel.getRiskTypes()) {
//
//            RiskType riskType1 = riskTypeRepository.getOne(riskType.getId());
//
//            Diff diff1 = javers.compare(riskType, riskType1);
//
//            List<Change> changes = diff1.getChanges();
//
//            for (Change change : changes) {
//                System.out.println("Change :" + change.toString());
//            }
//        }
        return false;
    }
}
