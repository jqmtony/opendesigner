package org.openossad.ui.component;

import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 14/10/11
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class TableOOElements extends JXTable
{
    private int margin = 2;

    public TableOOElements()
    {
		configureCommonTableProperties();
        setPreferredScrollableViewportSize(new Dimension(480, 70));
		setHorizontalScrollEnabled(true);
		setRowHeightEnabled(true);
		setRowHeight(20);
		setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        setRowSelectionAllowed(true);
		setShowGrid(false);
		setCellSelectionEnabled(false);
        setEditable(false);
        packTable(margin);

    }

    private void configureCommonTableProperties()
    {
        setColumnControlVisible(true);
        setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //setRolloverEnabled(true);
        //packTable(0);
        TableCellRenderer renderer = new DefaultTableCellRenderer() {
            public void setValue(Object value) {
                if (value instanceof Point) {
                    Point p = (Point) value;
                    value = createString(p.x, p.y);
                } else if (value instanceof Dimension) {
                    Dimension dim = (Dimension) value;
                    value = createString(dim.width, dim.height);
                }
                super.setValue(value);
                setIcon(MetalIconFactory.getTreeFolderIcon());
            }

            private Object createString(int width, int height) {
                return "(" + width + ", " + height + ")";
            }
        };
        setDefaultRenderer(Point.class, renderer);
        setDefaultRenderer(Dimension.class, renderer);
    }
}
