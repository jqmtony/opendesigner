package org.openossad.util.init;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 6/11/11
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */
public class InitOpenossadDataHelperTest
{

    private InitOpenossadDataHelper sut;
    private String sep = File.separator;
    private String workingFolder;

    @BeforeClass
    public void setUpClass() throws Exception
    {
        sut = getSut();
        InitOpenossadData initOpenossadData = new InitOpenossadData();
        workingFolder = initOpenossadData.getWorkingFolder();
    }
    @Test
    public void testCreateInitialModels() throws Exception
    {
    }


    @DataProvider(name = "foldersToCheck")
    Object[][] getFoldersToCheck(){
        return new Object[][]{
                {sep + "TEST1",false},
                {sep + "TEST2",false},
                {workingFolder,true},
                {sep + "asdfasdf",false},
        };
    };


    @Test(dataProvider = "foldersToCheck")
    public void containValidFolderTest_returnsFalseIfWorkingDirectoryDoesntExists(String path,boolean expected)
    {
        boolean actual = sut.containValidFolder(path);
        System.out.println(">\n"+path+"\n<");
        Assert.assertEquals(actual, expected, "Check for valid folder doesn't work");
    }

    public InitOpenossadDataHelper getSut()
    {
        return new InitOpenossadDataHelper();
    }
}
