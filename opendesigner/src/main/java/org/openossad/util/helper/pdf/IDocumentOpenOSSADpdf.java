package org.openossad.util.helper.pdf;

import com.lowagie.text.Document;
import org.openossad.CustomOpenDESIGNERGraph;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 22/10/11
 * Time: 15:36
 * To change this template use File | Settings | File Templates.
 */
public interface IDocumentOpenOSSADpdf
{
    Document insertPortada(Document document);
    Document insertGraph(CustomOpenDESIGNERGraph graph);
    Document insertDetalle(Document document);
}
