package org.openossad.data.dao;

import org.openossad.data.domain.OoDocFlow;
import org.openossad.data.domain.Tblaccounts;

import java.util.List;

public interface TblaccountsDAO extends GenericDAO<Tblaccounts,Integer> {
    List<Tblaccounts> getAccountsActorsList(List<Tblaccounts> tblaccountsList, List<OoDocFlow> ooDocFlowList, boolean belongGraph, Integer actor);
}
