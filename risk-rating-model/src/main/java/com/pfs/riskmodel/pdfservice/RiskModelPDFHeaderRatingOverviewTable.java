package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskType;

/**
 * Created by sajeev on 03-Jan-19.
 */
public class RiskModelPDFHeaderRatingOverviewTable {

    public Document buildHeaderRatingTable(   Document doc, RiskModelTemplate riskModelTemplate) throws Exception {


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

        // First Row - Risk Rating Label
        // First Column - Project Name Text
        PdfPCell projectDetailsCell1 = new PdfPCell();
        projectDetailsCell1.setBackgroundColor(BaseColor.BLUE.darker().darker().darker().darker());
        projectDetailsCell1.setPhrase(new Phrase("Risk Rating",headerfont));
        projectDetailsCell1.setColspan(3);
        projectDetailsCell1.setHorizontalAlignment(Element.ALIGN_CENTER);

        projectDetailsTable.addCell(projectDetailsCell1);
        projectDetailsTable.completeRow();

        // Second Row - Risk Rating Label
        // First Column - Rating Type
        projectDetailsCell1 = new PdfPCell();
        projectDetailsCell1.setBackgroundColor(BaseColor.ORANGE);
        projectDetailsCell1.setPhrase(new Phrase("Rating Type",valueFont));

        // Second Column - Score Label
        PdfPCell projectDetailsCell2 = new PdfPCell();
        projectDetailsCell2.setBackgroundColor(BaseColor.ORANGE);
        projectDetailsCell2.setPhrase(new Phrase("Score",valueFont));

        // Third Column - Loan Number Text
        PdfPCell projectDetailsCell3 = new PdfPCell();
        projectDetailsCell3.setBackgroundColor(BaseColor.ORANGE);
        projectDetailsCell3.setPhrase(new Phrase("Grade",valueFont));

        projectDetailsTable.addCell(projectDetailsCell1);
        projectDetailsTable.addCell(projectDetailsCell2);
        projectDetailsTable.addCell(projectDetailsCell3);
        projectDetailsTable.completeRow();



        // Risk Types Score and Grade
        for (RiskType riskType: riskModelTemplate.getRiskTypes()) {

            projectDetailsCell1 = new PdfPCell();
            projectDetailsCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            projectDetailsCell1.setPhrase(new Phrase(riskType.getDescription(),valueFont));

            // Second Column - Project Score
            projectDetailsCell2 = new PdfPCell();
            projectDetailsCell2.setBackgroundColor(BaseColor.WHITE);
            projectDetailsCell2.setPhrase(new Phrase(riskType.getScore().toString(),valueFont));

            // Third Column - Project Grade
            projectDetailsCell3 = new PdfPCell();
            projectDetailsCell3.setBackgroundColor(BaseColor.WHITE);
            projectDetailsCell3.setPhrase(new Phrase(riskType.getGrade(),valueFont));

            projectDetailsTable.addCell(projectDetailsCell1);
            projectDetailsTable.addCell(projectDetailsCell2);
            projectDetailsTable.addCell(projectDetailsCell3);
            projectDetailsTable.completeRow();
        }



        // Project Rating Row
        // First Column - Rating Type
        projectDetailsCell1 = new PdfPCell();
        projectDetailsCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        projectDetailsCell1.setPhrase(new Phrase("Project Rating ",valueFont));

        // Second Column - Project Score
        projectDetailsCell2 = new PdfPCell();
        projectDetailsCell2.setBackgroundColor(BaseColor.WHITE);
        projectDetailsCell2.setPhrase(new Phrase(riskModelTemplate.getScore().toString(),valueFont));

        // Third Column - Project Grade
        projectDetailsCell3 = new PdfPCell();
        projectDetailsCell3.setBackgroundColor(BaseColor.WHITE);
        projectDetailsCell3.setPhrase(new Phrase(riskModelTemplate.getOverallProjectGrade(),valueFont));

        projectDetailsTable.addCell(projectDetailsCell1);
        projectDetailsTable.addCell(projectDetailsCell2);
        projectDetailsTable.addCell(projectDetailsCell3);
        projectDetailsTable.completeRow();



        // Modified Rating  Row -
        // First Column - Modified Rating Label
        if (riskModelTemplate.getApplyRatingModifiers() != null) {
            if (riskModelTemplate.getApplyRatingModifiers() == true) {
                projectDetailsCell1 = new PdfPCell();
                projectDetailsCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                projectDetailsCell1.setPhrase(new Phrase("Modified Rating", valueFont));

                // Second Column - Score Label
                projectDetailsCell2 = new PdfPCell();
                projectDetailsCell2.setBackgroundColor(BaseColor.WHITE);
                projectDetailsCell2.setPhrase(new Phrase(riskModelTemplate.getModifiedProjectGrade(), valueFont));

                projectDetailsCell2.setColspan(2);

                projectDetailsTable.addCell(projectDetailsCell1);
                projectDetailsTable.addCell(projectDetailsCell2);
                projectDetailsTable.completeRow();
            }
        }


        // Modified Rating  Row -
        // First Column - Modified Rating Label
        projectDetailsCell1 = new PdfPCell();
        projectDetailsCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        if (riskModelTemplate.getApplyParentalNotchup() != null) {
            if (riskModelTemplate.getApplyParentalNotchup() == true)
                projectDetailsCell1.setPhrase(new Phrase("Final Rating  (Post Parental Notchup)", valueFont));
            else
                projectDetailsCell1.setPhrase(new Phrase("Final Rating", valueFont));

            // Second Column - Score Label
            projectDetailsCell2 = new PdfPCell();
            projectDetailsCell2.setBackgroundColor(BaseColor.WHITE);
            if (riskModelTemplate.getApplyParentalNotchup() == true) {
                projectDetailsCell2.setPhrase(new Phrase(riskModelTemplate.getAfterParentalNotchUpGrade(), valueFont));
            } else {
                projectDetailsCell2.setPhrase(new Phrase(riskModelTemplate.getFinalProjectGrade(), valueFont));
            }

            projectDetailsCell2.setColspan(2);

            projectDetailsTable.addCell(projectDetailsCell1);
            projectDetailsTable.addCell(projectDetailsCell2);
            projectDetailsTable.completeRow();

        }
        doc.add(projectDetailsTable);

        return doc;
    }
}
