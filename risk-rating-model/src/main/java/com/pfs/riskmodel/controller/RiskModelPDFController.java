package com.pfs.riskmodel.controller;

import com.pfs.riskmodel.config.ApiController;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.pdfservice.RiskModelPDFBuilder;
import com.pfs.riskmodel.pdfservice.RiskModelPDFBuilderDebugMode;
import com.pfs.riskmodel.repository.RiskModelTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;

/**
 * Created by sajeev on 15-Dec-18.
 */
@ApiController
@RequiredArgsConstructor
public class RiskModelPDFController {


    private final RiskModelTemplateRepository riskModelTemplateRepository;

    private final RiskModelPDFBuilder riskModelPDFBuilder;

    private final RiskModelPDFBuilderDebugMode riskModelPDFBuilderDebugMode;

    /**
     * Handle request to download a RISK MODEL Evaluation as a PDF Document
     */

    @GetMapping(value = "/riskModelPDF")
    public ResponseEntity getPdf(@RequestParam(value = "id", required = true) Long id) throws Exception {
        RiskModelTemplate riskModelTemplate = riskModelTemplateRepository.getOne(id);
        ByteArrayOutputStream stream = riskModelPDFBuilder.buildPdfDocument(riskModelTemplate);
        return streamToResponseEntity(stream);
    }


    @GetMapping(value = "/riskModelPDFDebugMode")
    public ResponseEntity getRiskModelAsPDFDebugMode(@RequestParam(value = "id", required = true) Long id) throws Exception {
        RiskModelTemplate riskModelTemplate = riskModelTemplateRepository.getOne(id);
        ByteArrayOutputStream stream = riskModelPDFBuilderDebugMode.buildPdfDocument(riskModelTemplate);
        return streamToResponseEntity(stream);
    }

    public ResponseEntity streamToResponseEntity(ByteArrayOutputStream stream){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setContentType(MediaType.APPLICATION_PDF);
        responseHeader.add("Expires", "0");
        responseHeader.add("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        responseHeader.add("Pragma", "no-cache");
        responseHeader.add("Content-disposition", "inline; filename=test.pdf");
        return new ResponseEntity(stream.toByteArray(), responseHeader, HttpStatus.OK);
    }
}