package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.RiskSubFactor;
import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import com.pfs.riskmodel.dto.RiskSubFactorDTO;
import com.pfs.riskmodel.repository.RiskSubFactorRepository;
import com.pfs.riskmodel.resource.RiskSubFactorResource;
import com.pfs.riskmodel.service.IRiskSubFactorService;
import com.pfs.riskmodel.service.Impl.RiskSubFactorService;
import com.pfs.riskmodel.util.Check;
import com.pfs.riskmodel.util.CheckServiceResult;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class RiskSubFactorController {


    @Autowired
    RiskSubFactorRepository riskSubFactorRepository;


    @Autowired
    IRiskSubFactorService riskSubFactorService;

    @GetMapping("/riskSubFactor/all")
    public ResponseEntity findAll (){

        List<RiskSubFactorDTO> resources = new ArrayList<>(0);

        List<RiskSubFactorDTO> riskSubFactorDTOList = new ArrayList<>();


        List<RiskSubFactor> riskSubFactorList = new ArrayList<>();
        riskSubFactorList = riskSubFactorRepository.findAll();

        riskSubFactorList.forEach(riskSubFactor -> {
            RiskSubFactorDTO riskSubFactorDTO = mapDomainToDTO(riskSubFactor);
            riskSubFactorDTOList.add(riskSubFactorDTO);
        });

        return ResponseEntity.ok(riskSubFactorDTOList);
    }


    @GetMapping("/riskSubFactor/id/{id}")
    public ResponseEntity findOne (
                             @PathVariable("id") Long id,
                             HttpServletRequest request) {


        RiskSubFactor riskSubFactor = new RiskSubFactor();
        RiskSubFactorDTO riskSubFactorDTO = new RiskSubFactorDTO();
        try{
        Optional<RiskSubFactor> riskSubFactorOptional = riskSubFactorRepository.findById(id);
           riskSubFactor = riskSubFactorOptional.get();

        }
        catch (Exception ex) {
            Check.notNull(null, "Exception.JAVA",
                    "RiskSubFactor:" +id.toString(), ex.getMessage());

       }

        Check.notNull(riskSubFactor.getId(), "Exception.notFound",
                "RiskSubFactor", id.toString());



        riskSubFactorDTO =  mapDomainToDTO(riskSubFactor);


        return ResponseEntity.ok(riskSubFactorDTO);
    }




    @PostMapping("/riskSubFactor")
    public ResponseEntity create(@RequestBody RiskSubFactorDTO riskSubFactorDTO, HttpServletRequest request) {

        RiskSubFactor riskSubFactor = mapDTOToDomain(riskSubFactorDTO);

        Map<String, Object> result = riskSubFactorService.createRiskSubFactor(riskSubFactor);
        CheckServiceResult.checkResult(result);


        riskSubFactor = (RiskSubFactor) result.get("RiskSubFactor");
        RiskSubFactorDTO riskSubFactorDTOResponse =mapDomainToDTO(riskSubFactor);

        return ResponseEntity.ok(riskSubFactorDTOResponse);
    }


    @PutMapping("/riskSubFactor")
    public ResponseEntity update(@RequestBody RiskSubFactorDTO riskSubFactorDTO,
                                 HttpServletRequest request) {

        RiskSubFactor riskSubFactor = mapDTOToDomain(riskSubFactorDTO);

        Map<String, Object> result = riskSubFactorService.updateRiskSubFactor(riskSubFactor);
        CheckServiceResult.checkResult(result);

        // Call the update resource
        riskSubFactor = (RiskSubFactor) result.get("RiskSubFactor");

        Check.notNull(riskSubFactorDTO.getId(), "Exception.IdNull",
                "RiskSubFactor");

        RiskSubFactorDTO riskSubFactorDTOResponse =mapDomainToDTO(riskSubFactor);
        return ResponseEntity.ok(riskSubFactorDTOResponse);


    }

        //MAP Domain to DTO
     private RiskSubFactorDTO mapDomainToDTO (RiskSubFactor riskSubFactor) {


        RiskSubFactorDTO riskSubFactorDTO = new RiskSubFactorDTO();

        DozerBeanMapper mapper = new DozerBeanMapper();
        riskSubFactorDTO = mapper.map(riskSubFactor, RiskSubFactorDTO.class);

        return riskSubFactorDTO;

    }


    //Map DTO to Domain
    private  RiskSubFactor mapDTOToDomain(RiskSubFactorDTO riskSubFactorDTO) {

          RiskSubFactor riskSubFactor = new RiskSubFactor();

        DozerBeanMapper mapper = new DozerBeanMapper();
        riskSubFactor = mapper.map(riskSubFactorDTO, RiskSubFactor.class);

//       for(RiskSubFactorAttribute riskSubFactorAttribute : riskSubFactor.getRiskSubFactorAttribute()) {
//           riskSubFactorAttribute.setRiskSubFactor(riskSubFactor);
//        }


         return riskSubFactor;
    }





}
