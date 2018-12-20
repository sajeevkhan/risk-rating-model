package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.domain.RiskSubFactor;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import com.pfs.riskmodel.repository.RiskSubFactorAttributeRepository;
import com.pfs.riskmodel.repository.RiskSubFactorRepository;
import com.pfs.riskmodel.service.IRiskSubFactorService;
import com.pfs.riskmodel.service.validator.RiskSubFactorValidator;
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
 * Created by sajeev on 15-Dec-18.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RiskSubFactorService implements IRiskSubFactorService {

    @Autowired
    RiskSubFactorRepository riskSubFactorRepository;

    @Autowired
    RiskSubFactorAttributeRepository riskSubFactorAttributeRepository;

    @Autowired
    RiskSubFactorValidator riskSubFactorValidator;

    @Override
    public  Map<String, Object> createRiskSubFactor(RiskSubFactor riskSubFactor) {

        Map<String, Object> result = new HashMap<>();

        ValidationResult validationResult =  riskSubFactorValidator.validate(riskSubFactor);
        result.put("ValidationResult", validationResult);
        if (validationResult.isFailed()) {

            return result;
        }

        riskSubFactor =  riskSubFactorRepository.save(riskSubFactor);
        result.put("RiskSubFactor", riskSubFactor);

        return result;

    }

    @Override
    public  Map<String, Object> updateRiskSubFactor(RiskSubFactor riskSubFactor) {

        //List of RiskSubFactorAttribute Ids for Deletion
        List<Long> idsForDeletion = new ArrayList<>();

        Map<String, Object> result = new HashMap<>();

        ValidationResult validationResult =  riskSubFactorValidator.validate(riskSubFactor);
        if (validationResult.isFailed()) {
            result.put("ValidationResult", validationResult);
            return result;
        }

        // Get Existing Risk Sub Factor
        RiskSubFactor riskSubFactorExisting;


        riskSubFactorExisting = riskSubFactorRepository.getOne(riskSubFactor.getId());

        if (riskSubFactorExisting == null || riskSubFactorExisting.getId() == null){
            validationResult.setNotFound(true);
            result.put("ValidationResult", validationResult);
            return null;
        }

        // Update RiskSubFactor Header Attributes
        riskSubFactorExisting.setDescription(riskSubFactor.getDescription());
        riskSubFactor.setScore(riskSubFactor.getScore());
        riskSubFactor.setWeightage(riskSubFactor.getWeightage());


        // Update RiskSubFactorAttribute Items
        for (RiskSubFactorAttribute riskSubFactorAttribute: riskSubFactor.getRiskSubFactorAttributes()) {

            Boolean itemFound = false;

            //New Item
            if (riskSubFactorAttribute.getId() == null) {

                riskSubFactorExisting.addRiskSubFactorAttribute(riskSubFactorAttribute);
            }

            // Modified Items
            for (RiskSubFactorAttribute riskSubFactorAttributeExisting : riskSubFactorExisting.getRiskSubFactorAttributes()) {

                 if ( riskSubFactorAttributeExisting.getId() == riskSubFactorAttribute.getId() ) {
                    itemFound = true;
                    riskSubFactorAttributeExisting.setDescription(riskSubFactorAttribute.getDescription());
                    riskSubFactorAttributeExisting.setScore(riskSubFactorAttribute.getScore());
                    riskSubFactorAttributeExisting.setWeightage(riskSubFactorAttributeExisting.getWeightage());
                }
            }

            if (itemFound = false)
             idsForDeletion.add(riskSubFactorAttribute.getId());
        }


        //Update Risk Sub Factor
        riskSubFactorExisting = riskSubFactorRepository.save(riskSubFactorExisting);


        // Delete the Item entries
        for (Long id: idsForDeletion) {
            riskSubFactorAttributeRepository.deleteById(id);
        }

        result.put("ValidationResult", validationResult);
        result.put("RiskSubFactor", riskSubFactorExisting);

        return result;
    }










}
