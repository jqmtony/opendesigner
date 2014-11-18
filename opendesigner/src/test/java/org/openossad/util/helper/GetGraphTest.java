package org.openossad.util.helper;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.DialogFixture;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 26/10/11
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */
public class GetGraphTest
{
    private DialogFixture window;

    @BeforeMethod
    public void setUp() {
        GetGraph frame = GuiActionRunner.execute(new GuiQuery<GetGraph>()
        {
            protected GetGraph executeInEDT()
            {
                return new GetGraph(null);
            }
        });
        window = new DialogFixture(frame);
        //window.show();
    }
    @AfterMethod
    public void tearDown() {
        window.cleanUp();
    }
    @Test(enabled = false)
    public void testMakeSampleTree() throws Exception
    {
//        window.textBox("jButton1").enterText("anyText");
        window.button("cancel").click();
    }
}
