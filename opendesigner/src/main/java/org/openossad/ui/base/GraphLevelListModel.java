package org.openossad.ui.base;

import org.openossad.data.dao.HibernateDAOFactory;
import org.openossad.data.domain.Tblgraphs;

import javax.swing.*;
import java.util.List;

public class GraphLevelListModel extends DefaultListModel {

    private HibernateDAOFactory hibernateDAOFactory;
    private int gLevel;

    public GraphLevelListModel(int gLevel)
    {
        hibernateDAOFactory= new HibernateDAOFactory();
        this.gLevel=gLevel+4;
        loadList();
    }

    public void loadList() {
        hibernateDAOFactory.getCurrentSession().beginTransaction();
        final List<Tblgraphs> tblgraphsList = hibernateDAOFactory.getTblgraphsDAO().getModelsProcess();
        for (Tblgraphs tblgraps : tblgraphsList) {
            if (gLevel==tblgraps.getGlevel())
            {
                addElement(tblgraps);
            }
        }
	}
}