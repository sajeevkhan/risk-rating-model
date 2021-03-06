package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

/**
 * Created by sajeev on 01-Jan-19.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class RiskModelPDFBuilder {


    public ByteArrayOutputStream buildPdfDocument(RiskModelTemplate riskModelTemplate) throws Exception {

        Document doc = new Document(PageSize.A4,36, 36, 70, 80);


        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        PdfWriter writer = PdfWriter.getInstance(doc,stream);


        PDFFooter event = new PDFFooter( riskModelTemplate.getProjectName(),
                riskModelTemplate.getLoanAmountInCrores().toString(),
                riskModelTemplate.getRatingDate(),
                riskModelTemplate.getRiskProjectType().getValue(),
                riskModelTemplate.getProjectRiskLevel().getValue());

        writer.setPageEvent(event);

        doc.open();

        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));


        // Header Table with Loan Details
        RiskModelPDFHeaderTable riskModelPDFHeaderTable = new RiskModelPDFHeaderTable();
        doc = riskModelPDFHeaderTable.buildHeader(doc, riskModelTemplate);

        // Rating Overview Table
        RiskModelPDFHeaderRatingOverviewTable riskModelPDFHeaderRatingOverviewTable = new RiskModelPDFHeaderRatingOverviewTable();
        doc = riskModelPDFHeaderRatingOverviewTable.buildHeaderRatingTable(doc, riskModelTemplate);

        // Risk Component Scores
        RiskModelPDFRiskTypeComponentTable riskModelPDFRiskTypeComponentOverviewTable = new RiskModelPDFRiskTypeComponentTable();
        doc = riskModelPDFRiskTypeComponentOverviewTable.buildRiskTypeComponentOverview(doc, riskModelTemplate);


        Font parafont = new Font(Font.FontFamily.HELVETICA );
        parafont.setSize(14);
        parafont.setStyle(Font.BOLD);
        parafont.setStyle(Font.UNDERLINE);
        parafont.setColor(BaseColor.BLUE.darker().darker().darker().darker());

        // Details
        for (RiskType riskType: riskModelTemplate.getRiskTypes()){

            // Risk Type Desc. BOLD AND UNDERLINED
            Phrase riskTypeDesc = new Phrase(riskType.getDescription(),parafont);
            Paragraph riskTypePara = new Paragraph();
            riskTypePara.setAlignment(Element.ALIGN_CENTER);

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph( riskTypeDesc.getContent().toString(),parafont));
            doc.add(new Paragraph(" "));

            // Component, Factors, Sub Factors and Attribtues
            for (RiskComponent riskComponent: riskType.getRiskComponents()){

                RiskModelPDFComponentTable riskModelPDFComponentTable = new RiskModelPDFComponentTable();
                riskModelPDFComponentTable.buildRiskComponentTable(doc,riskModelTemplate,riskComponent);
            }
        }


        // Rating Modifiers
        RiskModelPDFRiskRatingModifiersTable riskModelPDFRiskRatingModifiersTable = new RiskModelPDFRiskRatingModifiersTable();
        doc = riskModelPDFRiskRatingModifiersTable.buildRatingModifiers(doc, riskModelTemplate);

        // Parental Notchup
        RiskModelPDFRiskParentalNotchupTable riskModelPDFRiskParentalNotchupTable = new RiskModelPDFRiskParentalNotchupTable();
        doc = riskModelPDFRiskParentalNotchupTable.buildParentalNotchup(doc,riskModelTemplate);

        doc.close();

        return stream;

    }



}
