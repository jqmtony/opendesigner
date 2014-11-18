package org.openossad.ui.renderers;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class IconListRenderer extends DefaultListCellRenderer {
	private Map<Object, Icon> icons = null;

	public IconListRenderer(Map<Object, Icon> icons) {

		this.icons = icons;

	}

	@Override
	public Component getListCellRendererComponent(

	JList list, Object value, int index,

	boolean isSelected, boolean cellHasFocus) {

		// Get the comboBoxPropertyRenderer component from parent class

		JLabel label =

		(JLabel) super.getListCellRendererComponent(list,

		value, index, isSelected, cellHasFocus);

		// Get icon to use for the list item value

		Icon icon = icons.get(value);

		// Set icon to display for value

		label.setIcon(icon);

		return label;

	}

	/**
	 * 44.
	 * 
	 * @param args
	 *            45.
	 */

	public static void main(String[] args) {

		// setup mappings for which icon to use for each value

		Map<Object, Icon> icons = new HashMap<Object, Icon>();

		icons.put("details",

		MetalIconFactory.getFileChooserDetailViewIcon());
		icons.put("folder",

		MetalIconFactory.getTreeFolderIcon());

		icons.put("computer",

		MetalIconFactory.getTreeComputerIcon());

		JFrame frame = new JFrame("Icon List");

		frame.setDefaultCloseOperation(

		JFrame.DISPOSE_ON_CLOSE);

		// create a list with some test data

		JComboBox combo = new JComboBox(

		new Object[]{

		"details", "computer", "folder", "computer"});

		// create a cell comboBoxPropertyRenderer to add the appropriate icon

		combo.setRenderer(new IconListRenderer(icons));

		frame.add(combo);

		frame.pack();

		frame.setVisible(true);

	}

}