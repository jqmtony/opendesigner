package org.openossad.palette;

import com.mxgraph.view.mxGraph;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 11/10/11
 * Time: 13:17
 * To change this template use File | Settings | File Templates.
 */
public class ProcessPalette extends OoMainPalette
{

    public ProcessPalette(JTabbedPane libraryPane, final mxGraph graph) {

        getOpenOssad_Palette(libraryPane, graph, "Nivel P1", OoPaletteMetadata.getProcessLevel_1_Items());
        getOpenOssad_Palette(libraryPane, graph, "Nivel P2", OoPaletteMetadata.getProcessLevel_2_Items());
        getOpenOssad_Palette(libraryPane, graph, "Nivel P3", OoPaletteMetadata.getProcessLevel_3_Items());

        getOpenOssad_Palette(libraryPane, graph, "Nivel H1", OoPaletteMetadata.getHumanLevel_1_Items());
        getOpenOssad_Palette(libraryPane, graph, "Nivel H2", OoPaletteMetadata.getHumanLevel_2_Items());
        getOpenOssad_Palette(libraryPane, graph, "Nivel H3", OoPaletteMetadata.getHumanLevel_3_Items());

    }

}
