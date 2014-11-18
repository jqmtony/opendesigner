package org.openossad.ui.renderers;

import org.openossad.data.domain.Tblgraphs;
import org.openossad.data.domain.Tblprojects;
import org.openossad.util.ImageUtil;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * OpenDESIGNER-2.0.3
 * RgraphTreeRenderer.java,21/03/2009,
 * Copyright (c) 2009,  Xavi Hidalgo
 *
 * //////////////////////////////////////////////////////
 * Este código es propiedad de TricomZone, S.L.
 * Sujeto a la licencia LGPL.
 * 
 * //////////////////////////////////////////////////////
 *
 */

public class RgraphTreeRenderer extends DefaultTreeCellRenderer {
	String[] str1;
	Object obj = null;
	DefaultMutableTreeNode dmtn;
	String p="/ui/images/ooGraph/";
	Integer R = 0;
	
	Icon home = ImageUtil.createImageIcon(p+"45.png");
	Icon folder = ImageUtil.createImageIcon(p+"46.png");
	Icon folderOpen = ImageUtil.createImageIcon(p+"47.png");
	
	Icon mp1 = ImageUtil.createImageIcon(p+"mp1.png");
	Icon mp2 = ImageUtil.createImageIcon(p+"mp2.png");
	Icon mp3 = ImageUtil.createImageIcon(p+"mp3.png");
	Icon p1 = ImageUtil.createImageIcon(p+"p1.png");
	Icon p2 = ImageUtil.createImageIcon(p+"p2.png");
	Icon p3 = ImageUtil.createImageIcon(p+"p3.png");
	
	Icon mh1 = ImageUtil.createImageIcon(p+"mh1.png");
	Icon mh2 = ImageUtil.createImageIcon(p+"mh2.png");
	Icon mh3 = ImageUtil.createImageIcon(p+"mh3.png");
	Icon h1 = ImageUtil.createImageIcon(p+"h1.png");
	Icon h2 = ImageUtil.createImageIcon(p+"h2.png");
	Icon h3 = ImageUtil.createImageIcon(p+"h3.png");
	
	Icon doc = ImageUtil.createImageIcon(p+"22.png");
	
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.DefaultTreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		// TODO Auto-generated method stub
		super.selected=sel;
		super.hasFocus = hasFocus;
		//super.tree = tree;
		
		String  stringValue = tree.convertValueToText(value, sel, expanded, leaf, row, hasFocus);
		
		dmtn = (DefaultMutableTreeNode) value;
		
		obj = dmtn.getUserObject();
		
		if (dmtn.isRoot()) {
			setIcon(home);
			//this.setText(((Tblprojects)obj).getProjectName());
		} 
		else {
			
			if (obj instanceof Tblprojects) {
				
				if (expanded) {
		            setIcon(folderOpen);
		        } else {
		            setIcon(folder);
		        }
				this.setText(((Tblprojects)obj).getProjectName());
			}
			else {
				Integer opt=((Tblgraphs)obj).getGlevel();
				switch (opt) {
					case 1: setIcon(p1); break;
					case 2: setIcon(p2); break;
					case 3: setIcon(p3); break;
					case 4: setIcon(doc); break;
					case 5: setIcon(mp1); break;
					case 6: setIcon(mp2); break;
					case 7: setIcon(mp3); break;
					case 8: setIcon(h1); break;
					case 9: setIcon(h2); break;
					case 10: setIcon(h3); break;
					case 11: setIcon(mh1); break;
					case 12: setIcon(mh2); break;
					case 13: setIcon(mh3); break;
					default: setIcon(doc); 
				}
				this.setText(((Tblgraphs)obj).getGname());
			}
			
			
		}
		
		//System.out.println(R);
		return this;
		//return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,row, hasFocus);
	}


}
