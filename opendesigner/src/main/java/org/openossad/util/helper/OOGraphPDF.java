/**
 *
 */
package org.openossad.util.helper;

import com.lowagie.text.*;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxCellRenderer.CanvasFactory;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;
import org.openossad.CustomOpenDESIGNERGraph;
import org.openossad.CustomOpenDESIGNERGraphComponent;
import org.openossad.OpenDESIGNER;
import org.openossad.data.domain.Tblgraphs;
import org.openossad.ui.component.JXErrorDialog;
import org.openossad.ui.experimental.ooDrawMark;
import org.openossad.ui.ooExportPDFDlg;
import org.openossad.util.helper.pdf.PdfOOHelper;
import org.openossad.util.ooString;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

/**
 * OpenDESIGNER_2.0.5_2008
 * OOGraphPDF.java,19/10/2009,
 * Copyright (c) 2009,  Xavi Hidalgo
 *
 * //////////////////////////////////////////////////////
 * Este código es propiedad de TricomZone, S.L.
 * Sujeto a la licencia LGPL.
 *
 * //////////////////////////////////////////////////////
 *
 */

public class OOGraphPDF
{

    private Document document;
    private ReferencialOOSSAD roo = new ReferencialOOSSAD();
    private ReferencialGraphOOSSAD rgo = new ReferencialGraphOOSSAD();
    private static DAOOOSSAD DAO = new DAOOOSSAD();
    private CustomOpenDESIGNERGraphComponent customGraphComponent;
    private String filePdf;
    private Tblgraphs tblgraph;
    private mxGraph currentGraph;
    private PdfFormField[] pushbutton = new PdfFormField[100];
    private int idxCell = 0;
    private Paragraph br = new Paragraph(new Chunk("  ",FontFactory.getFont(FontFactory.HELVETICA, 14)));
    private HeaderFooter hf;
    private PdfWriter writer;
    private mxRectangle bounds;
    private mxGraphics2DCanvas canvas;
    private PdfContentByte cb;
    private float docH,docW;
    private Boolean noPrompt=false;

    public OOGraphPDF(){

    }
    public static void main(String[] args)
    {
        OOGraphPDF obj = new OOGraphPDF();
        obj.exportAllGraphs();
    }

    public void exportGraph(String gid){
        Boolean[] opt = new Boolean[10];
        opt[0]=true;
        opt[1]=true;
        opt[2]=true;
        opt[6]=true;
        opt[8]=true;

        buildReport("HTML",gid,opt);

    }

    public void exportAllGraphs(){
        int c=0;
        noPrompt=true;
        List<Tblgraphs> graphList = DAO.getAllGraphs();
        for (Iterator<Tblgraphs> it = graphList.iterator(); it.hasNext();) {
            c++;
            Tblgraphs graph = it.next();
            System.out.println("Procesando "+graph.getGid()+" ...");
            try {
                exportGraph(graph.getGid());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("Error en el "+graph.getGid());
                e.printStackTrace();
            }
            System.out.println("Proceso "+graph.getGid()+" terminado.");
        }

        System.out.println("_________________________________");
        System.out.println("FINAL: terminados "+c+" gráficos ");
        System.out.println("_________________________________");
    }
    public void buildReport(String filename, String gid,Boolean[] ops) {

        if (filename == null) return;
        if (filename.equals("")) { filename=ReferencialFileOOSSAD.getFileToSavePDF();}

        try {
            currentGraph=LoadGraph(gid);
            if (filename.equals("HTML")) {
                filename= filePdf;
            }
            bounds = currentGraph.getGraphBounds();
            document = new Document();
            writer = PdfWriter.getInstance(document,new FileOutputStream(filename));

            docH = document.getPageSize().getHeight();
            docW = document.getPageSize().getWidth();
            writer.setViewerPreferences(PdfWriter.PageModeUseThumbs);
            document.addTitle(tblgraph.getGname());
            document.addAuthor(tblgraph.getGauthor()+".");
            document.addSubject(tblgraph.getGdomain()+".");
            document.addKeywords(tblgraph.getGkeywords()+".");
            document.addCreator("OpenDESIGNER, calidad libre para todo el mundo");

            hf=new HeaderFooter(new Phrase(" "+ tblgraph.getGname()+"      - "),true);

            document.open();
            cb = writer.getDirectContent();

            ///////////////////////////////////////////////
            if (ops[0]) {
                grafico(ops);
            } else {
                portada();
                grafico(ops);
                if (ops[4]) detalle();
                if (ops[5]) propsPersonalizadas();
            }

            ///////////////////////////////////////////////

        } catch (IOException e) {
            JXErrorDialog.showDialog(null, "Error creando el documento pdf", e);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            JXErrorDialog.showDialog(null, "Error creando el documento pdf", e);
        } finally {
            if (!noPrompt) JOptionPane.showMessageDialog(
                    null,
                    "El documento...\n"+filename.substring(filename.lastIndexOf("/")+1,filename.length())+" ha sido creado. \n", "Repository:",
                    JOptionPane.INFORMATION_MESSAGE);

        }
        document.close();
    }

    private void propsPersonalizadas() {
        // TODO Auto-generated method stub
        try{
            document.newPage();
            document.add(t3());
            document.add(br);

            PdfPTable table = new PdfPTable(2);
            PdfPCell cell = new PdfPCell(new Paragraph(new Chunk("Campos personalizados",FontFactory.getFont(FontFactory.HELVETICA, 16))));
            cell.setColspan(2);
            cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
            table.addCell(cell);
            table.addCell("Campo personalizado 1");table.addCell(tblgraph.getGfield1());
            table.addCell("Campo personalizado 2");table.addCell(tblgraph.getGfield2());
            table.addCell("Campo personalizado 3");table.addCell(tblgraph.getGfield3());
            document.add(table);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            JXErrorDialog.showDialog(null, "Error creando el documento pdf", e);
        }
    }

    /**
     *
     */
    private void detalle() {
        try {
            document.setPageSize( new com.lowagie.text.Rectangle(docW,docH) );
            document.newPage();
            document.add(t3());
            document.add(br);
            //Graficos
            printTabla("procesos","SELECT * FROM ooCells WHERE shTypeId in (333,3,5,16,132,133,112,113,114) AND shGraphID='"+ tblgraph.getGid()+"'",3,5);
            //documentos
            printTabla("documentos","SELECT * FROM ooCells WHERE shTypeId in (8,144) AND shGraphID='"+ tblgraph.getGid()+"'",3,5);
            //roles
            printTabla("roles","SELECT * FROM ooCells WHERE shTypeId in (6,9,10,111,131,804,805,806,821,822,823) AND shGraphID='"+ tblgraph.getGid()+"'",3,5);
            //indicadores
            printTabla("indicadores","SELECT * FROM ooCells WHERE shTypeId in (137) AND shGraphID='"+ tblgraph.getGid()+"'",3,5);
            //Medios
            printTabla("medios","SELECT * FROM ooCells WHERE shTypeId in (7,14,145) AND shGraphID='"+ tblgraph.getGid()+"'",3,5);
            //Aprobadores
            printTabla("aprobadores","SELECT SID,Name,CompleteName,Description,IsGroup FROM tblaccounts " +
                                     "WHERE SID IN (SELECT CodeUser FROM ooDocFlow WHERE GraphID='"+ tblgraph.getGid()+"' AND Type=3  )",1,3);
            //Redactores
            printTabla("redactores","SELECT SID,Name,CompleteName,Description,IsGroup FROM tblaccounts " +
                                    "WHERE SID IN (SELECT CodeUser FROM ooDocFlow WHERE GraphID='"+ tblgraph.getGid()+"' AND Type=2)",1,3);
            //Lectores
            printTabla("lectores","SELECT SID,Name,CompleteName,Description,IsGroup FROM tblaccounts " +
                                  "WHERE SID IN (SELECT CodeUser FROM ooDocFlow WHERE GraphID='"+ tblgraph.getGid()+"' AND Type=1 )",1,3);

            Chunk txt = new Chunk(tblgraph.getGname(),FontFactory.getFont(FontFactory.HELVETICA, 35));
            float superscript = 8.0f;
            txt.setTextRise(superscript);
            txt.setUnderline(Color.black, 3.0f, 0.0f, -5.0f + superscript, 0.0f, PdfContentByte.LINE_CAP_ROUND);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            JXErrorDialog.showDialog(null, "Error creando el documento pdf", e);
        }
    }

    /**
     * @return
     */
    private PdfPTable t3() {
        float[] widths = {0.2f, 0.8f};
        PdfPTable t3 = new PdfPTable(widths);
        t3.setWidthPercentage(95);
        PdfPCell c5 = new PdfPCell(new Paragraph(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA, 30))));
        c5.setFixedHeight(50f);
        //t3.addCell(c5);
        try {
            t3.addCell(com.lowagie.text.Image.getInstance(ooExportPDFDlg.class.getResource("/ui/images/openossadlogo.png")));
        } catch (BadElementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t3.addCell(t2());
        return t3;

    }

    /**
     * @return
     */
    private PdfPTable t2() {
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

    /**
     * @param ops
     *
     */
    private void grafico(Boolean[] ops) {

        float scala = (float) ((tblgraph.getGorientation()==1) ? 0.72 : 0.56);
        scala=(float)1;
        document.setFooter(null);
        document.setHeader(null);
        document.setPageSize( new com.lowagie.text.Rectangle(new Rectangle((float)(bounds.getWidth()+bounds.getX()),(float)(bounds.getHeight()+bounds.getY()))) );
        document.newPage();
        document.setHeader(null);
        canvas = (mxGraphics2DCanvas) mxCellRenderer.drawCells(currentGraph, null, scala, null, new CanvasFactory()

        {
            public mxICanvas createCanvas(int width, int height)
            {
                Graphics2D g = cb.createGraphics(width, height);
                ooDrawMark oodm = new ooDrawMark();
                g=oodm.SimpleMark(g,width,height, tblgraph);
                return new mxGraphics2DCanvas(g);
            }
        });
        canvas.getGraphics().dispose();

        Tblgraphs tempG = tblgraph;
        if (ops[1]||(ops[6]&&ops[7])) addGadgets(ops);
        if (ops[1]||(ops[6]&&ops[8])) addGadgetsLinked(ops);
        tblgraph =tempG;

    }

    /**
     * @param ops
     */
    private void addDocsLinked(Boolean[] ops) {
        // TODO Auto-generated method stub

    }
    /**
     *
     */
    private void portada() throws IOException
    {
        // TODO Auto-generated method stub
        try {
            document = PdfOOHelper.insertPortadaIntoDocument(document, tblgraph);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            JXErrorDialog.showDialog(null, "Error creando el documento pdf", e);
        }
    }

    /**
     * @param ops
     *
     */
    private void addGadgetsLinked(Boolean[] ops) {
        int w=50;int h=50;
        float l=w>h?h:w;
        String comment;
        // Obtener gráficos enlazados
        try {

            for ( Object myObj : customGraphComponent.getCells(new java.awt.Rectangle(0,0,(int)bounds.getRectangle().getWidth(),(int)bounds.getRectangle().getHeight()))) {
                mxCell myCell= (mxCell) myObj;

                mxGeometry geo = myCell.getGeometry();
                int x=(int)(geo.getX()-20);
                int y=(int)(((bounds.getHeight()-geo.getY())-geo.getHeight()));
                comment=(ops[2]) ? rgo.getCellComment( tblgraph.getGid(),myCell.getId()) : "";
                if (!comment.equals("")) document.add(new Annotation(myCell.getValue().toString(), comment,x,y,x,y ));
                String idLinked= ooString.p(myCell.getId(), ":", 2);
                if (idLinked.length()>15) {
                    String tipCell = ooString.p(myCell.getStyle(),";");

                    if ((tipCell.equals("112") ||tipCell.equals("333") || tipCell.equals("16") || tipCell.equals("3"))&&ops[1]) {

                        currentGraph=LoadGraph(idLinked);
                        bounds =currentGraph.getGraphBounds();
                        document.setPageSize(new Rectangle((float)(bounds.getWidth()+bounds.getX()),(float)(bounds.getHeight()+bounds.getY())));

                        document.newPage();
                        document.add(new Chunk(idLinked,FontFactory.getFont(FontFactory.HELVETICA, 1)).setLocalDestination(idLinked));


                        PdfAppearance normal = cb.createAppearance(w,h);
                        normal.circle(w/2,h/2,l*(float)0.4);
                        normal.setColorFill(Color.gray);
                        normal.fill();

                        PdfAppearance roll = cb.createAppearance(w,h);
                        roll.circle(w/2,h/2,l*(float)0.4);
                        roll.setColorFill(Color.red);
                        roll.fill();

                        //normal.addImage(com.lowagie.text.Image.getInstance(new java.net.URL(pdfTest3.class.getResource("images/undo.gif"),"")));
                        //String img="/home/xavi/Escritorio/__OPEN_OSSAD__/OpenDESIGNER/compilations/2.0.3_RC1/developers/OpenDESIGNER-src/org/openossad/opendesigner/images/undo.gif";
                        //normal.addImage(com.lowagie.text.Image.getInstance("undo.gif"));


                        pushbutton[idxCell] = PdfFormField.createPushButton(writer);
                        pushbutton[idxCell].setAppearance(PdfAnnotation.APPEARANCE_NORMAL, normal);
                        pushbutton[idxCell].setAppearance(PdfAnnotation.APPEARANCE_ROLLOVER, roll);
                        pushbutton[idxCell].setFieldName("back"+idxCell);
                        pushbutton[idxCell].setWidget(new Rectangle(0,0,50,50), PdfAnnotation.HIGHLIGHT_PUSH);
                        pushbutton[idxCell].setAction(PdfAction.gotoLocalPage(1,new PdfDestination(PdfDestination.XYZ, -1, -1, 0),writer));
                        writer.addAnnotation(pushbutton[idxCell]);
                        idxCell++;

                        canvas=(mxGraphics2DCanvas)mxCellRenderer.drawCells(currentGraph,null,1,null,new CanvasFactory()
                        {
                            public mxICanvas createCanvas(int width, int height)
                            {
                                Graphics2D g = cb.createGraphics(width, height);
                                ooDrawMark oodm = new ooDrawMark();
                                g=oodm.SimpleMark(g,width,height, tblgraph);
                                return new mxGraphics2DCanvas(g);

                            }
                        });
                        canvas.getGraphics().dispose();

                    }
                    if (tipCell.equals("8")&&ops[6]&&ops[8]){
                        try {
                            tblgraph =DAO.getooGraphOBJ(idLinked);
                            String fileToEmbed=roo.getParsedModelDoc(idLinked);
                            String ext=fileToEmbed.substring(fileToEmbed.lastIndexOf(".")+1, fileToEmbed.length());
                            writer.addFileAttachment(tblgraph.getGdomain(), null,fileToEmbed , tblgraph.getGname()+"."+ext);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }


                    }
                }
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param ops
     *
     */
    private void addGadgets(Boolean[] ops) {
        String comment;


        for ( Object myObj : customGraphComponent.getCells(new java.awt.Rectangle(0,0,(int)bounds.getRectangle().getWidth(),(int)bounds.getRectangle().getHeight()))) {
            mxCell myCell= (mxCell) myObj;
            String idLinked=ooString.p(myCell.getId(),":",2);
            String tip=ooString.p(ooString.p(myCell.getId(),":",1),"^",2);

            mxGeometry geo = myCell.getGeometry();
            int x=(int)(geo.getX()-20);
            int y=(int)(((bounds.getHeight()-geo.getY())-geo.getHeight()));
            comment=(ops[2]) ? rgo.getCellComment( tblgraph.getGid(),myCell.getId()) : "";
            if (!comment.equals(""))
                try {
                    document.add(new Annotation(myCell.getValue().toString(), comment,x,y,x,y ));
                } catch (DocumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            if (idLinked.length()>15) {
                String tipCell = ooString.p(myCell.getStyle(),";");
                if ((tipCell.equals("112") ||tipCell.equals("333") || tipCell.equals("16") || tipCell.equals("3"))&&ops[1]) {
                    pushbutton[idxCell] = PdfFormField.createPushButton(writer);
                    pushbutton[idxCell].setFieldName("PushMe"+idxCell);
                    pushbutton[idxCell].setWidget(new Rectangle(x,y, (int)(x+geo.getWidth()), (int)(y+geo.getHeight())), PdfAnnotation.HIGHLIGHT_PUSH);
                    pushbutton[idxCell].setAction(PdfAction.gotoLocalPage(idLinked,false));

                    writer.addAnnotation(pushbutton[idxCell]);

                    idxCell++;
                }
                if (tipCell.equals("8")&&ops[6]&&ops[7]) {

                    pushbutton[idxCell] = PdfFormField.createPushButton(writer);
                    pushbutton[idxCell].setFieldName("PushMeDoc"+idxCell);
                    pushbutton[idxCell].setWidget(new Rectangle(x,y, (int)(x+geo.getWidth()), (int)(y+geo.getHeight())), PdfAnnotation.HIGHLIGHT_PUSH);
                    //pushbutton[idxCell].setAction(PdfAction.gotoLocalPage(idLinked,false));
                    pushbutton[idxCell].setAction(new PdfAction(roo.getParsedModelDoc(idLinked)));

                    writer.addAnnotation(pushbutton[idxCell]);

                    idxCell++;
                }
            }

        }
    }




    private void printTabla(String tip, String q,Integer a1,Integer a2) {
        // TODO Auto-generated method stub
        List<String[]> res=roo.exeGetResultSQL(q);
        if (res==null) return;

        try {

            Chunk f = new Chunk("    Lista de "+tip+" ligados al gráfico:",FontFactory.getFont(FontFactory.TIMES_BOLD, 16));
            //float superscript = 10.0f;
            //f.setTextRise(superscript);
            //f.setUnderline(new Color(0xC0, 0xC0, 0xC0), 3.0f, 0.0f, superscript, 0.0f, PdfContentByte.LINE_CAP_ROUND);
            document.add(br);
            document.add(f);

            PdfPCell cell;
            float[] widths = {0.3f, 0.6f};
            PdfPTable t4 = new PdfPTable(widths);
            t4.setWidthPercentage(95);
            cell = new PdfPCell(new Paragraph(new Chunk("Elemento",FontFactory.getFont(FontFactory.HELVETICA, 16))));
            cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
            t4.addCell(cell);
            cell = new PdfPCell(new Paragraph(new Chunk("Comentarios",FontFactory.getFont(FontFactory.HELVETICA, 16))));
            cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
            t4.addCell(cell);

            for (int x=0;x<res.size();x++){

                //t4.addCell(res[x][a1]);
                //t4.addCell(res[x][a2]);
                //for (int y=0;y<res[x].length;y++){ System.out.println(res[x][y]); }

            }
            document.add(t4);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    private mxGraph LoadGraph(String graphId) {
        customGraphComponent = new CustomOpenDESIGNERGraphComponent( new CustomOpenDESIGNERGraph() );
        mxGraph graph = customGraphComponent.getGraph();
        graph.getModel().beginUpdate();

        try
        {
            ReferencialOOSSAD referencialOOSSAD = new ReferencialOOSSAD();
            String FileGIDstr=referencialOOSSAD.getParsedModel(graphId);
            File myFILE = new File(FileGIDstr);
            if (!myFILE.exists()&&!noPrompt) {
                // throw new IOException("FileCopy: " +
                // "no such source file: " + fromFileName);
                JOptionPane
                        .showMessageDialog(
                                null,
                                "El Archivo no se encuentra, \nconsulte con su administrador de sistema. ",
                                "Error abriendo gráfico:",
                                JOptionPane.ERROR_MESSAGE);
                return null;
            }
            filePdf = referencialOOSSAD.getExportHTML(graphId);
            if (!filePdf.equals("")) filePdf = filePdf.substring(0, filePdf.length()-5)+".pdf";
            tblgraph = DAOOOSSAD.getooGraphOBJ(graphId);
            org.w3c.dom.Document document = mxUtils.parse(mxUtils.readFile(FileGIDstr));
            mxCodec codec = new mxCodec(document);
            codec.decode(document.getDocumentElement(),graph.getModel());



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            graph.getModel().endUpdate();
        }
        return graph;
    }

    public static class CustomGraphComponent extends mxGraphComponent {


        /**
         *
         * @param graph
         */
        public CustomGraphComponent(mxGraph graph) {
            super(graph);

            // Sets switches typically used in an editor
            setPageVisible(true);
            setGridVisible(false);
            setAntiAlias(false);
            setToolTips(true);

            getConnectionHandler().setCreateTarget(true);
            // Loads the defalt stylesheet from an external file
            mxCodec codec = new mxCodec();
            org.w3c.dom.Document doc = mxUtils.loadDocument(OpenDESIGNER.class.getResource("/opendesigner-style.xml").toString());
            codec.decode(doc.getDocumentElement(), graph.getStylesheet());

            // Sets the background to white
            getViewport().setOpaque(false);
            setBackground(Color.WHITE);


            this.setPageBorderColor(Color.BLACK);
            try {
                this.warningIcon=new ImageIcon(
                        new java.net.URL(OpenDESIGNER.class.getResource("/ui/images/pagesetup.gif"),"")
                );
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            /**
             *
             */

        }
    }

    /**
     * A graph that creates new edges from a given template edge.
     */
    public static class CustomGraph extends mxGraph {
        /* (non-Javadoc)
           * @see com.mxgraph.view.mxGraph#setCellsLocked(boolean)
           */


        /**
         * Holds the edge to be used as a template for inserting new edges.
         */

        protected Object edgeTemplate;

        /**
         * Custom graph that defines the alternate edge style to be used when
         * the middle control point of edges is double clicked (flipped).
         */
        public CustomGraph() {
            //setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.ElbowConnector;elbow=vertical");
            setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.EntityRelation;elbow=horizontal");

        }

        /**
         * Sets the edge template to be used to inserting edges.
         */
        public void setEdgeTemplate(Object template) {
            edgeTemplate = template;
        }

        /**
         * Prints out some useful information about the cell in the tooltip.
         */
        public String getToolTipForCell(Object cell) {


            //JFrame frame = (JFrame) SwingUtilities.windowForComponent((Component) this.getSelectionCell());
            //BasicGraphEditor graphFrame = (BasicGraphEditor) frame;
            //mxGraph graph = graphFrame.getGraphComponent().getGraph();
            mxCell celll= (mxCell) cell;

            //BasicGraphEditor graphFrame = (BasicGraphEditor) celll.getParent().getParent().getParent().getParent();
            mxGraph graph =(mxGraph) getModel().getParent(this);
            //String currentF=graphFrame.getCurrentFile().toString();

            String tip = "<html><STRONG>OpenDESIGNER</STRONG><br/>";
            tip += celll.getValue() + "<br/>";
            tip += "</html>";

            return tip;
        }

        /**
         * @return
         */


        public Object createEdge(Object parent, String id, Object value,
                                 Object source, Object target, String style) {
            if (edgeTemplate != null) {
                mxCell edge = (mxCell) cloneCells(new Object[]{edgeTemplate})[0];
                edge.setId(id);

                return edge;
            }

            return super.createEdge(parent, id, value, source, target, style);
        }

    }
}
