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
        projectDetailsCell1.setPhrase(new Phrase("Project Phase",headerfont));

        // Second Column - Risk Level Name
        projectDetailsCell2 = new PdfPCell();
        projectDetailsCell2.setBackgroundColor(BaseColor.WHITE);

        String projectPhase = riskModelTemplate.getProjectRiskLevel().getValue() + " (" +riskModelTemplate.getPurpose().getDescription() + ")";

        projectDetailsCell2.setPhrase(new Phrase(projectPhase,valueFont));


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


        //Workflow Details Table
        float[] columnWidths1 = {3, 3f, 3f, 4f, 3, 3};

        PdfPTable workflowTable = new PdfPTable(columnWidths1);
        workflowTable.setWidthPercentage(100.0f);


        // First Column - Created by Label
        PdfPCell workflowCell1 = new PdfPCell();
        workflowCell1.setBackgroundColor(BaseColor.BLACK);
        workflowCell1.setPhrase(new Phrase("Created by",headerfont));

        // Second Column - Created by
        PdfPCell workflowCell2 = new PdfPCell();
        workflowCell2.setBackgroundColor(BaseColor.WHITE);
        workflowCell2.setPhrase(new Phrase(riskModelTemplate.getCreatedBy(),valueFont));

        // Third Column - Reviewed By Label
        PdfPCell workflowCell3 = new PdfPCell();
        workflowCell3.setBackgroundColor(BaseColor.BLACK);
        workflowCell3.setPhrase(new Phrase("Reviewed By",headerfont));

        // Fourth Column - Reviewed By Name
        PdfPCell workflowCell4 = new PdfPCell();
        workflowCell4.setBackgroundColor(BaseColor.WHITE);
        workflowCell4.setPhrase(new Phrase(riskModelTemplate.getReviewedBy()  ,valueFont));


        // Fifth Column - Status  Label
        PdfPCell workflowCell5 = new PdfPCell();
        workflowCell5.setBackgroundColor(BaseColor.BLACK);
        workflowCell5.setPhrase(new Phrase("Status ",headerfont));

        // Sixth Column - Status
        PdfPCell workflowCell6 = new PdfPCell();
        workflowCell6.setBackgroundColor(BaseColor.WHITE);
        if (riskModelTemplate.getWorkflowStatus() != null) {
            workflowCell6.setPhrase(new Phrase(riskModelTemplate.getWorkflowStatus().getDescription(), valueFont));
        } else  {
            workflowCell6.setPhrase(new Phrase("Created", valueFont));
        }


        workflowTable.addCell(workflowCell1);
        workflowTable.addCell(workflowCell2);
        workflowTable.addCell(workflowCell3);
        workflowTable.addCell(workflowCell4);
        workflowTable.addCell(workflowCell5);
        workflowTable.addCell(workflowCell6);

        workflowTable.completeRow();


        doc.add(workflowTable);

        return doc;
    }
}
