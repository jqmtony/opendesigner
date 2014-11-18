package org.openossad.ui.panels;

import com.mxgraph.util.mxResources;
import org.openossad.data.domain.Tblgraphs;
import org.openossad.data.domain.Tblprojects;
import org.openossad.ui.base.PanelNewBase;
import org.openossad.util.helper.ReferencialOOSSAD;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class panelNewOO5 extends PanelNewBase
{

	private int h = 20;
	private int w = 150;
	private static final long serialVersionUID = 1L;
	public JLabel lbl;
	public JLabel lblTit;
	public JLabel lblImg;
	public JLabel lbl2;
	public JLabel lbl3;
	public JTextField txt1;
	public JTextField txt2;
	public JTextField txt3;
	private JButton jButton1 = null;
	private JButton jButton5 = null;
	public JCheckBox check1 = new JCheckBox();
	/**
	 * This is the default constructor
	 */
	public panelNewOO5() {
		super("Documentos", "/ui/images/paper_content_48.png");
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

        comboTblprojects = new JComboBox();
        add(gui.getComboFolder(160, 42, (this.size().width - (250)), 25, comboTblprojects),null);

		add(gui.getLbl("Nombre documento", 80, 100, w, h), null);
		add(gui.getTxt(txtGName, 230, 100, (this.size().width - (280)), 20), null);
		add(gui.getTxt(txtGRef,230, 130, (this.size().width - (280)), 20), null);
		add(gui.getDivider(this,173), null);

		add(gui.getLbl("Ruta del documento", 30, 200, w, h), null);
		add(gui.getDocumentToCatchTextField(190, 200, (this.size().width - (240)), 20), null);
		add(getbutFolder2(550, 200, 20, 20), null);
		
		add(gui.getChkBut(check1,false,"Guardar solamente el enlace al documento", 30, 230, 400, 20));



		this.setVisible(true);
	}


	private JComponent getbutFolder2(int xx, int yy, int width, int height) {
		jButton5 = new JButton(MetalIconFactory.getFileChooserNewFolderIcon());
		jButton5.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,
		jButton5.setToolTipText("Gestión de carpetas");
		jButton5.addMouseListener(mouseListener);
		return jButton5;
	}



	MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {


			if (event.getSource().equals(jButton5)) {
				String lastDir = null;
				String wd = (lastDir != null) ? lastDir : System.getProperty("user.dir");
				JFileChooser fc = new JFileChooser(wd);
                int rc = fc.showDialog(null, mxResources.get("openFile"));

				if (rc == JFileChooser.APPROVE_OPTION) {
					gui.documentToCatch.setText(fc.getSelectedFile().toString());

				}
			}

		}
	};

    @Override
    public MouseListener newButtonCreateMouseListener()
    {
        return new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    JDialog currentDialog = (JDialog) SwingUtilities.windowForComponent(e.getComponent());
                    PanelNewBase currentPanel = (PanelNewBase) e.getComponent().getParent();


                    if (currentPanel.txtGName.getText().equals("") || currentPanel.txtGRef.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Por favor, rellene los campos para crear el document.", "Validación", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    try
                    {
                        //
                        Tblprojects project = (Tblprojects) currentPanel.comboTblprojects.getSelectedItem();
                        Tblgraphs newTblgraphsDoc = ReferencialOOSSAD.createNewOODocument(project, currentPanel.txtGName.getText(), currentPanel.txtGRef.getText(), gui.documentToCatch.getText());

                        currentDialog.setVisible(false);
                    }
                    catch(IOException exception)
                    {
                        exception.printStackTrace();
                    }


                }
            };
    }
}
