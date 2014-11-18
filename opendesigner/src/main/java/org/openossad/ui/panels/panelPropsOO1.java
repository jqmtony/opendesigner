package org.openossad.ui.panels;


import org.jdesktop.swingx.JXList;
import org.openossad.ui.base.PanelPropsBase;
import org.openossad.ui.ooFolderExplorer;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.*;

public class panelPropsOO1 extends PanelPropsBase
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
	public JTextField txtGName;
	public JTextField txtGRef;
	public JComboBox comboF;
	public JButton butFolder;
	public ButtonGroup g; // = new ButtonGroup();
	public JRadioButton rb1, rb2, rb3;
	private JXList jxList;
	private JScrollPane scrollPane;
	private JButton jButton4 = null;
	private JButton jButton1 = null;
	public JCheckBox check1;
	public JCheckBox check2;


	public panelPropsOO1() {
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
		add(gui.getLblTit("General", 0, 0, 600, 30), null);

		add(gui.getLblImg("/ui/images/grafic_n_1_48.png", 10, 50, 50, 50), null);
		//add(new JButtonRef(), null);
		
		
		add(gui.getLbl("Nombre del gráfico:", 80, 50, w, h), null);
		add(getTxt2(230, 50, (this.size().width - (280)), 20), null);
		add(gui.getLbl("Referencia:", 80, 80, w, h), null);
		add(getTxt3(230, 80, (this.size().width - (280)), 20), null);
		
		add(gui.getDivider(this,115), null);
		add(getCombo(150, 125), null);
		add(gui.getDivider(this,180), null);
		add(gui.getLbl("Crear en carpeta:", 30, 200, 130, 20), null);
		//add(getComboFolder(160, 200, (this.size().width - (250)), 25), null);
		add(getbutFolder(520, 200, 25, 25), null);
		add(gui.getDivider(this,240), null);
		//TODO
		//add(getLbl("Tamaño:", 30, 270, 130, 20), null);
		
		//add(getLbl("Archivo:", 30, 300, 130, 20), null);
		
		add(getChkBut1("Visibilidad pública", 30, 330, 200, 20), null);
		add(getChkBut2("Generado", 30, 360, 200, 20), null);
		
		
		
	
		add(getDivider(427), null);
		//add(getJButton1(600 - 120, 440, 103, 30), null);
		//add(getJButton4(600 - 240, 440, 103, 30), null);

		this.setVisible(true);
	}

	private JComponent getChkBut1(String label,int xx, int yy, int width, int height) {
		check1 = new JCheckBox(label);
		//check1.setMnemonic(KeyEvent.VK_C);
		check1.setBounds(new Rectangle(xx, yy, width, height));
		check1.setSelected(false);
		return check1;
	}
	private JComponent getChkBut2(String label,int xx, int yy, int width, int height) {
		check2 = new JCheckBox(label);
		//check2.setMnemonic(KeyEvent.VK_C);
		check2.setBounds(new Rectangle(xx, yy, width, height));
		check2.setSelected(false);
		check2.setEnabled(false);
		return check2;
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
		cp.setBounds(new Rectangle(xx, yy, 300, 40));
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
			Integer level = 1;
			if (rb1.isSelected())
				level = 1;
			if (rb2.isSelected())
				level = 2;
			if (rb3.isSelected())
				level = 3;
			remove(lblImg);
			add(gui.getLblImg("images/grafic_n_" + level + "_48.png", 10, 50, 50,50), null);
			repaint();

		}
	};

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
		return butFolder;
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
				comboF.removeAllItems();
				//new CargaCombo(comboF);

			}

			// TODO: dale caña a esto
			if (event.getSource().equals(jButton1)) {

			}
		}
	};

}
