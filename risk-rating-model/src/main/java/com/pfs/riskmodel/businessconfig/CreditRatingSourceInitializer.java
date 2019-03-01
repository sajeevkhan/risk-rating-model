package com.pfs.riskmodel.businessconfig;

import com.pfs.riskmodel.domain.CreditRatingMap;
import com.pfs.riskmodel.domain.CreditRatingSource;
import com.pfs.riskmodel.repository.CreditRatingMapRepository;
import com.pfs.riskmodel.repository.CreditRatingSourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Component
@RequiredArgsConstructor



public class CreditRatingSourceInitializer implements CommandLineRunner{

    //@Autowired
    //private CreditRatingMapRepository creditRatingMapRepository;

    @Autowired
    private CreditRatingSourceRepository creditRatingSourceRepository;


    @Override
    public void run(String... strings) throws Exception {


        if(creditRatingSourceRepository.count() == 0) {
            CreditRatingSource c1 = new CreditRatingSource(null, "CRISIL", "CRISIL");
            CreditRatingSource c2 = new CreditRatingSource(null, "ICRA", "ICRA");
            CreditRatingSource c3 = new CreditRatingSource(null, "CARE", "CARE");
            CreditRatingSource c4 = new CreditRatingSource(null, "BWR", "BWR");
            CreditRatingSource c9 = new CreditRatingSource(null, "SMERA", "SMERA");
            CreditRatingSource c5 = new CreditRatingSource(null, "S&P", "S&P");
            CreditRatingSource c6 = new CreditRatingSource(null, "Moodys", "Moodys");
            CreditRatingSource c7 = new CreditRatingSource(null, "Fitch", "Fitch");
            CreditRatingSource c8 = new CreditRatingSource(null, "PFS", "PFS");

            creditRatingSourceRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9));

            log.info("-------------------------- Added Credit Rating Data for : " + c1.getCode());

        }

    }










}
