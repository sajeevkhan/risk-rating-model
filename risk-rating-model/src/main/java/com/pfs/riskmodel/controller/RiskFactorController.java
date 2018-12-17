package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.RiskFactor;
import com.pfs.riskmodel.domain.RiskSubFactor;
import com.pfs.riskmodel.dto.RiskFactorDTO;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;
import com.pfs.riskmodel.repository.ComputingMethodRepository;
import com.pfs.riskmodel.repository.RiskFactorRepository;
 import com.pfs.riskmodel.repository.ScoreTypeRepository;
import com.pfs.riskmodel.service.IRiskFactorService;
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
public class RiskFactorController {


    @Autowired
    RiskFactorRepository riskFactorRepository;

    @Autowired
    ComputingMethodRepository computingMethodRepository;

    @Autowired
    ScoreTypeRepository scoreTypeRepository;


    @Autowired
    IRiskFactorService riskFactorService;

    @GetMapping("/riskFactor/all")
    public ResponseEntity findAll (){


        List<RiskFactorDTO> riskFactorDTOS = new ArrayList<>();


        List<RiskFactor> riskFactorList = new ArrayList<>();

        riskFactorList = riskFactorRepository.findAll();

        riskFactorList.forEach(riskFactor -> {
            RiskFactorDTO riskFactorDTO = mapDomainToDTO(riskFactor);
            riskFactorDTOS.add(riskFactorDTO);
        });

        return ResponseEntity.ok(riskFactorDTOS);
    }


    @GetMapping("/riskFactor/id/{id}")
    public ResponseEntity findOne (
                             @PathVariable("id") Long id,
                             HttpServletRequest request) {


        RiskFactor riskFactor = new RiskFactor();
        RiskFactorDTO riskFactorDTO = new RiskFactorDTO();

        riskFactor = riskFactorRepository.getOne(id);


        riskFactorDTO =  mapDomainToDTO(riskFactor);
        Check.notNull(riskFactorDTO.getId(), "Exception.notFound",
                "RiskFactor", id.toString());


        return ResponseEntity.ok(riskFactorDTO);
    }


    @PostMapping("/riskFactor")
    public ResponseEntity create(@RequestBody RiskFactorDTO riskFactorDTO, HttpServletRequest request) {

        RiskFactor riskFactor = mapDTOToDomain(riskFactorDTO);

        Map<String, Object> result = riskFactorService.createRiskFactor(riskFactor);
        CheckServiceResult.checkResult(result);


        riskFactor = (RiskFactor) result.get("RiskFactor");
        RiskFactorDTO riskFactorDTOResponse =mapDomainToDTO(riskFactor);

        return ResponseEntity.ok(riskFactorDTOResponse);
    }


    @PutMapping("/riskFactor")
    public ResponseEntity update(@RequestBody RiskFactorDTO riskFactorDTO,
                                 HttpServletRequest request) {

        RiskFactor riskFactor = mapDTOToDomain(riskFactorDTO);

        Map<String, Object> result = riskFactorService.updateRiskFactor(riskFactor);
        CheckServiceResult.checkResult(result);

         riskFactor = (RiskFactor) result.get("RiskFactor");

        Check.notNull(riskFactor.getId(), "Exception.IdNull",
                "RiskFactor");

        RiskFactorDTO riskFactorDTOResponse =mapDomainToDTO(riskFactor);
        return ResponseEntity.ok(riskFactorDTOResponse);


    }

        //MAP Domain to DTO
     private RiskFactorDTO mapDomainToDTO (RiskFactor riskFactor) {


        RiskFactorDTO riskFactorDTO = new RiskFactorDTO();

        DozerBeanMapper mapper = new DozerBeanMapper();
        riskFactorDTO = mapper.map(riskFactor, RiskFactorDTO.class);

//        for (RiskSubFactor riskSubFactor: riskFactor.getRiskSubFactors()) {
//            RiskSubFactorDTO riskSubFactorDTO =  mapper.map(riskFactor, RiskSubFactorDTO.class);
//            riskFactorDTO.addRiskSubFactorDTO(riskSubFactorDTO);
//        }


        riskFactorDTO.setComputingMethodCode(riskFactor.getComputingMethod().getCode());
        riskFactorDTO.setScoreTypeCode(riskFactor.getScoreType().getCode());

        return riskFactorDTO;

    }


    //Map DTO to Domain
    private  RiskFactor mapDTOToDomain(RiskFactorDTO riskFactorDTO) {

        RiskFactor riskFactor = new RiskFactor();

        DozerBeanMapper mapper = new DozerBeanMapper();
        riskFactor = mapper.map(riskFactorDTO, RiskFactor.class);

        riskFactor.setScoreType(scoreTypeRepository.findByCode(riskFactorDTO.getScoreTypeCode()));
        riskFactor.setComputingMethod(computingMethodRepository.findByCode(riskFactorDTO.getComputingMethodCode()));

        return riskFactor;
    }





}
