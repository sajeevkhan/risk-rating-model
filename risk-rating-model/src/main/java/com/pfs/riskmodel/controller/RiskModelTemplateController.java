package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.dto.*;
import com.pfs.riskmodel.repository.*;
import com.pfs.riskmodel.service.IRiskModelService;
import com.pfs.riskmodel.service.IRiskModelTemplateService;
import com.pfs.riskmodel.util.Check;
import com.pfs.riskmodel.util.CheckServiceResult;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
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
    RatingModifierComputingMethodRepository ratingModifierComputingMethodRepository;

    @Autowired
    ProjectRiskLevelRepository projectRiskLevelRepository;

    @Autowired
    RiskProjectTypeRepository riskProjectTypeRepository;

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

    @Autowired
    WorkflowStatusRepository workflowStatusRepository;

    @Autowired
    RiskPurposeRepository purposeRepository;

    @Autowired
    IRiskModelService riskModelService;


    //-----------------------------------------------------------------------------------------------------------------
    //                              RISK MODEL - VALUATIONS
    //      action 1 - Save only
    //      action 2 - Save and Send for Approval
    //      action 3 - Save and Approve
    //      action 4 - Save and Reject
    //-----------------------------------------------------------------------------------------------------------------

    @PostMapping("/riskModel")
    public ResponseEntity createRiskModel(@RequestBody RiskModelTemplateDTO riskModelTemplateDTO,
                                          @RequestParam(value = "action",required = true) Integer action,
                                          HttpServletRequest request) {

        RiskModelTemplate riskModelTemplate = mapDTOToDomain(riskModelTemplateDTO);


        Map<String, Object> result = riskModelService.createRiskModel(riskModelTemplate,action, request);
        CheckServiceResult.checkResult(result);


        riskModelTemplate = (RiskModelTemplate) result.get("RiskModel");
        RiskModelTemplateDTO riskModelTemplateDTOResponse = mapDomainToDTO(riskModelTemplate);

        return ResponseEntity.ok(riskModelTemplateDTOResponse);
    }


    @PutMapping("/riskModel")
    public ResponseEntity updateRiskModel(@RequestBody RiskModelTemplateDTO riskModelTemplateDTO,
                                          @RequestParam(value = "action",required = true) Integer action,
                                          HttpServletRequest request) {

        RiskModelTemplate riskModelTemplate = mapDTOToDomain(riskModelTemplateDTO);

        Map<String, Object> result = riskModelService.createRiskModel(riskModelTemplate,action, request);
        CheckServiceResult.checkResult(result);

        riskModelTemplate = (RiskModelTemplate) result.get("RiskModel");
        RiskModelTemplateDTO riskModelTemplateDTOResponse = mapDomainToDTO(riskModelTemplate);

        return ResponseEntity.ok(riskModelTemplateDTOResponse);
    }


    @PutMapping("/riskModel/process")
    public ResponseEntity updateRiskModel(@RequestParam(value = "id",required = true) Long id,
                                          @RequestParam(value = "action",required = true) Integer action,
                                          HttpServletRequest request) {

        RiskModelTemplate riskModelTemplate= (RiskModelTemplate) riskModelTemplateRepository.findById(id).get();

        Map<String, Object> result = riskModelService.createRiskModel(riskModelTemplate,action, request);
        CheckServiceResult.checkResult(result);

        riskModelTemplate = (RiskModelTemplate) result.get("RiskModel");
        RiskModelTemplateDTO riskModelTemplateDTOResponse = mapDomainToDTO(riskModelTemplate);

        return ResponseEntity.ok(riskModelTemplateDTOResponse);
    }








    //-----------------------------------------------------------------------------------------------------------------
    //                              RISK MODEL - TEMPLATES
    //-----------------------------------------------------------------------------------------------------------------

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


    @GetMapping("/riskModel/loanNumber/{loanNumber}")
    public ResponseEntity findByLoanNumber (@PathVariable("loanNumber") String loanNumber,
                                            HttpServletRequest request){


        List<RiskModelTemplateDTO> riskModelTemplateDTOS = new ArrayList<>();
        List<RiskModelTemplate> riskModelTemplates = new ArrayList<>();

        riskModelTemplates = riskModelTemplateRepository.findByLoanNumber(loanNumber);

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

        System.out.println("LOCALDATE --------- : " +LocalDate.now());


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

        riskModelTemplateDTO =  mapRiskTemplateDomainToDTO(riskModelTemplateDTO);
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





    //-----------------------------------------------------------------------------------------------------------------
    //                              DTO-> DOMAIN -> DTO Mappers
    //-----------------------------------------------------------------------------------------------------------------



    /*
       MAP Domain to DTO   */
     private RiskModelTemplateDTO mapDomainToDTO (RiskModelTemplate riskModelTemplate) {


        RiskModelTemplateDTO riskModelTemplateDTO = new RiskModelTemplateDTO();

        // Sort Risk Model Template
        riskModelTemplate = sortRiskModelTemplate(riskModelTemplate);

        DozerBeanMapper mapper = new DozerBeanMapper();
        riskModelTemplateDTO = mapper.map(riskModelTemplate, RiskModelTemplateDTO.class);

        riskModelTemplateDTO.setPurposeCode(riskModelTemplate.getPurpose().getCode());
        riskModelTemplateDTO.setPurposeDescription(riskModelTemplate.getPurpose().getDescription());

        riskModelTemplateDTO.setWorkflowStatusCode(riskModelTemplate.getWorkflowStatus().getCode());
        riskModelTemplateDTO.setWorkflowStatusDescription(riskModelTemplate.getWorkflowStatus().getDescription());

        riskModelTemplateDTO.setModelCategoryCode(riskModelTemplate.getModelCategory().getCode());
        riskModelTemplateDTO.setModelCategoryDescription(riskModelTemplate.getModelCategory().getValue());

        String computingMethodCode =  riskModelTemplate.getComputingMethod().getCode();
        riskModelTemplateDTO.setComputingMethodCode(computingMethodCode);
        riskModelTemplateDTO.setComputingMethodDescription(riskModelTemplate.getComputingMethod().getValue());

        String projectTypeCode = riskModelTemplate.getRiskProjectType().getCode();
        riskModelTemplateDTO.setRiskProjectTypeCode(projectTypeCode);
        riskModelTemplateDTO.setRiskProjectTypeDescription(riskModelTemplate.getRiskProjectType().getValue());


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

        riskModelTemplate.setPurpose(purposeRepository.findByCode(riskModelTemplateDTO.getPurposeCode()));
        riskModelTemplate.setWorkflowStatus(workflowStatusRepository.findByCode(riskModelTemplateDTO.getPurposeCode()));


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
                            System.out.println(" ");
                        }

                        if (riskSubFactor.getRiskSubFactorAttributes() == null){

                            System.out.println(" NULL Risk Sub Factor Attributes:" + riskSubFactor.getDescription());
                        }

                        for (RiskSubFactorAttribute riskSubFactorAttribute: riskSubFactor.getRiskSubFactorAttributes() ){

                            if (riskSubFactorAttribute.getWeightage() == null){
                                System.out.println(" NULL Risk Sub Factor Attribute Weightage:" + riskSubFactorAttribute.getDescription());
                            }
                        }
                    }
                }


                // Rating Modifiers
                if (riskModelTemplate.getRiskRatingModifiers() != null ) {
                    for (RiskRatingModifier riskRatingModifier : riskModelTemplate.getRiskRatingModifiers()) {
                        riskRatingModifier.setComputingMethod(ratingModifierComputingMethodRepository.findByCode(riskRatingModifier.getComputingMethodCode()));
                    }
                }

                // Parental Notchup
                if (riskModelTemplate.getRiskParentalNotchUps() != null) {
                    for (RiskParentalNotchUp riskParentalNotchUp : riskModelTemplate.getRiskParentalNotchUps()) {

                        for (RiskSubFactor riskSubFactor : riskParentalNotchUp.getRiskSubFactors()) {
                            riskSubFactor.setScoreType(scoreTypeRepository.findByCode(riskSubFactor.getScoreTypeCode()));
                        }
                    }
                }


            }
        }

        riskModelTemplate.setModelCategory(modelCategoryRepository.findByCode(riskModelTemplateDTO.getModelCategoryCode()));
        riskModelTemplate.setComputingMethod(computingMethodRepository.findByCode(riskModelTemplateDTO.getComputingMethodCode()));
        riskModelTemplate.setProjectRiskLevel(projectRiskLevelRepository.findByCode(riskModelTemplateDTO.getProjectRiskLevelCode()));
        riskModelTemplate.setRiskProjectType(riskProjectTypeRepository.findByCode(riskModelTemplateDTO.getRiskProjectTypeCode()));

        riskModelTemplate = sortRiskModelTemplate(riskModelTemplate);

        return riskModelTemplate;
    }




    private RiskModelTemplate sortRiskModelTemplate (RiskModelTemplate riskModelTemplate) {


        for (RiskType riskType: riskModelTemplate.getRiskTypes()) {

            for (RiskComponent riskComponent: riskType.getRiskComponents()) {
                for (RiskFactor riskFactor: riskComponent.getRiskFactors()) {
                    for (RiskSubFactor riskSubFactor: riskFactor.getRiskSubFactors()) {

                        // Sort RiskSubFactorAttributes
                        List<RiskSubFactorAttribute> riskSubFactorAttributes = riskSubFactor.getRiskSubFactorAttributes();
                        Collections.sort(riskSubFactorAttributes,
                                                (a, b) -> a.getItemNo().compareTo(b.getItemNo()));
                        riskSubFactor.setRiskSubFactorAttributes(riskSubFactorAttributes);
                    }

                    // Sort RiskSubFactors
                    List<RiskSubFactor> riskSubFactors = riskFactor.getRiskSubFactors();
                    Collections.sort(riskSubFactors,
                            (a, b) -> a.getItemNo().compareTo(b.getItemNo()));
                    riskFactor.setRiskSubFactors(riskSubFactors);
                }

                //Sort RiskFactors
                List<RiskFactor> riskFactors = riskComponent.getRiskFactors();
                Collections.sort(riskFactors,
                        (a, b) -> a.getItemNo().compareTo(b.getItemNo()));

                riskComponent.setRiskFactors(riskFactors);
            }

            //Sort RiskComponents
            List<RiskComponent> riskComponents = riskType.getRiskComponents();
            Collections.sort(riskComponents,
                    (a, b) -> a.getItemNo().compareTo(b.getItemNo()));
            riskType.setRiskComponents(riskComponents);
        }

        //Sort Risk Types
        List<RiskType> riskTypes = riskModelTemplate.getRiskTypes();
        Collections.sort(riskTypes,
                (a, b) -> a.getItemNo().compareTo(b.getItemNo()));
        riskModelTemplate.setRiskTypes(riskTypes);

        return riskModelTemplate;
    }




    /*
   MAP Domain to DTO   */
    private RiskModelTemplateDTO mapRiskTemplateDomainToDTO (RiskModelTemplateDTO riskModelTemplateDTO) {


        riskModelTemplateDTO.setId(null);

        Integer iRiskTypeItemNo = 0;
        for (RiskTypeDTO riskTypeDTO: riskModelTemplateDTO.getRiskTypes() ) {

            riskTypeDTO.setItemNo(iRiskTypeItemNo++);
            riskTypeDTO.setId(null);

            Integer iRiskComponentItemNo = 0;
            for (RiskComponentDTO riskComponentDTO : riskTypeDTO.getRiskComponents()) {
                riskComponentDTO.setItemNo(iRiskComponentItemNo++);
                riskComponentDTO.setId(null);

                Integer iRiskFactorItemNo = 0;
                for (RiskFactorDTO riskFactorDTO: riskComponentDTO.getRiskFactors()) {
                    riskFactorDTO.setId(null);
                    riskFactorDTO.setItemNo(iRiskFactorItemNo++);

                    Integer iRiskSubFactorItemNo = 0;
                    for (RiskSubFactorDTO riskSubFactorDTO: riskFactorDTO.getRiskSubFactors()) {
                        riskFactorDTO.setItemNo(iRiskSubFactorItemNo++);
                        riskSubFactorDTO.setId(null);

                        Integer iRiskSubFactorAttrItemNo = 0;
                        for (RiskSubFactorAttributeDTO riskSubFactorAttributeDTO: riskSubFactorDTO.getRiskSubFactorAttributes()) {
                            riskSubFactorAttributeDTO.setItemNo(iRiskSubFactorAttrItemNo++);
                            riskSubFactorAttributeDTO.setId(null);



                        }
                     }
                }
            }

        }

        for (RiskParentalNotchUpDTO riskParentalNotchUpDTO: riskModelTemplateDTO.getRiskParentalNotchUps()) {
            riskParentalNotchUpDTO.setId(null);

            Integer iRiskParentalNotcuUpConditionItemNo = 0;
            for (RiskParentalNotchUpConditionDTO riskParentalNotchUpConditionDTO
                                    : riskParentalNotchUpDTO.getRiskParentalConditions()) {
                riskParentalNotchUpConditionDTO.setItemNo(iRiskParentalNotcuUpConditionItemNo++);
                riskParentalNotchUpConditionDTO.setId(null);
            }


            Integer iRiskParentalNotcuUpSubFactorItemNo = 0;
            for (RiskSubFactorDTO riskSubFactorDTO: riskParentalNotchUpDTO.getRiskSubFactors()) {
                riskSubFactorDTO.setId(null);
                riskSubFactorDTO.setItemNo(iRiskParentalNotcuUpSubFactorItemNo++);

                Integer iRiskParentalNotcuUpSubFactorAttrItemNo = 0;
                for(RiskSubFactorAttributeDTO riskSubFactorAttributeDTO: riskSubFactorDTO.getRiskSubFactorAttributes()) {
                    riskSubFactorAttributeDTO.setItemNo(iRiskParentalNotcuUpSubFactorAttrItemNo++);
                    riskSubFactorAttributeDTO.setId(null);
                }
            }
        }


        Integer iRiskRatingModifierItemNo = 0;
        for (RiskRatingModifierDTO riskRatingModifierDTO: riskModelTemplateDTO.getRiskRatingModifiers()) {
            riskRatingModifierDTO.setItemNo(iRiskRatingModifierItemNo++);
            riskRatingModifierDTO.setId(null);

            Integer iRiskRatingModifierAttrItemNo = 0;
            for (RiskRatingModifierAttributeDTO riskRatingModifierAttributeDTO: riskRatingModifierDTO.getRiskRatingModifierAttributes()) {
                riskRatingModifierAttributeDTO.setId(null);
                riskRatingModifierAttributeDTO.setItemNo(iRiskRatingModifierAttrItemNo);

            }

        }

        return riskModelTemplateDTO;

    }


}
