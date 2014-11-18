package org.openossad.ui;

import org.openossad.BasicGraphEditorOO;
import org.openossad.ui.base.BaseJDialog;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ooOptionsProperty extends BaseJDialog
{

	private JPanel jContentPane = null;
	private JButton jButton4 = null;
	private ooOptionsPropertySheet optionsPropertySheet = null;
	private JDesktopPane jDesktopPane = null;
	public BasicGraphEditorOO frame = null;
	

	/**
	 * @param owner
	 */
	public ooOptionsProperty(Frame owner) {
		super(owner,"Gestor de configuración OpenOSSAD");
		frame=BasicGraphEditorOO.get();
        initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.pack();
		this.setSize(750, 500);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
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
			jContentPane.add(getJDesktopPane(), BorderLayout.CENTER);;
		}
		return jContentPane;
	}

	private ooOptionsPropertySheet getooOptionsPropertySheet() {
		if (optionsPropertySheet == null) {
			optionsPropertySheet = new ooOptionsPropertySheet();
			optionsPropertySheet.setBounds(new Rectangle(1, 1, 749, 430));
		}
		return optionsPropertySheet;
	}

	private JDesktopPane getJDesktopPane() {
		if (jDesktopPane == null) {
			jDesktopPane = new JDesktopPane();
			jDesktopPane.setBackground(SystemColor.control);
			jDesktopPane.add(getJButton4(), null);
			jDesktopPane.add(getooOptionsPropertySheet(), null);
		}
		return jDesktopPane;
	}

	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton("Cerrar", MetalIconFactory
					.getInternalFrameCloseIcon(16));
			jButton4.setBounds(new Rectangle(580, 437, 100, 30));
			jButton4.setToolTipText("Cerrar");
			jButton4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					dispose();
				}
			});
		}
		return jButton4;
	}

	public static void main(String[] args) {
		try {
			ooOptionsProperty oo1 = new ooOptionsProperty(null);
			oo1.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
