package org.openossad.util.draw;

/**
 * OpenDESIGNER_2.0.5_2008
 * ooDrawTools.java,25/10/2009,
 * Copyright (c) 2009,  Xavi Hidalgo
 *
 * //////////////////////////////////////////////////////
 * Este código es propiedad de TricomZone, S.L.
 * Sujeto a la licencia LGPL.
 * 
 * //////////////////////////////////////////////////////
 *
 */

import javax.swing.*;
import java.awt.*;

public class ooDrawTools extends JApplet{

	/**
	 * @param g
	 * @param nivel
	 * @param height
	 * @param width
	 */
	public static int height;
	public static int width;
	public static int escala;
	
	public static void processTri(Graphics2D g, String nivel, int h,int w) {
		height=h;width=w;
		switch(Integer.parseInt(nivel)) {
	    	case 1:
	    		nivel="p1";
	    		g.setColor(Color.red);
	    		break;
	    	case 2:
	    		nivel="p2";
	    		g.setColor(Color.blue);
	    		break;
	    	case 3:
	    		nivel="p1";
	    		g.setColor(Color.green);
	    		break;
	    	case 8:
	    		nivel="h1";
	    		g.setColor(Color.red);
	    		break;
	    	case 9:
	    		nivel="h2";
	    		g.setColor(Color.blue);
	    		break;
	    	case 10:
	    		nivel="h3";
	    		g.setColor(Color.green);
	    		break;
	    	default:
	    		nivel="p0";
	    		break;
	    	
	    }
	    
	    escala=(width>height) ? width/13 :width/8;
	    
	    if (nivel.startsWith("p")) {
	    	//g.setPaint(new GradientPaint(0, 0,Color.blue, 100, 100,Color.red));
	    	g.setPaint(new GradientPaint((width/100)*85, (height/100)*6,g.getColor(), escala, escala,Color.white));
    	    g.fillPolygon(drawTriangleProcess((width/100)*85,(height/100)*6,escala )); //percent(100)
			g.setColor(Color.black);
		    g.drawPolygon(drawTriangleProcess((width/100)*85,(height/100)*6,escala )); //percent(100)
		    
		    escala=(width>height) ? (width/100)*88:(width/98)*90;
		    g.setFont(new Font("sansserif", Font.BOLD, (height/100)*3));
		    g.drawString(nivel.substring(nivel.length()-1),escala, (height/100)*5);
	    }
	    else {
	    	//g.setPaint(new GradientPaint(0, 0,Color.blue, 100, 100,Color.red));
		    g.fillPolygon(drawTriangleHuman((width/100)*85,(height/100)*6,escala )); //percent(100)
			g.setColor(Color.black);
		    g.drawPolygon(drawTriangleHuman((width/100)*85,(height/100)*6,escala )); //percent(100)
		    
		    escala=(width>height) ? (width/100)*88:(width/98)*90;
		    g.setFont(new Font("sansserif", Font.BOLD, (height/100)*3));
		    g.drawString(nivel.substring(nivel.length()-1),escala, (height/100)*4);
	    	
	    }
	    
	}
	
	public static void getPHD(Graphics2D g, int h,int w) {
		height=h;width=w;
		escala=(width>height) ? width/32 :width/21;
	    getPhd(g, (width/96)*2,(height/99)*4,escala);
	}
	
	public static void getPhd(Graphics2D g2d, int x,int y,int m) {
		double rr=(m*0.3);
	    int r=(int)rr;
	    
	    g2d.setPaint(new GradientPaint(x-r, y-(m-(m/4)),Color.yellow, m+m+r+r+(r/2), m+r+(r/2),Color.white));
	    g2d.fillOval(x-r, y-(m-(m/4)), m+m+r+r+(r/2), m+r+(r/2));
	    g2d.setColor(Color.black);
	    g2d.drawOval(x-r, y-(m-(m/4)), m+m+r+r+(r/2), m+r+(r/2));
	    g2d.setColor(Color.red);
	    g2d.fillPolygon(drawTriangleProcess(x, y, m));
	    g2d.setColor(Color.blue);
	    g2d.fillPolygon(drawTriangleHuman(x+m, y, m));
	    
	    g2d.setColor(Color.gray);
	    g2d.fillRect(x, y+(r/2), m+m, m/4);
	    g2d.setColor(Color.black);
	    g2d.drawRect(x, y+(r/2), m+m, m/4);
	    g2d.drawPolygon(drawTriangleProcess(x, y, m));
	    g2d.drawPolygon(drawTriangleHuman(x+m, y, m));
	    	
	}
	
	private int percent(int num) {
		// TODO Auto-generated method stub
		int res100=1068*816;
		int resNow=height*width;
		float percent=(float)resNow/res100;
		percent=((float)num)*percent;
		return (int)percent;
	}

	public static Polygon drawTriangleProcess(int x, int y, int w) {  
		Polygon polygon= new Polygon();
	    polygon.addPoint(x,y);
	    polygon.addPoint(x+(w/2),y-(w/2));
	    polygon.addPoint(x+w,y);
		return polygon;
	}
	
	public static Polygon drawTriangleHuman(int x, int y, int w) {  
		y=y-(w/2);
		Polygon polygon= new Polygon();
		polygon.addPoint(x,y);
	    polygon.addPoint(x+w,y);
	    polygon.addPoint(x+(w/2),y+(w/2));
	    
		return polygon;
	}
	public void init() {
    	setBackground(Color.lightGray);
    }
	public void paint(Graphics g) {
		getPHD((Graphics2D)g, this.getWidth(),this.getHeight());
		
	}
	public static void main(String s[]) {
	    JFrame frame = new JFrame("DrawTools");
	    JApplet obj = new ooDrawTools();
	    frame.getContentPane().add("Center", obj);
	    obj.init();
	    frame.setSize(600, 600);
	    frame.show();
  }
	
}
