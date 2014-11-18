package org.openossad.util.helper;

import com.mxgraph.model.mxCell;
import junit.framework.Assert;
import org.openossad.CustomOpenDESIGNERGraphComponent;
import org.openossad.GraphTestHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 23/10/11
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public class ReferencialGraphOOSSADTest
{
    private ReferencialGraphOOSSAD sut;
    private mxCell cellDummy;

    @BeforeMethod
    public void setUp() throws Exception
    {
        sut=getSut();
        cellDummy=mock(mxCell.class);

    }

    private ReferencialGraphOOSSAD getSut()
    {
        return new ReferencialGraphOOSSAD();
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testGetCellComment() throws Exception
    {
        sut.getCellComment("OPS000-000000001","1");
    }



    @DataProvider(name = "cellsEditables")
    Object[][] getCellsEditables(){
        return new Object[][]{
                {"sepRol",false},{"101",true},{"marc1",false},{"",true},{null,false},
        };
    }
    @Test(dataProvider = "cellsEditables")
    public void isCellEditable_mustTreatOOCells_test(String style, boolean expected) throws Exception
    {
        doReturn(style).when(cellDummy).getStyle();
        boolean actual = sut.isCellEditable(cellDummy);
        Assert.assertEquals("",expected,actual);
    }

    @Test
    public void getCells_test() throws Exception
    {
        String workingFolder = System.getProperty("user.dir");
        GraphTestHelper.loadInPathTestFiles(workingFolder);

        String graphFilePath = (String) GraphTestHelper.getProviderGraphs()[0][0];
        CustomOpenDESIGNERGraphComponent dummyGraphComponent = GraphTestHelper.getDummyGraphComponent(graphFilePath);

        sut.getCells(dummyGraphComponent,"ops000-000000001.odd");

        GraphTestHelper.deleteInPathTempFiles(workingFolder);
    }






}
