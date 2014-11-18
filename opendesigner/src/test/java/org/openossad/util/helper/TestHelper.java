package org.openossad.util.helper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 26/10/11
 * Time: 17:49
 * To change this template use File | Settings | File Templates.
 */
public class TestHelper
{
    public static void copyFilesFromResources(String modelFile, String dest) throws IOException
    {
        InputStream in = TestHelper.class.getResourceAsStream(modelFile);
        OutputStream out = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        //copy the file content in bytes
        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }
        in.close();
        out.close();
    }
}
