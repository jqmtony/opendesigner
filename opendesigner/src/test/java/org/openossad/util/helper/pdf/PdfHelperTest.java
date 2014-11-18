package org.openossad.util.helper.pdf;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 8/11/11
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */
public class PdfHelperTest
{
    @BeforeMethod
    public void setUp() throws Exception
    {
    }



    @Test
    public void testConcatPDFs() throws Exception
    {
        final String destinationPath = System.getProperty("java.io.tmpdir").concat("/testConcat.pdf");

        List pdfList = new ArrayList();
        String pdf1 = ITextStamperTest.class.getResource("/templateTest001.pdf").getPath();;
        String pdf2 = ITextStamperTest.class.getResource("/templateTest002.pdf").getPath();;
        pdfList.add(new FileInputStream(pdf1));
        pdfList.add(new FileInputStream(pdf2));

        FileOutputStream output = new FileOutputStream(destinationPath);
        OutputStream actual = PdfHelper.concatPDFs(pdfList, output, true);
        actual.close();

        Assert.assertNotNull(actual);

    }


}
