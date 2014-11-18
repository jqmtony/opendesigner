package org.openossad.util.helper;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;
import org.openossad.BasicGraphEditorOO;
import org.openossad.CustomOpenDESIGNERGraph;
import org.openossad.CustomOpenDESIGNERGraphComponent;
import org.openossad.data.CellUserObject;
import org.openossad.data.domain.OoCells;
import org.openossad.data.domain.Tblgraphs;
import org.openossad.util.ooString;
import org.w3c.dom.Document;

import javax.swing.*;
import javax.swing.text.html.parser.Element;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReferencialGraphOOSSAD {


    public ReferencialGraphOOSSAD() {


    }

    public ReferencialGraphOOSSAD(Boolean noConn) {

    }

    public static String getGIDfromFile(String p){
        if (p.equals("")) return "";
        p=p.substring(p.lastIndexOf(".")-16,p.lastIndexOf("."));
        return p.substring(0,6)+"-"+p.substring(7,16);

    }

    public static CustomOpenDESIGNERGraph chargeGraphInGraphComponent(CustomOpenDESIGNERGraphComponent graphComponent, String filePath)
    {
        CustomOpenDESIGNERGraph graph = (CustomOpenDESIGNERGraph) graphComponent.getGraph();


        try {
            final String xml = mxUtils.readFile(filePath);
            if (xml.equals("")) return null;
            Document document = mxUtils.parseXml(xml);
            mxCodec codec = new mxCodec(document);
            codec.decode(document.getDocumentElement(), graph.getModel());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return graph;
    }

    public static void updateCellComment(String grapgId, String cellId, String Comment) {
        String updateSql="UPDATE ooCells SET shComment='"+Comment+"' WHERE shGraphId='"+grapgId+"' AND shNumber='"+ ooString.p(cellId, "^")+"'";
        ReferencialOOSSAD.exeUpdate(updateSql);

    }


    public static String getCellComment(String tblgraphId, String cellId) {
        String executeQuery = "SELECT shComment FROM ooCells WHERE shGraphId='"+tblgraphId+"' AND shNumber='"+ooString.p(cellId,"^")+"'";
        String cellComment=ReferencialOOSSAD.exeGetUniqueResultSQL(executeQuery);
        if (cellComment.isEmpty()) return "";
        return cellComment;
    }

    public static void getCells(CustomOpenDESIGNERGraphComponent graphComponent, String filename) {
        final String graphGid = getGIDfromFile(filename);
        DAOOOSSAD.deleteOoCellsObjOfGraph(graphGid);
        List<OoCells> ooCellsList = new ArrayList<OoCells>();

        for ( Object myObj : graphComponent.getCells(new Rectangle(0,0,1600,1600))) {
            mxCell myCell= (mxCell) myObj;
            if (myCell!=null) {
                OoCells ooCells = populateCell(graphGid, myCell);
                ooCellsList.add(ooCells);
            }
        }

        DAOOOSSAD.insertOoCellList(ooCellsList);
    }

    private static OoCells populateCell(String graphGid, mxCell myCell) {
        OoCells ooCells = new OoCells();
        ooCells.setShGraphId(graphGid);
        ooCells.setShFormId(ooString.p(myCell.getId(), "^", 2));
        ooCells.setShTypeId(ooString.p(myCell.getStyle(), ";"));
        ooCells.setShNumber(ooString.p(myCell.getId(), "^"));

        final Object value = myCell.getValue();
        CellUserObject cellUserObject;
        if (value instanceof Element) {
            cellUserObject = new CellUserObject(value);
        } else if (value instanceof String) {
            cellUserObject = new CellUserObject((String)value);
        } else {
            cellUserObject = new CellUserObject("");
        }

        ooCells.setShName(cellUserObject.getValue());
        ooCells.setShComment(cellUserObject.getComment());
        ooCells.setShRelatedId(cellUserObject.getRelationId());
        ooCells.setShRelatedName(cellUserObject.getRelationName());

        ooCells.setShCenterX(String.valueOf(myCell.getGeometry().getCenterX()));
        ooCells.setShCenterY(String.valueOf(myCell.getGeometry().getCenterY()));
        ooCells.setShWidth(String.valueOf(myCell.getGeometry().getWidth()));
        ooCells.setShHeight(String.valueOf(myCell.getGeometry().getHeight()));
        ooCells.setShChildCount(String.valueOf(myCell.getChildCount()));
        ooCells.setShStyle(myCell.getStyle());
        ooCells.setShParentId(myCell.getParent().getId());

        return ooCells;
    }

    String getCommentFromArray(String idCell,String[] Comments){
        String i1,c="";
        i1=ooString.p(idCell, "^");
        for (int x=0;x<Comments.length;x++){

            c=ooString.p(Comments[x], "|");
            if (c.equals(i1)) {
                return ooString.p(Comments[x], "|",2);
            }
            //System.out.println("comment:"+Comments[x]);
        }

        return "";
    }
    /**
     * Handles the writing into DB for the values of the cells.
     */

    public void getCells(mxGraphComponent graphComponent) { //

        for ( Object myObj : graphComponent.getCells(new Rectangle(0,0,900,1200))) {
            mxCell myCell= (mxCell) myObj;

            //System.out.println("index:--ID:--Value:--Style:");
            System.out.println("");
            System.out.print(myCell.getIndex(myCell)+"--");
            System.out.print(myCell.getId()+"--");
            System.out.print(myCell.getValue()+"--");
            System.out.print(myCell.getStyle()+"--");
            System.out.print(myCell.getGeometry().getCenterX()+"--");
            System.out.print(myCell.getGeometry().getCenterY()+"--");
            System.out.print(myCell.getGeometry().getHeight()+"--");
            System.out.print(myCell.getGeometry().getWidth()+"--");
            System.out.print(myCell.getChildCount()+"--");

        }



    }

    public void getCellsBelongsofRoles(mxGraphComponent graphComponent,Integer numRoles) { //
        /*
           * dependiendo del numero de roles, el rectangulo de donde coge cambia de tamaño.
           */

        for ( Object myObj : graphComponent.getCells(new Rectangle(0,0,1000,1200))) {
            mxCell myCell= (mxCell) myObj;
            System.out.println("--------------------------------");
            System.out.println("index: "+myCell.getIndex(myCell));
            System.out.println("ID:    "+myCell.getId());
            System.out.println("Value: "+myCell.getValue());
            System.out.println("Style: "+myCell.getStyle());
            //System.out.println("Style: "+myCell.get);;
        }



    }

    /**
     * @param graphComponent
     */
    public static void deleteRolesLines(mxGraphComponent graphComponent) {
        mxCell[] CellsRoles = new mxCell[20];
        Integer x=0;
        Integer GOrientation = graphComponent.getPageFormat().getOrientation();
        Integer width=(GOrientation==1) ? 1600 : 2000;
        Integer height=(GOrientation==1) ? 2000 : 1600;
        for ( Object myObj : graphComponent.getCells(new Rectangle(0,0,width,height))) {
            mxCell myCell= (mxCell) myObj;
            String style = myCell.getStyle();
            String tipCell="";
            if (style != null) {
                if (style.lastIndexOf(';') > 0) {
                    tipCell=style.substring(0,style.lastIndexOf(';'));
                } else {
                    tipCell=style;
                }
                if (tipCell.equals("sepRol")) {
                    graphComponent.getGraph().removeCells(new Object[]{myCell});
                }

            }

        }



    }



    public static List<mxCell> getCellsRoles(mxGraphComponent graphComponent) { //
        /*
           * dependiendo del numero de roles, el rectangulo de donde coge cambia de tamaño.
           */

        //TODO poner (instanceof) para asegurar que coje vertex o edge
        List<mxCell> mxCellList = new ArrayList<mxCell>();
        Integer GOrientation = graphComponent.getPageFormat().getOrientation();
        Integer width=(GOrientation==1) ? 1600 : 2000;
        Integer height=(GOrientation==1) ? 2000 : 1600;

        for ( Object myObj : graphComponent.getCells(new Rectangle(0,0,width,height))) {
            mxCell myCell= (mxCell) myObj;
            if (myCell!=null) {
                String style = myCell.getStyle();
                String tipCell="";
                if (style != null) {
                    if (style.lastIndexOf(';') > 0) {
                        tipCell=style.substring(0,style.lastIndexOf(';'));
                    } else {
                        tipCell=style;
                    }
                    if (tipCell.equals("10") || tipCell.equals("6") ||tipCell.equals("9")) {
                        mxCellList.add(myCell);
                    }
                }
            }
        }
        return mxCellList;
    }




    public Integer Login(String u, String p) {
//		connQuery = "SELECT Type FROM tblaccounts WHERE Name = '" + u
//				+ "' AND Password='" + p + "' AND IsGroup=0 ";
        String wek = "";
//		try {
//			if (conn.isClosed()) {
//				Class.forName(connDriverClass).newInstance();
//				conn = DriverManager.getConnection(connString, connUser,
//						connPassword);
//			}
//			stmt = conn.createStatement();
//
//			ResultSet res = stmt.executeQuery(connQuery);
//			while (res.next()) {
//				wek = res.getObject(1).toString();
//			}
//			res.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//		if (wek.equals(""))
//			return 0;
        return Integer.parseInt(wek);
    }


    /**
     * @return
     */
    public static String getTipCell(BasicGraphEditorOO graphFrame) {
        mxGraph graph = graphFrame.getGraphComponent().getGraph();
        String style = graph.getModel().getStyle(graph.getSelectionCell());
        String tipCell="";
        if (style==null) return "";
        if (style.lastIndexOf(';') > 0) {
            tipCell=style.substring(0,style.lastIndexOf(';'));
            style = style.substring(style.lastIndexOf(';')+1,style.length());
        } else {
            tipCell=style;
        }
        return tipCell;
    }


    public static Boolean getisCellToLink(BasicGraphEditorOO graphFrame) {
        mxGraph graph = graphFrame.getGraphComponent().getGraph();
        mxCell mxCell = (mxCell) graph.getSelectionCell();
        if (mxCell==null) return false;
        final Object value = mxCell.getValue();
        CellUserObject cellUserObject = new CellUserObject(value);
        if (!cellUserObject.getRelationId().equals("")) return true;
        return false;
    }

    public void deleteGraphFromGraph(CustomOpenDESIGNERGraphComponent graphComp) {
        // TODO conseguir una sola función que borre un ID seleccionado
        String style;
        String id;
        mxGraph graph;
        graphComp.getGraph().getModel().beginUpdate();
        for ( Object myObj : graphComp.getCells(new Rectangle(0,0,2000,2000))) {
            mxCell myCell = (mxCell) myObj;
            style = graphComp.getGraph().getModel().getStyle(myCell);
            graph = graphComp.getGraph();
            if (style!=null) {
                if (style.equals("marc1")) {
                    graph.removeCells( new Object[]{myCell});
                }
            }

        }
        graphComp.getGraph().getModel().endUpdate();
    }

    public boolean graphHasMarco(mxGraphComponent graphComp) {
        // TODO conseguir una sola función que borre un ID seleccionado
        String style;
        String id;
        mxGraph graph;
        Boolean graphHasMarco = false;
        for ( Object myObj : graphComp.getCells(new Rectangle(0,0,1500,1500))) {
            mxCell myCell = (mxCell) myObj;
            if (myCell==null){
                style = graphComp.getGraph().getModel().getStyle(myCell);
                if (style.equals("marc1")) {
                    return true;
                }
            }



        }
        return graphHasMarco;
    }

    public static boolean graphHasMarco(CustomOpenDESIGNERGraph graph) {
        Boolean graphHasMarco = false;
        for ( Object myObj : graph.getChildCells(graph)) {
            mxCell myCell = (mxCell) myObj;
            if (myCell==null){
                String style = graph.getModel().getStyle(myCell);
                if (style.equals("marc1")) {
                    return true;
                }
            }
        }
        return graphHasMarco;
    }


    public static mxGraph alignRolesInOoGraph(CustomOpenDESIGNERGraphComponent graphComponent, List<mxCell> cellsRolesList)
    {
        mxGraph graph = graphComponent.getGraph();
        Integer GOrientation = graphComponent.getPageFormat().getOrientation();
        Integer width=(GOrientation==1) ? 860 : 1110;
        Integer height=(GOrientation==1) ? 1110 : 860;
        Integer top=100;
        if (graph != null) {

            int numRoles = cellsRolesList.size();

            Integer spaces=width/(numRoles);
            Integer midSpace=spaces/numRoles;
            Integer barsGap=(width/(numRoles));

            Integer cont=1;
            graph.getModel().beginUpdate();
            deleteRolesLines(graphComponent);
            for (mxCell myCell : cellsRolesList) {
                if (myCell!=null){
                    myCell.getGeometry().setX(0);myCell.getGeometry().setY(0);
                    graph.moveCells(new Object[]{myCell}, midSpace,top);

                    mxGeometry cGeo=new mxGeometry(0, 0, 1, (height-(top+100)));
                    cGeo.setX( barsGap*cont);
                    cGeo.setY((top+50));
                    cGeo.setAlternateBounds(new mxRectangle(0,0,0,0));

                    mxCell cLine = new mxCell("",cGeo,"sepRol");
                    cLine.setVertex(true);
                    cLine.setConnectable(false);

                    if (cont<numRoles ) graph.addCell(cLine);

                    midSpace=midSpace+spaces;
                    cont++;

                }

            }

            graph.getModel().endUpdate();
        }
        return graph;
    }

    public static mxGraph addRolesToGraph(mxGraph graph, int numRolesToCreate)
    {
        for (int x=1;x<= numRolesToCreate;x++) {
            graph.getModel().beginUpdate();
            mxGeometry cGeo=new mxGeometry(0, 0, 100, 40);
            cGeo.setX(100);cGeo.setY(100);
            mxCell cLine = new mxCell("Rol Interno",cGeo,"10");
            cLine.setVertex(true);
            graph.addCell(cLine);
            graph.getModel().endUpdate();
        }
        return graph;
    }

    public static int promptUser_getNumRolesToCreate()
    {
        final String message = "No existen roles en el gráfico." +
                               "\nPuede especificar el número de roles para añadirlos automáticamente" +
                               "\no puede cancelar para volver al gráfico." +
                               "\nPodrá elegir el tipo de rol después en el gráfico." +
                               "\nSe recomienda utilizar esta función solamente en gráficos de nivel 2." +
                               "\n";
        final String initialSelectionValue = "3";
        String NewString = JOptionPane.showInputDialog(message, initialSelectionValue);
        if (NewString == null ) return 0;
        int numRoles;
        try{
            numRoles=Integer.parseInt(NewString);
        } catch (Exception e1){
            return 0;
        }
        if (numRoles>10) {
            JOptionPane.showMessageDialog(
                    null,
                    "No está permitido crear más de 10 Roles.",
                    "Error de redacción",
                    JOptionPane.ERROR_MESSAGE);
            return 10;
        }
        if (numRoles==1) {
            JOptionPane.showMessageDialog(
                    null,
                    "Un gráfico con un sólo rol es una intrucción de trabajo.",
                    "Error de redacción",
                    JOptionPane.ERROR_MESSAGE);
            return 2;

        }
        return numRoles;



    }

    public static CustomOpenDESIGNERGraph getGraphFromTblgraph(Tblgraphs tblgraphs, String folder)
    {
        return getGraphFromTblgraph(tblgraphs.getGid(), folder);
    }

    public static CustomOpenDESIGNERGraph getGraphFromTblgraph(String graphGId, String folder)
    {
        String graphFilePath = ReferencialOOSSAD.getFilePathFromGId(graphGId,folder);
        CustomOpenDESIGNERGraphComponent graphComponent = new CustomOpenDESIGNERGraphComponent(new CustomOpenDESIGNERGraph());
        CustomOpenDESIGNERGraph graph = ReferencialGraphOOSSAD.chargeGraphInGraphComponent(graphComponent, graphFilePath);
        return graph;
    }

    public static boolean isCellEditable(mxCell cell)
    {

        if (cell == null){
            return false;
        }

        if (cell.getStyle()==null){
            return false;
        }

        String style = cell.getStyle();
        if (style.equals("sepRol") || style.equals("marc1")) return false;
        return true;
    }
}
