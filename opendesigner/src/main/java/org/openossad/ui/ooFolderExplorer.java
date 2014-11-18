package org.openossad.ui;

import org.openossad.data.domain.Tblprojects;
import org.openossad.ui.base.BaseJDialog;
import org.openossad.ui.renderers.RgraphTreeRenderer;
import org.openossad.util.helper.DAOOOSSAD;
import org.openossad.util.helper.NodeTreeHelper;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ooFolderExplorer extends BaseJDialog
{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JDesktopPane jDesktopPane = null;
	private JTree jTree1 = null;
	private JLabel jLabel = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;
	private DefaultTreeModel model; // @jve:decl-index=0:
	private JScrollPane scrollPane;

	private DefaultMutableTreeNode root;
	public DefaultMutableTreeNode[] nodos;
	public Tblprojects[] MyArray = null;
	public int R;
	String query;
	// @jve:decl-index=0:
	// ImageIcon
	// (
	// "/images/openossadico.gif"
	// );

	public ooFolderExplorer(Object owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setResizable(false);
		this.setSize(460, 430);
		this.setLocationRelativeTo(null);
		this.setTitle("Explorador de carpetas OpenOSSAD");
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
			jContentPane.add(getJDesktopPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	private JDesktopPane getJDesktopPane() {
		if (jDesktopPane == null) {

			jDesktopPane = new JDesktopPane();
			jDesktopPane.setBackground(SystemColor.control);
			scrollPane = new JScrollPane(getJTree1());
			scrollPane.setBounds(new Rectangle(15, 38, 320, 294));
			jDesktopPane.add(scrollPane, null);
			jDesktopPane.add(GUI.getLblTit("Explore y gestione las carpetas", 15, 1, 430, 36));
			jDesktopPane.add(getJButton(), null);
			jDesktopPane.add(getJButton1(), null);
			jDesktopPane.add(getJButton2(), null);
			jDesktopPane.add(getJButton3(), null);
			jDesktopPane.add(getJButton4(), null);
		}
		return jDesktopPane;
	}


	private JTree getJTree1() {
		if (jTree1 == null) {
            List<Tblprojects> tblprojectsList = DAOOOSSAD.getAllTblprojects();
            TreeNode root = NodeTreeHelper.makeSampleTreeLegacy(tblprojectsList);
			model = new DefaultTreeModel(root);
			jTree1 = new JTree(model);
			jTree1.setEditable(true);
			jTree1.setCellRenderer(new RgraphTreeRenderer());

		}
		return jTree1;
	}

	private JButton getJButton() {
		if (jButton == null) {
			// Icon folderIcon = new ImageIcon("openossad/images/Cat.gif");
			jButton = new JButton("Nuevo", MetalIconFactory
					.getFileChooserNewFolderIcon());
			jButton.setToolTipText("Nuevo");
			jButton.setBounds(new Rectangle(344, 38, 100, 30)); // 38
			jButton.addMouseListener(mouseListener);
		}
		return jButton;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton("Cambio", MetalIconFactory
					.getFileChooserDetailViewIcon());
			jButton.setToolTipText("Cambio");
			jButton1.setBounds(new Rectangle(344, 78, 100, 30));
			jButton1.addMouseListener(mouseListener);
		}
		return jButton1;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton("Borrar", MetalIconFactory
					.getInternalFrameCloseIcon(16));
			jButton.setToolTipText("Borrar");
			jButton2.setBounds(new Rectangle(344, 118, 100, 30));
			jButton2.addMouseListener(mouseListener);
		}
		return jButton2;
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton("Propiedades");
			jButton3.setBounds(new Rectangle(344, 158, 100, 30));
			jButton3.addMouseListener(mouseListener);
			jButton3.setVisible(false);
		}
		return jButton3;
	}

	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton("Cerrar", MetalIconFactory
					.getInternalFrameCloseIcon(16));
			jButton4.setBounds(new Rectangle(344, 300, 100, 30));
			jButton4.setToolTipText("Cerrar");
			jButton4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					dispose();
				}
			});
		}
		return jButton4;
	}

	MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {
			// JButton but = (JButton) mouseEvent.getSource();

			if (event.getSource().equals(jButton4)) {
				// System.exit(0); // esto cierra la aplicación por completo
				//jButton4.getParent().getParent().getParent().setVisible(false)
				// ;
				// jContentPane.getParent().setVisible(false);

			}

			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
			Tblprojects selectedTblproject = (Tblprojects) selectedNode.getUserObject();
			
			if (selectedNode == null)
				return;

			if (event.getSource().equals(jButton1)) {
				String NewString = JOptionPane.showInputDialog("Introduzca un nombre para la carpeta.");
				if (NewString == null)return;
				selectedTblproject.setProjectName(NewString);
				//getDAOFactory().getTblprojectsDAO().makePersistent();
				getDAOFactory().getCurrentSession().save(selectedTblproject);
			}

			if (event.getSource().equals(jButton2)) {
				if (selectedNode.getParent() != null) {
					if (selectedNode.getChildCount() != 0) {
						JOptionPane
								.showMessageDialog(
										null,
										"No es posible borrar una carpeta con subcarpetas.",
										"Error lógico",
										JOptionPane.ERROR_MESSAGE);
					} else {
						String[] wek = selectedNode.toString().split("-");
						getDAOFactory().getCurrentSession().delete(selectedTblproject);
						
//						query = "UPDATE tblgraphs SET ProjectId='OPS000-000000001' WHERE ProjectId = '"
//							+ TblprojectsArray[Integer.parseInt(wek[0].trim())][0].toString() + "'";
//						//exeUpdate(query);
					}

				}
			}

			// add new node as child
			if (event.getSource().equals(jButton)) {
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedNode.getParent();
				//selectedTblproject = (Tblprojects) parent.getUserObject();
				
				String NewString = JOptionPane.showInputDialog("Introduzca un nombre para la carpeta.");
				if (NewString == null) return;
				R++;
				String newID = "000000000" + R;
				newID = "OPS000-" + newID.substring(newID.length() - 9, newID.length());
				Tblprojects newTblprojects = new Tblprojects();
				newTblprojects.setProjectId(newID);
				newTblprojects.setProjectName(NewString);
				newTblprojects.setVisPublic(false);
				newTblprojects.setParentId(selectedTblproject.getProjectId());
				newTblprojects.setCreateDate(Calendar.getInstance().getTime());
				newTblprojects.setFldLevel(selectedNode.getLevel() + 2);
				newTblprojects.setFldNumber(R);
				getDAOFactory().getCurrentSession().save(newTblprojects);
			}
            List<Tblprojects> tblprojectsList = DAOOOSSAD.getAllTblprojects();
			jTree1.setModel(new DefaultTreeModel(NodeTreeHelper.makeSampleTreeLegacy(tblprojectsList)));
			
		}
	};



	public static void main(String[] args) {
		try {
			BaseJDialog oo1 = new ooFolderExplorer(null);
			oo1.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
} // @jve:decl-index=0:visual-constraint="10,10"
