package org.openossad;


import com.mxgraph.examples.swing.editor.EditorMenuBar;
import com.mxgraph.util.mxResources;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 3/10/11
 * Time: 12:43
 * To change this template use File | Settings | File Templates.
 */
public class ooEditorMenuBar extends EditorMenuBar
{

    public ooEditorMenuBar(final BasicGraphEditorOO editor)
    {
        super(editor);
        JMenu menuFile = this.getMenu(0);
        menuFile.removeAll();

        remove(this.getMenu(4));
        remove(this.getMenu(5));
        remove(this.getMenu(6));

        menuFile.add(editor.bind(mxResources.get("new"), new ooEditorActions.ooNewAction(), "/images/new.gif"));
        menuFile.add(editor.bind(mxResources.get("open"), new ooEditorActions.ooOpenAction(), "/images/open.gif"));
        menuFile.add(editor.bind(mxResources.get("save"), new ooEditorActions.ooSaveAction(false), "/images/save.gif"));
        menuFile.add(editor.bind(mxResources.get("saveAs"), new ooEditorActions.ooSaveAction(true),"/images/saveas.gif"));
        menuFile.addSeparator();
        menuFile.add(editor.bind("Properties", new ooEditorActions.ooPropertiesAction(), "/images/save.gif"));
        menuFile.addSeparator();
        menuFile.add(editor.bind("Export PDF", new ooEditorActions.ooExportPDFAction(), "/images/saveas.gif"));
        menuFile.addSeparator();

        JMenu menuOo = add(new JMenu("Opendesigner"));
        menuOo.add(editor.bind("Projects / Folders", new ooEditorActions.ooFolderAction()));
        menuOo.add(editor.bind("Preferences",new ooEditorActions.ooOptionsDialog()));
        menuOo.add(editor.bind("Explorar referencial",new ooEditorActions.ooExploreReferentiel()));
        menuOo.addSeparator();

        JMenu submenu = (JMenu) menuOo.add(new JMenu("Redacción"));
        submenu.add(editor.bind("Crear / alinear roles", new ooEditorActions.OpenOSSADalignRolesAction()));
        submenu.addSeparator();

        menuOo.add(submenu);

        this.add(menuOo);

        // Creates the help menu
        JMenu helpMenu = add(new JMenu(mxResources.get("help")));
        JMenuItem item = helpMenu.add(new JMenuItem(mxResources.get("aboutGraphEditor")));
        item.addActionListener(new ActionListener()
        {
            /*
                * (non-Javadoc)
                * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
                */
            public void actionPerformed(ActionEvent e)
            {
                editor.about();
            }
        });
        this.add(helpMenu);

    }
}
