package org.openossad.ui;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.DialogFixture;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 3/11/11
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
public class ooOptionsPropertyGUI_Test
{
    private DialogFixture window;


    @BeforeClass
    public void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeMethod public void setUp() {
        ooOptionsProperty frame = GuiActionRunner.execute(new GuiQuery<ooOptionsProperty>()
        {
            protected ooOptionsProperty executeInEDT()
            {
                return new ooOptionsProperty(null);
            }
        });
        window = new DialogFixture(frame);
        window.show();
    }

    @AfterMethod public void tearDown() {

    }

    @Test (enabled = false) public void shouldCreateProcessWhenClickingOKButton() {
        window.button("JButton4").click();

    }
}
