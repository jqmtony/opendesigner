package org.openossad.util.openoffice;

import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 9/11/11
 * Time: 12:31
 * To change this template use File | Settings | File Templates.
 */
public class OpenOfficeConverter_Test
{
    private OpenOfficeConverter sut;
    private String strHindiHTML  = "Hello hi there<h1>heading</h1>";

    @BeforeMethod
    public void setUp() throws Exception
    {
        sut = getSut();
    }

    @Test(enabled = false)
    public void testConvertFromHTMLToPDF() throws Exception
    {
        String tempFolder = System.getProperty("java.io.tmpdir");
        FileOutputStream out = new FileOutputStream(tempFolder.concat(File.separator).concat("testHTMLToPDFConverter.pdf"));
        sut.openConnection();
        sut.convertFromHTMLToPDF(strHindiHTML, out);
        sut.closeConnection();
    }

    @Test(enabled = false)
    public void testOpenConnection() throws Exception
    {
        OpenOfficeConnection actual = sut.openConnection();
        Assert.assertEquals(actual.isConnected(),true);

    }


    @Test(enabled = false)
    public void testCloseConnection() throws Exception
    {
        sut.openConnection();
        OpenOfficeConnection actual = sut.closeConnection();
        Assert.assertEquals(actual.isConnected(),false);
    }

    public OpenOfficeConverter getSut()
    {
        return new OpenOfficeConverter();
    }
}
