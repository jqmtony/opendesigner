package org.openossad.ui.panels;

import org.openossad.ui.base.PanelNewBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class panelNewOO6 extends PanelNewBase
{

	/**
	 * This is the default constructor
	 */
	public panelNewOO6() {
		super("Indicadores", "/ui/images/clock_48.png");
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setVisible(false);

        comboTblprojects = new JComboBox();
        add(gui.getComboFolder(160, 42, (this.size().width - (250)), 25, comboTblprojects),null);

		add(gui.getLbl("Nombre indicador", 80, 100, w, h), null);
		add(getTxt2(230, 100, (this.size().width - (280)), 20), null);
		add(getTxt3(230, 130, (this.size().width - (280)), 20), null);
		add(gui.getDivider(this,173), null);

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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
