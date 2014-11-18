package org.openossad.ui.base;

import junit.framework.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 3/10/11
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */
public class TableModel1Test
{

    TableModel1 sut;

    @BeforeMethod
    public void setUp() throws Exception
    {
        sut = getTableModel1();
    }

    private TableModel1 getTableModel1()
    {
        return new TableModel1();
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
    }
    @Test
    public void testLoadDataFromODBC() throws Exception
    {
        sut.setConnQuery("SELECT Gid,GName,GLevel FROM tblgraphs");
        sut.loadDataFromODBC();
        Assert.assertTrue(true);
    }


    @DataProvider(name = "myDataProvider")
    public Object[][] createEntry() {
        return new Object[][]{
                {   "SELECT field1,field2,field3 FROM tblgraphs","field1^field2^field3^"  },
                {   "SELECT field0 field1,field2,field3 FROM tblgraphs","field1^field2^field3^"  },
                {   "SELECT field1,field2,COALESCE(field3,bla) FROM tblgraphs","field1^field2^field3^"  },
        };
    }
    @Test(groups = "unit", dataProvider = "myDataProvider",enabled = true)
    public void testExtractColumnNames(String sqlQuery,String expected) throws Exception
    {
        List<String> columns=sut.extractColumnNames(sqlQuery);
        StringBuilder builder = new StringBuilder();
        for (String column : columns ) {
            builder.append(column).append("^");
        }
        Assert.assertEquals(expected,builder.toString());
    }


}
