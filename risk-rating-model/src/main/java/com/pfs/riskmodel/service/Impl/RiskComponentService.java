package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.repository.RiskComponentRepository;
import com.pfs.riskmodel.repository.RiskFactorRepository;
import com.pfs.riskmodel.service.IRiskComponentService;
import com.pfs.riskmodel.service.IRiskFactorService;
import com.pfs.riskmodel.service.validator.RiskComponentValidator;
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
public class RiskComponentService implements IRiskComponentService {

    @Autowired
    RiskComponentRepository  riskComponentRepository;

    @Autowired
    RiskComponentValidator riskComponentValidator;

    @Autowired
    IRiskFactorService iRiskFactorService;

    @Autowired
    RiskFactorRepository riskFactorRepository;

    @Override
    public Map<String, Object> createRiskComponent(RiskComponent riskComponent) {
        Map<String, Object> result = new HashMap<>();

        ValidationResult validationResult =  riskComponentValidator.validate(riskComponent);
        result.put("ValidationResult", validationResult);
        if (validationResult.isFailed()) {
            return result;
        }

        riskComponent =  riskComponentRepository.save(riskComponent);
        result.put("RiskComponent", riskComponent);
        return result;
    }

    @Override
    public Map<String, Object> updateRiskComponent(RiskComponent riskComponent) {

        //List of RiskFactor Ids for Deletion
        List<Long> idsForDeletion = new ArrayList<>();

        Map<String, Object> result = new HashMap<>();

        ValidationResult validationResult =  riskComponentValidator.validate(riskComponent);
        if (validationResult.isFailed()) {
            result.put("ValidationResult", validationResult);
            return result;
        }

        // Get Existing Risk Component
        RiskComponent riskComponentExisting;
        riskComponentExisting = riskComponentRepository.getOne(riskComponent.getId());

        if (riskComponentExisting == null || riskComponentExisting.getId() == null){
            validationResult.setNotFound(true);
            result.put("ValidationResult", validationResult);
            return null;
        }

        //Update Header Attribtues
        riskComponentExisting.setDescription(riskComponent.getDescription());
        riskComponentExisting.setComputingMethod(riskComponent.getComputingMethod());
        riskComponentExisting.setScoreType(riskComponent.getScoreType());
        riskComponentExisting.setWeightage(riskComponent.getWeightage());
        riskComponentExisting.setScore(riskComponent.getScore());


        // Update RiskFactor Items
        for (RiskFactor riskFactor: riskComponent.getRiskFactors()) {

            Boolean itemFound = false;

            //New Item
            if (riskFactor.getId() == null) {
                riskComponentExisting.addRiskFactor(riskFactor);
            }

            // Modified Items
            for (RiskFactor riskFactorExisting : riskComponent.getRiskFactors()) {

                if ( riskFactorExisting.getId() == riskFactor.getId() ) {
                    itemFound = true;
                    riskFactorExisting.setDescription(riskFactor.getDescription());
                    riskFactorExisting.setScore(riskFactor.getScore());
                    riskFactorExisting.setWeightage(riskFactor.getWeightage());
                    result =  iRiskFactorService.updateRiskFactor(riskFactorExisting);

                    ValidationResult validationResultRiskFactor = (ValidationResult) result.get("ValidationResult");
                    if (validationResultRiskFactor.isFailed() == true) {
                        result.put("ValidationResult", validationResult);
                        return result;
                    }
                }
            }

            if (itemFound = false)
                idsForDeletion.add(riskFactor.getId());

        }
        //Update Risk Component
        riskComponent = riskComponentRepository.save(riskComponentExisting);


        // Delete the Item entries
        for (Long id: idsForDeletion) {
            riskFactorRepository.deleteById(id);
        }

        result.put("ValidationResult", validationResult);
        result.put("RiskComponent", riskComponentExisting);

        return result;

    }
}
