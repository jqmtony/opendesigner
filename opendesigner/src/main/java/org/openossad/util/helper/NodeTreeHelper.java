package org.openossad.util.helper;

import org.openossad.data.domain.Tblgraphs;
import org.openossad.data.domain.Tblprojects;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 26/10/11
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
public class NodeTreeHelper
{

    public static TreeNode makeSampleTreeLegacy(List<Tblprojects> tblprojectsList)
    {
        Integer level = 0;
        Integer maxlevel = 0;

        DefaultMutableTreeNode treeNode = null;

        for (Tblprojects tblproject : tblprojectsList) {
            level = tblproject.getFldLevel();
            if (level > maxlevel) maxlevel = level;
            if (tblproject.getParentId().equals("000000-000000000")) {
                treeNode = new DefaultMutableTreeNode(tblproject);
            }
        }

        tblprojectsList = orderByFldLevel(tblprojectsList,maxlevel);


        for (Tblprojects tblproject : tblprojectsList) {
            treeNode = (DefaultMutableTreeNode) getParentTblprojectsNode(tblproject, treeNode,tblproject.getParentId());
        }

        final List<Tblgraphs> tblgraphsList = DAOOOSSAD.getAllTblgraphs();
        treeNode = chargeGraphsOnNode(treeNode, tblgraphsList);

        return treeNode;
    }

    public static List<Tblprojects> orderByFldLevel(List<Tblprojects> tblprojectsList, Integer maxlevel)
    {
        List<Tblprojects> tblprojectsListOrderer  = new ArrayList<Tblprojects>();
        for (Integer x= 0; x <= maxlevel  ; x++){
            for (Tblprojects p : tblprojectsList) {
                if (p.getFldLevel().equals(x)) {
                    tblprojectsListOrderer.add(p);
                }
            }
        }
        return tblprojectsListOrderer;
    }

    private static TreeNode getParentTblprojectsNode(Object userObject, DefaultMutableTreeNode treeNode, String parentId)
    {
        treeNode = (DefaultMutableTreeNode) chargeNodeInFolder(userObject, treeNode, parentId);
        Enumeration en = treeNode.children();
        while (en.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) en.nextElement();
            node = (DefaultMutableTreeNode) chargeNodeInFolder(userObject, node, parentId);
            treeNode.add(node);
        }
        return treeNode;
    }

    private static TreeNode chargeNodeInFolder(Object userObject, DefaultMutableTreeNode treeNode, String parentId)
    {
        Object treeNodeUserObject = treeNode.getUserObject();
        if (treeNodeUserObject instanceof Tblprojects) {
            Tblprojects tblprojects = (Tblprojects) treeNodeUserObject;
            if (tblprojects.getProjectId().equals(parentId)) {
                treeNode.add(new DefaultMutableTreeNode(userObject));
                return treeNode;
            }
        }
        return treeNode;
    }

    private static DefaultMutableTreeNode chargeGraphsOnNode(DefaultMutableTreeNode treeNode, List<Tblgraphs> tblgraphsList)
    {
        for (Tblgraphs tblgraph : tblgraphsList) {
            treeNode = (DefaultMutableTreeNode) getParentTblprojectsNode(tblgraph, treeNode,tblgraph.getProjectId());
        }
        return treeNode;
    }

}
