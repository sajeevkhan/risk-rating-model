package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.domain.ProjectRiskLevel;
import com.pfs.riskmodel.domain.RiskProjectType;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.repository.ProjectRiskLevelRepository;
import com.pfs.riskmodel.repository.RiskProjectTypeRepository;
import com.pfs.riskmodel.repository.RiskModelTemplateRepository;
import com.pfs.riskmodel.repository.RiskTypeRepository;
import com.pfs.riskmodel.service.IRiskModelTemplateService;
import com.pfs.riskmodel.service.IRiskTypeService;
import com.pfs.riskmodel.service.validator.RiskModelTemplateValidator;
import com.pfs.riskmodel.util.ValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RiskModelTemplateService implements IRiskModelTemplateService {

    @Autowired
    RiskModelTemplateRepository riskModelTemplateRepository;

    @Autowired
    RiskModelTemplateValidator riskModelTemplateValidator;

    @Autowired
    IRiskTypeService iRiskTypeService;

    @Autowired
    RiskTypeRepository riskTypeRepository;

    @Autowired
    RiskProjectTypeRepository riskProjectTypeRepository;

    @Autowired
    ProjectRiskLevelRepository projectRiskLevelRepository;

    @Override
    public RiskModelTemplate getByRiskModelId(Long id) {
       Optional<RiskModelTemplate> riskModelTemplateOptional = riskModelTemplateRepository.findById(id);
       return riskModelTemplateOptional.get();

    }

    @Override
    public Map<String, Object> findByProjectTypeAndRiskLevel(String projectTypeCode, String projectRiskLevelCode) {

        Map<String, Object> result = new HashMap<>();
        ValidationResult validationResult = new ValidationResult();




        RiskProjectType riskProjectType =  riskProjectTypeRepository.findByCode(projectTypeCode);
        if (riskProjectType == null  ) {
            validationResult.setAttributeName("ProjectType.Code");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            validationResult.setNotFound(true);
            result.put("ValidationResult", validationResult);
            return  result;
        }

        ProjectRiskLevel projectRiskLevel = projectRiskLevelRepository.findByCode(projectRiskLevelCode);
        if (projectRiskLevel == null) {
            validationResult.setAttributeName("ProjectType.RiskLevel");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            validationResult.setNotFound(true);
            result.put("ValidationResult", validationResult);
            return  result;
        }

        String status = "X";
        // Find Risk Model Tempalates - modelType = 1 ONLY
        List<RiskModelTemplate>  riskModelTemplates =
                riskModelTemplateRepository.findByRiskProjectTypeAndProjectRiskLevelAndModelTypeAndStatus(riskProjectType,
                                                                                          projectRiskLevel, 0,
                                                                                          status);

        if ( riskModelTemplates.size() > 1 ){
            validationResult.setAttributeName("RiskModelTemplate");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            validationResult.setMultipleValueFoundError(true);
            result.put("ValidationResult", validationResult);
            return  result;
        }

        if (riskModelTemplates.size() == 1) {
            validationResult.setAttributeName("RiskModelTemplate");
            validationResult.setValue(null);
            validationResult.setFailed(false);
            result.put("ValidationResult", validationResult);
            result.put("RiskModelTemplate", riskModelTemplates.get(0));
            return result;
        }

        if (riskModelTemplates.size() == 0) {
            validationResult.setAttributeName("RiskModelTemplate");
            validationResult.setValue(null);
            validationResult.setFailed(true);
            validationResult.setNotFound(true);
            validationResult.setObject("RiskModelTemplate");
            result.put("ValidationResult", validationResult);
            return  result;
        }


        return null;
    }


    @Override
    public Map<String, Object> createRiskModelTemplate(RiskModelTemplate riskModelTemplate) {
        Map<String, Object> result = new HashMap<>();

        ValidationResult validationResult =  riskModelTemplateValidator.validate(riskModelTemplate);
        result.put("ValidationResult", validationResult);
        if (validationResult.isFailed()) {
            return result;
        }

        riskModelTemplate =  riskModelTemplateRepository.save(riskModelTemplate);
        result.put("RiskModelTemplate", riskModelTemplate);

        Long createdRiskModelTemplateId = riskModelTemplate.getId();

        //On Creating New Entities, Mark all other entities as Inactive


        List<RiskModelTemplate> riskModelTemplatesActive =
                riskModelTemplateRepository.findByRiskProjectTypeAndProjectRiskLevelAndModelTypeAndStatus(
                        riskModelTemplate.getRiskProjectType(),
                        riskModelTemplate.getProjectRiskLevel(),
                        0, //Find Template Models only
                        "X");


        for (RiskModelTemplate riskModelTemplateActive : riskModelTemplatesActive ) {
            if ( riskModelTemplateActive.getId() != createdRiskModelTemplateId ) {
                riskModelTemplateActive.setStatus(" ");
                riskModelTemplateRepository.save(riskModelTemplateActive);
            }
            }

        return result;
    }

    @Override
    public Map<String, Object> updateRiskModelTemplate(RiskModelTemplate riskModelTemplate) {

        //List of RiskType Ids for Deletion
        List<Long> idsForDeletion = new ArrayList<>();

        Map<String, Object> result = new HashMap<>();

        ValidationResult validationResult =  riskModelTemplateValidator.validate(riskModelTemplate);
        if (validationResult.isFailed()) {
            result.put("ValidationResult", validationResult);
            return result;
        }

        // Get Existing Risk Model Template
        RiskModelTemplate riskModelTemplateExisting;
        riskModelTemplateExisting = riskModelTemplateRepository.getOne(riskModelTemplate.getId());

        if (riskModelTemplateExisting == null || riskModelTemplateExisting.getId() == null){
            validationResult.setNotFound(true);
            result.put("ValidationResult", validationResult);
            return null;
        }

        //Update Header Attribtues
        riskModelTemplateExisting.setStatus(riskModelTemplate.getStatus());
        riskModelTemplateExisting.setDescription(riskModelTemplate.getDescription());
        riskModelTemplateExisting.setRiskProjectType(riskModelTemplate.getRiskProjectType());
        riskModelTemplateExisting.setProjectRiskLevel(riskModelTemplate.getProjectRiskLevel());
        riskModelTemplateExisting.setComputingMethod(riskModelTemplate.getComputingMethod());
        riskModelTemplateExisting.setScore(riskModelTemplate.getScore());
        riskModelTemplateExisting.setModelCategory(riskModelTemplate.getModelCategory());

        // Update RiskType Items
        for (RiskType riskType: riskModelTemplate.getRiskTypes()) {

            Boolean itemFound = false;

            //New Item
            if (riskType.getId() == null) {
                riskModelTemplateExisting.addRiskType(riskType);
            }

            // Modified Items
            for (RiskType riskTypeExisting : riskModelTemplate.getRiskTypes()) {

                if ( riskTypeExisting.getId() == riskType.getId() ) {
                    itemFound = true;
                    riskTypeExisting.setDescription(riskType.getDescription());
                    riskTypeExisting.setScore(riskType.getScore());

                    result =  iRiskTypeService.updateRiskType(riskTypeExisting);

                    ValidationResult validationResultRiskType  = (ValidationResult) result.get("ValidationResult");
                    if (validationResultRiskType.isFailed() == true) {
                        result.put("ValidationResult", validationResult);
                        return result;
                    }
                }
            }

            if (itemFound = false)
                idsForDeletion.add(riskType.getId());

        }
        //Update Risk Model Template
        riskModelTemplate = riskModelTemplateRepository.save(riskModelTemplateExisting);


        // Delete the Item entries
        for (Long id: idsForDeletion) {
            riskTypeRepository.deleteById(id);
        }

        result.put("ValidationResult", validationResult);
        result.put("RiskModelTemplate", riskModelTemplateExisting);

        return result;

    }

}
