package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.pfs.riskmodel.domain.RiskModelTemplate;
import com.pfs.riskmodel.domain.RiskRatingModifier;
import com.pfs.riskmodel.domain.RiskRatingModifierAttribute;

/**
 * Created by sajeev on 03-Jan-19.
 */
public class RiskModelPDFRiskRatingModifiersTableDebugMode {

    public Document buildRatingModifiers(  Document doc, RiskModelTemplate riskModelTemplate) throws Exception {


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


        Paragraph ratingModHeaderPara = new Paragraph("Rating Modifiers", paraFont);
        ratingModHeaderPara.setAlignment(Element.ALIGN_CENTER);
        doc.add(new Paragraph(" "));
        doc.add(ratingModHeaderPara);
        doc.add(new Paragraph(" "));


        //Rating Modifiers Table
        float[] columnWidths = {1,14, 3};
        PdfPTable ratingModifiersTable = new PdfPTable(columnWidths);
        ratingModifiersTable.setWidthPercentage(100.0f);

        ratingModifiersTable.setSpacingBefore(10);

        for (RiskRatingModifier riskRatingModifier: riskModelTemplate.getRiskRatingModifiers()) {

            // First Row -  Rating Modifier Description and Applicable Attribute
            PdfPCell cell1 = new PdfPCell();
            cell1.setBackgroundColor(BaseColor.BLUE.darker().darker().darker().darker());
            cell1.setPhrase(new Phrase(riskRatingModifier.getDescription(), headerfont));
            cell1.setColspan(2);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell cell2 = new PdfPCell();
            cell2.setBackgroundColor(BaseColor.ORANGE);
            if (riskRatingModifier.getIsApplicable() != null ){
                if (riskRatingModifier.getIsApplicable() == true) {
                    cell2.setPhrase(new Phrase("Applicable", valueFont));
            }
            } else
                cell2.setPhrase(new Phrase("Not Applicable", valueFont));

            //cell2.setColspan(2);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);

            ratingModifiersTable.addCell(cell1);
            ratingModifiersTable.addCell(cell2);
            ratingModifiersTable.completeRow();

            if (riskRatingModifier.getModifierType() == 1) {

                // Second Row -  Rating Modifier Description and Applicable Attribute
                cell1 = new PdfPCell();
                cell1.setBackgroundColor(BaseColor.ORANGE);
                cell1.setPhrase(new Phrase( "Number of notches to downgrade : " +
                                                 riskRatingModifier.getNumberOfNotchesDown().toString(), valueFont));
                cell1.setColspan(2);
                cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

                cell2 = new PdfPCell();
                cell2.setBackgroundColor(BaseColor.ORANGE);
                cell2.setPhrase(new Phrase("Count: " +riskRatingModifier.getCountOfDowngradeBy1or2Notches().toString() , valueFont));

                //cell2.setColspan(2);
                cell2.setHorizontalAlignment(Element.ALIGN_LEFT);

                ratingModifiersTable.addCell(cell1);
                ratingModifiersTable.addCell(cell2);
                ratingModifiersTable.completeRow();

            }





            for (RiskRatingModifierAttribute riskRatingModifierAttribute : riskRatingModifier.getRiskRatingModifierAttributes()) {
                // Second Row On..... Risk Modifiers
                // First Column - Risk Component Label
                cell1 = new PdfPCell();
                cell1.setBackgroundColor(BaseColor.WHITE);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setPhrase(new Phrase(riskRatingModifierAttribute.getItemNo().toString(), valueFont));


                // Second Column - Rating Modifier Attribute Description
                cell2 = new PdfPCell();
                cell2.setBackgroundColor(BaseColor.WHITE);
                cell2.setPhrase(new Phrase(riskRatingModifierAttribute.getDescription(), valueFont));

                // Third Column - Score Label
                PdfPCell cell3 = new PdfPCell();
                cell3.setBackgroundColor(BaseColor.WHITE);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                if (riskRatingModifierAttribute.getYesOrNoIndicator() == 'Y')
                    cell3.setPhrase(new Phrase("Yes", valueFont));
                else
                    cell3.setPhrase(new Phrase("No", valueFont));

                ratingModifiersTable.addCell(cell1);
                ratingModifiersTable.addCell(cell2);
                ratingModifiersTable.addCell(cell3);
                ratingModifiersTable.completeRow();

            }


        }

        doc.add(ratingModifiersTable);

        return doc;
    }
}
