package org.openossad.util;

import junit.framework.Assert;
import org.openossad.data.domain.Tblprojects;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 3/10/11
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public class GUItoolsTest
{
    private GUItools sut;

    @BeforeMethod
    public void setUp() throws Exception
    {
        sut = getGUItools();
    }

    private GUItools getGUItools()
    {
        return new GUItools();
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
    }

    @Test(enabled = false) //thows a gui dialog box :$
    public void testLoadFolderCombo_returnsAtLeastFirstElementTblproject() throws Exception
    {
        JComboBox dummyJComboBox = new JComboBox();
        sut.loadFolderCombo(dummyJComboBox);
        Tblprojects actual = (Tblprojects) dummyJComboBox.getItemAt(0);
        final String expected = "openossad";
        Assert.assertEquals(actual.getProjectName(), expected);



    }
}
