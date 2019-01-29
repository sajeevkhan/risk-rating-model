package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
 import com.pfs.riskmodel.domain.RiskPurpose;
 import com.pfs.riskmodel.repository.RiskPurposeRepository;
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
public class PurposeController {


    @Autowired
    RiskPurposeRepository purposeRepository;



    @GetMapping("/purposes")
    public ResponseEntity findAll (){

        List<RiskPurpose> purposes = new ArrayList<>();
        purposes= purposeRepository.findAll();
        return ResponseEntity.ok(purposes);
    }


}
