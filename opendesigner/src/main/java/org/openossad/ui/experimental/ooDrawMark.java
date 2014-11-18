package org.openossad.ui.experimental;

import com.lowagie.text.pdf.PdfContentByte;
import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxRectangle;
import org.openossad.CustomOpenDESIGNERGraph;
import org.openossad.data.domain.Tblgraphs;
import org.openossad.util.ImageUtil;
import org.openossad.util.draw.ooDrawTools;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;

public class ooDrawMark {
	
	public ooDrawMark(){
		/*
		try {
			imageIcon = new ImageIcon(new java.net.URL(ooDrawMark.class.getResource("resources/sgc_300.png"), ""));
			image = imageIcon.getImage();
			imageIcon2 = new ImageIcon(new java.net.URL(ooDrawMark.class.getResource("resources/piramid_p.png"), ""));
			image2 = imageIcon2.getImage();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JXErrorDialog.showDialog(null, "No se puede cargar la imágen de fondo", e);
		}
		*/
	}

	
	/**
	 * @param g
	 * @param height 
	 * @param width 
	 * @param tblgraphs
	 * @return
	 */
	public Graphics2D SimpleMark(Graphics2D g, int width, int height, Tblgraphs tblgraphs) {
        if (tblgraphs==null) {
            tblgraphs = new Tblgraphs();
            tblgraphs.setGname("");
            tblgraphs.setGref("");
            tblgraphs.setGauthor("");
            tblgraphs.setGlevel(2);
        }

        Integer cajaAlto = 7;
	    //g.setFont(font);
        if (tblgraphs.getGbgimagePath()!=null) {
            String gbgimagePath = tblgraphs.getGbgimagePath();
            if (!gbgimagePath.isEmpty()){
                java.net.URL imgURL = null;
                try {
                    imgURL = new java.net.URL(ImageUtil.createImagePath(gbgimagePath));
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                File Gbg = new File(imgURL.getPath());
                if (Gbg.exists()) {g.drawImage(ImageUtil.createImage(gbgimagePath), 0,0, width, height, null);}
            }
        }
		
	    g.setFont(new Font("sansserif", Font.BOLD, (height/100)*2));
	    g.drawString(tblgraphs.getGname(), (width/100)*20, (height/100)*6);
	    
	    g.setFont(new Font("sansserif", Font.BOLD, (int) ((height/100)*1.5)));
	    g.drawString("Versión: "+tblgraphs.getGversionNumber(), (width/100)*16, (height/100)*2);
	    g.drawString("Ref: "+tblgraphs.getGref(), (width/100)*35, (height/100)*2);
	    g.drawString("Autor: " +tblgraphs.getGauthor(), (width / 100) * 57, (height / 100) * 2);
	    
	    g.drawRect(0, 0, width-1, height-1);
	    g.drawRect(0, 0, width-1, (height/100)*cajaAlto);
	    //g.drawRect((width/100)*14, 0, width/100-1, (height/100)*cajaAlto);
	    
	    g.drawRect((width/100)*15, 0, (width/100)*65, (height/100)*cajaAlto);
	    g.drawRect((width/100)*15, 0, (width/100)*65, (height/100)*(cajaAlto/2));
	    g.drawRect((width/100)*34, 0, (width/100)*20, (height/100)*(cajaAlto/2));
	    
	    ooDrawTools.processTri(g,tblgraphs.getGlevel() + "", height, width);
	    ooDrawTools.getPHD(g, height, width);
	    return g;
	    
	}

    public static PdfContentByte createGraphImageIntoGraphic(final Tblgraphs tblgraphs, final CustomOpenDESIGNERGraph graphWithMarco, final PdfContentByte pdfContentByte)
    {
        float scala = (float) 1;
        mxGraphics2DCanvas canvas = (mxGraphics2DCanvas) mxCellRenderer.drawCells(graphWithMarco, null, scala, null, new mxCellRenderer.CanvasFactory()
        {
            public mxICanvas createCanvas(int width, int height)
            {
                mxRectangle bounds = graphWithMarco.getGraphBounds();
                final int w = (int) (bounds.getWidth() + bounds.getX() + 1);
                final int h = (int) (bounds.getHeight() + bounds.getY() - 1);
                Graphics2D graphics = pdfContentByte.createGraphics(w, h);
                ooDrawMark drawMark = new ooDrawMark();
                graphics = drawMark.SimpleMark(graphics, width, height, tblgraphs);
                return new mxGraphics2DCanvas(graphics);
            }
        });
        canvas.getGraphics().dispose();
        return pdfContentByte;
    }


}
