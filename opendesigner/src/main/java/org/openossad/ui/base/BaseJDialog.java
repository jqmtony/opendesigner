package org.openossad.ui.base;

import org.openossad.data.dao.HibernateDAOFactory;
import org.openossad.util.GUItools;

import javax.swing.*;
import java.awt.*;


public class BaseJDialog extends JDialog {
	

	public GUItools GUI=new GUItools();
	public static HibernateDAOFactory DAOFactory = new HibernateDAOFactory();

	public BaseJDialog(Object parent) {
		super((Frame) parent, false);
        this.setSize(750, 510);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        DAOFactory.getCurrentSession().beginTransaction();
	}
    public BaseJDialog(Object parent,String title) {
		super((Frame) parent,false);
        new BaseJDialog(parent);

	}
	protected static HibernateDAOFactory getDAOFactory() {
		if (!DAOFactory.getCurrentSession().getTransaction().isActive())
        {
            DAOFactory.getCurrentSession().beginTransaction();
        }
		return DAOFactory;
	}
}
