package org.openossad.ui.component;

import org.openossad.data.domain.Tblgraphs;
import org.openossad.ui.base.PanelOpenBase;
import org.openossad.ui.ooExportPDFDlg;
import org.openossad.ui.ooNewGenericDlg;
import org.openossad.ui.ooOpenGenericDlg;
import org.openossad.ui.ooPropsGenericDlg;
import org.openossad.ui.panels.*;
import org.openossad.util.helper.DAOOOSSAD;
import org.openossad.util.helper.ReferencialOOSSAD;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonMore extends JButton {
    public JPopupMenu popupMenu = new JPopupMenu();
    public JMenuItem cutMenu1;
    public JMenuItem cutMenu2;
    public JMenuItem cutMenu3;
    public JMenuItem cutMenu31;
    public JMenuItem cutMenu4;
    public JMenuItem cutMenu5;
    public JMenuItem cutMenu6;
    private ooOpenGenericDlg currentDialog;


    public JButtonMore() {
        currentDialog = ooOpenGenericDlg.get();

        cutMenu1 = new JMenuItem("Nuevo");
        cutMenu1.addActionListener(new NewElementActionListener());
        cutMenu1.setEnabled(true);
        popupMenu.add(cutMenu1);

        popupMenu.addSeparator();

        // Copy
        cutMenu2 = new JMenuItem("Propiedades");
        cutMenu2.addActionListener(new PropertiesDialogActionListener());
        popupMenu.add(cutMenu2);

        // Paste
        cutMenu3 = new JMenuItem("Generación Web");
        cutMenu3.addActionListener(new popup3Listener());
        //cutMenu3.setEnabled(false);
        popupMenu.add(cutMenu3);

        cutMenu31 = new JMenuItem("Generación Doc");
        //cutMenu31.addActionListener(new popup3Listener());
        cutMenu31.setEnabled(false);
        popupMenu.add(cutMenu31);

        cutMenu5 = new JMenuItem("Exportar");
        cutMenu5.addActionListener(new popup3Listener());
        cutMenu5.setEnabled(false);
        popupMenu.add(cutMenu5);

        cutMenu6 = new JMenuItem("Exportar Pdf");
        cutMenu6.addActionListener(new ExportPdfActionListener());
//        cutMenu6.addMouseListener(exportPdfActionListener);
        popupMenu.add(cutMenu6);

        // Separator
        popupMenu.addSeparator();

        // Find
        cutMenu4 = new JMenuItem("Eliminar");
        cutMenu4.addActionListener(new DeleteActionListener());
        popupMenu.add(cutMenu4);

        // this.setComponentPopupMenu(popupMenu);

        this.setIcon(MetalIconFactory.getInternalFrameCloseIcon(16));
        this.setBounds(542, 440, 20, 30);

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (evt.isPopupTrigger()) {
                    // popupMenu.show(evt.getComponent(), evt.getX(),
                    // evt.getY());

                }
                popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());

            }

            public void mouseReleased(MouseEvent evt) {
                if (evt.isPopupTrigger()) {
                    // popupMenu.show(evt.getComponent(), evt.getX(),
                    // evt.getY());
                }
            }
        });

    }

    private String getCurrentTblgraphsGId()
    {
        currentDialog = ooOpenGenericDlg.get();
        PanelOpenBase panelOpenBase = (PanelOpenBase) currentDialog.currentComponent;
        return panelOpenBase.selectedToOpen;
    }

    private Component getAnalogOpenComponent(Component currentComponent)
    {
        if (currentComponent instanceof panelOpenOO1 ) {
            return new panelNewOO1();
        }
        if (currentComponent instanceof panelOpenOO2) {
            return new panelNewOO2();
        }
        if (currentComponent instanceof panelOpenOO3) {
            return new panelNewOO3();
        }
        if (currentComponent instanceof panelOpenOO4) {
            return new panelNewOO4();
        }
        if (currentComponent instanceof panelOpenOO5 ) {
            return new panelNewOO5();
        }
        if (currentComponent instanceof panelOpenOO6 ) {
            return new panelNewOO6();
        }
        if (currentComponent instanceof panelOpenOO7 ) {
            return new panelNewOO7();
        }
        if (currentComponent instanceof panelOpenOO8 ) {
            return new panelNewOO8();
        }
        if (currentComponent instanceof panelOpenOO9 ) {
            return new panelNewOO9();
        }


        return null;
    }


    class ExportPdfActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            String currentTblgraphGId = getCurrentTblgraphsGId();
            if (currentTblgraphGId == null || currentTblgraphGId.equals("")) {
                JOptionPane.showMessageDialog(null,"Por favor, seleccione el gráfico a exportar...","OpenDESIGNER", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Tblgraphs tblgraphs = DAOOOSSAD.getooGraphOBJ(currentTblgraphGId);

            if (tblgraphs == null) {
                return;
            }
            final int glevel = tblgraphs.getGlevel();
            switch(glevel) {
                case 4: return;
                case 5: return;
                case 6: return;
                case 7: return;
                default:
                    new ooExportPDFDlg(currentDialog, currentTblgraphGId);

            }

        }

    }
    class NewElementActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = (JFrame) SwingUtilities.windowForComponent(currentDialog);
            ooNewGenericDlg ooNewGenericDlg = new ooNewGenericDlg(frame);
            currentDialog.setVisible(false);
            final Component analogOpenComponent = getAnalogOpenComponent(currentDialog.currentComponent);
            if (analogOpenComponent==null)
            {
                currentDialog.setVisible(true);
                return;
            }
            ooNewGenericDlg.show(analogOpenComponent);
            ooNewGenericDlg.setVisible(true);

        }

    }

    class PropertiesDialogActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String currentTblgraphGId = getCurrentTblgraphsGId();

            if (currentDialog.currentComponent instanceof panelOpenOO1 ||
                currentDialog.currentComponent instanceof panelOpenOO2 ||
                currentDialog.currentComponent instanceof panelOpenOO3 ||
                currentDialog.currentComponent instanceof panelOpenOO4 ||
                currentDialog.currentComponent instanceof panelOpenOO5   ) {
                currentDialog.setVisible(false);
                ooPropsGenericDlg ooPropsGenericDlg = new ooPropsGenericDlg(null,currentTblgraphGId);
                ooPropsGenericDlg.setVisible(true);
            }



//
//			if (ct.equals("OO1")) {
//				panelOpenOO1 pn1 = (panelOpenOO1) dlg.currentComponent;
//				if (pn1.getIDGrafico().isEmpty()) {
//					JOptionPane.showMessageDialog(null,
//							"Por favor, seleccione un gráfico.",
//							"OpenDESIGNER", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				dlg.setVisible(false);
//				ooPropsGenericDlg oo1 = new ooPropsGenericDlg(null,pn1.getIDGrafico());
//				pn1.REDOgetMainJXTable();
//				dlg.setVisible(true);
//			}
//			if (ct.equals("transferedObject")) {
//				panelOpenOO2 pn1 = (panelOpenOO2) dlg.currentComponent;
//				if (pn1.getIDGrafico().isEmpty()) {
//					JOptionPane.showMessageDialog(null,
//							"Por favor, seleccione un gráfico.",
//							"OpenDESIGNER", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				dlg.setVisible(false);
//				ooPropsGenericDlg oo1 = new ooPropsGenericDlg(null,pn1.getIDGrafico());
//				pn1.REDOgetMainJXTable();
//				dlg.setVisible(true);
//			}
//
//			if (ct.equals("OO3")) {
//				panelOpenOO3 pn1 = (panelOpenOO3) dlg.currentComponent;
//				if (pn1.getIDGrafico().isEmpty()) {
//					JOptionPane.showMessageDialog(null,
//									"Por favor, seleccione un gráfico.",
//									"OpenDESIGNER", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				dlg.setVisible(false);
//				ooPropsGenericDlg oo1 = new ooPropsGenericDlg(null,pn1.getIDGrafico());
//				pn1.REDOgetMainJXTable();
//				dlg.setVisible(true);
//			}
//
//			if (ct.equals("OO4")) {
//				panelOpenOO4 pn1 = (panelOpenOO4) dlg.currentComponent;
//				if (pn1.getIDGrafico().isEmpty()) {
//					JOptionPane.showMessageDialog(
//									null,
//									"Por favor, seleccione un gráfico.",
//									"OpenDESIGNER", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				dlg.setVisible(false);
//				ooPropsGenericDlg oo1 = new ooPropsGenericDlg(null,pn1.getIDGrafico());
//				pn1.REDOgetMainJXTable();
//				dlg.setVisible(true);
//			}
//			if (ct.equals("OO5")) {
//				panelOpenOO5 pn1 = (panelOpenOO5) dlg.currentComponent;
//				if (pn1.getIDGrafico().isEmpty()) {
//					JOptionPane.showMessageDialog(
//									null,
//									"Por favor, seleccione un gráfico.",
//									"OpenDESIGNER", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				dlg.setVisible(false);
//				ooPropsGenericDlg oo1 = new ooPropsGenericDlg(null,pn1.getIDGrafico());
//				pn1.REDOgetMainJXTable();
//				dlg.setVisible(true);
//			}
//
//			if (ct.equals("OO6")) {
//				panelOpenOO6 pn1 = (panelOpenOO6) dlg.currentComponent;
//				if (pn1.getIDGrafico().isEmpty()) {
//					JOptionPane.showMessageDialog(
//									null,
//									"Por favor, seleccione un indicador.",
//									"OpenDESIGNER", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				dlg.setVisible(false);
//				ooIndicsGenericDlg oo1 = new ooIndicsGenericDlg(null,pn1.getIDGrafico());
//				pn1.REDOgetMainJXTable();
//				dlg.setVisible(true);
//			}
//
//
//
//
//
//			if (ct.equals("OO8")) {
//				panelOpenOO8 pn1 = (panelOpenOO8) dlg.currentComponent;
//				if (pn1.getIDGrafico().isEmpty()) {
//					JOptionPane.showMessageDialog(
//									null,
//									"Por favor, seleccione un actor.",
//									"OpenDESIGNER", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				dlg.setVisible(false);
//				ooUserGenericDlg oo1 = new ooUserGenericDlg(null,Integer.parseInt(pn1.getIDGrafico()));
//				pn1.REDOgetMainJXTable();
//				dlg.setVisible(true);
//			}
//			if (ct.equals("OO9")) {
//				panelOpenOO9 pn1 = (panelOpenOO9) dlg.currentComponent;
//				if (pn1.getIDGrafico().isEmpty()) {
//					JOptionPane.showMessageDialog(
//									null,
//									"Por favor, seleccione un grupo.",
//									"OpenDESIGNER", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				dlg.setVisible(false);
//				ooUserGenericDlg oo1 = new ooUserGenericDlg(null,Integer.parseInt(pn1.getIDGrafico()));
//				pn1.REDOgetMainJXTable();
//				dlg.setVisible(true);
//			}



        }

    }
    //
//	class popup3Listener implements ActionListener {
//		popup3Listener() {
//		}
//
//		public void actionPerformed(ActionEvent e) {
//			System.out.println("Generación Web");
//			ooOpenGenericDlg dlg = (ooOpenGenericDlg) SwingUtilities
//			.windowForComponent((Component) getParent().getParent().getParent().getParent());
//			String ct = dlg.currentComponent.getClass().getName();
//			ct = ct.substring((ct.length() - 3), ct.length());
//
//			JFrame frame = (JFrame) SwingUtilities.windowForComponent((Component) dlg.getParent());
//			ooNewGenericDlg dlgN = new ooNewGenericDlg(frame);
//			dlg.setVisible(false);
//			if (ct.equals("OO1")) {
//				panelOpenOO1 pn1 = (panelOpenOO1) dlg.currentComponent;
//				if (pn1.getIDGrafico().isEmpty()) {
//					JOptionPane.showMessageDialog(null,
//							"Por favor, seleccione el gráfico a exportar...",
//							"OpenDESIGNER", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				//ooExportWebDlg expW = new ooExportWebDlg(dlg,pn1.getIDGrafico());
//				OOGraphPDF pdf = new OOGraphPDF();
//				pdf.exportGraph(pn1.getIDGrafico());
//
//			}
//			if (ct.equals("transferedObject")) {
//				panelOpenOO2 pn1 = (panelOpenOO2) dlg.currentComponent;
//				if (pn1.getIDGrafico().isEmpty()) {
//					JOptionPane.showMessageDialog(null,
//							"Por favor, seleccione el gráfico a exportar...",
//							"OpenDESIGNER", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				//ooExportWebDlg expW = new ooExportWebDlg(dlg,pn1.getIDGrafico());
//				OOGraphPDF pdf = new OOGraphPDF();
//				pdf.exportGraph(pn1.getIDGrafico());
//			}
//			if (ct.equals("OO3")) {
//				panelOpenOO3 pn1 = (panelOpenOO3) dlg.currentComponent;
//				if (pn1.getIDGrafico().isEmpty()) {
//					JOptionPane.showMessageDialog(null,
//							"Por favor, seleccione el gráfico a exportar...",
//							"OpenDESIGNER", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				//ooExportWebDlg expW = new ooExportWebDlg(dlg,pn1.getIDGrafico());
//				OOGraphPDF pdf = new OOGraphPDF();
//				pdf.exportGraph(pn1.getIDGrafico());
//
//			}
//			if (ct.equals("OO4")) {
//				panelOpenOO4 pn1 = (panelOpenOO4) dlg.currentComponent;
//				if (pn1.getIDGrafico().isEmpty()) {
//					JOptionPane.showMessageDialog(null,
//							"Por favor, seleccione el gráfico a exportar...",
//							"OpenDESIGNER", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				//ooExportWebDlg expW = new ooExportWebDlg(dlg,pn1.getIDGrafico());
//				OOGraphPDF pdf = new OOGraphPDF();
//				pdf.exportGraph(pn1.getIDGrafico());
//			}
//		}
//
//	}
//
    public class DeleteActionListener implements ActionListener { // extends
        // AbstractAction


        public void actionPerformed(ActionEvent e) {
            String currentTblgraphGId = getCurrentTblgraphsGId();

            if (currentTblgraphGId.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Por favor, seleccione el elemento a eliminar...","OpenDESIGNER", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Integer resultConfirm = JOptionPane.showConfirmDialog(null,"¿ Desea eliminar el elemento ?", "OpenDESIGNER",JOptionPane.INFORMATION_MESSAGE);
            if (!resultConfirm.equals(0)) {
                return;
            }
            if (currentDialog.currentComponent instanceof panelOpenOO1 ||
                currentDialog.currentComponent instanceof panelOpenOO2 ||
                currentDialog.currentComponent instanceof panelOpenOO3 ||
                currentDialog.currentComponent instanceof panelOpenOO4 ||
                currentDialog.currentComponent instanceof panelOpenOO5   ) {
                    ReferencialOOSSAD.deleteGraph(currentTblgraphGId);
                }

                if (currentDialog.currentComponent instanceof panelOpenOO6) {
                    ReferencialOOSSAD.deleteIndicador(Integer.parseInt(currentTblgraphGId));
                }
                if (currentDialog.currentComponent instanceof panelOpenOO7) {

                }
                if (currentDialog.currentComponent instanceof panelOpenOO8) {

                }
                if (currentDialog.currentComponent instanceof panelOpenOO9) {

                }

            PanelOpenBase panelOpenBase = (PanelOpenBase) currentDialog.currentComponent;
            panelOpenBase.updateTableModelJXTable();

        }

    }

    private class popup3Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
