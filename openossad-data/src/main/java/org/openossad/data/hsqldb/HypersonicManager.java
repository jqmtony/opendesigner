package org.openossad.data.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 7/11/11
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
public class HypersonicManager

{
//    private String instanceName = "jdbc:hsqldb:data/openossad";
    private String instanceName = "jdbc:hsqldb:file:data/openossad";

    public Connection startHypersonicServer() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        Class.forName("org.hsqldb.jdbcDriver").newInstance();
        Connection connection = DriverManager.getConnection(instanceName, "sa", "");

//        Statement stmt = connection.createStatement();
//        stmt.execute("CREATE SCHEMA openossad");
//        System.out.println(connection.getCatalog());

        return connection;
    }

    public Connection stopHypersonicServer(Connection connection) throws SQLException
    {
        Statement stmt = connection.createStatement();
        stmt.execute("SHUTDOWN");
        connection.close();
        return connection;
    }
}
