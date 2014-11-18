package org.openossad;

import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import com.mxgraph.examples.swing.editor.EditorPalette;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;
import org.openossad.data.domain.OoOptions;
import org.openossad.data.domain.Tblgraphs;
import org.openossad.palette.MarcoPalette;
import org.openossad.palette.OoMainPalette;
import org.openossad.palette.OoPaletteMetadata;
import org.openossad.util.helper.DAOOOSSAD;
import org.openossad.util.helper.ReferencialFileOOSSAD;
import org.openossad.util.helper.ReferencialGraphOOSSAD;
import org.openossad.util.helper.ReferencialOOSSAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 28/09/11
 * Time: 13:30
 * To change this template use File | Settings | File Templates.
 */
public class BasicGraphEditorOO extends BasicGraphEditor
{
    public DAOOOSSAD DAO = new DAOOOSSAD();
    public ReferencialOOSSAD referencialOssad = new ReferencialOOSSAD();
    public OoOptions tblOoptions;
    public Tblgraphs currentTblgraph;
    private Object currentGraphProjectID;
    private Integer currentGraphProjectIDIndex;


    private static BasicGraphEditorOO singleton;
    private OoMainPalette ooMainPalette = new OoMainPalette();

    static
    {
        try
        {
            mxResources.add("opendesigner");
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public JFrame createFrame(JMenuBar menuBar)
    {
        JFrame frame = new JFrame();
        frame.getContentPane().add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        frame.setSize(600, 500);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Updates the frame title
        updateTitle();

        return frame;
    }

    public BasicGraphEditorOO(String appTitle, CustomOpenDESIGNERGraphComponent component)
    {
        super(appTitle, component);
        singleton=this;
    }

    protected void installToolBar()
    {
        add(new ooEditorToolBar(this, JToolBar.HORIZONTAL), BorderLayout.NORTH);
    }



    @Override
    public void updateTitle() {
        /*
          String title = (currentFile != null)
                  ? currentFile.getAbsolutePath()
                  : mxResources.get("newDiagram");

          if (modified) {
              title += "*";
          }
          if (title.length() > 13) {
              title = title.substring((title.length() - 14), title.length());
              //title = (currentTblgraph.getGname().equals(null)) ? title : currentTblgraph.getGname();
              setTitle(appTitle + "["+ title + "]");
          }
          */
        String title;
        if (currentFile != null) {
//			if (tblOoptions.getDocumentoGuardarPorNombre()) title = currentTblgraph.getGname();
//			else title = currentTblgraph.getGid();
            title="dummy";
        }
        else {
            title= mxResources.get("newDiagram");
        }
        if (modified) {
            title += "*";
        }
//        JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);
//        frame.setTitle(appTitle + "["+ title + "]");
        status(title);

    }

    public void openossadToGraph(String file, Integer level, Integer orientation) throws IOException
    {
        currentTblgraph =DAO.getooGraphOBJ(referencialOssad.getGIDfromFile(file));
        currentTblgraph.setGlevel(level);
        currentTblgraph.setGorientation(orientation);
        doOpenGraph(currentTblgraph);
    }

    public void doOpenGraph(String selectedToOpen) throws IOException
    {
        Tblgraphs tblgraphs = DAO.getooGraphOBJ(selectedToOpen);
        currentTblgraph = tblgraphs;
        doOpenGraph(tblgraphs);
    }

    public void doOpenGraph(Tblgraphs tblgraphToOpen) throws IOException
    {
        currentTblgraph = tblgraphToOpen;
        Integer level= currentTblgraph.getGlevel();
        level = (level == null) ? 1 : level;
        Integer orientation= currentTblgraph.getGorientation();
        orientation = (orientation == null) ? 1 : orientation;

        if (level==4) {
            String myFile = ReferencialOOSSAD.getParsedModelDoc(currentTblgraph.getGid(),currentTblgraph.getGlinkDoc());
            ReferencialFileOOSSAD.OpenDocument(myFile);
            return;
        }

        final String lastIDFile = ReferencialOOSSAD.getFilePathFromGId(currentTblgraph.getGid());
        if (!new File(lastIDFile).exists()) {
            final String basicModelFromLevel = ReferencialOOSSAD.getBasicModelFromLevel(level);
            ReferencialOOSSAD.CreateGraphInREP(basicModelFromLevel, lastIDFile);
        }
        mxGraph graph = ReferencialGraphOOSSAD.chargeGraphInGraphComponent((CustomOpenDESIGNERGraphComponent) getGraphComponent(),lastIDFile);



        deleteAllPalette();

        if (level == 1 || level == 5) N1PPalette();
        if (level == 2 || level == 6) N2PPalette();
        if (level == 3 || level == 7) N3PPalette();

        if (level == 8 || level == 11) N1HPalette();
        if (level == 9 || level == 12) N2HPalette();
        if (level == 10 || level == 13) N3HPalette();

        new MarcoPalette(libraryPane,graph);

        PageFormat format = graphComponent.getPageFormat();
        format.setOrientation(orientation);
        graphComponent.setPageFormat(format);
        graphComponent.zoomAndCenter();
        setModified(false);
        final File file = new File(referencialOssad.getParsedModel(currentTblgraph.getGid()));
        setCurrentFile(file);


//        if (currentTblgraph.getGbgimagePath()!=null) {
//            java.net.URL imgURL = OpenDESIGNER.class.getResource(currentTblgraph.getGbgimagePath());
//            if (imgURL!=null) {
//                File Gbg = new File(imgURL.getPath());
//                if (!currentTblgraph.getGbgcolor().equals("")) {
//                    //graphComponent.setBackground(currentTblgraph.getGbgcolor());
//                }
//                else {
//                    graphComponent.setBackground(Color.white);
//                }
//                if (Gbg.exists()) {
//                    graphComponent.setBackgroundImage(new ImageIcon( imgURL, ""));
//                } else {
//                    graphComponent.setBackgroundImage(null);
//                }
//            }
//        }


    }



    /**
     * @return
     */
    public Object getCurrentGraphProjectID() {
        // TODO Auto-generated method stub
        return currentGraphProjectID;
    }

    public void setCurrentGraphProjectID(Object obj) {
        // TODO Auto-generated method stub
        currentGraphProjectID=obj;
    }
    public Integer getCurrentGraphProjectIDIndex() {
        // TODO Auto-generated method stub
        return currentGraphProjectIDIndex;
    }
    public void setCurrentGraphProjectIDIndex(Integer itemIndex) {
        // TODO Auto-generated method stub
        currentGraphProjectIDIndex=itemIndex;
    }




    public void deleteAllPalette() {
        libraryPane.removeAll();
    }

    /*
      * public void setOrientation(){ //graphComponent.setBackgroundImage(arg0);
      *
      *
      * }
      */
    public void PilotPalette(){
        EditorPalette pilotPalette = insertPalette("pilot");
        for (int x=1;x<=6;x++) {
            pilotPalette.addTemplate("", new ImageIcon(OpenDESIGNER.class.getResource("/ui/images/galery/ooP/"+x+".png")),
                                     "image;image=/ui/images/galery/ooP/"+x+".png", 32,
                                     32, "");
        }

    }
    public void OffimatikPalette(){
        EditorPalette OffimatikPalette = insertPalette("Offimatik");
        for (int x=1;x<=70;x++) {
            OffimatikPalette.addTemplate("", new ImageIcon(OpenDESIGNER.class.getResource("/ui/images/galery/ooD/"+x+".png")),
                                         "image;image=/ui/images/galery/ooD/"+x+".png", 32,
                                         32, "");
        }
    }
    public void BenchmarkPalette(){
        EditorPalette BenchmarkPalette = insertPalette("Benchmark");
        for (int x=1;x<=5;x++) {
            BenchmarkPalette.addTemplate("", new ImageIcon(OpenDESIGNER.class
                                                                   .getResource("/ui/images/galery/ooQ/"+x+".png")),
                                         "image;image=/ui/images/galery/ooQ/"+x+".png", 32,
                                         32, "");
        }
    }
    public void standartPalette() {
        PilotPalette();
        OffimatikPalette();
        BenchmarkPalette();
    }
    public void N1PPalette(){
        //mxResources.get("proces1");
        ooMainPalette.getOpenOssad_Palette(libraryPane, getGraphComponent().getGraph(), "Nivel P1", OoPaletteMetadata.getProcessLevel_1_Items());
    }
    public void N2PPalette(){
        ooMainPalette.getOpenOssad_Palette(libraryPane, getGraphComponent().getGraph(), "Nivel P2", OoPaletteMetadata.getProcessLevel_2_Items());
    }
    public void N3PPalette(){
        ooMainPalette.getOpenOssad_Palette(libraryPane, getGraphComponent().getGraph(), "Nivel P3", OoPaletteMetadata.getProcessLevel_3_Items());
    }
    public void N1HPalette() {
        ooMainPalette.getOpenOssad_Palette(libraryPane, getGraphComponent().getGraph(), "Nivel H1", OoPaletteMetadata.getHumanLevel_1_Items());
    }
    public void N2HPalette(){
        ooMainPalette.getOpenOssad_Palette(libraryPane, getGraphComponent().getGraph(), "Nivel H1", OoPaletteMetadata.getHumanLevel_2_Items());
    }
    public void N3HPalette(){
        ooMainPalette.getOpenOssad_Palette(libraryPane, getGraphComponent().getGraph(), "Nivel H1", OoPaletteMetadata.getHumanLevel_3_Items());
    }

    public static BasicGraphEditorOO get()
    {
        return singleton;  //To change body of created methods use File | Settings | File Templates.
    }

    public String getCurrentGID()
    {
       return  (currentTblgraph!=null) ? currentTblgraph.getGid() : "";
    }

    @Override
    protected void showGraphPopupMenu(MouseEvent e)
	{
		mxCell cell = (mxCell) graphComponent.getGraph().getSelectionCell();
        if (ReferencialGraphOOSSAD.isCellEditable(cell)) {
            Point pt = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(),graphComponent);
            ooEditorPopupMenu menu = new ooEditorPopupMenu(BasicGraphEditorOO.this);
            menu.show(graphComponent, pt.x, pt.y);
            e.consume();
        }
	}


    public void setGraphComponent(CustomOpenDESIGNERGraphComponent customOpenDESIGNERGraphComponent) {
        graphComponent=customOpenDESIGNERGraphComponent;
    }
}
//