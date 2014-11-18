package org.openossad.ui.panels;

import org.openossad.ui.base.IPanelOpenBase;
import org.openossad.ui.base.PanelOpenBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class panelOpenOO6 extends PanelOpenBase implements IPanelOpenBase
{
	public JLabel lbl0;
	public JLabel lbl1;
	public JLabel lbl2;
	public JLabel lbl3;
	public JTextField txt1;
	public JTextField txt2;
	public JTextField txt3;
    
    private static String sqlQuery  ="SELECT Iname, IId, ICalcul, IPeriode,IIDX FROM tblindic";

    /**
	 * This is the default constructor
	 */
	public panelOpenOO6(JDialog parent) {
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
		add(gui.getLblTit("Indicadores", 0, 0, 600, 30), null);

        //addComboTblprojects();

		add(getMainJXTable(""), null);
		add(gui.getDivider(this,275), null);
		add(gui.getDivider(this,305), null);
		add(gui.getLbl("ID   ", 50, 320, 100, 20), null);
		add(gui.getLbl("Cálculo", 50, 340, 100, 20), null);
		add(gui.getLbl("Periodicidad", 50, 360, 100, 20), null);
		add(gui.getLbl("", 50, 380, 100, 20), null);
		add(getLbl0("", 160, 280, 300, 20), null);
		add(getLbl1("", 160, 320, 300, 20), null);
		add(getLbl2("", 160, 340, 300, 20), null);
		add(getLbl3("", 160, 360, 300, 20), null);
        remove(gui.buttonOpenGraph);
        add(gui.getJButtonOpenIndic(600 - 120, 440, 103, 30));

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

	private JComponent getLbl2(String label, int xx, int yy, int width,int height) {
		lbl2 = new JLabel(label);
		lbl2.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return lbl2;
	}

	private JComponent getLbl3(String label, int xx, int yy, int width,int height) {
		lbl3 = new JLabel(label);
		lbl3.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return lbl3;
	}

    @Override
    public MouseListener getTableOoElementsMouseAdapter()
    {
        
        return new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lbl0.setText(getValueAtRow(tableOoElements,0).toString());
				lbl1.setText(getValueAtRow(tableOoElements,1).toString());
				lbl2.setText(getValueAtRow(tableOoElements,2).toString());
				lbl3.setText(getValueAtRow(tableOoElements,3).toString());
				selectedToOpen = getValueAtRow(tableOoElements,4).toString();
                if (e.getClickCount() == 2) {
					JOptionPane.showConfirmDialog(null, "¿ Desea ir a la página de propiedades del gráfico ? ", "OpenDESIGNER", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		};
    }
}
