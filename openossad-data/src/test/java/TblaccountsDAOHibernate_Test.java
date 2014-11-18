/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 22/11/11
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Assert;
import org.openossad.data.dao.HibernateDAOFactory;
import org.openossad.data.domain.OoDocFlow;
import org.openossad.data.domain.Tblaccounts;
import org.openossad.data.util.OpenossadData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TblaccountsDAOHibernate_Test {

    private HibernateDAOFactory.TblaccountsDAOHibernate sut;
    private List<Tblaccounts> tblaccountsList;
    private List<OoDocFlow> ooDocFlowList;

    @BeforeMethod
    public void setUp() {
        sut = getSut();
        tblaccountsList = getTblaccountsList();
        ooDocFlowList = getOoDocFlowList();
    }

    @DataProvider(name="data")
    Object[][] getData(){
        return new Object[][]{
                {true, OpenossadData.actorAprobador,2},
                {false, OpenossadData.actorAprobador,4},
                {true, OpenossadData.actorLector,1},
                {false, OpenossadData.actorLector,5},
                {true, OpenossadData.actorRedactor,3},
                {false, OpenossadData.actorRedactor,3}
        };
    }

    @Test(dataProvider="data")
    public void getAccountsActorsList_test(boolean belongGraph, Integer actor, int expected) {
        List<Tblaccounts> actual = sut.getAccountsActorsList(tblaccountsList, ooDocFlowList, belongGraph, actor);

        Assert.assertEquals(expected,actual.size());

    }

    @DataProvider(name="data2")
    Object[][] getData2(){
        return new Object[][]{
                {OpenossadData.actorAprobador,getDummyTblaccount(1),true},
                {OpenossadData.actorAprobador,getDummyTblaccount(2),true},
                {OpenossadData.actorLector,getDummyTblaccount(1),true},
                {OpenossadData.actorLector,getDummyTblaccount(2),false},
                {OpenossadData.actorRedactor,getDummyTblaccount(11),false},
                {OpenossadData.actorRedactor,getDummyTblaccount(7),false}
        };
    }
    @Test(dataProvider="data2")
    public void isAccountInOoDocFlowList_test(Integer actor, Tblaccounts tblaccounts, boolean expected) {
        boolean actual = sut.isAccountInOoDocFlowList(ooDocFlowList,actor,tblaccounts);
        Assert.assertEquals(expected,actual);
    }



    public HibernateDAOFactory.TblaccountsDAOHibernate getSut() {
        return new HibernateDAOFactory.TblaccountsDAOHibernate();
    }

    private List<OoDocFlow> getOoDocFlowList() {
        List<OoDocFlow> ooDocFlowList = new ArrayList<OoDocFlow>();
        ooDocFlowList.add(new OoDocFlow(1,OpenossadData.actorAprobador,1,"OPS-000-000000001","","",null));
        ooDocFlowList.add(new OoDocFlow(2,OpenossadData.actorAprobador,2,"OPS-000-000000001","","",null));
        ooDocFlowList.add(new OoDocFlow(7,OpenossadData.actorRedactor,1,"OPS-000-000000001","","",null));
        ooDocFlowList.add(new OoDocFlow(8,OpenossadData.actorRedactor,2,"OPS-000-000000001","","",null));
        ooDocFlowList.add(new OoDocFlow(9,OpenossadData.actorRedactor,3,"OPS-000-000000001","","",null));
        ooDocFlowList.add(new OoDocFlow(13,OpenossadData.actorLector,1,"OPS-000-000000001","","",null));
        return ooDocFlowList;
    }

    private List<Tblaccounts> getTblaccountsList() {
        List<Tblaccounts> dummy = new ArrayList<Tblaccounts>();
        dummy.add(getDummyTblaccount(1));
        dummy.add(getDummyTblaccount(2));
        dummy.add(getDummyTblaccount(3));
        dummy.add(getDummyTblaccount(4));
        dummy.add(getDummyTblaccount(5));
        dummy.add(getDummyTblaccount(6));
        return dummy;
    }

    private Tblaccounts getDummyTblaccount(int SID) {
        Tblaccounts tblaccounts = new Tblaccounts();
        tblaccounts.setSid(SID);
        return tblaccounts;
    }
}