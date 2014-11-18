package org.openossad.ui.panels;

import org.jdesktop.swingx.JXList;
import org.openossad.ui.base.PanelPropsBase;
import org.openossad.ui.ooFolderExplorer;
import org.openossad.util.GUItools;
import org.openossad.util.helper.ReferencialFileOOSSAD;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class panelPropsOO1d extends PanelPropsBase
{


	private int h = 20;
	private int w = 150;
	private static final long serialVersionUID = 1L;
	public JLabel lbl;
	public JLabel lblTit;
	public JLabel lblImg;
	public JLabel lbl2;
	public JLabel lbl3;
	public JTextField txt1= new JTextField();
	public JTextField txtGName = new JTextField();
	public JTextField txtGRef = new JTextField();
	public JTextField txt4= new JTextField();
	public JComboBox comboF = new JComboBox();
	public JButton butFolder;
	public ButtonGroup g; // = new ButtonGroup();
	public JRadioButton rb1, rb2, rb3;
	private JXList jxList;
	private JScrollPane scrollPane;
	private JButton jButton4 = null;
	private JButton jButton1 = null;
	private JButton jButtonMore = null;
	private Integer level = null;
	private Integer orientation = null;
	public JCheckBox check1 = new JCheckBox();
	public JCheckBox check2 = new JCheckBox();
	public JComboBox comboD=new JComboBox();
	GUItools GUI = new GUItools();
	
	/**
	 * This is the default constructor
	 * @param roo 
	 */
	private JButton but1=new JButton();
	private JButton but2=new JButton();
	public panelPropsOO1d() {
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
		add(GUI.getLblTit("General", 0, 0, 600, 30));
		
		add(GUI.getLblImg("/ui/images/paper_content_48.png", 10, 50, 50, 50));
		//add(new JButtonRef());
		
		add(GUI.getLbl("Documento:", 80, 50, w, 25));
		add(GUI.getTxt(txtGName,230, 50, 320, 25));
		add(GUI.getLbl("Referencia:", 80, 80, w, 25));
		add(GUI.getTxt(txtGRef,230, 80, 320, 25));
		
		add(GUI.getDivider(this,115));
		
		add(GUI.getLbl("Archivo externo:", 20, 125, 150, 20));
		add(GUI.getTxt(txt4,150, 125, 200, 25));
		add(GUI.getJButton("but1",but1,"/ui/images/buttons/46.png",350, 125, 25, 25));
		add(GUI.getLblImg("/ui/images/paper_content_48.png", 430, 132, 100, 120,true));
		add(GUI.getChkBut(check2,false,"Es enlace a documento", 20, 155, 355, 20));
		check2.setEnabled(false);
		
		add(GUI.getDivider(this,270));
		add(GUI.getLbl("Mover a carpeta:", 30, 290, 130, 20));
		//add(gui.getComboFolder(comboF,160, 290, 350, 25));
		//add(gui.getbutFolder(520, 200, 25, 25));
		add(GUI.getDivider(this,330));
		
		
		add(GUI.getLbl("Tamaño:", 30, 340, 130, 20));
		
		add(GUI.getLbl("Archivo:", 30, 370, 130, 20));
		add(GUI.getJButton("but2",but2,"Abrir",277, 230, 100, 25));
		add(GUI.getLbl("Tipo:", 30, 400, 130, 20));
		add(getComboDoc(120, 400, 300, 25));
		add(GUI.getChkBut(check1,false,"Visibilidad pública", 300, 340, 200, 20));
		//add(getChkBut2("Generado", 30, 360, 200, 20));
		
		but2.addMouseListener(mouseListener);
		
	
		add(GUI.getDivider(this,427));
		
		this.setVisible(true);
	}
	
	
	public JComponent getComboDoc(int xx, int yy, int width, int height) {
		;
		comboD.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,
		comboD.addItem("Documento");
		comboD.addItem("Auditorías");
		comboD.addItem("Calidad");
		comboD.addItem("Indicadores");
		comboD.addItem("Mejoras");
		comboD.addItem("Análisis");
		comboD.addItem("Normas técnicas");
		comboD.addItem("No conformidad");
		return comboD;
	}

	
	private JComponent getbutFolder(int xx, int yy, int width, int height) {
		butFolder = new JButton(MetalIconFactory.getFileChooserNewFolderIcon());
		butFolder.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,
		// height
		butFolder.setToolTipText("Gestión de carpetas");
		butFolder.addMouseListener(mouseListener);
		return butFolder;
	}

	MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {
			// JButton but = (JButton) mouseEvent.getSource();
			JButton wek = (JButton) event.getSource();
			if (wek.getName().equals("but2")){
				
				ReferencialFileOOSSAD.OpenDocument(txt4.getText());

			}
			if (event.getSource().equals(jButton4)) {
				getParent().getParent().getParent().getParent().setVisible(
						false);
			}

			if (event.getSource().equals(butFolder)) {
				ooFolderExplorer oo1 = new ooFolderExplorer(null);
				oo1.setVisible(true);
				remove(comboF);
				//add(gui.getComboFolder(comboF,160, 200, 350, 25));
				//comboF.removeAllItems();
				//new CargaCombo(comboF);

			}

			// TODO: dale caña a esto
			if (event.getSource().equals(jButton1)) {

			}
		}
	};



	

}
