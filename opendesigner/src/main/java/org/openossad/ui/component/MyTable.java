package org.openossad.ui.component;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;
import org.openossad.ui.base.TableModel1;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MyTable extends JXTable {

	// ----------------- demo decorators
	private Highlighter currentHighlighter;
	private String currentHighlighterKey;
	private String currentComparatorKey;



	public MyTable() {
	}

	public JXTable initTable() {
		// boilerplate table-setup; this would be the same for a JTable
		TableModel1 model = new TableModel1();
		JXTable table = new JXTable(model);

		model.loadDataFromODBC();
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setHorizontalScrollEnabled(true);
		table.setRowHeightEnabled(true);
		table.setRowHeight(20);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		return table;
	}

	public JXTable initTable(String sqlQuery) {
		// boilerplate table-setup; this would be the same for a JTable
		TableModel1 model = new TableModel1();
		model.setConnQuery(sqlQuery);
        model.loadDataFromODBC();
        JXTable table = new JXTable(model);

		configureCommonTableProperties(table);

		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		// table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setHorizontalScrollEnabled(true);
		table.setRowHeightEnabled(true);
		table.setRowHeight(20);
		table.setShowGrid(false);
		table.setCellSelectionEnabled(false);

		// table.setHighlighters(new HighlighterPipeline(new Highlighter[]{
		// AlternateRowHighlighter.linePrinter}));
		// table.getHighlighters().addHighlighter(new
		// RolloverHighlighter(Color.BLACK, Color.WHITE ));

		int margin = 1;
		table.packTable(margin);

		return table;
	}

	private void configureCommonTableProperties(JXTable table) {
		table.setColumnControlVisible(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRolloverEnabled(true);
		// table.packTable(0);
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
		table.setDefaultRenderer(Point.class, renderer);
		table.setDefaultRenderer(Dimension.class, renderer);

	}

	public static void main(String[] args) {
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);

			// Create and set up the window.
			JFrame frame = new JFrame("SimpleTableDemo");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// Create and set up the content pane.
			MyTable t1 = new MyTable();
			JXTable t2 = t1.initTable();

			// Display the window.
			frame.add(t2);
			frame.pack();
			frame.setSize(1024, 768);
			frame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
