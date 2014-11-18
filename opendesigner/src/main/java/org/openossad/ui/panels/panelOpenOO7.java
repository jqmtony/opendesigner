package org.openossad.ui.panels;

import org.jdesktop.swingx.JXList;
import org.openossad.ui.base.IPanelOpenBase;
import org.openossad.ui.base.PanelOpenBase;
import org.openossad.ui.ooOpenGenericDlg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class panelOpenOO7 extends PanelOpenBase implements IPanelOpenBase
{

	private int h = 20;
	private int w = 150;
	private static final long serialVersionUID = 1L;
	public JLabel lbl;
	public JLabel lblTit;
	public JLabel lblImg;
	public JTextField txt1;
	public JTextField txt2;
	public JTextField txt3;
	public JButton butFolder;
	private ButtonGroup g; // = new ButtonGroup();
	private JRadioButton rb1, rb2, rb3;
	private JXList jxList;
	private JScrollPane scrollPane;
	private JButton jButton4 = null;
	private JButton jButton1 = null;

	public String selectedToOpen = "";
	public Integer level;
	public Integer orientation;
	public ooOpenGenericDlg parent;
    private static String sqlQuery="SELECT GName , GRef , COALESCE(GAuthor,'') ,GDesignDate,GId  FROM tblentity WHERE  ProjectID=";
	/**
	 * This is the default constructor
	 */
	public panelOpenOO7(ooOpenGenericDlg parent) {
		super();
		this.parent=parent;
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
		add(gui.getLblTit("Roles", 0, 0, 600, 30), null);

        addComboTblprojects();


		add(getMainJXTable("OPS000-000000001"), null);
		add(gui.getDivider(this,275), null);
		add(gui.getDivider(this,305), null);
		add(getLbl("Referencia ", 50, 320, 100, 20), null);
		add(getLbl("Autor", 50, 340, 100, 20), null);
		add(getLbl("Fecha", 50, 360, 100, 20), null);
		add(getLbl("", 50, 380, 100, 20), null);
		add(getLbl("", 50, 400, 100, 20), null);
		add(getLbl0("", 160, 280, 300, 20), null);
		add(getLbl1("", 160, 320, 300, 20), null);
		add(getLbl2("", 160, 340, 300, 20), null);
		add(getLbl3("", 160, 360, 300, 20), null);
		add(getLbl4("", 160, 380, 300, 20), null);

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

	private JComponent getLbl(String label, int xx, int yy, int width,
			int height) {
		lbl = new JLabel(label);
		lbl.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,height
		return lbl;
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

	MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {


			// TODO: dale caña a esto
			if (event.getSource().equals(jButton1)) {

				if (lbl0.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Por favor, seleccione un gráfico para abrir.",
							"Validación", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				ooOpenGenericDlg oo1 = (ooOpenGenericDlg) getParent()
						.getParent().getParent().getParent();
				oo1.choosed = lbl0.getText() + "^OO7:" + selectedToOpen;
				oo1.setVisible(false);

			}
		}
	};

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
					JOptionPane.showConfirmDialog(null,"¿ Desea ir a la página de propiedades del modelo ? ","OpenDESIGNER",JOptionPane.INFORMATION_MESSAGE);
				}

			}
		};

    }
}
