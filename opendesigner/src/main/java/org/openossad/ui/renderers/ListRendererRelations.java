/**
 * 
 */
package org.openossad.ui.renderers;

import org.openossad.util.ImageUtil;
import org.openossad.util.ooString;

import javax.swing.*;
import java.awt.*;

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

public class ListRendererRelations extends JPanel implements ListCellRenderer {
	String[] str1;
	
	public Icon medi = ImageUtil.createImageIcon("/ui/images/gear.png");
	public Icon graf = ImageUtil.createImageIcon("/ui/images/n1_16.png");
	public Icon role = ImageUtil.createImageIcon("/ui/images/28.png");
	public Icon indi = ImageUtil.createImageIcon("/ui/images/37.png");
	public Icon docu = ImageUtil.createImageIcon("/ui/images/22.png");
	
	JLabel left, middle, right;
	
	public ListRendererRelations() {
		setLayout(new GridLayout(1, 4));
		left = new JLabel();middle = new JLabel();right = new JLabel();
		left.setOpaque(true);middle.setOpaque(true);right.setOpaque(true);
		add(left);add(middle);add(right);
		setOpaque(true);
	}
	public ListRendererRelations getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus){
	
		if (isSelected) {
			left.setBackground(list.getSelectionBackground());
			left.setForeground(list.getSelectionForeground());
			middle.setBackground(list.getSelectionBackground());
			middle.setForeground(list.getSelectionForeground());
			right.setBackground(list.getSelectionBackground());
			right.setForeground(list.getSelectionForeground());
		} else {
			left.setBackground(list.getBackground());
			left.setForeground(list.getForeground());
			middle.setBackground(list.getBackground());
			middle.setForeground(list.getForeground());
			right.setBackground(list.getBackground());
			right.setForeground(list.getForeground());
			
		}
		
		Integer R; 
		R = 0;for (Object str : (Object[]) value) R++;
		str1 = new String[R];
		R = 0;for (Object str : (Object[]) value) {str1[R] = str.toString();R++;}
		
		Boolean go = false;
		String gr= null;
		String [] temp=null;
		
		gr="333,3,5,16,132,133,112,113,114";
		temp = gr.split(",");
		if (!go) for (int x=0;x<temp.length;x++) {
			if (temp[x].equals(str1[2])) {
				left.setIcon(graf);go=true;
				
			}
		}
		
		gr="8,144";
		temp = gr.split(",");
		if (!go) for (int x=0;x<temp.length;x++) {
			if (temp[x].equals(str1[2])) {left.setIcon(docu);go=true;}
		}
		
		gr="6,9,10,111,131,804,805,806,821,822,823";
		temp = gr.split(",");
		if (!go) for (int x=0;x<temp.length;x++) {
			if (temp[x].equals(str1[2])) {left.setIcon(role);go=true;}
		}
		
		gr="137,";
		temp = gr.split(",");
		if (!go) for (int x=0;x<temp.length;x++) {
			if (temp[x].equals(str1[2])) {left.setIcon(indi);go=true;}
		}
		
		gr="7,14,145";
		temp = gr.split(",");
		if (!go) for (int x=0;x<temp.length;x++) {
			if (temp[x].equals(str1[2])) {left.setIcon(medi);go=true;}
		}
		
		left.setText(str1[3]);middle.setText(str1[5]);
		String res="";
		if (str1[1].length()>3) {
			res= ooString.p(str1[1], "^", 2);
			res=ooString.p(res,":",2);
		}
		
		
		right.setText(res);
		
		
		
		
		setEnabled(list.isEnabled());
		setFont(list.getFont());
		return this;

	}
}
