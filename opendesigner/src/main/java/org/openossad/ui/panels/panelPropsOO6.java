package org.openossad.ui.panels;

import org.jdesktop.swingx.JXList;
import org.openossad.ui.base.PanelPropsBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class panelPropsOO6 extends PanelPropsBase
{
	public JXList jxList;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;
    public JLabel lbl2;


    public panelPropsOO6() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setVisible(false);
		this.setBackground(SystemColor.control);
		this.setSize(600, 470);
		add(gui.getLblTit("Gráficos", 0, 0, 600, 30), null);
		
		add(gui.getLbl("Lista de los enlaces:", 20, 50, 580, 20), null);
		add(getxjList(20, 67, 570, 200), null);
		
		add(gui.getLbl("Número total de enlaces:", 20, 280, 200, 20), null);
		add(getLbl2("....", 220, 280, 100, 20), null);
		
		add(getJButton1(300, 350, 130, 25), null);
		add(getJButton2(300, 380, 130, 25), null);
		add(getJButton3(450, 350, 130, 25), null);
		add(getJButton4(450, 380, 130, 25), null);

		this.setVisible(true);
	}
	private JButton getJButton1(int xx, int yy, int width, int height) {
		if (jButton1 == null) {
			jButton1 = new JButton("Suprimir");
			jButton1.setBounds(new Rectangle(xx, yy, width, height));
			jButton1.setEnabled(false);
			jButton1.addMouseListener(mouseListener);
		}
		return jButton1;
	}
	private JButton getJButton2(int xx, int yy, int width, int height) {
		if (jButton2 == null) {
			jButton2 = new JButton("Alcanzar");
			jButton2.setBounds(new Rectangle(xx, yy, width, height));
			jButton2.addMouseListener(mouseListener);
			jButton2.setEnabled(false);
		}
		return jButton2;
	}

	private JButton getJButton3(int xx, int yy, int width, int height) {
		if (jButton3 == null) {
			jButton3 = new JButton("Restablecer");
			jButton3.setBounds(new Rectangle(xx, yy, width, height));
			jButton3.addMouseListener(mouseListener);
			jButton3.setEnabled(false);
		}
		return jButton3;
	}
	private JButton getJButton4(int xx, int yy, int width, int height) {
		if (jButton4 == null) {
			jButton4 = new JButton("Alcanzar");
			jButton4.setBounds(new Rectangle(xx, yy, width, height));
			jButton4.addMouseListener(mouseListener);
		}
		return jButton4;
	}
	
	
	private JComponent getxjList(int xx, int yy, int width, int height)  {
		jxList = new JXList(genericListModelHibernate_1);
		genericListModelHibernate_1.connQuery = ""; //"SELECT * FROM openossad.ooCells WHERE shTypeId in (6,9,10) AND shGraphID='"+getParent().GId+"'";
		genericListModelHibernate_1.loadDataFromODBC();
		JScrollPane scrollPane = new JScrollPane(jxList);
		scrollPane.setBounds(new Rectangle(xx, yy, width, height));
		return scrollPane;
	}

	private JComponent getLbl2(String label, int xx, int yy, int width,int height) {
		lbl2 = new JLabel(label);
		lbl2.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,height
		return lbl2;
	}

    MouseListener mouseListener = new MouseAdapter()
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
        }
    };



}
