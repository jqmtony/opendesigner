package org.openossad.util;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 23/10/11
 * Time: 16:36
 * To change this template use File | Settings | File Templates.
 */
public class PromptUtil
{
    private static String title = "OpenDESIGNER";
    private static Icon icon=ImageUtil.createImageIcon("/ui/images/buttons/42.png");
    private static String message = "Introduzca un comentario";

    public static String promptCellComment(Component component, String initialValue)
    {
        final JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Sans-Serif", Font.PLAIN, 10));
		textArea.setEditable(true);
		textArea.setText(initialValue);

        JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(350, 150));

        return (String) JOptionPane.showInputDialog
                (textArea, message, title, JOptionPane.PLAIN_MESSAGE, icon, null, initialValue);
    }

    public static String promptCellCommentExtended(Component component, String initialValue)
    {
        return (String) JOptionPane.showInputDialog
                (component, message, title, JOptionPane.PLAIN_MESSAGE, icon, null, initialValue);
    }

}
