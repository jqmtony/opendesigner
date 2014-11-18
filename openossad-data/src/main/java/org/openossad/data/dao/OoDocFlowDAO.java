package org.openossad.data.dao;

import org.openossad.data.domain.OoDocFlow;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 19/10/11
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public interface OoDocFlowDAO extends GenericDAO<OoDocFlow,Integer> {

    List<OoDocFlow> findAllByGraphId(String gId);
}