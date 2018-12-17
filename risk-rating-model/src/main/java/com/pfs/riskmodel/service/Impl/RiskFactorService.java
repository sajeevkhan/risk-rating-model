package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.domain.RiskSubFactor;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import com.pfs.riskmodel.repository.RiskFactorRepository;
import com.pfs.riskmodel.repository.RiskSubFactorRepository;
import com.pfs.riskmodel.service.IRiskFactorService;
import com.pfs.riskmodel.service.IRiskSubFactorService;
import com.pfs.riskmodel.service.validator.RiskFactorValidator;
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
public class RiskFactorService implements IRiskFactorService {

    @Autowired
    RiskFactorRepository riskFactorRepository;

    @Autowired
    RiskFactorValidator riskFactorValidator;

    @Autowired
    IRiskSubFactorService iRiskSubFactorService;

    @Autowired
    RiskSubFactorRepository riskSubFactorRepository;

    @Override
    public Map<String, Object> createRiskFactor(RiskFactor riskFactor) {
        Map<String, Object> result = new HashMap<>();

        ValidationResult validationResult =  riskFactorValidator.validate(riskFactor);
        result.put("ValidationResult", validationResult);
        if (validationResult.isFailed()) {
            return result;
        }

        riskFactor =  riskFactorRepository.save(riskFactor);
        result.put("RiskFactor", riskFactor);
        return result;
    }

    @Override
    public Map<String, Object> updateRiskFactor(RiskFactor riskFactor) {

        //List of RiskSubFactor Ids for Deletion
        List<Long> idsForDeletion = new ArrayList<>();

        Map<String, Object> result = new HashMap<>();

        ValidationResult validationResult =  riskFactorValidator.validate(riskFactor);
        if (validationResult.isFailed()) {
            result.put("ValidationResult", validationResult);
            return result;
        }

        // Get Existing Risk Sub Factor
        RiskFactor riskFactorExisting;
        riskFactorExisting = riskFactorRepository.getOne(riskFactor.getId());

        if (riskFactorExisting == null || riskFactorExisting.getId() == null){
            validationResult.setNotFound(true);
            result.put("ValidationResult", validationResult);
            return null;
        }

        //Update Header Attribtues
        riskFactorExisting.setDescription(riskFactor.getDescription());
        riskFactorExisting.setComputingMethod(riskFactor.getComputingMethod());
        riskFactorExisting.setScoreType(riskFactor.getScoreType());
        riskFactorExisting.setWeightage(riskFactor.getWeightage());
        riskFactorExisting.setScore(riskFactor.getScore());


        // Update RiskSubFactor Items
        for (RiskSubFactor riskSubFactor: riskFactor.getRiskSubFactors()) {

            Boolean itemFound = false;

            //New Item
            if (riskSubFactor.getId() == null) {
                riskFactorExisting.addRiskSubFactor(riskSubFactor);
            }

            // Modified Items
            for (RiskSubFactor riskSubFactorExisting : riskFactor.getRiskSubFactors()) {

                if ( riskSubFactorExisting.getId() == riskSubFactor.getId() ) {
                    itemFound = true;
                    riskSubFactorExisting.setDescription(riskSubFactor.getDescription());
                    riskSubFactorExisting.setScore(riskSubFactor.getScore());
                    riskSubFactorExisting.setWeightage(riskSubFactor.getWeightage());


                    result =  iRiskSubFactorService.updateRiskSubFactor(riskSubFactorExisting);
                    ValidationResult validationResultRiskSubFactor = (ValidationResult) result.get("ValidationResult");
                    if (validationResultRiskSubFactor.isFailed() == true) {
                        result.put("ValidationResult", validationResult);
                        return result;
                    }
                }
            }

            if (itemFound = false)
                idsForDeletion.add(riskSubFactor.getId());

        }
        //Update Risk Factor
        riskFactor = riskFactorRepository.save(riskFactorExisting);


        // Delete the Item entries
        for (Long id: idsForDeletion) {
            riskSubFactorRepository.deleteById(id);
        }

        result.put("ValidationResult", validationResult);
        result.put("RiskFactor", riskFactorExisting);

        return result;

    }
}
