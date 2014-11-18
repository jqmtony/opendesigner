package org.openossad.ui;

import com.l2fprod.common.swing.PercentLayout;
import org.openossad.OpenDESIGNER;
import org.openossad.data.domain.Tblindic;
import org.openossad.ui.component.JOutlookBar;
import org.openossad.ui.component.JXErrorDialog;
import org.openossad.ui.renderers.ComboBoxActorRenderer;
import org.openossad.ui.renderers.ListRendererRelations;
import org.openossad.util.GUItools;
import org.openossad.util.helper.DAOOOSSAD;
import org.openossad.util.helper.ReferencialOOSSAD;

import org.openossad.ui.panels.panelPropsOO1i;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;

public class ooIndicsGenericDlg extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panel = null;
	public Component currentComponent;
    public JButton but2;
    private JDesktopPane pn1;
    public String choosed;
    protected ImageIcon imageIcon;
	protected Image image = null;

	/////////////////////////////////

	/////////////////////////////////
	public ReferencialOOSSAD roo = new ReferencialOOSSAD();

//	panelPropsO13 pO13 = new panelPropsO13(roo);
    GUItools GUI = new GUItools();

    panelPropsOO1i pOO1 = new panelPropsOO1i();
//	panelPropsOO2i pOO2 = new panelPropsOO2i(roo);
//	panelPropsOO3 pOO3 = new panelPropsOO3(roo);
//	panelPropsOO4 pOO4 = new panelPropsOO4(roo);
//	panelPropsOO5 pOO5 = new panelPropsOO5(roo);
//	panelPropsOO6 pOO6 = new panelPropsOO6(roo);
//	panelPropsOO7 pOO7 = new panelPropsOO7(roo);
//	panelPropsOO8 pOO8 = new panelPropsOO8(roo);
//	panelPropsOO9 pOO9 = new panelPropsOO9(roo);
//	panelPropsO91 pO91 = new panelPropsO91(roo);
//
    private JOutlookBar outlook;
    DAOOOSSAD DAO = new DAOOOSSAD();

	public Tblindic tblindic;
    JButton jButton1;
    JButton jButton2;
    JButton jButton3;
    JButton jButton4;

    /**
	 * @param owner
	 */
	public ooIndicsGenericDlg(Frame owner) {
		super(owner);
		initialize();
	}
	public ooIndicsGenericDlg(Frame owner, Integer IId) {
		super(owner);
		
		//TODO: acceso a la BD 
		if (IId.equals("")){
			JOptionPane.showMessageDialog(
					null,"Debe seleccionar un indicador para poder abrir sus propiedades.", "Imposible abrir propiedades",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		tblindic = DAO.getTblindicOBJ(IId);
		//roo.cargaOpenOSSADgraph(this,IId);
		initializeOO();
		initialize();
		
		pack();
		setSize(720, 530);
		setModal(true);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * 
	 */
	private void initializeOO() {
		// TODO Auto-generated method stub
		
		ListRendererRelations renderR = new ListRendererRelations();
		ComboBoxActorRenderer renderActor = new ComboBoxActorRenderer();
		//Indic
		pOO1.txt2.setText(tblindic.getIname());
		pOO1.txt3.setText(tblindic.getIref());
		pOO1.comboD.setSelectedIndex(tblindic.getItype());
//
//		pOO2.txtAFinalidad.setText(tblindic.getIdescription());
//		pOO2.txtGName.setText(tblindic.getIobjectif()+"");
//		pOO2.txtGName.setText(tblindic.getIponderation()+"");
//		pOO2.txtGName.setText(tblindic.getInbVal()+"");
//		pOO2.documentToCatch.setText(tblindic.getInbDec()+"");
//		pOO2.txt6.setText(tblindic.getIresponsable()+"");
//
//		pOO6.genericListModelHibernate_1.clear();
//		pOO6.genericListModelHibernate_1.connQuery="SELECT * FROM ooCells WHERE shTypeId in (333,3,5,16,132,133,112,113,114)";// AND shGraphID='"+GId+"'";
//		pOO6.genericListModelHibernate_1.loadDataFromODBC();
//		pOO6.jxList.setModel(pOO6.genericListModelHibernate_1);
//		pOO6.jxList.setCellRenderer(renderR);
//		pOO6.createdDate.setText(pOO6.genericListModelHibernate_1.getSize()+"");
//		//documentos
//		pOO7.genericListModelHibernate_1.clear();
//		pOO7.genericListModelHibernate_1.connQuery="SELECT * FROM ooCells WHERE shTypeId in (8,144) ";//AND shGraphID='"+GId+"'";
//		pOO7.genericListModelHibernate_1.loadDataFromODBC();
//		pOO7.jxList.setModel(pOO7.genericListModelHibernate_1);
//		pOO7.jxList.setCellRenderer(renderR);
//		pOO7.createdDate.setText(pOO7.genericListModelHibernate_1.getSize()+"");
//		//roles
//		pOO8.genericListModelHibernate_1.clear();
//		pOO8.genericListModelHibernate_1.connQuery="SELECT * FROM ooCells WHERE shTypeId in (6,9,10,111,131,804,805,806,821,822,823) ";//AND shGraphID='"+GId+"'";
//		pOO8.genericListModelHibernate_1.loadDataFromODBC();
//		pOO8.jxList.setModel(pOO8.genericListModelHibernate_1);
//		pOO8.jxList.setCellRenderer(renderR);
//		pOO8.createdDate.setText(pOO8.genericListModelHibernate_1.getSize()+"");
//		//indicadores
//		pOO9.genericListModelHibernate_1.clear();
//		pOO9.genericListModelHibernate_1.connQuery="SELECT * FROM ooCells WHERE shTypeId in (137)";// AND shGraphID='"+GId+"'";
//		pOO9.genericListModelHibernate_1.loadDataFromODBC();
//		pOO9.jxList.setModel(pOO9.genericListModelHibernate_1);
//		pOO9.jxList.setCellRenderer(renderR);
//		pOO9.createdDate.setText(pOO9.genericListModelHibernate_1.getSize()+"");
//		//Medios
//		pO91.genericListModelHibernate_1.clear();
//		pO91.genericListModelHibernate_1.connQuery="SELECT * FROM ooCells WHERE shTypeId in (7,14,145)";// AND shGraphID='"+GId+"'";
//		pO91.genericListModelHibernate_1.loadDataFromODBC();
//		pO91.jxList.setModel(pO91.genericListModelHibernate_1);
//		pO91.jxList.setCellRenderer(renderR);
//		pO91.createdDate.setText(pO91.genericListModelHibernate_1.getSize()+"");
//
//
//		///////////////////////////////////////////
//		panelPropsOO1.add(getJButton1(), null);
//		panelPropsOO1.add(getJButton2(), null);
//		panelPropsOO1.add(getJButton3(), null);
//
//		pOO2.add(getJButton1(), null);
//		pOO2.add(getJButton2(), null);
//		pOO2.add(getJButton3(), null);
//		pOO3.add(getJButton1(), null);
//		pOO3.add(getJButton2(), null);
//		pOO3.add(getJButton3(), null);
//		pOO4.add(getJButton1(), null);
//		pOO4.add(getJButton2(), null);
//		pOO4.add(getJButton3(), null);
//		pOO4.add(getJButton4(450, 380, 130, 25), null);
//		pOO5.add(getJButton1(), null);
//		pOO5.add(getJButton2(), null);
//		pOO5.add(getJButton3(), null);
//		pOO6.add(getJButton1(), null);
//		pOO6.add(getJButton2(), null);
//		pOO6.add(getJButton3(), null);
//		pOO7.add(getJButton1(), null);
//		pOO7.add(getJButton2(), null);
//		pOO7.add(getJButton3(), null);
//		pOO8.add(getJButton1(), null);
//		pOO8.add(getJButton2(), null);
//		pOO8.add(getJButton3(), null);
//		pOO9.add(getJButton1(), null);
//		pOO9.add(getJButton2(), null);
//		pOO9.add(getJButton3(), null);
//
//		pO13.add(getJButton1(), null);
//		pO13.add(getJButton2(), null);
//		pO13.add(getJButton3(), null);
//		///////////////////////////////////////////
		
	}
	
	
	private JButton getJButton1() {
		//
		jButton1 = new JButton("Aplicar");
		jButton1.setBounds(new Rectangle(480, 435, 103, 30));
		jButton1.setToolTipText("OK");
		jButton1.addMouseListener(mouseListener2);
		
		return jButton1;
	}
	private JButton getJButton2() {
		
		jButton2 = new JButton("OK");
		jButton2.setBounds(new Rectangle(360, 435, 103, 30));
		jButton2.setToolTipText("OK");
		jButton2.addMouseListener(mouseListener2);
		
		return jButton2;
	}
	private JButton getJButton3() {
		
		jButton3 = new JButton("Cancelar");
		jButton3.setBounds(new Rectangle(240, 435, 103, 30));
		jButton3.setToolTipText("OK");
		jButton3.addMouseListener(mouseListener2);
		
		return jButton3;
	}
	private JButton getJButton4(int xx, int yy, int width, int height) {
		
			jButton4 = new JButton("Nueva versión");
			jButton4.setBounds(new Rectangle(xx, yy, width, height));
			jButton4.addMouseListener(mouseListener2);
		
		return jButton4;
	}
	MouseListener mouseListener2 = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			JButton wek = (JButton) e.getSource();
			if (wek.getText().equals("Cancelar")){
				dispose();
			}
			if (wek.getText().equals("OK")){
				saveOOG();
				dispose();
			}
			if (wek.getText().equals("Aplicar")){
				saveOOG();
				
			}
		}
	};
	
	public void saveOOG(){
		
//		tblindic.setIname(panelPropsOO1.txtGName.getText());
//		tblindic.setIref(panelPropsOO1.txtGName.getText());
//		tblindic.setItype(panelPropsOO1.comboD.getSelectedIndex());
//		tblindic.setIdescription(pOO2.txtAFinalidad.getText());
//		tblindic.setIobjectif(Float.parseFloat(pOO2.txtGName.getText()));
//		tblindic.setIponderation(Integer.parseInt(pOO2.txtGName.getText()));
//		tblindic.setInbVal(Integer.parseInt(pOO2.txtGName.getText()));
//		tblindic.setInbDec(Integer.parseInt(pOO2.documentToCatch.getText()));
//		tblindic.setIresponsable(pOO2.txt6.getText());
		
		DAO.updateooIndicOBJ(new Object[]{tblindic});
		//roo.guardaOpenOSSADgraph(this);
	}
	
	
	
	/**
	 * @param onlyLink
	 * @return
	 */
	private Boolean parseBoolean(Boolean onlyLink) {
		// TODO Auto-generated method stub
		return false;
	}
	public String parseBoolean(String b){
		return (b=="true") ? "1" :"0"; 
	}
	public String parseComboProject(){
		String[] str1 = new String[3];
		int R = 0;
//		for (Object obj : (Object[]) panelPropsOO1.comboTblprojects.getSelectedItem()) {
//			str1[R] = obj.toString();
//			R++;
//		}
		return str1[0];
	}
	
	
	/**
	 * 
	 */
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		setLayout(new BorderLayout());
		try {
			imageIcon = new ImageIcon(new java.net.URL(OpenDESIGNER.class.getResource("/ui/images/background1.png"), "background1.png"));
			image = imageIcon.getImage();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JXErrorDialog.showDialog(null, "No se puede cargar la imágen de fondo", e);
		}
		this.setSize(720, 530);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(getJContentPane());
		this.setTitle("Propiedades de "+ tblindic.getIname());
		add("West", makeOutlookPanel());
		add("Center", getpanelOpenOO1());

	}
	private Component getpanelOpenOO1() {
		currentComponent = pOO1;
		return currentComponent;

	}
	public void show(Component component) {
		if (currentComponent != null) {
			remove(currentComponent);
			remove(outlook);
			repaint();
		}
		currentComponent = component;
		add("Center", currentComponent = component);

		add("West", makeOutlookPanel());

		// revalidate();

		repaint();
		validate();
	}


	

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
		}
		return jContentPane;
	}

	

	JPanel makeOutlookPanel() { // int alignment
		outlook = new org.openossad.ui.component.JOutlookBar(mouseListener){
			{setOpaque(false);}  // instance initializer
			public void paintComponent (Graphics g) {
		    g.drawImage(image, 0, 0, this);
		    super.paintComponent(g);

		}};
//        outlook = new JOutlookBar();
		//outlook.setTabPlacement(JTabbedPane.LEFT);
		outlook.setSize(150, 0);
		String[] buttons;
		buttons = new String[]{"General", "/ui/images/frmPropiedades/general_48.png",
				"Resumen", "/ui/images/frmPropiedades/resumen_48.png","Personal", "/ui/images/frmPropiedades/personal_48.png",
		};
		outlook.addTab("       General      ", buttons);

		buttons = new String[]{
				"Gráficos", "/ui/images/frmPropiedades/graficos_48.png","Documentos", "/ui/images/frmPropiedades/documentos_48.png",
				"Roles", "/ui/images/frmPropiedades/roles_48.png", "Indicadores", "/ui/images/frmPropiedades/indicadores_48.png",
				"Medios", "/ui/images/frmPropiedades/gear.png",};
		outlook.addTab("    Elementos    ", buttons);
		
		buttons = new String[]{"Restricciones", "/ui/images/frmPropiedades/restricciones_48.png",};
		outlook.addTab("  Seguridad   ", buttons);
	
		PercentLayout PL = new PercentLayout(PercentLayout.HORIZONTAL, 2);
		PL.minimumLayoutSize(outlook);
		panel = new JPanel(PL);
		outlook.setBackground(new Color(144, 153, 174));
		panel.add(outlook, null);
		return panel;
	};

	MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent mouseEvent) {
			JButton but = (JButton) mouseEvent.getSource();
//			if (but.getText().equals("General")){
//				show(panelPropsOO1);
//			}
//			else if (but.getText().equals("Resumen"))
//				show(pOO2);
//			else if (but.getText().equals("Personal"))
//				show(pOO3);
//			else if (but.getText().equals("Versión"))
//				show(pOO4);
//			else if (but.getText().equals("Iconos"))
//				show(pOO5);
//			else if (but.getText().equals("Gráficos"))
//				show(pOO6);
//			else if (but.getText().equals("Documentos"))
//				show(pOO7);
//			else if (but.getText().equals("Roles"))
//				show(pOO8);
//			else if (but.getText().equals("Indicadores"))
//				show(pOO9);
//
//			else if (but.getText().equals("Restricciones"))
//				show(pO13);
//			else if (but.getText().equals("Medios"))
//				show(pO91);
//
//			else {
//				show(panelPropsOO1);
//
//			}

			if (mouseEvent.getClickCount() == 2) {
				// System.out.println("Double-clicked");
				// SwingXDemo.this.descriptionContainer.setTitle("we");
				// SwingXDemo.this.demoContainer.setTitle(but.getText());
				// build(getDemoContainer(), null, true, but.getText());
			} else {

				//   .println("Simple-clicked");

			}
		}
	};


	public static void main(String[] args) {
		try {
			ooIndicsGenericDlg oo1 = new ooIndicsGenericDlg(null,0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
