package org.openossad.util.init;

import org.openossad.util.helper.ReferencialFileOOSSAD;
import org.testng.annotations.*;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 26/10/11
 * Time: 13:05
 * To change this template use File | Settings | File Templates.
 */
public class InitOpenossadDataTest
{
    private InitOpenossadData sut;
    final String systemUserDir = System.getProperty("java.io.tmpdir");
    private String sep = File.separator;
    private String workingFolder;

    @BeforeClass
    public void setUpClass() throws Exception
    {
        sut = getSut();
        workingFolder = sut.getWorkingFolder();
        InitOpenossadDataHelper.createInitialModels(new File(workingFolder));
    }
    @AfterClass
    public void endClass() throws Exception
    {


    }

    private void deleteTestFolders()
    {
        for (Object[] folder: getFolders()) {
            String testFolder = (String) folder[0];
            testFolder = systemUserDir + testFolder;
            File fileTest = new File(testFolder);
            if (fileTest.exists())
            {
                ReferencialFileOOSSAD.deleteDirectory(fileTest);
            }
        }
    }



    private InitOpenossadData getSut()
    {
        return new InitOpenossadData();
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
        deleteTestFolders();
    }

    @Test
    public void initDatabase_insertInitRows() throws Exception
    {
        sut.insertInitRows();

    }
    @Test
    public void workingFolder_isValidPath() throws Exception
    {
        String actual = sut.getWorkingFolder();
        boolean isValidPath = new File(actual).exists();
        System.out.println("path for repo: " + actual);
        if (!isValidPath)
        {
            throw new Exception();
        }
    }

    @DataProvider(name = "folders")
    Object[][] getFolders(){
        return new Object[][]{
                {sep + "TEST1"},
                {sep + "TEST2"},
                {sep + "asdfasdf"},
        };
    };
    @Test(dataProvider = "folders")
    public void createRepository(String folder) throws Exception
    {

        folder = systemUserDir +folder;
        String actual = sut.createRepository(folder);
        boolean isValidPath = new File(actual).exists();
        System.out.println("path for repo: "+actual);
        if (!isValidPath)
        {
            throw new Exception();
        }
    }


}
