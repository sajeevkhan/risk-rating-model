package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.ProjectRiskLevel;
import com.pfs.riskmodel.repository.ProjectRiskLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class RiskLevelController {


    @Autowired
    ProjectRiskLevelRepository projectRiskLevelRepository;



    @GetMapping("/projectRiskLevels")
    public ResponseEntity findAll (){

        List<ProjectRiskLevel> riskLevels = new ArrayList<>();
        riskLevels= projectRiskLevelRepository.findAll();
        return ResponseEntity.ok(riskLevels);
    }


}
