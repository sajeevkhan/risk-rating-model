package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import com.pfs.riskmodel.repository.RiskSubFactorAttributeRepository;
import com.pfs.riskmodel.resource.RiskSubFactorAttributeResource;
import com.pfs.riskmodel.service.IRiskSubFactorAttributeService;
import com.pfs.riskmodel.util.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * Created by sajeev on 06-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class RiskSubFactorAttributeController {


    @Autowired
    private RiskSubFactorAttributeRepository riskSubFactorAttributeRepository;

    @Autowired
    private IRiskSubFactorAttributeService riskSubFactorAttributeService;

    @GetMapping("/riskSubFactorAttribute/all")
    public List<RiskSubFactorAttribute> getRiskSubFactor (){

        return riskSubFactorAttributeRepository.findAll();


    }

    @GetMapping("/riskSubFactorAttribute")
    public RiskSubFactorAttribute getRiskSubFactorSingle (@RequestParam(value = "id",required = false) Long id) {

       // UUID riskSubFactorAttributeId = UUID.fromString(id);

        return riskSubFactorAttributeRepository.getOne(id);


    }

    @GetMapping("/riskSubFactorAttribute/id/{id}")
    public ResponseEntity getRiskSubFactorSingleById (@PathVariable("id") Long id) {

       RiskSubFactorAttribute riskSubFactorAttribute = new RiskSubFactorAttribute();


       riskSubFactorAttribute =  riskSubFactorAttributeService.getById(id);

        Check.notNull(riskSubFactorAttribute.getId(), "Exception.notFound",
                "RiskSubFactorAttribute", id.toString());

        RiskSubFactorAttributeResource riskSubFactorAttributeResource = new RiskSubFactorAttributeResource();
        riskSubFactorAttributeResource.setRiskSubFactorAttribute(riskSubFactorAttribute);

           return ResponseEntity.ok( riskSubFactorAttributeResource);
    }


    @PutMapping("/riskSubFactorAttribute/{id}")
    public ResponseEntity update(@PathVariable("id") String loanApplicationId,
                                 @RequestBody RiskSubFactorAttribute riskSubFactorAttribute,
                                 HttpServletRequest request) {
       RiskSubFactorAttribute riskSubFactorAttributeUpdated =   riskSubFactorAttributeService.update(riskSubFactorAttribute);


        Check.notNull(riskSubFactorAttributeUpdated, "Exception.notFound",
                "RiskSubFactorAttribute",
                riskSubFactorAttribute.getId().toString());

        return  ResponseEntity.ok(riskSubFactorAttribute);
    }

    @PostMapping("/riskSubFactorAttribute")
    public ResponseEntity createRiskSubFactorAttribute(@RequestBody RiskSubFactorAttribute riskSubFactorAttribute) {

        // Create the RiskSubFactorAttribute.

        riskSubFactorAttribute = riskSubFactorAttributeService.createRiskSubFactorAttribute(riskSubFactorAttribute);
        if ( riskSubFactorAttribute == null || riskSubFactorAttribute.getId() == null ) {
            Check.notNull(null, "Exception.CreateFailed",
                    "RiskSubFactorAttribute" );

        }

        return ResponseEntity.ok(riskSubFactorAttribute);
    }
}
