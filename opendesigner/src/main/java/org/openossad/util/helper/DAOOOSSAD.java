package org.openossad.util.helper;

import org.openossad.data.dao.HibernateDAOFactory;
import org.openossad.data.domain.*;
import org.openossad.ui.base.GenericListModelHibernate;

import java.util.List;

public class DAOOOSSAD
{
    public static HibernateDAOFactory DAOFactory = new HibernateDAOFactory();

	public String EXT = "ood";
    private String connQuery;

    public DAOOOSSAD() {
	}

	public static OoOptions getooOptionsOBJ() {
        DAOFactory.getCurrentSession().beginTransaction();
        List<OoOptions> optionsOptionsAll = DAOFactory.getOoOptionsDAO().findAll();
        if (optionsOptionsAll.isEmpty()) {
            return null;
        }
        return optionsOptionsAll.get(0);

	}	
	
	////////////////////////////////////////////////////////////////
	//  ooDocFlow
	////////////////////////////////////////////////////////////////
	
	public OoDocFlow getooDocFlowOBJ(Integer ID) {
        DAOFactory.getCurrentSession().beginTransaction();
        return DAOFactory.getOoDocFlowDAO().findById(ID,false);
	}	
	
	public Boolean insertooDocFlowOBJ(Object[] oop) {
		// TODO Auto-generated method stub
		OoDocFlow obj = null;
		for (int x=0;x<oop.length;x++){
			obj=(OoDocFlow) oop[x];
			connQuery = "INSERT INTO ooDocFlow VALUES (" +
			"null," +
			obj.getOrd() + "," +
			obj.getType() + "," +
			obj.getCodeUser() + "," +
			"'" + obj.getGraphId() + "'," +
			"'" + obj.getVote() + "'," +
			"'" + obj.getMessage() + "'," +
			"null" +
			")"; 
			doQuery(connQuery);
		}
		return true;
	}	
	
	public Boolean updateooDocFlowOBJ(Object[] oop) {
		// TODO Auto-generated method stub
		OoDocFlow obj = null;
		for (int x=0;x<oop.length;x++){
			obj=(OoDocFlow) oop[x];
			connQuery = "DELETE FROM ooDocFlow WHERE ID = " + obj.getId();
			doQuery(connQuery);
			connQuery = "INSERT INTO ooDocFlow VALUES (" +
			obj.getId() + "," +
			obj.getOrd() + "," +
			obj.getType() + "," +
			obj.getCodeUser() + "," +
			"'" + obj.getGraphId() + "'," +
			"'" + obj.getVote() + "'," +
			"'" + obj.getMessage() + "'," +
			"null" +
			")"; 
			doQuery(connQuery);
		}
		return true;
	}
	
	public Boolean deleteooDocFlowOBJ(Integer ID) {
		// TODO Auto-generated method stub
		connQuery = "DELETE FROM ooDocFlow WHERE ID = " + ID;
		doQuery(connQuery);
		return true;
	}

    private void doQuery(String connQuery)
    {
        //To change body of created methods use File | Settings | File Templates.
    }

    public Boolean deleteooDocFlowOBJ(String GId) {
		// TODO Auto-generated method stub
		connQuery = "DELETE FROM ooDocFlow WHERE GraphID = '" + GId + "'";
		doQuery(connQuery);
		return true;
	}
	
	

	////////////////////////////////////////////////////////////////
	//  currentTblgraph / tblGraphs
	////////////////////////////////////////////////////////////////
	
	public static Tblgraphs getooGraphOBJ(String GId) {
        DAOFactory.getCurrentSession().beginTransaction();
        return DAOFactory.getTblgraphsDAO().findById(GId,false);
	}	
	
	public List<Tblgraphs> getAllGraphs() {
        DAOFactory.getCurrentSession().beginTransaction();
		return DAOFactory.getTblgraphsDAO().findAll();
	}

	public Boolean deleteooGraphOBJ(String GId) {
		// TODO Auto-generated method stub
		connQuery = "DELETE FROM tblgraphs WHERE GId = '" + GId +"'";
		doQuery(connQuery);
		return true;
	}
	public Boolean insertooGraphOBJ(Object[] oop) {
		// TODO Auto-generated method stub
		Tblgraphs obj = null;
		for (int x=0;x<oop.length;x++){
			obj=(Tblgraphs) oop[x];
			connQuery = "INSERT INTO tblgraphs VALUES (" +
			"'" + obj.getGid()+ "'," +
			"'" + obj.getGname()+ "'," +
			"'" + obj.getGref()+ "'," +
			"" + obj.getGlevel()+ "," +
			"" + obj.getGorientation()+ "," +
			"'" + obj.getGtemplate()+ "'," +
			"" + obj.getGqgrCaptions()+ "," +
			"" + obj.getGvisPublic()+ "," +
			"'" + obj.getProjectId()+ "'," +
			"" + obj.getGisOk()+ "," +
			"'" + obj.getGversionNumber()+ "'," +
			"'" + obj.getGindice()+ "'," +
			"'" + obj.getGnews()+ "'," +
			"'" + obj.getGauthor()+ "'," +
			"'" + obj.getGdesignDate()+ "'," +
			"'" + obj.getGupdateDate()+ "'," +
			"'" + obj.getGverifNames()+ "'," +
			"'" + obj.getGapprobNames()+ "'," +
			"'" + obj.getGlinkDoc()+ "'," +
			"'" + obj.getGobject()+ "'," +
			"'" + obj.getGdomain()+ "'," +
			"'" + obj.getGabrList()+ "'," +
			"'" + obj.getGdifList()+ "'," +
			"'" + obj.getGkeywords()+ "'," +
			"'" + obj.getGfield1()+ "'," +
			"'" + obj.getGfield2()+ "'," +
			"'" + obj.getGfield3()+ "'," +
			"" + obj.getGisModel()+ "," +
			"" + obj.getGhtmlGen()+ "," +
			"" + obj.getGdocGen()+ "," +
			"'" + obj.getGdifDate()+ "'," +
			"'" + obj.getIcone()+ "'," +
			"'" + obj.getGtype()+ "'," +
			"" + obj.getGonlyLink()+ "," +
			"" + obj.getGgraphState()+ "," +
			"'" + obj.getGbgcolor()+ "'," +
			"'" + obj.getGbgimagePath()+ "'" +
			")"; 
			System.out.println(connQuery);
			doQuery(connQuery);
		}
		return true;
	}	
	
	public Boolean updateooGraphOBJ(Object[] oop) {
		// TODO Auto-generated method stub
		Tblgraphs obj = null;
		for (int x=0;x<oop.length;x++){
			obj=(Tblgraphs) oop[x];
			connQuery = "UPDATE tblgraphs SET " +
			"GName = '" + obj.getGname()+ "'," +
			"GRef = '" + obj.getGref()+ "'," +
			"GLevel = " + obj.getGlevel()+ "," +
			"GOrientation = " + obj.getGorientation()+ "," +
			"GTemplate = '" + obj.getGtemplate()+ "'," +
			"GQgrCaptions = " + obj.getGqgrCaptions()+ "," +
			"GVisPublic =" + obj.getGvisPublic()+ "," +
			"ProjectId = '" + obj.getProjectId()+ "'," +
			"GIsOk = " + obj.getGisOk()+ "," +
			"GVersionNumber = '" + obj.getGversionNumber()+ "'," +
			"GIndice = '" + obj.getGindice()+ "'," +
			"GNews = '" + obj.getGnews()+ "'," +
			"GAuthor = '" + obj.getGauthor()+ "'," +
			"GDesignDate = '" + obj.getGdesignDate()+ "'," +
			"GUpdateDate = '" + obj.getGupdateDate()+ "'," +
			"GVerifNames = '" + obj.getGverifNames()+ "'," +
			"GApprobNames = '" + obj.getGapprobNames()+ "'," +
			"GLinkDoc = '" + obj.getGlinkDoc()+ "'," +
			"GObject = '" + obj.getGobject()+ "'," +
			"GDomain = '" + obj.getGdomain()+ "'," +
			"GAbrList = '" + obj.getGabrList()+ "'," +
			"GDifList = '" + obj.getGdifList()+ "'," +
			"GKeywords = '" + obj.getGkeywords()+ "'," +
			"GField1 = '" + obj.getGfield1()+ "'," +
			"GField2 = '" + obj.getGfield2()+ "'," +
			"GField3 = '" + obj.getGfield3()+ "'," +
			"GIsModel = " + obj.getGisModel()+ "," +
			"GHtmlGen = " + obj.getGhtmlGen()+ "," +
			"GDocGen = " + obj.getGdocGen()+ "," +
			"GDifDate = '" + obj.getGdifDate()+ "'," +
			"Icone = '" + obj.getIcone()+ "'," +
			"GType = '" + obj.getGtype()+ "'," +
			"GOnlyLink = " + obj.getGonlyLink()+ "," +
			"GGraphState = " + obj.getGgraphState()+ "," +
			"GBgcolor = '" + obj.getGbgcolor()+ "'," +
			"GBgimagePath = '" + obj.getGbgimagePath()+ "'" +
			" WHERE GId = '" + obj.getGid()+ "'" ;
			
			doQuery(connQuery);
		}
		return true;
	}
	
	////////////////////////////////////////////////////////////////
	//  currentTblgraph / Tblaccounts
	////////////////////////////////////////////////////////////////
	
	public Tblaccounts getTblaccountsOBJ(Integer SID) {
        DAOFactory.getCurrentSession().beginTransaction();
        return DAOFactory.getTblaccountsDAO().findById(SID,false);
	}
	
	public Boolean updateooAccountsOBJ(Object[] oop) {
		// TODO Auto-generated method stub
		Tblaccounts obj = null;
		for (int x=0;x<oop.length;x++){
			obj=(Tblaccounts) oop[x];
			connQuery = "UPDATE tblaccounts SET " +
			" name= '"+ obj.getName() + "'," +
			" completeName= '"+ obj.getCompleteName() + "'," +
			" description= '"+ obj.getDescription() + "'," +
			" isGroup= "+ obj.getIsGroup() + "," +
			" type= '"+ obj.getType() + "'," +
			" password= '"+ obj.getPassword() + "'," +
			" samsid= '"+ obj.getSamsid() + "'," +
			" samaccountName= '"+ obj.getSamaccountName() + "'," +
			" phone= '"+ obj.getPhone() + "'," +
			" mail= '"+ obj.getMail() + "'," +
			" lastLoginDate= '"+ obj.getLastLoginDate() + "'," +
			" id= '"+ obj.getId() + "'," +
			" phoneHome= '"+ obj.getPhoneHome() + "'," +
			" fax= '"+ obj.getFax() + "'," +
			" FaxHome= '"+ obj.getFaxHome() + "'," +
			" mobilPhone= '"+ obj.getMobilPhone() + "'," +
			" mobilHomePhone= '"+ obj.getMobilHomePhone() + "'," +
			" mobilCarPhone= '"+ obj.getMobilCarPhone() + "'," +
			" adress= '"+ obj.getAdress() + "'," +
			" mail2= '"+ obj.getMail2() + "'," +
			" mail3= '"+ obj.getMail3() + "'," +
			" responsable= '"+ obj.getResponsable() + "'," +
			" assistant= '"+ obj.getAssistant() + "'," +
			" photoGid= '"+ obj.getPhotoGid() + "'," +
			" updateDate= '"+ obj.getUpdateDate() + "'," +
			" htmlGen= "+ obj.getHtmlGen() + "," +
			" gtype= '"+ obj.getGtype() + "'," +
			" pageArrivee= '"+ obj.getPageArrivee() + "'," +
			" roles= '"+ obj.getRoles() + "'" +
			" WHERE SId = " + obj.getSid();
			doQuery(connQuery);
		}
		return true;
	}
	

	////////////////////////////////////////////////////////////////
	//  Tblindics
	////////////////////////////////////////////////////////////////
	
	public Tblindic getTblindicOBJ(Integer IID) {
        DAOFactory.getCurrentSession().beginTransaction();
        return DAOFactory.getTblindicDAO().findById(IID,false);
    }
	
	public Boolean updateooIndicOBJ(Object[] oop) {
		// TODO Auto-generated method stub
		Tblindic obj = null;
		for (int x=0;x<oop.length;x++){
			obj=(Tblindic) oop[x];
			connQuery = "UPDATE tblindic SET " +
			"iref='"+obj.getIref()+"',"+
			"iname='"+obj.getIname()+"',"+
			"idescription='"+obj.getIdescription()+"',"+
			"icalcul='"+obj.getIcalcul()+"',"+
			"iperiode='"+obj.getIperiode()+"',"+
			"itype='"+obj.getItype()+"',"+
			"iobjectif='"+obj.getIobjectif()+"',"+
			"iponderation='"+obj.getIponderation()+"',"+
			"iactualValue='"+obj.getIactualValue()+"',"+
			"itrend='"+obj.getItrend()+"',"+
			"inbVal='"+obj.getInbVal()+"',"+
			"iresponsable='"+obj.getIresponsable()+"',"+
			"inbDec='"+obj.getInbDec()+"' " +
			"WHERE iid='"+obj.getIid()+"'";
			
			doQuery(connQuery);
		}
		return true;
	}

	/**
	 * @return
	 */
	public List<OoCells> getooCellsFromGraph() {
		// TODO Auto-generated method stub
		return null;
	}

    public static void deleteoodocflowOBJ(String gId)
    {
        //To change body of created methods use File | Settings | File Templates.
    }

    public static void insertoodocflowOBJ(OoDocFlow ooDocFlow)
    {
        DAOFactory.getCurrentSession().beginTransaction();
        DAOFactory.getOoDocFlowDAO().makePersistent(ooDocFlow);
        DAOFactory.getCurrentSession().flush();
    }

    public static void insertOoDocFlowFromListModel(String GId, GenericListModelHibernate listModel, int typeActor)
    {
        Object[] str;
        for (int x=0;x< listModel.getSize();x++) {
            OoDocFlow ooDocFlow = new OoDocFlow();
            str = (Object[]) listModel.getElementAt(x);
            ooDocFlow.setOrd(x + 1);
            ooDocFlow.setType(typeActor);
            ooDocFlow.setCodeUser(Integer.parseInt(str[0].toString()));
            ooDocFlow.setGraphId(GId);
            ooDocFlow.setVote("");
            ooDocFlow.setMessage("");
            ooDocFlow.setVoteDate(null);
            DAOOOSSAD.insertoodocflowOBJ(ooDocFlow);
        }
    }

    public static void insertOoCellList(List<OoCells> ooCellsList)
    {
        DAOFactory.getOoCellsDAO().makePersistentAll(ooCellsList);

    }

    public static List<OoCells> getOoCellsInTblgraphs(String gId)
    {
        DAOFactory.getCurrentSession().beginTransaction();
        List<OoCells> ooCellsList = DAOFactory.getOoCellsDAO().getOoCellsInTblgraphs(gId);
        return ooCellsList;
    }

    public static void deleteTblgraphsByGName(String name)
    {
        DAOFactory.getCurrentSession().beginTransaction();
        DAOFactory.getTblgraphsDAO().deleteTblgraphsByGName(name);
    }

    public static List<Tblgraphs> getAllTblgraphs()
    {
        DAOFactory.getCurrentSession().beginTransaction();
        return DAOFactory.getTblgraphsDAO().findAll();
    }

    public static List<Tblprojects> getAllTblprojects()
    {
        DAOFactory.getCurrentSession().beginTransaction();
        return DAOFactory.getTblprojectsDAO().findAll();
    }

    public static Integer getCounterByCType(String cType) {
        DAOFactory.getCurrentSession().beginTransaction();
        OoCounters example = new OoCounters();
        example.setGserial("OPS000");
        example.setCtype(cType);
        List<OoCounters> result = DAOFactory.getOoCountersDAO().findByExample(example);
        if (result.isEmpty()) {
            return 0;
        }
        return result.get(0).getCount();
    }

    public static OoCounters updateCounters(Integer lastIdFile, String cType) {
        DAOFactory.getCurrentSession().beginTransaction();
        OoCounters example = new OoCounters();
        example.setGserial("OPS000");
        example.setCtype(cType);
        List<OoCounters> result = DAOFactory.getOoCountersDAO().findByExample(example);
        OoCounters ooCounters;
        if (result.isEmpty()) {
            ooCounters = new OoCounters();
            ooCounters.setGserial("OPS000");
            ooCounters.setCtype(cType);

        } else {
            ooCounters = result.get(0);

        }
        ooCounters.setCount(lastIdFile++);
        DAOFactory.getOoCountersDAO().makePersistent(ooCounters);
        DAOFactory.getCurrentSession().flush();

        return ooCounters;
    }

    public static void deleteOoCellsObjOfGraph(String graphGid) {
        OoCells example = new OoCells();
        example.setShGraphId(graphGid);
        DAOFactory.getCurrentSession().beginTransaction();
        List<OoCells> ooCellsList = DAOFactory.getOoCellsDAO().findByExample(example);
        for (OoCells ooCells : ooCellsList) {
            DAOFactory.getCurrentSession().delete(ooCells);
        }
    }
}
