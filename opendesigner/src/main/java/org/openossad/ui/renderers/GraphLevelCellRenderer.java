package org.openossad.ui.renderers;

import org.openossad.data.domain.Tblgraphs;
import org.openossad.util.ImageUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 28/09/11
 * Time: 15:38
 * To change this template use File | Settings | File Templates.
 */

public class GraphLevelCellRenderer implements ListCellRenderer
{
    private JRadioButton rb1,rb2,rb3;

    Icon m1 = ImageUtil.createImageIcon("/ui/images/model_n_1_16.png");
    Icon m2 = ImageUtil.createImageIcon("/ui/images/model_n_2_16.png");
    Icon m3 = ImageUtil.createImageIcon("/ui/images/model_n_3_16.png");
    String[] str1;
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected, boolean cellHasFocus) {
        Font theFont = null;
        Color theForeground = null;
        Icon theIcon = null; // MetalIconFactory.getTreeFolderIcon();
        String theText = null;

        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Tblgraphs) {
            Tblgraphs obj = (Tblgraphs) value;
            renderer.setText(obj.getGname());
            switch (obj.getGlevel()) {
                case 5 : {renderer.setIcon(m1);break;}
                case 11 : {renderer.setIcon(m1);break;}
                case 6 : {renderer.setIcon(m2);break;}
                case 12 : {renderer.setIcon(m2);break;}
                case 7 : {renderer.setIcon(m3);break;}
                case 13 : {renderer.setIcon(m3);break;}
                default : renderer.setIcon(m1);
            }

        }

        return renderer;
    }
}