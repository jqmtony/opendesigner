package org.openossad.util.helper.pdf;

import org.junit.Assert;
import org.openossad.data.domain.Tblgraphs;
import org.openossad.util.helper.ReferencialFileOOSSAD;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 8/11/11
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public class ITextStamperTest
{
    private ITextStamper sut;
    private Date date = getDummyDate();

    @BeforeMethod
    public void setUp() throws Exception
    {
        sut=getSut();
    }



    @DataProvider(name = "templatePdf")
    Object[][] getTemplatePdf(){
        return new Object[][]{
                {getDummyTblGraphs(),"/templateTest002.pdf",}, // empty pdf
                {getDummyTblGraphs(),"/templateTest001.pdf",}, // sample pdf with fields
        };
    }

    @Test(dataProvider = "templatePdf")
    public void testGenerateFromTemplate(Tblgraphs dummyTblGraphs, String templateName) throws Exception
    {
        final String destinationPath = System.getProperty("java.io.tmpdir").concat("/test.pdf");

        String templatePdfPath = ITextStamperTest.class.getResource(templateName).getPath();
        FileOutputStream actual = sut.generateFromTemplate(dummyTblGraphs, templatePdfPath, destinationPath);
        Assert.assertNotNull(actual);

        actual.close();
    }

    public ITextStamper getSut()
    {
        return new ITextStamper();
    }

    public Tblgraphs getDummyTblGraphs()
    {
        Tblgraphs tblgraph = new Tblgraphs("OPS000-000000001","dummy tblgraphs","dummyRef",1,1,"OPS000-000000000",date,date,date);
        tblgraph.setGdocGen(true);
        return tblgraph;
    }

    public Date getDummyDate()
    {
        return Calendar.getInstance().getTime();
    }
}
