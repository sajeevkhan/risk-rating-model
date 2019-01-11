package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.pdfservice.Temp.DetailsTable;
import com.pfs.riskmodel.pdfservice.Temp.HeaderTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by sajeev on 01-Jan-19.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class TestPDF extends AbstractITextTestPdfView  {


    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        //RiskModelTemplate riskModelTemplate = (RiskModelTemplate) model.get("RiskModelTemplate");


        doc.add(new Paragraph(" "));

        Integer month = (Integer) model.get("Month");



        DetailsTable detailsTable = new DetailsTable();
        doc = detailsTable.buildDetails(doc, month);


    }



}
