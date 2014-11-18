package org.openossad.ui;

import com.l2fprod.common.swing.PercentLayout;
import org.openossad.data.domain.OoCells;
import org.openossad.data.domain.OoDocFlow;
import org.openossad.data.domain.Tblaccounts;
import org.openossad.data.domain.Tblgraphs;
import org.openossad.data.util.OpenossadData;
import org.openossad.ui.base.BaseJDialog;
import org.openossad.ui.component.JOutlookBar;
import org.openossad.ui.panels.*;
import org.openossad.ui.renderers.RActorRenderer;
import org.openossad.util.ImageUtil;
import org.openossad.util.helper.DAOOOSSAD;
import org.openossad.util.helper.ReferencialOOSSAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Calendar;
import java.util.List;

public class ooPropsGenericDlg extends BaseJDialog
{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panel = null;
	public Component currentComponent;
	private JOutlookBar outlook;
	protected ImageIcon imageIcon;
	protected Image image = null;

	/////////////////////////////////
	
	panelPropsOO1 panelPropsOO1 = new panelPropsOO1();
	panelPropsOO1d panelPropsOO1Documents = new panelPropsOO1d();
	panelPropsOO2 pOO2 = new panelPropsOO2();
	panelPropsOO3 pOO3 = new panelPropsOO3();
	panelPropsOO4 pOO4 = new panelPropsOO4();
	panelPropsOO5 pOO5 = new panelPropsOO5();
	panelPropsOO6 pOO6 = new panelPropsOO6();
	panelPropsOO7 pOO7 = new panelPropsOO7();
	panelPropsOO8 pOO8 = new panelPropsOO8();
	panelPropsOO9 pOO9 = new panelPropsOO9();
	panelPropsO91 pO91 = new panelPropsO91();
	panelPropsO10 pO10 = new panelPropsO10();
	panelPropsO11 pO11 = new panelPropsO11();
	panelPropsO12 pO12 = new panelPropsO12();
	panelPropsO13 pO13 = new panelPropsO13();

    private Tblgraphs currentTblgraphs;
    private RActorRenderer actorRenderer = new RActorRenderer();

    /**
	 * @param owner
	 */
	public ooPropsGenericDlg(Frame owner) {
		super(owner);
		initialize();
	}
	public ooPropsGenericDlg(Frame owner,String GId) {
		super(owner);
		if (GId.equals("")){
			JOptionPane.showMessageDialog(null,"Debe seleccionar un gráfico para poder abrir sus propiedades.", "Imposible abrir propiedades",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		currentTblgraphs = DAOOOSSAD.getooGraphOBJ(GId);
		initializeOO(currentTblgraphs);
		initialize();
	}

	/**
	 *
     * @param currentTblgraphs
     */
	private void initializeOO(Tblgraphs currentTblgraphs) {
		// TODO Auto-generated method stub
        String GLevel = this.currentTblgraphs.getGlevel()+"";
        if (!GLevel.equals("4")) {
			panelPropsOO1.txtGName.setText(this.currentTblgraphs.getGname());
			panelPropsOO1.txtGRef.setText(this.currentTblgraphs.getGref());
			if (GLevel.equals("1") || GLevel.equals("5") || GLevel.equals("8") ||GLevel.equals("11")) panelPropsOO1.rb1.setSelected(true);
			if (GLevel.equals("2") || GLevel.equals("6") || GLevel.equals("9") ||GLevel.equals("12")) panelPropsOO1.rb2.setSelected(true);
			if (GLevel.equals("3") || GLevel.equals("7") || GLevel.equals("10") ||GLevel.equals("13")) panelPropsOO1.rb3.setSelected(true);
			
			if (this.currentTblgraphs.getGvisPublic().equals(true)) panelPropsOO1.check1.setSelected(true); //visPublic
			//if (currentTblgraphs.getGdocGen().equals("1")) panelPropsOO1.check2.setSelected(true); //docGen
			//setComboProject();

        }
		else {
            panelPropsOO1Documents.txtGName.setText(this.currentTblgraphs.getGname());
            panelPropsOO1Documents.txtGRef.setText(this.currentTblgraphs.getGref());
            String fileDoc=ReferencialOOSSAD.getParsedModelDoc(this.currentTblgraphs.getGid(), this.currentTblgraphs.getGlinkDoc());
            panelPropsOO1Documents.txt4.setText(fileDoc);
            File fileD=new File(fileDoc);
            if (this.currentTblgraphs.getGlinkDoc().lastIndexOf(File.separator)>0) {
                panelPropsOO1Documents.check2.setSelected(true);
            }

            if (this.currentTblgraphs.getGvisPublic().equals(true)) panelPropsOO1Documents.check1.setSelected(true); //visPublic
            //if (GDocGen.equals("1")) panelPropsOO1Documents.check2.setSelected(true); //docGen
            panelPropsOO1Documents.add(GUI.getLbl(fileD.getUsableSpace()+" ", 120, 340, 130, 20));
            panelPropsOO1Documents.add(GUI.getLbl(fileDoc, 120, 370, 400, 20));
            panelPropsOO1Documents.comboD.setSelectedItem(this.currentTblgraphs.getGtype());
            //setComboProjectd();

        }


        String GId = currentTblgraphs.getGid();

        pOO2.txt1.setText(currentTblgraphs.getGkeywords());
        pOO2.txt2.setText(currentTblgraphs.getGabrList()); //exigencias
        pOO2.txt3.setText(currentTblgraphs.getGfield1());
        pOO2.txt4.setText(currentTblgraphs.getGfield2());
        pOO2.txt5.setText(currentTblgraphs.getGfield3());
        pOO2.txt6.setText(currentTblgraphs.getGauthor()); //redactor
        pOO2.txtAFinalidad.setText(currentTblgraphs.getGobject());
        pOO2.txtADescription.setText(currentTblgraphs.getGdomain());
        String text3 = currentTblgraphs.getGdesignDate().toString() + "";
        String text2 = currentTblgraphs.getGversionNumber() + "";
        String text1 = currentTblgraphs.getGupdateDate().toString() + "";
        String text = currentTblgraphs.getGindice()+"";
        String text4 = currentTblgraphs.getGnews() + "";
        text1="";text2="";text3="";text4="";
        pOO4.lblIndice.setText(text);
        pOO4.txtModificado.setText(text1);
        pOO4.txtVersion.setText(text2);
        pOO4.createdDate.setText(text3);
        pOO4.txtANovedades.setText(text4);
        //if (currentTblgraphs.getGGraphState().equals("1")) pOO4.comboF.setSelectedIndex(1); //estado
        pOO4.genericListModelHibernate_1.clear();
        pOO4.genericListModelHibernate_1.connQuery = ""; //"SELECT GArchiveDate FROM atblgraphs  WHERE GId='"+ GId +"'";
        pOO4.genericListModelHibernate_1.loadDataFromODBC();
        pOO4.jxList.setModel(pOO4.genericListModelHibernate_1);
        pOO4.lblTotalNumberFiles.setText(pOO4.genericListModelHibernate_1.getSize()+" ");

        List<OoCells> ooCellsList = DAOFactory.getOoCellsDAO().getOoCellsInTblgraphs(GId);
        //Graficos
        pOO6.genericListModelHibernate_1.clear();

        List<OoCells> ooCellsProcessList = OpenossadData.getOneType(ooCellsList, OpenossadData.process);
        pOO6.genericListModelHibernate_1.loadDataFromDAO(ooCellsProcessList);
		pOO6.jxList.setModel(pOO6.genericListModelHibernate_1);
		pOO6.lbl2.setText(pOO6.genericListModelHibernate_1.getSize()+"");
		//documentos
		pOO7.genericListModelHibernate_1.clear();
		List<OoCells> ooCellsDocumentsList = OpenossadData.getOneType(ooCellsList, OpenossadData.document);
        pOO7.genericListModelHibernate_1.loadDataFromDAO(ooCellsDocumentsList);
		pOO7.jxList.setModel(pOO7.genericListModelHibernate_1);
		pOO7.lbl2.setText(pOO7.genericListModelHibernate_1.getSize()+"");
		//roles
		pOO8.genericListModelHibernate_1.clear();
		List<OoCells> ooCellsRolesList = OpenossadData.getOneType(ooCellsList, OpenossadData.role);
        pOO8.genericListModelHibernate_1.loadDataFromDAO(ooCellsRolesList);
		pOO8.jxList.setModel(pOO8.genericListModelHibernate_1);
		pOO8.lbl2.setText(pOO8.genericListModelHibernate_1.getSize()+"");
		//indicadores
		pOO9.genericListModelHibernate_1.clear();
		List<OoCells> ooCellsIndicList = OpenossadData.getOneType(ooCellsList, OpenossadData.indic);
        pOO8.genericListModelHibernate_1.loadDataFromDAO(ooCellsIndicList);
		pOO9.jxList.setModel(pOO9.genericListModelHibernate_1);
		pOO9.lbl2.setText(pOO9.genericListModelHibernate_1.getSize()+"");
		//Medios
		pO91.genericListModelHibernate_1.clear();
		List<OoCells> ooCellsMediosList = OpenossadData.getOneType(ooCellsList, OpenossadData.medio);
        pOO8.genericListModelHibernate_1.loadDataFromDAO(ooCellsMediosList);
		pO91.jxList.setModel(pO91.genericListModelHibernate_1);
		pO91.lbl2.setText(pO91.genericListModelHibernate_1.getSize()+"");

        /////////////////////////////////////////////////////////////////////////////
        List<OoDocFlow> ooDocFlowList = DAOFactory.getOoDocFlowDAO().findAllByGraphId(GId);
        List<Tblaccounts> tblaccountsList = DAOFactory.getTblaccountsDAO().findAll();

        //Aprobadores
        pO10.genericListModelHibernate_1.clear();
        List<Tblaccounts> accountsAprobadoresList = DAOFactory.getTblaccountsDAO().getAccountsActorsList(tblaccountsList, ooDocFlowList, false,OpenossadData.actorAprobador);
        pO10.genericListModelHibernate_1.loadDataFromDAO(accountsAprobadoresList);
        pO10.jxList.setModel(pO10.genericListModelHibernate_1);
        pO10.jxList.setCellRenderer(actorRenderer);
        pO10.genericListModelHibernate_2.clear();
        List<Tblaccounts> accountsAprobadores2List = DAOFactory.getTblaccountsDAO().getAccountsActorsList(tblaccountsList, ooDocFlowList, true, OpenossadData.actorAprobador);
        pO10.genericListModelHibernate_2.loadDataFromDAO(accountsAprobadores2List);
        pO10.jxList2.setModel(pO10.genericListModelHibernate_2);
        pO10.jxList2.setCellRenderer(actorRenderer);

        //Redactores
        pO11.genericListModelHibernate_1.clear();
        List<Tblaccounts> accountsRedactorList = DAOFactory.getTblaccountsDAO().getAccountsActorsList(tblaccountsList, ooDocFlowList, false,OpenossadData.actorRedactor);
        pO11.genericListModelHibernate_1.loadDataFromDAO(accountsRedactorList);
        pO11.jxList.setModel(pO11.genericListModelHibernate_1);
        pO11.jxList.setCellRenderer(actorRenderer);
        pO11.genericListModelHibernate_2.clear();
        List<Tblaccounts> accountsRedactor2List = DAOFactory.getTblaccountsDAO().getAccountsActorsList(tblaccountsList, ooDocFlowList, true,OpenossadData.actorRedactor);
        pO11.genericListModelHibernate_2.loadDataFromDAO(accountsRedactor2List);
        pO11.jxList2.setModel(pO11.genericListModelHibernate_2);
        pO11.jxList2.setCellRenderer(actorRenderer);

        //Lectores
        pO12.genericListModelHibernate_1.clear();
        List<Tblaccounts> accountsLectoresList = DAOFactory.getTblaccountsDAO().getAccountsActorsList(tblaccountsList, ooDocFlowList, false,OpenossadData.actorLector);
        pO12.genericListModelHibernate_1.loadDataFromDAO(accountsLectoresList);
        pO12.jxList.setModel(pO12.genericListModelHibernate_1);
        pO12.jxList.setCellRenderer(actorRenderer);
        pO12.genericListModelHibernate_2.clear();
        List<Tblaccounts> accountsLectores2List = DAOFactory.getTblaccountsDAO().getAccountsActorsList(tblaccountsList, ooDocFlowList, true,OpenossadData.actorLector);
		pO12.genericListModelHibernate_2.loadDataFromDAO(accountsLectores2List);
		pO12.jxList2.setModel(pO12.genericListModelHibernate_2);
		pO12.jxList2.setCellRenderer(actorRenderer);

		
	}



    MouseListener mouseListener2 = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			JButton wek = (JButton) e.getSource();
			if (wek.getText().equals("Cancelar")){
				dispose();
			}
			if (wek.getText().equals("OK")){
				saveTblgraphsUpdated();
				dispose();
			}
			if (wek.getText().equals("Aplicar")){
				saveTblgraphsUpdated();
				
			}
			if (wek.getText().equals("Nueva versión")){
				saveTblgraphsUpdated();
				saveVer();
				
			}
			
			
		}
	};
	public void saveVer(){
		
		
//		if (pOO4.txtAFinalidad.getText().equals("") || pOO4.txtVersion.getText().equals("")) {
//			JOptionPane.showMessageDialog(null,"Debe informar del número de versión y \nlas novedades en el documento.", "OpenDESIGNER",
//				JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//
//		Integer res = JOptionPane.showConfirmDialog(null,"¿ Desea crear una nueva versión del gráfico ?", "OpenDESIGNER",
//				JOptionPane.INFORMATION_MESSAGE);
//		if (res.equals(0)) {
//
//			String tempVersion="0000"+pOO4.lblTotalNumberFiles.getText().trim(); //GVersionNumber;
//			tempVersion=tempVersion.substring(tempVersion.length()-4,tempVersion.length());
//			roo.GenerarVersion(GId,tempVersion);
//
//			pOO4.genericListModelHibernate_1.clear();
//			pOO4.genericListModelHibernate_1.connQuery = "SELECT GArchiveDate FROM atblgraphs WHERE GId='"+ GId +"'";
//			pOO4.genericListModelHibernate_1.loadDataFromODBC();
//			pOO4.jxList.setModel(pOO4.genericListModelHibernate_1);
//			pOO4.lblTotalNumberFiles.setText(pOO4.genericListModelHibernate_1.getSize()+" ");
//
//			JOptionPane
//			.showMessageDialog(null,"Se ha creado con éxito la nueva versión del gráfico.","OpenDESIGNER",
//					JOptionPane.INFORMATION_MESSAGE);
//		}
	}
	
	public void saveTblgraphsUpdated(){

        if(currentTblgraphs.getGlevel() == 4)
        {
            currentTblgraphs.setGname(panelPropsOO1Documents.txtGName.getText());
            currentTblgraphs.setGref(panelPropsOO1Documents.txtGRef.getText());

            currentTblgraphs.setGvisPublic(panelPropsOO1Documents.check1.isSelected());
            currentTblgraphs.setGtype(panelPropsOO1Documents.comboD.getSelectedItem().toString());

        }
        else
        {
            currentTblgraphs.setGname(panelPropsOO1.txtGName.getText());
            currentTblgraphs.setGref(panelPropsOO1.txtGRef.getText());
            currentTblgraphs.setGlevel(parseGLevel(currentTblgraphs.getGlevel()));

            currentTblgraphs.setGvisPublic(panelPropsOO1.check1.isSelected());
            doGuardarActores();
        }
        currentTblgraphs.setProjectId(parseComboProjectd());

        currentTblgraphs.setGkeywords(pOO2.txt1.getText());
		currentTblgraphs.setGabrList(pOO2.txt2.getText());
		currentTblgraphs.setGfield1(pOO2.txt3.getText());
		currentTblgraphs.setGfield2(pOO2.txt4.getText());
		currentTblgraphs.setGfield3(pOO2.txt5.getText());
		currentTblgraphs.setGauthor(pOO2.txt6.getText());
		currentTblgraphs.setGobject(pOO2.txtAFinalidad.getText());
		currentTblgraphs.setGdomain(pOO2.txtADescription.getText());

		currentTblgraphs.setGversionNumber(pOO4.txtVersion.getText());
		currentTblgraphs.setGupdateDate(Calendar.getInstance().getTime());
//        if (pOO4.lblIndice.getText()!=null) {
//            currentTblgraphs.setGindice(Integer.parseInt(pOO4.lblIndice.getText()));
//        }
		currentTblgraphs.setGnews(pOO4.txtANovedades.getText());
		//currentTblgraphs.setGGraphState(String.valueOf(pOO4.comboF.getSelectedIndex());

		Object[] resO10=new Object[1];
		Object[] resO11=new Object[1];
        StringBuilder resO10b=new StringBuilder();
        StringBuilder resO11b=new StringBuilder();

		for (int x=0;x<pO10.genericListModelHibernate_2.getSize();x++) {
			resO10 = (Object[]) pO10.genericListModelHibernate_2.getElementAt(x);
			resO10b.append(resO10[1].toString()).append(" ");
		}

		for (int x=0;x<pO11.genericListModelHibernate_2.getSize();x++) {
			resO11 = (Object[]) pO11.genericListModelHibernate_2.getElementAt(x);
			resO11b.append(resO11[1].toString()).append(" ");
		}


		currentTblgraphs.setGverifNames(resO10b.toString());
		currentTblgraphs.setGapprobNames(resO11b.toString());



	}

    private int parseGLevel(int GLevel)
    {
       if (GLevel==1 || GLevel==2 || GLevel==3) {
                if (panelPropsOO1.rb1.isSelected()==true) GLevel=1;
                if (panelPropsOO1.rb2.isSelected()==true) GLevel=2;
                if (panelPropsOO1.rb3.isSelected()==true) GLevel=3;
            }
            if (GLevel==4 || GLevel==6 || GLevel==7) {
                if (panelPropsOO1.rb1.isSelected()==true) GLevel=5;
                if (panelPropsOO1.rb2.isSelected()==true) GLevel=6;
                if (panelPropsOO1.rb3.isSelected()==true) GLevel=7;
            }
            if (GLevel==8 || GLevel==9 || GLevel==10) {
                if (panelPropsOO1.rb1.isSelected()==true) GLevel=8;
                if (panelPropsOO1.rb2.isSelected()==true) GLevel=9;
                if (panelPropsOO1.rb3.isSelected()==true) GLevel=1;
            }
            if (GLevel==11 || GLevel==12 || GLevel==13) {
                if (panelPropsOO1.rb1.isSelected()==true) GLevel=11;
                if (panelPropsOO1.rb2.isSelected()==true) GLevel=12;
                if (panelPropsOO1.rb3.isSelected()==true) GLevel=13;
            }
        return GLevel;
    }

    private String parseComboProjectd()
    {
        return currentTblgraphs.getProjectId();
    }


    /**
	 * 
	 */
	private void doGuardarActores() {
        String GId = currentTblgraphs.getGid();
        DAOOOSSAD.deleteoodocflowOBJ(GId);
		DAOOOSSAD.insertOoDocFlowFromListModel(GId,pO10.genericListModelHibernate_2, 2);
        DAOOOSSAD.insertOoDocFlowFromListModel(GId,pO11.genericListModelHibernate_2, 3);
        DAOOOSSAD.insertOoDocFlowFromListModel(GId,pO12.genericListModelHibernate_2, 1);
	}



    private void initialize() {
		setLayout(new BorderLayout());
        imageIcon = ImageUtil.createImageIcon("/ui/images/background1.png");
        image = imageIcon.getImage();
        this.setSize(720, 530);
		this.setContentPane(getJContentPane());
		this.setTitle("Propiedades de "+currentTblgraphs.getGname());
		add("West", makeOutlookPanel());
		add("Center", getpanelOpenOO1());

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

	private Component getpanelOpenOO1() {
		if (currentTblgraphs.getGlevel()!=4) {
				currentComponent= panelPropsOO1;
		} else {
				currentComponent= panelPropsOO1Documents;
		}
		
		return currentComponent;

	}

	JPanel makeOutlookPanel() { // int alignment
		outlook = new JOutlookBar(mouseListener){
			{setOpaque(false);}  // instance initializer
			public void paintComponent (Graphics g) {
		    g.drawImage(image, 0, 0, this);
		    super.paintComponent(g);
			
		}};
		outlook.setSize(150, 0);
		String[] buttons;
		buttons = new String[]{"General", "/ui/images/frmPropiedades/general_48.png",
				"Resumen", "/ui/images/frmPropiedades/resumen_48.png","Personal", "/ui/images/frmPropiedades/personal_48.png",
				"Versión", "/ui/images/frmPropiedades/version_48.png","Iconos", "/ui/images/frmPropiedades/iconos_48.png",};
		outlook.addTab("       General      ", buttons);

		buttons = new String[]{
				"Gráficos", "/ui/images/frmPropiedades/graficos_48.png","Documentos", "/ui/images/frmPropiedades/documentos_48.png",
				"Roles", "/ui/images/frmPropiedades/roles_48.png", "Indicadores", "/ui/images/frmPropiedades/indicadores_48.png",
				"Medios", "/ui/images/frmPropiedades/gear.png",};
		outlook.addTab("    Elementos    ", buttons);
		
		buttons = new String[]{"Verificadores", "/ui/images/frmPropiedades/verificadores_48.png",
				"Aprobadores", "/ui/images/frmPropiedades/aprobadores_48.png",
				"Lectores", "/ui/images/frmPropiedades/lectores_48.png",};
		// "Familias", "/ui/images/paperpencil_48_2.png",};
		outlook.addTab("   Actores   ", buttons);
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
			if (but.getText().equals("General")){
                if(currentTblgraphs.getGlevel() == 4)
                {
                    show(panelPropsOO1Documents);
                }
                else
                {
                    show(panelPropsOO1);
                }
            }
			else if (but.getText().equals("Resumen"))
				show(pOO2);
			else if (but.getText().equals("Personal"))
				show(pOO3);
			else if (but.getText().equals("Versión"))
				show(pOO4);
			else if (but.getText().equals("Iconos"))
				show(pOO5);
			else if (but.getText().equals("Gráficos"))
				show(pOO6);
			else if (but.getText().equals("Documentos"))
				show(pOO7);
			else if (but.getText().equals("Roles"))
				show(pOO8);
			else if (but.getText().equals("Indicadores"))
				show(pOO9);
			else if (but.getText().equals("Verificadores"))
				show(pO10);
			else if (but.getText().equals("Aprobadores"))
				show(pO11);
			else if (but.getText().equals("Lectores"))
				show(pO12);
			else if (but.getText().equals("Restricciones"))
				show(pO13);
			else if (but.getText().equals("Medios"))
				show(pO91);

			else {
				if(currentTblgraphs.getGlevel() == 4)
                {
                    show(panelPropsOO1Documents);
                }
                else
                {
                    show(panelPropsOO1);
                }
			}

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
			ooPropsGenericDlg oo1 = new ooPropsGenericDlg(null,"OPS000-000000078");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public Tblgraphs getCurrentTblgraphs()
    {
        return currentTblgraphs;
    }
}
