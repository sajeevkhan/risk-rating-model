package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskFactorDTO;
import com.pfs.riskmodel.dto.RiskModelTemplateDTO;
import com.pfs.riskmodel.dto.RiskTypeDTO;
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


    @PostMapping("/riskModelTemplate")
    public ResponseEntity create(@RequestBody RiskModelTemplateDTO riskModelTemplateDTO, HttpServletRequest request) {

        RiskModelTemplate riskModelTemplate = mapDTOToDomain(riskModelTemplateDTO);

        Map<String, Object> result = riskModelTemplateService.createRiskModelTemplate(riskModelTemplate);
        CheckServiceResult.checkResult(result);


        riskModelTemplate = (RiskModelTemplate) result.get("RiskModelTemplate");
        RiskModelTemplateDTO riskModelTemplateDTOResponse = mapDomainToDTO(riskModelTemplate);

        return ResponseEntity.ok(riskModelTemplateDTO);
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

        //MAP Domain to DTO
     private RiskModelTemplateDTO mapDomainToDTO (RiskModelTemplate riskModelTemplate) {


        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();

        DozerBeanMapper mapper = new DozerBeanMapper();
        riskModelTemplateDTO = mapper.map(riskModelTemplate, RiskModelTemplateDTO.class);

        String computingMethodCode =  riskModelTemplate.getComputingMethod().getCode();
        riskModelTemplateDTO.setComputingMethodCode(computingMethodCode);

        String projectTypeCode = riskModelTemplate.getProjectType().getCode();
        riskModelTemplateDTO.setProjectTypeCode(projectTypeCode);

        String projectRiskLevelCode = riskModelTemplate.getProjectRiskLevel().getCode();
        riskModelTemplateDTO.setProjectRiskLevelCode(projectRiskLevelCode);


         for (RiskTypeDTO riskTypeDTO: riskModelTemplateDTO.getRiskTypes() ) {

             for (RiskComponentDTO riskComponentDTO : riskTypeDTO.getRiskComponents()) {
                  RiskComponent riskComponent = riskComponentRepository.getOne(riskComponentDTO.getId());
                  riskComponentDTO.setComputingMethodCode(  riskComponent.getComputingMethod().getCode());
                  riskComponentDTO.setScoreTypeCode(riskComponent.getScoreType().getCode());

                  for (RiskFactorDTO riskFactorDTO: riskComponentDTO.getRiskFactors()) {
                      riskFactorDTO.setComputingMethodCode(riskFactorRepository.getOne(riskFactorDTO.getId()).getComputingMethod().getCode());
                      riskFactorDTO.setScoreTypeCode(riskFactorRepository.getOne(riskFactorDTO.getId()).getScoreType().getCode());
                  }

             }

        }

        return riskModelTemplateDTO;

    }


    //Map DTO to Domain
    private  RiskModelTemplate mapDTOToDomain(RiskModelTemplateDTO riskModelTemplateDTO) {

        RiskModelTemplate riskModelTemplate = new RiskModelTemplate();

        for (RiskTypeDTO riskTypeDTO: riskModelTemplateDTO.getRiskTypes()) {

            for (RiskComponentDTO riskComponentDTO : riskTypeDTO.getRiskComponents()) {
                riskComponentDTO.setComputingMethod(computingMethodRepository.findByCode(riskComponentDTO.getScoreTypeCode()));
                riskComponentDTO.setScoreType(scoreTypeRepository.findByCode(riskComponentDTO.getScoreTypeCode()));

                for (RiskFactorDTO riskFactorDTO: riskComponentDTO.getRiskFactors()) {

                    ComputingMethod computingMethod = computingMethodRepository.findByCode(riskFactorDTO.getComputingMethodCode());
                    ScoreType scoreType = scoreTypeRepository.findByCode(riskFactorDTO.getScoreTypeCode());

                    riskFactorDTO.setComputingMethod(computingMethod);
                    riskFactorDTO.setScoreType(scoreType);
                }

            }

        }


        DozerBeanMapper mapper = new DozerBeanMapper();
        riskModelTemplate = mapper.map(riskModelTemplateDTO, RiskModelTemplate.class);

//        ComputingMethod computingMethod = computingMethodRepository.findByCode(riskModelTemplateDTO.getComputingMethodCode());
//        ScoreType scoreType = scoreTypeRepository.findByCode(riskFactorDTO.getScoreTypeCode());

        riskModelTemplate.setComputingMethod(computingMethodRepository.findByCode(riskModelTemplateDTO.getComputingMethodCode()));
        riskModelTemplate.setProjectRiskLevel(projectRiskLevelRepository.findByCode(riskModelTemplateDTO.getProjectRiskLevelCode()));
        riskModelTemplate.setProjectType(projectTypeRepository.findByCode(riskModelTemplateDTO.getProjectTypeCode()));

        return riskModelTemplate;
    }





}
