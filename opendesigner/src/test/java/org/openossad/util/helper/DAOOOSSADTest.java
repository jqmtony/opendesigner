package org.openossad.util.helper;

import org.openossad.data.domain.OoCells;
import org.openossad.data.domain.OoCounters;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 17/11/11
 * Time: 8:28
 * To change this template use File | Settings | File Templates.
 */
public class DAOOOSSADTest {

    private String cTypeDummy = "DUMMY_WALKER";


    @DataProvider(name = "counters")
    Object[][] getCounters(){
        return new Object[][]{
                {"GID",0},
        };
    }
    @Test (dataProvider = "counters")
    public void testGetCounterByCType(String idCounter, Integer expected) throws Exception {

        Integer actual = DAOOOSSAD.getCounterByCType(idCounter);

        Assert.assertEquals(actual,expected);
    }


    @DataProvider(name = "counters2")
    Object[][] getCounters2(){
        return new Object[][]{
                {12,12},
        };
    }
    @Test (dataProvider = "counters2")
    public void testUpdateCounters_updateOrCreateObj(Integer lastIdFile, Integer expected) throws Exception {

        OoCounters ooCounters = DAOOOSSAD.updateCounters(lastIdFile, cTypeDummy);
        Integer actual = ooCounters.getCount();
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void insertOoCellList_test(){

        List<OoCells> ooCellsList = getDummyOoCellsList();

        DAOOOSSAD.insertOoCellList(ooCellsList);

        List<OoCells> actual = DAOOOSSAD.getOoCellsInTblgraphs("dummy");
        Assert.assertEquals(actual,ooCellsList);

        DAOOOSSAD.deleteOoCellsObjOfGraph("dummy");
        actual = DAOOOSSAD.getOoCellsInTblgraphs("dummy");
        Assert.assertTrue(actual.isEmpty());
    }

    public static List<OoCells> getDummyOoCellsList() {
        List<OoCells> ooCellsList = new ArrayList<OoCells>();
        ooCellsList.add(new OoCells("dummy","100"));
        ooCellsList.add(new OoCells("dummy","101"));
        ooCellsList.add(new OoCells("dummy","102"));
        ooCellsList.add(new OoCells("dummy","103"));
        ooCellsList.add(new OoCells("dummy","104"));
        return ooCellsList;
    }

}
