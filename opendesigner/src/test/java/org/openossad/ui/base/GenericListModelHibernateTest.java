package org.openossad.ui.base;

import org.openossad.data.domain.OoCells;
import org.openossad.util.helper.DAOOOSSADTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 18/11/11
 * Time: 12:20
 * To change this template use File | Settings | File Templates.
 */
public class GenericListModelHibernateTest {
    private GenericListModelHibernate sut;

    @BeforeMethod
    public void setUp() throws Exception {
        sut = getSut();
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testLoadDataFromODBC() throws Exception {

        sut.loadDataFromODBC();
    }

    @DataProvider(name = "objs")
    Object[][] getObjs(){
        return new Object[][]{
                {DAOOOSSADTest.getDummyOoCellsList(),new OoCells("dummy","100")}
        };
    }
    @Test(dataProvider = "objs")
    public void testLoadDataFromDAO(List<OoCells> objectList, Object expected) throws Exception {

        sut.loadDataFromDAO( objectList );
        Object actual = sut.getElementAt(0);
        Assert.assertEquals(actual.getClass(),expected.getClass());

    }



    public GenericListModelHibernate getSut() {
        return new GenericListModelHibernate();
    }

}
