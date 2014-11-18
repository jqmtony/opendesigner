package org.openossad.util.helper;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.openossad.data.dao.HibernateDAOFactory;
import org.openossad.data.domain.*;
import org.openossad.ui.component.JXErrorDialog;
import org.openossad.ui.ooOptionsPropertySheet;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReferencialOOSSAD {

    public static String EXT = "ood";
    Object[][] MyArray = null;
    String[] AttributeNames;
    String state;
    int NumberOfRows, NumberOfColumns;

    public static String Serial="OPS000";
    public static OoOptions options;

    private static HibernateDAOFactory DAOFactory = new HibernateDAOFactory();
    private static Session currentSession = DAOFactory.getCurrentSession();

    public ReferencialOOSSAD() {

    }
    static {
        options = DAOOOSSAD.getooOptionsOBJ();

    }

    public Integer Login(String u, String p) {
//		connQuery = "SELECT Type FROM tblaccounts WHERE Name = '" + u
//		+ "' AND Password='" + p + "' AND IsGroup=0 ";
//		String wek = "";
//		try {
//			if (conn.isClosed()) {
//				Class.forName(connDriverClass).newInstance();
//				conn = DriverManager.getConnection(connString, connUser,connPassword);
//			}
//			stmt = conn.createStatement();
//
//			ResultSet res = stmt.executeQuery(connQuery);
//			while (res.next()) {
//				wek = res.getObject(1).toString();
//			}
//			res.close();
//			conn.close();
//		} catch (Exception e) {
//			JXErrorDialog.showDialog(null, "Fallo en la conexion", e);
//
//		}
//		if (wek.equals(""))
//			return 0;
//		return Integer.parseInt(wek);
        return 1;
    }

    public static String getFilePathFromGId(String graphId) {
        return getFilePathFromGId(graphId, options.getCarpetaTrabajo());
    }

    public static String getFilePathFromGId(String graphId,String carpetaTrabajo) {
        File f = new File(carpetaTrabajo);
        if (!f.exists()) f=new File(System.getProperty("user.dir") + "/referencial");
        String serial = "";
        String id ="";
        if (graphId.contains("-")) {
            int endIndex = graphId.indexOf("-");
            serial=graphId.substring(0, endIndex);
            id=graphId.substring(endIndex+1,graphId.length());
        } else {
            serial = Serial;
            id=graphId;
        }
        final String graphFilePath = f.getAbsolutePath() + "/" + serial + "/" + id + "." + EXT;
        return graphFilePath;
    }



    public static String getLastIDTblgraph() {
        return Serial + "-" + getCounter("GID");
    }
    public static String getLastIDIndic() {
        return Serial + "-" + getCounter("IID");
    }
    public static String getLastIDEntity() {
        return Serial + "-" + getCounter("EID");
    }
    public String getLastIDAccount() {
        return Serial + "-" + getCounter("UID");
    }



    public static String getCounter(String CType) {

        Integer lastIdFile = DAOOOSSAD.getCounterByCType(CType);
        lastIdFile++;
        OoCounters ooCounters = DAOOOSSAD.updateCounters(lastIdFile,CType);

        String newID = "000000000" + ooCounters.getCount();
        newID = newID.substring(newID.length() - 9, newID.length());
        return newID;
    }





    public static String getParsedModel(String modelStr,String carpetaTrabajo) {
        String FOLDER = modelStr.substring(0, modelStr.lastIndexOf('-'));
        String MODELID = modelStr.substring(modelStr.lastIndexOf('-') + 1);
        String res=carpetaTrabajo.replace("\\", "/")  + "/" + FOLDER + "/" + MODELID + "." + EXT;
        res=res.replace("/", File.separator);
        res=res.replace("\\", File.separator);
        return res;
    }


    public static String getParsedModel(String modelStr) {
        return getParsedModel(modelStr,options.getCarpetaTrabajo());
    }


    public static String getParsedModelDoc(String modelStr) {
        DAOOOSSAD DAO = new DAOOOSSAD();
        Tblgraphs tbg = DAO.getooGraphOBJ(modelStr);
        if (tbg.getGlevel()==4 && !(tbg.getGlinkDoc().lastIndexOf(File.separator)>0) ) {
            return getParsedModelDoc(modelStr,tbg.getGlinkDoc());
        }
        else  { return ""; } //

    }

    public static String getParsedModelDoc(String gid,String GLinkDoc) {
        String res;
        res=GLinkDoc.replace("/", File.separator);
        res=GLinkDoc.replace("\\", File.separator);
        if (res.lastIndexOf(File.separator)>0) return res;
        String ext = res.substring(res.lastIndexOf(".")+1,res.length());
        String FOLDER = gid.substring(0, gid.lastIndexOf('-'));
        String MODELID = gid.substring(gid.lastIndexOf('-') + 1);
        File f=new File(options.getCarpetaTrabajo());
        if (!f.exists()) f=new File(System.getProperty("user.dir") + "/referencial");
        res=f.getAbsolutePath().replace("\\", "/")  + "/" + FOLDER + "/" + MODELID + "." + ext;
        res=res.replace("/", File.separator);
        res=res.replace("\\", File.separator);
        return res;
    }
    public String getParsedModelVersion(String modelStr,String version) {
        String FOLDER = modelStr.substring(0, modelStr.lastIndexOf('-'));
        String MODELID = modelStr.substring(modelStr.lastIndexOf('-') + 1);
        File f=new File(options.getCarpetaTrabajo());
        if (!f.exists()) f=new File(System.getProperty("user.dir") + "/referencial");
        String res= f.getAbsolutePath() + "/"+ FOLDER + "/"+version+"-" + MODELID + "." + EXT;
        res=res.replace("/", File.separator);
        res=res.replace("\\", File.separator);
        return res;
    }
    public String getExportHTML(String modelStr) {
        String FOLDER = modelStr.substring(0, modelStr.lastIndexOf('-'));
        String MODELID = modelStr.substring(modelStr.lastIndexOf('-') + 1);

        //System.out.println(System.getProperty("user.dir") + "/referencial"+"/DIF_WEB");

        File f=new File(options.getCarpetaDifusionWeb().toString() );
        if (!f.exists()) {
            f= new File(System.getProperty("user.dir") + "/referencial"+"/DIF_WEB");
            if (!f.exists()) {
                JOptionPane.showMessageDialog(
                        null,
                        "No tiene definida ninguna carpeta de exportación o la.\n" +
                        "que esta indicada en las opciones es incorrecta.\n" +
                        "Por favor, configure la carpeta de exportación o consulte\n" +
                        "con su administrador.", "Repository:",
                        JOptionPane.ERROR_MESSAGE);
                return "";
            }
        }

        String res=f.getAbsolutePath() + "/"+ FOLDER + "/" + MODELID + ".html";
        res=res.replace("/", File.separator);
        res=res.replace("\\", File.separator);
        Boolean creaFH=CreateFolderWEBRepository(res);
        if (creaFH) JOptionPane.showMessageDialog(
                null,
                "La carpeta para la exportación ha sido creada. \n"
                + res, "Repository:",
                JOptionPane.INFORMATION_MESSAGE);
        return res;
    }


    public String getIDfromFullName(String FullName) {

        return FullName
                .substring(FullName.length() - 13, FullName.length() - 4);
    }

    public String getGIDfromFile(String p){
        if (p.equals("")) return "";
        p=p.substring(p.lastIndexOf(".")-16,p.lastIndexOf("."));
        return p.substring(0,6)+"-"+p.substring(7,16);
    }

    public static Boolean CreateFolderRepository(String LastIDFile) {

        String LastIDFileFolder = LastIDFile.substring(0,LastIDFile.length() - 14);
        File f = new File(LastIDFileFolder);
        if (f.exists())
        {
            return false;
        }
        try {
            boolean success = f.mkdir();

        } catch (Exception e) {// Catch exception if any
            JXErrorDialog.showDialog(null, "Fallo en la conexion", e);
            return false;
        }

        return true;
    }

    public Boolean CreateFolderWEBRepository(String LastIDFile) {

        String LastIDFileFolder = LastIDFile.substring(0,
                                                       LastIDFile.length() - 14);
        File f = new File(LastIDFileFolder);
        if (f.exists())
            return false;
        try {
            boolean success = f.mkdir();
            if (success)
                System.out.println("Directory: " + LastIDFileFolder
                                   + " created");
        } catch (Exception e) {// Catch exception if any
            JXErrorDialog.showDialog(null, "Fallo en la conexion", e);
            return false;
        }

        return true;
    }

    public static Integer exeUpdate(String query) {
        currentSession.beginTransaction();
        Integer ok = currentSession.createSQLQuery(query).executeUpdate();
        return ok;
    }

    public static void exeDELETE(final String query, String FileOO) {
        try {


            String FileOO2 = getParsedModelDoc(FileOO);
            if (!FileOO2.equals("")) {
                File wek = new File(FileOO2);
                Boolean success = wek.delete();
                if (!success) {
                    JOptionPane
                            .showMessageDialog(
                                    null,
                                    "Error interno de repositorio borrando archivo. \nConsulte con su administrador \n"
                                    + FileOO, "Repository:",
                                    JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                                                  "¡ Elemento eliminado con éxito !", "Repository:",
                                                  JOptionPane.INFORMATION_MESSAGE);
                }
            }

            exeDELETE(query);


        } catch (Exception e) {
            JXErrorDialog.showDialog(null, "Fallo en la conexion", e);
        }

    }

    public static void exeDELETE(String query) {
        currentSession.beginTransaction();
        currentSession.createSQLQuery(query).executeUpdate();
    }

    public static String dateformat() {
        String DATE_FORMAT = "yyyy-MM-dd H:m:s";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance(); // today
        return sdf.format(c1.getTime());
    }

    public String dateDIFformat() {
        String DATE_FORMAT = "yy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance(); // today
        return sdf.format(c1.getTime());
    }

    public static Boolean CreateGraphInREP(String fromFileName, String toFileName) throws IOException {

        CreateFolderRepository(toFileName);
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);

        if (!fromFile.exists()) {
            // throw new IOException("FileCopy: " + "no such source file: " +
            // fromFileName);
            JOptionPane.showMessageDialog(null, "no such source file: " + fromFileName, "Repository:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!fromFile.isFile()) {
            // throw new IOException("FileCopy: " + "can't copy directory: "+
            // fromFileName);
            JOptionPane.showMessageDialog(null, "can't copy directory: "
                                                + fromFileName, "Repository:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!fromFile.canRead()) {
            // throw new IOException("FileCopy: " +
            // "source file is unreadable: " + fromFileName);
            JOptionPane.showMessageDialog(null, "source file is unreadable: "
                                                + fromFileName, "Repository:", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (toFile.isDirectory())
            toFile = new File(toFile, fromFile.getName());

        if (toFile.exists()) {
            if (!toFile.canWrite()) {
                // throw new IOException("FileCopy: " +
                // "destination file is unwriteable: " + toFileName);
                JOptionPane.showMessageDialog(null,
                                              "destination file is unwriteable: " + fromFileName,
                                              "Repository:", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            Integer response = JOptionPane.showConfirmDialog(null,
                                                             "Overwrite existing file ? " + toFileName, "Repository:",
                                                             JOptionPane.ERROR_MESSAGE);
            // System.out.print("Overwrite existing file " + toFile.getName()+
            // "? (Y/N): ");
            // System.out.flush();
            // BufferedReader in = new BufferedReader(new
            // InputStreamReader(System.in));
            // String response = in.readLine();

            /*
                * if (!response.equals("Y") && !response.equals("y")) throw new
                * IOException("FileCopy: " + "existing file was not overwritten.");
                * }
                */

            if (response == 1) {
                return false;
            } else {
                String parent = toFile.getParent();
                if (parent == null)
                    parent = System.getProperty("user.dir") + "/referencial";
                File dir = new File(parent);
                if (!dir.exists()) {
                    // throw new IOException("FileCopy: " +
                    // "destination directory doesn't exist: " + parent);
                    JOptionPane.showMessageDialog(null,
                                                  "destination directory doesn't exist: " + parent,
                                                  "Repository:", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (dir.isFile()) {
                    // throw new IOException("FileCopy: " +
                    // "destination is not a directory: " + parent);
                    JOptionPane.showMessageDialog(null,
                                                  "destination is not a directory: " + parent,
                                                  "Repository:", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (!dir.canWrite()) {
                    // throw new IOException("FileCopy: " +
                    // "destination directory is unwriteable: " + parent);
                    JOptionPane.showMessageDialog(null,
                                                  "destination directory is unwriteable: " + parent,
                                                  "Repository:", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

            }
        }
        FileInputStream from = null;
        FileOutputStream to = null;
        try {
            from = new FileInputStream(fromFile);
            to = new FileOutputStream(toFile);
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = from.read(buffer)) != -1)
                to.write(buffer, 0, bytesRead); // write
        } finally {
            if (from != null)
                try {
                    from.close();
                } catch (IOException e) {
                    JXErrorDialog.showDialog(null, "Fallo en la conexion", e);

                }
            if (to != null)
                try {
                    to.close();
                } catch (IOException e) {
                    JXErrorDialog.showDialog(null, "Fallo en la conexion", e);
                }

            return true;
        }

    }

    public String getLevelandOrientation(String idSelected) {
//		//XHF: detecta si es in GID o un archivo
//		File f = new File(idSelected);
//		if (f.exists()) idSelected=getGIDfromFile(idSelected);
//		///
//		connQuery = "SELECT GOrientation,GLevel FROM tblgraphs WHERE GId = '"+idSelected+"'";
//		String res1 = "0";
//		String res2 = "0";
//		try {
//			if (conn.isClosed()) {
//				Class.forName(connDriverClass).newInstance();
//				conn = DriverManager.getConnection(connString, connUser,
//						connPassword);
//			}
//			stmt = conn.createStatement();
//
//			ResultSet res = stmt.executeQuery(connQuery);
//			while (res.next()) {
//				res1 = res.getObject(1).toString();
//				res2 = res.getObject(2).toString();
//			}
//			res.close();
//			conn.close();
//		} catch (Exception e) {
//			JXErrorDialog.showDialog(null, "Bad connection: ", e);
//
//		}

        //return res1 + "^" + res2;
        return "";

    }

    public String getData4Marco(String idSelected) {
        //XHF: detecta si es in GID o un archivo
        //File f = new File(idSelected);
        //if (f.exists()) idSelected=getGIDfromFile(idSelected);
        ///
//		connQuery = "SELECT GName,GLevel,GVersionNumber,GRef,GGraphState FROM tblgraphs WHERE GId = '"+idSelected+"'";
//		String res1 = null,res2 = null,res3 = null,res4 = null,res5 = null;
//		try {
//			if (conn.isClosed()) {
//				Class.forName(connDriverClass).newInstance();
//				conn = DriverManager.getConnection(connString, connUser,
//						connPassword);
//			}
//			stmt = conn.createStatement();
//
//			ResultSet res = stmt.executeQuery(connQuery);
//			while (res.next()) {
//				res1 = (res.getObject(1)==null) ? "" : res.getObject(1).toString();
//				res2 = (res.getObject(2)==null) ? "" : res.getObject(2).toString();
//				res3 = (res.getObject(3)==null) ? "" : res.getObject(3).toString();
//				res4 = (res.getObject(4)==null) ? "" : res.getObject(4).toString();
//				res5 = (res.getObject(5).toString().equals("0")) ? "En creación" : "Aplicable";
//			} //GName,GLevel,GVersionNumber,GRef,GIsOK
//			res.close();
//			conn.close();
//		} catch (Exception e) {
//			JXErrorDialog.showDialog(null, "Bad Connection", e);
//
//		}
//
//		return res1 + "^" + res2 + "^" + res3 + "^" + res4 + "^" + res5;
        return "";
    }

    /**
     "GId,GName,GRef,GLevel,GOrientation,GTemplate,GQgrCaptions,GVisPublic," +
     "ProjectID,GIsOK,GVersionNumber,GIndice,GNews,GAuthor,GDesignDate,GUpdateDate," +
     "GVerifNames,GApprobNames,GLinkDoc,GObject,GDomain,GAbrList,GDifList," +
     "GKeywords,GField1,GField2,GField3,GIsModel,GHtmlGen,GDocGen,GDifDate,Icone," +
     "Gtype,GOnlyLink,GGraphState" +
     */
//	public void cargaOpenOSSADgraph(ooPropsGenericDlg ooPropsDlg,String GId) {
//		// TODO Auto-generated method stub
//		connQuery = "SELECT * FROM tblgraphs WHERE GId = '"+GId+"'";
//		try {
//			if (conn.isClosed()) {
//				Class.forName(connDriverClass).newInstance();
//				conn = DriverManager.getConnection(connString, connUser,
//						connPassword);
//			}
//			stmt = conn.createStatement();
//
//			ResultSet res = stmt.executeQuery(connQuery);
//			while (res.next()) {
//				ooPropsDlg.GId=(res.getObject(1).toString()==null) ? "" :res.getObject(1).toString();
//				ooPropsDlg.GName=(res.getObject(2).toString()==null) ? "" :res.getObject(2).toString();
//				ooPropsDlg.GRef=(res.getObject(3).toString()==null) ? "" :res.getObject(3).toString();
//				ooPropsDlg.GLevel=(res.getObject(4).toString()==null) ? "" :res.getObject(4).toString();
//				ooPropsDlg.GOrientation=(res.getObject(5).toString()==null) ? "" :res.getObject(5).toString();
//				ooPropsDlg.GTemplate=(res.getObject(6).toString()==null) ? "" :res.getObject(6).toString();
//				ooPropsDlg.GQgrCaptions=(res.getObject(7).toString()==null) ? "" :res.getObject(7).toString();
//				ooPropsDlg.GVisPublic=(Boolean) ((res.getObject(8)==null) ? false :res.getObject(8));
//				ooPropsDlg.ProjectID=(res.getObject(9).toString()==null) ? "" :res.getObject(9).toString();
//				ooPropsDlg.GIsOK=(res.getObject(10).toString()==null) ? "" :res.getObject(10).toString();
//				ooPropsDlg.GVersionNumber=(res.getObject(11)==null) ? "" :res.getObject(11).toString();
//				ooPropsDlg.GIndice=(res.getObject(12)==null) ? "" :res.getObject(12).toString();
//				ooPropsDlg.GNews=(res.getObject(13)==null) ? "" :res.getObject(13).toString();
//				ooPropsDlg.GAuthor=(res.getObject(14)==null) ? "" :res.getObject(14).toString();
//				ooPropsDlg.GDesignDate=(res.getObject(15)==null) ? "" :res.getObject(15).toString();
//				ooPropsDlg.GUpdateDate=(res.getObject(16)==null) ? "" :res.getObject(16).toString();
//				ooPropsDlg.GVerifNames=(res.getObject(17)==null) ? "" :res.getObject(17).toString();
//				ooPropsDlg.GApprobNames=(res.getObject(18)==null) ? "" :res.getObject(18).toString();
//				ooPropsDlg.GLinkDoc=(res.getObject(19)==null) ? "" :res.getObject(19).toString();
//				ooPropsDlg.GObject=(res.getObject(20)==null) ? "" :res.getObject(20).toString();
//				ooPropsDlg.GDomain=(res.getObject(21)==null) ? "" :res.getObject(21).toString();
//				ooPropsDlg.GAbrList=(res.getObject(22)==null) ? "" :res.getObject(22).toString();
//				ooPropsDlg.GDifList=(res.getObject(23)==null) ? "" :res.getObject(23).toString();
//				ooPropsDlg.GKeywords=(res.getObject(24)==null) ? "" :res.getObject(24).toString();
//				ooPropsDlg.GField1=(res.getObject(25)==null) ? "" :res.getObject(25).toString();
//				ooPropsDlg.GField2=(res.getObject(26)==null) ? "" :res.getObject(26).toString();
//				ooPropsDlg.GField3=(res.getObject(27)==null) ? "" :res.getObject(27).toString();
//				ooPropsDlg.GIsModel=(Boolean) ((res.getObject(28)==null) ? false :res.getObject(28));
//				ooPropsDlg.GHtmlGen=(Boolean) ((res.getObject(29)==null) ? false :res.getObject(29));
//				ooPropsDlg.GDocGen=(res.getObject(30)==null) ? "" :res.getObject(30).toString();
//				ooPropsDlg.GDifDate=(res.getObject(31)==null) ? "" :res.getObject(31).toString();
//				ooPropsDlg.Icone=(res.getObject(32)==null) ? "" :res.getObject(32).toString();
//				ooPropsDlg.Gtype=(res.getObject(33)==null) ? "" :res.getObject(33).toString();
//				ooPropsDlg.GOnlyLink=(Boolean) ((res.getObject(34)==null) ? false :res.getObject(34));
//				ooPropsDlg.GGraphState=(res.getObject(35)==null) ? "" :res.getObject(35).toString();
//			}
//			res.close();
//			conn.close();
//		} catch (Exception e) {
//			JXErrorDialog.showDialog(null, "Fallo en la conexion", e);
//		}
//
//		return ;
//	}
    /**
     "GId,GName,GRef,GLevel,GOrientation,GTemplate,GQgrCaptions,GVisPublic," +
     "ProjectID,GIsOK,GVersionNumber,GIndice,GNews,GAuthor,GDesignDate,GUpdateDate," +
     "GVerifNames,GApprobNames,GLinkDoc,GObject,GDomain,GAbrList,GDifList," +
     "GKeywords,GField1,GField2,GField3,GIsModel,GHtmlGen,GDocGen,GDifDate,Icone," +
     "Gtype,GOnlyLink,GGraphState" +
     */
//	public Boolean guardaOpenOSSADgraph(ooPropsGenericDlg ooPropsDlg) {
//
//		// TODO Auto-generated method stub
//		/*
//		"( GId,GName,GRef,GLevel,GOrientation,GTemplate,GQgrCaptions,GVisPublic," +
//		"ProjectID,GIsOK,GVersionNumber,GIndice,GNews,GAuthor,GDesignDate,GUpdateDate," +
//		"GVerifNames,GApprobNames,GLinkDoc,GObject,GDomain,GAbrList,GDifList," +
//		"GKeywords,GField1,GField2,GField3,GIsModel,GHtmlGen,GDocGen,GDifDate,Icone," +
//		"Gtype,GOnlyLink,GGraphState )  " +
//		"VALUES (" +
//		*/
//		connQuery = "UPDATE tblgraphs SET " +
//		"GName='"+ooPropsDlg.GName+"',"+                              //2).toString()==null) ? "" :res.getObject(2).toString();
//		"GRef='"+ooPropsDlg.GRef+"',"+                              //3).toString()==null) ? "" :res.getObject(3).toString();
//		"GLevel='"+ooPropsDlg.GLevel+"',"+                              //4).toString()==null) ? "" :res.getObject(4).toString();
//		"GOrientation='"+ooPropsDlg.GOrientation+"',"+                              //5).toString()==null) ? "" :res.getObject(5).toString();
//		"GTemplate='"+ooPropsDlg.GTemplate+"',"+                              //6).toString()==null) ? "" :res.getObject(6).toString();
//		"GQgrCaptions='"+ooPropsDlg.GQgrCaptions+"',"+                              //7).toString()==null) ? "" :res.getObject(7).toString();
//		"GVisPublic="+ooPropsDlg.GVisPublic+","+                              //8).toString()==null) ? "" :res.getObject(8).toString();
//		"ProjectID='"+ooPropsDlg.ProjectID+"',"+                              //9).toString()==null) ? "" :res.getObject(9).toString();
//		"GIsOK="+ooPropsDlg.GIsOK+","+                              //10).toString()==null) ? "" :res.getObject(10).toString();
//		"GVersionNumber='"+ooPropsDlg.GVersionNumber+"',"+                              //11)==null) ? "" :res.getObject(11).toString();
//		"GIndice='"+ooPropsDlg.GIndice+"',"+                              //12)==null) ? "" :res.getObject(12).toString();
//		"GNews='"+ooPropsDlg.GNews+"',"+                              //13)==null) ? "" :res.getObject(13).toString();
//		"GAuthor='"+ooPropsDlg.GAuthor+"',"+                              //14)==null) ? "" :res.getObject(14).toString();
//		"GDesignDate='"+ooPropsDlg.GDesignDate+"',"+                              //15)==null) ? "" :res.getObject(15).toString();
//		"GUpdateDate='"+ooPropsDlg.GUpdateDate+"',"+                              //16)==null) ? "" :res.getObject(16).toString();
//		"GVerifNames='"+ooPropsDlg.GVerifNames+"',"+                              //17)==null) ? "" :res.getObject(17).toString();
//		"GApprobNames='"+ooPropsDlg.GApprobNames+"',"+                              //18)==null) ? "" :res.getObject(18).toString();
//		"GLinkDoc='"+ooPropsDlg.GLinkDoc+"',"+                              //19)==null) ? "" :res.getObject(19).toString();
//		"GObject='"+ooPropsDlg.GObject+"',"+                              //20)==null) ? "" :res.getObject(20).toString();
//		"GDomain='"+ooPropsDlg.GDomain+"',"+                              //21)==null) ? "" :res.getObject(21).toString();
//		"GAbrList='"+ooPropsDlg.GAbrList+"',"+                              //22)==null) ? "" :res.getObject(22).toString();
//		"GDifList='"+ooPropsDlg.GDifList+"',"+                              //23)==null) ? "" :res.getObject(23).toString();
//		"GKeywords='"+ooPropsDlg.GKeywords+"',"+                              //24)==null) ? "" :res.getObject(24).toString();
//		"GField1='"+ooPropsDlg.GField1+"',"+                              //25)==null) ? "" :res.getObject(25).toString();
//		"GField2='"+ooPropsDlg.GField2+"',"+                              //26)==null) ? "" :res.getObject(26).toString();
//		"GField3='"+ooPropsDlg.GField3+"',"+                              //27)==null) ? "" :res.getObject(27).toString();
//		"GIsModel="+ooPropsDlg.GIsModel+","+                              //28)==null) ? "" :res.getObject(28).toString();
//		"GHtmlGen="+ooPropsDlg.GHtmlGen+","+                              //29)==null) ? "" :res.getObject(29).toString();
//		"GDocGen="+ooPropsDlg.GDocGen+","+                              //30)==null) ? "" :res.getObject(30).toString();
//		"GDifDate='"+ooPropsDlg.GDifDate+"',"+                              //31)==null) ? "" :res.getObject(31).toString();
//		"Icone='"+ooPropsDlg.Icone+"',"+                              //32)==null) ? "" :res.getObject(32).toString();
//		"Gtype='"+ooPropsDlg.Gtype+"',"+                              //33)==null) ? "" :res.getObject(33).toString();
//		"GOnlyLink="+ooPropsDlg.GOnlyLink+","+                              //34)==null) ? "" :res.getObject(34).toString();
//		"GGraphState='"+ooPropsDlg.GGraphState+"' " +
//		" WHERE GId='"+ooPropsDlg.GId+"'";                              //35)==null) ? "" :res.getObject(35).toString();
//
//
//		//System.out.println(connQuery);
//		exeDELETE(connQuery);
//
//		return true;
//
//	}

    public void GenerarVersion(String GId,String version) {

        try {
            CreateGraphInREP(getParsedModel(GId),getParsedModelVersion(GId,version));
            String connQuery="INSERT INTO atblgraphs " +
                             "( SELECT "+version+",'" +dateformat()+ "',"+
                             "GId,GName,GRef,GLevel,GOrientation,GTemplate,GQgrCaptions,GVisPublic," +
                             "ProjectID,GVersionNumber,GNews,GIndice,GAuthor,GDesignDate,GUpdateDate," +
                             "GVerifNames,GApprobNames,GLinkDoc,GObject,GDomain,GAbrList,GDifList,GKeywords," +
                             "GField1,GField2,GField3,GIsModel,GIsOK,GHtmlGen,GDocGen,GDifDate,Icone,Gtype," +
                             "GOnlyLink FROM tblgraphs " +
                             " WHERE GId='"+GId+"')";
            exeDELETE(connQuery);


        } catch (IOException e) {
            JXErrorDialog.showDialog(null, "Bad Connection", e);
        }




    }

    public void cargaOpenOSSADoptions(ooOptionsPropertySheet ooOptions) {

//			while (res.next()) {
//
//			ooOptions.OId=(res.getObject(1)==null) ? 0 : Integer.parseInt(res.getObject(1).toString());
//			ooOptions.BaseDeDatos=(res.getObject(2)==null) ? "" :res.getObject(2).toString();
//			ooOptions.connDriverClass=(res.getObject(3)==null) ? "" :res.getObject(3).toString();
//			ooOptions.connString=(res.getObject(4)==null) ? "" :res.getObject(4).toString();
//			ooOptions.connUser=(res.getObject(5)==null) ? "" :res.getObject(5).toString();
//			ooOptions.connPassword=(res.getObject(6)==null) ? "" :res.getObject(6).toString();
//			ooOptions.ServidorOpenSERVER=(res.getObject(7)==null) ? "" :res.getObject(7).toString();
//			ooOptions.CarpetaGallery=(res.getObject(8)==null) ? "" :res.getObject(8).toString();
//			ooOptions.CarpetaModelosWord=(res.getObject(9)==null) ? "" :res.getObject(9).toString();
//			ooOptions.CarpetaRelaciones=(res.getObject(10)==null) ? "" :res.getObject(10).toString();
//			ooOptions.CarpetaTrabajo=(res.getObject(11)==null) ? "" :res.getObject(11).toString();
//			ooOptions.CarpetaTrabajoPublico=(res.getObject(12)==null) ? "" :res.getObject(12).toString();
//			ooOptions.CarpetaDifusionCreacion=(res.getObject(13)==null) ? "" :res.getObject(13).toString();
//			ooOptions.CarpetaDifusionAplicable=(res.getObject(14)==null) ? "" :res.getObject(14).toString();
//			ooOptions.CarpetaDifusionWeb=(res.getObject(15)==null) ? "" :res.getObject(15).toString();
//			ooOptions.CarpetaImportacion=(res.getObject(16)==null) ? "" :res.getObject(16).toString();
//
//			ooOptions.ActualizarPropiedadesGrafico=(Boolean) ((res.getObject(17)==null) ? false :res.getObject(17));
//			ooOptions.SolicitarPropiedadesGrafico=(Boolean) ((res.getObject(18)==null) ? false :res.getObject(18));
//			ooOptions.AbrirGraficosAlInicio=(Boolean) ((res.getObject(19)==null) ? false :res.getObject(19));
//			ooOptions.VisualizarIndicadorCarpeta=(Boolean) ((res.getObject(20)==null) ? false :res.getObject(20));
//			ooOptions.AplicarIntegridadReferencial=(Boolean) ((res.getObject(21)==null) ? false :res.getObject(21));
//			ooOptions.TiempoDeGuardado=(res.getObject(22)==null) ? 0 : Integer.parseInt(res.getObject(22).toString());
//			ooOptions.ConfirmarAntesDeGuardar=(Boolean) ((res.getObject(23)==null) ? false :res.getObject(23));
//			ooOptions.DocumentoGuardarPorNombre=(Boolean) ((res.getObject(24)==null) ? false :res.getObject(24));
//			ooOptions.WebGuardarPorNombre=(Boolean) ((res.getObject(25)==null) ? false :res.getObject(25));
//			ooOptions.ProyectoGuardarPorNombre=(Boolean) ((res.getObject(26)==null) ? false :res.getObject(26));
//			ooOptions.ControlarCoherenciaSegunNivel=(Boolean) ((res.getObject(27)==null) ? false :res.getObject(27));
//			ooOptions.DesencadenarAlineacionRolesAutomatica=(Boolean) ((res.getObject(28)==null) ? false :res.getObject(28));
//			ooOptions.UtilizarFlechaDeAnguloRecto=(Boolean) ((res.getObject(29)==null) ? false :res.getObject(29));
//			ooOptions.OrientarPaginaEnFuncionDeAlineacion=(Boolean) ((res.getObject(30)==null) ? false :res.getObject(30));
//			ooOptions.SeleccionDeArchivoAlIndicarReferenciaDocumental=(Boolean) ((res.getObject(31)==null) ? false :res.getObject(31));
//			ooOptions.VisualizarSugerenciasInicio=(Boolean) ((res.getObject(32)==null) ? false :res.getObject(32));
//			ooOptions.ActivarConsejos=(Boolean) ((res.getObject(33)==null) ? false :res.getObject(33));
//			ooOptions.ActivarPanelDeInicio=(Boolean) ((res.getObject(34)==null) ? false :res.getObject(34));
//			ooOptions.ActivarSeguridad=(Boolean) ((res.getObject(35)==null) ? false :res.getObject(35));
//			ooOptions.ActivarSeguridadOO=(Boolean) ((res.getObject(36)==null) ? false :res.getObject(36));
//			ooOptions.ActivarSeguridadSys=(Boolean) ((res.getObject(37)==null) ? false :res.getObject(37));
//			}


        return ;
    }
    public Boolean guardaOpenOSSADoptions(ooOptionsPropertySheet ooOptions) {

        // TODO Auto-generated method stub
        /*
          "( GId,GName,GRef,GLevel,GOrientation,GTemplate,GQgrCaptions,GVisPublic," +
          "ProjectID,GIsOK,GVersionNumber,GIndice,GNews,GAuthor,GDesignDate,GUpdateDate," +
          "GVerifNames,GApprobNames,GLinkDoc,GObject,GDomain,GAbrList,GDifList," +
          "GKeywords,GField1,GField2,GField3,GIsModel,GHtmlGen,GDocGen,GDifDate,Icone," +
          "Gtype,GOnlyLink,GGraphState )  " +
          "VALUES (" +
          */
        String connQuery = "UPDATE ooOptions SET " +
                           "BaseDeDatos='"+ooOptions.BaseDeDatos+"',"+
                           "connDriverClass='"+ooOptions.connDriverClass+"',"+
                           "connString='"+ooOptions.connString+"',"+
                           "connUser='"+ooOptions.connUser+"',"+
                           "connPassword='"+ooOptions.connPassword+"',"+
                           "ServidorOpenSERVER='"+ooOptions.ServidorOpenSERVER+"',"+

                           "CarpetaGallery='"+ooOptions.CarpetaGallery+"',"+
                           "CarpetaModelosWord='"+ooOptions.CarpetaModelosWord+"',"+
                           "CarpetaRelaciones='"+ooOptions.CarpetaRelaciones+"',"+
                           "CarpetaTrabajo='"+ooOptions.CarpetaTrabajo+"',"+
                           "CarpetaTrabajoPublico='"+ooOptions.CarpetaTrabajoPublico+"',"+
                           "CarpetaDifusionCreacion='"+ooOptions.CarpetaDifusionCreacion+"',"+

                           "CarpetaDifusionAplicable='"+ooOptions.CarpetaDifusionAplicable+"',"+
                           "CarpetaDifusionWeb='"+ooOptions.CarpetaDifusionWeb+"',"+
                           "CarpetaImportacion='"+ooOptions.CarpetaImportacion+"',"+
                           // empieza el bool -nostring-
                           "ActualizarPropiedadesGrafico="+((ooOptions.ActualizarPropiedadesGrafico==false) ? 0 :1)+","+
                           "SolicitarPropiedadesGrafico="+((ooOptions.SolicitarPropiedadesGrafico==false) ? 0 :1)+","+
                           "AbrirGraficosAlInicio="+((ooOptions.AbrirGraficosAlInicio==false) ? 0 :1)+","+
                           "VisualizarIndicadorCarpeta="+((ooOptions.VisualizarIndicadorCarpeta==false) ? 0 :1)+","+
                           "AplicarIntegridadReferencial="+((ooOptions.AplicarIntegridadReferencial==false) ? 0 :1)+","+
                           "TiempoDeGuardado="+ooOptions.TiempoDeGuardado+","+
                           "ConfirmarAntesDeGuardar="+((ooOptions.ConfirmarAntesDeGuardar==false) ? 0 :1)+","+
                           "DocumentoGuardarPorNombre="+((ooOptions.DocumentoGuardarPorNombre==false) ? 0 :1)+","+
                           "WebGuardarPorNombre="+((ooOptions.WebGuardarPorNombre==false) ? 0 :1)+","+

                           "ProyectoGuardarPorNombre="+((ooOptions.ProyectoGuardarPorNombre==false) ? 0 :1)+","+
                           "ControlarCoherenciaSegunNivel="+((ooOptions.ControlarCoherenciaSegunNivel==false) ? 0 :1)+","+
                           "DesencadenarAlineacionRolesAutomatica="+((ooOptions.DesencadenarAlineacionRolesAutomatica==false) ? 0 :1)+","+
                           "UtilizarFlechaDeAnguloRecto="+((ooOptions.UtilizarFlechaDeAnguloRecto==false) ? 0 :1)+","+
                           "OrientarPaginaEnFuncionDeAlineacion="+((ooOptions.OrientarPaginaEnFuncionDeAlineacion==false) ? 0 :1)+","+
                           "SeleccionDeArchivoAlIndicarReferenciaDocumental="+((ooOptions.SeleccionDeArchivoAlIndicarReferenciaDocumental==false) ? 0 :1)+","+
                           "VisualizarSugerenciasInicio="+((ooOptions.VisualizarSugerenciasInicio==false) ? 0 :1)+","+
                           "ActivarConsejos="+((ooOptions.ActivarConsejos==false) ? 0 :1)+","+
                           "ActivarPanelDeInicio="+((ooOptions.ActivarPanelDeInicio==false) ? 0 :1)+","+
                           "VisualizarSugerenciasInicio="+((ooOptions.VisualizarSugerenciasInicio==false) ? 0 :1)+","+
                           "ActivarSeguridad="+((ooOptions.ActivarSeguridad==false) ? 0 :1)+","+
                           "ActivarSeguridadOO="+((ooOptions.ActivarSeguridadOO==false) ? 0 :1)+","+
                           "ActivarSeguridadSys="+((ooOptions.ActivarSeguridadSys==false) ? 0 :1)+" " +
                           "WHERE OId="+ooOptions.OId;

        exeDELETE(connQuery);
        return true;

    }


    public static void deleteGraph(String GId) {

        exeDELETE("DELETE FROM tblgraphs WHERE GId='"+ GId + "'", GId);
        exeDELETE("DELETE FROM atblgraphs WHERE GId='"+ GId + "'");
        exeDELETE("DELETE FROM ooCells WHERE shGraphId='"+ GId + "'");
        //exeDELETE("DELETE FROM ooReaded WHERE GId='"+ GId + "'");
        exeDELETE("DELETE FROM oodocflow WHERE GraphID='"+ GId + "'");


    }

    public static List<String[]> exeGetResultSQL(String connQuery) {
        if (!connQuery.toLowerCase().startsWith("select")) {
            return null;
        }

        final Session currentSession = DAOFactory.getCurrentSession();
        currentSession.beginTransaction();
        SQLQuery query = currentSession.createSQLQuery(connQuery);

        List result = query.list();

        return result;
    }
    public static String exeGetUniqueResultSQL(String connQuery) {
        if (!connQuery.toLowerCase().startsWith("select")) {
            return null;
        }
        final Session currentSession = DAOFactory.getCurrentSession();
        currentSession.beginTransaction();
        SQLQuery query = currentSession.createSQLQuery(connQuery);

        List result = query.list();
        if (result.isEmpty()) return "";
        return result.get(0).toString();
    }

    public static Tblgraphs createNewOOGraph(Tblgraphs model, String txtGName, String txtGRef, Tblprojects project, boolean isModel) throws IOException
    {
        boolean isTest = false ;
        return  createNewOOGraph( model,  txtGName,  txtGRef,  project,  isModel,isTest);
    }
    public static Tblgraphs createNewOOGraph(Tblgraphs model, String txtGName, String txtGRef, Tblprojects project, boolean isModel, boolean isTest) throws IOException
    {
        String newId = getCounter("GID");
        final String gid = Serial + "-" + newId;
        String lastIDFile = (isTest) ? getFilePathFromGId(newId,"dummyWork") : getFilePathFromGId(newId);

        Tblgraphs newTblgraphs = new Tblgraphs();
        newTblgraphs.setGname(txtGName);
        newTblgraphs.setGref(txtGRef);
        newTblgraphs.setProjectId(project.getProjectId());
        final Date time = Calendar.getInstance().getTime();
        newTblgraphs.setGdesignDate(time);
        newTblgraphs.setGupdateDate(time);
        newTblgraphs.setGdifDate(time);
        newTblgraphs.setGversionNumber("1.0");
        newTblgraphs.setGid(gid);
        newTblgraphs.setGisModel(false);
        int GLevel = (isModel) ? model.getGlevel() : model.getGlevel() - 4;
        newTblgraphs.setGlevel(GLevel);
        newTblgraphs.setGorientation(model.getGorientation());
        newTblgraphs.setGvisPublic(model.getGvisPublic());
        newTblgraphs.setGtemplate(model.getGid());
        newTblgraphs.setGqgrCaptions(false);

        DAOFactory.getCurrentSession().beginTransaction();
        DAOFactory.getTblgraphsDAO().makePersistent(newTblgraphs);
        DAOFactory.getCurrentSession().flush();

        String ParseModel = (isTest) ? getParsedModel(model.getGid(),"dummyWork") : getParsedModel(model.getGid());
        if (!isTest)
        {
            CreateGraphInREP(ParseModel, lastIDFile);
        }
        return newTblgraphs;
    }

    public static Tblgraphs createNewOODocument(Tblprojects project, String txtGName, String txtGRef, String pathToDocument) throws IOException
    {
        String newId = getCounter("GID");
        final String gid = Serial + "-" + newId;
        String lastIDFile = getFilePathFromGId(newId);

        Tblgraphs document = new Tblgraphs();
        document.setGname(txtGName);
        document.setGref(txtGRef);
        document.setProjectId(project.getProjectId());
        final Date time = Calendar.getInstance().getTime();
        document.setGdesignDate(time);
        document.setGupdateDate(time);
        document.setGdifDate(time);
        document.setGversionNumber("1.0");
        document.setGid(gid);
        document.setGisModel(false);
        document.setGlevel(4);
        document.setGorientation(1);
        document.setGvisPublic(false);
        document.setGtemplate("");
        document.setGqgrCaptions(false);
        document.setGlinkDoc(pathToDocument);

        DAOFactory.getCurrentSession().beginTransaction();
        DAOFactory.getTblgraphsDAO().makePersistent(document);
        DAOFactory.getCurrentSession().flush();

        if (!pathToDocument.equals(""))
        {
            String destFile = lastIDFile.substring(0,(lastIDFile.length() - 4));
            String extNewFile = pathToDocument.substring(pathToDocument.lastIndexOf('.') + 1);
            destFile = destFile + "." + extNewFile;
            CreateGraphInREP(pathToDocument, destFile);
        }
        return document;
    }

    public static void deleteIndicador(Integer currentTblindicGId)
    {
        DAOFactory.getCurrentSession().beginTransaction();
        Tblindic tblindic = DAOFactory.getTblindicDAO().findById(currentTblindicGId,true);
        DAOFactory.getCurrentSession().delete(tblindic);
        DAOFactory.getCurrentSession().flush();
    }

    public static Tblindic createNewIndic(Tblprojects project, String IName,String IRef)
    {
        DAOFactory.getCurrentSession().beginTransaction();
        Tblindic indic = new Tblindic();
        indic.setIname(IName);
        indic.setIref(IRef);
        indic.setIdescription("");
        indic.setIcalcul("");
        String Iid = getLastIDIndic();
        indic.setIid(Iid);
        indic.setIperiode("");
        indic.setIresponsable("");
        DAOFactory.getTblindicDAO().makePersistent(indic);
        DAOFactory.getCurrentSession().flush();
        return indic;
    }

    public static Tblentity createNewRole(String name, String ref, Tblprojects project, String gType)
    {
        //TODO: refactorize tblEntity domain class
        String time = dateformat();
        String Eid = getLastIDEntity();
        String sqlQ = "INSERT INTO tblentity VALUES ('" + Eid + "','" + name + "','" + ref + "','" + project.getProjectId() + "','','" + time.toString() + "','" + time.toString()  + "','','','','','','','',0,0,'" + time.toString()  + "','" + gType + "','','')";
        exeUpdate(sqlQ);
        return new Tblentity();
    }


    public static String getBasicModelFromLevel(Integer level)
    {
        return getBasicModelFromLevel(level, options.getCarpetaTrabajo());
    }

    public static String getBasicModelFromLevel(Integer level, String carpetaTrabajo)
    {
        switch(level) {
            case 10:level=6;break;
            case 11:level=7;break;
            case 12:level=8;break;
            default:
        }
        return carpetaTrabajo
                .concat(File.separator).concat("TRZ000")
                .concat(File.separator).concat("00000000").concat(level.toString()).concat(".odd");
    }
}
