package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.dao.RiskSubFactorAttributeDao;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import com.pfs.riskmodel.repository.RiskSubFactorAttributeRepository;
import com.pfs.riskmodel.service.IRiskSubFactorAttributeService;
import com.pfs.riskmodel.util.Check;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public RiskSubFactorAttribute createRiskSubFactorAttribute(RiskSubFactorAttribute riskSubFactorAttribute) {

        return riskSubFactorAttributeRepository.save(riskSubFactorAttribute);
    }

    @Override
    public RiskSubFactorAttribute update(RiskSubFactorAttribute riskSubFactorAttribute) {

        // Get Existing Entity
        RiskSubFactorAttribute riskSubFactorAttributeExisting;

        try {

            if (riskSubFactorAttribute.getId() != null) {
                riskSubFactorAttributeExisting = riskSubFactorAttributeRepository.getOne(riskSubFactorAttribute.getId());

                //If Entity Not Found, Return Null
                if (riskSubFactorAttributeExisting == null) return null;
                else {
                    // Save Entity
                    riskSubFactorAttributeExisting.setDescription(riskSubFactorAttribute.getDescription());
                    riskSubFactorAttributeExisting.setRiskSubFactorScore(riskSubFactorAttribute.getRiskSubFactorScore());
                    riskSubFactorAttributeExisting.setWeightage(riskSubFactorAttribute.getWeightage());
                    riskSubFactorAttributeRepository.save(riskSubFactorAttributeExisting);
                    return riskSubFactorAttributeExisting;
                }
            }

        } catch (Exception ex) {


        }

        return null;
    }

    @Override
    public RiskSubFactorAttribute getById(long id) {
        Optional<RiskSubFactorAttribute> riskSubFactorAttribute = riskSubFactorAttributeRepository.findById(id);

        return riskSubFactorAttribute.get();
    }
}
