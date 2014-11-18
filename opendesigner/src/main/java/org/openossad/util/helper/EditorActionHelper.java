package org.openossad.util.helper;

import org.openossad.BasicGraphEditorOO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 20/10/11
 * Time: 11:33
 * To change this template use File | Settings | File Templates.
 */
public class EditorActionHelper
{
    public static final BasicGraphEditorOO getEditor(ActionEvent e)
    {
        if (e.getSource() instanceof Component)
        {
            Component component = (Component) e.getSource();

            while (component != null && !(component instanceof BasicGraphEditorOO))
            {
                component = component.getParent();
            }

            return (BasicGraphEditorOO) component;
        }

        return null;
    }
        public static String getCurrentGidWithGUI(ActionEvent e)
    {
        BasicGraphEditorOO editor = getEditor(e);
        Frame frame = (Frame) SwingUtilities.windowForComponent(editor);
        String currentGId = getCurrentGId(e);
        if (currentGId.equals(""))
        {
            GetGraph getGraph = new GetGraph(frame);
            currentGId=getGraph.getGidx();
        }
        return currentGId;
    }

    public static String getCurrentGId(ActionEvent e)
    {
        BasicGraphEditorOO editor = getEditor(e);
        String currentGId = editor.getCurrentGID();
        return currentGId = (currentGId == null) ? "" : currentGId;
    }


}
