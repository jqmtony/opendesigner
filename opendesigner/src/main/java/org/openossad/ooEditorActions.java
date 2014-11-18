package org.openossad;

import com.lowagie.text.DocumentException;
import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxSvgCanvas;
import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import com.mxgraph.examples.swing.editor.DefaultFileFilter;
import com.mxgraph.io.mxCodec;
import com.mxgraph.io.mxGdCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.png.mxPngEncodeParam;
import com.mxgraph.util.png.mxPngImageEncoder;
import com.mxgraph.view.mxGraph;
import org.openossad.data.CellUserObject;
import org.openossad.data.domain.Tblgraphs;
import org.openossad.ui.experimental.ooDrawMark;
import org.openossad.ui.*;
import org.openossad.util.PromptUtil;
import org.openossad.util.helper.*;
import org.openossad.util.helper.pdf.PdfLocalService;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 10/10/11
 * Time: 15:23
 * To change this template use File | Settings | File Templates.
 */
public class ooEditorActions
{


    public static class ooNewAction extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {

            BasicGraphEditorOO editor = EditorActionHelper.getEditor(e);

            if (editor != null)
            {
                if (!editor.isModified() || JOptionPane.showConfirmDialog(editor,mxResources.get("loseChanges")) == JOptionPane.YES_OPTION)
                {
                    mxGraph graph = editor.getGraphComponent().getGraph();
                    if (graph != null)
                    {
                        JFrame frame = (JFrame) SwingUtilities.windowForComponent((Component) e.getSource());
                        ooNewGenericDlg oo1 = new ooNewGenericDlg(frame);
                        oo1.pack();
                        oo1.setSize(730, 530);
                        oo1.setVisible(true);
                    }
                }
            }
        }

    }

    public static class ooOpenAction extends AbstractAction {

        public void actionPerformed(ActionEvent e) {

            BasicGraphEditorOO editor = EditorActionHelper.getEditor(e);

            if (editor != null)
            {
                if (!editor.isModified() || JOptionPane.showConfirmDialog(editor,mxResources.get("loseChanges")) == JOptionPane.YES_OPTION)
                {
                    mxGraph graph = editor.getGraphComponent().getGraph();
                    if (graph != null)
                    {
                        JFrame frame = (JFrame) SwingUtilities.windowForComponent((Component) e.getSource());
                        ooOpenGenericDlg oo1 = new ooOpenGenericDlg(frame);
                        oo1.pack();
                        oo1.setSize(730, 530);
                        oo1.setVisible(true);
                    }
                }
            }
        }
    }

    public static class ooSaveAction extends AbstractAction
    {
        /**
         *
         */
        protected boolean showDialog;

        /**
         *
         */
        protected String lastDir = null;

        /**
         *
         */
        public ooSaveAction(boolean showDialog)
        {
            this.showDialog = showDialog;
        }

        /**
         * Saves XML+PNG format.
         */
        protected void saveXmlPng(BasicGraphEditor editor, String filename,Color bg) throws IOException
        {
            mxGraphComponent graphComponent = editor.getGraphComponent();
            mxGraph graph = graphComponent.getGraph();

            // Creates the image for the PNG file
            BufferedImage image = mxCellRenderer.createBufferedImage(graph,null, 1, bg, graphComponent.isAntiAlias(), null,graphComponent.getCanvas());

            // Creates the URL-encoded XML data
            mxCodec codec = new mxCodec();
            String xml = URLEncoder.encode(mxUtils.getXml(codec.encode(graph.getModel())), "UTF-8");
            mxPngEncodeParam param = mxPngEncodeParam.getDefaultEncodeParam(image);
            param.setCompressedText(new String[] { "mxGraphModel", xml });

            // Saves as a PNG file
            FileOutputStream outputStream = new FileOutputStream(new File(filename));
            try
            {
                mxPngImageEncoder encoder = new mxPngImageEncoder(outputStream,
                                                                  param);

                if (image != null)
                {
                    encoder.encode(image);
                    editor.setModified(false);
                    editor.setCurrentFile(new File(filename));
                }
                else
                {
                    JOptionPane.showMessageDialog(graphComponent,
                                                  mxResources.get("noImageData"));
                }
            }
            finally
            {
                outputStream.close();
            }
        }

        /**
         *
         */
        public void actionPerformed(ActionEvent e)
        {
            BasicGraphEditorOO editor = EditorActionHelper.getEditor(e);

            if (editor != null)
            {
                CustomOpenDESIGNERGraphComponent graphComponent = (CustomOpenDESIGNERGraphComponent) editor.getGraphComponent();
                final CustomOpenDESIGNERGraph graph = (CustomOpenDESIGNERGraph) graphComponent.getGraph();
                FileFilter selectedFilter = null;
                DefaultFileFilter xmlPngFilter = new DefaultFileFilter(".png","PNG+XML " + mxResources.get("file") + " (.png)");
                FileFilter vmlFileFilter = new DefaultFileFilter(".html","VML " + mxResources.get("file") + " (.html)");
                String filename = null;
                boolean dialogShown = false;

                if (showDialog || editor.getCurrentFile() == null)
                {
                    String wd;

                    if (lastDir != null)
                    {
                        wd = lastDir;
                    }
                    else if (editor.getCurrentFile() != null)
                    {
                        wd = editor.getCurrentFile().getParent();
                    }
                    else
                    {
                        wd = System.getProperty("user.dir");
                    }

                    JFileChooser fc = new JFileChooser(wd);

                    // Adds the default file format
                    FileFilter defaultFilter = xmlPngFilter;
                    fc.addChoosableFileFilter(defaultFilter);

                    // Adds special vector graphics formats and HTML
                    fc.addChoosableFileFilter(new DefaultFileFilter(".ood","Opendesigner" + mxResources.get("file")+ " (.ood)"));
                    fc.addChoosableFileFilter(new DefaultFileFilter(".mxe","mxGraph Editor " + mxResources.get("file")+ " (.mxe)"));
                    fc.addChoosableFileFilter(new DefaultFileFilter(".txt","Graph Drawing " + mxResources.get("file")+ " (.txt)"));
                    fc.addChoosableFileFilter(new DefaultFileFilter(".svg","SVG " + mxResources.get("file") + " (.svg)"));
                    fc.addChoosableFileFilter(vmlFileFilter);
                    fc.addChoosableFileFilter(new DefaultFileFilter(".html","HTML " + mxResources.get("file") + " (.html)"));

                    // Adds a filter for each supported image format
                    Object[] imageFormats = ImageIO.getReaderFormatNames();

                    // Finds all distinct extensions
                    HashSet<String> formats = new HashSet<String>();

                    for (int i = 0; i < imageFormats.length; i++)
                    {
                        String ext = imageFormats[i].toString().toLowerCase();
                        formats.add(ext);
                    }

                    imageFormats = formats.toArray();

                    for (int i = 0; i < imageFormats.length; i++)
                    {
                        String ext = imageFormats[i].toString();
                        fc.addChoosableFileFilter(new DefaultFileFilter("."+ ext, ext.toUpperCase() + " " + mxResources.get("file") + " (." + ext + ")"));
                    }

                    // Adds filter that accepts all supported image formats
                    fc.addChoosableFileFilter(new DefaultFileFilter.ImageFileFilter(mxResources.get("allImages")));
                    fc.setFileFilter(defaultFilter);
                    int rc = fc.showDialog(null, mxResources.get("save"));
                    dialogShown = true;

                    if (rc != JFileChooser.APPROVE_OPTION)
                    {
                        return;
                    }
                    else
                    {
                        lastDir = fc.getSelectedFile().getParent();
                    }

                    filename = fc.getSelectedFile().getAbsolutePath();
                    selectedFilter = fc.getFileFilter();

                    if (selectedFilter instanceof DefaultFileFilter)
                    {
                        String ext = ((DefaultFileFilter) selectedFilter).getExtension();
                        if (!filename.toLowerCase().endsWith(ext))
                        {
                            filename += ext;
                        }
                    }

                    if (new File(filename).exists()
                        && JOptionPane.showConfirmDialog(graphComponent,mxResources.get("overwriteExistingFile")) != JOptionPane.YES_OPTION)
                    {
                        return;
                    }
                }
                else
                {
                    filename = editor.getCurrentFile().getAbsolutePath();
                }

                try
                {
                    String ext = filename.substring(filename.lastIndexOf('.') + 1);

                    if (ext.equalsIgnoreCase("svg"))
                    {
                        mxSvgCanvas canvas = (mxSvgCanvas) mxCellRenderer
                                .drawCells(graph, null, 1, null,
                                           new mxCellRenderer.CanvasFactory()
                                           {
                                               public mxICanvas createCanvas(int width, int height)
                                               {
                                                   mxSvgCanvas canvas = new mxSvgCanvas(mxUtils.createSvgDocument(width, height));
                                                   canvas.setEmbedded(true);

                                                   return canvas;
                                               }

                                           });

                        mxUtils.writeFile(mxUtils.getXml(canvas.getDocument()),filename);
                    }
                    else if (selectedFilter == vmlFileFilter)
                    {
                        mxUtils.writeFile(mxUtils.getXml(mxCellRenderer.createVmlDocument(graph, null, 1, null, null).getDocumentElement()), filename);
                    }
                    else if (ext.equalsIgnoreCase("html"))
                    {
                        mxUtils.writeFile(mxUtils.getXml(mxCellRenderer.createHtmlDocument(graph, null, 1, null, null).getDocumentElement()), filename);
                    }
                    else if (ext.equalsIgnoreCase("mxe") || ext.equalsIgnoreCase("xml") )
                    {
                        mxCodec codec = new mxCodec();
                        String xml = mxUtils.getXml(codec.encode(graph.getModel()));
                        mxUtils.writeFile(xml, filename);
                        editor.setModified(false);
                        editor.setCurrentFile(new File(filename));
                    }
                    else if (ext.equalsIgnoreCase("ood") )
                    {
                        mxCodec codec = new mxCodec();
                        String xml = mxUtils.getXml(codec.encode(graph.getModel()));
                        mxUtils.writeFile(xml, filename);
                        editor.setModified(false);
                        editor.setCurrentFile(new File(filename));
                        //TODO: verify that cells go into DB
                        ReferencialGraphOOSSAD.getCells((CustomOpenDESIGNERGraphComponent) editor.getGraphComponent(),filename);

                    }

                    else if (ext.equalsIgnoreCase("txt"))
                    {
                        String content = mxGdCodec.encode(graph).getDocumentString();
                        mxUtils.writeFile(content, filename);
                    }
                    else
                    {
                        Color bg = null;

                        if ((!ext.equalsIgnoreCase("gif") && !ext.equalsIgnoreCase("png")) || JOptionPane.showConfirmDialog(graphComponent, mxResources.get("transparentBackground")) != JOptionPane.YES_OPTION)
                        {
                            bg = graphComponent.getBackground();
                        }

                        if (selectedFilter == xmlPngFilter|| (editor.getCurrentFile() != null && ext.equalsIgnoreCase("png") && !dialogShown))
                        {
                            saveXmlPng(editor, filename, bg);
                        }


                        else
                        {
                            String currentGId = EditorActionHelper.getCurrentGId(e);
                            if (currentGId.equals("")) {
                                JOptionPane.showMessageDialog(graphComponent,"not open");
                            } else {

                                BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 1, bg,graphComponent.isAntiAlias(), null,graphComponent.getCanvas());

                                ooDrawMark drawMark = new ooDrawMark();


                                Tblgraphs tblgraphs = DAOOOSSAD.getooGraphOBJ(currentGId);
                                mxRectangle bounds = graph.getGraphBounds();

                                Graphics2D bufferedImageGraphics = image.createGraphics();
                                bufferedImageGraphics = drawMark.SimpleMark(bufferedImageGraphics, 800,1100, tblgraphs);
                                image.getGraphics().dispose();


                                if (image != null)
                                {

                                    ImageIO.write(image, ext, new File(filename));
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(graphComponent,mxResources.get("noImageData"));
                                }
                            }
                        }
                    }
                }
                catch (Throwable ex)
                {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(graphComponent,
                                                  ex.toString(), mxResources.get("error"),
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }


    public static class ooExportPDFAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String currentGId = EditorActionHelper.getCurrentGId(e);
            if(currentGId == null) return;

            new ooSaveAction(false);

            Tblgraphs myGraph = DAOOOSSAD.getooGraphOBJ(currentGId);
            PdfLocalService pdfLocalService = new PdfLocalService();
            InputStream actual = null;
            try
            {
                String newFilePath = ReferencialFileOOSSAD.getFileToSavePDF();
                if (!newFilePath.equals("")) {
                    String workingFolder = DAOOOSSAD.getooOptionsOBJ().getCarpetaTrabajo();
                    actual = pdfLocalService.createFullPdfFromMxGraph(myGraph,newFilePath, workingFolder);
                }
                if (actual.available()>0) {
                    final BasicGraphEditorOO editor = EditorActionHelper.getEditor(e);
                    if (JOptionPane.showConfirmDialog(editor,"El archivo pdf se ha creado !\n¿ Desea abrir el nuevo pdf ?") == JOptionPane.YES_OPTION) {
                        ReferencialFileOOSSAD.OpenDocument(newFilePath);
                    }
                }
            }
            catch(DocumentException e1)
            {
                e1.printStackTrace();
            }
            catch(IOException e1)
            {
                e1.printStackTrace();
            }

        }
    }


    public static class ooPropertiesAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            BasicGraphEditorOO editor = EditorActionHelper.getEditor(e);
            Frame frame = (Frame) SwingUtilities.windowForComponent(editor);
            String currentGId = EditorActionHelper.getCurrentGId(e);

            if (currentGId.equals(""))
            {
                GetGraph getGraph = new GetGraph(frame);
                currentGId=getGraph.getGidx();
            }
            if (currentGId == null ) return;
            ooPropsGenericDlg propsGenericDlg = new ooPropsGenericDlg(frame,currentGId);
            propsGenericDlg.setVisible(true);
        }
    }


    public static class OpenOSSADalignRolesAction extends AbstractAction {


        public void actionPerformed(ActionEvent e) {

            BasicGraphEditorOO graphFrame = EditorActionHelper.getEditor(e);
            String currentGId = EditorActionHelper.getCurrentGId(e);

            if (currentGId.equals("")) {
                JOptionPane.showMessageDialog(
                        null,
                        "Antes debe de abrir un gráfico para poder insertar roles.",
                        "Error de redacción",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            final CustomOpenDESIGNERGraphComponent graphComponent = (CustomOpenDESIGNERGraphComponent) graphFrame.getGraphComponent();


            List<mxCell> mxCellList = ReferencialGraphOOSSAD.getCellsRoles(graphComponent);
            int numRoles = mxCellList.size();
            if (numRoles==0) {
                numRoles = ReferencialGraphOOSSAD.promptUser_getNumRolesToCreate();
                if (numRoles>0) {
                    ReferencialGraphOOSSAD.addRolesToGraph(graphComponent.getGraph(), numRoles);
                }
            }
            if (numRoles>0) {
                mxGraph graph = ReferencialGraphOOSSAD.alignRolesInOoGraph(graphComponent, ReferencialGraphOOSSAD.getCellsRoles(graphComponent));
            }
        }
    }

    public static class OpenOSSADdoComment extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            BasicGraphEditor editor = EditorActionHelper.getEditor(e);
            mxGraph graph = editor.getGraphComponent().getGraph();

            if (graph != null && !graph.isSelectionEmpty()) {
                mxCell myCell = (mxCell) graph.getSelectionCell();

                Object value = myCell.getValue();
                CellUserObject cellUserObject = new CellUserObject(value);

                String comment = cellUserObject.getComment();
                String promptCellComment = PromptUtil.promptCellComment(editor, comment);
                if (promptCellComment!=null) {
                    cellUserObject.setComment(promptCellComment);
                    graph.getModel().beginUpdate();
                    myCell.setValue(cellUserObject.getUserObject());
                    graph.getModel().endUpdate();
                }
            }
        }
    }

    public static class OpenOSSADLinkCellAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFrame frame = (JFrame) SwingUtilities.windowForComponent((Component) e.getSource());
            BasicGraphEditorOO graphFrame = EditorActionHelper.getEditor(e);
            mxGraph graph = graphFrame.getGraphComponent().getGraph();

            mxCell mxCell = (mxCell) graph.getSelectionCell();
            CellUserObject  cellUserObject = new CellUserObject(mxCell.getValue());
            String relatedId = cellUserObject.getRelationId();
            String tipCell = mxCell.getStyle();



            if (!relatedId.equals("")) {
                // GESTION DE ENLACES DE ROLES

                if (tipCell.equals("10") || tipCell.equals("6") ||tipCell.equals("9")) {

                }
                // GESTION DE ENLACES DE PROCESOS

                if (tipCell.equals("112") ||tipCell.equals("333") || tipCell.equals("16") || tipCell.equals("3")) {
                    try
                    {
                        graphFrame.doOpenGraph(relatedId);
                    }
                    catch(IOException e1)
                    {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                if (tipCell.equals("8")) {
                    String relatedIdDocumentUrl = DAOOOSSAD.getooGraphOBJ(relatedId).getGlinkDoc();
                    ReferencialFileOOSSAD.OpenDocument(relatedIdDocumentUrl);
                }

            }


        }
    }

    public static class OpenOSSADdeleteLinkCellAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            BasicGraphEditorOO graphFrame = EditorActionHelper.getEditor(e);
            mxGraph graph = graphFrame.getGraphComponent().getGraph();

            if (graph != null && !graph.isSelectionEmpty()) {

                mxCell currentCell = (mxCell) graph.getSelectionCell();
                CellUserObject cellUserObject = new CellUserObject(currentCell.getValue());
                cellUserObject.clearRelations();
                currentCell.setValue(cellUserObject.getUserObject());
            }
        }
    }
    public static class OpenOSSADcreateLinkCellAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFrame frame = (JFrame) SwingUtilities.windowForComponent((Component) e.getSource());
            BasicGraphEditor graphFrame = EditorActionHelper.getEditor(e);
            mxGraph graph = graphFrame.getGraphComponent().getGraph();

            String style = graph.getModel().getStyle(graph.getSelectionCell());
            String tipCell=style;

            if (graph != null && !graph.isSelectionEmpty()) {

                // mxCell currentCell = (mxCell) graph.getSelectionCell();
                // Inicio de la gestión

                mxCell currentCell = (mxCell) graph.getSelectionCell();
                Object value = currentCell.getValue();
                if (value instanceof String) {
                    value = new CellUserObject(value).getUserObject();
                    currentCell.setValue(value);
                }
                String CellID=currentCell.getId();
                ooOpenGenericDlg openGenericDlg = new ooOpenGenericDlg(frame);
                openGenericDlg.setSize(750, 510);


                String result ="";
                String resultId="";
                String resultName="";

                if (tipCell.equals("10") || tipCell.equals("6") ||tipCell.equals("9")) {
                    result =openGenericDlg.oo1GetIDEntity();
                }
                if (tipCell.equals("333") || tipCell.equals("16") || tipCell.equals("3") ||
                    tipCell.equals("112") || tipCell.equals("113") || tipCell.equals("114") || tipCell.equals("8")) {

                    GetGraph getGraph = new GetGraph(frame);
                    resultName = (getGraph.getID()==null) ? "" : getGraph.getID();
                    resultId   = (getGraph.getGidx()==null) ? "" : getGraph.getGidx();

                }

                //Obtengo lo que linko: IDFORM ^ IDX

                if (!resultId.equals("")) {
                    currentCell.setAttribute("relationName",resultName);
                    currentCell.setAttribute("relationId",resultId);

                    //style = graph.getModel().getStyle(graph.getSelectionCell());
                    //style= (style.indexOf(";",2)>0 ) ? style.substring(style.indexOf(";",2)+1,style.length()) : "";


//					graph.setCellStyle(style0+style);
//					currentCell.setValue(resultName);
//					currentCell.setId(CellID+"^"+resultId);

                }
            }
        }  }
    public static class ooFolderAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            BasicGraphEditorOO basicGraphEditorOO = EditorActionHelper.getEditor(e);
            Frame frame = (Frame) SwingUtilities.windowForComponent(basicGraphEditorOO);
            ooFolderExplorer folderExplorer = new ooFolderExplorer(frame);
            folderExplorer.setVisible(true);
        }
    }

    public static class ooOptionsDialog extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            BasicGraphEditorOO basicGraphEditorOO = EditorActionHelper.getEditor(e);
            Frame frame = (Frame) SwingUtilities.windowForComponent(basicGraphEditorOO);
            ooOptionsProperty optionsProperty = new ooOptionsProperty(frame);
            optionsProperty.setVisible(true);
        }
    }
    public static class ooExploreReferentiel extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            BasicGraphEditorOO basicGraphEditorOO = EditorActionHelper.getEditor(e);
            Frame frame = (Frame) SwingUtilities.windowForComponent(basicGraphEditorOO);
            GetGraph getGraph = new GetGraph(frame);
        }
    }
}
