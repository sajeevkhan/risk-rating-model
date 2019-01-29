package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.RiskProjectType;
import com.pfs.riskmodel.repository.RiskProjectTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class RiskProjectTypeController {


    @Autowired
    RiskProjectTypeRepository riskProjectTypeRepository;



    @GetMapping("/projectTypes")
    public ResponseEntity findAll (){

        List<RiskProjectType> riskProjectTypes = new ArrayList<>();
        riskProjectTypes = riskProjectTypeRepository.findAll();
        return ResponseEntity.ok(riskProjectTypes);
    }


}
