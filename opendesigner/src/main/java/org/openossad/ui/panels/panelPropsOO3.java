package org.openossad.ui.panels;

import org.jdesktop.swingx.JXList;
import org.openossad.ui.base.GenericListModelHibernate;
import org.openossad.ui.base.PanelPropsBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class panelPropsOO3 extends PanelPropsBase
{

	public JLabel lbl2;
	public JLabel lbl3;
	public JTextField txt1;
	public JTextField txt2;
	public JTextField txt3;
	private JXList jxList;
	private JScrollPane scrollPane;
	private JButton jButton4 = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	//"pvCode","pvDescription","pvType","pvGraphUse","pvDocUse","pvEntityUse","pvFormUse","pvActorUse","pvFolderUse","pvOrder","pvLongDescription","pvIndicatorUse","pvUseDetail"
	/////////////////////////////////////
	public String pvCode;
	public String pvDescription;
	public String pvType;
	public Boolean pvGraphUse;
	public Boolean pvDocUse;
	public Boolean pvEntityUse;
	public Boolean pvFormUse;
	public Boolean pvActorUse;
	public Boolean pvFolderUse;
	public String pvOrder;
	public String pvLongDescription;
	public String pvIndicatorUse;
	public String pvUseDetail;

	public panelPropsOO3() {
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
		add(gui.getLblTit("Propiedades personalizadas", 0, 0, 600, 30), null);
		
		add(gui.getLbl("Propiedades personalizadas del gráfico:", 20, 43, 580, 20), null);
		add(getxjList(20, 60, 470, 300), null);
		
		add(getJButton1(500, 60, 80, 30), null);
		add(getJButton2(500, 110, 80, 30), null);
		add(getJButton3(500, 160, 80, 30), null);
		
		add(gui.getDivider(this,427), null);
		this.setVisible(true);
	}
	private JComponent getxjList(int xx, int yy, int width, int height)  {
		//Aqui la query sale que 
		genericListModelHibernate_1 = new GenericListModelHibernate();
		jxList = new JXList(genericListModelHibernate_1);
		jxList.setCellRenderer(comboBoxPropertyRenderer);
		genericListModelHibernate_1.connQuery = "SELECT pvDescription FROM tblproperties";
		genericListModelHibernate_1.loadDataFromODBC();
		JScrollPane scrollPane = new JScrollPane(jxList);
		scrollPane.setBounds(new Rectangle(xx, yy, width, height));
		jxList.addMouseListener(xjListListener);
		return scrollPane;
	}
	protected void refreshME() {
		// TODO Auto-generated method stub
		genericListModelHibernate_1.clear();
		genericListModelHibernate_1.connQuery = "SELECT pvDescription FROM tblproperties";
		genericListModelHibernate_1.loadDataFromODBC();
		jxList.setModel(genericListModelHibernate_1);
	}
	MouseListener xjListListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {
			// JButton but = (JButton) mouseEvent.getSource();
			if (event.getClickCount()==2){
				//(jxList.getSelectedValue());  
				//jxList.getSelectedIndex();
				//genericListModelHibernate_2.addElement(jxList.getSelectedValue());jxList2.setModel(genericListModelHibernate_2);
				//genericListModelHibernate_1.remove(jxList.getSelectedIndex());jxList.setModel(genericListModelHibernate_1);
			}
		}
	};
	private JButton getJButton1(int xx, int yy, int width, int height) {
		if (jButton1 == null) {
			jButton1 = new JButton("Arriba");
			jButton1.setBounds(new Rectangle(xx, yy, width, height));
			jButton1.addMouseListener(mouseListener);
		}
		return jButton1;
	}
	private JButton getJButton2(int xx, int yy, int width, int height) {
		if (jButton2 == null) {
			jButton2 = new JButton("Abajo");
			jButton2.setBounds(new Rectangle(xx, yy, width, height));
			jButton2.addMouseListener(mouseListener);
		}
		return jButton2;
	}

	private JButton getJButton3(int xx, int yy, int width, int height) {
		if (jButton3 == null) {
			jButton3 = new JButton("...");
			jButton3.setBounds(new Rectangle(xx, yy, width, height));
			jButton3.addMouseListener(mouseListener);
		}
		return jButton3;
	}
	


	MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {
			// JButton but = (JButton) mouseEvent.getSource();

			if (event.getSource().equals(jButton3)) {
				cargaPropsDlg();
				
			
			}
		}
	};
	/**
	 * 
	 */
	protected void cargaPropsDlg() {
		//ooPropsPersDlg opp = new ooPropsPersDlg();
		refreshME();
	}

}
