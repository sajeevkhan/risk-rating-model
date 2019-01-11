package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.pfs.riskmodel.domain.RiskModelTemplate;

import java.text.SimpleDateFormat;

/**
 * Created by sajeev on 03-Jan-19.
 */
public class RiskModelPDFHeaderTable {

    public Document buildHeader(   Document doc, RiskModelTemplate riskModelTemplate) throws Exception {


        // Header Font
        Font headerfont = new Font(Font.FontFamily.HELVETICA );
        headerfont.setColor(BaseColor.WHITE);
        headerfont.setSize(8);

        // Value Font
        Font valueFont = new Font(Font.FontFamily.HELVETICA);
        valueFont.setColor(BaseColor.BLACK);
        valueFont.setSize(8);


        //Project Details Table
        float[] columnWidths = {3, 10, 3, 3};
        PdfPTable projectDetailsTable = new PdfPTable(columnWidths);
        projectDetailsTable.setWidthPercentage(100.0f);


        // First Column - Project Name Text
        PdfPCell projectDetailsCell1 = new PdfPCell();
        projectDetailsCell1.setBackgroundColor(BaseColor.BLACK);
        projectDetailsCell1.setPhrase(new Phrase("Project Name",headerfont));

        // Second Column - Project Name
        PdfPCell projectDetailsCell2 = new PdfPCell();
        projectDetailsCell2.setBackgroundColor(BaseColor.WHITE);
        projectDetailsCell2.setPhrase(new Phrase(riskModelTemplate.getProjectName(),valueFont));

        // Third Column - Loan Number Text
        PdfPCell projectDetailsCell3 = new PdfPCell();
        projectDetailsCell3.setBackgroundColor(BaseColor.BLACK);
        projectDetailsCell3.setPhrase(new Phrase("Loan Number",headerfont));

        // Fourth Column - Loan Number
        PdfPCell projectDetailsCell4 = new PdfPCell();
        projectDetailsCell4.setBackgroundColor(BaseColor.WHITE);
        projectDetailsCell4.setPhrase(new Phrase(riskModelTemplate.getLoanNumber()  ,valueFont));

        projectDetailsTable.addCell(projectDetailsCell1);
        projectDetailsTable.addCell(projectDetailsCell2);
        projectDetailsTable.addCell(projectDetailsCell3);
        projectDetailsTable.addCell(projectDetailsCell4);
        projectDetailsTable.completeRow();




        // First Column - Project Type Text
        projectDetailsCell1 = new PdfPCell();
        projectDetailsCell1.setBackgroundColor(BaseColor.BLACK);
        projectDetailsCell1.setPhrase(new Phrase("Project Type",headerfont));

        // Second Column - Project Type
        projectDetailsCell2 = new PdfPCell();
        projectDetailsCell2.setBackgroundColor(BaseColor.WHITE);
        projectDetailsCell2.setPhrase(new Phrase(riskModelTemplate.getProjectType().getValue(),valueFont));

        // Third Column - Loan Number Text
        projectDetailsCell3 = new PdfPCell();
        projectDetailsCell3.setBackgroundColor(BaseColor.BLACK);
        projectDetailsCell3.setPhrase(new Phrase("Loan Amount",headerfont));

        // Fourth Column - Loan Number
        projectDetailsCell4 = new PdfPCell();
        projectDetailsCell4.setBackgroundColor(BaseColor.WHITE);
        projectDetailsCell4.setPhrase(new Phrase(riskModelTemplate.getLoanAmountInCrores().toString() + " CR"  ,valueFont));

        projectDetailsTable.addCell(projectDetailsCell1);
        projectDetailsTable.addCell(projectDetailsCell2);
        projectDetailsTable.addCell(projectDetailsCell3);
        projectDetailsTable.addCell(projectDetailsCell4);
        projectDetailsTable.completeRow();





        // First Column - Risk Level Label
        projectDetailsCell1 = new PdfPCell();
        projectDetailsCell1.setBackgroundColor(BaseColor.BLACK);
        projectDetailsCell1.setPhrase(new Phrase("Risk Level Type",headerfont));

        // Second Column - Risk Level Name
        projectDetailsCell2 = new PdfPCell();
        projectDetailsCell2.setBackgroundColor(BaseColor.WHITE);
        projectDetailsCell2.setPhrase(new Phrase(riskModelTemplate.getProjectRiskLevel().getValue(),valueFont));


        // Third Column - Rating Date  Text
        projectDetailsCell3 = new PdfPCell();
        projectDetailsCell3.setBackgroundColor(BaseColor.BLACK);
        projectDetailsCell3.setPhrase(new Phrase("Rating Date",headerfont));

        // Fourth Column - Loan Number
        projectDetailsCell4 = new PdfPCell();
        projectDetailsCell4.setBackgroundColor(BaseColor.WHITE);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        String dateAsString = sdf.format(riskModelTemplate.getRatingDate());

        projectDetailsCell4.setPhrase(new Phrase(dateAsString ,valueFont));

        projectDetailsTable.addCell(projectDetailsCell1);
        projectDetailsTable.addCell(projectDetailsCell2);
        projectDetailsTable.addCell(projectDetailsCell3);
        projectDetailsTable.addCell(projectDetailsCell4);
        projectDetailsTable.completeRow();

        doc.add(projectDetailsTable);

        return doc;
    }
}
