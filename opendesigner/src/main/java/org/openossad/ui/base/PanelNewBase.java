package org.openossad.ui.base;

import org.openossad.BasicGraphEditorOO;
import org.openossad.data.domain.Tblgraphs;
import org.openossad.data.domain.Tblprojects;
import org.openossad.util.GUItools;
import org.openossad.util.helper.ReferencialOOSSAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 16/10/11
 * Time: 17:08
 * To change this template use File | Settings | File Templates.
 */
public abstract class PanelNewBase extends JDesktopPane implements IPanelNewBase
{

    public GUItools gui = new GUItools();

    public JLabel lbl0;
    public JLabel lbl1;
    public JLabel lbl2;
    public JLabel lbl3;
    public JLabel lbl4;
    public JLabel lbl5;

    public String selectedToOpen = "";
    public Integer level;
    public Integer orientation;


    public boolean isModel = false;


    protected int w = 150;
	protected int h = 20;


    public JTextField txtGName =new JTextField();
    public JTextField txtGRef =new JTextField();


    public JComboBox comboTblprojects;
    private String title;
    private String imageTitle;
    private static IPanelNewBase iPanelOpenBase;


    public PanelNewBase(String title, String imageTitle)
    {
        super();
        this.title=title;
        this.imageTitle=imageTitle;
        iPanelOpenBase=this;
        comboTblprojects = new JComboBox();
        initComponents();
    }

    private void initComponents()
    {
        setBackground(SystemColor.control);
        setSize(600, 470);

        add(gui.getLblTit(title, 0, 0, 600, 30), null);
		add(gui.getLblImg(imageTitle, 10, 100, 50, 50), null);
        add(gui.getLbl("Referencia", 80, 130, w, h), null);


        add(gui.getLbl("Crear en carpeta", 30, 43, 130, 20), null);
        add(gui.getDivider(this,427));
        add(gui.getbutFolder(520, 42, 25, 25), null);
        add(gui.getJButtonCreate(600 - 120, 440, 103, 30));
        add(gui.getJButtonCancel(600 - 240, 440, 103, 30));


        //tableModel1 = new TableModel1();
    }

    protected MouseListener getListenerForTblgraphs(){
        return new MouseAdapter()
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
                        Tblgraphs model = (Tblgraphs) gui.jxList.getElementAt(gui.jxList.getSelectedIndex());
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
    }


    public static IPanelNewBase get()
    {
        return iPanelOpenBase;
    }
}