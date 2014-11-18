package org.openossad.ui.base;

import org.openossad.data.domain.Tblprojects;
import org.openossad.ui.component.JButtonMore;
import org.openossad.ui.component.TableOOElements;
import org.openossad.util.GUItools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 3/10/11
 * Time: 13:48
 * To change this template use File | Settings | File Templates.
 */
public class PanelOpenBase extends JDesktopPane
{
    public GUItools gui = new GUItools();
    public TableOOElements tableOoElements;
    private JScrollPane scrollPane;
    private TableModel1 tableModel1;

    public String selectedToOpen = "";
    public Integer level;
    public Integer orientation;

    protected MouseListener   comboTblprojectsMouseListener;
    private MouseListener   ooElementsMouseAdapter;


    protected int w = 150;


    private String sqlQueryForTableModel = "";
    protected JComboBox comboTblprojects;
    public JLabel lbl0;
    public JLabel lbl1;
    public JLabel lbl2;
    public JLabel lbl3;
    public JLabel lbl4;
    public JLabel lbl5;


    public PanelOpenBase()
    {
        super();
        initComponents();
    }

    private void initComponents()
    {
        comboTblprojectsMouseListener= new MouseAdapter()
        {
            public void mouseClicked(MouseEvent mouseEvent)
            {
                final Tblprojects selectedItem = (Tblprojects) comboTblprojects.getSelectedItem();
                final String projectId = selectedItem.getProjectId();
                updateTableModel(projectId);

            }
        };
        tableOoElements = new TableOOElements();
        add(gui.getLbl("Buscar en carpeta", 30, 43, 130, 20), null);
        add(gui.getDivider(this,75), null);

        add(new JButtonMore(), null);

        //common components
        add(gui.getDivider(this,427));
        add(gui.getbutFolder(520, 42, 25, 25), null);
        add(gui.getJButtonOpenGraph(600 - 120, 440, 103, 30));
        add(gui.getJButtonCancel(600 - 240, 440, 103, 30));


        //tableModel1 = new TableModel1();
    }

    public PanelOpenBase(MouseListener mouseListener)
    {
        super();
        ooElementsMouseAdapter=mouseListener;
        initComponents();
    }


    public JComponent getMainJXTable() {
        int xx=1;
        int yy=80;
        int width=590;
        int height=190;
        scrollPane = new JScrollPane(tableOoElements);
        scrollPane.setBounds(new Rectangle(xx, yy, width, height));
        return scrollPane;
    }

    public JComponent getMainJXTable(String idx)
    {
        updateTableModel(idx);
        return getMainJXTable();
    }

    public void updateTableModel(String idx){
        //String sqlquery = "SELECT Gname ,GRef ,GLevel ,COALESCE(GVersionNumber,'1.0') ,GDesignDate ,GUpdateDate ,ProjectID ,GVisPublic ,COALESCE(GAuthor,'') ,GId,GOrientation  FROM tblgraphs WHERE GLevel in (1,2,3) AND ProjectID='" + idx + "' ";
        tableModel1 = new TableModel1();
        idx=(idx.equals(""))? "":"'"+idx+"'";
        tableModel1.setConnQuery(sqlQueryForTableModel+idx);

        tableModel1.loadDataFromODBC();
        tableOoElements.clearSelection();
        tableOoElements.setModel(tableModel1);
    }

    public MouseListener getOoElementsMouseAdapter()
    {
        return ooElementsMouseAdapter;
    }

    public void setOoElementsMouseAdapter(MouseListener ooElementsMouseAdapter)
    {
        this.ooElementsMouseAdapter = ooElementsMouseAdapter;
        tableOoElements.addMouseListener(getOoElementsMouseAdapter());
    }
    public void setSqlQueryForTableModel(String sqlQueryForTableModel)
    {
        this.sqlQueryForTableModel = sqlQueryForTableModel;
    }

        protected void addComboTblprojects()
    {
        comboTblprojects = new JComboBox();
        add(gui.getComboFolder(comboTblprojects,(this.size().width - (250))));
        comboTblprojects.addMouseListener(comboTblprojectsMouseListener);
    }


    public MouseListener getMouseAdapterTblgraphsTable()
    {
        return new MouseAdapter() {
             public void mouseClicked(MouseEvent e) {
                 lbl0.setText(getValueAtRow(tableOoElements,0));
                 lbl1.setText(getValueAtRow(tableOoElements,4));
                 lbl2.setText(getValueAtRow(tableOoElements,5));
                 lbl3.setText(getValueAtRow(tableOoElements,8));
                 lbl4.setText(getValueAtRow(tableOoElements,1));
                 lbl5.setText(getValueAtRow(tableOoElements,9).substring(7, 16) + ".osd");
                 selectedToOpen = getValueAtRow(tableOoElements,9);
                 level = Integer.parseInt((getValueAtRow(tableOoElements,2)));
                 orientation = Integer.parseInt(getValueAtRow(tableOoElements,10));
                 if (e.getClickCount() == 2) {
                     JOptionPane.showConfirmDialog(null, "¿ Desea ir a la página de propiedades del gráfico ? ", "OpenDESIGNER", JOptionPane.INFORMATION_MESSAGE);
                 }
             }
         };
    }

    public String getValueAtRow(TableOOElements tableOoElements, int columnIdx) {
        return tableOoElements.getValueAt(tableOoElements.getSelectedRow(), columnIdx).toString();
    }

    public void updateTableModelJXTable()
    {
        tableOoElements.removeAll();
        String tempConnQuery = tableModel1.getConnQuery();
        tableModel1=new TableModel1();
        tableModel1.setConnQuery(tempConnQuery);
        tableModel1.loadDataFromODBC();
        tableOoElements.setModel(tableModel1);
    }
}
