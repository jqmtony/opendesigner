package org.openossad.util.init;

import org.openossad.data.dao.HibernateDAOFactory;
import org.openossad.data.domain.OoOptions;
import org.openossad.data.domain.Tblgraphs;
import org.openossad.data.domain.Tblprojects;
import org.openossad.ui.component.SwingUtil;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 26/10/11
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
public class InitOpenossadData extends JComponent
{
    private HibernateDAOFactory hibernateDAOFactory;
    final Date time = Calendar.getInstance().getTime();
    private String workingFolder;
    private OoOptions ooOptions;
    private List<Tblgraphs> tblgraphsList = getInitialTblgraphs();
    private String trzFolder = "TRZ000";
    private String sep = File.separator;


    public InitOpenossadData()
    {
        hibernateDAOFactory = new HibernateDAOFactory();
        hibernateDAOFactory.getCurrentSession().close();
    }

    public String getValidWorkingFolder()
    {
        hibernateDAOFactory.getCurrentSession().beginTransaction();
        final List<OoOptions> optionsList = hibernateDAOFactory.getOoOptionsDAO().findAll();
        if (!optionsList.isEmpty()) {
            ooOptions = optionsList.get(0);
            workingFolder = ooOptions.getCarpetaTrabajo();
            boolean folderExists = new File(workingFolder).exists();
            if (folderExists) {
                return workingFolder;
            }
        } else {
            ooOptions = new OoOptions();
        }
        String defaultDir = System.getProperty("user.dir");
        File folderToCreate = SwingUtil.getDirectoryChoice(this, defaultDir, "Choose a folder to install repository");
        workingFolder = folderToCreate.getPath();
        ooOptions.setCarpetaTrabajo(workingFolder);
        hibernateDAOFactory.getCurrentSession().persist(ooOptions);
        hibernateDAOFactory.getCurrentSession().getTransaction().commit();
        return workingFolder;
    }

    public void insertInitRows()
    {
        deleteInitialModels();
        hibernateDAOFactory.getCurrentSession().beginTransaction();


        hibernateDAOFactory.getTblgraphsDAO().makePersistent(tblgraphsList);

        Tblprojects tblprojects = getInitialTblprojects();
        hibernateDAOFactory.getTblprojectsDAO().makePersistent(tblprojects);
        hibernateDAOFactory.getCurrentSession().getTransaction().commit();
    }

    private void deleteInitialModels()
    {

        for (Tblgraphs obj : tblgraphsList)   {
            hibernateDAOFactory.getCurrentSession().beginTransaction();
            hibernateDAOFactory.getCurrentSession().delete(obj);
            hibernateDAOFactory.getCurrentSession().getTransaction().commit();
        }
    }

    private Tblprojects getInitialTblprojects()
    {
        Tblprojects tblprojectsExample = new Tblprojects();
        tblprojectsExample.setProjectId("OPS000-000000001");

        List<Tblprojects> tblprojectsToDelete = hibernateDAOFactory.getTblprojectsDAO().findByExample(tblprojectsExample);
        for (Tblprojects tblprojects : tblprojectsToDelete)   {
            hibernateDAOFactory.getCurrentSession().delete(tblprojects);
        }
        return  new Tblprojects("OPS000-000000001","OpenDESIGNER",true,"000000-000000000",time,"","OpenDESIGNER",false,"TRZ-7","Main Project","Quality system",1,1);
    }

    private List<Tblgraphs> getInitialTblgraphs()
    {
        Tblgraphs tblgraphs;

        List<Tblgraphs> tblgraphsList = new ArrayList<Tblgraphs>();

        tblgraphs = new Tblgraphs("TRZ000-000000001","Modelo process","TRZ-1",5,0,"OPS000-000000001",null,null,null);
        insertCommonData(tblgraphs, time);
        tblgraphsList.add(tblgraphs);

        tblgraphs = new Tblgraphs("TRZ000-000000002","Modelo process","TRZ-2",6,1,"OPS000-000000001",null,null,null);
        insertCommonData(tblgraphs, time);
        tblgraphsList.add(tblgraphs);

        tblgraphs = new Tblgraphs("TRZ000-000000003","Modelo process","TRZ-3",7,1,"OPS000-000000001",null,null,null);
        insertCommonData(tblgraphs, time);
        tblgraphsList.add(tblgraphs);

        tblgraphs = new Tblgraphs("TRZ000-000000004","Modelo human","TRZ-4",8,0,"OPS000-000000001",null,null,null);
        insertCommonData(tblgraphs, time);
        tblgraphsList.add(tblgraphs);

        tblgraphs = new Tblgraphs("TRZ000-000000005","Modelo human","TRZ-5",9,0,"OPS000-000000001",null,null,null);
        insertCommonData(tblgraphs, time);
        tblgraphsList.add(tblgraphs);

        tblgraphs = new Tblgraphs("TRZ000-000000006","Modelo human","TRZ-6",10,0,"OPS000-000000001",null,null,null);
        insertCommonData(tblgraphs, time);
        tblgraphsList.add(tblgraphs);

        return tblgraphsList;

    }

    private void insertCommonData(Tblgraphs tblgraphs, Date time)
    {
        tblgraphs.setGdesignDate(time);
        tblgraphs.setGupdateDate(time);
        tblgraphs.setGdifDate(time);
        tblgraphs.setGauthor("OpenDESIGNER");
        tblgraphs.setGabrList("");
        tblgraphs.setGbgimagePath("");
        tblgraphs.setGdomain("Generic models for OpenDESISGNER");
        tblgraphs.setGdocGen(false);
        tblgraphs.setGhtmlGen(false);
        tblgraphs.setGisModel(true);
        tblgraphs.setGisOk(true);
        tblgraphs.setGlinkDoc("");
        tblgraphs.setGtemplate("");
        tblgraphs.setGversionNumber("1.0");
        tblgraphs.setGvisPublic(true);
    }

    public String createRepository(String folder) throws IOException
    {
        File folderRepository =  new File(folder);
        if (!folderRepository.exists()) {
            folderRepository.mkdirs();
        }
        final String modelfolderPath = folderRepository.getPath().concat(sep + trzFolder);
        final File modelFolder = new File(modelfolderPath);

        if (!modelFolder.exists())
        {
            modelFolder.mkdirs();
        }
        InitOpenossadDataHelper.createInitialModels(folderRepository);
        return modelfolderPath;
    }




    public String getWorkingFolder()
    {
        workingFolder= getValidWorkingFolder();
        return workingFolder;
    }

    public void setWorkingFolder(String workingFolder)
    {
        this.workingFolder = workingFolder;
        hibernateDAOFactory.getCurrentSession().beginTransaction();
        ooOptions = hibernateDAOFactory.getOoOptionsDAO().findAll().get(0);
        ooOptions.setCarpetaTrabajo(this.workingFolder);
        hibernateDAOFactory.getOoOptionsDAO().makePersistent(ooOptions);
        hibernateDAOFactory.getCurrentSession().getTransaction().commit();

    }

    public void init() throws IOException
    {
        workingFolder = getWorkingFolder();
        InitOpenossadDataHelper.createInitialModels(new File(workingFolder));
        insertInitRows();

    }


}
