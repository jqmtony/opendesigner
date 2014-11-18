package org.openossad.ui.panels;

import org.jdesktop.swingx.JXList;
import org.openossad.ui.base.PanelPropsBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class panelPropsOO4 extends PanelPropsBase
{

	public JLabel createdDate;
	public JLabel lblIndice;
	public JLabel lblTotalNumberFiles;
	public JTextField txtVersion;
	public JTextField txtModificado;
	public JTextField txt6;
	public JTextArea txtANovedades;
	public JXList jxList;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;
    private JComboBox comboBoxEstado;

    public panelPropsOO4() {
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
		add(gui.getLblTit("Versión", 0, 0, 600, 30), null);
		
		//add(new JButtonRef(), null);
		
		
		add(gui.getLbl("Creado el:", 20, 50, 100, 20), null);
		add(getCreadoEl("...^.", 100, 50, 200, 20), null);
		
		add(gui.getLbl("Versión nº:", 300, 50, 100, 20), null);
		add(getNumberVersion(410, 50, 150, 25), null);
		
		add(gui.getLbl("Índice:", 20, 80, 100, 20), null);
		add(getIndex("....", 100, 80, 100, 20), null);
		
		add(gui.getLbl("Modificado el:", 300, 80, 100, 20), null);
		add(getDateModified(410, 80, 150, 25), null);
		
		add(gui.getLbl("Estado:", 300, 110, 100, 20), null);
		add(getEstado(410, 110, 150, 25), null);
		
		add(gui.getLbl("Novedades:", 20, 143, 580, 20), null);
		add(getTxtAreaNovedades(20, 160, 570, 50), null);
		add(gui.getDivider(this,220), null);
		
		add(gui.getLbl("Lista de las versiones anteriores:", 20, 233, 580, 20), null);
		add(getxjList(20, 250, 570, 70), null);
		
		add(gui.getLbl("Número total de archivos:", 20, 330, 200, 20), null);
		add(getTotalNumberFiles("....", 220, 330, 100, 20), null);
		
		add(getJButton1(300, 350, 130, 25), null);
		add(getJButton2(300, 380, 130, 25), null);
		add(getJButton3(450, 350, 130, 25), null);


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
			jButton4 = new JButton("Nueva versión");
			jButton4.setBounds(new Rectangle(xx, yy, width, height));
			jButton4.addMouseListener(mouseListener);
		}
		return jButton4;
	}
	
	
	private JComponent getxjList(int xx, int yy, int width, int height)  {
		jxList = new JXList(genericListModelHibernate_1);
		genericListModelHibernate_1.connQuery = "";
		//genericListModelHibernate_1.connQuery = "SELECT GArchiveDate FROM atblgraphs  WHERE GId='"+ +"'";
		genericListModelHibernate_1.loadDataFromODBC();
		JScrollPane scrollPane = new JScrollPane(jxList);
		scrollPane.setBounds(new Rectangle(xx, yy, width, height));
		return scrollPane;
	}
	
	private JComponent getEstado(int xx, int yy, int width, int height)  {
		comboBoxEstado = new JComboBox();
		comboBoxEstado.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,
		comboBoxEstado.addItem("En creación");
		comboBoxEstado.addItem("Aplicable");
		comboBoxEstado.setBounds(new Rectangle(xx, yy, width, height));
		return comboBoxEstado;
	}


	/**
	 * 
	 */
	protected void delTxt6() {
		txt6.setText("");
		
	}

	private JComponent getTxtAreaNovedades(int xx, int yy, int width, int height) {
		
		txtANovedades = new JTextArea(4,20);
		txtANovedades.setToolTipText("Novedades");
		txtANovedades.setLineWrap(true);
        txtANovedades.setWrapStyleWord(true);

		JScrollPane sc1 = new JScrollPane(txtANovedades);
		sc1.setBounds(new Rectangle(xx, yy, width, height));
		sc1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sc1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		return sc1;
	}
	


	private JComponent getCreadoEl(String label, int xx, int yy, int width, int height) {
		createdDate = new JLabel(label);
		createdDate.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,height
		return createdDate;
	}

	private JComponent getIndex(String label, int xx, int yy, int width, int height) {
		lblIndice = new JLabel(label);
		lblIndice.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,height
		return lblIndice;
	}
	private JComponent getTotalNumberFiles(String label, int xx, int yy, int width, int height) {
		lblTotalNumberFiles = new JLabel(label);
		lblTotalNumberFiles.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,height
		return lblTotalNumberFiles;
	}

	private JComponent getNumberVersion(int xx, int yy, int width, int height) {
		txtVersion = new JTextField();
		txtVersion.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return txtVersion;
	}

	private JComponent getDateModified(int xx, int yy, int width, int height) {
		txtModificado = new JTextField();
		txtModificado.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return txtModificado;
	}


	MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {
			// JButton but = (JButton) mouseEvent.getSource();
		}
	};

}
