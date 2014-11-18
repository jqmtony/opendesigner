package org.openossad;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;
import org.openossad.data.CellUserObject;
import org.openossad.util.helper.ReferencialGraphOOSSAD;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.text.NumberFormat;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 27/09/11
 * Time: 13:51
 * To change this template use File | Settings | File Templates.
 */

public class CustomOpenDESIGNERGraph extends mxGraph
{
    /**
     * Holds the edge to be used as a template for inserting new edges.
     */
    protected Object edgeTemplate;
    public static final NumberFormat numberFormat = NumberFormat.getInstance();

    /**
     * Custom graph that defines the alternate edge style to be used when
     * the middle control point of edges is double clicked (flipped).
     */
    public CustomOpenDESIGNERGraph()
    {
        setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.elbowConnector;elbow=horizontal");
    }

    // Overrides method to disallow edge label editing
    public boolean isCellEditable(Object cell)
    {
        return ReferencialGraphOOSSAD.isCellEditable((mxCell) cell);
    }



    // Overrides method to provide a cell label in the display
    public String convertValueToString(Object cell)
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

                    if (comment != null && comment.length() > 0)
                    {

                    }

                    return caption;
                }
                else if (elt.getTagName().equalsIgnoreCase("other"))
                {
                    return elt.getTagName() + " (Since " + elt.getAttribute("since") + ")";
                }

            }
        }

        return super.convertValueToString(cell);
    }

    // Overrides method to store a cell label in the model
    public void cellLabelChanged(Object cell, Object newValue,boolean autoSize)
    {
        if (cell instanceof mxCell && newValue != null)
        {
            Object value = ((mxCell) cell).getValue();

            if (value instanceof Node)
            {
                String label = newValue.toString();
                Element elt = (Element) value;

                if (elt.getTagName().equalsIgnoreCase("ooCellObject"))
                {
                    String caption = label;
                    String comment = elt.getAttribute("comment");
                    String relationId = elt.getAttribute("relationId");
                    String relationName = elt.getAttribute("relationName");

                    // Clones the value for correct undo/redo
                    elt = (Element) elt.cloneNode(true);

                    elt.setAttribute("caption", caption);
                    elt.setAttribute("comment", comment);
                    elt.setAttribute("relationId", relationId);
                    elt.setAttribute("relationName", relationName);

                    CellUserObject cellUserObject = new CellUserObject(caption);
                    cellUserObject.setComment(comment);
                    cellUserObject.setRelationId(relationId);
                    cellUserObject.setRelationName(relationName);
                    newValue = cellUserObject.getUserObject();
                }
            }
        }

        super.cellLabelChanged(cell, newValue, autoSize);
    }

    /**
     * Sets the edge template to be used to inserting edges.
     */
    public void setEdgeTemplate(Object template)
    {
        edgeTemplate = template;
    }

    /**
     * Prints out some useful information about the cell in the tooltip.
     */
    public String getToolTipForCell(Object cell)
    {
        mxCell mxCell = (mxCell) cell;

        StringBuilder tip = new StringBuilder("<html>");
        if (getModel().isEdge(cell) || mxCell.getStyle().equals("sepRol"))
        {

        }
        else
        {
            CellUserObject cellUserObject = new CellUserObject(mxCell.getValue());
            tip.append("<p><b>Comments&nbsp;:</b>&nbsp;").append(cellUserObject.getComment()).append("</p>");
            tip.append("<p><b>Related Id&nbsp;:</b>&nbsp;").append(cellUserObject.getRelationId()).append("</p>");
            tip.append("<p><b>Related Name&nbsp;:</b>&nbsp;").append(cellUserObject.getRelationName()).append("</p>");
        }

        tip.append("</html>");

        return tip.toString();
    }


    public Object createEdge(Object parent, String id, Object value,Object source, Object target, String style)
    {
        if (edgeTemplate != null)
        {
            mxCell edge = (mxCell) cloneCells(new Object[] { edgeTemplate })[0];
            edge.setId(id);

            return edge;
        }

        return super.createEdge(parent, id, value, source, target, style);
    }


}
