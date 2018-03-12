package org.openossad;

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import org.openossad.data.hsqldb.HypersonicManager;
import org.openossad.palette.MarcoPalette;
import org.openossad.palette.ProcessPalette;
import org.openossad.palette.WallpaperPalette;
import org.openossad.util.init.InitOpenossadData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 27/09/11
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class OpenDESIGNER extends BasicGraphEditorOO
{
    //static Logger logger = Logger.getLogger("OpenDESIGNER");

    public OpenDESIGNER()
    {
        this("OpenDESIGNER", new CustomOpenDESIGNERGraphComponent(new CustomOpenDESIGNERGraph()));
        //logger.info("entering applicacion");
    }
    public OpenDESIGNER(String appTitle, CustomOpenDESIGNERGraphComponent component)
    {
        super(appTitle, component);

        mxConstants.W3C_SHADOWCOLOR = "#D3D3D3";
        mxConstants.SHADOW_COLOR = Color.BLACK;
        mxConstants.SHADOW_OFFSETX = 5;
        mxConstants.SHADOW_OFFSETY = 5;

        JFrame frame = (JFrame) SwingUtilities.windowForComponent((Component) this);
        new SplashOpenOSSAD(frame, 2000);

        final mxGraph graph = graphComponent.getGraph();

        new MarcoPalette(libraryPane,graph);
        new ProcessPalette(libraryPane,graph);
        new WallpaperPalette(libraryPane,graph);

    }




    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            HypersonicManager hypersonicManager = new HypersonicManager();
            hypersonicManager.startHypersonicServer();

//            InitOpenossadData initOpenossadData = new InitOpenossadData();
//            initOpenossadData.init();
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
        OpenDESIGNER opendesigner = new OpenDESIGNER();
        opendesigner.createFrame(new ooEditorMenuBar(opendesigner)).setVisible(true);
    }

}
