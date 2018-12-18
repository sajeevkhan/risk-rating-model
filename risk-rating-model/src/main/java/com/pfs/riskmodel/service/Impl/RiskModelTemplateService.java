package com.pfs.riskmodel.service.Impl;

import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<RiskModelTemplate> riskModelTemplatesActive = riskModelTemplateRepository.findByStatus("X");
        for (RiskModelTemplate riskModelTemplateActive : riskModelTemplatesActive ) {
            if ( riskModelTemplateActive.getId() != createdRiskModelTemplateId ) {
                riskModelTemplateActive.setStatus("");
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
        riskModelTemplateExisting.setProjectType(riskModelTemplate.getProjectType());
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
