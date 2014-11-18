package org.openossad.data.hsqldb;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 7/11/11
 * Time: 15:03
 * To change this template use File | Settings | File Templates.
 */
public class HypersonicManagerTest
{
    private HypersonicManager sut;
    private Connection connection;

    @BeforeMethod
    public void setUp() throws Exception
    {
        sut= getSut();
        connection = sut.startHypersonicServer();
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testStartHypersonicServer() throws Exception
    {
        Assert.assertTrue(!connection.isClosed());

    }

    @Test(enabled = true)
    public void testStopHypersonicServer() throws Exception
    {
        Connection actual = sut.stopHypersonicServer(connection);
        Assert.assertTrue(actual.isClosed());

    }


    public HypersonicManager getSut()
    {
        return new HypersonicManager();
    }
}
