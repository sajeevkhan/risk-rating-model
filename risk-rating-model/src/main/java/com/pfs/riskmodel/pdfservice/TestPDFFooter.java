package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.pfs.riskmodel.pdfservice.Temp.DetailsTable;
import com.pfs.riskmodel.pdfservice.Temp.HeaderTable;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by sajeev on 01-Jan-19.
 */
class TestPDFFooter extends PdfPageEventHelper {


    Font mainFont = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);

    Font ffont = new Font(Font.FontFamily.UNDEFINED, 10, Font.NORMAL);

    Font ffont1 = new Font(Font.FontFamily.UNDEFINED, 7, Font.NORMAL);

    Rectangle headerBox = new Rectangle(36, 54, 559, 788);

//    private String projectName;
//    private String loanAmount;
//    private Date ratingDate;
//    private String projectType;
//    private String riskLevelDescription;
//


    private static Image getImage() throws Exception{

        Path path = Paths.get(ClassLoader.getSystemResource("images/pfs-logo.jpg").toURI());
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        return img;
    }


    public TestPDFFooter( ) {
        
    }

    public void onEndPage(PdfWriter writer, Document document) {

        PdfContentByte cb = writer.getDirectContent();

        Phrase header1 = new Phrase("Koopil Sawmill, Punnala P.O., Pathanapuram, Phone 0475 2385232  ", mainFont);
        Phrase header2 = new Phrase( "Attendance and Wage Register" , ffont);

        Phrase footer1 = new Phrase( "Koopil Sawmill - Attendance and Wage Register", ffont1);


        // add header text

        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_MIDDLE, header1,
                headerBox.getLeft()+ 100, headerBox.getTop()+10, 0);

        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_MIDDLE, header2,
                headerBox.getLeft()+ 100, headerBox.getTop() , 0);


        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                footer1,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() - 10, 0);

    }
}

