package org.openossad.ui.base;

import org.openossad.ui.renderers.ComboBoxActorRenderer;
import org.openossad.ui.renderers.ComboBoxPropertyRenderer;
import org.openossad.util.GUItools;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 18/10/11
 * Time: 13:57
 * To change this template use File | Settings | File Templates.
 */

public class PanelPropsBase extends JDesktopPane
{

    public GUItools gui = new GUItools();


	public GenericListModelHibernate genericListModelHibernate_1 = new GenericListModelHibernate();
	public GenericListModelHibernate genericListModelHibernate_2 = new GenericListModelHibernate();
	public ComboBoxActorRenderer comboBoxActorRenderer = new ComboBoxActorRenderer();
	public ComboBoxPropertyRenderer comboBoxPropertyRenderer = new ComboBoxPropertyRenderer();

    public PanelPropsBase()
    {
        super();
        initComponents();
    }

    private void initComponents()
    {

        //common components
        add(gui.getDivider(this,427));
        add(gui.getJButtonSaveProps(600 - 120, 440, 103, 30));
        add(gui.getJButtonCancel(600 - 240, 440, 103, 30));
    }



}
