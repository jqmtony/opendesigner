package org.openossad.ui.panels;

import org.openossad.ui.base.IPanelOpenBase;
import org.openossad.ui.base.PanelOpenBase;
import org.openossad.ui.ooOpenGenericDlg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class panelOpenOO2 extends PanelOpenBase implements IPanelOpenBase
{
    private static String sqlQuery="SELECT Gname,GRef,GLevel ,COALESCE(GVersionNumber,'1.0'),GDesignDate,GUpdateDate,ProjectID,GVisPublic,COALESCE(GAuthor,''),GId,GOrientation  FROM tblgraphs WHERE GLevel in (5,6,7) AND ProjectID=";

    /**
	 * This is the default constructor
	 */
	public panelOpenOO2(ooOpenGenericDlg parent) {
		super();
        setOoElementsMouseAdapter(getTableOoElementsMouseAdapter());
        setSqlQueryForTableModel(sqlQuery);
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
		add(gui.getLblTit("Modelos", 0, 0, 600, 30), null);

        addComboTblprojects();


		add(getMainJXTable("OPS000-000000001"), null);
		add(gui.getDivider(this,275), null);
		add(gui.getDivider(this,305), null);
		add(gui.getLbl("Creado el   ", 50, 320, 100, 20), null);
		add(gui.getLbl("Modificado el", 50, 340, 100, 20), null);
		add(gui.getLbl("Autor", 50, 360, 100, 20), null);
		add(gui.getLbl("Referencia", 50, 380, 100, 20), null);
		add(gui.getLbl("Archivo", 50, 400, 100, 20), null);
		add(getLbl0("", 160, 280, 300, 20), null);
		add(getLbl1("", 160, 320, 300, 20), null);
		add(getLbl2("", 160, 340, 300, 20), null);
		add(getLbl3("", 160, 360, 300, 20), null);
		add(getLbl4("", 160, 380, 300, 20), null);
		add(getLbl5("", 160, 400, 300, 20), null);

		this.setVisible(true);
	}


	private JComponent getLbl0(String label, int xx, int yy, int width,
			int height) {
		lbl0 = new JLabel(label);
		lbl0.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		lbl0.setBackground(Color.white);
		lbl0.setFont(new Font(lbl0.getFont().getName(), Font.BOLD, 14));
		lbl0.setOpaque(true);
		return lbl0;
	}

	private JComponent getLbl1(String label, int xx, int yy, int width,
			int height) {
		lbl1 = new JLabel(label);
		lbl1.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return lbl1;
	}

	private JComponent getLbl2(String label, int xx, int yy, int width,
			int height) {
		lbl2 = new JLabel(label);
		lbl2.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return lbl2;
	}

	private JComponent getLbl3(String label, int xx, int yy, int width,
			int height) {
		lbl3 = new JLabel(label);
		lbl3.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return lbl3;
	}

	private JComponent getLbl4(String label, int xx, int yy, int width,
			int height) {
		lbl4 = new JLabel(label);
		lbl4.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return lbl4;
	}

	private JComponent getLbl5(String label, int xx, int yy, int width,
			int height) {
		lbl5 = new JLabel(label);
		lbl5.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return lbl5;
	}



    @Override
    public MouseListener getTableOoElementsMouseAdapter()
    {
        return getMouseAdapterTblgraphsTable();
    }
}
