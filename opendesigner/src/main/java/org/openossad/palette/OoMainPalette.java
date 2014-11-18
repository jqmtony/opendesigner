package org.openossad.palette;

import com.mxgraph.examples.swing.GraphEditor;
import com.mxgraph.examples.swing.editor.EditorPalette;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.view.mxGraph;
import org.openossad.CustomOpenDESIGNERGraph;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 12/10/11
 * Time: 12:12
 * To change this template use File | Settings | File Templates.
 */
public class OoMainPalette
{


    public void getOpenOssad_Palette(JTabbedPane libraryPane, final mxGraph graph, String title, List<OoPaletteMetadata> ooPaletteMetadataList)
    {
        final EditorPalette palette = createPalette(libraryPane, graph, title);
        addTemplates(palette,ooPaletteMetadataList);
    }

    protected EditorPalette createPalette(JTabbedPane libraryPane, final mxGraph graph, String title)
    {
        final EditorPalette palette = new EditorPalette();
        final JScrollPane scrollPane = new JScrollPane(palette);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        libraryPane.add(title, scrollPane);
        libraryPane.addComponentListener(new ComponentAdapter()
        {

            public void componentResized(ComponentEvent e)
            {
                int w = scrollPane.getWidth() - scrollPane.getVerticalScrollBar().getWidth();
                palette.setPreferredWidth(w);
            }

        });
        final mxEventSource.mxIEventListener listener = new mxEventSource.mxIEventListener()
        {
            public void invoke(Object sender, mxEventObject evt)
            {
                Object tmp = evt.getProperty("transferable");
                if(tmp instanceof mxGraphTransferable)
                {
                    mxGraphTransferable t = (mxGraphTransferable) tmp;
                    Object cell = t.getCells()[0];
                    if(graph.getModel().isEdge(cell))
                    {
                        ((CustomOpenDESIGNERGraph) graph).setEdgeTemplate(cell);
                    }
                }
            }

        };
        palette.addListener(mxEvent.SELECT, listener);
        palette.setPreferredWidth(palette.getWidth());
        return palette;
    }

    private void addTemplates(EditorPalette palette, List<OoPaletteMetadata> ooPaletteMetadataList)
    {
        for (OoPaletteMetadata metadata : ooPaletteMetadataList) {
            addTemplates(palette,metadata.name, metadata.iconPath, metadata.style, metadata.width, metadata.height, metadata.value);
        }
    }

    private void addTemplates(EditorPalette palette,String name, String iconPath, String style, int width, int height, String value)
    {
        ImageIcon icon = new ImageIcon(GraphEditor.class.getResource(iconPath));
        palette.addTemplate(name, icon, style, width, height, value);
    }
}
