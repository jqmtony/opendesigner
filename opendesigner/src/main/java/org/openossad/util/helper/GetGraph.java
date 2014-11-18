package org.openossad.util.helper;

import org.openossad.data.domain.Tblgraphs;
import org.openossad.data.domain.Tblprojects;
import org.openossad.ui.renderers.RgraphTreeRenderer;
import org.openossad.util.GUItools;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GetGraph extends JDialog {
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JDesktopPane jDesktopPane = null;
    private JTree jTree1 = null;
    private DefaultTreeModel model;
    private JScrollPane scrollPane;

    GUItools GUI = new GUItools();
    JTextField txt1 = new JTextField();
    JButton jButton1=new JButton();
    JButton jButton2=new JButton();
    JButton jButton3=new JButton();
    //JFrame frame = new JFrame();
    JWindow panel = new JWindow();
    JLabel lbl1Props = new JLabel();
    JLabel lbl2Props = new JLabel();
    JLabel lbl3Props = new JLabel();
    JLabel lbl4Props = new JLabel();
    JLabel lbl5Props = new JLabel();
    JLabel lbl6Props = new JLabel();
    JLabel lbl7Props = new JLabel();
    public String gid;
    private Integer showOnlyGraphs;

    public GetGraph(Frame owner) {
        super(owner,true);
        initialize();
        this.setVisible(true);
    }
    public GetGraph(Frame owner,Integer op) {
        super(owner,true);

        this.showOnlyGraphs=op;

        initialize();
        this.setVisible(true);
    }


    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setResizable(false);
        this.setSize(560, 450);

        this.setLocationRelativeTo(null);
        this.setTitle("Explorador del referencial OpenOSSAD");

        this.setLayout(new BorderLayout());
        this.add(getJDesktopPane());

        this.addWindowListener(new WindowListener(){

            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                HideProps();
            }

            public void windowActivated(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            public void windowClosed(WindowEvent e) {
                // TODO Auto-generated method stub
                HideProps();

            }

            public void windowDeactivated(WindowEvent e) {
                // TODO Auto-generated method stub
                HideProps();
            }

            public void windowDeiconified(WindowEvent e) {
                // TODO Auto-generated method stub
                HideProps();
            }

            public void windowIconified(WindowEvent e) {
                // TODO Auto-generated method stub
                HideProps();
            }

            public void windowOpened(WindowEvent e) {
                // TODO Auto-generated method stub

            }


        });
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


    }


    private JDesktopPane getJDesktopPane() {
        if (jDesktopPane == null) {

            jDesktopPane = new JDesktopPane();
            jDesktopPane.setBackground(SystemColor.control);
            scrollPane = new JScrollPane(getJTree1());
            scrollPane.setBounds(new Rectangle(10, 38, 540, 300));
            jDesktopPane.add(scrollPane, null);
            jDesktopPane.add(GUI.getLblTit("Explore y elija un elemento del referencial", 10, 1, 540, 36));
            jDesktopPane.add(GUI.getLbl("Gráfico seleccionado", 15, 350, 150, 25));
            jDesktopPane.add(GUI.getTxt(txt1, 170, 350, 350, 25));
            txt1.setEditable(false);
            jDesktopPane.add(GUI.getJButton(jButton3,"/ui/images/ooGraph/01.png", 525, 350, 25, 25));
            jDesktopPane.add(GUI.getJButton(jButton1,"Cancelar", 335, 390, 100, 25));
            jDesktopPane.add(GUI.getJButton(jButton2,"OK", 450, 390, 100, 25));
            jDesktopPane.add(GUI.getDivider(jDesktopPane, 385));
            jButton1.addMouseListener(mouseListener2);
            jButton1.setName("cancel");
            jButton2.addMouseListener(mouseListener2);
            jButton2.setName("ok");
            jButton3.addMouseListener(mouseListener2);
            jButton3.setName("del");


        }
        return jDesktopPane;
    }

    MouseListener mouseListener2 = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            JButton wek = (JButton) e.getSource();
            if (wek.getName().equals("cancel")){
                txt1.setText("-1");
                dispose();
            }
            if (wek.getName().equals("ok")){
                //saveTblgraphsUpdated();
                dispose();
            }
            if (wek.getName().equals("del")){
                txt1.setText("");
            }

        }
    };

    private JTree getJTree1() {
        if (jTree1 == null) {
            List<Tblprojects> tblprojectsList = DAOOOSSAD.getAllTblprojects();
            TreeNode root = NodeTreeHelper.makeSampleTreeLegacy(tblprojectsList);
            model = new DefaultTreeModel(root);
            jTree1 = new JTree(model);
            jTree1.setEditable(false);
            RgraphTreeRenderer renderer3 = new RgraphTreeRenderer();
            jTree1.setCellRenderer(renderer3);
            jTree1.addMouseListener(treeListener);

        }
        return jTree1;
    }
    MouseListener treeListener = new MouseAdapter() {

        Object obj = null;
        DefaultMutableTreeNode dmtn;


        public void mouseClicked(MouseEvent e) {
            HideProps();
            JTree wek = (JTree) e.getSource();
            TreePath path = wek.getSelectionPath();
            if (path==null) return;
            Integer R2=0;
            dmtn = (DefaultMutableTreeNode) path.getLastPathComponent();
            obj = dmtn.getUserObject();

            if (obj instanceof Tblgraphs ){
                Tblgraphs graph = (Tblgraphs) obj;
                gid=graph.getGid();
                txt1.setText(graph.getGname());

                if (e.getClickCount()==2){
                    ShowProps(graph);
                }
            }
        }
    };


    /**
     *
     */
    protected void HideProps() {
        txt1.setText("-1");
        panel.setVisible(false);
    }

    /**
     *
     * @param graph
     */
    protected void ShowProps(Tblgraphs graph) {
        // TODO Auto-generated method stub

        panel.setSize(300,300);

        //frame.setResizable(false);
        //panel.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = this.getPreferredSize();
        panel.setLocation(screenSize.width / 2 - (labelSize.width / 2),screenSize.height / 2 - (labelSize.height / 2));
        panel.setLayout(null);

        panel.remove(GUI.lblTit);
        panel.add(GUI.getLblTit(graph.getGname(), 1, 1, 298, 35));
        panel.add(GUI.getLbl(lbl1Props,"ID: "+ graph.getGid(), 30, 40, 250, 25));
        panel.add(GUI.getLbl(lbl2Props,"Nivel: "+	graph.getGlevel(), 30, 70, 250, 25));
        panel.add(GUI.getLbl(lbl3Props,"Versión Nº: "+	graph.getGversionNumber(), 30, 100, 250, 25));
        panel.add(GUI.getLbl(lbl4Props,"Autor: "+		graph.getGauthor(), 30, 130, 250, 25));
        panel.add(GUI.getLbl(lbl5Props,"Creación: "+		graph.getGdesignDate().toString(), 30, 160, 250, 25));
        panel.add(GUI.getLbl(lbl6Props,"Modificación: "+		graph.getGupdateDate(), 30, 190, 250, 25));
        panel.add(GUI.getLbl(lbl7Props,"Keywords: "+		graph.getGkeywords(), 30, 220, 250, 25));
        panel.add(GUI.getBorder(panel));
        panel.setVisible(true);

        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                dispose();
            }
        });

    }



    public static void main(String[] args) {
        try {
            GetGraph oo1 = new GetGraph(null,1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @return
     */
    public String getID() {
        // TODO Auto-generated method stub
        return txt1.getText();
    }
    /**
     * @return
     */
    public String getGidx() {
        // TODO Auto-generated method stub
        return gid;
    }
} // @jve:decl-index=0:visual-constraint="10,10"
