package org.openossad.ui.panels;

import org.jdesktop.swingx.JXList;
import org.openossad.ui.base.PanelNewBase;
import org.openossad.ui.ooFolderExplorer;
import org.openossad.util.helper.ReferencialOOSSAD;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.*;

public class panelNewOO9 extends PanelNewBase
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
	public JComboBox comboF;
	public JButton butFolder;
	private ButtonGroup g; // = new ButtonGroup();
	private JRadioButton rb1, rb2, rb3;
	private JXList jxList;
	private JScrollPane scrollPane;
	private JButton jButton4 = null;
	private JButton jButton1 = null;

	private Integer level = null;
	private Integer orientation = null;


	/**
	 * This is the default constructor
	 */
	public panelNewOO9() {
		super("Grupos","/ui/images/users_two_48.png");
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

		add(getLbl("Identificación", 80, 100, w, h), null);
		add(getTxt2(230, 100, (this.size().width - (280)), 20), null);
		add(getLbl("Nombre del actor", 80, 130, w, h), null);
		add(getTxt3(230, 130, (this.size().width - (280)), 20), null);
		add(getDivider(173), null);
		// add(getComboroleType(50,210),null);
		// add(getLblImg2("images/1.png",190,200,250,200),null);
		// add(getjxList(190,200,250,200,1),null);

		add(getLbl("Contraseña", 200, 210, w, h), null);
		add(getTxt4(300, 210, (this.size().width - (350)), 20), null);
		add(getLbl("Descripción", 200, 240, w, h), null);
		add(getTxt5(300, 240, (this.size().width - (350)), 20), null);

		this.setVisible(true);
	}



	public JComponent getCombo(int xx, int yy) {
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

	private JComponent getbutFolder(int xx, int yy, int width, int height) {
		butFolder = new JButton(MetalIconFactory.getFileChooserNewFolderIcon());
		butFolder.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,
		// height
		butFolder.setToolTipText("Gestión de carpetas");
		butFolder.addMouseListener(mouseListener);
		butFolder.setEnabled(false);
		return butFolder;
	}

	private JComponent getLblImg(String icon, int xx, int yy, int width,
			int height) {
		ImageIcon iconOO = createImageIcon(icon, icon);
		lblImg = new JLabel(iconOO);
		lblImg.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,
		// height
		lblImg.setBackground(Color.blue);
		return lblImg;
	}

	private JComponent getLblImg2(String icon, int xx, int yy, int width,
			int height) {
		ImageIcon iconOO = createImageIcon(icon, icon);
		lblImg2 = new JLabel(iconOO);
		lblImg2.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,
		// height
		lblImg2.setBackground(Color.blue);
		return lblImg2;
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

	private JComponent getLbl2() {
		JLabel lbl2 = new JLabel("Campo 2");
		lbl2.setBounds(new Rectangle(10, 50, w, h)); // x,y,width,height
		return lbl2;
	}

	private JComponent getLbl3() {
		JLabel lbl3 = new JLabel("Campo 3");
		lbl3.setBounds(new Rectangle(10, 90, w, h)); // x,y,width,height
		return lbl3;
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
		java.net.URL imgURL = panelNewOO9.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {
			// JButton but = (JButton) mouseEvent.getSource();

			if (event.getSource().equals(jButton4)) {
				getParent().getParent().getParent().getParent().setVisible(
						false);
			}

			if (event.getSource().equals(butFolder)) {
				ooFolderExplorer oo1 = new ooFolderExplorer(null);
				oo1.setVisible(true);
				comboF.removeAllItems();
                gui.loadFolderCombo(comboF);

			}

			// TODO: dale caña a esto
			if (event.getSource().equals(jButton1)) {

				// System.out.println(g.getSelection().getSelectedObjects().
				// toString());
				String[] str1 = new String[3];
				int R = 0;

				for (Object obj : (Object[]) comboF.getSelectedItem()) {
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
					sqlQ = sqlQ + "'" + txt5.getText() + "',1,'0','"
							+ txt4.getText() + "','','','',";
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
