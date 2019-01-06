package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.pfs.riskmodel.domain.*;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by sajeev on 03-Jan-19.
 */
public class RiskModelPDFComponentTable {

    private static Image getImage() throws Exception{

        Path path = Paths.get(ClassLoader.getSystemResource("images/Tick_Icon.png").toURI());
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.scalePercent(50f);
        img.setAlignment(Element.ALIGN_CENTER);


        return img;
    }


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


        // Value Font
        Font selectedValueFont = new Font(Font.FontFamily.HELVETICA);
        selectedValueFont.setColor(BaseColor.BLACK);
        selectedValueFont.setStyle(Font.BOLD);
        selectedValueFont.setSize(9);

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


        for (RiskFactor riskFactor: riskComponent.getRiskFactors()) {

            Integer riskFactorSectionNumber = riskFactor.getItemNo();

            if (riskComponent.getRiskFactors().size() > 1) {
                // First Row - Risk Factor  Description
                // First Column - Risk Type Description
                projectDetailsCell1 = new PdfPCell();
                projectDetailsCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                projectDetailsCell1.setFixedHeight(15);

                String sectionNumber = riskFactor.toString() + "." +
                                       riskFactor.getItemNo().toString();

                projectDetailsCell1.setPhrase(new Phrase(sectionNumber + "  " + riskFactor.getDescription(), subHeaderFont));
                projectDetailsCell1.setColspan(3);
                projectDetailsCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                projectDetailsTable.addCell(projectDetailsCell1);
                projectDetailsTable.completeRow();
            }

            for (RiskSubFactor riskSubFactor : riskFactor.getRiskSubFactors()) {

                Integer riskSubFactorSectionNumber = riskSubFactor.getItemNo();
                // First Row - Risk Factor  Description
                // First Column - Risk Type Description
                projectDetailsCell1 = new PdfPCell();
                projectDetailsCell1.setBackgroundColor(BaseColor.LIGHT_GRAY.darker().darker().darker());

                String sectionNumber= " ";

                if (riskComponent.getRiskFactors().size() > 1) {
                    sectionNumber = riskFactor.getItemNo().toString()  + "." +
                            riskSubFactor.getItemNo().toString() + "." +
                            riskSubFactor.getItemNo().toString();

                } else {
                    sectionNumber =  riskFactorSectionNumber.toString() + "." +
                            riskSubFactor.getItemNo().toString() ;
                }
                projectDetailsCell1.setPhrase(new Phrase(sectionNumber + "  " + riskSubFactor.getDescription(), headerfont));
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
                    projectDetailsCell2.setHorizontalAlignment(Element.ALIGN_CENTER);

                    if (riskSubFactorAttribute.getIsSelected())
                        projectDetailsCell2.setPhrase(new Phrase(riskSubFactorAttribute.getScore().toString(), selectedValueFont));
                    else
                        projectDetailsCell2.setPhrase(new Phrase(riskSubFactorAttribute.getScore().toString(), valueFont));

                    // Third Column - Score Label
                    PdfPCell projectDetailsCell3 = new PdfPCell();
                    projectDetailsCell3.setBackgroundColor(BaseColor.WHITE);
                    projectDetailsCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    projectDetailsCell3.setVerticalAlignment(Element.ALIGN_CENTER);


                    if (riskSubFactorAttribute.getIsSelected() == true)
                        projectDetailsCell3.addElement(this.getImage());
                    else
                        projectDetailsCell3.setPhrase(new Phrase(" ", valueFont));

                    projectDetailsTable.addCell(projectDetailsCell1);
                    projectDetailsTable.addCell(projectDetailsCell2);
                    projectDetailsTable.addCell(projectDetailsCell3);

                    projectDetailsTable.completeRow();
                }

            }



        }
        doc.add(projectDetailsTable);


        return doc;
    }
}
