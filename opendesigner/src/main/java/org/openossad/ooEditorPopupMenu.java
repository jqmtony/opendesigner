package org.openossad;

import com.mxgraph.examples.swing.editor.EditorActions;
import com.mxgraph.examples.swing.editor.EditorMenuBar;
import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxResources;
import org.openossad.util.helper.ReferencialGraphOOSSAD;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 23/10/11
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class ooEditorPopupMenu extends JPopupMenu
{
    public ooEditorPopupMenu(BasicGraphEditorOO editor) {
        boolean selected = !editor.getGraphComponent().getGraph().isSelectionEmpty();


        String tipCell= ReferencialGraphOOSSAD.getTipCell(editor);
        Boolean isCellToLink=ReferencialGraphOOSSAD.getisCellToLink(editor);

        if (selected) {
            add(editor.bind(mxResources.get("comments"), new ooEditorActions.OpenOSSADdoComment(),"/ui/images/buttons/42.png"));
            addSeparator();
        }

        if (isCellToLink) {
            add(editor.bind(mxResources.get("link"), new ooEditorActions.OpenOSSADLinkCellAction(),"/ui/images/buttons/44.png"));

            add(editor.bind(mxResources.get("deletelink"),new ooEditorActions.OpenOSSADdeleteLinkCellAction(),"/ui/images/buttons/50.png"));

        }

        else {
            add(editor.bind(mxResources.get("createlink"),new ooEditorActions.OpenOSSADcreateLinkCellAction(),"/ui/images/buttons/21.png"));
        }

//
//		if (tipCell.equals("10") || tipCell.equals("6") ||tipCell.equals("9")) {
//			addSeparator();
//			add(editor.bind(mxResources.get("proces2_1"),new OpenOSSADchangeTipToAction("10"),"/ui/images/undo.gif"));
//			add(editor.bind(mxResources.get("proces2_2"),new OpenOSSADchangeTipToAction("6"),"/ui/images/undo.gif"));
//			add(editor.bind(mxResources.get("proces2_3"),new OpenOSSADchangeTipToAction("9"),"/ui/images/undo.gif"));
//		}
//
//		if (tipCell.equals("333") || tipCell.equals("16") || tipCell.equals("3")) {
//			addSeparator();
//			add(editor.bind(mxResources.get("proces2_4"),new OpenOSSADchangeTipToAction("333"),"/ui/images/undo.gif"));
//			add(editor.bind(mxResources.get("proces2_5"),new OpenOSSADchangeTipToAction("16"),"/ui/images/undo.gif"));
//			add(editor.bind(mxResources.get("proces2_6"),new OpenOSSADchangeTipToAction("3"),"/ui/images/undo.gif"));
//		}
//
//		if (tipCell.equals("112") || tipCell.equals("113") || tipCell.equals("114")) {
//			addSeparator();
//			add(editor.bind(mxResources.get("proces1_2"),new OpenOSSADchangeTipToAction("112"),"/ui/images/undo.gif"));
//			add(editor.bind(mxResources.get("proces1_3"),new OpenOSSADchangeTipToAction("114"),"/ui/images/undo.gif"));
//			add(editor.bind(mxResources.get("proces1_4"),new OpenOSSADchangeTipToAction("113"),"/ui/images/undo.gif"));
//		}




        addSeparator();

        add(editor.bind(mxResources.get("undo"), new EditorActions.HistoryAction(true),"/images/undo.gif"));

        addSeparator();

        add(editor.bind(mxResources.get("cut"), TransferHandler.getCutAction(),"/images/cut.gif")).setEnabled(selected);
        add(editor.bind(mxResources.get("copy"), TransferHandler.getCopyAction(),"/images/copy.gif")).setEnabled(selected);
        add(editor.bind(mxResources.get("paste"), TransferHandler.getPasteAction(),"/images/paste.gif"));

        addSeparator();

        add(editor.bind(mxResources.get("delete"), mxGraphActions.getDeleteAction(),"/images/delete.gif")).setEnabled(selected);

        addSeparator();

        // Creates the format menu
        JMenu menu = (JMenu) add(new JMenu(mxResources.get("format")));

        EditorMenuBar.populateFormatMenu(menu, editor);

        // Creates the shape menu
        menu = (JMenu) add(new JMenu(mxResources.get("shape")));

        EditorMenuBar.populateShapeMenu(menu, editor);

        addSeparator();

        add(editor.bind(mxResources.get("edit"), mxGraphActions.getEditAction())).setEnabled(selected);

        addSeparator();

        add(editor.bind(mxResources.get("selectVertices"), mxGraphActions.getSelectVerticesAction()));
        add(editor.bind(mxResources.get("selectEdges"), mxGraphActions.getSelectEdgesAction()));

        addSeparator();

        add(editor.bind(mxResources.get("selectAll"), mxGraphActions.getSelectAllAction()));
    }

}
