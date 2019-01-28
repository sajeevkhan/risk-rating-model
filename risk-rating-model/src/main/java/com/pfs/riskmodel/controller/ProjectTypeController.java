package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.ProjectType;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.dto.RiskTypeDTO;
import com.pfs.riskmodel.repository.ProjectTypeRepository;
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
public class ProjectTypeController {


    @Autowired
    ProjectTypeRepository projectTypeRepository;



    @GetMapping("/projectTypes")
    public ResponseEntity findAll (){

        List<ProjectType> projectTypes = new ArrayList<>();
        projectTypes= projectTypeRepository.findAll();
        return ResponseEntity.ok(projectTypes);
    }


}
