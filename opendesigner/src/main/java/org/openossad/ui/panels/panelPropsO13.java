package org.openossad.ui.panels;

import org.jdesktop.swingx.JXList;
import org.openossad.ui.base.PanelPropsBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class panelPropsO13 extends PanelPropsBase
{


	public ButtonGroup g; // = new ButtonGroup();
	public JRadioButton rb0, rb1, rb2, rb3, rb4;
	
	public JComboBox comboF;
	public JXList jxList;
	public JXList jxList2;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;
    private JLabel lblTit;

    public panelPropsO13() {
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
		add(gui.getLblTit("Restricciones", 0, 0, 600, 30), null);
		
		add(gui.getLbl("Lista de los actores:", 20, 50, 230, 20), null);
		add(getxjList(20, 67, 560, 300), null);
		
		add(getJButton1(140, 380, 100, 25), null);
		add(getJButton2(250, 380, 100, 25), null);
		add(getJButton3(360, 380, 100, 25), null);
		
		
		add(getCombo(50, 300), null);

		this.setVisible(true);
	}
	public JComponent getCombo(int xx, int yy) {
		g = new ButtonGroup();
		rb0 = new JRadioButton("Sin permisos", false);
		rb1 = new JRadioButton("Lectura", false);
		rb2 = new JRadioButton("Añadir", false);
		rb3 = new JRadioButton("Modificar", false);
		rb4 = new JRadioButton("Control Total", false);
		rb0.addActionListener(al);
		rb0.setSelected(true);
		rb1.addActionListener(al);
		rb2.addActionListener(al);
		rb3.addActionListener(al);
		rb4.addActionListener(al);
		g.add(rb0);
		g.add(rb1);
		g.add(rb2);
		g.add(rb3);
		g.add(rb4);
		Container cp = new JPanel();
		cp.setLayout(new FlowLayout());
		cp.setBounds(new Rectangle(xx, yy, 500, 40));
		lblTit = new JLabel();
		lblTit.setVerticalTextPosition(SwingConstants.CENTER);
		lblTit.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTit.setBorder(BorderFactory.createLineBorder(Color.black));
		lblTit.setBounds(new Rectangle(1, 1, 90, 130));
		cp.add(rb0);
		cp.add(rb1);
		cp.add(rb2);
		cp.add(rb3);
		cp.add(rb4);
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
		}
	};

	private JButton getJButton1(int xx, int yy, int width, int height) {
		if (jButton1 == null) {
			jButton1 = new JButton("Añadir");
			jButton1.setBounds(new Rectangle(xx, yy, width, height));
			jButton1.addMouseListener(mouseListener);
		}
		return jButton1;
	}
	private JButton getJButton2(int xx, int yy, int width, int height) {
		if (jButton2 == null) {
			jButton2 = new JButton("Modificar");
			jButton2.setBounds(new Rectangle(xx, yy, width, height));
			jButton2.addMouseListener(mouseListener);
		}
		return jButton2;
	}

	private JButton getJButton3(int xx, int yy, int width, int height) {
		if (jButton3 == null) {
			jButton3 = new JButton("Suprimir");
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
		
			}
			if (event.getSource().equals(jButton2)) {
	
			}
			if (event.getSource().equals(jButton3)) {

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
