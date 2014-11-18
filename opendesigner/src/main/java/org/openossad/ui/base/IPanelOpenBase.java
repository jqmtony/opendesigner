package org.openossad.ui.base;

import java.awt.event.MouseListener;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 14/10/11
 * Time: 18:46
 * To change this template use File | Settings | File Templates.
 */
public interface IPanelOpenBase
{

    void updateTableModel(String projectId);
    MouseListener getTableOoElementsMouseAdapter();
}
