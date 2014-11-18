package org.openossad.util.helper;

import org.openossad.data.domain.Tblprojects;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 27/10/11
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */
public class NodeTreeHelperTest
{
    private NodeTreeHelper sut;
    private Tblprojects[] tblprojectsArray;
    private List<Tblprojects> tblprojectsList;

    @BeforeMethod
    public void setUp() throws Exception
    {
        sut = getSut();
        tblprojectsArray = getDummyTblprojectsArray();
        tblprojectsList =  getDummyTblprojectsList();
    }

    private List<Tblprojects> getDummyTblprojectsList()
    {
        tblprojectsList = new ArrayList<Tblprojects>();
        for (Tblprojects tblprojects : tblprojectsArray) {
            tblprojectsList.add(tblprojects);
        }
        return tblprojectsList;
    }

    @AfterMethod
    public void tearDown() throws Exception
    {

    }

    private NodeTreeHelper getSut()
    {
        return new NodeTreeHelper();
    }

    @Test
    public void testMakeSampleTree() throws Exception
    {
    }

    @Test(enabled = false)
    public void testMakeSampleTreeLegacy() throws Exception
    {
        TreeNode rootNode = sut.makeSampleTreeLegacy(tblprojectsList);
        int actual = rootNode.getChildCount();
        int expected = 9;
        Assert.assertEquals(actual, expected, "Number of childs doesn't match");

    }
    @Test(enabled = false) // only to check production tables
    public void testMakeSampleTreeLegacy_withProductionTblgraps() throws Exception
    {
        List<Tblprojects> tblprojectsListActual = DAOOOSSAD.getAllTblprojects();
        TreeNode rootNode = sut.makeSampleTreeLegacy(tblprojectsListActual);
        Object userObject = ((DefaultMutableTreeNode) rootNode).getUserObject();
        Tblprojects tblprojects = (Tblprojects) userObject;
        String rootParentId = "000000-000000000";
        Assert.assertEquals(tblprojects.getParentId(), rootParentId,"Root node parent is not main openossad folder");

    }



    @DataProvider(name = "projects_1")
    Object[][] getProjects1(){
        return new Object[][] {
                {"000000-000000000"},
                {"000000-000000123"},
                {""}
    };
}

    @Test
    public void testOrderByFolderLevel() throws Exception
    {
        List<Tblprojects> actual = sut.orderByFldLevel(tblprojectsList, 5);
        Assert.assertEquals(tblprojectsList.size(),actual.size(),"Orderer function fail: some lost objects");
        Integer fldLevel = actual.get(0).getFldLevel();
        Integer expectedFirstLevel = 1;
        Assert.assertEquals(fldLevel,expectedFirstLevel,"Orderer function fail: first object is not the main Folder");
    }

    private Tblprojects[] getDummyTblprojectsArray()
    {

        Tblprojects[] tblprojectsArray = new Tblprojects[8];
        final Date time = Calendar.getInstance().getTime();
        tblprojectsArray[0] = new Tblprojects("OPS000-000000001","OpenDESIGNER",true,"000000-000000000",time,"","OpenDESIGNER",false,"TRZ-7","Main Project","Quality system",1,1);
        tblprojectsArray[1] = new Tblprojects("OPS000-000000002","OpenDESIGNER",true,"OPS000-000000001",time,"","OpenDESIGNER",false,"TRZ-7","Main Project","Quality system",2,2);
        tblprojectsArray[2] = new Tblprojects("OPS000-000000003","OpenDESIGNER",true,"OPS000-000000002",time,"","OpenDESIGNER",false,"TRZ-7","Main Project","Quality system",3,3);
        tblprojectsArray[3] = new Tblprojects("OPS000-000000004","OpenDESIGNER",true,"OPS000-000000002",time,"","OpenDESIGNER",false,"TRZ-7","Main Project","Quality system",3,4);
        tblprojectsArray[4] = new Tblprojects("OPS000-000000005","OpenDESIGNER",true,"OPS000-000000001",time,"","OpenDESIGNER",false,"TRZ-7","Main Project","Quality system",2,5);
        tblprojectsArray[5] = new Tblprojects("OPS000-000000006","OpenDESIGNER",true,"OPS000-000000005",time,"","OpenDESIGNER",false,"TRZ-7","Main Project","Quality system",3,6);
        tblprojectsArray[6] = new Tblprojects("OPS000-000000007","OpenDESIGNER",true,"OPS000-000000006",time,"","OpenDESIGNER",false,"TRZ-7","Main Project","Quality system",4,7);
        tblprojectsArray[7] = new Tblprojects("OPS000-000000008","OpenDESIGNER",true,"OPS000-000000007",time,"","OpenDESIGNER",false,"TRZ-7","Main Project","Quality system",5,8);

        return tblprojectsArray;

    }
}
