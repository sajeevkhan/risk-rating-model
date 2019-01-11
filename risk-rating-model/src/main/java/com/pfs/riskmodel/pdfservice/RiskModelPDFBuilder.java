package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;
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
public class RiskModelPDFBuilder extends AbstractITextPdfView  {


    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        RiskModelTemplate riskModelTemplate = (RiskModelTemplate) model.get("RiskModelTemplate");

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

    }



}