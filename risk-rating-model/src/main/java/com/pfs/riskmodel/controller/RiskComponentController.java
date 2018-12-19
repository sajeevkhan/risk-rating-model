package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.domain.RiskSubFactor;
import com.pfs.riskmodel.dto.RiskComponentDTO;
import com.pfs.riskmodel.dto.RiskFactorDTO;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;
import com.pfs.riskmodel.repository.*;
import com.pfs.riskmodel.service.IRiskComponentService;
 import com.pfs.riskmodel.util.Check;
import com.pfs.riskmodel.util.CheckServiceResult;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class RiskComponentController {


    @Autowired
    RiskComponentRepository riskComponentRepository;



    @Autowired
    RiskSubFactorRepository riskSubFactorRepository;

    @Autowired
    RiskFactorRepository riskFactorRepository;


    @Autowired
    ComputingMethodRepository computingMethodRepository;

    @Autowired
    ScoreTypeRepository scoreTypeRepository;


    @Autowired
    IRiskComponentService riskComponentService;


    @GetMapping("/riskComponent/all")
    public ResponseEntity findAll (){


        List<RiskComponentDTO> riskComponentDTOS = new ArrayList<>();


        List<RiskComponent> riskComponents = new ArrayList<>();

        riskComponents = riskComponentRepository.findAll();

        riskComponents.forEach(riskComponent -> {
            RiskComponentDTO riskComponentDTO = mapDomainToDTO(riskComponent);
            riskComponentDTOS.add(riskComponentDTO);
        });

        return ResponseEntity.ok(riskComponentDTOS);
    }


    @GetMapping("/riskComponent/id/{id}")
    public ResponseEntity findOne (
                             @PathVariable("id") Long id,
                             HttpServletRequest request) {


        RiskComponent riskComponent = new RiskComponent();
        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();

        riskComponent = riskComponentRepository.getOne(id);

        Check.notNull(riskComponent.getId(), "Exception.notFound",
                "RiskComponent", id.toString());


        riskComponentDTO =  mapDomainToDTO(riskComponent);


        return ResponseEntity.ok(riskComponentDTO);
    }


    @PostMapping("/riskComponent")
    public ResponseEntity create(@RequestBody RiskComponentDTO riskComponentDTO, HttpServletRequest request) {

        RiskComponent riskComponent = mapDTOToDomain(riskComponentDTO);

        Map<String, Object> result = riskComponentService.createRiskComponent(riskComponent);
        CheckServiceResult.checkResult(result);


        riskComponent = (RiskComponent) result.get("RiskComponent");
        RiskComponentDTO riskComponentDTOResponse =mapDomainToDTO(riskComponent);

        return ResponseEntity.ok(riskComponentDTOResponse);
    }


    @PutMapping("/riskComponent")
    public ResponseEntity update(@RequestBody RiskComponentDTO riskComponentDTO,
                                 HttpServletRequest request) {

        RiskComponent riskComponent  = mapDTOToDomain(riskComponentDTO);

        Map<String, Object> result = riskComponentService.updateRiskComponent(riskComponent);
        CheckServiceResult.checkResult(result);

        riskComponent = (RiskComponent) result.get("RiskComponent");

        Check.notNull(riskComponent.getId(), "Exception.IdNull",
                "RiskComponent");

        RiskComponentDTO riskComponentDTOResponse =mapDomainToDTO(riskComponent);
        return ResponseEntity.ok(riskComponentDTOResponse);


    }

        //MAP Domain to DTO
     private RiskComponentDTO mapDomainToDTO (RiskComponent riskComponent) {


        RiskComponentDTO riskComponentDTO = new RiskComponentDTO();

        DozerBeanMapper mapper = new DozerBeanMapper();
        riskComponentDTO = mapper.map(riskComponent, RiskComponentDTO.class);


        riskComponentDTO.setComputingMethodCode(riskComponent.getComputingMethod().getCode());
        riskComponentDTO.setScoreTypeCode(riskComponent.getScoreType().getCode());

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


        return riskComponentDTO;

    }


    //Map DTO to Domain
    private  RiskComponent mapDTOToDomain(RiskComponentDTO riskComponentDTO) {

        RiskComponent riskComponent = new RiskComponent();

        DozerBeanMapper mapper = new DozerBeanMapper();
        riskComponent = mapper.map(riskComponentDTO, RiskComponent.class);

        riskComponent.setScoreType(scoreTypeRepository.findByCode(riskComponentDTO.getScoreTypeCode()));
        riskComponent.setComputingMethod(computingMethodRepository.findByCode(riskComponentDTO.getComputingMethodCode()));


        for (RiskFactor riskFactor: riskComponent.getRiskFactors()) {

            riskFactor.setComputingMethod(computingMethodRepository.findByCode(riskFactor.getComputingMethodCode()));
            riskFactor.setComputingMethodCode(computingMethodRepository.findByCode(riskFactor.getComputingMethodCode()).getCode());

            riskFactor.setScoreType(scoreTypeRepository.findByCode(riskFactor.getScoreTypeCode()));
            riskFactor.setScoreTypeCode(scoreTypeRepository.findByCode(riskFactor.getScoreTypeCode()).getCode());

            for (RiskSubFactor riskSubFactor: riskFactor.getRiskSubFactors()) {

                riskSubFactor.setScoreType(scoreTypeRepository.findByCode(riskSubFactor.getScoreTypeCode()));
                riskSubFactor.setScoreTypeCode(scoreTypeRepository.findByCode(riskSubFactor.getScoreTypeCode()).getCode());


            }


        }

        return riskComponent;
    }





}
