package org.openossad.util.init;

import org.openossad.util.helper.ReferencialFileOOSSAD;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 5/11/11
 * Time: 0:13
 * To change this template use File | Settings | File Templates.
 */
public class InitOpenossadDataHelper
{
    private static String trzFolder = "TRZ000";
    private static String sep = File.separator;


    public static void createInitialModels(File folderRepository) throws IOException
    {

        if (!containValidFolder(folderRepository.getPath())) {
            new File(folderRepository.getPath().concat(File.separator).concat(trzFolder)).mkdir();
        }

        for (int x=1;x<=6;x++) {
            final String resourceFile = File.separator + trzFolder + File.separator  + "00000000" +x+".ood";
            final String dest = folderRepository.getPath().concat(resourceFile);
            if (!new File(dest).exists())
            {
                ReferencialFileOOSSAD.copyFilesFromResources(resourceFile, dest);
            }
        }
    }

    public static boolean containValidFolder(String workingFolder)
    {

        File file =  new File(workingFolder.concat(sep).concat(trzFolder));
        if (!file.exists()) {
            return false;
        }
        String[] files = file.list();
        if (files.length<6)
        {
            return false;
        }
        return true;
    }

}
