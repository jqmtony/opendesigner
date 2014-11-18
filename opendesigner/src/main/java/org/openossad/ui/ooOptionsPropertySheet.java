/**
 * L2FProd.com Common Components 7.3 License.
 *
 * Copyright 2005-2007 L2FProd.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openossad.ui;

import com.l2fprod.common.propertysheet.DefaultProperty;
import com.l2fprod.common.propertysheet.Property;
import com.l2fprod.common.propertysheet.PropertySheetPanel;
import com.l2fprod.common.swing.LookAndFeelTweaks;
import com.l2fprod.common.util.ResourceManager;
import org.openossad.util.helper.DAOOOSSAD;
import org.openossad.util.helper.ReferencialOOSSAD;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

public class ooOptionsPropertySheet extends JPanel {
    private static final Class THIS_CLASS = ooOptionsPropertySheet.class;
    static ResourceManager RESOURCE = ResourceManager.get(THIS_CLASS);

    //static ResourceManager RESOURCE = ooOptionsPropertySheet.class.getResource("/ooOptionsPropertySheetRB.properties");

    // static ResourceManager RESOURCE = ResourceManager.getManager(THIS_CLASS,
    // "es_ES");
    // ResourceManager mgr = ResourceManager.getManager("MyApp");

    // ResourceManager.getManager(THIS_CLASS, "en-US")
    /*
      * try { BufferedReader in = new BufferedReader( new InputStreamReader(new
      * FileInputStream("infilename"), "UTF8")); String str = in.readLine(); }
      * catch (UnsupportedEncodingException e) { } catch (IOException e) { }
      */

    protected ReferencialOOSSAD roo = new ReferencialOOSSAD();

    public DefaultProperty GrupoDeTrabajo;
    public DefaultProperty Preferencias;
    public DefaultProperty Generacion;
    public DefaultProperty OpcionesAvanzadas;

    public Integer OId;
    public String BaseDeDatos;
    public String connDriverClass;
    public String connString;
    public String connUser;
    public String connPassword;
    public String ServidorOpenSERVER;
    public String CarpetaGallery;
    public String CarpetaModelosWord;
    public String CarpetaRelaciones;
    public String CarpetaTrabajo;
    public String CarpetaTrabajoPublico;
    public String CarpetaDifusionCreacion;
    public String CarpetaDifusionAplicable;
    public String CarpetaDifusionWeb;
    public String CarpetaImportacion;
    public Boolean ActualizarPropiedadesGrafico;
    public Boolean SolicitarPropiedadesGrafico;
    public Boolean AbrirGraficosAlInicio;
    public Boolean VisualizarIndicadorCarpeta;
    public Boolean AplicarIntegridadReferencial;
    public Integer TiempoDeGuardado;
    public Boolean ConfirmarAntesDeGuardar;
    public Boolean DocumentoGuardarPorNombre;
    public Boolean WebGuardarPorNombre;
    public Boolean ProyectoGuardarPorNombre;
    public Boolean ControlarCoherenciaSegunNivel;
    public Boolean DesencadenarAlineacionRolesAutomatica;
    public Boolean UtilizarFlechaDeAnguloRecto;
    public Boolean OrientarPaginaEnFuncionDeAlineacion;
    public Boolean SeleccionDeArchivoAlIndicarReferenciaDocumental;
    public Boolean VisualizarSugerenciasInicio;
    public Boolean ActivarConsejos;
    public Boolean ActivarPanelDeInicio;
    public Boolean ActivarSeguridad;
    public Boolean ActivarSeguridadOO;
    public Boolean ActivarSeguridadSys;

    public PropertySheetPanel sheet = new PropertySheetPanel();


    public ooOptionsPropertySheet() {


        setLayout(LookAndFeelTweaks.createVerticalPercentLayout());


        GrupoDeTrabajo = new NoReadWriteProperty();
        Preferencias = new NoReadWriteProperty();
        Generacion = new NoReadWriteProperty();
        OpcionesAvanzadas = new NoReadWriteProperty();

        GrupoDeTrabajo.setDisplayName("Grupo de Trabajo");
        Preferencias.setDisplayName("Preferencias");
        Generacion.setDisplayName("Generación");
        OpcionesAvanzadas.setDisplayName("Opciones Avanzadas");

        ReferencialOOSSAD roo = new ReferencialOOSSAD();
        roo.cargaOpenOSSADoptions(this);

        Property[] props = {
                new DF(this,"BaseDeDatos"),
                new DF(this,"connDriverClass"),
                new DF(this,"connString"),
                new DF(this,"connUser"),
                new DF(this,"connPassword"),

                new DF(this,"ServidorOpenSERVER"),
                new DF(this,"CarpetaGallery", 1),
                new DF(this,"CarpetaModelosWord", 1),
                new DF(this,"CarpetaRelaciones", 1),
                new DF(this,"CarpetaTrabajo",1),
                new DF(this,"CarpetaTrabajoPublico", 1),
                new DF(this,"CarpetaDifusionCreacion", 1),
                new DF(this,"CarpetaDifusionAplicable", 1),
                new DF(this,"CarpetaDifusionWeb", 1),
                new DF(this,"CarpetaImportacion", 1),
        };
        //GrupoDeTrabajo.addSubProperties(props);
        GrupoDeTrabajo.setValue(props);


        Property[] prefs = {
                new DF(this,"ActualizarPropiedadesGrafico", 4),
                new DF(this,"SolicitarPropiedadesGrafico", 4),
                new DF(this,"AbrirGraficosAlInicio", 4),
                new DF(this,"VisualizarIndicadorCarpeta", 4),
                new DF(this,"AplicarIntegridadReferencial", 4),
                new DF(this,"TiempoDeGuardado", 5),
                new DF(this,"ConfirmarAntesDeGuardar", 4),
                new DF(this,"NumeroDeArchivos", 5)};
        //Preferencias.addSubProperties(prefs);
        Preferencias.setValue(prefs);

        Property[] gen = {
                new DF(this,"DocumentoGuardarPorNombre", 4),
                new DF(this,"WebGuardarPorNombre", 4),
                new DF(this,"ProyectoGuardarPorNombre", 4),
                new DF(this,"ControlarCoherenciaSegunNivel", 4),
                new DF(this,"DesencadenarAlineacionRolesAutomatica", 4),
                new DF(this,"UtilizarFlechaDeAnguloRecto", 4),
                new DF(this,"OrientarPaginaEnFuncionDeAlineacion", 4),
                new DF(this,"SeleccionDeArchivoAlIndicarReferenciaDocumental", 4),
                new DF(this,"VisualizarSugerenciasInicio", 4),
                new DF(this,"ActivarConsejos", 4),
                new DF(this,"ActivarPanelDeInicio", 4)};
//		Generacion.addSubProperties(gen);
        Generacion.setValue(gen);

        Property[] ops = {
                new DF(this,"ActivarSeguridad", 4),
                new DF(this,"ActivarSeguridadOO", 4),
                new DF(this,"ActivarSeguridadSys", 4)};
//		OpcionesAvanzadas.addSubProperties(ops);
        OpcionesAvanzadas.setValue(ops);

        //sheet.setMode(PropertySheet.VIEW_AS_CATEGORIES);
        sheet.setProperties(new Property[]{GrupoDeTrabajo, Preferencias,Generacion, OpcionesAvanzadas});

        // sheet.readFromObject( data );
//		sheet.getTable().getWantsExtraIndent();

        sheet.setDescriptionVisible(true);
        sheet.setSortingCategories(true);
        sheet.setSortingProperties(true);


        add(sheet, "*");

        // everytime a property change, update the button with it

        PropertyChangeListener listener = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                Property name = (Property) evt.getSource();

                saveOptions(name);

                // JOptionPane.showMessageDialog(null,
                // prop.getValue().toString(), "Aviso",
                // JOptionPane.INFORMATION_MESSAGE);
                // prop.writeToObject( RESOURCE );
                //FileOutputStream fout;
                /*
                    String file = THIS_CLASS.getResource(
                                    "/org/openossad/opendesigner/openossad/ooOptionsPropertySheetRB.properties").getPath();
                    // System.out.println(file);

                    try {
                        //fout = new FileOutputStream(file);
                        Writer fout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file+"TEST"), "UTF8"));
                        // Print a line of text

                        String finLin="\n";
                        fout.write("opendesigner_version=2.0.3"+finLin);
                        for (Property propsFora : sheet.getProperties()) {
                            for (Property subPropsFora : propsFora.getSubProperties()) {

                                fout.write(subPropsFora
                                        .getName().toString()
                                        + ".value="
                                        + subPropsFora.getValue().toString()+finLin);
                                fout.write(subPropsFora
                                        .getName().toString()
                                        + ".cat="
                                        + subPropsFora.getCategory().toString()+finLin);
                                fout.write(subPropsFora
                                        .getName().toString()
                                        + ".desc="
                                        + subPropsFora.getShortDescription()
                                                .toString()+finLin);
                                fout.write(subPropsFora
                                        .getName().toString()
                                        + ".label="
                                        + subPropsFora.getDisplayName().toString()+finLin);
                            }
                        }
                        // Close our output stream
                        fout.close();

                    }
                    // Catches any error conditions
                    catch (IOException e) {
                        System.err.println("Unable to write to file");
                        System.exit(-1);
                    }
                    */

            }
        };
        sheet.addPropertySheetChangeListener(listener);
    }

    /**
     * @param name
     *
     */
    protected void saveOptions(Property name) {

        if (name.getName().equals("BaseDeDatos"))  this.BaseDeDatos=(name.getValue()==null) ? "" : name.getValue().toString();
        else if (name.getName().equals("connDriverClass"))  this.connDriverClass=(name.getValue()==null) ? "" : name.getValue().toString();
        else if (name.getName().equals("connString"))  this.connString=(name.getValue()==null) ? "" : name.getValue().toString();
        else if (name.getName().equals("connUser"))  this.connUser=(name.getValue()==null) ? "" : name.getValue().toString();
        else if (name.getName().equals("connPassword"))  this.connPassword=(name.getValue()==null) ? "" : name.getValue().toString();
        else if (name.getName().equals("ServidorOpenSERVER"))  this.ServidorOpenSERVER=(name.getValue()==null) ? "" : name.getValue().toString();
        else if (name.getName().equals("CarpetaGallery"))  this.CarpetaGallery=(name.getValue()==null) ? "" : name.getValue().toString();
        else if (name.getName().equals("CarpetaModelosWord"))  this.CarpetaModelosWord=(name.getValue()==null) ? "" : name.getValue().toString();
        else if (name.getName().equals("CarpetaTrabajo"))  this.CarpetaTrabajo=(name.getValue()==null) ? "" : name.getValue().toString();
        else if (name.getName().equals("CarpetaTrabajoPublico"))  this.CarpetaTrabajoPublico=(name.getValue()==null) ? "" : name.getValue().toString();
        else if (name.getName().equals("CarpetaDifusionCreacion"))  this.CarpetaDifusionCreacion=(name.getValue()==null) ? "" : name.getValue().toString();
        else if (name.getName().equals("CarpetaDifusionAplicable"))  this.CarpetaDifusionAplicable=(name.getValue()==null) ? "" : name.getValue().toString();
        else if (name.getName().equals("CarpetaDifusionWeb"))  this.CarpetaDifusionWeb=(name.getValue()==null) ? "" : name.getValue().toString();
        else if (name.getName().equals("CarpetaImportacion"))  this.CarpetaImportacion=(name.getValue()==null) ? "" : name.getValue().toString();
        else if (name.getName().equals("ActualizarPropiedadesGrafico"))  this.ActualizarPropiedadesGrafico=(Boolean) name.getValue();
        else if (name.getName().equals("SolicitarPropiedadesGrafico"))  this.SolicitarPropiedadesGrafico=(Boolean) name.getValue();
        else if (name.getName().equals("AbrirGraficosAlInicio"))  this.AbrirGraficosAlInicio=(Boolean) name.getValue();
        else if (name.getName().equals("VisualizarIndicadorCarpeta"))  this.VisualizarIndicadorCarpeta=(Boolean) name.getValue();
        else if (name.getName().equals("AplicarIntegridadReferencial"))  this.AplicarIntegridadReferencial=(Boolean) name.getValue();
        else if (name.getName().equals("TiempoDeGuardado"))  this.TiempoDeGuardado=Integer.parseInt(name.getValue().toString());
        else if (name.getName().equals("ConfirmarAntesDeGuardar"))  this.ConfirmarAntesDeGuardar=(Boolean) name.getValue();
        else if (name.getName().equals("DocumentoGuardarPorNombre"))  this.DocumentoGuardarPorNombre=(Boolean) name.getValue();
        else if (name.getName().equals("WebGuardarPorNombre"))  this.WebGuardarPorNombre=(Boolean) name.getValue();
        else if (name.getName().equals("ProyectoGuardarPorNombre"))  this.ProyectoGuardarPorNombre=(Boolean) name.getValue();
        else if (name.getName().equals("ControlarCoherenciaSegunNivel"))  this.ControlarCoherenciaSegunNivel=(Boolean) name.getValue();
        else if (name.getName().equals("DesencadenarAlineacionRolesAutomatica"))  this.DesencadenarAlineacionRolesAutomatica=(Boolean) name.getValue();
        else if (name.getName().equals("UtilizarFlechaDeAnguloRecto"))  this.UtilizarFlechaDeAnguloRecto=(Boolean) name.getValue();
        else if (name.getName().equals("OrientarPaginaEnFuncionDeAlineacion"))  this.OrientarPaginaEnFuncionDeAlineacion=(Boolean) name.getValue();
        else if (name.getName().equals("SeleccionDeArchivoAlIndicarReferenciaDocumental"))  this.SeleccionDeArchivoAlIndicarReferenciaDocumental=(Boolean) name.getValue();
        else if (name.getName().equals("VisualizarSugerenciasInicio"))  this.VisualizarSugerenciasInicio=(Boolean) name.getValue();
        else if (name.getName().equals("ActivarConsejos"))  this.ActivarConsejos=(Boolean) name.getValue();
        else if (name.getName().equals("ActivarPanelDeInicio"))  this.ActivarPanelDeInicio=(Boolean) name.getValue();
        else if (name.getName().equals("ActivarSeguridad"))  this.ActivarSeguridad=(Boolean) name.getValue();
        else if (name.getName().equals("ActivarSeguridadOO"))  this.ActivarSeguridadOO=(Boolean) name.getValue();
        else if (name.getName().equals("ActivarSeguridadSys"))  this.ActivarSeguridadSys=(Boolean) name.getValue();


        roo.guardaOpenOSSADoptions(this);

        ooOptionsProperty frame = (ooOptionsProperty) SwingUtilities.windowForComponent((Component) getParent().getParent().getParent().getParent());
        DAOOOSSAD objDAO = new DAOOOSSAD();
        frame.frame.tblOoptions = objDAO.getooOptionsOBJ();
    }

    static class NoReadWriteProperty extends DefaultProperty {
        public void readFromObject(Object object) {
        }

        public void writeToObject(Object object) {
        }
    }

    public static class DF extends DefaultProperty {
        public DF(ooOptionsPropertySheet cl,String name, Integer clase) {
            setName(name);
            setCategory(RESOURCE.getString(name + ".cat"));
            setDisplayName(RESOURCE.getString(name + ".label"));
            setShortDescription(RESOURCE.getString(name + ".desc"));
            if (name.equals("CarpetaGallery"))  setValue(cl.CarpetaGallery);
            else if (name.equals("CarpetaModelosWord"))  setValue(cl.CarpetaModelosWord);
            else if (name.equals("CarpetaTrabajo"))  setValue(cl.CarpetaTrabajo);
            else if (name.equals("CarpetaTrabajoPublico"))  setValue(cl.CarpetaTrabajoPublico);
            else if (name.equals("CarpetaDifusionCreacion"))  setValue(cl.CarpetaDifusionCreacion);
            else if (name.equals("CarpetaDifusionAplicable"))  setValue(cl.CarpetaDifusionAplicable);
            else if (name.equals("CarpetaDifusionWeb"))  setValue(cl.CarpetaDifusionWeb);
            else if (name.equals("CarpetaImportacion"))  setValue(cl.CarpetaImportacion);
            else if (name.equals("ActualizarPropiedadesGrafico"))  setValue(cl.ActualizarPropiedadesGrafico);
            else if (name.equals("SolicitarPropiedadesGrafico"))  setValue(cl.SolicitarPropiedadesGrafico);
            else if (name.equals("AbrirGraficosAlInicio"))  setValue(cl.AbrirGraficosAlInicio);
            else if (name.equals("VisualizarIndicadorCarpeta"))  setValue(cl.VisualizarIndicadorCarpeta);
            else if (name.equals("AplicarIntegridadReferencial"))  setValue(cl.AplicarIntegridadReferencial);
            else if (name.equals("TiempoDeGuardado"))  setValue(cl.TiempoDeGuardado);
            else if (name.equals("ConfirmarAntesDeGuardar"))  setValue(cl.ConfirmarAntesDeGuardar);
            else if (name.equals("DocumentoGuardarPorNombre"))  setValue(cl.DocumentoGuardarPorNombre);
            else if (name.equals("WebGuardarPorNombre"))  setValue(cl.WebGuardarPorNombre);
            else if (name.equals("ProyectoGuardarPorNombre"))  setValue(cl.ProyectoGuardarPorNombre);
            else if (name.equals("ControlarCoherenciaSegunNivel"))  setValue(cl.ControlarCoherenciaSegunNivel);
            else if (name.equals("DesencadenarAlineacionRolesAutomatica"))  setValue(cl.DesencadenarAlineacionRolesAutomatica);
            else if (name.equals("UtilizarFlechaDeAnguloRecto"))  setValue(cl.UtilizarFlechaDeAnguloRecto);
            else if (name.equals("OrientarPaginaEnFuncionDeAlineacion"))  setValue(cl.OrientarPaginaEnFuncionDeAlineacion);
            else if (name.equals("SeleccionDeArchivoAlIndicarReferenciaDocumental"))  setValue(cl.SeleccionDeArchivoAlIndicarReferenciaDocumental);
            else if (name.equals("VisualizarSugerenciasInicio"))  setValue(cl.VisualizarSugerenciasInicio);
            else if (name.equals("ActivarConsejos"))  setValue(cl.ActivarConsejos);
            else if (name.equals("ActivarPanelDeInicio"))  setValue(cl.ActivarPanelDeInicio);
            else if (name.equals("ActivarSeguridad"))  setValue(cl.ActivarSeguridad);
            else if (name.equals("ActivarSeguridadOO"))  setValue(cl.ActivarSeguridadOO);
            else if (name.equals("ActivarSeguridadSys"))  setValue(cl.ActivarSeguridadSys);
            else setValue("");


            if (clase == 1)
                //setType(File.class);
                setType(String.class);
            if (clase == 2)
                setType(Color.class);
            if (clase == 3)
                setType(Date.class);
            if (clase == 4)
                setType(Boolean.class);
            if (clase == 5)
                setType(Integer.class);

        }

        public DF(ooOptionsPropertySheet cl,String name) {
            new DF( cl, name, 1);
        }

        public void writeToRESOURCE() {

        }
    }

    private void guarda() {

    }

}