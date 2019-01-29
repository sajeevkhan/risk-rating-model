package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.RiskProjectType;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.dto.RiskTypeDTO;
import com.pfs.riskmodel.repository.RiskTypeRepository;
import com.pfs.riskmodel.service.IRiskTypeService;
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
public class RiskTypeController {


    @Autowired
    RiskTypeRepository riskTypeRepository;


    @Autowired
    IRiskTypeService riskTypeService;




    @GetMapping("/riskType/all")
    public ResponseEntity findAll (){


        List<RiskTypeDTO> riskTypeDTOS = new ArrayList<>();


        List<RiskType> riskTypes = new ArrayList<>();

        riskTypes = riskTypeRepository.findAll();

        riskTypes.forEach(riskType -> {
            RiskTypeDTO riskTypeDTO = mapDomainToDTO(riskType);
            riskTypeDTOS.add(riskTypeDTO);
        });

        return ResponseEntity.ok(riskTypeDTOS);
    }


    @GetMapping("/riskType/id/{id}")
    public ResponseEntity findOne (
                             @PathVariable("id") Long id,
                             HttpServletRequest request) {


        RiskType riskType = new RiskType();
        RiskTypeDTO riskTypeDTO = new RiskTypeDTO();

        riskType = riskTypeRepository.getOne(id);

        Check.notNull(riskType.getId(), "Exception.notFound",
                "RiskType", id.toString());


        riskTypeDTO =  mapDomainToDTO(riskType);


        return ResponseEntity.ok(riskTypeDTO);
    }


    @PostMapping("/riskType")
    public ResponseEntity create(@RequestBody RiskTypeDTO riskTypeDTO, HttpServletRequest request) {

        RiskType riskType = mapDTOToDomain(riskTypeDTO);

        Map<String, Object> result = riskTypeService.createRiskType(riskType);
        CheckServiceResult.checkResult(result);


        riskType = (RiskType) result.get("RiskType");
        RiskTypeDTO riskTypeDTOResponse = mapDomainToDTO(riskType);

        return ResponseEntity.ok(riskTypeDTO);
    }


    @PutMapping("/riskType")
    public ResponseEntity update(@RequestBody RiskTypeDTO riskTypeDTO,
                                 HttpServletRequest request) {

        RiskType riskType  = mapDTOToDomain(riskTypeDTO);

        Map<String, Object> result = riskTypeService.updateRiskType(riskType);
        CheckServiceResult.checkResult(result);

        riskType = (RiskType) result.get("RiskType");

        Check.notNull(riskType.getId(), "Exception.IdNull",
                "RiskType");

        RiskTypeDTO riskTypeDTOResponse =mapDomainToDTO(riskType);
        return ResponseEntity.ok(riskTypeDTOResponse);


    }

        //MAP Domain to DTO
     private RiskTypeDTO mapDomainToDTO (RiskType riskType) {


        RiskTypeDTO riskTypeDTO = new RiskTypeDTO();

        DozerBeanMapper mapper = new DozerBeanMapper();
        riskTypeDTO = mapper.map(riskType, RiskTypeDTO.class);

        return riskTypeDTO;

    }


    //Map DTO to Domain
    private  RiskType mapDTOToDomain(RiskTypeDTO riskTypeDTO) {

        RiskType riskType = new RiskType();

        DozerBeanMapper mapper = new DozerBeanMapper();
        riskType = mapper.map(riskTypeDTO, RiskType.class);

        return riskType;
    }





}
