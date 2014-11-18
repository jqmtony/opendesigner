package org.openossad.util.helper.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 8/11/11
 * Time: 18:10
 * To change this template use File | Settings | File Templates.
 */
public class PdfHelper
{


    public static OutputStream concatPDFs(List streamOfPDFFiles, OutputStream outputStream, boolean paginate) throws IOException, DocumentException
    {

        Document document = new Document();

        List pdfs = streamOfPDFFiles;
        List readers = new ArrayList();
        int totalPages = 0;
        Iterator iteratorPDFs = pdfs.iterator();

        // Create Readers for the pdfs.
        while (iteratorPDFs.hasNext()) {
            InputStream pdf = (InputStream) iteratorPDFs.next();
            PdfReader pdfReader = new PdfReader(pdf);
            readers.add(pdfReader);
            totalPages += pdfReader.getNumberOfPages();
        }
        // Create a writer for the outputstream
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        document.open();
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
        // data

        PdfImportedPage page;
        int currentPageNumber = 0;
        int pageOfCurrentReaderPDF = 0;
        Iterator iteratorPDFReader = readers.iterator();

        // Loop through the PDF files and add to the output.
        while (iteratorPDFReader.hasNext()) {
            PdfReader pdfReader = (PdfReader) iteratorPDFReader.next();

            // Create a new page in the target for each source page.
            while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                document.newPage();
                pageOfCurrentReaderPDF++;
                currentPageNumber++;
                page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
                cb.addTemplate(page, 0, 0);

                // Code for pagination.
                if (paginate) {
                    cb.beginText();
                    cb.setFontAndSize(bf, 9);
                    cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " of " + totalPages, 520, 5, 0);
                    cb.endText();
                }
            }
            pageOfCurrentReaderPDF = 0;
        }
        outputStream.flush();
        document.close();
        outputStream.close();

        return outputStream;
    }
}

