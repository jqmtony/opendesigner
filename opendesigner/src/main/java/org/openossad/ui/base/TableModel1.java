package org.openossad.ui.base;


import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.openossad.data.dao.HibernateDAOFactory;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModel1 extends DefaultTableModel {

    private HibernateDAOFactory hibernateDAOFactory;

    public TableModel1() {
        hibernateDAOFactory= new HibernateDAOFactory();
    }

    private String connQuery = "";

    public void loadDataFromODBC() {

        if (!connQuery.toLowerCase().startsWith("select")&&!connQuery.contains("*")) {
            return;
        }

        final Session currentSession = hibernateDAOFactory.getCurrentSession();
        currentSession.beginTransaction();
        SQLQuery query = currentSession.createSQLQuery(connQuery);

        List result = query.list();

        // AttributeNames=new String[NumberOfColumns];

        for (String column : extractColumnNames(connQuery) ) {
            addColumn(column);
        }

        for (int i = 0; i < result.size(); i++) {
            final Object[] rowData = (Object[])result.get(i);
            addRow(rowData);
        }
    }

    public List<String> extractColumnNames(String str)
    {
        List<String> columns = new ArrayList<String>();
        str=str.substring(str.toLowerCase().indexOf(" "));
        str=str.substring(0,str.toLowerCase().indexOf(" from "));
        String[] splitStr = str.split(",");

        for (int x=0;x<splitStr.length;x++) {
            String trim = splitStr[x].trim();

            if  (trim.toLowerCase().contains("coalesce")) {
                trim = splitStr[x++].trim();
                trim=trim.substring(trim.indexOf("(")+1).trim();
            }
            trim=(trim.contains(" ")) ? trim.substring(trim.indexOf(" ")).trim() : trim ;
            columns.add(trim);
        }

        return columns;

    }

    public void setConnQuery(String connQuery)
    {
        this.connQuery = connQuery;
    }

    public String getConnQuery()
    {
        return connQuery;
    }
}
