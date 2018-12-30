package com.pfs.riskmodel.businessconfig;

import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final ModelCategoryRepository modelCategoryRepository;

    private final RatingModifierComputingMethodRepository riskRatingComputingMethodRepository;

    @Override
    public void run(String... strings) throws Exception {



        if(modelCategoryRepository.count() == 0) {
            ModelCategory  c1 = new ModelCategory(1, "Renewable-Build");
            ModelCategory  c2 = new ModelCategory( 2,"Renewable-Operational");
            ModelCategory  c3 = new ModelCategory(  3,"InfraTransmission-Build");
            ModelCategory  c4 = new ModelCategory( 4,"InfraTransmission-Operational");
            ModelCategory  c5 = new ModelCategory( 5,"InfraRoadProjectHybridAnnuity-Build");
            ModelCategory  c6 = new ModelCategory( 6,"InfraRoadProjectHybridAnnuity-Operational");
            ModelCategory  c7 = new ModelCategory( 7,"InfraRoadProjectToll-Build");
            ModelCategory  c8 = new ModelCategory( 8,"InfraRoadProjectToll-Operational");
            ModelCategory  c9 = new ModelCategory( 9,"HoldingCompany-Build");
            ModelCategory  c10 = new ModelCategory( 10,"HoldingCompany-Operational");


            modelCategoryRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10));
            log.info("-------------------------- Added Model Category  data");
        }



        if(computingMethodRepository.count() == 0) {
            ComputingMethod  c1 = new ComputingMethod(null,"01", "Weighted");
            ComputingMethod  c2 = new ComputingMethod(null,"02", "Sum");
            ComputingMethod  c3 = new ComputingMethod(null,"03", "Minimum");
            ComputingMethod  c4 = new ComputingMethod(null,"04", "Maximum");
            ComputingMethod  c5 = new ComputingMethod(null,"05", "Equals");
            ComputingMethod  c6 = new ComputingMethod(null,"06", "Multiply");


            computingMethodRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6));
            log.info("-------------------------- Added Computing Methods data");
        }

        if (scoreTypeRepository.count() == 0) {
            ScoreType s1 = new ScoreType(null,"01","Normal");
            ScoreType s2 = new ScoreType(null,"02","Deflator");
            ScoreType s3 = new ScoreType(null,"03","Multiplier");

            scoreTypeRepository.saveAll(Arrays.asList( s1,s2,s3));
            log.info("-------------------------- Added Score Types data");


        }


        if(projectTypeRepository.count() == 0) {
            ProjectType  p1 = new ProjectType(null,"01", "Renewable Project");
            ProjectType  p2 = new ProjectType(null,"02", "Infrastructure Transmission Project");
            ProjectType  p3 = new ProjectType(null,"03", "Infrastructure Road Project – Hybrid Annuity");
            ProjectType  p4 = new ProjectType(null,"04", "Infrastrucutre Road Project - Toll ");
            ProjectType  p5 = new ProjectType(null,"05", "Holding Company");



            projectTypeRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
            log.info("-------------------------- Added project Type   data");
        }


        if(projectRiskLevelRepository.count() == 0) {
            ProjectRiskLevel p1 = new ProjectRiskLevel(null,"01", "Build Phase ");
            ProjectRiskLevel  p2 = new ProjectRiskLevel(null,"02", "Operational Risk Phase");



            projectRiskLevelRepository.saveAll(Arrays.asList(p1,p2 ));
            log.info("-------------------------- Added project Risk Level (Phases) data");
        }


        if(riskRatingComputingMethodRepository.count() == 0) {

            //Modifiers to cap final ratings at sub-investment grade
            RatingModifierComputationMethod  m1 = new RatingModifierComputationMethod(null,"01", "On Select Any One - Notch Down to Sub Investment Grade");
            // Modifiers having impact on final ratings up to 2 notches
            RatingModifierComputationMethod m2 = new RatingModifierComputationMethod(null,"02", "Notch Down By Selection- OneorTwoBYOne, MoreThanThree By Two");



            riskRatingComputingMethodRepository.saveAll(Arrays.asList(m1,m2 ));
            log.info("-------------------------- Added Risk Rating Modifier Computing Method data");
        }



    }
}
