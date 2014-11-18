/**
 *
 */
package org.openossad.util;

import org.jdesktop.swingx.JXList;
import org.openossad.BasicGraphEditorOO;
import org.openossad.data.dao.HibernateDAOFactory;
import org.openossad.data.dao.TblprojectsDAO;
import org.openossad.data.domain.Tblgraphs;
import org.openossad.data.domain.Tblindic;
import org.openossad.data.domain.Tblprojects;
import org.openossad.ui.base.GraphLevelListModel;
import org.openossad.ui.base.PanelNewBase;
import org.openossad.ui.base.PanelOpenBase;
import org.openossad.ui.base.PanelPropsBase;
import org.openossad.ui.ooFolderExplorer;
import org.openossad.ui.ooIndicsGenericDlg;
import org.openossad.ui.ooPropsGenericDlg;
import org.openossad.ui.renderers.ComboBoxRenderer;
import org.openossad.ui.renderers.GraphLevelCellRenderer;
import org.openossad.util.helper.ReferencialOOSSAD;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * OpenDESIGNER-2.0.3
 * GUItools.java,20/03/2009,
 * Copyright (c) 2009,  Xavi Hidalgo
 *
 * //////////////////////////////////////////////////////
 * Este código es propiedad de TricomZone, S.L.
 * Sujeto a la licencia LGPL.
 *
 * //////////////////////////////////////////////////////
 *
 */

public class GUItools {

    HibernateDAOFactory hibernateDAOFactory = new HibernateDAOFactory();

    public JLabel lblTit;
    JLabel lblImg;
    JLabel lbl;
    JPanel divider;
    JLabel borderLabel;
    private ButtonGroup buttonGroupModels;
    private JRadioButton rb1, rb2, rb3;
    public JXList jxList;
    private GraphLevelCellRenderer graphLevelCellRenderer;
    public JScrollPane scrollPane;
    private JButton buttonCancel;
    public JButton buttonCreateGraph;
    private JButton butFolder;
    private JPanel buttonGroupModelsContainer;

    public JTextField documentToCatch;

    public JButton buttonOpenGraph;
    private JButton buttonNewDocument;
    private JButton buttonOpenDocument;
    private JButton buttonSaveProps;
    private JButton buttonOpenIndic;
    private JButton buttonNewIndic;
    private JButton buttonCreate;


    public GUItools()
    {
        jxList=new JXList();
        graphLevelCellRenderer = new GraphLevelCellRenderer();
        scrollPane=new JScrollPane();
    }


    public JComponent getLblImg(String icon, int xx, int yy, int width,int height) {
        ImageIcon iconOO = createImageIcon(icon, icon);
        lblImg = new JLabel(iconOO);
        lblImg.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,
        return lblImg;
    }

    public JComponent getLblImg(String icon, int xx, int yy, int width,int height,Boolean border) {
        JComponent lblImg=getLblImg(icon,xx, yy, width, height);
        if (border) lblImg.setBorder(BorderFactory.createEtchedBorder());
        return lblImg;
    }

    public JComponent getLbl(String label, int xx, int yy, int width,int height) {
        lbl = new JLabel(label);
        lbl.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,height
        return lbl;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    public static ImageIcon createImageIcon(String path, String description) {
        return ImageUtil.createImageIcon(path);
    }
    public JButton getJButton(String Name,JButton jButton,String label,int xx, int yy, int width, int height) {

        //truqui para que instancie la clase que es comp
        jButton.setName(Name);
        return getJButton(jButton,label,xx, yy,  width,  height);
    }
    public JButton getJButton(JButton jButton,String label,int xx, int yy, int width, int height) {
        if (label.lastIndexOf(".")>0) {
            jButton.setIcon(createImageIcon(label,label));
        }
        else {
            jButton.setText(label);
        }
        jButton.setBounds(new Rectangle(xx, yy, width, height));
        jButton.setToolTipText(label);
        return jButton;
    }

    public JLabel getLblTit(String label, int xx, int yy, int width, int height) {
        lblTit = new JLabel("    "+label);
        lblTit.setBounds(new Rectangle(xx, yy+1, width-2, height)); // x,y,width,
        // height
        lblTit.setFont(new Font(lblTit.getFont().getName(), Font.BOLD, 19));
        lblTit.setVerticalTextPosition(SwingConstants.CENTER);
        lblTit.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTit.setBorder(BorderFactory.createLineBorder(Color.black));
        lblTit.setOpaque(true);
        lblTit.setBackground(new Color(144, 153, 174));
        lblTit.setForeground(Color.white);
        return lblTit;
    }



    public JComponent getDivider(Component parent,int yy) {
        divider = new JPanel();
        divider.setBackground(new Color(SystemColor.WINDOW_BORDER));
        divider.setSize(new Dimension(parent.getWidth(), 1));
        divider.setBounds(new Rectangle(5, yy, parent.getWidth() - 10, 1));
        return divider;
    }

    public JComponent getTxt(JTextField txt,int xx, int yy, int width, int height) {
        txt.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
        txt.setBorder(BorderFactory.createLoweredBevelBorder());
        return txt;
    }

    public JComponent getLbl(JLabel lbl,String labelTxt,int xx, int yy, int width, int height) {
        lbl.setText(labelTxt);
        lbl.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,height
        return lbl;
    }
    /**
     * @param panel
     * @return
     */

    public Component getBorder(JWindow panel) {
        borderLabel=new JLabel();
        borderLabel.setBounds(new Rectangle(1, 1, panel.getWidth()-2, panel.getHeight()-2));
        borderLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        borderLabel.setOpaque(false);
        borderLabel.setForeground(Color.black);
        return borderLabel;
    }


    public JComponent getChkBut(JCheckBox check,boolean selected,String label,int xx, int yy, int width, int height) {
        check.setText(label);
        check.setBounds(new Rectangle(xx, yy, width, height));
        check.setSelected(selected);
        return check;
    }

    public JComboBox getComboFolder(int xx, int yy, int width, int height, JComboBox comboTblprojectsLocal) {
            comboTblprojectsLocal.removeAllItems();
            loadFolderCombo(comboTblprojectsLocal);
            comboTblprojectsLocal.setRenderer(new ComboBoxRenderer());
            comboTblprojectsLocal.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,



        return comboTblprojectsLocal;
    }

    public JComponent getCombo(int xx, int yy) {
        if (buttonGroupModelsContainer ==null) {
            buttonGroupModels = new ButtonGroup();
            rb1=new JRadioButton();
            rb2=new JRadioButton();
            rb3=new JRadioButton();
            rb1.setText("Nivel 1");
            rb2.setText("Nivel 2");
            rb3.setText("Nivel 3");
            rb1.setSelected(true);
            buttonGroupModels.add(rb1);
            buttonGroupModels.add(rb2);
            buttonGroupModels.add(rb3);
            buttonGroupModelsContainer = new JPanel();
            buttonGroupModelsContainer.setLayout(new FlowLayout());
            buttonGroupModelsContainer.setBounds(new Rectangle(xx, yy, 100, 130));
            lblTit = new JLabel();
            lblTit.setVerticalTextPosition(SwingConstants.CENTER);
            lblTit.setHorizontalTextPosition(SwingConstants.CENTER);
            lblTit.setBorder(BorderFactory.createLineBorder(Color.black));
            lblTit.setBounds(new Rectangle(1, 1, 90, 130));
            buttonGroupModelsContainer.add(rb1);
            buttonGroupModelsContainer.add(rb2);
            buttonGroupModelsContainer.add(rb3);
            buttonGroupModelsContainer.setVisible(true);
            MouseListener comboModelsActionListener = new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {

                    PanelNewBase currentPanel = (PanelNewBase) e.getComponent().getParent().getParent();
                     Integer level = 1;
                    if (rb1.isSelected())
                    {
                        level = 1;
                    }
                    if (rb2.isSelected())
                    {
                        level = 2;
                    }
                    if (rb3.isSelected())
                    {
                        level = 3;
                    }
                    currentPanel.remove(scrollPane);
                    currentPanel.remove(lblImg);
                    currentPanel.add(getjxList(190, 200, 250, 200, level));
                    currentPanel.add(getLblImg("/ui/images/model_n_" + level + "_48.png", 10, 100, 50, 50), null);
                    currentPanel.repaint();

                }
            };

            rb1.addMouseListener(comboModelsActionListener);
            rb2.addMouseListener(comboModelsActionListener);
            rb3.addMouseListener(comboModelsActionListener);

        }


        return buttonGroupModelsContainer;
    }




    public void loadFolderCombo(JComboBox elcomb) {
        hibernateDAOFactory.getCurrentSession().beginTransaction();
        TblprojectsDAO tblProjectsDAO = hibernateDAOFactory.getTblprojectsDAO();
        java.util.List<Tblprojects> tblProjectsList = tblProjectsDAO.findAll();
        for (Tblprojects tblprojects : tblProjectsList ){
            elcomb.addItem(tblprojects);
        }

    }

    public JComponent getjxList(int xx, int yy, int width, int height, int GLevel)
    {
        jxList.setModel(new GraphLevelListModel(GLevel));
        jxList.setCellRenderer(graphLevelCellRenderer);
        scrollPane = new JScrollPane(jxList);
        scrollPane.setBounds(new Rectangle(xx, yy, width, height));
        jxList.setSelectedIndex(0);
        return scrollPane;
    }


    public JComboBox addFakeComboBoxProjects(final int size)
    {
        JComboBox jcb= new JComboBox();
        jcb.setBounds(new Rectangle(160, 42, (size - (250)), 25));
        jcb.addItem("OpenOSSAD");
        jcb.setEnabled(false);
        return jcb;
    }


    public JButton getJButtonCancel(int xx, int yy, int width, int height) {
        if (buttonCancel==null) {
            buttonCancel = new JButton("Cancelar", MetalIconFactory.getInternalFrameCloseIcon(16));
            buttonCancel.setBounds(new Rectangle(xx, yy, width, height));
            buttonCancel.setToolTipText("Cancelar");
            MouseListener thisButtonCancelListener = new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    JDialog currentDialog = (JDialog) SwingUtilities.windowForComponent(e.getComponent());
                    currentDialog.setVisible(false);

                }
            };
            buttonCancel.addMouseListener(thisButtonCancelListener);
        }

        return buttonCancel;
    }


    public JComponent getbutFolder(int xx, int yy, int width, int height) {
        if (butFolder == null) {
            butFolder = new JButton(MetalIconFactory.getFileChooserNewFolderIcon());
            butFolder.setBounds(new Rectangle(xx, yy, width, height)); // x,y,width,
            butFolder.setToolTipText("Gestión de carpetas");
            MouseListener thisButtonFolderListener = new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    final Container parent = e.getComponent().getParent();
                    JDesktopPane currentPanel = (JDesktopPane) parent;

                    ooFolderExplorer oo1 = new ooFolderExplorer(null);
                    oo1.setVisible(true);
                    if (parent instanceof  PanelNewBase) {
                        PanelNewBase panelNewBase = (PanelNewBase) parent;
                        panelNewBase.comboTblprojects.removeAllItems();
                        loadFolderCombo(panelNewBase.comboTblprojects);
                    }
                }
            };
            butFolder.addMouseListener(thisButtonFolderListener);
        }


        return butFolder;
    }


    public JButton getJButtonCreateGraph(int xx, int yy, int width, int height) {
        if (buttonCreateGraph == null) {
            buttonCreateGraph = new JButton("OK", MetalIconFactory.getMenuItemArrowIcon());
            buttonCreateGraph.setBounds(new Rectangle(xx, yy, width, height));
            buttonCreateGraph.setToolTipText("OK");
            MouseListener thisButtonCreateListener = new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    JDialog currentDialog = (JDialog) SwingUtilities.windowForComponent(e.getComponent());
                    PanelNewBase currentPanel = (PanelNewBase) e.getComponent().getParent();


                    if (currentPanel.txtGName.getText().equals("") || currentPanel.txtGRef.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Por favor, rellene los campos para crear el gráfico.", "Validación", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    try
                    {
                         Tblgraphs model = (Tblgraphs) jxList.getElementAt(jxList.getSelectedIndex());
                        Tblprojects project = (Tblprojects) currentPanel.comboTblprojects.getSelectedItem();
                        Tblgraphs newTblgraphs = ReferencialOOSSAD.createNewOOGraph(model, currentPanel.txtGName.getText(), currentPanel.txtGRef.getText(), project, currentPanel.isModel, false);
                        BasicGraphEditorOO.get().doOpenGraph(newTblgraphs);
                        currentDialog.setVisible(false);
                    }
                    catch(IOException exception)
                    {
                        exception.printStackTrace();
                    }


                }
            };
            buttonCreateGraph.addMouseListener(thisButtonCreateListener);
        }
        return buttonCreateGraph;
    }

    public JButton getJButtonOpenGraph(int xx, int yy, int width, int height) {
        if (buttonOpenGraph == null) {
            buttonOpenGraph = new JButton("OK", MetalIconFactory.getMenuItemArrowIcon());
            buttonOpenGraph.setBounds(new Rectangle(xx, yy, width, height));
            buttonOpenGraph.setToolTipText("Open");
            MouseListener thisButtonOpenListener = new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    JDialog currentDialog = (JDialog) SwingUtilities.windowForComponent(e.getComponent());
                    PanelOpenBase currentPanel = (PanelOpenBase) e.getComponent().getParent();
                    final String selectedToOpen = currentPanel.selectedToOpen;
                    if (!selectedToOpen.equals("")) {
                        try
                        {
                            BasicGraphEditorOO.get().doOpenGraph(selectedToOpen);
                        }
                        catch(IOException e1)
                        {
                            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        currentDialog.setVisible(false);
                    }


                }
            };
            buttonOpenGraph.addMouseListener(thisButtonOpenListener);
        }
        return buttonOpenGraph;
    }

    public JButton getJButtonOpenIndic(int xx, int yy, int width, int height) {
        if (buttonOpenIndic == null) {
            buttonOpenIndic = new JButton("OK", MetalIconFactory.getMenuItemArrowIcon());
            buttonOpenIndic.setBounds(new Rectangle(xx, yy, width, height));
            buttonOpenIndic.setToolTipText("Open");
            MouseListener thisButtonOpenIndicListener = new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    JDialog currentDialog = (JDialog) SwingUtilities.windowForComponent(e.getComponent());
                    Frame frame = (Frame) SwingUtilities.windowForComponent(BasicGraphEditorOO.get());
                    PanelOpenBase currentPanel = (PanelOpenBase) e.getComponent().getParent();
                    currentDialog.setVisible(false);
                    ooIndicsGenericDlg indicGenericDlg = new ooIndicsGenericDlg(frame,Integer.parseInt(currentPanel.selectedToOpen));
                }
            };
            buttonOpenIndic.addMouseListener(thisButtonOpenIndicListener);
        }
        return buttonOpenIndic;
    }

    public JComponent getComboFolder(JComboBox comboTblprojects, int size)
    {
        return getComboFolder(160, 42, size, 25,comboTblprojects);
    }

    public JButton getJButtonCreateIndic(int xx, int yy, int width, int height) {
        if (buttonNewIndic == null) {
            buttonNewIndic = new JButton("OK", MetalIconFactory.getMenuItemArrowIcon());
            buttonNewIndic.setBounds(new Rectangle(xx, yy, width, height));
            buttonNewIndic.setToolTipText("Create new indic");
            MouseListener thisButtonCreateIndicatortListener = new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    JDialog currentDialog = (JDialog) SwingUtilities.windowForComponent(e.getComponent());
                    PanelNewBase currentPanel = (PanelNewBase) e.getComponent().getParent();


                    if (currentPanel.txtGName.getText().equals("") || currentPanel.txtGRef.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Por favor, rellene los campos para crear el indicador.", "Validación", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                        Tblprojects project = (Tblprojects) currentPanel.comboTblprojects.getSelectedItem();
                        Tblindic newTblindic = ReferencialOOSSAD.createNewIndic(project, currentPanel.txtGName.getText(), currentPanel.txtGRef.getText());
                        currentDialog.setVisible(false);
                }
            };
            buttonNewIndic.addMouseListener(thisButtonCreateIndicatortListener);
        }
        return buttonNewIndic;

    }

    public JButton getJButtonCreateDocument(int xx, int yy, int width, int height) {
        if (buttonNewDocument == null) {
            buttonNewDocument = new JButton("OK", MetalIconFactory.getMenuItemArrowIcon());
            buttonNewDocument.setBounds(new Rectangle(xx, yy, width, height));
            buttonNewDocument.setToolTipText("Create new document");
            MouseListener thisButtonCreateDocumentListener = new MouseAdapter()
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
                        Tblgraphs newTblgraphsDoc = ReferencialOOSSAD.createNewOODocument(project, currentPanel.txtGName.getText(), currentPanel.txtGRef.getText(), documentToCatch.getText());

                        currentDialog.setVisible(false);
                    }
                    catch(IOException exception)
                    {
                        exception.printStackTrace();
                    }


                }
            };
            buttonNewDocument.addMouseListener(thisButtonCreateDocumentListener);
        }
        return buttonNewDocument;

    }

    public JComponent getDocumentToCatchTextField(int xx, int yy, int width, int height) {
        if (documentToCatch==null) {
            documentToCatch = new JTextField();
            documentToCatch.setBounds(new Rectangle(xx, yy, width, height)); //x,y,width,height
         }
        return documentToCatch;
    }

    public JButton getJButtonSaveProps(int xx, int yy, int width, int height) {
            if (buttonSaveProps == null) {
            buttonSaveProps = new JButton("Save", MetalIconFactory.getMenuItemArrowIcon());
            buttonSaveProps.setBounds(new Rectangle(xx, yy, width, height));
            buttonSaveProps.setToolTipText("Open");
            MouseListener thisButtonSavePropsListener = new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    ooPropsGenericDlg currentDialog = (ooPropsGenericDlg) SwingUtilities.windowForComponent(e.getComponent());
                    PanelPropsBase currentPanel = (PanelPropsBase) e.getComponent().getParent();

                    currentDialog.saveTblgraphsUpdated();
                    currentDialog.setVisible(false);

                }
            };
            buttonSaveProps.addMouseListener(thisButtonSavePropsListener);
        }
        return buttonSaveProps;
    }

    public JButton getJButtonCreate(int xx, int yy, int width, int height) {
        if (buttonCreate == null) {
            buttonCreate = new JButton("OK", MetalIconFactory.getMenuItemArrowIcon());
            buttonCreate.setBounds(new Rectangle(xx, yy, width, height));
            buttonCreate.setToolTipText("OK");
            MouseListener mouseListener = PanelNewBase.get().newButtonCreateMouseListener();
            buttonCreate.addMouseListener(mouseListener);
        }
        return buttonCreate;
    }
}
