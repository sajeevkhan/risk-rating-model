package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.pdfservice.RiskModelPDFBuilder;
import com.pfs.riskmodel.repository.RiskModelTemplateRepository;
import com.pfs.riskmodel.util.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class RiskModelPDFController {


    @Autowired
    RiskModelTemplateRepository riskModelTemplateRepository;

    /**
     * Handle request to download a RISK MODEL Evaluation as a PDF Document
     */

    @GetMapping("/riskModelPDF")
    public ModelAndView getRiskModelAsPDF (
            @RequestParam(value = "id",required = true) Long id,
            HttpServletRequest request){


        Check.notNull(id, "Exception.IdNullFoRead",
                "Risk Model PDF Document", " ");

        RiskModelTemplate riskModelTemplate = new RiskModelTemplate();

        try {


            Optional<RiskModelTemplate> riskModelTemplateOptional = riskModelTemplateRepository.findById(id);
             riskModelTemplate = riskModelTemplateOptional.get();
        }
        catch ( Exception ex) {
            System.out.println(ex);
        }


        Check.notNull(riskModelTemplate.getId(), "Exception.notFound",
                "RiskComponent", id.toString());



        return new ModelAndView("RiskModelPDFBuilder", "RiskModelTemplate",riskModelTemplate);



    }


    @GetMapping("/riskModelPDFDebugMode")
    public ModelAndView getRiskModelAsPDFDebugMode (
            @RequestParam(value = "id",required = true) Long id,
            HttpServletRequest request){


        Check.notNull(id, "Exception.IdNullFoRead",
                "Risk Model PDF Document", " ");

        RiskModelTemplate riskModelTemplate = new RiskModelTemplate();

        try {


            Optional<RiskModelTemplate> riskModelTemplateOptional = riskModelTemplateRepository.findById(id);
            riskModelTemplate = riskModelTemplateOptional.get();
        }
        catch ( Exception ex) {
            System.out.println(ex);
        }


        Check.notNull(riskModelTemplate.getId(), "Exception.notFound",
                "RiskModel", id.toString());



        return new ModelAndView("RiskModelPDFBuilderDebugMode", "RiskModelTemplate",riskModelTemplate);



    }



    @GetMapping("/testPDF")
    public ModelAndView getTestPDF (
            @RequestParam(value = "id",required = false) Integer id,
            HttpServletRequest request){


        return new ModelAndView("TestPDF", "Month",id);



    }

}
