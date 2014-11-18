package org.openossad.ui.panels;

import org.openossad.ui.base.PanelPropsBase;
import org.openossad.ui.component.ButtonGetActorListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class panelPropsOO2 extends PanelPropsBase
{

	public JLabel lbl2;
	public JLabel lbl3;
	public JTextField txt1;
	public JTextField txt2;
	public JTextField txt3;
	public JTextField txt4;
	public JTextField txt5;
	public JTextField txt6;
	public JTextArea txtAFinalidad;
	public JTextArea txtADescription;
	public JButton but1 = new JButton();
	public JButton but4 = new JButton();

	public panelPropsOO2() {
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
		add(gui.getLblTit("Resumen", 0, 0, 600, 30));
		
		add(gui.getLbl("Palabra(s) clave:", 280, 190, 200, 20));
		add(getTxt1(280, 205, 310, 25));
		
		add(gui.getLbl("Abreviaciones:", 280, 235, 200, 20));
		add(getTxt2(280, 250, 310, 25));
		
		add(gui.getLbl("Campo 1:", 280, 280, 200, 20));
		add(getTxt3(280, 295, 310, 25));
		add(gui.getLbl("Campo 2:", 280, 325,200, 20));
		add(getTxt4(280, 340, 310, 25));
		add(gui.getLbl("Campo 3:", 280, 370, 200, 20));
		add(getTxt5(280, 385, 310, 25));
		
		
		add(gui.getLbl("Redactor:", 20, 190, 150, 20));
		add(gui.getJButton("but1",but1,"/ui/images/buttons/07.png",220, 205, 25, 25));
		add(gui.getJButton("but4",but4,"/ui/images/ooGraph/01.png", 245, 205, 25, 25));
		but1.addActionListener(new ButtonGetActorListener(this));
		but4.addMouseListener(mouseListener);
		
		add(getTxt6(20, 205, 200, 25));
		
		
		add(gui.getLbl("Finalidad:", 20, 43, 580, 20));
		add(getTxtArea1(20, 60, 570, 50));
		add(gui.getLbl("Descripción:", 20, 113, 580, 20));
		add(getTxtArea2(20, 130, 570, 50));
	
		add(gui.getDivider(this,427));
		
		this.setVisible(true);
	}
	
	public MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			JButton source = (JButton) e.getSource();
			if (source.getName().equals("but1")){
//				buttonGetActor.setVisible(true);
//				String res=buttonGetActor.getID();
//				if (!res.equals("-1")) txt6.setText(res);
			}
			if (source.getName().equals("but4")){
				txt6.setText("");
			}
		}
	};

	protected void delTxt6() {
		txt6.setText("");
		
	}

	private JComponent getTxtArea1(int xx, int yy, int width, int height) {
		
		txtAFinalidad = new JTextArea(4,20);
		txtAFinalidad.setToolTipText("Finalidad");
		txtAFinalidad.setLineWrap(true);
        txtAFinalidad.setWrapStyleWord(true);

		JScrollPane sc1 = new JScrollPane(txtAFinalidad);
		sc1.setBounds(new Rectangle(xx, yy, width, height));
		sc1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sc1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		return sc1;
	}
	
	private JComponent getTxtArea2(int xx, int yy, int width, int height) {
		
		txtADescription = new JTextArea(4,20);
		txtADescription.setToolTipText("Descripción");
		txtADescription.setLineWrap(true);
        txtADescription.setWrapStyleWord(true);

		JScrollPane sc2 = new JScrollPane(txtADescription);
		sc2.setBounds(new Rectangle(xx, yy, width, height));
		sc2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sc2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		return sc2;
	}	


	private JComponent getTxt1(int xx, int yy, int width, int height) {
		txt1 = new JTextField();
		txt1.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return txt1;
	}

	private JComponent getTxt2(int xx, int yy, int width, int height) {
		txt2 = new JTextField();
		txt2.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return txt2;
	}

	private JComponent getTxt3(int xx, int yy, int width, int height) {
		txt3 = new JTextField();
		txt3.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return txt3;
	}

	private JComponent getTxt4(int xx, int yy, int width, int height) {
		txt4 = new JTextField();
		txt4.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return txt4;
	}
	private JComponent getTxt5(int xx, int yy, int width, int height) {
		txt5 = new JTextField();
		txt5.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return txt5;
	}
	private JComponent getTxt6(int xx, int yy, int width, int height) {
		txt6 = new JTextField();
		txt6.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		txt6.setEditable(false);
		return txt6;
	}


}
