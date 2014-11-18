package org.openossad.util.openoffice;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 9/11/11
 * Time: 12:12
 * To change this template use File | Settings | File Templates.
 */
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.net.ConnectException;

/**
 * Prerequisites
 * %OPENOFFICE_HOME%/soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard
 *
 * @author Ashwin Kumar
 *
 */
public class OpenOfficeConverter {
    // connect to an OpenOffice.org instance running on port 8100
    private OpenOfficeConnection connection = null;

    /**
     * @param oHTMLText
     * @param oOutputStream
     */
    public OutputStream convertFromHTMLToPDF(String oHTMLText, OutputStream oOutputStream) {

        DocumentFormat inputDocumentFormat = new DocumentFormat("HTML",DocumentFamily.TEXT, "text/html", "html");
        inputDocumentFormat.setExportFilter(DocumentFamily.TEXT,"HTML 	(StarWriter)");
        DocumentFormat outputDocumentFormat = new DocumentFormat("Portable	Document Format", DocumentFamily.TEXT,"application/pdf", "pdf");
        outputDocumentFormat.setExportFilter(DocumentFamily.TEXT,"writer_pdf_Export");
        DocumentConverter oDocumentConverter = new OpenOfficeDocumentConverter(connection);
        oDocumentConverter.convert(new ByteArrayInputStream(oHTMLText.getBytes()),inputDocumentFormat, oOutputStream, outputDocumentFormat);
        return oOutputStream;
    }

    /**
     *
     */
    public OpenOfficeConnection openConnection() throws ConnectException
    {
        if (connection == null || !connection.isConnected())
        {
            connection = new SocketOpenOfficeConnection(8100);
        }
        connection.connect();
        return connection;

    }

    /**
     *
     */
    public OpenOfficeConnection openConnection(String host, int port) throws ConnectException
    {

        if (connection == null || !connection.isConnected())
        {
            connection = new SocketOpenOfficeConnection(host, port);
        }
        connection.connect();

        return connection;

    }

    /**
     *
     */
    public OpenOfficeConnection closeConnection() {
        // close the connection

        if (connection != null && connection.isConnected())
        {
            connection.disconnect();
        }
        return connection;
    }
}
