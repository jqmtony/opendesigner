package org.openossad.ui.panels;

import org.openossad.ui.base.PanelNewBase;
import org.openossad.util.helper.ReferencialOOSSAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class panelNewOO8 extends PanelNewBase
{


	private int h = 20;
	private int w = 150;
	private static final long serialVersionUID = 1L;
	public JLabel lbl;
	public JLabel lblTit;
	public JLabel lblImg;
	public JLabel lblImg2;
	public JLabel lbl2;
	public JLabel lbl3;
	public JTextField txt1;
	public JTextField txt2;
	public JTextField txt3;
	public JTextField txt4;
	public JTextField txt5;
    private ButtonGroup g;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JRadioButton rb3;


    /**
	 * This is the default constructor
	 */
	public panelNewOO8() {
		super("Actores", "/ui/images/user_48_4.png");
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
		add(getLblTit("Actores", 0, 0, 600, 30), null);
		add(gui.getLblImg("/ui/images/user_48_4.png", 10, 100, 50, 50), null);


		add(getLbl("Identificación", 80, 100, w, h), null);
		add(getTxt2(230, 100, (this.size().width - (280)), 20), null);
		add(getLbl("Nombre del actor", 80, 130, w, h), null);
		add(getTxt3(230, 130, (this.size().width - (280)), 20), null);
		add(getDivider(173), null);
		add(getComboroleType(50, 210), null);
		// add(getLblImg2("images/1.png",190,200,250,200),null);
		// add(getjxList(190,200,250,200,1),null);

		add(getLbl("Contraseña", 200, 210, w, h), null);
		add(getTxt4(300, 210, (this.size().width - (350)), 20), null);
		add(getLbl("Descripción", 200, 240, w, h), null);
		add(getTxt5(300, 240, (this.size().width - (350)), 20), null);

		this.setVisible(true);
	}

	public JComponent getComboroleType(int xx, int yy) {
		g = new ButtonGroup();
		rb1 = new JRadioButton("Lector        ", false);
		rb2 = new JRadioButton("Redactor     ", false);
		rb3 = new JRadioButton("Administrador", false);
		rb1.addActionListener(al);
		rb1.setSelected(true);
		rb2.addActionListener(al);
		rb3.addActionListener(al);
		g.add(rb1);
		g.add(rb2);
		g.add(rb3);
		Container cp = new JPanel();
		cp.setLayout(new FlowLayout());
		cp.setBounds(new Rectangle(xx, yy, 120, 130));
		cp.add(rb1);
		cp.add(rb2);
		cp.add(rb3);
		cp.setVisible(true);
		return (JPanel) cp;
	}

	private ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	};


	private JComponent getLbl(String label, int xx, int yy, int width,
			int height) {
		lbl = new JLabel(label);
		lbl.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,height
		return lbl;
	}

	private JComponent getDivider(int yy) {
		JPanel divider = new JPanel();
		divider.setBackground(new Color(SystemColor.WINDOW_BORDER));
		divider.setSize(new Dimension(this.getWidth(), 1));
		divider.setBounds(new Rectangle(5, yy, this.getWidth() - 10, 1));
		return divider;
	}


	private JLabel getLblTit(String label, int xx, int yy, int width, int height) {
		lblTit = new JLabel(label);
		lblTit.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,
		// height
		lblTit.setFont(new Font(lblTit.getFont().getName(), Font.BOLD, 18));
		lblTit.setVerticalTextPosition(SwingConstants.CENTER);
		lblTit.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTit.setBorder(BorderFactory.createLineBorder(Color.black));
		lblTit.setOpaque(true);
		lblTit.setBackground(new Color(144, 153, 174));
		lblTit.setForeground(Color.white);
		return lblTit;
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

	private void addLblandText(String label, int xx, int yy, int width,
			int height) {
		JLabel lbl = new JLabel(label);
		lbl.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,height
		JTextField txt = new JTextField();
		txt.setBounds(new Rectangle(xx + width, yy, this.WIDTH - 15, height)); // x
		// ,
		// y
		// ,
		// width
		// ,
		// height
		txt.setVisible(true);
		add(lbl, null);
		add(txt, null);

	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = panelNewOO8.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	MouseListener mouseListener = new MouseAdapter() {
		public Object jButton1;

        public void mouseClicked(MouseEvent event) {
			
			// TODO: dale caña a esto
			if (event.getSource().equals(jButton1)) {

				// System.out.println(g.getSelection().getSelectedObjects().
				// toString());
				String[] str1 = new String[3];
				int R = 0;

				for (Object obj : (Object[]) comboTblprojects.getSelectedItem()) {
					str1[R] = obj.toString();
					R++;
				}

				if (txt2.getText().equals("") || txt3.getText().equals("")) {
					JOptionPane
							.showMessageDialog(
									null,
									"Por favor, rellene los campos para crear el actor .",
									"Validación",
									JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				Integer GType = 3;
				if (rb1.isSelected())
					GType = 3;
				if (rb2.isSelected())
					GType = 1;
				if (rb3.isSelected())
					GType = 2;

				boolean success = true;
				ReferencialOOSSAD refOO = new ReferencialOOSSAD();
				String LastID = refOO.getLastIDAccount();

				if (success) {
					String sqlQ = "INSERT INTO tblaccounts (";
					sqlQ = sqlQ + "SID,Name,CompleteName,";
					sqlQ = sqlQ
							+ "Description,IsGroup,Type,Password,SAMSID,SAMACCOUNTNAME,Phone,";
					sqlQ = sqlQ
							+ "Mail,LastLoginDate,ID,PhoneHOME,Fax,FaxHome,MobilPhone,MobilHomePhone,MobilCarPhone,";
					sqlQ = sqlQ
							+ "Adress,Mail2,Mail3,Responsable,Assistant,PhotoGID,UpdateDate,HTMLGEN,Gtype,PageArrivee,Roles)";
					sqlQ = sqlQ + "VALUES ";
					sqlQ = sqlQ + "(";
					sqlQ = sqlQ + "'" + LastID.substring(13, 16) + "',";
					sqlQ = sqlQ + "'" + txt2.getText() + "',";
					sqlQ = sqlQ + "'" + txt3.getText() + "',";
					sqlQ = sqlQ + "'" + txt5.getText() + "',0,'" + GType
							+ "','" + txt4.getText() + "','','','',";
					sqlQ = sqlQ + "'','" + refOO.dateDIFformat() + "','"
							+ LastID + "','','','','','','',";
					sqlQ = sqlQ + "'','','','','','','" + refOO.dateformat()
							+ "',0,'','',''";
					sqlQ = sqlQ + ")";
					refOO.exeUpdate(sqlQ);

					getParent().getParent().getParent().getParent().setVisible(
							false);

				} else {

				}

			}
		}
	};

    @Override
    public MouseListener newButtonCreateMouseListener()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
