package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by sajeev on 01-Jan-19.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class RiskModelPDFBuilder extends AbstractITextPdfView  {


    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        RiskModelTemplate riskModelTemplate = (RiskModelTemplate) model.get("RiskModelTemplate");

//        doc.add(new Paragraph("Recommended books for Spring framework"));
//
//           Path path = Paths.get(ClassLoader.getSystemResource("images/pfs-logo.jpg").toURI());
//            Image img = Image.getInstance(path.toAbsolutePath().toString());
//            img.setAbsolutePosition(50,50);

        //doc.add(img);

        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));


        RiskModelPDFHeader riskModelPDFHeader = new RiskModelPDFHeader();
        doc = riskModelPDFHeader.buildHeader(doc, riskModelTemplate);

        RiskModelPDFHeaderRating riskModelPDFHeaderRating = new RiskModelPDFHeaderRating();
        doc = riskModelPDFHeaderRating.buildHeaderRatingTable(doc, riskModelTemplate);


//        PdfPTable table = new PdfPTable(1);
//        table.setWidthPercentage(1.0f);
//        table.setWidths(new float[] {3.0f});
//        table.setSpacingBefore(10);
//
//        doc.add(table);


    }



}
