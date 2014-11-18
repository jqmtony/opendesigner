package org.openossad.util.helper;

import org.openossad.data.domain.Tblgraphs;
import org.openossad.data.domain.Tblprojects;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 12/10/11
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public class ReferencialOOSSADTest
{
    private ReferencialOOSSAD sut;
    private boolean isTest= true;

    @BeforeTest
    public void SetUp(){
        sut = getSut();
    }

    @AfterTest
    public void goodBye(){
        DAOOOSSAD.deleteTblgraphsByGName("NAME");
    }



    private ReferencialOOSSAD getSut()
    {
        return new ReferencialOOSSAD();
    }


    @DataProvider(name = "a")
    Object[][] getDataA(){
        return new Object[][]{
                {"ops000-000000001","/ops000/000000001.ood","dummyfolder"},
                {"ops000-000000001",System.getProperty("user.dir")+"/ops000/000000001.ood",System.getProperty("user.dir")},
                {"we-000000001","/we/000000001.ood","dummyfolder"}

        };
    }
    @Test(dataProvider = "a")
    public void testGetLastIDFile(String graphId, String expected, String dummyFolder) throws Exception
    {
        String actual=sut.getFilePathFromGId(graphId, dummyFolder);
        Assert.assertTrue(actual.contains(expected));
    }

    @Test
    public void testGetLastIDIndic() throws Exception
    {
    }

    @Test
    public void testGetLastIDEntity() throws Exception
    {
    }

    @Test
    public void testGetLastIDAccount() throws Exception
    {
    }

    @DataProvider(name = "counters")
    Object[][] getCounters(){
        return new Object[][]{
                {"GID","000000001"},
        };
    }
    @Test (dataProvider = "counters")
    public void testGetCounter(String idCounter, String expected) throws Exception
    {
        String actual = sut.getCounter(idCounter);

        Assert.assertEquals(actual,expected);

    }

    @Test
    public void testGetParsedModel() throws Exception
    {
    }

    @Test
    public void testGetParsedModelDoc() throws Exception
    {
    }

    @Test
    public void testCreateGraphInREP() throws Exception
    {
    }

    @Test
    public void testGetData4Marco() throws Exception
    {
    }

    @Test
    public void testGenerarVersion() throws Exception
    {
    }

    @Test
    public void testChargeComments() throws Exception
    {
    }


    @DataProvider(name = "myDataProvider2")
    public Object[][] resourceFiles() {
        return new Object[][]{
                {   getDummyTblgraphs(),"NAME","GREF_001",getDummyTblprojects()},
        };
    }

    private Tblprojects getDummyTblprojects()
    {
        Tblprojects dummy = new Tblprojects();
        dummy.setProjectId("idToTest-0000");
        dummy.setProjectName("test");
        dummy.setParentId("idToTestParentId-000");
        return dummy;

    }

    private Tblgraphs getDummyTblgraphs()
    {
        Tblgraphs dummy = new Tblgraphs();
        dummy.setGname("asdfasdfasdfasdfasdf");
        dummy.setGid("OPS000-00000000000");
        dummy.setGlevel(1);
        return dummy;
    }


    @Test(groups = "unit", dataProvider = "myDataProvider2")
    public void testCreateNewOOGraph(Tblgraphs model, String name, String ref, Tblprojects project) throws Exception
    {
        Tblgraphs actual = sut.createNewOOGraph(model, name, ref, project, true, isTest);
        junit.framework.Assert.assertNotNull("is not a valid tblgraphs object",actual);
        DAOOOSSAD.deleteTblgraphsByGName(name);
    }

    @Test(groups = "unit")
    public void getCounter_test_mustIncrement(){
        String actual=sut.getCounter("GID");
        String actualbis=sut.getCounter("GID");
        Assert.assertNotSame(actual,actualbis);
    }

    @DataProvider(name = "levels")
    Object[][] getLevels(){
        return new Object[][]{
                {1, "TRZ000".concat(File.separator).concat("000000001.odd")},
                {10,"TRZ000".concat(File.separator).concat("000000006.odd")},
                {11,"TRZ000".concat(File.separator).concat("000000007.odd")},
                {12,"TRZ000".concat(File.separator).concat("000000008.odd")}
        };
    }

    @Test(dataProvider = "levels")
    public void getBasicModelFromLevel_test(int level, String expected){
        String actual = sut.getBasicModelFromLevel(level,"dummyWorkFolder");
        Assert.assertTrue(actual.contains(expected),"Model path is wrongly formatted");
    }

}
