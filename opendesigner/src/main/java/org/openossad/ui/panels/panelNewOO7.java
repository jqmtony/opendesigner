package org.openossad.ui.panels;

import org.openossad.data.domain.Tblentity;
import org.openossad.data.domain.Tblprojects;
import org.openossad.ui.base.PanelNewBase;
import org.openossad.util.helper.ReferencialOOSSAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class panelNewOO7 extends PanelNewBase
{

    public JLabel lblImg;
    public JLabel lblImg2;
    public JLabel lbl2;
    public JLabel lbl3;
    public JTextField txt1;
    public JTextField txt2;
    public JTextField txt3;
    private ButtonGroup g; // = new ButtonGroup();
    private JRadioButton rb1, rb2, rb3;
    private JLabel lblTit;

    public panelNewOO7() {
        super("Roles","/ui/images/technorati_48.png");
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
        add(gui.getLbl("Nombre del rol", 80, 100, w, h), null);

        add(gui.getTxt(txtGName, 230, 100, (this.size().width - (280)), 20), null);
        add(gui.getTxt(txtGRef,230, 130, (this.size().width - (280)), 20), null);

        add(gui.getDivider(this,173), null);
        add(getComboChooseTypeRole(50, 210), null);
        //add(gui.getLblImg("/ui/images/1.png", 190, 200, 250, 200), null);

        this.setVisible(true);
    }

    public JComponent getComboChooseTypeRole(int xx, int yy) {
        g = new ButtonGroup();
        rb1 = new JRadioButton("Rol Interno ", false);
        rb2 = new JRadioButton("Rol Externo", false);
        rb3 = new JRadioButton("Unidad     ", false);
        rb1.addActionListener(al);
        rb1.setSelected(true);
        rb2.addActionListener(al);
        rb3.addActionListener(al);
        g.add(rb1);
        g.add(rb2);
        g.add(rb3);
        Container cp = new JPanel();
        cp.setLayout(new FlowLayout());
        cp.setBounds(new Rectangle(xx, yy, 100, 130));
        lblTit = new JLabel();
        lblTit.setVerticalTextPosition(SwingConstants.CENTER);
        lblTit.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTit.setBorder(BorderFactory.createLineBorder(Color.black));
        lblTit.setBounds(new Rectangle(1, 1, 90, 130));
        cp.add(rb1);
        cp.add(rb2);
        cp.add(rb3);
        cp.setVisible(true);
        return (JPanel) cp;
    }

    private ActionListener al = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Integer level = 1;
            if (rb1.isSelected())
                level = 1;
            if (rb2.isSelected())
                level = 2;
            if (rb3.isSelected())
                level = 3;
            // TODO: asdf
            remove(lblImg2);
            // repaint();
            add(gui.getLblImg("images/" + level.toString() + ".png", 190, 200,250, 200), null);
            repaint();

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
                    JOptionPane.showMessageDialog(null, "Por favor, rellene los campos para crear el rol.", "Validación", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                Tblprojects project = (Tblprojects) currentPanel.comboTblprojects.getSelectedItem();
                String GType="U";
                if (rb1.isSelected()) GType = "I";
                if (rb2.isSelected()) GType = "E";
                if (rb3.isSelected()) GType = "U";
                Tblentity tblentity = ReferencialOOSSAD.createNewRole(currentPanel.txtGName.getText(), currentPanel.txtGRef.getText(), project, GType);
                currentDialog.setVisible(false);
            }
        };

    }
}
