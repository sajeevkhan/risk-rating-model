package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.pfs.riskmodel.domain.RiskComponent;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;
import com.pfs.riskmodel.service.modelvaluator.Utils;

/**
 * Created by sajeev on 03-Jan-19.
 */
public class RiskModelPDFRiskTypeComponentTable {

    public Document buildRiskTypeComponentOverview(  Document doc, RiskModelTemplate riskModelTemplate) throws Exception {


        // Header Font
        Font headerfont = new Font(Font.FontFamily.HELVETICA );
        headerfont.setColor(BaseColor.WHITE);
        headerfont.setSize(8);

        // Value Font
        Font valueFont = new Font(Font.FontFamily.HELVETICA);
        valueFont.setColor(BaseColor.BLACK);
        valueFont.setSize(8);


        //Project Details Table
        float[] columnWidths = {10, 3, 3};
        PdfPTable projectDetailsTable = new PdfPTable(columnWidths);
        projectDetailsTable.setWidthPercentage(100.0f);

        projectDetailsTable.setSpacingBefore(10);

        for (RiskType riskType: riskModelTemplate.getRiskTypes()) {
            // First Row - Risk Type Description
            // First Column - Risk Type Description
            PdfPCell projectDetailsCell1 = new PdfPCell();
            projectDetailsCell1.setBackgroundColor(BaseColor.BLUE.darker().darker().darker().darker());
            projectDetailsCell1.setPhrase(new Phrase(riskType.getDescription(), headerfont));
            projectDetailsCell1.setColspan(3);
            projectDetailsCell1.setHorizontalAlignment(Element.ALIGN_CENTER);

            projectDetailsTable.addCell(projectDetailsCell1);
            projectDetailsTable.completeRow();



            // Second Row - Risk Component  Column Headings
            // First Column - Risk Component Label
            projectDetailsCell1 = new PdfPCell();
            projectDetailsCell1.setBackgroundColor(BaseColor.ORANGE);
            projectDetailsCell1.setPhrase(new Phrase("Risk Component", valueFont));

            // Second Column - Score Label
            PdfPCell projectDetailsCell2 = new PdfPCell();
            projectDetailsCell2.setBackgroundColor(BaseColor.ORANGE);
            projectDetailsCell2.setPhrase(new Phrase("Score", valueFont));
            projectDetailsCell2.setColspan(2);

            projectDetailsTable.addCell(projectDetailsCell1);
            projectDetailsTable.addCell(projectDetailsCell2);

            projectDetailsTable.completeRow();


            for (RiskComponent riskComponent: riskType.getRiskComponents()) {

                // Risk Component Scores
                // First Column - Risk Component Name
                projectDetailsCell1 = new PdfPCell();
                projectDetailsCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                projectDetailsCell1.setPhrase(new Phrase(riskComponent.getDescription(), valueFont));

                // Second Column - Riskc Component Score
                projectDetailsCell2 = new PdfPCell();
                projectDetailsCell2.setBackgroundColor(BaseColor.WHITE);
                projectDetailsCell2.setPhrase(new Phrase(Utils.round(riskComponent.getScore()).toString(), valueFont));
                projectDetailsCell2.setColspan(2);

                projectDetailsTable.addCell(projectDetailsCell1);
                projectDetailsTable.addCell(projectDetailsCell2);

                projectDetailsTable.completeRow();

            }
        }

        doc.add(projectDetailsTable);

        return doc;
    }
}
