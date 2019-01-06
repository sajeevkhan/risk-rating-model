package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.pfs.riskmodel.domain.*;

/**
 * Created by sajeev on 03-Jan-19.
 */
public class RiskModelPDFRiskParentalNotchupTable {

    public Document buildParentalNotchup(  Document doc, RiskModelTemplate riskModelTemplate) throws Exception {


        // Header Font
        Font headerfont = new Font(Font.FontFamily.HELVETICA );
        headerfont.setColor(BaseColor.WHITE);
        headerfont.setSize(10);

        // Paragraph Font
        Font paraFont = new Font(Font.FontFamily.HELVETICA );
        paraFont.setColor(BaseColor.BLACK);
        paraFont.setSize(12);
        paraFont.setStyle(Font.BOLD);
        paraFont.setStyle(Font.UNDERLINE);


        // Value Font
        Font valueFont = new Font(Font.FontFamily.HELVETICA);
        valueFont.setColor(BaseColor.BLACK);
        valueFont.setSize(8);


        doc.add(new Paragraph( " ",paraFont));
        doc.add(new Paragraph( "Parental Notchup.",paraFont));
        doc.add(new Paragraph( " ",paraFont));


        //Rating Modifiers Table
        float[] columnWidths = {1,14, 3};
        PdfPTable ratingConditonsTable = new PdfPTable(columnWidths);
        ratingConditonsTable.setWidthPercentage(100.0f);

        ratingConditonsTable.setSpacingBefore(10);

        // First Row -  Parental Notcchup Criteria - LABEL
        PdfPCell cell1 = new PdfPCell();
        cell1.setBackgroundColor(BaseColor.BLUE.darker().darker().darker().darker());
        cell1.setPhrase(new Phrase("Parental Notchup Criteria", headerfont));
        cell1.setColspan(3);
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

        ratingConditonsTable.addCell(cell1);
        ratingConditonsTable.completeRow();

        RiskParentalNotchUp riskParentalNotchUp = new RiskParentalNotchUp();
        try {
             riskParentalNotchUp = riskModelTemplate.getRiskParentalNotchUps().iterator().next();

        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        for (RiskParentalNotchUpCondition riskParentalNotchUpCondition: riskParentalNotchUp.getRiskParentalConditions()) {

                 // Second Row On..... Risk Parental Notchup Condition
                // First Column - Item No.
                cell1 = new PdfPCell();
                cell1.setBackgroundColor(BaseColor.WHITE);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setPhrase(new Phrase(riskParentalNotchUpCondition.getItemNo().toString(), valueFont));


                // Second Column - Condition Attribute Description
                PdfPCell cell2 = new PdfPCell();
                cell2.setBackgroundColor(BaseColor.WHITE);
                cell2.setPhrase(new Phrase(riskParentalNotchUpCondition.getDescription(), valueFont));

                // Third Column - Value
            String value = " ";
            switch (riskParentalNotchUpCondition.getCategory()) {

                case 0:  //Rating of Parent Entity
                    value =  riskParentalNotchUpCondition.getValue() ;
                    break;
                case 1:  //Source of Rating of Parent Firm
                    value = riskParentalNotchUpCondition.getValue();
                    break;
                case 2:  //Nature of Rating of Parent Firm
                    if (riskParentalNotchUpCondition.getNatureOfRatingOfParentFirm() == '0')
                          value  = "Long Term";
                    if (riskParentalNotchUpCondition.getNatureOfRatingOfParentFirm() == '1')
                        value  = "Short Term";

                case 3: // Is Parent's rating at GRADE 10
                    if (riskParentalNotchUpCondition.getYesNoIndicatorValue() == 'Y')
                        value = "Yes";
                    if (riskParentalNotchUpCondition.getYesNoIndicatorValue() == 'Y')
                        value = "No";
                    break;
                case 4: //Is Parent's Rating Better Than Borrower's Rating
                    if (riskParentalNotchUpCondition.getYesNoIndicatorValue() == 'Y')
                        value = "Yes";
                    if (riskParentalNotchUpCondition.getYesNoIndicatorValue() == 'Y')
                        value = "No";
                    break;
            }


            PdfPCell cell3 = new PdfPCell();
                cell3.setBackgroundColor(BaseColor.WHITE);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setPhrase(new Phrase(value, valueFont));

            ratingConditonsTable.addCell(cell1);
            ratingConditonsTable.addCell(cell2);
            ratingConditonsTable.addCell(cell3);
            ratingConditonsTable.completeRow();
        }

        doc.add(ratingConditonsTable);

        return doc;
    }
}
