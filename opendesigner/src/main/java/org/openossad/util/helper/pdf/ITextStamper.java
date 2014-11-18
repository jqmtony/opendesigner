package org.openossad.util.helper.pdf;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import org.openossad.data.domain.Tblgraphs;

import java.io.*;


public class ITextStamper
{



    public static FileOutputStream generateFromTemplate(Tblgraphs tblgraphs, String templatePdfPath, String dest) throws IOException,FileNotFoundException, DocumentException
    {


        PdfReader pdfTemplate = new PdfReader(templatePdfPath);
        FileOutputStream fileOutputStream = new FileOutputStream(dest);

        PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);
        stamper.setFormFlattening(true);

        stamper.getAcroFields().setField("gName", tblgraphs.getGname());
        stamper.getAcroFields().setField("projectId", tblgraphs.getProjectId());
        stamper.getAcroFields().setField("gAbrList",tblgraphs.getGabrList());
        stamper.getAcroFields().setField("gApprobNames", tblgraphs.getGapprobNames());
        stamper.getAcroFields().setField("gAuthor", tblgraphs.getGauthor());
        stamper.getAcroFields().setField("gBgColor", tblgraphs.getGbgcolor());
        stamper.getAcroFields().setField("gDesignDate",tblgraphs.getGdesignDate().toString());
        stamper.getAcroFields().setField("gDomain", tblgraphs.getGdomain());
        stamper.getAcroFields().setField("gDocGen", tblgraphs.getGdocGen().toString());
        stamper.getAcroFields().setField("gNews",tblgraphs.getGnews());
        stamper.getAcroFields().setField("gId", tblgraphs.getGid());

        stamper.close();
        pdfTemplate.close();

        return fileOutputStream;

    }
}
