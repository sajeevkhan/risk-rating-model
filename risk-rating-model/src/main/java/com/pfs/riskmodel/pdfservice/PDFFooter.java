package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by sajeev on 01-Jan-19.
 */
class PDFFooter extends PdfPageEventHelper {

    Font ffont = new Font(Font.FontFamily.UNDEFINED, 10, Font.NORMAL);

    Font ffont1 = new Font(Font.FontFamily.UNDEFINED, 7, Font.NORMAL);

    Rectangle headerBox = new Rectangle(36, 54, 559, 788);

    private String projectName;
    private String loanAmount;
    private Date ratingDate;
    private String projectType;
    private String riskLevelDescription;



    private static Image getImage() throws Exception{

        Path path = Paths.get(ClassLoader.getSystemResource("images/pfs-logo.jpg").toURI());
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        return img;
    }


    public PDFFooter(String projectName, String loanAmount, Date ratingDate, String projectType, String riskLevelDescription) {
        this.projectName = projectName;
        this.loanAmount = loanAmount;
        this.ratingDate = ratingDate;
        this.projectType = projectType;
        this.riskLevelDescription = riskLevelDescription;
    }

    public void onEndPage(PdfWriter writer, Document document) {


        PdfContentByte cb = writer.getDirectContent();

        Phrase header1 = new Phrase("PFS Risk Rating Model. ", ffont);
        Phrase header2 = new Phrase( projectType + ":" + riskLevelDescription , ffont);

        Phrase footer1 = new Phrase( "Project : " + projectName + ";    Loan Amount : " + loanAmount + "CR. ", ffont);
        Phrase footer2 = new Phrase( "Rating Date: " + ratingDate.toString(), ffont1);

 //       writer.setBoxSize("headerBox", headerBox);
//        Rectangle rect = writer.getBoxSize("headerBox");
//
        // add header text

        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_MIDDLE, header1,
                headerBox.getLeft()+ 100, headerBox.getTop()+10, 0);

        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_MIDDLE, header2,
                headerBox.getLeft()+ 100, headerBox.getTop() , 0);

//       ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
//                header,
//                (document.right() - document.left()) / 2 + document.leftMargin(),
//                document.top() + 10, 0);
//
//
          ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                footer1,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() - 10, 0);

        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                footer2,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() - 20, 0);




        try {
            Image img = PDFFooter.getImage();

            img.scaleToFit(40,40);
            //img.setAbsolutePosition(document.top() + 30, document.left() + 40);


            //img.setAbsolutePosition(document.top()+20,document.leftMargin()+20);

            img.setAbsolutePosition((headerBox.getLeft() + headerBox.getRight()) /2- 250,
                                     headerBox.getTop() );

            //img.setAbsolutePosition(500,50);

            img.setAlignment(Element.ALIGN_LEFT);


            cb.addImage(img);

        } catch (Exception x) {
            x.printStackTrace();
        }

    }
}

