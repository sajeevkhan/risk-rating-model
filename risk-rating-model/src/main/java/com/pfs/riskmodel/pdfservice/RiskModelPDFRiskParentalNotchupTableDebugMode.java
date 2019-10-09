package com.pfs.riskmodel.pdfservice;

import com.google.common.io.ByteStreams;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.pfs.riskmodel.domain.*;
import com.pfs.riskmodel.service.modelvaluator.Utils;
import org.springframework.core.io.FileSystemResource;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by sajeev on 03-Jan-19.
 */
public class RiskModelPDFRiskParentalNotchupTableDebugMode {


    private static Image getImg() throws Exception {

        try {
            InputStream imageStream = ClassLoader.getSystemResourceAsStream("images/Tick_Icon.png");
            //System.out.println("IMAGE STREAM:" + imageStream);


            Path path = Paths.get(ClassLoader.getSystemResource("images/Tick_Icon.png").toURI());
            Image img = Image.getInstance(path.toAbsolutePath().toString());

//
//                Path path = Paths.get(ClassLoader.getSystemResource("images/pfs-logo.jpg").toURI());
//                Image img = Image.getInstance(path.toAbsolutePath().toString());


            img.scalePercent(50f);
            img.setAlignment(Element.ALIGN_CENTER);
            return img;

        } catch (Exception ex) {

            FileSystemResource logo = new FileSystemResource("/opt/risk-rating-model/risk-rating-model/src/main/resources/images/Tick_Icon.png");
            Image image = Image.getInstance(ByteStreams.toByteArray(logo.getInputStream()));
            image.scalePercent(50f);
            image.setAlignment(Element.ALIGN_CENTER);
            return image;

//                System.out.println("IMAGE TICK ICON NOT FOUND");
//                System.out.println(ex.getMessage());
//                return null;
        }
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
        Paragraph riskParaHeader = new Paragraph();
//
//        if (riskModelTemplate.getApplyParentalNotchup() != null) {
//            if (riskModelTemplate.getApplyParentalNotchup() == true)
        riskParaHeader = new Paragraph("Parental Notchup", paraFont);
//            else {
//                riskParaHeader = new Paragraph("Parental Notchup - NOT APPLICABLE", paraFont);
//            }
//        } else {
//            riskParaHeader = new Paragraph("Parental Notchup - NOT APPLICABLE", paraFont);
//        }

        riskParaHeader.setAlignment(Element.ALIGN_CENTER);

        doc.add(new Paragraph( " "));
        doc.add(new Paragraph( riskParaHeader));
        doc.add(new Paragraph( " "));


        //Rating Modifiers Table
        float[] columnWidths = {1,14, 3};
        PdfPTable ratingConditonsTable = new PdfPTable(columnWidths);
        ratingConditonsTable.setWidthPercentage(100.0f);

        ratingConditonsTable.setSpacingBefore(10);

//        // DEBUG MODE First Row -  Number of Notches up after Parental Notchup
        PdfPCell cell1 = new PdfPCell();
//        cell1.setBackgroundColor(BaseColor.ORANGE);
//        cell1.setPhrase(new Phrase("Number of Notches up after Parental Notchup :" + riskModelTemplate.getNumberOfNotchesUpAfterParentalNotchup(), headerfont));
//        cell1.setColspan(3);
//        cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
//
//        ratingConditonsTable.addCell(cell1);
//        ratingConditonsTable.completeRow();



        // First Row -  Parental Notcchup Criteria - LABEL
         cell1 = new PdfPCell();
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
                        value  = "Short Term";
                    if (riskParentalNotchUpCondition.getNatureOfRatingOfParentFirm() == '1')
                        value  = "Long Term";
                    break;
                case 3: // Is Parent's rating at GRADE 10
                    if (riskParentalNotchUpCondition.getYesNoIndicatorValue() == 'Y')
                        value = "Yes";
                    if (riskParentalNotchUpCondition.getYesNoIndicatorValue() == 'N')
                        value = "No";
                    break;
                case 4: //Is Parent's Rating Better Than Borrower's Rating
                    if (riskParentalNotchUpCondition.getYesNoIndicatorValue() == 'Y')
                        value = "Yes";
                    if (riskParentalNotchUpCondition.getYesNoIndicatorValue() == 'N')
                        value = "No";
                    break;
                case 6: //Borrower Rating Grade of the Parent Firm
                    value = riskParentalNotchUpCondition.getValue();
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


        String calculation = "Calculation : ";  Double parentalNotchupScore = 0D;

        // Construct Calculation String for Expert Mode
        for (RiskSubFactor riskSubFactor: riskParentalNotchUp.getRiskSubFactors()){


            for (RiskSubFactorAttribute riskSubFactorAttribute: riskSubFactor.getRiskSubFactorAttributes()){
                if (riskSubFactorAttribute.getIsSelected() == true) {
                    parentalNotchupScore = parentalNotchupScore + riskSubFactorAttribute.getScore() * riskSubFactor.getWeightage();


                    if (riskSubFactor.getItemNo() == 1)
                        calculation = calculation + riskSubFactorAttribute.getScore().toString() + " x " + riskSubFactor.getWeightage().toString();

                    if (riskSubFactor.getItemNo() > 1 && riskSubFactor.getItemNo() <  riskParentalNotchUp.getRiskSubFactors().size())
                        calculation = calculation + " + " + riskSubFactorAttribute.getScore().toString() + " x " + riskSubFactor.getWeightage().toString();


                    if ( riskParentalNotchUp.getRiskSubFactors().size() == riskSubFactor.getItemNo()){
                        calculation = calculation + " + " + riskSubFactorAttribute.getScore().toString() + " x " + riskSubFactor.getWeightage().toString() + " = ";
                    }
                }
            }

        }

        // Add Row for ParentalNotchup Score  - for Expert Mode
        calculation = calculation + " " + parentalNotchupScore.toString();
        cell1 = new PdfPCell();
        cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell1.setBackgroundColor(BaseColor.ORANGE);
        cell1.setPhrase(new Phrase(calculation, valueFont));
        cell1.setColspan(3);
        parentalNotchupSubFactorsTable.addCell(cell1);
        parentalNotchupSubFactorsTable.completeRow();




        for (RiskSubFactor riskSubFactor : riskParentalNotchUp.getRiskSubFactors()) {

            Integer riskSubFactorSectionNumber = riskSubFactor.getItemNo();
            // First Row - Risk Factor  Description
            // First Column - Risk Type Description
            cell1 = new PdfPCell();
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY.darker().darker().darker());

            String sectionNumber= " ";

            sectionNumber =  riskSubFactor.getItemNo().toString();

            cell1.setPhrase(new Phrase(sectionNumber + "  " + riskSubFactor.getDescription() + " (" +riskSubFactor.getWeightage().toString() + "%)", headerfont));
            cell1.setColspan(3);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            parentalNotchupSubFactorsTable.addCell(cell1);
            parentalNotchupSubFactorsTable.completeRow();

            for (RiskSubFactorAttribute riskSubFactorAttribute : riskSubFactor.getRiskSubFactorAttributes()) {

                valueFont.setStyle(Font.NORMAL);

                // Second Row - Risk Component  Column Headings
                // First Column - Risk Component Label
                cell1 = new PdfPCell();
                cell1.setBackgroundColor(BaseColor.WHITE);
                cell1.setPhrase(new Phrase(riskSubFactorAttribute.getDescription().toString(), valueFont));

                // Second Column - Score Label
                PdfPCell cell2 = new PdfPCell();
                cell2.setBackgroundColor(BaseColor.WHITE);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

                if (riskModelTemplate.getPurpose().getCode().equals("02")) {

                    if (riskSubFactorAttribute.getIsSelected()) {
                        valueFont.setStyle(Font.BOLD);
                        cell2.setPhrase(new Phrase(riskSubFactorAttribute.getScore().toString(), valueFont));
                    } else
                        cell2.setPhrase(new Phrase(riskSubFactorAttribute.getScore().toString(), valueFont));
                }
                else {
                    cell2.setPhrase(new Phrase(riskSubFactorAttribute.getScore().toString(), valueFont));
                    cell2.setBackgroundColor(BaseColor.GRAY.brighter());
                }
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
