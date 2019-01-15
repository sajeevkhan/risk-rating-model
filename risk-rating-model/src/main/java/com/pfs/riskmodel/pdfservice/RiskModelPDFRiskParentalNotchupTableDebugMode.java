package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.service.modelvaluator.Utils;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by sajeev on 03-Jan-19.
 */
public class RiskModelPDFRiskParentalNotchupTableDebugMode {

    private Image getImg() throws  Exception{
    Path path = Paths.get(ClassLoader.getSystemResource("images/Tick_Icon.png").toURI());
    Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.scalePercent(50f);
        img.setAlignment(Element.ALIGN_CENTER);


        return img;
}


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

        Paragraph riskParaHeader = new Paragraph("Parental Notchup",paraFont);
        riskParaHeader.setAlignment(Element.ALIGN_CENTER);

        doc.add(new Paragraph( " "));
        doc.add(new Paragraph( riskParaHeader));
        doc.add(new Paragraph( " "));


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
                    if (riskParentalNotchUpCondition.getYesNoIndicatorValue() == 'N')
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


        //Rating Modifiers Table
        float[] columnWidths1 = {14, 3,3};
        PdfPTable parentalNotchupSubFactorsTable = new PdfPTable(columnWidths1);
        parentalNotchupSubFactorsTable.setWidthPercentage(100.0f);

        parentalNotchupSubFactorsTable.setSpacingBefore(10);




        // First Row Parental Notchup Score
        cell1 = new PdfPCell();
        cell1.setBackgroundColor(BaseColor.ORANGE);
        cell1.setPhrase(new Phrase("Is Parental Notchup Applicable?", valueFont));
        cell1.setColspan(2);
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

        PdfPCell  cell2 = new PdfPCell();
        cell2.setBackgroundColor(BaseColor.ORANGE);
        if (riskParentalNotchUp.getIsParentalNotchUpApplicable() == true) {
            cell2.setBackgroundColor(BaseColor.GREEN);
            cell2.setPhrase(new Phrase("Applicable", valueFont));
        }
        else {
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell2.setPhrase(new Phrase("Not Applicable", valueFont));
        }
        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        parentalNotchupSubFactorsTable.addCell(cell1);
        parentalNotchupSubFactorsTable.addCell(cell2);
        parentalNotchupSubFactorsTable.completeRow();

        // Second Row Parental Notchup Score
        cell1 = new PdfPCell();
        cell1.setBackgroundColor(BaseColor.ORANGE);
        cell1.setPhrase(new Phrase("Parental Notchup Score", valueFont));
        cell1.setColspan(2);
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

        cell2 = new PdfPCell();
        cell2.setBackgroundColor(BaseColor.ORANGE);
        cell2.setPhrase(new Phrase(riskParentalNotchUp.getParentalNotchUpScore().toString(), valueFont));
        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        parentalNotchupSubFactorsTable.addCell(cell1);
        parentalNotchupSubFactorsTable.addCell(cell2);
        parentalNotchupSubFactorsTable.completeRow();


        // Third Row Parental Notchup Calculated
        cell1 = new PdfPCell();
        cell1.setBackgroundColor(BaseColor.ORANGE);
        cell1.setPhrase(new Phrase("Number of notches calculated " +
                "\n " +
                "(Notch-up score as a percentage of maximum possible score) * Difference between parent and borrower grades (in terms of notches)", valueFont));
        cell1.setColspan(2);
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

        cell2 = new PdfPCell();
        cell2.setBackgroundColor(BaseColor.ORANGE);
        if (riskParentalNotchUp.getNumberOfNotchesUpgraded() != null) {
            cell2.setPhrase(new Phrase(riskParentalNotchUp.getNumberOfNotchesCalculated().toString(), valueFont));
        } else
            cell2.setPhrase(new Phrase(" ", valueFont));


        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        parentalNotchupSubFactorsTable.addCell(cell1);
        parentalNotchupSubFactorsTable.addCell(cell2);
        parentalNotchupSubFactorsTable.completeRow();

        // Third Row Parental Notchup
        cell1 = new PdfPCell();
        cell1.setBackgroundColor(BaseColor.ORANGE);
        cell1.setPhrase(new Phrase("Number of notches to be upgraded", valueFont));
        cell1.setColspan(2);
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

        cell2 = new PdfPCell();
        cell2.setBackgroundColor(BaseColor.ORANGE);
        if (riskParentalNotchUp.getNumberOfNotchesUpgraded() != null) {
            cell2.setPhrase(new Phrase(riskParentalNotchUp.getNumberOfNotchesUpgraded().toString(), valueFont));
        } else
            cell2.setPhrase(new Phrase(" ", valueFont));


        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        parentalNotchupSubFactorsTable.addCell(cell1);
        parentalNotchupSubFactorsTable.addCell(cell2);
        parentalNotchupSubFactorsTable.completeRow();



        for (RiskSubFactor riskSubFactor : riskParentalNotchUp.getRiskSubFactors()) {

            Integer riskSubFactorSectionNumber = riskSubFactor.getItemNo();
            // First Row - Risk Factor  Description
            // Risk Type Description and Score
            cell1 = new PdfPCell();
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY.darker().darker().darker());
            String sectionNumber= " ";
            sectionNumber =  riskSubFactor.getItemNo().toString();
            cell1.setPhrase(new Phrase(sectionNumber + "  " + riskSubFactor.getDescription(), headerfont));
            cell1.setColspan(2);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

            cell2 = new PdfPCell();
            cell2.setBackgroundColor(BaseColor.ORANGE);
            Double weightage = Utils.round(riskSubFactor.getWeightage() * 100);
            cell2.setPhrase(new Phrase(weightage + "%", valueFont));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);


            parentalNotchupSubFactorsTable.addCell(cell1);
            parentalNotchupSubFactorsTable.addCell(cell2);

            parentalNotchupSubFactorsTable.completeRow();

            for (RiskSubFactorAttribute riskSubFactorAttribute : riskSubFactor.getRiskSubFactorAttributes()) {

                valueFont.setStyle(Font.NORMAL);

                // Second Row - Risk Component  Column Headings
                // First Column - Risk Component Label
                cell1 = new PdfPCell();
                cell1.setBackgroundColor(BaseColor.WHITE);
                cell1.setPhrase(new Phrase(riskSubFactorAttribute.getDescription().toString(), valueFont));

                // Second Column - Score Label
                cell2 = new PdfPCell();
                cell2.setBackgroundColor(BaseColor.WHITE);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

                if (riskSubFactorAttribute.getIsSelected()) {
                    valueFont.setStyle(Font.BOLD);
                    cell2.setPhrase(new Phrase(riskSubFactorAttribute.getScore().toString(), valueFont));
                }
                else
                    cell2.setPhrase(new Phrase(riskSubFactorAttribute.getScore().toString(), valueFont));

                // Third Column - Score Label
                PdfPCell cell3 = new PdfPCell();
                cell3.setBackgroundColor(BaseColor.WHITE);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setVerticalAlignment(Element.ALIGN_CENTER);


                if (riskSubFactorAttribute.getIsSelected() == true){
                    cell1.setBackgroundColor(BaseColor.YELLOW  );
                    cell2.setBackgroundColor(BaseColor.YELLOW );
                    cell3.setBackgroundColor(BaseColor.YELLOW);
                    cell3.addElement(this.getImg());
                }
                else
                    cell3.setPhrase(new Phrase(" ", valueFont));

                parentalNotchupSubFactorsTable.addCell(cell1);
                parentalNotchupSubFactorsTable.addCell(cell2);
                parentalNotchupSubFactorsTable.addCell(cell3);

                parentalNotchupSubFactorsTable.completeRow();
            }

        }





        doc.add(ratingConditonsTable);
        doc.add(parentalNotchupSubFactorsTable);

        return doc;
    }
}
