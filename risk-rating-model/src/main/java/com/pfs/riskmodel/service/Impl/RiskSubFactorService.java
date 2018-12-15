package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.domain.RiskSubFactor;
import com.pfs.riskmodel.repository.RiskSubFactorRepository;
import com.pfs.riskmodel.service.IRiskSubFactorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sajeev on 15-Dec-18.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RiskSubFactorService implements IRiskSubFactorService {

    @Autowired
    RiskSubFactorRepository riskSubFactorRepository;

    @Override
    public RiskSubFactor createRiskSubFactor(RiskSubFactor riskSubFactor) {

        riskSubFactor =  riskSubFactorRepository.save(riskSubFactor);

        return riskSubFactor;
    }
}
