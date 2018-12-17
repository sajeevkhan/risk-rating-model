package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.dao.RiskSubFactorAttributeDao;
import com.pfs.riskmodel.domain.RiskSubFactor;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import com.pfs.riskmodel.repository.RiskSubFactorAttributeRepository;
import com.pfs.riskmodel.service.IRiskSubFactorAttributeService;
import com.pfs.riskmodel.service.validator.RiskSubFactorAttributeValidator;
import com.pfs.riskmodel.service.validator.RiskSubFactorValidator;
import com.pfs.riskmodel.util.Check;
import com.pfs.riskmodel.util.ValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by sajeev on 06-Dec-18.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RiskSubFactorAttributeService implements IRiskSubFactorAttributeService {


    @Autowired
    private RiskSubFactorAttributeRepository riskSubFactorAttributeRepository;

    @Autowired
    private RiskSubFactorAttributeDao riskSubFactorAttributeDao;

    @Autowired
    RiskSubFactorAttributeValidator riskSubFactorAttributeValidator;

    @Override
    public Map<String, Object> createRiskSubFactorAttribute(RiskSubFactorAttribute riskSubFactorAttribute) {

        Map<String, Object> result = new HashMap<>();

        ValidationResult validationResult =  riskSubFactorAttributeValidator.validate(riskSubFactorAttribute);
        result.put("ValidationResult", validationResult);

        if (validationResult.isFailed()) {
            return result;
        }

        riskSubFactorAttribute = riskSubFactorAttributeRepository.save(riskSubFactorAttribute);
        result.put("RiskSubFactorAttribute", riskSubFactorAttribute);
        return result;

    }

    @Override
    public Map<String, Object> update(RiskSubFactorAttribute riskSubFactorAttribute) {

        // Get Existing Entity
        RiskSubFactorAttribute riskSubFactorAttributeExisting;

        Map<String, Object> result = new HashMap<>();

        ValidationResult validationResult =  riskSubFactorAttributeValidator.validate(riskSubFactorAttribute);
        result.put("ValidationResult", validationResult);

        if (validationResult.isFailed()) {
            return result;
        }

             if (riskSubFactorAttribute.getId() != null) {

                riskSubFactorAttributeExisting = riskSubFactorAttributeRepository.getOne(riskSubFactorAttribute.getId());

                //If Entity Not Found, Return Null
                if (riskSubFactorAttributeExisting == null) return null;
                else {
                    // Save Entity
                    riskSubFactorAttributeExisting.setDescription(riskSubFactorAttribute.getDescription());
                    riskSubFactorAttributeExisting.setScore(riskSubFactorAttribute.getScore());
                    riskSubFactorAttributeExisting.setWeightage(riskSubFactorAttribute.getWeightage());
                    riskSubFactorAttributeRepository.save(riskSubFactorAttributeExisting);
                    result.put("RiskSubFactorAttribute", riskSubFactorAttribute);
                 }
            }


        return null;
    }

    @Override
    public RiskSubFactorAttribute getById(long id) {
        Optional<RiskSubFactorAttribute> riskSubFactorAttribute = riskSubFactorAttributeRepository.findById(id);

        return riskSubFactorAttribute.get();
    }
}
