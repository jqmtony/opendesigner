package org.openossad.ui;

import com.l2fprod.common.swing.PercentLayout;
import org.openossad.ui.base.BaseJDialog;
import org.openossad.ui.component.JOutlookBar;
import org.openossad.ui.panels.*;
import org.openossad.util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import org.openossad.OpenDESIGNERv2;

public class ooOpenGenericDlg extends BaseJDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panel = null;
	public Component  currentComponent;
	public JButton but2;
	private JDesktopPane pn1;
	public JOutlookBar outlook;
	public String choosed;
	protected Image image = null;

	panelOpenOO1 pOO1 = new panelOpenOO1(this);
	panelOpenOO2 pOO2 = new panelOpenOO2(this);
	panelOpenOO3 pOO3 = new panelOpenOO3(this);
	panelOpenOO4 pOO4 = new panelOpenOO4(this);
	panelOpenOO5 pOO5 = new panelOpenOO5(this);
	panelOpenOO6 pOO6 = new panelOpenOO6(this);
	panelOpenOO7 pOO7 = new panelOpenOO7(this);
	panelOpenOO8 pOO8 = new panelOpenOO8(this);
	panelOpenOO9 pOO9 = new panelOpenOO9(this);
    private static ooOpenGenericDlg me;

    /**
	 * @param owner
	 */
	public ooOpenGenericDlg(Frame owner) {
		super(owner);
        me=this;
        initialize();
	}

    /**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		setLayout(new BorderLayout());
		image = ImageUtil.createImage("/ui/images/background1.png");
        this.pack();
		this.setContentPane(getJContentPane());
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

		repaint();
		validate();
	}

	public JComponent getbut() {
		but2 = new JButton("Añada el elemento seleccionado al gráfico en uso.");
		but2.setToolTipText("Añadir elemento seleccionado");
		but2.addMouseListener(mouseListener2);
		// butFolder.addActionListener(actionListener);
		return but2;
	}

	MouseListener mouseListener2 = new MouseAdapter() {
		public void mouseClicked(MouseEvent mouseEvent) {
			choosed = "cacahué!";
		}
	};

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
		currentComponent = new panelOpenOO1(this);
		return currentComponent;

	}

	JPanel makeOutlookPanel() { // int alignment
		outlook = new JOutlookBar(mouseListener){
			{setOpaque(false);}  // instance initializer
			public void paintComponent (Graphics g) {
		    g.drawImage(image, 0, 0, this);
		    super.paintComponent(g);

		}};
//        outlook = new JOutlookBar();
		//outlook.setTabPlacement(JTabbedPane.LEFT);
		outlook.setSize(150, 0);
		String[] buttons;
		buttons = new String[]{"Procesos", "/ui/images/grafic_n_1_48.png","Modelos", "/ui/images/model_n_1_48.png",};
		outlook.addTab("     Procesos    ", buttons);
		buttons = new String[]{"ProcesosH", "/ui/images/human_n_1_48.png","ModelosH", "/ui/images/modelh_n_1_48.png",};
		outlook.addTab("    Human    ", buttons);
		buttons = new String[]{"Documentos", "/ui/images/paper_content_48.png",};
		outlook.addTab(" Documentos ", buttons);
		buttons = new String[]{"Indicadores", "/ui/images/clock_48.png",};
		outlook.addTab("  Indicadores   ", buttons);
		buttons = new String[]{"Roles", "/ui/images/technorati_48.png", "Actores","/ui/images/user_48_4.png", "Grupos", "/ui/images/users_two_48.png",};
		outlook.addTab("   Individuos   ", buttons);

		// show it is possible to add any component to the bar

		// Tree tree = new JTree();
		// outlook.addTab("A JTree", outlook.makeScrollPane(tree));
		// outlook.addTab("Disabled", new JButton());

		// outlook.setEnabledAt(3, false);
		// outlook.setAllTabsAlignment(alignment);

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
			if (but.getText().equals("Procesos")){
				//panelPropsOO1.comboF.setSelectedIndex(CurrentIndexFolder);
				show(pOO1);
			}
			else if (but.getText().equals("Modelos")){
				//pOO2.comboF.setSelectedIndex(CurrentIndexFolder);
				show(pOO2);
			}
			else if (but.getText().equals("ProcesosH")){
				//pOO3.comboF.setSelectedIndex(CurrentIndexFolder);
				show(pOO3);
			}
			else if (but.getText().equals("ModelosH")){
				//pOO4.comboF.setSelectedIndex(CurrentIndexFolder);
				show(pOO4);
			}
			else if (but.getText().equals("Documentos")){
				//pOO5.comboF.setSelectedIndex(CurrentIndexFolder);
				show(pOO5);
			}
			else if (but.getText().equals("Indicadores")){
				//pOO6.comboTblprojects.setSelectedIndex(CurrentIndexFolder);
				show(pOO6);
			}
			else if (but.getText().equals("Roles")){
				//pOO7.comboF.setSelectedIndex(CurrentIndexFolder);
				show(pOO7);
			}
			else if (but.getText().equals("Actores")){
				//pOO8.comboF.setSelectedIndex(CurrentIndexFolder);
				show(pOO8);
			}
			else if (but.getText().equals("Grupos")){
				//pOO9.comboF.setSelectedIndex(CurrentIndexFolder);
				show(pOO9);
			}
			else
				// TODO: create panels for new elements
				// show(new panelNewOO1());

			if (mouseEvent.getClickCount() == 2) {
				// System.out.println("Double-clicked");
				// SwingXDemo.this.descriptionContainer.setTitle("we");
				// SwingXDemo.this.demoContainer.setTitle(but.getText());
				// build(getDemoContainer(), null, true, but.getText());
			} else {

				// System.out.println("Simple-clicked");

			}
		}
	};



	public static void main(String[] args) {
        ooOpenGenericDlg oo1 = new ooOpenGenericDlg(null);
		oo1.setVisible(true);

	}

	public String oo1GetIDEntity() {
		choosed = "";
		show((Component) new panelOpenOO7(this));
		setVisible(true);
		return choosed;

	}
	public String oo1GetIDProcess() {
		choosed = "";
		panelOpenOO1 pn1=new panelOpenOO1(this);
		//pn1.ComeFromGraph=true;
		show((Component) pn1);
		setVisible(true);
		return choosed;

	}

	/**
	 * @return
	 */
	public String oo1GetIDDocument() {
		choosed = "";
		panelOpenOO5 pn1=new panelOpenOO5(this);
		//pn1.ComeFromGraph=true;
		show((Component) pn1);
		setVisible(true);
		return choosed;
	}


    public static ooOpenGenericDlg get()
    {
        return me;
    }
}
