package org.openossad.ui.renderers;

import org.openossad.util.ImageUtil;

import javax.swing.*;

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

public class ComboBoxPropertyRenderer extends JLabel implements ListCellRenderer {
	String[] str1;
	
	Icon m1 = ImageUtil.createImageIcon("/ui/images/001_36.png");
	
	public ComboBoxPropertyRenderer() {
		setOpaque(true);
	}

	public ComboBoxPropertyRenderer getListCellRendererComponent(JList list,
			Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		setIcon(m1);
		setText(value.toString());

		return this;

	}
}