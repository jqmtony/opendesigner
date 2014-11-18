package org.openossad.ui.component;

import org.jdesktop.swingx.JXList;
import org.openossad.ui.base.GenericListModelHibernate;
import org.openossad.ui.panels.panelPropsOO2;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonGetActor extends JButton {


    private String result;

    public JButtonGetActor(Object transferedObject)  {

		this.setIcon(MetalIconFactory.getFileChooserUpFolderIcon());
		this.setBounds(550, 130, 20, 20);
		ButtonGetActorListener buttonGetActorListener =  new ButtonGetActorListener(transferedObject);
		this.addActionListener(buttonGetActorListener);
	}

    public String getID()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }


}
