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

public class RActorRenderer extends JLabel implements ListCellRenderer {
	String[] str1;
	
	Icon lector = ImageUtil.createImageIcon("/ui/images/05.png");
	Icon redactor = ImageUtil.createImageIcon("/ui/images/06.png");
	Icon aprobador = ImageUtil.createImageIcon("/ui/images/07.png");
	public RActorRenderer() {
		setOpaque(true);
	}

	public RActorRenderer getListCellRendererComponent(JList list,
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
        {
            R++;
        }
		str1 = new String[R];
		R = 0;
		for (Object str : (Object[]) value) {
			str1[R] = str.toString();
			R++;
		}
		
		if (str1.length>5) {
            if (str1[5].equals("3")) setIcon(lector);
            else if (str1[5].equals("1")) setIcon(redactor);
            else setIcon(aprobador);
        }  else {
            setIcon(aprobador);
        }

		setText(str1[1]+" - "+str1[2]);

		return this;

	}
}