package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.dto.*;
import com.pfs.riskmodel.repository.*;
import com.pfs.riskmodel.service.IRiskModelTemplateService;
import com.pfs.riskmodel.service.IRiskTypeService;
import com.pfs.riskmodel.util.Check;
import com.pfs.riskmodel.util.CheckServiceResult;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class RiskModelTemplateController {


    @Autowired
    RiskModelTemplateRepository riskModelTemplateRepository;

    @Autowired
    RiskTypeRepository riskTypeRepository;

    @Autowired
    RiskComponentRepository riskComponentRepository;

    @Autowired
    ComputingMethodRepository computingMethodRepository;

    @Autowired
    ProjectRiskLevelRepository projectRiskLevelRepository;

    @Autowired
    ProjectTypeRepository projectTypeRepository;

    @Autowired
    ScoreTypeRepository scoreTypeRepository;

    @Autowired
    RiskFactorRepository riskFactorRepository;

    @Autowired
    RiskSubFactorRepository riskSubFactorRepository;

    @Autowired
    ModelCategoryRepository modelCategoryRepository;



    @Autowired
    IRiskModelTemplateService riskModelTemplateService;


    @GetMapping("/riskModelTemplate/all")
    public ResponseEntity findAll (){


        List<RiskModelTemplateDTO> riskModelTemplateDTOS = new ArrayList<>();
        List<RiskModelTemplate> riskModelTemplates = new ArrayList<>();

        riskModelTemplates = riskModelTemplateRepository.findAll();

        riskModelTemplates.forEach(riskModelTemplate -> {
            RiskModelTemplateDTO riskModelTemplateDTO = mapDomainToDTO(riskModelTemplate);
            riskModelTemplateDTOS.add(riskModelTemplateDTO);
        });

        return ResponseEntity.ok(riskModelTemplateDTOS);
    }


    @GetMapping("/riskModelTemplate/id/{id}")
    public ResponseEntity findOne (
                             @PathVariable("id") Long id,
                             HttpServletRequest request) {

        RiskModelTemplate riskModelTemplate = new RiskModelTemplate();
        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();

        riskModelTemplate = riskModelTemplateRepository.getOne(id);
        Check.notNull(riskModelTemplate.getId(), "Exception.notFound",
                "RiskModelTemplate", id.toString());

        riskModelTemplateDTO =  mapDomainToDTO(riskModelTemplate);
        return ResponseEntity.ok(riskModelTemplateDTO);
    }

    @GetMapping("/riskModelTemplate")
    public ResponseEntity findByProjectTypeAndRiskLevel (@RequestParam(value = "projectType",required = true) String projectType,
                                                         @RequestParam(value = "projectRiskLevel",required = true) String projectRiskLevel,
                                                         HttpServletRequest request) {

        RiskModelTemplate riskModelTemplate = new RiskModelTemplate();
        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();

        Map<String, Object> result = riskModelTemplateService.findByProjectTypeAndRiskLevel(projectType,projectRiskLevel);
        CheckServiceResult.checkResult(result);


        riskModelTemplate = (RiskModelTemplate) result.get("RiskModelTemplate");

        if (riskModelTemplate == null) {
            Check.notNull(riskModelTemplate.getId(), "Exception.notFound",
                    "RiskModelTemplate", projectType + ":" + projectRiskLevel);
        }else {
            Check.notNull(projectType, "Exception.notFound",
                    "RiskModelTemplate", projectType + ":" + projectRiskLevel);
        }

        riskModelTemplateDTO =  mapDomainToDTO(riskModelTemplate);
        return ResponseEntity.ok(riskModelTemplateDTO);
    }




    @PostMapping("/riskModelTemplate")
    public ResponseEntity create(@RequestBody RiskModelTemplateDTO riskModelTemplateDTO, HttpServletRequest request) {

        RiskModelTemplate riskModelTemplate = mapDTOToDomain(riskModelTemplateDTO);

        Map<String, Object> result = riskModelTemplateService.createRiskModelTemplate(riskModelTemplate);
        CheckServiceResult.checkResult(result);


        riskModelTemplate = (RiskModelTemplate) result.get("RiskModelTemplate");
        RiskModelTemplateDTO riskModelTemplateDTOResponse = mapDomainToDTO(riskModelTemplate);

        return ResponseEntity.ok(riskModelTemplateDTOResponse);
    }


    @PutMapping("/riskModelTemplate")
    public ResponseEntity update(@RequestBody RiskModelTemplateDTO riskModelTemplateDTO,
                                 HttpServletRequest request) {

        RiskModelTemplate riskModelTemplate  = mapDTOToDomain(riskModelTemplateDTO);

        Map<String, Object> result = riskModelTemplateService.updateRiskModelTemplate(riskModelTemplate);
        CheckServiceResult.checkResult(result);

        riskModelTemplate = (RiskModelTemplate) result.get("RiskModelTemplate");

        Check.notNull(riskModelTemplate.getId(), "Exception.IdNull",
                "RiskModelTemplate");

        RiskModelTemplateDTO riskModelTemplateDTOResponse = mapDomainToDTO(riskModelTemplate);
        return ResponseEntity.ok(riskModelTemplateDTOResponse);


    }

     /*
       MAP Domain to DTO

     */
     private RiskModelTemplateDTO mapDomainToDTO (RiskModelTemplate riskModelTemplate) {


        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();

        DozerBeanMapper mapper = new DozerBeanMapper();
        riskModelTemplateDTO = mapper.map(riskModelTemplate, RiskModelTemplateDTO.class);

         riskModelTemplateDTO.setModelCategoryCode(riskModelTemplate.getModelCategory().getCode());
         riskModelTemplateDTO.setModelCategoryDescription(riskModelTemplate.getModelCategory().getValue());

         String computingMethodCode =  riskModelTemplate.getComputingMethod().getCode();
        riskModelTemplateDTO.setComputingMethodCode(computingMethodCode);
        riskModelTemplateDTO.setComputingMethodDescription(riskModelTemplate.getComputingMethod().getValue());

        String projectTypeCode = riskModelTemplate.getProjectType().getCode();
        riskModelTemplateDTO.setProjectTypeCode(projectTypeCode);
        riskModelTemplateDTO.setProjectTypeDescription(riskModelTemplate.getProjectType().getValue());


        String projectRiskLevelCode = riskModelTemplate.getProjectRiskLevel().getCode();
        riskModelTemplateDTO.setProjectRiskLevelCode(projectRiskLevelCode);
        riskModelTemplateDTO.setProjectRiskLevelDescription(riskModelTemplate.getProjectRiskLevel().getValue());


         for (RiskTypeDTO riskTypeDTO: riskModelTemplateDTO.getRiskTypes() ) {

             for (RiskComponentDTO riskComponentDTO : riskTypeDTO.getRiskComponents()) {

                 RiskComponent riskComponent = riskComponentRepository.getOne(riskComponentDTO.getId());

                  riskComponentDTO.setComputingMethodCode(  riskComponent.getComputingMethod().getCode());
                  riskComponentDTO.setComputingMethodDescription(riskComponent.getComputingMethod().getValue());

                  riskComponentDTO.setScoreTypeCode(riskComponent.getScoreType().getCode());
                  riskComponentDTO.setScoreTypeDescription(riskComponent.getScoreType().getDescription());

                  for (RiskFactorDTO riskFactorDTO: riskComponentDTO.getRiskFactors()) {

                      riskFactorDTO.setComputingMethodCode(riskFactorRepository.getOne(riskFactorDTO.getId()).getComputingMethod().getCode());
                      riskFactorDTO.setComputingMethodDescription(riskFactorRepository.getOne(riskFactorDTO.getId()).getComputingMethod().getValue());

                      riskFactorDTO.setScoreTypeCode(riskFactorRepository.getOne(riskFactorDTO.getId()).getScoreType().getCode());
                      riskFactorDTO.setScoreTypeDescription(riskFactorRepository.getOne(riskFactorDTO.getId()).getScoreType().getDescription());

                      for (RiskSubFactorDTO riskSubFactorDTO: riskFactorDTO.getRiskSubFactors()) {
                          riskSubFactorDTO.setScoreTypeCode( riskSubFactorRepository.getOne(riskSubFactorDTO.getId()).getScoreTypeCode());
                          riskSubFactorDTO.setScoreTypeDescription( riskSubFactorRepository.getOne(riskSubFactorDTO.getId()).getScoreTypeDescription());

                      }

                  }

             }

        }

        return riskModelTemplateDTO;

    }

    /*
        Map DTO to Domain
     */

    private  RiskModelTemplate mapDTOToDomain(RiskModelTemplateDTO riskModelTemplateDTO) {

        RiskModelTemplate riskModelTemplate = new RiskModelTemplate();

         DozerBeanMapper mapper = new DozerBeanMapper();
        riskModelTemplate = mapper.map(riskModelTemplateDTO, RiskModelTemplate.class);

        for (RiskType riskType: riskModelTemplate.getRiskTypes()) {


            for (RiskComponent riskComponent: riskType.getRiskComponents()) {

                riskComponent.setComputingMethod(computingMethodRepository.findByCode(riskComponent.getComputingMethodCode()));
                riskComponent.setComputingMethodCode(computingMethodRepository.findByCode(riskComponent.getComputingMethodCode()).getCode());

                riskComponent.setScoreType(scoreTypeRepository.findByCode(riskComponent.getScoreTypeCode()));
                riskComponent.setScoreTypeCode(scoreTypeRepository.findByCode(riskComponent.getScoreTypeCode()).getCode());


                for (RiskFactor riskFactor: riskComponent.getRiskFactors()) {

                    riskFactor.setComputingMethod(computingMethodRepository.findByCode(riskFactor.getComputingMethodCode()));
                    riskFactor.setComputingMethodCode(computingMethodRepository.findByCode(riskFactor.getComputingMethodCode()).getCode());

                    riskFactor.setScoreType(scoreTypeRepository.findByCode(riskFactor.getScoreTypeCode()));
                    riskFactor.setScoreTypeCode(scoreTypeRepository.findByCode(riskFactor.getScoreTypeCode()).getCode());

                    for (RiskSubFactor riskSubFactor: riskFactor.getRiskSubFactors()) {


                        if (riskSubFactor.getWeightage() == null) {
                            System.out.println(" NULL Risk Sub Factor Weightage:" + riskSubFactor.getDescription());

                        }


                        riskSubFactor.setScoreType(scoreTypeRepository.findByCode(riskSubFactor.getScoreTypeCode()));
                        try {
                            riskSubFactor.setScoreTypeCode(scoreTypeRepository.findByCode(riskSubFactor.getScoreTypeCode()).getCode());
                        }
                        catch ( Exception ex) {
                            System.out.println("");
                        }

                        if (riskSubFactor.getRiskSubFactorAttributes() == null){

                            System.out.println(" NULL Risk Sub Factor Attributes:" + riskSubFactor.getDescription());
                        }

                        for (RiskSubFactorAttribute riskSubFactorAttribute: riskSubFactor.getRiskSubFactorAttributes() ){

                            if (riskSubFactorAttribute.getWeightage() == null){
                                System.out.println(" NULL Risk Sub Factor Attribute Weightage:" + riskSubFactorAttribute.getDescription());

//                                String description = riskSubFactorAttribute.getDescription();
//                                description.replace("/", "-");


                                //riskSubFactorAttribute.setDescription(description);

                            }
                            //riskSubFactorAttribute.set
                        }

                    }


                }
            }
        }

        riskModelTemplate.setModelCategory(modelCategoryRepository.findByCode(riskModelTemplateDTO.getModelCategoryCode()));

        riskModelTemplate.setComputingMethod(computingMethodRepository.findByCode(riskModelTemplateDTO.getComputingMethodCode()));
        riskModelTemplate.setProjectRiskLevel(projectRiskLevelRepository.findByCode(riskModelTemplateDTO.getProjectRiskLevelCode()));
        riskModelTemplate.setProjectType(projectTypeRepository.findByCode(riskModelTemplateDTO.getProjectTypeCode()));

        return riskModelTemplate;
    }





}
