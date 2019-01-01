package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.pdfservice.RiskModelPDFBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class RiskModelPDFController {



    /**
     * Handle request to download a RISK MODEL Evaluation as a PDF Document
     */

    @GetMapping("/riskModelPDF")
    public ModelAndView getRiskModelAsPDF (
            @RequestParam(value = "id",required = false) Long id,
            HttpServletRequest request){




        return new ModelAndView("RiskModelPDFBuilder", "id",1L);



    }

}
