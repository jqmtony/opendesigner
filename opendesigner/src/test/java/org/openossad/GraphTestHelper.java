package org.openossad;

import org.openossad.util.helper.ReferencialGraphOOSSAD;
import org.openossad.util.helper.TestHelper;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 17/11/11
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
public class GraphTestHelper {

    public static Object[][] getProviderGraphs() {
        return new Object[][]{
                {   "/999999236.ood"  },
                {   "/999999263.ood"  },
        };
    }

    public static CustomOpenDESIGNERGraph getDummyGraph(String graphFilePath)
    {
        String graphFilePathFromResources  = GraphTestHelper.class.getResource(graphFilePath).getPath();
        CustomOpenDESIGNERGraphComponent graphComponent = new CustomOpenDESIGNERGraphComponent(new CustomOpenDESIGNERGraph());
        CustomOpenDESIGNERGraph graph = ReferencialGraphOOSSAD.chargeGraphInGraphComponent(graphComponent, graphFilePathFromResources);
        return graph;
    }


    public static void loadInPathTestFiles(String workingFolder) throws IOException
    {
        String destFolder = workingFolder + "/OPS000";
        new File(destFolder).mkdir();
        for (Object[] xmlGraph : getProviderGraphs()) {
            final String file = xmlGraph[0].toString();
            TestHelper.copyFilesFromResources(file, destFolder + file);
        }
    }

    public static void deleteInPathTempFiles(String workingFolder) {
        String destFolder = workingFolder + "/OPS000";
        for (Object[] xmlGraph : getProviderGraphs()) {
            String resourceFile = xmlGraph[0].toString();
            new File(destFolder.concat(resourceFile)).delete();
        }
        new File(destFolder.concat(destFolder)).delete();
    }

    public static CustomOpenDESIGNERGraphComponent getDummyGraphComponent(String graphFilePath) {
        CustomOpenDESIGNERGraph customOpenDESIGNERGraph = GraphTestHelper.getDummyGraph(graphFilePath);
        return new CustomOpenDESIGNERGraphComponent(customOpenDESIGNERGraph);
    }
}
