package org.openossad;

import org.openossad.ui.base.PanelOpenBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 3/10/11
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
public class PanelOpenBaseTest
{
    PanelOpenBase sut;
    @BeforeMethod
    public void setUp() throws Exception
    {
         sut = getPanelOpenBase();
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
    }



    public PanelOpenBase getPanelOpenBase()
    {
        return new PanelOpenBase();
    }
}
