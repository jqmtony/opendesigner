package org.openossad.util.helper.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;
import org.openossad.CustomOpenDESIGNERGraph;
import org.openossad.data.CellUserObject;
import org.openossad.data.domain.Tblgraphs;
import org.openossad.ui.experimental.ooDrawMark;
import org.openossad.util.helper.DAOOOSSAD;
import org.openossad.util.helper.ReferencialGraphOOSSAD;
import org.openossad.util.helper.ReferencialOOSSAD;

import java.awt.*;
import java.io.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 21/10/11
 * Time: 11:58
 * To change this template use File | Settings | File Templates.
 */
public class PdfLocalService
{
    private Document document;
    private PdfWriter writer;
    private mxRectangle bounds;
    private PdfContentByte pdfContentByte;
    private com.lowagie.text.Rectangle pageSize;

    boolean hasComments = true;
    boolean insertLinkedGraphs=true;
    boolean insertDocuments=true;

    int idxCell = 0;
    PdfFormField[] pushbutton = new PdfFormField[100];
    private Rectangle rectangleGraphBounds;


    public PdfLocalService()
    {
        document = new Document();
        float pageHeight = document.getPageSize().getHeight();
        float pageWidth = document.getPageSize().getWidth();
        pageSize = new Rectangle(pageWidth, pageHeight);
    }

    static {
        mxConstants.W3C_SHADOWCOLOR = "#D3D3D3";
        mxConstants.SHADOW_COLOR = Color.BLACK;
        mxConstants.SHADOW_OFFSETX = 5;
        mxConstants.SHADOW_OFFSETY = 5;
    }

    public FileInputStream createPdfFromMxGraph(CustomOpenDESIGNERGraph graph, String filePdf) throws IOException, DocumentException
    {
        bounds = graph.getGraphBounds();
        document=getFormatedDocument(document, getRectangleGraphBounds(graph), filePdf);

        insertGraphImageWithMarcIntoDocument(document, graph);

        document.close();

        return new FileInputStream(new File(filePdf));
    }

    public InputStream createFullPdfFromMxGraph(Tblgraphs tblgraphs, String filePdf, String workingFolder) throws DocumentException, IOException
    {
        CustomOpenDESIGNERGraph graph = ReferencialGraphOOSSAD.getGraphFromTblgraph(tblgraphs, workingFolder);
        document=getFormatedDocument(document, getRectangleGraphBounds(graph), filePdf);

        document = PdfOOHelper.insertPortadaIntoDocument(document, tblgraphs);
        document = insertGraphImageWithMarcIntoDocument(document, graph,tblgraphs);
        document = PdfOOHelper.insertDetailGraphIntoDocument(document, tblgraphs, pageSize);

        document.close();

        return new FileInputStream(new File(filePdf));
    }


    public Document getFormatedDocument(Document document, Rectangle bounds, String filePdf) throws FileNotFoundException, DocumentException
    {
        document.setFooter(null);
        document.setHeader(null);
        document.setPageSize(bounds);
        writer = PdfWriter.getInstance(document, new FileOutputStream(filePdf));
        writer.setViewerPreferences(PdfWriter.PageModeUseThumbs);
        document.open();
        pdfContentByte = writer.getDirectContent();
        return document;
    }

    private Document insertGraphImageWithMarcIntoDocument(Document document, CustomOpenDESIGNERGraph graph) throws IOException, DocumentException
    {
        Tblgraphs tblgraphs = new Tblgraphs("TRZ000-000000002","gname","gref",2,1,"projectId",new Date(),new Date(),new Date());
        return insertGraphImageWithMarcIntoDocument(document, graph, tblgraphs);
    }

    private Document insertGraphImageWithMarcIntoDocument(Document document, CustomOpenDESIGNERGraph graph, final Tblgraphs tblgraphs) throws DocumentException, IOException
    {
        document.newPage();
        graph = addMarcoToGraph(graph);
        pdfContentByte = ooDrawMark.createGraphImageIntoGraphic(tblgraphs, graph,pdfContentByte);

        document = addCommentsGraphToPage(document, graph, tblgraphs);
        document = addLinkedGraphToPage(document,graph,tblgraphs);

        return document;
    }

    public Document addCommentsGraphToPage(Document document, CustomOpenDESIGNERGraph graph, Tblgraphs tblgraph) throws DocumentException
    {
        Object parent = graph.getDefaultParent();
        for ( Object myObj : graph.getChildCells(parent)) {
            mxCell myCell= (mxCell) myObj;

            CellUserObject cellUserObject = new CellUserObject(myCell.getValue());

            mxGeometry geo = myCell.getGeometry();
            int x=0;
            int y=0;
            if (geo!=null) {
                x=(int)(geo.getX()-20);
                y=(int)(((graph.getGraphBounds().getHeight()-geo.getY())-geo.getHeight()));
            }

            final String comment = cellUserObject.getComment();
            if (!comment.equals("")&&hasComments)
            {
                document.add(new Annotation(cellUserObject.getValue(), comment, x, y, x, y));
            }
            final String relationId = cellUserObject.getRelationId();
            if (relationId.length()>15) {
                String tipCell = myCell.getStyle();
                if ((tipCell.equals("112") ||tipCell.equals("333") || tipCell.equals("16") || tipCell.equals("3"))&&insertLinkedGraphs) {
                    pushbutton[idxCell] = PdfFormField.createPushButton(writer);
                    pushbutton[idxCell].setFieldName("PushMe"+idxCell);
                    pushbutton[idxCell].setWidget(new Rectangle(x,y, (int)(x+geo.getWidth()), (int)(y+geo.getHeight())), PdfAnnotation.HIGHLIGHT_PUSH);
                    pushbutton[idxCell].setAction(PdfAction.gotoLocalPage(relationId, false));

                    writer.addAnnotation(pushbutton[idxCell]);

                    idxCell++;
                }
                if (tipCell.equals("8")&&insertDocuments) {

                    pushbutton[idxCell] = PdfFormField.createPushButton(writer);
                    pushbutton[idxCell].setFieldName("PushMeDoc"+idxCell);
                    pushbutton[idxCell].setWidget(new Rectangle(x,y, (int)(x+geo.getWidth()), (int)(y+geo.getHeight())), PdfAnnotation.HIGHLIGHT_PUSH);
                    //pushbutton[idxCell].setAction(PdfAction.gotoLocalPage(idLinked,false));
                    pushbutton[idxCell].setAction(new PdfAction(ReferencialOOSSAD.getParsedModelDoc(relationId)));

                    writer.addAnnotation(pushbutton[idxCell]);

                    idxCell++;
                }
            }

        }
        return document;
    }



    private CustomOpenDESIGNERGraph addMarcoToGraph(CustomOpenDESIGNERGraph graph)
    {
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        try
        {
            for ( Object myObj : graph.getChildCells(parent)) {
                mxCell myCell = (mxCell) myObj;
                String style = graph.getModel().getStyle(myCell);
                if (style!=null) {
                    if (style.equals("marc1")) {
                        graph.removeCells( new Object[]{myCell});
                    }
                }
            }
            mxCell cell = getMarcoCell();
            graph.addCell(cell,parent);
        }
        finally
        {
            graph.getModel().endUpdate();
        }
        return graph;
    }

    private mxCell getMarcoCell()
    {
        mxCell cell = new mxCell(null);
        cell.setVertex(true);
        cell.setStyle("marc1");
        cell.setConnectable(false);
        cell.setId("marco");
        cell.setCollapsed(true);
        return cell;
    }

    public Document addLinkedGraphToPage(Document document, CustomOpenDESIGNERGraph graph, Tblgraphs tblgraph) throws DocumentException, IOException
    {
        int w=50;int h=50;
        float l=w>h?h:w;

        Object parent = graph.getDefaultParent();
        for ( Object myObj : graph.getChildCells(parent)) {
            mxCell myCell= (mxCell) myObj;
            CellUserObject cellUserObject = new CellUserObject(myCell.getValue());

            mxGeometry geo = myCell.getGeometry();
            int x=0;
            int y=0;
            if (geo!=null) {
                x=(int)(geo.getX()-20);
                y=(int)(((graph.getGraphBounds().getHeight()-geo.getY())-geo.getHeight()));
            }

            String comment = cellUserObject.getComment();
            if (!comment.equals("")&&hasComments) document.add(new Annotation(myCell.getValue().toString(), comment,x,y,x,y ));
            String graphGId= cellUserObject.getRelationId();
            if (graphGId.length()>15) {
                String tipCell = myCell.getStyle();

                if ((tipCell.equals("112") ||tipCell.equals("333") || tipCell.equals("16") || tipCell.equals("3"))&&insertLinkedGraphs) {
                    CustomOpenDESIGNERGraph currentGraph = ReferencialGraphOOSSAD.getGraphFromTblgraph(graphGId, null);
                    if (currentGraph!=null) {
                        document.setPageSize(getRectangleGraphBounds(currentGraph));

                        document.newPage();
                        document.add(new Chunk(graphGId,FontFactory.getFont(FontFactory.HELVETICA, 1)).setLocalDestination(graphGId));


                        PdfAppearance normal = pdfContentByte.createAppearance(w,h);
                        normal.circle(w/2,h/2,l*(float)0.4);
                        normal.setColorFill(Color.gray);
                        normal.fill();

                        PdfAppearance roll = pdfContentByte.createAppearance(w,h);
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
                        final Tblgraphs finalTblgraph = tblgraph;

                        pdfContentByte = ooDrawMark.createGraphImageIntoGraphic(finalTblgraph, currentGraph,pdfContentByte);
                    }

                }
                if (tipCell.equals("8")&&insertDocuments){

                    tblgraph = DAOOOSSAD.getooGraphOBJ(graphGId);
                    String fileToEmbed=ReferencialOOSSAD.getParsedModelDoc(graphGId);
                    String ext=fileToEmbed.substring(fileToEmbed.lastIndexOf(".")+1, fileToEmbed.length());
                    writer.addFileAttachment(tblgraph.getGdomain(), null,fileToEmbed , tblgraph.getGname()+"."+ext);

                }
            }
        }

        return document;
    }

    public Rectangle getRectangleGraphBounds(mxGraph currentGraph)
    {
        mxRectangle graphBounds = currentGraph.getGraphBounds();
        return new Rectangle((float) (graphBounds.getWidth() + graphBounds.getX()), (float) (graphBounds.getHeight() + graphBounds.getY()));
    }


}
