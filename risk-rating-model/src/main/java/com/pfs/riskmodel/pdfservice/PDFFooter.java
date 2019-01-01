package com.pfs.riskmodel.pdfservice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by sajeev on 01-Jan-19.
 */
class PDFFooter extends PdfPageEventHelper {
    Font ffont = new Font(Font.FontFamily.UNDEFINED, 10, Font.ITALIC);


    //Image img = Image.getInstance("c:/mylogo.PNG");


    private static Image getImage() throws Exception{

        Path path = Paths.get(ClassLoader.getSystemResource("images/pfs-logo.jpg").toURI());
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        return img;
    }

    public void onEndPage(PdfWriter writer, Document document) {

        PdfContentByte cb = writer.getDirectContent();

        Phrase header = new Phrase("this is a header", ffont);

        Phrase footer = new Phrase("this is a footer", ffont);

        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                header,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.top() + 10, 0);


        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                footer,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() - 10, 0);



        try {
            Image img = PDFFooter.getImage();

            img.scaleToFit(100,100);
            img.setAbsolutePosition(document.top() + 30, document.left() + 40);
            cb.addImage(img);
           // document.add(img);

        } catch (Exception x) {
            x.printStackTrace();
        }

    }
}

