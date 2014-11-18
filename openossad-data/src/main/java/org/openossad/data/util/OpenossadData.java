package org.openossad.data.util;

import org.openossad.data.domain.OoCells;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 26/10/11
 * Time: 8:36
 * To change this template use File | Settings | File Templates.
 */
public class OpenossadData
{
    public static Integer process = 1;
    public static Integer document = 2;
    public static Integer role = 3;
    public static Integer indic = 4;
    public static Integer medio = 5;

    public static Integer[] process_ShTypeId  = new Integer[]{333,3,5,16,132,133,112,113,114};
    public static Integer[] document_ShTypeId = new Integer[]{8,144};
    public static Integer[] role_ShTypeId = new Integer[]{6,9,10,111,131,804,805,806,821,822,823};
    public static Integer[] indic_ShTypeId = new Integer[]{137};
    public static Integer[] medio_ShTypeId = new Integer[]{7,14,145};


    public static Map<Integer,Integer> ooTypeElements;
    public static Map<Integer, String> stringNameMap;

    public static String xmlSimpleGraphData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><mxGraphModel><root><mxCell id=\"0\"/><mxCell id=\"1\" parent=\"0\"/></root></mxGraphModel>";
    public static Integer actorAprobador = 3;
    public static Integer actorRedactor = 2;
    public static Integer actorLector = 1;

    static {
        chargeHashMaps();
    }

    public static void chargeHashMaps()
    {
        ooTypeElements = new HashMap<Integer,Integer>();

        for(int x=0;x<process_ShTypeId.length;x++) {
            ooTypeElements.put(process_ShTypeId[x] ,process);
        }
        for(int x=0;x<document_ShTypeId.length;x++) {
            ooTypeElements.put(document_ShTypeId[x] ,document);
        }
        for(int x=0;x<role_ShTypeId.length;x++) {
            ooTypeElements.put(role_ShTypeId[x] ,role);
        }
        for(int x=0;x<indic_ShTypeId.length;x++) {
            ooTypeElements.put(indic_ShTypeId[x] ,indic);
        }
        for(int x=0;x<medio_ShTypeId.length;x++) {
            ooTypeElements.put(medio_ShTypeId[x] ,medio);
        }

        stringNameMap = new HashMap<Integer,String>();
        stringNameMap.put(process,"procesos");
        stringNameMap.put(role,"roles");
        stringNameMap.put(medio,"medios / aplicaciones");
        stringNameMap.put(document,"documentos");
        stringNameMap.put(indic,"indicadores");
    }

    public static String getValueProperty(String propertyName)
    {
        Properties prop = new Properties();

        try {
            InputStream properties = OpenossadData.class.getResourceAsStream("/app.properties");
            prop.load(properties);
        }
        catch (IOException e)
        {
            System.out.println("retrieveTestKey::Unable to load properties");
        }
        return prop.getProperty(propertyName);
    }

    public static List<OoCells> getOneType(List<OoCells> ooCellsList, Integer tip)
    {
        List<OoCells> ooCellsSubList = new ArrayList<OoCells>();

        for(OoCells ooCells : ooCellsList) {
            final String shTypeId1 = ooCells.getShTypeId();
            if (shTypeId1.trim().matches("\\d")) { // number
                Integer shTypeId = Integer.parseInt(shTypeId1);
                if (ooTypeElements.get(shTypeId) == tip) {
                    ooCellsSubList.add(ooCells);
                }
            }
        }
        return ooCellsSubList;
    }
}
