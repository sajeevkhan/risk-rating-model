package com.pfs.riskmodel.pdfservice;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pfs.riskmodel.domain.RiskModelTemplate;
import org.springframework.web.servlet.view.AbstractView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Created by sajeev on 01-Jan-19.
 */



/**
  * This class is a work around for working with iText 5.x in Spring.
  * The code here is almost identical to the AbstractPdfView class.
  *
  */
public abstract class AbstractITextPdfView extends AbstractView {

        public AbstractITextPdfView() {
                setContentType("application/pdf");
        }


        @Override
        protected boolean generatesDownloadContent() {
                return true;
        }


        @Override
        protected void renderMergedOutputModel(Map<String, Object> model,
                                               HttpServletRequest request, HttpServletResponse response) throws Exception {
                // IE workaround: write into byte array first.
                ByteArrayOutputStream baos = createTemporaryOutputStream();

                // Apply preferences and build metadata.

                Document document = newDocument();
                PdfWriter writer = newWriter(document, baos);
                prepareWriter(model, writer, request);
                buildPdfMetadata(model, document, request);


                RiskModelTemplate riskModelTemplate = new RiskModelTemplate();
                riskModelTemplate = (RiskModelTemplate) model.get("RiskModelTemplate");


                //Footer

                PDFFooter event = new PDFFooter( riskModelTemplate.getProjectName(),
                                                 riskModelTemplate.getLoanAmountInCrores().toString(),
                        riskModelTemplate.getRatingDate(),
                        riskModelTemplate.getProjectType().getValue(),
                        riskModelTemplate.getProjectRiskLevel().getValue());

                writer.setPageEvent(event);

                // Build PDF document.
                document.open();
                buildPdfDocument(model, document, writer, request, response);
                document.close();

                // Flush to HTTP response.
                writeToResponse(response, baos);
        }

        protected Document newDocument() {

                return new Document(PageSize.A4,36, 36, 70, 80);
        }

        protected PdfWriter newWriter(Document document, OutputStream os) throws DocumentException {
                return PdfWriter.getInstance(document, os);
        }

        protected void prepareWriter(Map<String, Object> model, PdfWriter writer, HttpServletRequest request)
                throws DocumentException {

                writer.setViewerPreferences(getViewerPreferences());
        }

        protected int getViewerPreferences() {
                return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
        }

        protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
        }

        protected abstract void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                                 HttpServletRequest request, HttpServletResponse response) throws Exception;






}