package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.domain.RiskComponent;
 import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.repository.RiskComponentRepository;
 import com.pfs.riskmodel.repository.RiskTypeRepository;
import com.pfs.riskmodel.service.IRiskComponentService;
 import com.pfs.riskmodel.service.IRiskTypeService;
 import com.pfs.riskmodel.service.validator.RiskTypeValidator;
import com.pfs.riskmodel.util.ValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RiskTypeService implements IRiskTypeService {

    @Autowired
    RiskTypeRepository riskTypeRepository;

    @Autowired
    RiskTypeValidator riskTypeValidator;

    @Autowired
    IRiskComponentService iRiskComponentService;

    @Autowired
    RiskComponentRepository riskComponentRepository;

    @Override
    public Map<String, Object> createRiskType(RiskType riskType) {
        Map<String, Object> result = new HashMap<>();

        ValidationResult validationResult =  riskTypeValidator.validate(riskType);
        result.put("ValidationResult", validationResult);
        if (validationResult.isFailed()) {
            return result;
        }

        riskType =  riskTypeRepository.save(riskType);
        result.put("RiskType", riskType);
        return result;
    }

    @Override
    public Map<String, Object> updateRiskType(RiskType riskType) {

        //List of RiskComponent Ids for Deletion
        List<Long> idsForDeletion = new ArrayList<>();

        Map<String, Object> result = new HashMap<>();

        ValidationResult validationResult =  riskTypeValidator.validate(riskType);
        if (validationResult.isFailed()) {
            result.put("ValidationResult", validationResult);
            return result;
        }

        // Get Existing Risk Type
        RiskType riskTypeExisting;
        riskTypeExisting = riskTypeRepository.getOne(riskType.getId());

        if (riskTypeExisting == null || riskTypeExisting.getId() == null){
            validationResult.setNotFound(true);
            result.put("ValidationResult", validationResult);
            return null;
        }

        //Update Header Attribtues
        riskTypeExisting.setDescription(riskType.getDescription());
        riskTypeExisting.setScore(riskType.getScore());


        // Update RiskComponent Items
        for (RiskComponent riskComponent: riskType.getRiskComponents()) {

            Boolean itemFound = false;

            //New Item
            if (riskComponent.getId() == null) {
                riskTypeExisting.addRiskComponent(riskComponent);
            }

            // Modified Items
            for (RiskComponent riskComponentExisting : riskType.getRiskComponents()) {

                if ( riskComponentExisting.getId() == riskComponent.getId() ) {
                    itemFound = true;
                    riskComponentExisting.setDescription(riskComponent.getDescription());
                    riskComponentExisting.setScore(riskComponent.getScore());
                    riskComponentExisting.setWeightage(riskComponent.getWeightage());
                    result =  iRiskComponentService.updateRiskComponent(riskComponentExisting);

                    ValidationResult validationResultRiskComponent = (ValidationResult) result.get("ValidationResult");
                    if (validationResultRiskComponent.isFailed() == true) {
                        result.put("ValidationResult", validationResult);
                        return result;
                    }
                }
            }

            if (itemFound = false)
                idsForDeletion.add(riskComponent.getId());

        }
        //Update Risk Type
        riskType = riskTypeRepository.save(riskType);


        // Delete the Item entries
        for (Long id: idsForDeletion) {
            riskComponentRepository.deleteById(id);
        }

        result.put("ValidationResult", validationResult);
        result.put("RiskType", riskTypeExisting);

        return result;

    }
}
