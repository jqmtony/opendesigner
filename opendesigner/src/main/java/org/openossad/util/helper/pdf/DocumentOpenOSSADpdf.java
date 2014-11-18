package org.openossad.util.helper.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 22/10/11
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public abstract class DocumentOpenOSSADpdf implements IDocumentOpenOSSADpdf
{
    private Document document;
    private PdfWriter writer;
    private OutputStream outputStream;
    private PdfContentByte pdfContentByte;


    protected DocumentOpenOSSADpdf(OutputStream ostream) throws DocumentException
    {
        document= new Document();
        outputStream=ostream;
        writer = PdfWriter.getInstance(document, outputStream);
        writer.setViewerPreferences(PdfWriter.PageModeUseThumbs);
        document.open();
        pdfContentByte = writer.getDirectContent();
    }


}
