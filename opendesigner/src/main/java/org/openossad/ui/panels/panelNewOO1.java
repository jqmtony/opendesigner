package org.openossad.ui.panels;

import org.openossad.ui.base.PanelNewBase;

import javax.swing.*;
import java.awt.event.MouseListener;

public class panelNewOO1 extends PanelNewBase
{
    private int h = 20;
    private int w = 150;
    private static final long serialVersionUID = 1L;
    public JLabel lbl;
    public JLabel lblTit;
    public JLabel lblImg;
    public JLabel lbl2;
    public JLabel lbl3;
    public JTextField txt1=new JTextField();

    /**
     * This is the default constructor
     */
    public panelNewOO1() {
        super("Creación de gráfico de procesos", "/ui/images/grafic_n_1_48.png");
        initialize();
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setVisible(false);

        comboTblprojects = new JComboBox();
        add(gui.getComboFolder(160, 42, (this.size().width - (250)), 25, comboTblprojects),null);

        add(gui.getLbl("Nombre del gráfico", 80, 100, w, h));
        add(gui.getTxt(txtGName,230, 100, (this.size().width - (280)), 20));
        add(gui.getTxt(txtGRef,230, 130, (this.size().width - (280)), 20));

        add(gui.getDivider(this, 173));
        add(gui.getCombo(50, 210));

        add(gui.getjxList(190, 200, 250, 200, 1));

        this.setVisible(true);
    }

    @Override
    public MouseListener newButtonCreateMouseListener()
    {
        return getListenerForTblgraphs();
    }
}
