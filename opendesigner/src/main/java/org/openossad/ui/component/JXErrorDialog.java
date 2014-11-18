package org.openossad.ui.component;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: xavi
 * Date: 2/10/11
 * Time: 11:52
 * To change this template use File | Settings | File Templates.
 */
public class JXErrorDialog
{
    public static void showDialog(Object parent, String s, Exception e)
    {
        // create and configure a text area - fill it with exception text.
		final JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Sans-Serif", Font.PLAIN, 10));
		textArea.setEditable(false);

		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		textArea.setText(writer.toString());

		// stuff it in a scrollpane with a controlled size.
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(350, 150));

		// pass the scrollpane to the joptionpane.
		JOptionPane.showMessageDialog(textArea, scrollPane, "An Error Has Occurred", JOptionPane.ERROR_MESSAGE);
    }
}
