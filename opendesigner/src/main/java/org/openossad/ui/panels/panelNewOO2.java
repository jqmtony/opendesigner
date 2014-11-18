package org.openossad.ui.panels;


import org.openossad.ui.base.PanelNewBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;


public class panelNewOO2 extends PanelNewBase
{


	private int h = 20;
	private int w = 150;
	private static final long serialVersionUID = 1L;
	public JLabel lbl;
	public JLabel lblTit;
	public JLabel lblImg;
	public JLabel lbl2;
	public JLabel lbl3;
	public JTextField txt1;
	/**
	 * This is the default constructor
	 */
	public panelNewOO2() {
        super("Creación de Modelo", "/ui/images/model_n_1_48.png");
        isModel=true;



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

        comboTblprojects = new JComboBox();
        add(gui.getComboFolder(160, 42, (this.size().width - (250)), 25, comboTblprojects),null);

		add(gui.getLbl("Nombre del modelo", 80, 100, w, h), null);
		add(getTxt2(230, 100, (this.size().width - (280)), 20), null);
		add(getTxt3(230, 130, (this.size().width - (280)), 20), null);
		add(gui.getDivider(this, 173), null);
		add(gui.getCombo(50, 210));
        add(gui.getjxList(190, 200, 250, 200, 1));

		this.setVisible(true);
	}


	private JComponent getTxt2(int xx, int yy, int width, int height) {
		txtGName = new JTextField();
		txtGName.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return txtGName;
	}

	private JComponent getTxt3(int xx, int yy, int width, int height) {
		txtGRef = new JTextField();
		txtGRef.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return txtGRef;
	}


    @Override
    public MouseListener newButtonCreateMouseListener()
    {
        return getListenerForTblgraphs();
    }
}
