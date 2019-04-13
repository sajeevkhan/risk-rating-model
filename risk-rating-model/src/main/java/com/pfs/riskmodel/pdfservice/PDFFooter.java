package com.pfs.riskmodel.pdfservice;

import com.google.common.io.ByteStreams;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
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

    private ResourceLoader resourceLoader;

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }




    private static Image getImage() throws Exception{


        try {
            Path path = Paths.get(ClassLoader.getSystemResource("images/pfs-logo.jpg").toURI());
            System.out.println("ClassLoader.getSystemResource(\"images/pfs-logo.jpg\").toURI()    : " + ClassLoader.getSystemResource("images/pfs-logo.jpg").toURI());
            System.out.println("LOG FILE PATH: " + path);


        System.out.println("PDF HEADER -----------------------------------------------------------PATH ");
        System.out.println("Path :  " + path.toString());
        System.out.println("Path.AbsolutePath :  " + path.toAbsolutePath().toString());

        Image img = Image.getInstance(path.toAbsolutePath().toString());
        System.out.println("Image " + img);
        return img;
//
//            if (img == null) {
//
//                Image image =  Image.getInstance("/opt/risk-rating-model/risk-rating-model/src/main/resources/images/pfs-logo.jpg");
//                return image;
//
//            }else{
//            return img;
//            }

        }
        catch (NullPointerException ex ) {
            System.out.println("Image Path Null Pointer Exception: " );

            FileSystemResource logo = new FileSystemResource("/opt/risk-rating-model/risk-rating-model/src/main/resources/images/pfs-logo.jpg");
            Image image = Image.getInstance(ByteStreams.toByteArray(logo.getInputStream()));
            return image;

//            Image image =  Image.getInstance("/opt/risk-rating-model/risk-rating-model/src/main/resources/images/pfs-logo.jpg");
//            return image;
        }

        //return null;
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
                headerBox.getLeft()+ 175, headerBox.getTop()+10, 0);

        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_MIDDLE, header2,
                headerBox.getLeft()+ 150, headerBox.getTop() , 0);

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

