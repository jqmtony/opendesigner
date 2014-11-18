package org.openossad.util.helper.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.openossad.data.util.OpenossadData;
import org.openossad.data.domain.OoCells;
import org.openossad.data.domain.Tblgraphs;
import org.openossad.util.helper.DAOOOSSAD;
import org.openossad.util.helper.ReferencialOOSSAD;

import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;


public class PdfOOHelper
{

    private static HeaderFooter headerFooter;
    private static Paragraph emptyParagraph = new Paragraph(new Chunk("  ",FontFactory.getFont(FontFactory.HELVETICA, 14)));

    public static Document insertPortadaIntoDocument(Document document, Tblgraphs tblgraph) throws DocumentException, IOException
    {
        document.newPage();
        document.setFooter(getHeaderFooter(tblgraph));
        document.add(t3(tblgraph));
        document.add(emptyParagraph);
        PdfPTable t4 = new PdfPTable(2);
        t4.setWidthPercentage(95);
        t4.addCell(getTitleParagraph("Objeto de la aplicación:"));
        t4.addCell(getTitleParagraph("Campo de la aplicación:"));
        final String gobject = (tblgraph.getGobject()==null) ? "" : tblgraph.getGobject();
        PdfPCell c3 = new PdfPCell(new Paragraph(new Chunk(gobject, FontFactory.getFont(FontFactory.HELVETICA, 14))));
        c3.setFixedHeight(100f);
        t4.addCell(c3);
        final String gdomain = (tblgraph.getGdomain() == null) ? "" : tblgraph.getGdomain();
        PdfPCell c4 = new PdfPCell(new Paragraph(new Chunk(gdomain, FontFactory.getFont(FontFactory.HELVETICA, 14))));
        c4.setFixedHeight(100f);
        t4.addCell(c4);
        document.add(t4);
        document.add(emptyParagraph);
        t4 = new PdfPTable(1);
        t4.setWidthPercentage(95);
        final String gversionNumber = (tblgraph.getGversionNumber() == null) ? "" : tblgraph.getGversionNumber();
        final Date updateDate = (tblgraph.getGupdateDate() == null) ? new Date() : tblgraph.getGupdateDate();
        Paragraph pp = new Paragraph(new Chunk("Novedades de la versión "+ gversionNumber +" :\n",FontFactory.getFont(FontFactory.HELVETICA, 14)));
        pp.add("Última actualización el "+ updateDate.toString() +" :\n");
        c3=new PdfPCell(pp);
        c3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        t4.addCell(c3);
        final String gnews = (tblgraph.getGnews() == null) ? "" : tblgraph.getGnews();
        c3 = new PdfPCell(new Paragraph(new Chunk(gnews,FontFactory.getFont(FontFactory.HELVETICA, 14))));
        c3.setFixedHeight(100f);
        t4.addCell(c3);
        document.add(t4);
        document.add(emptyParagraph);
        t4 = new PdfPTable(2);
        t4.setWidthPercentage(95);
        c3 = new PdfPCell(new Paragraph(new Chunk("Palabras clave:",FontFactory.getFont(FontFactory.HELVETICA, 14))));
        c3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        t4.addCell(c3);
        c4 = new PdfPCell(new Paragraph(new Chunk("Lista de abreviaciones:",FontFactory.getFont(FontFactory.HELVETICA, 14))));
        c4.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        t4.addCell(c4);
        final String gkeywords = (tblgraph.getGkeywords()==null) ? "" : tblgraph.getGkeywords();
        c3 = new PdfPCell(new Paragraph(new Chunk(gkeywords,FontFactory.getFont(FontFactory.HELVETICA, 14))));
        c3.setFixedHeight(100f);
        t4.addCell(c3);
        final String gabrList = (tblgraph.getGabrList()==null) ? "" : tblgraph.getGabrList();
        c4 = new PdfPCell(new Paragraph(new Chunk(gabrList,FontFactory.getFont(FontFactory.HELVETICA, 14))));
        c4.setFixedHeight(100f);
        t4.addCell(c4);
        document.add(t4);
        document.add(emptyParagraph);
        PdfPTable t2 = new PdfPTable(3);
        t2.setWidthPercentage(95);
        PdfPCell c2 = new PdfPCell(new Paragraph(new Chunk("Autor:",FontFactory.getFont(FontFactory.HELVETICA, 14))));
        c2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        t2.addCell(c2);
        c2 = new PdfPCell(new Paragraph(new Chunk("Verificadores:",FontFactory.getFont(FontFactory.HELVETICA, 14))));
        c2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        t2.addCell(c2);
        c2 = new PdfPCell(new Paragraph(new Chunk("Aprobadores:",FontFactory.getFont(FontFactory.HELVETICA, 14))));
        c2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        t2.addCell(c2);
        document.add(t2);
        t2 = new PdfPTable(3);
        t2.setWidthPercentage(95);
        final String gauthor = (tblgraph.getGauthor()==null) ? "" : tblgraph.getGauthor();
        c2 = new PdfPCell(new Paragraph(new Chunk(gauthor,FontFactory.getFont(FontFactory.HELVETICA, 14))));
        c2.setFixedHeight(100f);
        t2.addCell(c2);
        final String gverifNames = (tblgraph.getGverifNames()==null) ? "" : tblgraph.getGverifNames();
        c2 = new PdfPCell(new Paragraph(new Chunk(gverifNames,FontFactory.getFont(FontFactory.HELVETICA, 14))));
        c2.setFixedHeight(100f);
        t2.addCell(c2);
        final String gapprobNames = (tblgraph.getGapprobNames()==null) ? "" : tblgraph.getGapprobNames();
        c2 = new PdfPCell(new Paragraph(new Chunk(gapprobNames,FontFactory.getFont(FontFactory.HELVETICA, 14))));
        c2.setFixedHeight(100f);
        t2.addCell(c2);
        document.add(t2);
        //document.add(emptyParagraph);
        Paragraph p3 = new Paragraph(new Chunk("    Lista de difusión:",FontFactory.getFont(FontFactory.HELVETICA, 14)));
        p3.add("");
        document.add(p3);
        final String gdifList = (tblgraph.getGdifList()==null) ? "" : tblgraph.getGdifList();
        p3 = new Paragraph(new Chunk("    "+ gdifList,FontFactory.getFont(FontFactory.HELVETICA, 14)));
        p3.add("");
        document.add(p3);
        return document;
    }

    private static PdfPCell getTitleParagraph(String text)
    {
        PdfPCell c3 = new PdfPCell(new Paragraph(new Chunk(text, FontFactory.getFont(FontFactory.HELVETICA, 14))));
        c3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        return c3;
    }

    private static HeaderFooter getHeaderFooter(Tblgraphs tblgraph)
    {
        return new HeaderFooter(new Phrase(" "+ tblgraph.getGname()+"      - "),true);
    }

    private static PdfPTable t3(Tblgraphs tblgraph) throws IOException, BadElementException
    {
        float[] widths = {0.2f, 0.8f};
        PdfPTable t3 = new PdfPTable(widths);
        t3.setWidthPercentage(95);
        PdfPCell c5 = new PdfPCell(new Paragraph(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA, 30))));
        c5.setFixedHeight(50f);
        //t3.addCell(c5);

        final Image image = Image.getInstance(PdfOOHelper.class.getResource("/ui/images/openossadlogo.png"));
        t3.addCell(image);
        t3.addCell(t2(tblgraph));
        return t3;

    }
    private static PdfPTable t2(Tblgraphs tblgraph) {
        PdfPTable t2 = new PdfPTable(3);
        t2.setWidthPercentage(100);
        PdfPCell c2 = new PdfPCell(new Paragraph(new Chunk(tblgraph.getGname(),FontFactory.getFont(FontFactory.HELVETICA, 22))));
        c2.setColspan(3);
        c2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        t2.addCell(c2);
        t2.addCell("Ref: "+ tblgraph.getGref());
        if (tblgraph.getGgraphState()==null) {
            t2.addCell("Estado: Aplicable");
        } else {
            t2.addCell("Estado: "+((tblgraph.getGgraphState()==1) ? "Aplicable" : "En creación"));
        }
        t2.addCell("Versión: "+ tblgraph.getGversionNumber());
        return t2;

    }

    public static Document insertDetailGraphIntoDocument(Document document, Tblgraphs tblgraph, Rectangle pageSize) throws IOException, DocumentException
    {
        document.setPageSize( pageSize );
        document.newPage();
        document.add(t3(tblgraph));
        document.add(emptyParagraph);



        List<OoCells> ooCellsList = DAOOOSSAD.getOoCellsInTblgraphs(tblgraph.getGid());

        document = printTabla(document,ooCellsList, OpenossadData.process);
        document = printTabla(document,ooCellsList, OpenossadData.document);
        document = printTabla(document,ooCellsList, OpenossadData.role);
        document = printTabla(document,ooCellsList, OpenossadData.indic);
        document = printTabla(document,ooCellsList, OpenossadData.medio);

        //Aprobadores
        document = printTabla(document, "aprobadores","SELECT SID,Name,CompleteName,Description,IsGroup FROM tblaccounts " +
                                       "WHERE SID IN (SELECT CodeUser FROM oodocflow WHERE GraphID='"+ tblgraph.getGid()+"' AND Type=3  )",1,3);
        //Redactores
        document = printTabla(document, "redactores","SELECT SID,Name,CompleteName,Description,IsGroup FROM tblaccounts " +
                                      "WHERE SID IN (SELECT CodeUser FROM oodocflow WHERE GraphID='"+ tblgraph.getGid()+"' AND Type=2 )",1,3);
        //Lectores
        document = printTabla(document, "lectores","SELECT SID,Name,CompleteName,Description,IsGroup FROM tblaccounts " +
                                    "WHERE SID IN (SELECT CodeUser FROM oodocflow WHERE GraphID='"+ tblgraph.getGid()+"' AND Type=1 )",1,3);

        Chunk txt = new Chunk(tblgraph.getGname(),FontFactory.getFont(FontFactory.HELVETICA, 35));
        float superscript = 8.0f;
        txt.setTextRise(superscript);
        txt.setUnderline(Color.black, 3.0f, 0.0f, -5.0f + superscript, 0.0f, PdfContentByte.LINE_CAP_ROUND);
        return document;

    }

    private static Document printTabla(Document document, List<OoCells> ooCellsList, Integer tip) throws DocumentException
    {
        Chunk f = new Chunk("    Lista de "+OpenossadData.stringNameMap.get(tip)+" ligados al gráfico:",FontFactory.getFont(FontFactory.TIMES_BOLD, 16));
        document.add(emptyParagraph);
        document.add(f);

        PdfPTable t4 = printTablaHeader();

        List<OoCells> subGroupCellsList = OpenossadData.getOneType(ooCellsList, tip);

        for (OoCells ooCell : subGroupCellsList) {
            t4.addCell(ooCell.getShName().toString());t4.addCell(ooCell.getShComment().toString());
        }

        document.add(t4);
        return document;
    }

    private static PdfPTable printTablaHeader()
    {
        PdfPCell cell;
        float[] widths = {0.3f, 0.6f};
        PdfPTable t4 = new PdfPTable(widths);
        t4.setWidthPercentage(95);
        cell = new PdfPCell(new Paragraph(new Chunk("Elemento", FontFactory.getFont(FontFactory.HELVETICA, 16))));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        t4.addCell(cell);
        cell = new PdfPCell(new Paragraph(new Chunk("Comentarios",FontFactory.getFont(FontFactory.HELVETICA, 16))));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        t4.addCell(cell);
        return t4;
    }

    private static Document printTabla(Document document, String tip, String query, Integer a1, Integer a2) throws DocumentException
    {
        // TODO Auto-generated method stub
        List<String[]> res= ReferencialOOSSAD.exeGetResultSQL(query);
        if (res==null) return document;


        Chunk f = new Chunk("    Lista de "+tip+" ligados al gráfico:",FontFactory.getFont(FontFactory.TIMES_BOLD, 16));
        document.add(emptyParagraph);
        document.add(f);
        PdfPTable t4 = printTablaHeader();

        for (int x=0;x<res.size();x++){
            Object[] row = res.get(x);
            t4.addCell(row[a1].toString());
            t4.addCell(row[a2].toString());
        }
        document.add(t4);
        return document;
    }
}
