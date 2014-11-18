package org.openossad;

import com.lowagie.text.Cell;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;
import org.openossad.util.draw.DrawRectPanel;
import org.openossad.util.helper.ReferencialGraphOOSSAD;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;
import java.util.EventObject;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 3/10/11
 * Time: 12:06
 * To change this template use File | Settings | File Templates.
 */
public class CustomOpenDESIGNERGraphComponent extends mxGraphComponent
{
    public CustomOpenDESIGNERGraphComponent(CustomOpenDESIGNERGraph customOpenDESIGNERGraph) {
        super(customOpenDESIGNERGraph);
        setPageVisible(true);
        setGridVisible(false);
        setToolTips(true);
        setAntiAlias(false);
        getConnectionHandler().setCreateTarget(true);


        // Loads the defalt stylesheet from an external file
        mxCodec codec = new mxCodec();
        final String uri = CustomOpenDESIGNERGraphComponent.class.getResource("/opendesigner-style.xml").toString();
        Document doc = mxUtils.loadDocument(uri);
        codec.decode(doc.getDocumentElement(), graph.getStylesheet());

        // Sets the background to white
        getViewport().setOpaque(true);
        getViewport().setBackground(Color.WHITE);
        setEnterStopsCellEditing(true);
    }

    public void setWarning(Object[] objects, String message)
    {


    }

    public mxGraph getGraph()
    {
        return graph;
    }

    public Component[] createComponents(mxCellState state) {
        if (getGraph().getModel().isVertex(state.getCell())) {
            mxCell currentCell = (mxCell) state.getCell();
            String value = getGraph().getModel().getStyle(currentCell).toString();

            if (value.equals("marc1")) {
                //esto hace que no se pueda seleccionar !
                state.setAbsoluteTerminalPoint(new mxPoint(1,1), true);
                state.setCell(new Cell());
                return new Component[]{new DrawRectPanel(state.getCell(),this)};
            }
            return null;
        }

        return null;
    }


    /**
     * Overrides drop behaviour to set the cell style if the target is not a
     * valid drop target and the cells are of the same type (eg. both
     * vertices or both edges).
     */
    public Object[] importCells(Object[] cells, double dx, double dy, Object target, Point location) {
        Boolean isTarget=false;
        String tipCell1 = "";
        String tipCell2 = "";

        mxCell myCell = (mxCell) cells[0];
        if (myCell.getStyle().equals("marc1")) {
            mxGeometry geo = new mxGeometry();
            int w=(int) ((this.getPageFormat().getWidth()*this.getPageScale())-40);
            int h=(int) ((this.getPageFormat().getHeight()*this.getPageScale())-40);
            geo.setWidth(w);
            geo.setHeight(h);
            myCell.setGeometry(geo);
            cells[0]=myCell;
            return super.importCells(cells, 20, 20, null, location);
        }
        if (myCell.getStyle().equals("marc0")) {
            //cells[0];
            ReferencialGraphOOSSAD rgo = new ReferencialGraphOOSSAD();
            rgo.deleteGraphFromGraph(this);
            return null;
        }

        ReferencialGraphOOSSAD rgo = new ReferencialGraphOOSSAD(true);





        if (!graph.isSelectionEmpty()) {
            target = (mxCell) graph.getSelectionCell();
            tipCell1 = ((mxCell) target).getStyle();
            tipCell2 = ((mxCell) cells[0]).getStyle();

            if (tipCell1=="333" && tipCell2=="16") isTarget=true;//subprocesos nivel2
            if (tipCell1=="333" && tipCell2=="3") isTarget=true;//subprocesos nivel2
            if (tipCell1=="112" && (tipCell2=="113"||tipCell2=="114")) isTarget=true;//subprocesos nivel1
            if (tipCell1=="132" && (tipCell2=="133")) isTarget=true;//macroprocesos nivel 3
            if (tipCell1=="801" && (tipCell2=="802")) isTarget=true;
            if ((tipCell1=="804" || tipCell1=="805" || tipCell1=="806") && (tipCell2=="808")) isTarget=true;

            //  if (tipCell1=="333" || tipCell1=="112" || tipCell1=="132" || tipCell1=="801"
            //	|| tipCell1=="804" || tipCell1=="805" || tipCell1=="806") isTarget=true;
        }


        //TODO: hacer que location sea en medio de la columna si el rol es cabecera

        if (!isTarget && graph.isSelectionEmpty()) {
            target = null; //target = getCellAt(location.x, location.y);
            super.importCells(cells, dx, dy, target, location);
            graph.setSelectionCell(this.getCellAt(location.x, location.y));
            return null;
        }

        if (!isTarget && !graph.isSelectionEmpty()) {

            mxICell targetCell = (mxICell) target;
            mxICell dropCell = (mxICell) cells[0];

            super.importCells(new Object[]{dropCell}, dx, dy, null, location);
            graph.setSelectionCell(this.getCellAt(location.x, location.y));
            graph.insertEdge(graph.getDefaultParent(), null, null, targetCell,graph.getSelectionCell() );

            return null;

        }


        if (isTarget && target == null && cells.length == 1 && location != null) {

            if (target instanceof mxICell && cells[0] instanceof mxICell) {

                mxICell targetCell = (mxICell) target;
                mxICell dropCell = (mxICell) cells[0];

                if (targetCell.isVertex() == dropCell.isVertex() || targetCell.isEdge() == dropCell.isEdge()) {
                    if (targetCell.getChildCount()==0) {
                        graph.addCell( dropCell, targetCell );
                        return null;

                    }
                    else  {
                    }
                }
                return null;
            }



        }
        return super.importCells(cells, dx, dy, target, location);
    }


    public String getEditingValue(Object cell, EventObject trigger)
    {
        if (cell instanceof mxCell)
        {
            Object value = ((mxCell) cell).getValue();

            if (value instanceof Element)
            {
                Element elt = (Element) value;

                if (elt.getTagName().equalsIgnoreCase("ooCellObject"))
                {
                    String caption = elt.getAttribute("value");
                    String comment = elt.getAttribute("comment");

                    return caption;
                }
            }
        }

        return super.getEditingValue(cell, trigger);
    };

}
