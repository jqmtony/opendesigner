/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 21/10/11
 * Time: 11:53
 * To change this template use File | Settings | File Templates.
 */

package org.openossad.util.helper.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import org.openossad.CustomOpenDESIGNERGraph;
import org.openossad.GraphTestHelper;
import org.openossad.data.dao.HibernateDAOFactory;
import org.openossad.data.domain.OoOptions;
import org.openossad.data.domain.Tblgraphs;
import org.openossad.util.helper.ReferencialFileOOSSAD;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class PdfLocalService_Test
{

    private PdfLocalService sut;
    private String filePdfToSave;

    private OoOptions ooOptions;
    private HibernateDAOFactory hibernateDAOFactory = new HibernateDAOFactory();
    private String workingFolder;

    @BeforeMethod
    public void setUp() throws IOException
    {
        sut = getSut();
        workingFolder =getWorkingFolder();

        GraphTestHelper.deleteInPathTempFiles(workingFolder);
        filePdfToSave = PdfLocalService_Test.class.getResource("/").getPath().concat("test.pdf");
        new File(filePdfToSave).delete();


        GraphTestHelper.loadInPathTestFiles(workingFolder);


    }



    private String getWorkingFolder() {
        hibernateDAOFactory.getCurrentSession().beginTransaction();

        List<OoOptions> ooOptionsList = hibernateDAOFactory.getOoOptionsDAO().findAll();
        if (ooOptionsList.isEmpty()) {
            return System.getProperty("user.dir");
        }
        ooOptions = ooOptionsList.get(0);
        return ooOptions.getCarpetaTrabajo();
    }



    @AfterMethod
    public void tearDown()
    {
        String workingFolder =getWorkingFolder();
        final String fileWorkingFolder = workingFolder + "/OPS000";
        ReferencialFileOOSSAD.deleteDirectory(new File(fileWorkingFolder));
    }

    private PdfLocalService getSut()
    {
        return new PdfLocalService();
    }


    @DataProvider(name = "graphsToTestPdfCreation")
    public Object[][] getProvider() {
        return GraphTestHelper.getProviderGraphs();
    }

    @Test(groups = "unit", dataProvider = "graphsToTestPdfCreation")
    public void createPdfFromMxgraph_retunsOutputStreamPDF_Test(String graphFilePath) throws DocumentException, IOException
    {
        CustomOpenDESIGNERGraph graph = GraphTestHelper.getDummyGraph(graphFilePath);
        String filePdf = getDummyFilePdf(graphFilePath);

        InputStream actual   = sut.createPdfFromMxGraph(graph, filePdf);
        InputStream expected = new  FileInputStream(new File(filePdf));
        Assert.assertEquals(actual.available(),expected.available(),"Pdf file has not been created");
    }



    private String getDummyFilePdf(String graphFilePath)
    {
        return  PdfLocalService_Test.class.getResource(graphFilePath).getPath().concat(".pdf");
    }



    @DataProvider(name = "TblgraphsToTestPdfCreation")
    public Object[][] getProvider2() {
        return new Object[][]{
                {   new Tblgraphs("OPS000-999999236","gname","gref",1,1,"projectId",new Date(),new Date(),new Date()), GraphTestHelper.getDummyGraph("/999999236.ood"),getDummyFilePdf("/999999236.ood")},
                {   new Tblgraphs("OPS000-999999263","gname","gref",2,2,"projectId",new Date(),new Date(),new Date()), GraphTestHelper.getDummyGraph("/999999263.ood"),getDummyFilePdf("/999999263.ood")},
        };
    }

    @Test(groups = "unit", dataProvider = "TblgraphsToTestPdfCreation")
    public void createFullPdfFromTblgraph_retunsOutputStreamPDF_Test(Tblgraphs tblgraphs,CustomOpenDESIGNERGraph graph, String filePdf) throws DocumentException, IOException
    {
        InputStream actual   = sut.createFullPdfFromMxGraph(tblgraphs,filePdfToSave,workingFolder);
        InputStream expected = new  FileInputStream(new File(filePdfToSave));
        Assert.assertEquals(actual.available(),expected.available(),"Pdf file has not been created");
    }

    @Test(groups = "unit", dataProvider = "TblgraphsToTestPdfCreation")
    public void addCommentsToIncludeInGraph_retunsWellFormedDocument_Test(Tblgraphs tblgraphs, CustomOpenDESIGNERGraph graph, String filePdf) throws DocumentException, IOException
    {
        final Rectangle rectangleGraphBounds = sut.getRectangleGraphBounds(graph);
        Document document = sut.getFormatedDocument(new Document(), rectangleGraphBounds, filePdf);
        Document actual = sut.addCommentsGraphToPage(document, graph, tblgraphs);
        Assert.assertEquals(actual.isOpen(),true,"Document pdf is not open");
    }
    @Test(groups = "unit", dataProvider = "TblgraphsToTestPdfCreation")
    public void addChildGraphsToPageGraph_retunsOutputStreamPDF_Test(Tblgraphs tblgraphs, CustomOpenDESIGNERGraph graph, String filePdf) throws DocumentException, IOException
    {
        final Rectangle rectangleGraphBounds = sut.getRectangleGraphBounds(graph);
        Document document = sut.getFormatedDocument(new Document(), rectangleGraphBounds, filePdf);
        Document actual = sut.addLinkedGraphToPage(document, graph, tblgraphs);
        Assert.assertEquals(actual.isOpen(),true,"Document pdf is not open");
    }

}