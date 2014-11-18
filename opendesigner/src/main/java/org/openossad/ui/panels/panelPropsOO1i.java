package org.openossad.ui.panels;

import org.openossad.util.GUItools;

import javax.swing.*;
import java.awt.*;

public class panelPropsOO1i extends JDesktopPane {

	private int h = 20;
	private int w = 150;
	private static final long serialVersionUID = 1L;
	public JLabel lblTit;
	public JLabel lblImg;
	public JTextField txt1;
	public JTextField txt2=new JTextField();
	public JTextField txt3=new JTextField();
	public JComboBox comboF;
	public JButton butFolder;

	GUItools GUI = new GUItools();
	public JComboBox comboD=new JComboBox();
	

	/**
	 * This is the default constructor
	 */
	public panelPropsOO1i() {
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
		add(GUI.getLblTit("Indicadores", 0, 0, 600, 30));
		add(GUI.getLblImg("/ui/images/clock_48.png", 10, 43, 50, 50));
		
		add(GUI.getLbl("Nombre indicador", 80, 43, w, h));
		add(GUI.getTxt(txt2,230, 42, (this.size().width - (280)), 25));
		add(GUI.getLbl("Referencia", 80, 73, w, h));
		add(GUI.getTxt(txt3,230, 72, (this.size().width - (280)), 25));
		add(GUI.getDivider(this,112));
		
		add(getComboDoc(120, 150, 300, 25));
		
		add(GUI.getDivider(this,427));
		this.setVisible(true);
	}
	
	public JComponent getComboDoc(int xx, int yy, int width, int height) {
		comboD.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,
		comboD.addItem("Estratégico");
		comboD.addItem("Proceso");
		comboD.addItem("Producto");
		comboD.addItem("Cliente");
		comboD.addItem("Cronológico");
		comboD.addItem("Otro");
		return comboD;
	}

	



}
