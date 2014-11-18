package org.openossad.data.dao;

import org.openossad.data.domain.Tblgraphs;

import java.util.List;

public interface TblgraphsDAO extends GenericDAO<Tblgraphs,String> {
    List<Tblgraphs> getModelsProcess();

    void deleteTblgraphsByGName(String name);

    void makePersistent(List<Tblgraphs> tblgraphsList);
}

