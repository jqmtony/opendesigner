package org.openossad.ui.renderers;

import org.openossad.data.domain.Tblprojects;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 28/09/11
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */
public class ComboBoxRenderer extends JLabel implements ListCellRenderer {
		String[] str1;

		public ComboBoxRenderer() {
			setOpaque(true);
		}

		public ComboBoxRenderer getListCellRendererComponent(JList list,Object value, int index, boolean isSelected,boolean cellHasFocus) {
			// Get the selected index. (The index param isn't
			// always valid, so just use the value.)
			// int selectedIndex = ((Integer)value).intValue();

			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			// Set the icon and text. If icon was null, say so.
			setIcon(MetalIconFactory.getTreeFolderIcon());
            Tblprojects obj = (Tblprojects) value;


			setText(getIndent(obj.getFldLevel()) + obj.getProjectName());

			return this;

		}

    private String getIndent(Integer fldLevel)
    {
        StringBuilder builder = new StringBuilder();
        for (int x=0;x<fldLevel;x++) {
            builder.append(" ");
        }
        return builder.toString();
    }

}

