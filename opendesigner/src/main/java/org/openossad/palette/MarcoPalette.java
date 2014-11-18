package org.openossad.palette;

import com.mxgraph.examples.swing.editor.EditorPalette;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.view.mxGraph;
import org.openossad.util.ImageUtil;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 12/10/11
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public class MarcoPalette extends OoMainPalette
{
    public MarcoPalette(JTabbedPane libraryPane, mxGraph graph) {
        final EditorPalette palette = createPalette(libraryPane, graph, "Marcos");
        final ImageIcon icon= ImageUtil.createImageIcon("/ui/images/rectangle.png");

        palette.addTemplate("Marco1",   icon,   getMarcoItem("marc1"));
        palette.addTemplate("Eliminar", icon,   getMarcoItem("marc0"));

    }

    private mxCell getMarcoItem(String style)
    {
        mxCell cell = new mxCell(null, new mxGeometry(0, 0,200,280), null);
        cell.setVertex(true);
        cell.setStyle(style);
        cell.setConnectable(false);
        cell.setId("marco");
        return cell;
    }

}
