package org.openossad.ui.component;

import org.jdesktop.swingx.JXList;
import org.openossad.ui.base.GenericListModelHibernate;
import org.openossad.ui.panels.panelPropsOO2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 19/10/11
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public class ButtonGetActorListener implements ActionListener
   { // extends
       // AbstractAction
       public Object transferedObject = null;
       public JXList jxList=null;

       public ButtonGetActorListener(Object transferedObject) {
           this.transferedObject=transferedObject;
       }

       public void actionPerformed(ActionEvent e) {
           getDlg("SELECT Distinct Name FROM tblaccounts WHERE IsGroup=0");
       }

       public void getDlg(String Query) {

           JDialog jdlg = new JDialog();
           jdlg.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
           jdlg.setLocationRelativeTo(null);
           jdlg.setLayout(new BorderLayout());
           //jdlg.setLocationRelativeTo(null);
           jdlg.setTitle("Actores...");

           JDesktopPane jdp = new JDesktopPane();

           GenericListModelHibernate model = new GenericListModelHibernate();
           jxList = new JXList(model);
           model.connQuery = Query;
           model.loadDataFromODBC();
           JScrollPane scrollPane = new JScrollPane(jxList);
           scrollPane.setBounds(new Rectangle(1, 40, 298, 260));

           JButton btn1 = new JButton("Seleccionar");
           btn1.setBounds(new Rectangle(200, 7, 93, 26));

           btn1.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent evt) {
                   if (!jxList.isSelectionEmpty())
                   {
                       sendString();
                   }
               }
           });

           JLabel lbl1 = new JLabel();
           lbl1.setBounds(new Rectangle(10, 7, 180, 26));

           jdp.add(lbl1, null);
           jdp.add(btn1, null);
           jdp.add(scrollPane, null);

           jdlg.add("Center", jdp);

           jdlg.pack();
           jdlg.setSize(300, 300);
           jdlg.setVisible(true);


       }

       /**
        *
        */
       protected void sendString() {
           if (transferedObject instanceof panelPropsOO2)
           {
               ((panelPropsOO2) transferedObject).txt6.setText(jxList.getSelectedValue().toString());
           }

       }

   }

