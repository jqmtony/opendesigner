package org.openossad.data.dao;

import org.openossad.data.domain.OoCells;

import java.util.List;

public interface OoCellsDAO extends GenericDAO<OoCells,String> {
    List<OoCells> getOoCellsInTblgraphs(String gId);
    void makePersistentAll(List<OoCells> ooCellsList);
    List<OoCells> findProcessCells(String gId);
    List<OoCells> findDocumentCells(String gId);
}
