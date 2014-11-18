package org.openossad;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 18/10/11
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.DialogFixture;
import org.openossad.ui.panels.panelNewOO1;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class panelNewOO1_GUI_Test
{

    private DialogFixture window;


    @BeforeClass public void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeMethod public void setUp() {
        panelNewOO1 frame = GuiActionRunner.execute(new GuiQuery<panelNewOO1>()
        {
            protected panelNewOO1 executeInEDT()
            {
                return new panelNewOO1();
            }
        });
//        window = new DialogFixture(frame);
//        window.show();
    }

    @AfterMethod public void tearDown() {

    }

    @Test(enabled = false) public void shouldCreateProcessWhenClickingOKButton() {
        window.textBox("txtGName").enterText("Some process name to create");
        window.textBox("txtGRef").enterText("Some reference to create");
        window.button("OK").click();
    }
}