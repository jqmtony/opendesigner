package org.openossad.ui.panels;

import com.mxgraph.util.mxResources;
import org.jdesktop.swingx.JXList;
import org.openossad.ui.base.IPanelOpenBase;
import org.openossad.ui.base.PanelOpenBase;
import org.openossad.ui.component.JButtonMore;
import org.openossad.ui.ooFolderExplorer;
import org.openossad.ui.ooOpenGenericDlg;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.*;

public class panelOpenOO8 extends PanelOpenBase implements IPanelOpenBase
{

	private int h = 20;
	private int w = 150;
	private static final long serialVersionUID = 1L;
	public JLabel lbl;
	public JLabel lblTit;
	public JLabel lblImg;
	public JLabel lbl0;
	public JLabel lbl1;
	public JLabel lbl2;
	public JLabel lbl3;
	public JLabel lbl4;
	public JLabel lbl5;
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
    private static String sqlQuery="SELECT Name, CompleteName ,Type , Description ,Mail,Responsable,Assistant ,SId FROM tblaccounts  WHERE IsGroup=0";

    /**
	 * This is the default constructor
	 */
	public panelOpenOO8(ooOpenGenericDlg parent) {
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
		add(getLblTit("Actores", 0, 0, 600, 30), null);
		// add(getLblImg("images/grafic_n_2_48.png",10,100,50,50),null);
		add(new JButtonMore(), null);
		add(getLbl("Buscar en carpeta", 30, 43, 130, 20), null);
		add(gui.addFakeComboBoxProjects(this.size().width));
		add(getbutFolder(520, 42, 25, 25), null);
		add(getDivider(75), null);
		add(getMainJXTable(""), null);
		add(getDivider(275), null);
		add(getDivider(305), null);
		add(getLbl("Nombre   ", 50, 320, 100, 20), null);
		add(getLbl("Tipo", 50, 340, 100, 20), null);
		add(getLbl("Descripción", 50, 360, 100, 20), null);
		add(getLbl("E-mail", 50, 380, 100, 20), null);
		add(getLbl("Responsable", 50, 400, 100, 20), null);
		add(getLbl0("", 160, 280, 300, 20), null);
		add(getLbl1("", 160, 320, 300, 20), null);
		add(getLbl2("", 160, 340, 300, 20), null);
		add(getLbl3("", 160, 360, 300, 20), null);
		add(getLbl4("", 160, 380, 300, 20), null);
		// add(getLbl5("",160,400,300,20),null);
		add(getDivider(427), null);
		add(getJButton1(600 - 120, 440, 65, 30), null);
		add(getJButton4(600 - 240, 440, 103, 30), null);
		this.setVisible(true);
	}

	private JButton getJButton1(int xx, int yy, int width, int height) {
		if (jButton1 == null) {
			jButton1 = new JButton(mxResources.get("newOpenossad"), MetalIconFactory.getMenuItemArrowIcon());
			jButton1.setBounds(new Rectangle(xx, yy, width, height));
			jButton1.setToolTipText(mxResources.get("newOpenossad"));
			jButton1.addMouseListener(mouseListener);
			// jButton1.addActionListener(actionListener);
		}
		return jButton1;
	}

	// //////////////////////////////////////////////////////////////////

	private JButton getJButton4(int xx, int yy, int width, int height) {
		if (jButton4 == null) {
			jButton4 = new JButton("Cancelar", MetalIconFactory.getInternalFrameCloseIcon(16));
			jButton4.setBounds(new Rectangle(xx, yy, width, height));
			jButton4.setToolTipText("Cancelar");
			jButton4.addMouseListener(mouseListener);
			// jButton4.addActionListener(actionListener);
		}
		return jButton4;
	}

	public JComponent getCombo(int xx, int yy) {
		g = new ButtonGroup();
		rb1 = new JRadioButton("Nivel 1", false);
		rb2 = new JRadioButton("Nivel 2", false);
		rb3 = new JRadioButton("Nivel 3", false);
		rb1.addActionListener(al);
		rb1.setSelected(true);
		rb2.addActionListener(al);
		rb3.addActionListener(al);
		g.add(rb1);
		g.add(rb2);
		g.add(rb3);
		Container cp = new JPanel();
		cp.setLayout(new FlowLayout());
		cp.setBounds(new Rectangle(xx, yy, 100, 130));
		lblTit = new JLabel();
		lblTit.setVerticalTextPosition(SwingConstants.CENTER);
		lblTit.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTit.setBorder(BorderFactory.createLineBorder(Color.black));
		lblTit.setBounds(new Rectangle(1, 1, 90, 130));
		cp.add(rb1);
		cp.add(rb2);
		cp.add(rb3);
		cp.setVisible(true);
		return (JPanel) cp;
	}

	private ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

		}
	};

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


	private JComponent getDivider(int yy) {
		JPanel divider = new JPanel();
		divider.setBackground(new Color(SystemColor.WINDOW_BORDER));
		divider.setSize(new Dimension(this.getWidth(), 1));
		divider.setBounds(new Rectangle(5, yy, this.getWidth() - 10, 1));
		return divider;
	}

	private JComponent getbutFolder(int xx, int yy, int width, int height) {
		butFolder = new JButton(MetalIconFactory.getFileChooserNewFolderIcon());
		butFolder.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,
		// height
		butFolder.setToolTipText("Gestión de carpetas");
		butFolder.addMouseListener(mouseListener);
		// butFolder.addActionListener(actionListener);
		butFolder.setEnabled(false);
		return butFolder;
	}

	private JLabel getLblTit(String label, int xx, int yy, int width, int height) {
		lblTit = new JLabel(label);
		lblTit.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,
		// height
		lblTit.setFont(new Font(lblTit.getFont().getName(), Font.BOLD, 18));
		lblTit.setVerticalTextPosition(SwingConstants.CENTER);
		lblTit.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTit.setBorder(BorderFactory.createLineBorder(Color.black));
		lblTit.setOpaque(true);
		lblTit.setBackground(new Color(144, 153, 174));
		lblTit.setForeground(Color.white);
		return lblTit;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = panelOpenOO8.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {

			// JButton but = (JButton) mouseEvent.getSource();

			if (event.getSource().equals(jButton4)) {
				getParent().getParent().getParent().getParent().setVisible(
						false);
			}

			if (event.getSource().equals(butFolder)) {
				ooFolderExplorer oo1 = new ooFolderExplorer(null);
				oo1.setVisible(true);
				comboTblprojects.removeAllItems();
				gui.loadFolderCombo(comboTblprojects);

			}

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
				oo1.choosed = lbl1.getText() + "^OO8:" + selectedToOpen;
				oo1.setVisible(false);

			}
		}
	};


    @Override
    public MouseListener getTableOoElementsMouseAdapter()
    {
        return new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				lbl0.setText(getValueAtRow(tableOoElements,1).toString());
				lbl1.setText(getValueAtRow(tableOoElements,0).toString());
				String res = "";
				switch (Integer.parseInt(getValueAtRow(tableOoElements,2).toString())) {
					case 0 : {
						res = "----";
						break;
					}
					case 1 : {
						res = "Redactor";
						break;
					}
					case 2 : {
						res = "Administrador";
						break;
					}
					case 3 : {
						res = "Lector";
						break;
					}
				}
				lbl2.setText(res);
				lbl3.setText(getValueAtRow(tableOoElements,3).toString());
				lbl4.setText(getValueAtRow(tableOoElements,4).toString());
				lbl4.setText(getValueAtRow(tableOoElements,5).toString());
				selectedToOpen = getValueAtRow(tableOoElements,7).toString();
				if (e.getClickCount() == 2) {
					JOptionPane.showConfirmDialog(null,"¿ Desea ir a la página de propiedades del modelo ? ","OpenDESIGNER",JOptionPane.INFORMATION_MESSAGE);
				}

			}
		};
    }
}
