package org.openossad.palette;

import com.mxgraph.examples.swing.editor.EditorPalette;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 12/10/11
 * Time: 12:28
 * To change this template use File | Settings | File Templates.
 */
public class WallpaperPalette extends OoMainPalette
{
    public WallpaperPalette(JTabbedPane libraryPane, mxGraph graph) {
        final EditorPalette palette = createPalette(libraryPane, graph, mxResources.get("Backgrouds"));
        // TODO: implement this function
        //palette.addBackground();

    }
}
