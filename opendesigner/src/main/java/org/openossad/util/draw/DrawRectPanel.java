/**
 *
 */
package org.openossad.util.draw;

import com.mxgraph.view.mxGraph;
import org.openossad.BasicGraphEditorOO;
import org.openossad.CustomOpenDESIGNERGraphComponent;
import org.openossad.data.domain.Tblgraphs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;

public class DrawRectPanel extends JPanel {

    /**
     *
     */
    protected Object cell;

    /**
     *
     */
    protected CustomOpenDESIGNERGraphComponent graphContainer;

    /**
     *
     */
    protected mxGraph graph;
    protected ImageIcon imageIcon,imageIcon2;
    protected Image image,image2 = null;
    protected String DATA;


    public DrawRectPanel(final Object cell,final CustomOpenDESIGNERGraphComponent graphContainer) {
        /*
          try {
              imageIcon = new ImageIcon(new java.net.URL(DrawRectPanel.class.getResource("resources/sgc_300.png"), ""));
              image = imageIcon.getImage();

          } catch (MalformedURLException e) {
              // TODO Auto-generated catch block
              //e.printStackTrace();
              JXErrorDialog.showDialog(null, "No se puede cargar la imágen de fondo", e);
          }
          */
        this.cell = cell;
        this.graphContainer = graphContainer;
        this.graph = graphContainer.getGraph();
        this.setOpaque(false);
        this.setSize(graphContainer.getSize());
        setLayout(null);

        //System.out.println(go.getCurrentGID());




        /*
          setLayout(new BorderLayout());
          setBorder(BorderFactory.createCompoundBorder(ShadowBorder
                  .getSharedInstance(), BorderFactory
                  .createBevelBorder(BevelBorder.RAISED)));
          */
    }

    public DrawRectPanel() {
        try {
            //imageIcon = new ImageIcon(new java.net.URL(DrawRectPanel.class.getResource("resources/openossadlogo.png"), ""));
            imageIcon = new ImageIcon(new java.net.URL(DrawRectPanel.class.getResource("/logoopenossad2.jpg"), ""));
            image = imageIcon.getImage();

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //JXErrorDialog.showDialog(null, "No se puede cargar la imágen de fondo", e);
        }
        this.setOpaque(false);
        this.setSize(400,600);
        setLayout(null);


        /*
          setLayout(new BorderLayout());
          setBorder(BorderFactory.createCompoundBorder(ShadowBorder
                  .getSharedInstance(), BorderFactory
                  .createBevelBorder(BevelBorder.RAISED)));
          */
    }


    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        super.paintComponent(g);
        g.setColor(Color.black);
        //g.drawRect(10, 10, 80, 30);
        //g.drawRoundRect(100, 10, 80, 30, 15, 15);
        /*
        int thickness = 2;
        for (int i = 0; i <= thickness; i++)
            g.draw3DRect(thickness - i, thickness - i, this.getWidth()-(thickness+3) + 2 * i, this.getHeight()-(thickness+3) + 2 * i, true);
        */

//    BasicGraphEditorOO basicGraphEditorOO = (BasicGraphEditorOO) graphContainer.getParent().getParent().getParent().getParent().getParent();
//
//	ReferencialOOSSAD referencialOOSSAD=new ReferencialOOSSAD();
//	String graphId = referencialOOSSAD.getGIDfromFile(basicGraphEditorOO.getCurrentGID());
//	DATA=referencialOOSSAD.getData4Marco(graphId);
        BasicGraphEditorOO basicGraphEditorOO = BasicGraphEditorOO.get();
        Tblgraphs tblgraphs = basicGraphEditorOO.currentTblgraph;

        Integer cajaAlto = 7;
        //g.setFont(font);
        g.setFont(new Font("sansserif", Font.BOLD, (this.getHeight()/100)*2));
        g.drawString(tblgraphs.getGname(), (this.getWidth()/100)*20, (this.getHeight()/100)*6);

        g.setFont(new Font("sansserif", Font.BOLD, (int) ((this.getHeight()/100)*1.5)));
        g.drawString("Versión: "+tblgraphs.getGversionNumber(), (this.getWidth()/100)*16, (this.getHeight()/100)*2);
        g.drawString("Ref: "+tblgraphs.getGref(), (this.getWidth()/100)*35, (this.getHeight()/100)*2);
        g.drawString("Autor: "+tblgraphs.getGauthor(), (this.getWidth()/100)*57, (this.getHeight()/100)*2);

        g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
        g.drawRect(0, 0, this.getWidth()-1, (this.getHeight()/100)*cajaAlto);
        //g.drawRect((this.getWidth()/100)*14, 0, this.getWidth()/100-1, (this.getHeight()/100)*cajaAlto);

        g.drawRect((this.getWidth()/100)*15, 0, (this.getWidth()/100)*65, (this.getHeight()/100)*cajaAlto);
        g.drawRect((this.getWidth()/100)*15, 0, (this.getWidth()/100)*65, (this.getHeight()/100)*(cajaAlto/2));
        g.drawRect((this.getWidth()/100)*34, 0, (this.getWidth()/100)*20, (this.getHeight()/100)*(cajaAlto/2));

        ooDrawTools.processTri(g, tblgraphs.getGlevel()+"", this.getHeight(), this.getWidth());
        ooDrawTools.getPHD(g,this.getHeight(),this.getWidth());

    }

    /**
     * @return
     *
     */

    MouseListener mouseListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent event) {
            //graph.setSelectionCell(null);
            //graphContainer.se

            //graph.selectNextCell();
            //graph.selectPreviousCell();
            //graph.cellsToBack(new Object[]{cell});
            //graph.setSelectionCells()
            //graph.removeCells(new Object[]{cell});
            //graph.cellsToBack(graph.getSelectionCells());

        }
    };

    public static void main(String[] args) {
        try {
            DrawRectPanel oo1 = new DrawRectPanel();

            oo1.setSize(710, 530);
            oo1.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}