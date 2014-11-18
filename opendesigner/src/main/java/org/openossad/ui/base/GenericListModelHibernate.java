package org.openossad.ui.base;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.openossad.data.dao.HibernateDAOFactory;

import javax.swing.*;
import java.util.List;

public class GenericListModelHibernate extends DefaultListModel {

    private HibernateDAOFactory hibernateDAOFactory;

    public GenericListModelHibernate() {
        hibernateDAOFactory= new HibernateDAOFactory();
    }

    public String connQuery = "";

    public void loadDataFromODBC()
    {
        if(!connQuery.toLowerCase().trim().startsWith("select"))
        {
            return;
        }
        final Session currentSession = hibernateDAOFactory.getCurrentSession();
        currentSession.beginTransaction();
        SQLQuery query = currentSession.createSQLQuery(connQuery);
        List result = query.list();
        Object rowData = null;
        for(int i = 0; i < result.size(); i++)
        {
            rowData = (Object) result.get(i);
        }
//        if(rowData.length > 1) addElement(rowData);
//        else addElement(rowData[0]);
        if (rowData != null )
        {
            addElement(rowData);
        }

    }


    public void loadDataFromDAO(Object object) {

        List<Object> objectList = (List<Object>) object;
        for (Object obj : objectList ) {
            addElement(obj);
        }
    }
}