package org.openossad.ui.panels;

import org.jdesktop.swingx.JXList;
import org.openossad.ui.base.PanelPropsBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class panelPropsO11 extends PanelPropsBase
{

	public JLabel lbl2;

	public JXList jxList;
	public JXList jxList2;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;

	public panelPropsO11() {
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
		add(gui.getLblTit("Aprobadores", 0, 0, 600, 30), null);
		
		add(gui.getLbl("Lista de los actores:", 20, 50, 230, 20), null);
		add(getxjList(20, 67, 230, 300), null);
		
		add(gui.getLbl("Actores seleccionados:", 350, 50, 230, 20), null);
		add(getxjList2(350, 67, 230, 300), null);
		
		add(getJButton1(270, 100, 60, 30), null);
		add(getJButton2(270, 140, 60, 30), null);
		add(getJButton3(270, 180, 60, 30), null);
		add(getJButton4(270, 220, 60, 30), null);

		this.setVisible(true);
	}
	private JButton getJButton1(int xx, int yy, int width, int height) {
		if (jButton1 == null) {
			jButton1 = new JButton(">");
			jButton1.setBounds(new Rectangle(xx, yy, width, height));
			jButton1.addMouseListener(mouseListener);
		}
		return jButton1;
	}
	private JButton getJButton2(int xx, int yy, int width, int height) {
		if (jButton2 == null) {
			jButton2 = new JButton("<");
			jButton2.setBounds(new Rectangle(xx, yy, width, height));
			jButton2.addMouseListener(mouseListener);
		}
		return jButton2;
	}

	private JButton getJButton3(int xx, int yy, int width, int height) {
		if (jButton3 == null) {
			jButton3 = new JButton(">>");
			jButton3.setBounds(new Rectangle(xx, yy, width, height));
			jButton3.addMouseListener(mouseListener);
		}
		return jButton3;
	}
	private JButton getJButton4(int xx, int yy, int width, int height) {
		if (jButton4 == null) {
			jButton4 = new JButton("<<");
			jButton4.setBounds(new Rectangle(xx, yy, width, height));
			jButton4.addMouseListener(mouseListener);
		}
		return jButton4;
	}
	
	
	private JComponent getxjList(int xx, int yy, int width, int height)  {
		//Aqui la query sale que 
		jxList = new JXList(genericListModelHibernate_1);
		jxList.setCellRenderer(comboBoxActorRenderer);
		genericListModelHibernate_1.connQuery = "SELECT SID,Name,CompleteName,Description,IsGroup FROM tblaccounts";
		genericListModelHibernate_1.loadDataFromODBC();
		JScrollPane scrollPane = new JScrollPane(jxList);
		scrollPane.setBounds(new Rectangle(xx, yy, width, height));
		jxList.addMouseListener(xjListListener);
		return scrollPane;
	}
	
	private JComponent getxjList2(int xx, int yy, int width, int height)  {
		//Aqui se cargaran los que en la query salgan como linkados
		jxList2 = new JXList(genericListModelHibernate_2);
		jxList2.setCellRenderer(comboBoxActorRenderer);
		//genericListModelHibernate_1.connQuery = "SELECT SID,Name,CompleteName,Description,IsGroup FROM tblaccounts";
		//genericListModelHibernate_1.loadDataFromODBC();
		JScrollPane scrollPane = new JScrollPane(jxList2);
		scrollPane.setBounds(new Rectangle(xx, yy, width, height));
		jxList2.addMouseListener(xjList2Listener);
		return scrollPane;
	}

	


	



	MouseListener xjListListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {
			// JButton but = (JButton) mouseEvent.getSource();
			if (event.getClickCount()==2){
				//(jxList.getSelectedValue());  
				//jxList.getSelectedIndex();
				genericListModelHibernate_2.addElement(jxList.getSelectedValue());jxList2.setModel(genericListModelHibernate_2);
				genericListModelHibernate_1.remove(jxList.getSelectedIndex());jxList.setModel(genericListModelHibernate_1);
			}
			
			
		}
	};
	MouseListener xjList2Listener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {
			// JButton but = (JButton) mouseEvent.getSource();
			if (event.getClickCount()==2){
				//(jxList.getSelectedValue());  
				//jxList.getSelectedIndex();
				genericListModelHibernate_1.addElement(jxList2.getSelectedValue());jxList.setModel(genericListModelHibernate_1);
				genericListModelHibernate_2.remove(jxList2.getSelectedIndex());jxList2.setModel(genericListModelHibernate_2);
			}
			
			
		}
	};
	MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {
			// JButton but = (JButton) mouseEvent.getSource();

			if (event.getSource().equals(jButton1)) {
				if (!jxList.isSelectionEmpty()){
				genericListModelHibernate_2.addElement(jxList.getSelectedValue());jxList2.setModel(genericListModelHibernate_2);
				genericListModelHibernate_1.remove(jxList.getSelectedIndex());jxList.setModel(genericListModelHibernate_1);
				}
			}
			if (event.getSource().equals(jButton2)) {
				if (!jxList2.isSelectionEmpty()){
				genericListModelHibernate_1.addElement(jxList2.getSelectedValue());jxList.setModel(genericListModelHibernate_1);
				genericListModelHibernate_2.remove(jxList2.getSelectedIndex());jxList2.setModel(genericListModelHibernate_2);
				}
			}
			if (event.getSource().equals(jButton3)) {
				
					for (int x=0;x<genericListModelHibernate_1.getSize();x++) {
						genericListModelHibernate_2.addElement(jxList.getElementAt(x));
					}
					jxList2.setModel(genericListModelHibernate_2);
					genericListModelHibernate_1.clear();
					jxList.setModel(genericListModelHibernate_1);
				
			}
			if (event.getSource().equals(jButton4)) {
				for (int x=0;x<genericListModelHibernate_2.getSize();x++) {
					genericListModelHibernate_1.addElement(jxList2.getElementAt(x));
				}
				jxList.setModel(genericListModelHibernate_1);
				genericListModelHibernate_2.clear();
				jxList2.setModel(genericListModelHibernate_2);
				
			}
		}

		/* (non-Javadoc)
		 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mousePressed(e);
		}
	};

}

/*
     // Create a list that allows adds and removes
    DefaultListModel genericListModelHibernate_1 = new DefaultListModel();
    JList list = new JList(genericListModelHibernate_1);
    
    // Initialize the list with items
    String[] items = {"A", "B", "C", "D"};
    for (int i=0; i<items.length; i++) {
        genericListModelHibernate_1.add(i, items[i]);
    }
    
    // Append an item
    int pos = list.getModel().getSize();
    genericListModelHibernate_1.add(pos, "E");
    
    // Insert an item at the beginning
    pos = 0;
    genericListModelHibernate_1.add(pos, "a");

This method replaces an item:

    
    // Replace the 2nd item
    pos = 1;
    genericListModelHibernate_1.set(pos, "b");

These methods are used to remove items:

    
    // Remove the first item
    pos = 0;
    genericListModelHibernate_1.remove(pos);
    
    // Remove the last item
    pos = genericListModelHibernate_1.getSize()-1;
    if (pos >= 0) {
        genericListModelHibernate_1.remove(pos);
    }
    
    // Remove all items
    genericListModelHibernate_1.clear();

*/
