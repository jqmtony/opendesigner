package org.openossad.data.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openossad.data.domain.*;
import org.openossad.data.util.OpenossadData;

import java.util.ArrayList;
import java.util.List;

public class HibernateDAOFactory extends DAOFactory {

    public TblgraphsDAO getTblgraphsDAO() {
        return (TblgraphsDAO)instantiateDAO(TblgraphsDAOHibernate.class);
    }

    public TblindicDAO getTblindicDAO() {
        return (TblindicDAO)instantiateDAO(TblindicDAOHibernate.class);
    }

    public TblprojectsDAO getTblprojectsDAO() {
        return (TblprojectsDAO)instantiateDAO(TblprojectsDAOHibernate.class);
    }

    public OoCellsDAO getOoCellsDAO() {
        return (OoCellsDAO)instantiateDAO(OoCellsDAOHibernate.class);
    }

    public OoOptionsDAO getOoOptionsDAO() {
        return (OoOptionsDAO)instantiateDAO(OoOptionsDAOHibernate.class);
    }

    public TblaccountsDAO getTblaccountsDAO() {
        return (TblaccountsDAO)instantiateDAO(TblaccountsDAOHibernate.class);
    }

    public OoDocFlowDAO getOoDocFlowDAO()
    {
        return (OoDocFlowDAO)instantiateDAO(OoDocFlowDAOHibernate.class);
    }
    public OoCountersDAO getOoCountersDAO()
    {
        return (OoCountersDAO)instantiateDAO(OoCountersDAOHibernate.class);
    }

    ////////////////////////////////////////////////////////////////
    private GenericHibernateDAO instantiateDAO(Class daoClass) {
        try {
            GenericHibernateDAO dao = (GenericHibernateDAO)daoClass.newInstance();
            dao.setSession(getCurrentSession());
            return dao;
        } catch (Exception ex) {
            throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);
        }
    }

    // You could override this if you don't want HibernateUtil for lookup
    public Session getCurrentSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }



    public static class OoCountersDAOHibernate
            extends GenericHibernateDAO<OoCounters, Integer>
            implements OoCountersDAO {
    }

    // Inline concrete DAO implementations with no business-related data access methods.
    // If we use public static nested classes, we can centralize all of them in one source file.

    public static class TblgraphsDAOHibernate extends GenericHibernateDAO<Tblgraphs, String> implements TblgraphsDAO {

        public List<Tblgraphs> findByExample(Tblgraphs exampleInstance) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public List<Tblgraphs> getModelsProcess()
        {
            Criteria criteria = this.getSession().createCriteria(Tblgraphs.class);
            criteria.add(Restrictions.between("glevel",3,8));
            return this.findByCriteria();
        }

        @Override
        public void deleteTblgraphsByGName(String name)
        {
            Criteria criteria = this.getSession().createCriteria(Tblgraphs.class);
            criteria.add(Restrictions.eq("gname", name));

            List<Tblgraphs> tblgraphsList = this.findByCriteria();
            for (Tblgraphs tblgraphs : tblgraphsList) {
                getSession().delete(tblgraphs);
            }
        }

        @Override
        public void makePersistent(List<Tblgraphs> tblgraphsList)
        {
            for (Tblgraphs tblgraphs : tblgraphsList) {
                this.makePersistent(tblgraphs);
            }

        }
    }

    public static class TblprojectsDAOHibernate
            extends GenericHibernateDAO<Tblprojects, String>
            implements TblprojectsDAO {
    }

    public static class OoDocFlowDAOHibernate
            extends GenericHibernateDAO<OoDocFlow, Integer>
            implements OoDocFlowDAO {
        @Override
        public List<OoDocFlow> findAllByGraphId(String gId) {
            OoDocFlow example = new OoDocFlow();
            example.setGraphId(gId);
            return findByExample(example);
        }
    }

    public static class OoCellsDAOHibernate
            extends GenericHibernateDAO<OoCells, String>
            implements OoCellsDAO {
        @Override
        public List<OoCells> getOoCellsInTblgraphs(String gId)
        {
            OoCells ooCell = new OoCells();
            ooCell.setShGraphId(gId);
            return this.findByExample(ooCell);
        }

        @Override
        public void makePersistentAll(List<OoCells> ooCellsList) {
            getSession().beginTransaction();
            for (OoCells ooCells : ooCellsList) {
                getSession().save(ooCells);
            }
        }

        @Override
        public List<OoCells> findProcessCells(String gId) {
            List<OoCells> ooCellsList = getOoCellsInTblgraphs(gId);
            return OpenossadData.getOneType(ooCellsList, OpenossadData.process);
        }

        @Override
        public List<OoCells> findDocumentCells(String gId) {
            List<OoCells> ooCellsList = getOoCellsInTblgraphs(gId);
            return OpenossadData.getOneType(ooCellsList, OpenossadData.document);

        }
    }
    public static class OoOptionsDAOHibernate
            extends GenericHibernateDAO<OoOptions, Integer>
            implements OoOptionsDAO {
    }

    public static class TblaccountsDAOHibernate
            extends GenericHibernateDAO<Tblaccounts, Integer>
            implements TblaccountsDAO {
        @Override
        public List<Tblaccounts> getAccountsActorsList(List<Tblaccounts> tblaccountsList, List<OoDocFlow> ooDocFlowList, boolean belongGraph, Integer actor) {

            List<Tblaccounts> accountsNotOoDocFlow = new ArrayList<Tblaccounts>();
            List<Tblaccounts> accountsInOoDocflow = new ArrayList<Tblaccounts>();

            for (Tblaccounts tblaccounts : tblaccountsList) {

                boolean isAccountInOoDocFlow = isAccountInOoDocFlowList(ooDocFlowList, actor, tblaccounts);

                if (isAccountInOoDocFlow) {
                    accountsInOoDocflow.add(tblaccounts);
                } else {
                    accountsNotOoDocFlow.add(tblaccounts);
                }
            }

            if (belongGraph) {
                return accountsInOoDocflow;
            }
            return accountsNotOoDocFlow;
        }


        public boolean isAccountInOoDocFlowList(List<OoDocFlow> ooDocFlowList, Integer actor, Tblaccounts tblaccounts) {
            boolean isInOoDocFlow=false;
            for (OoDocFlow ooDocFlow : ooDocFlowList) {

                Integer codeUser = ooDocFlow.getCodeUser();
                Integer type = ooDocFlow.getType();

                Integer sid = tblaccounts.getSid();

                if (type == actor && codeUser == sid) {
                    isInOoDocFlow=true;
                }
            }
            return isInOoDocFlow;
        }
    }
    public static class TblindicDAOHibernate
            extends GenericHibernateDAO<Tblindic, Integer>
            implements TblindicDAO {
    }
}
