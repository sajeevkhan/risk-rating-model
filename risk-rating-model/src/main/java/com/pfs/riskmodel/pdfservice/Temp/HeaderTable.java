package com.pfs.riskmodel.pdfservice.Temp;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.pfs.riskmodel.domain.RiskModelTemplate;

/**
 * Created by sajeev on 03-Jan-19.
 */
public class HeaderTable {

    public Document buildHeader(   Document doc) throws Exception {


        // Header Font
        Font mainFont = new Font(Font.FontFamily.HELVETICA );
        mainFont.setColor(BaseColor.WHITE);
        mainFont.setStyle(Font.BOLD);
        mainFont.setSize(10);


        // Header Font
        Font headerfont = new Font(Font.FontFamily.HELVETICA );
        headerfont.setColor(BaseColor.WHITE);
        headerfont.setSize(8);

        // Value Font
        Font valueFont = new Font(Font.FontFamily.HELVETICA);
        valueFont.setColor(BaseColor.BLACK);
        valueFont.setSize(8);


        //Project Details Table
        float[] columnWidths = {2, 12, 3};
        PdfPTable headerTable = new PdfPTable(columnWidths);
        headerTable.setWidthPercentage(100.0f);



        // First Column - Empty
        PdfPCell cell1 = new PdfPCell();
        cell1.setBackgroundColor(BaseColor.WHITE);
        cell1.setPhrase(new Phrase(" ",valueFont));

        // Second Column - Comp. Name
        PdfPCell cell2 = new PdfPCell();
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setBackgroundColor(BaseColor.GRAY);
        cell2.setPhrase(new Phrase("Koopil Saw Mill, Chachipunna, Punnala PO, Pathanapuram. Phone 0475-2385232",headerfont));

        // Third Column - Empty
        PdfPCell cell3 = new PdfPCell();
        cell3.setBackgroundColor(BaseColor.WHITE);
        cell3.setPhrase(new Phrase("",valueFont));

        headerTable.addCell(cell1);
        headerTable.addCell(cell2);
        headerTable.addCell(cell3);
        headerTable.completeRow();

        // First Column - Empty
         cell1 = new PdfPCell();
        cell1.setBackgroundColor(BaseColor.WHITE);
        cell1.setPhrase(new Phrase(" ",valueFont));

        // Second Column - Comp. Name
         cell2 = new PdfPCell();
        cell2.setBackgroundColor(BaseColor.WHITE);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setPhrase(new Phrase("Attendance and Wage Register",valueFont));

        // Third Column - Empty
         cell3 = new PdfPCell();
        cell3.setBackgroundColor(BaseColor.WHITE);
        cell3.setPhrase(new Phrase("" ,valueFont));

        headerTable.addCell(cell1);
        headerTable.addCell(cell2);
        headerTable.addCell(cell3);
        headerTable.completeRow();


        doc.add(headerTable);

        return doc;
    }
}
