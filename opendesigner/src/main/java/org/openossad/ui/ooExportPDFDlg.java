/**
 * OpenDESIGNER-2.0.3
 * CustomDialog.java,09/03/2009,
 * Copyright (c) 2009,  Xavi Hidalgo
 *
 * //////////////////////////////////////////////////////
 * Este código es propiedad de TricomZone, S.L.
 * Sujeto a la licencia LGPL.
 * 
 * //////////////////////////////////////////////////////
 *
 */

package org.openossad.ui;

import org.openossad.data.domain.Tblgraphs;
import org.openossad.ui.component.JXErrorDialog;
import org.openossad.util.GUItools;
import org.openossad.util.ImageUtil;
import org.openossad.util.draw.DrawRectPanel;
import org.openossad.util.helper.DAOOOSSAD;
import org.openossad.util.helper.GetGraph;
import org.openossad.util.helper.OOGraphPDF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;




/**
* @author ratul
*/
public class ooExportPDFDlg extends JDialog {

private JLabel jLabel1;
private JLabel lblImg;
private javax.swing.JButton jbtOK;
private javax.swing.JButton jbtCancel;
private JTextField jtB1;
private String inputString = "";
private String FileGID;
private ooOpenGenericDlg ooO;

protected ImageIcon imageIcon;
protected Image image = null;

public JFrame frame=null;
private DAOOOSSAD DAO = new DAOOOSSAD();
private Tblgraphs myooGraph;
private GUItools GUI=new GUItools();
private JButton but3 = new JButton();
private JButton but6 = new JButton();
private JLabel lbl5 = new JLabel();
private JTextField txt9 = new JTextField();
private JCheckBox[] check = new JCheckBox[9];

	public ooExportPDFDlg(ooOpenGenericDlg parent,String FileGID) {
		super(parent, false );
		this.ooO=parent;
		ooExportPDFDlgCOMEON(FileGID);
		
	}
	
	public ooExportPDFDlg(JFrame frame, String fileGID) {
		// TODO Auto-generated constructor stub
		super(frame, false );
		this.frame=frame;
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ooExportPDFDlgCOMEON(fileGID);
	}
	
	public ooExportPDFDlg(String FileGID) {
		ooExportPDFDlgCOMEON(FileGID);
		
	}
    public ooExportPDFDlg() {
		ooExportPDFDlgCOMEON("");

	}
	private void ooExportPDFDlgCOMEON(String fileGID) {
		this.FileGID=fileGID;
		if (!FileGID.isEmpty()) {
		myooGraph=DAO.getooGraphOBJ(FileGID);
		setTitle("Generación PDF "+myooGraph.getGname());
		} else {
			setTitle("Generación PDF ");
		}
		
		
		initComponents();
		
		
		pack();
		setSize(450,450);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}

	
	

	/**
	 * @param fileGID2
	 */
	private void carga(String fileGID2) {
		// TODO Auto-generated method stub
		
		
	}

	private void initComponents() {
		try {
			imageIcon = new ImageIcon(new java.net.URL(DrawRectPanel.class.getResource("/ui/images/openossadlogo.png"), "openossadlogo.png"));
			image = imageIcon.getImage();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JXErrorDialog.showDialog(null, "No se puede cargar la imágen de fondo", e);
		}
		
		for (int x=0;x<check.length;x++) check[x]=new JCheckBox();
		
		setLayout(null);
		
		
		add(getjLabel1(10, 20, 360, 25,"Con esta herramienta puede generar un archivo"));
		add(getjLabel1(10, 45, 360, 25,"PDF a partir de un gráfico OpenDESIGNER"));
		add(getLblImg("/ui/images/pdf-icon-48.jpg", 380, 20, 50, 50));
		
		add(getDivider(55));
		
		add(GUI.getLbl("Gráfico seleccionado", 10, 90, 200, 20));
		add(GUI.getTxt(txt9,10, 110, 350, 25));
		if (!FileGID.isEmpty()) txt9.setText(myooGraph.getGname());
		txt9.setEditable(false);
		add(GUI.getJButton("but3",but3,"/ui/images/buttons/p1.png", 360, 110, 25, 25));
		//add(GUI.getJButton("but6",but6,"utils/resources/currentTblgraph/01.png", 356, 110, 25, 25));
		
		add(GUI.getChkBut(check[0], false, "Solamente gráficos", 20, 145, 300, 25));
		add(GUI.getChkBut(check[1], true, "Incluir gráficos enlazados", 20, 170, 300, 25));
		add(GUI.getChkBut(check[2], true, "Incluir comentarios de las formas", 20, 195, 300, 25));
		add(GUI.getChkBut(check[3], false, "Incluir detalles de los roles", 20, 220, 300, 25));
		add(GUI.getChkBut(check[4], true,  "Páginas de detalle del gráfico", 20, 245, 300, 25));
		add(GUI.getChkBut(check[5], false, "Página de propiedades personalizadas", 20, 270, 350, 25));
		add(GUI.getChkBut(check[6], false, "Incluir documentos vinculados al grafico", 20, 295, 350, 25));
		add(GUI.getChkBut(check[7], true,  "Vincular el documento", 20, 320, 200, 25));
		add(GUI.getChkBut(check[8], false, "Incluir el documento", 20, 345, 200, 25));
		
		check[3].setEnabled(false);check[7].setEnabled(false);check[8].setEnabled(false);
		
		check[6].addItemListener(new ItemListener() {public void itemStateChanged(ItemEvent e) {
			check[7].setEnabled(check[6].isSelected());check[8].setEnabled(check[6].isSelected());  }});
		check[8].addItemListener(new ItemListener() {public void itemStateChanged(ItemEvent e) {
			check[7].setSelected( (check[8].isSelected() ? false : true) );  }});
		check[7].addItemListener(new ItemListener() {public void itemStateChanged(ItemEvent e) {
			check[8].setSelected( (check[7].isSelected() ? false : true) );  }});
		
		check[0].addItemListener(new ItemListener() {
		      public void itemStateChanged(ItemEvent e) {
		          if (check[0].isSelected()) {
		        	  check[3].setSelected(false);
		        	  check[4].setSelected(false);
		        	  check[5].setSelected(false);
		          }
		        }
		      });
		check[3].addItemListener(new ItemListener() {public void itemStateChanged(ItemEvent e) {if (check[3].isSelected()) {check[0].setSelected(false);}}});
		check[4].addItemListener(new ItemListener() {public void itemStateChanged(ItemEvent e) {if (check[4].isSelected()) {check[0].setSelected(false);}}});
		check[5].addItemListener(new ItemListener() {public void itemStateChanged(ItemEvent e) {if (check[5].isSelected()) {check[0].setSelected(false);}}});
		      
		but3.addMouseListener(mouseListener);
		but6.addMouseListener(mouseListener);
		
		
		add(getjbtOK(335, 380, 100, 25));
		add(getjbtCancel(225, 380, 100, 25));
		
		
		
	}
	/**
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return
	 */

	/**
	 * @return
	 */
	public MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			JButton wek = (JButton) e.getSource();
			
			if (wek.getName().equals("but3")){
				
				GetGraph g = new GetGraph(null,1);
				String res=g.getID();
				
				if (!res.equals("-1")) {
					txt9.setText(res);
					FileGID=g.getGidx();
					setTitle("Generación PDF "+res);
				}
			}
			if (wek.getName().equals("but6")){
				txt9.setText("");
			}
			if (wek.getName().equals("check")){
				
			}
			
			
		}
	};
	private Component getjtB1(int xx, int yy, int width, int height) {
		jtB1 = new JTextField();
		jtB1.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
		return jtB1;
	}


	private JComponent getLblImg(String icon, int xx, int yy, int width,
			int height) {
		ImageIcon iconOO = ImageUtil.createImageIcon(icon);
		lblImg = new JLabel(iconOO);
		lblImg.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,
		// height
		//lblImg.setBackground(Color.blue);
		return lblImg;
	}
	/**
	 * @return
	 */
	private Component getjLabel1(int xx, int yy, int width, int height,String bla) {
		jLabel1 = new JLabel();
		jLabel1.setText(bla);
		jLabel1.setBounds(new Rectangle(xx, yy, width, height));
		return jLabel1;
		
	}


	/**
	 * @return
	 */
	private Component getjbtCancel(int xx, int yy, int width, int height) {
		jbtCancel = new JButton();
		jbtCancel.setText("Cerrar");
		jbtCancel.setBounds(new Rectangle(xx, yy, width, height));
		jbtCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CancelPerformed(evt);
			}
		});
		return jbtCancel;
	}


	/**
	 * @param evt
	 */
	protected void CancelPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		this.dispose();
	}


	/**
	 * @return
	 */
	private Component getjbtOK(int xx, int yy, int width, int height)  {
		jbtOK = new JButton();
		jbtOK.setText("OK");
		jbtOK.setBounds(new Rectangle(xx, yy, width, height));
		jbtOK.setToolTipText("OK");
		jbtOK.addActionListener(new java.awt.event.ActionListener() {
		
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtActionPerformed(evt);
				
			}	
		});
		return jbtOK;
	}


	
	
	
	
	private void jbtActionPerformed(java.awt.event.ActionEvent evt) {
		if (txt9.getText().equals("")) {
			JOptionPane.showMessageDialog(
					null,
					"No ha informado del gráfico a exportar", "Exportación PDF",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		org.w3c.dom.Document document;
		OOGraphPDF pdfO = new OOGraphPDF();
		Boolean[] ops = new Boolean[9];
		for (int x=0;x<check.length;x++) ops[x]=check[x].isSelected();
		pdfO.buildReport("",FileGID,ops);
		this.dispose();
		
	}
	


	/**
	 * @return
	 */
	private Component getooO() {
		return ooO;
	}


	private void jtfInputKeyReleased(java.awt.event.KeyEvent evt) {
		inputString += evt.getKeyChar();
	}
	
	private JComponent getDivider(int yy) {
		JPanel divider = new JPanel();
		divider.setBackground(Color.RED); //new Color(SystemColor.WINDOW_TEXT));
		divider.setSize(new Dimension(this.getWidth(), 1));
		divider.setBounds(new Rectangle(5, yy, this.getWidth() - 10, 1));
		return divider;
	}

	public static void main(String[] args) {
		try {
			//ooExportWebDlg oo1 = new ooExportWebDlg(null,null);
			ooExportPDFDlg wek = new ooExportPDFDlg("");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
