import com.mxgraph.examples.swing.GraphEditor;
import com.mxgraph.util.mxUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 26/09/11
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class GraphEditor_Test
{
    private GraphEditor sut;

    @BeforeTest
    public void SetUp(){
    }


    @Test(groups = "unit", enabled = false)
    public void startApplication_Test() {
        sut = new GraphEditor();
        String[] args = null;
        sut.main(args);
        Assert.assertNotNull(sut);
    }


    @DataProvider(name = "myDataProvider2")
    public Object[][] resourceFiles() {
        return new Object[][]{
                {   "/basic-style.xml"},
                {   "/default-style.xml"}
        };
    }
    @Test(groups = "unit",dataProvider =  "myDataProvider2")
    public void resourcesStyleFilesArePlacedCorrectly_Test(String resourceFile){
        Document doc = mxUtils.loadDocument(GraphEditor.class.getResource(resourceFile).toString());
        Assert.assertNotNull(doc);
    }

    @AfterTest
    public void endTest(){

    }

}
