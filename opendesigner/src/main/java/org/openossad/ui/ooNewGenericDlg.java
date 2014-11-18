package org.openossad.ui;

import com.l2fprod.common.swing.PercentLayout;
import org.openossad.OpenDESIGNER;
import org.openossad.ui.base.BaseJDialog;
import org.openossad.ui.component.JOutlookBar;
import org.openossad.ui.component.JXErrorDialog;
import org.openossad.ui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;

public class ooNewGenericDlg extends BaseJDialog
{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panel = null;
	public Component currentComponent;
	private JButton unBtn;
	public JDesktopPane pn1;
	private JOutlookBar outlook;
	protected ImageIcon imageIcon;
	protected Image image = null;

	/**
	 * @param owner
	 */
	public ooNewGenericDlg(Frame owner) {
		super(owner);
		initialize();
	}

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
		this.setSize(710, 530);
		this.setResizable(false);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(getJContentPane());
		this.setTitle("Creación...");
		add("West", makeOutlookPanel());

		add("Center", getpanelNewOO1());
	}

	// TODO: arreglar esto que no actualiza bien
	public void show(Component component) {
		if (currentComponent != null) {
			remove(currentComponent);
			remove(outlook);
			repaint();
		}
		currentComponent = component;
		add("Center", currentComponent);

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

	private Component getpanelNewOO1() {
		currentComponent = new panelNewOO1();

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
		buttons = new String[]{"Procesos", "/ui/images/grafic_n_1_48.png","Modelos", "/ui/images/model_n_1_48.png",};
		outlook.addTab("     Procesos     ", buttons);

		buttons = new String[]{"ProcesosH", "/ui/images/human_n_1_48.png","ModelosH", "/ui/images/modelh_n_1_48.png",};
		outlook.addTab("    Human    ", buttons);

		buttons = new String[]{"Documentos", "/ui/images/paper_content_48.png",};
		// "Familias", "/ui/images/paperpencil_48_2.png",};
		outlook.addTab("  Documentos  ", buttons);
		buttons = new String[]{"Indicadores", "/ui/images/clock_48.png",};
		outlook.addTab("  Indicadores   ", buttons);
		
		buttons = new String[]{"Médios", "/ui/images/clock_48.png",};
		outlook.addTab("  Médios   ", buttons);
		
		buttons = new String[]{"Roles", "/ui/images/technorati_48.png", "Actores",
				"/ui/images/user_48_4.png", "Grupos", "/ui/images/users_two_48.png",};
		outlook.addTab("   Individuos   ", buttons);

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
			if (but.getText().equals("Procesos"))
				show(new panelNewOO1());
			else if (but.getText().equals("Modelos"))
				show(new panelNewOO2());
			else if (but.getText().equals("ProcesosH"))
				show(new panelNewOO3());
			else if (but.getText().equals("ModelosH"))
				show(new panelNewOO4());
			else if (but.getText().equals("Documentos"))
				show(new panelNewOO5());
			else if (but.getText().equals("Indicadores"))
				show(new panelNewOO6());
			else if (but.getText().equals("Roles"))
				show(new panelNewOO7());
			else if (but.getText().equals("Actores"))
				show(new panelNewOO8());
			else if (but.getText().equals("Grupos"))
				show(new panelNewOO9());
			else
				show(new panelNewOO1());

			if (mouseEvent.getClickCount() == 2) {
				// System.out.println("Double-clicked");
				// SwingXDemo.this.descriptionContainer.setTitle("we");
				// SwingXDemo.this.demoContainer.setTitle(but.getText());
				// build(getDemoContainer(), null, true, but.getText());
			} else {

				// System.out.println("Simple-clicked");

			}

			validate();
		}
	};

	public static void main(String[] args) {
		try {
			ooNewGenericDlg oo1 = new ooNewGenericDlg(null);
			oo1.pack();
			oo1.setSize(710, 530);
			oo1.setModal(true);
			oo1.setResizable(true);
			oo1.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
