/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 17/11/11
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */

package org.openossad;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ooSaveAction_Test {

    private ooEditorActions.ooSaveAction sut;
    private ActionEvent mock_actionEvent;
    private BasicGraphEditorOO mock_basicGraphEditor;
    private String workingFolder = System.getProperty("user.dir");

    @BeforeMethod
    public void setUp() throws IOException {
        sut = getSut();
        mock_actionEvent = mock(ActionEvent.class);
        mock_basicGraphEditor = mock(BasicGraphEditorOO.class);
        GraphTestHelper.loadInPathTestFiles(workingFolder);


    }

    @AfterClass
    public void tearDownClass() {
        GraphTestHelper.deleteInPathTempFiles(workingFolder);
    }

    @DataProvider(name = "graphsToTestPdfCreation")
    public Object[][] getProvider() {
        return GraphTestHelper.getProviderGraphs();
    }

    @Test(groups = "unit", dataProvider = "graphsToTestPdfCreation")
    public void exerciseOoSaveActionTest(String graphFilePath) throws Exception {

        CustomOpenDESIGNERGraphComponent customOpenDESIGNERGraphComponent = GraphTestHelper.getDummyGraphComponent(graphFilePath);

        doReturn(customOpenDESIGNERGraphComponent).when(mock_basicGraphEditor).getGraphComponent();

        File toBeReturned = new File(workingFolder.concat("/OPS000").concat(graphFilePath));
        doReturn(toBeReturned).when(mock_basicGraphEditor).getCurrentFile();

        doReturn(mock_basicGraphEditor).when(mock_actionEvent).getSource();

        sut.actionPerformed(mock_actionEvent);

    }

    public ooEditorActions.ooSaveAction getSut() {
        return new ooEditorActions.ooSaveAction(false);
    }
}