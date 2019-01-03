package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.pfs.riskmodel.domain.*;

/**
 * Created by sajeev on 03-Jan-19.
 */
public class RiskModelPDFComponentTable {

    public Document buildRiskComponentTable(  Document doc,
                                                     RiskModelTemplate riskModelTemplate,
                                                     RiskComponent riskComponent) throws Exception {


        // Main Header Font - Risk Component
        Font mainHeaderFont = new Font(Font.FontFamily.HELVETICA );
        mainHeaderFont.setColor(BaseColor.WHITE);
        mainHeaderFont.setSize(12);

        // Sub Header Font - Risk Factor
        Font subHeaderFont = new Font(Font.FontFamily.HELVETICA );
        subHeaderFont.setColor(BaseColor.WHITE);
        subHeaderFont.setSize(10);


        // Header Font - Risk Sub Factor
        Font headerfont = new Font(Font.FontFamily.HELVETICA );
        headerfont.setColor(BaseColor.WHITE);
        headerfont.setSize(8);


        // Value Font
        Font valueFont = new Font(Font.FontFamily.HELVETICA);
        valueFont.setColor(BaseColor.BLACK);
        valueFont.setSize(8);


        //Project Details Table
        float[] columnWidths = {10, 2, 2};
        PdfPTable projectDetailsTable = new PdfPTable(columnWidths);
        projectDetailsTable.setWidthPercentage(100.0f);

        projectDetailsTable.setSpacingBefore(20);


        // First Row - Risk Component Description
        // First Column - Risk Component Description
        PdfPCell projectDetailsCell1 = new PdfPCell();
        projectDetailsCell1.setBackgroundColor(BaseColor.BLUE.darker().darker().darker().darker());
        projectDetailsCell1.setPhrase(new Phrase(riskComponent.getDescription(), mainHeaderFont));
        projectDetailsCell1.setColspan(3);
        projectDetailsCell1.setHorizontalAlignment(Element.ALIGN_CENTER);

        projectDetailsCell1.setFixedHeight(20);
        projectDetailsTable.addCell(projectDetailsCell1);
        projectDetailsTable.completeRow();

        Integer riskFactorSectionNumber = 1;

        for (RiskFactor riskFactor: riskComponent.getRiskFactors()) {


            if (riskComponent.getRiskFactors().size() > 1) {
                // First Row - Risk Factor  Description
                // First Column - Risk Type Description
                projectDetailsCell1 = new PdfPCell();
                projectDetailsCell1.setBackgroundColor(BaseColor.BLUE.darker().darker().darker().darker());

                String sectionNumber = riskFactorSectionNumber.toString() + "." + riskFactor.getItemNo().toString();

                projectDetailsCell1.setPhrase(new Phrase(sectionNumber + riskFactor.getDescription(), subHeaderFont));
                projectDetailsCell1.setColspan(3);
                projectDetailsCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                projectDetailsTable.addCell(projectDetailsCell1);
                projectDetailsTable.completeRow();
            }

            Integer riskSubFactorSectionNumber = 1;
            for (RiskSubFactor riskSubFactor : riskFactor.getRiskSubFactors()) {

                // First Row - Risk Factor  Description
                // First Column - Risk Type Description
                projectDetailsCell1 = new PdfPCell();
                projectDetailsCell1.setBackgroundColor(BaseColor.LIGHT_GRAY.darker().darker().darker());


                String sectionNumber = riskSubFactorSectionNumber.toString() + "." + riskSubFactor.getItemNo().toString();

                projectDetailsCell1.setPhrase(new Phrase(riskSubFactor.getDescription(), headerfont));
                projectDetailsCell1.setColspan(3);
                projectDetailsCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                projectDetailsTable.addCell(projectDetailsCell1);
                projectDetailsTable.completeRow();

                for (RiskSubFactorAttribute riskSubFactorAttribute : riskSubFactor.getRiskSubFactorAttributes()) {

                    // Second Row - Risk Component  Column Headings
                    // First Column - Risk Component Label
                    projectDetailsCell1 = new PdfPCell();
                    projectDetailsCell1.setBackgroundColor(BaseColor.WHITE);
                    projectDetailsCell1.setPhrase(new Phrase(riskSubFactorAttribute.getDescription(), valueFont));

                    // Second Column - Score Label
                    PdfPCell projectDetailsCell2 = new PdfPCell();
                    projectDetailsCell2.setBackgroundColor(BaseColor.WHITE);
                    projectDetailsCell2.setPhrase(new Phrase(riskSubFactorAttribute.getScore().toString(), valueFont));


                    // Third Column - Score Label
                    PdfPCell projectDetailsCell3 = new PdfPCell();
                    projectDetailsCell2.setBackgroundColor(BaseColor.WHITE);
                    projectDetailsCell2.setPhrase(new Phrase("", valueFont));

                    projectDetailsTable.addCell(projectDetailsCell1);
                    projectDetailsTable.addCell(projectDetailsCell2);

                    projectDetailsTable.completeRow();


                }

                riskFactorSectionNumber++;
                riskSubFactorSectionNumber++;

            }


        }
        doc.add(projectDetailsTable);

        return doc;
    }
}
