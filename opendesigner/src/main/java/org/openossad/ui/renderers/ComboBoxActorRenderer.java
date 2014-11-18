/**
 * 
 */
package org.openossad.ui.renderers;

import org.openossad.util.ImageUtil;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;

/**
 * OpenDESIGNER-2.0.3
 * ComboBoxActorRenderer.java,10/03/2009,
 * Copyright (c) 2009,  Xavi Hidalgo
 *
 * //////////////////////////////////////////////////////
 * Este código es propiedad de TricomZone, S.L.
 * Sujeto a la licencia LGPL.
 * 
 * //////////////////////////////////////////////////////
 *
 */

public class ComboBoxActorRenderer extends JLabel implements ListCellRenderer {
	String[] str1;
	
	Icon m1 = ImageUtil.createImageIcon("/ui/images/001_55.gif");
	Icon m2 = ImageUtil.createImageIcon("/ui/images/001_57.gif");
	public ComboBoxActorRenderer() {
		setOpaque(true);
	}

	public ComboBoxActorRenderer getListCellRendererComponent(JList list,
			Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
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

		setIcon(MetalIconFactory.getTreeFolderIcon());
		Integer R = 0;
		for (Object str : (Object[]) value)
			R++;
		str1 = new String[R];
		R = 0;
		for (Object str : (Object[]) value) {
			str1[R] = str.toString();
			R++;
		}
		
		
		if (str1[4].equals("1")) setIcon(m2);
		else setIcon(m1);
		setText(str1[1]+" - "+str1[2]);

		return this;

	}
}