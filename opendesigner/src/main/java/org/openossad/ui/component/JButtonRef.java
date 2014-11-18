package org.openossad.ui.component;

import org.jdesktop.swingx.JXList;
import org.openossad.ui.base.GenericListModelHibernate;
import org.openossad.util.helper.ReferencialOOSSAD;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonRef extends JButton {

	public JButtonRef() {

		// this.setComponentPopupMenu(popupMenu);

		this.setIcon(MetalIconFactory.getFileChooserUpFolderIcon());
		this.setBounds(550, 130, 20, 20);
		this.addActionListener(new popup4Listener());

	}

	public class popup4Listener implements ActionListener { // extends
		// AbstractAction
		ReferencialOOSSAD roo = null;

		popup4Listener() {

		}

		public void actionPerformed(ActionEvent e) {

//			ooNewGenericDlg dlg = (ooNewGenericDlg)
//				SwingUtilities.windowForComponent((Component) getParent().getParent());
//			String ct = dlg.currentComponent.getClass().getName();
//			ct = ct.substring((ct.length() - 3), ct.length());
//			String res;
//			if (ct.equals("OO1")) {
//				//getDlg("SELECT Distinct GRef FROM tblgraphs ORDER BY GRef", dlg);
//				res=getDlg2("Graphs", dlg);
//				panelNewOO1 p=(panelNewOO1) dlg.currentComponent;
//				if (!res.equals("-1")) p.txtGName.setText(res);
//			}
//			if (ct.equals("transferedObject")) {
//				//getDlg("SELECT Distinct GRef FROM tblgraphs ORDER BY GRef", dlg);
//				res=getDlg2("Graphs", dlg);
//				panelNewOO2 p=(panelNewOO2) dlg.currentComponent;
//				if (!res.equals("-1")) p.txtGName.setText(res);
//			}
//			if (ct.equals("OO3")) {
//				//getDlg("SELECT Distinct GRef FROM tblgraphs ORDER BY GRef", dlg);
//				res=getDlg2("Graphs", dlg);
//				panelNewOO3 p=(panelNewOO3) dlg.currentComponent;
//				if (!res.equals("-1")) p.txtGName.setText(res);
//			}
//			if (ct.equals("OO4")) {
//				//getDlg("SELECT Distinct GRef FROM tblgraphs ORDER BY GRef", dlg);
//				res=getDlg2("Graphs", dlg);
//				panelNewOO4 p=(panelNewOO4) dlg.currentComponent;
//				if (!res.equals("-1")) p.txtGName.setText(res);
//			}
//			if (ct.equals("OO5")) {
//				//getDlg("SELECT Distinct GRef FROM tblgraphs ORDER BY GRef", dlg);
//				res=getDlg2("Graphs", dlg);
//				panelNewOO5 p=(panelNewOO5) dlg.currentComponent;
//				if (!res.equals("-1")) p.txtGName.setText(res);
//			}
//
//			if (ct.equals("OO6")) {
//				//getDlg("SELECT Distinct IRef FROM tblgraphs ORDER BY IRef", dlg);
//				res=getDlg2("Graphs", dlg);
//				panelNewOO6 p=(panelNewOO6) dlg.currentComponent;
//				if (!res.equals("-1")) p.txtGName.setText(res);
//			}
//			if (ct.equals("OO7")) {
//				//getDlg("SELECT Distinct GRef FROM tblentity ORDER BY GRef", dlg);
//				res=getDlg2("Entity", dlg);
//				panelNewOO7 p=(panelNewOO7) dlg.currentComponent;
//				if (!res.equals("-1")) p.txtGName.setText(res);
//			}
//			if (ct.equals("OO8")) {
//				//getDlg("SELECT Distinct GRef FROM tblaccounts ORDER BY GRef",dlg);
//				res=getDlg2("Accounts", dlg);
//				panelNewOO8 p=(panelNewOO8) dlg.currentComponent;
//				if (!res.equals("-1")) p.txtGName.setText(res);
//			}
//
//			if (ct.equals("OO9")) {
//				//getDlg("SELECT Distinct GRef FROM tblaccounts ORDER BY GRef",dlg);
//				res=getDlg2("Accounts", dlg);
//				panelNewOO9 p=(panelNewOO9) dlg.currentComponent;
//				if (!res.equals("-1")) p.txtGName.setText(res);
//			}
			
		}

		
		
		
		
		public void getDlg(String Query, JDialog dlg) {

			JDialog jdlg = new JDialog(dlg);
			jdlg.setLayout(new BorderLayout());
			jdlg.setLocationRelativeTo(null);
			jdlg.setTitle("Referencias...");

			JDesktopPane jdp = new JDesktopPane();

			GenericListModelHibernate model = new GenericListModelHibernate();
			JXList jxList = new JXList(model);
			//genericListModelHibernate_1.connQuery = Query; // WHERE GLevel =
			// " + (GLevel+4); //in (5,6,7) ORDER BY GName"
			// ; ///,11,12,13
			model.loadDataFromODBC();
			JScrollPane scrollPane = new JScrollPane(jxList);
			scrollPane.setBounds(new Rectangle(1, 40, 298, 260));
			JButton btn1 = new JButton("Buscar");
			btn1.setBounds(new Rectangle(200, 7, 93, 26));
			JLabel lbl1 = new JLabel();
			lbl1.setBounds(new Rectangle(10, 7, 180, 26));

			jdp.add(lbl1, null);
			jdp.add(btn1, null);
			jdp.add(scrollPane, null);

			jdlg.add("Center", jdp);

			jdlg.pack();
			jdlg.setSize(300, 300);
			jdlg.setVisible(true);
		}

	}

	/**
	 * @param string
	 * @param dlg
	 */
//	public String getDlg2(String CElement, ooNewGenericDlg dlg) {
//		// TODO Auto-generated method stub
//
//		GetReference gref = new GetReference(CElement,dlg);
//		return gref.getRef();
//
//		//dlg.currentComponent.getClass();
//
//
//		/*
//		Class myClass;
//		try {
//			//myClass = Class.forName(dlg.currentComponent.getClass().toString());
//			//Object newInstance = myClass.newInstance();
//
//
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//*/
//
//	}

}
