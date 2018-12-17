package com.pfs.riskmodel.appconfig;

import com.pfs.riskmodel.domain.ComputingMethod;
import com.pfs.riskmodel.domain.ProjectRiskLevel;
import com.pfs.riskmodel.domain.ProjectType;
import com.pfs.riskmodel.domain.ScoreType;
import com.pfs.riskmodel.repository.ComputingMethodRepository;
import com.pfs.riskmodel.repository.ProjectRiskLevelRepository;
import com.pfs.riskmodel.repository.ProjectTypeRepository;
import com.pfs.riskmodel.repository.ScoreTypeRepository;
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



public class Initializer implements CommandLineRunner{

    private final ComputingMethodRepository computingMethodRepository;

    private final ScoreTypeRepository scoreTypeRepository;

    private final ProjectTypeRepository projectTypeRepository;

    private final ProjectRiskLevelRepository projectRiskLevelRepository;

    @Override
    public void run(String... strings) throws Exception {

        if(computingMethodRepository.count() == 0) {
            ComputingMethod  c1 = new ComputingMethod("01", "Weighted");
            ComputingMethod  c2 = new ComputingMethod("02", "Sum");
            ComputingMethod  c3 = new ComputingMethod("03", "Minimum");
            ComputingMethod  c4 = new ComputingMethod("04", "Maximum");
            ComputingMethod  c5 = new ComputingMethod("05", "Equals");
            ComputingMethod  c6 = new ComputingMethod("06", "Multiply");


            computingMethodRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6));
            log.info("-------------------------- Added Computing Methods data");
        }

        if (scoreTypeRepository.count() == 0) {
            ScoreType s1 = new ScoreType("01","Normal");
            ScoreType s2 = new ScoreType("02","Deflator");
            ScoreType s3 = new ScoreType("03","Multiplier");

            scoreTypeRepository.saveAll(Arrays.asList( s1,s2,s3));
            log.info("-------------------------- Added Score Types data");


        }


        if(projectTypeRepository.count() == 0) {
            ProjectType  p1 = new ProjectType("01", "Renewable Project");
            ProjectType  p2 = new ProjectType("02", "Infrastructure Transmission Project");
            ProjectType  p3 = new ProjectType("03", "Infrastructure Road Project – Hybrid Annuity");
            ProjectType  p4 = new ProjectType("04", "Infrastrucutre Road Project - Toll ");
            ProjectType  p5 = new ProjectType("05", "Holding Company");



            projectTypeRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
            log.info("-------------------------- Added project Type   data");
        }


        if(projectRiskLevelRepository.count() == 0) {
            ProjectRiskLevel p1 = new ProjectRiskLevel("01", "Build Phase ");
            ProjectRiskLevel  p2 = new ProjectRiskLevel("02", "Operational Risk Phase");



            projectRiskLevelRepository.saveAll(Arrays.asList(p1,p2 ));
            log.info("-------------------------- Added project Risk Level (Phases) data");
        }


    }
}
