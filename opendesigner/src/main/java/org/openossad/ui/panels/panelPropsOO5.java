package org.openossad.ui.panels;

import org.openossad.ui.base.PanelPropsBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelPropsOO5 extends PanelPropsBase
{

	public JTextField txt1;
	public JTextField txt2;
	public JTextField txt3;
	public JComboBox comboF;
	private ButtonGroup g; // = new ButtonGro
    private JRadioButton rb1,rb2,rb3;

    public panelPropsOO5() {
		super();
		initialize();
	}

	private void initialize() {
		this.setVisible(false);
		this.setBackground(SystemColor.control);
		this.setSize(600, 470);
		add(gui.getLblTit("Iconos", 0, 0, 600, 30), null);
		add(getCombo(50, 125), null);
		this.setVisible(true);
	}

	public JComponent getCombo(int xx, int yy) {
		g = new ButtonGroup();
		rb1 = new JRadioButton("Por defecto (Carpetas)", false);
		rb2 = new JRadioButton("Imágen de la biblioteca", false);
		rb3 = new JRadioButton("Personalizado", false);
		rb1.addActionListener(al);
		rb1.setSelected(true);
		rb2.addActionListener(al);
		rb3.addActionListener(al);
		g.add(rb1);
		g.add(rb2);
		g.add(rb3);
		Container cp = new JPanel();
		cp.setLayout(new FlowLayout());
		cp.setBounds(new Rectangle(xx, yy, 500, 100));
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
			repaint();

		}
	};

}
