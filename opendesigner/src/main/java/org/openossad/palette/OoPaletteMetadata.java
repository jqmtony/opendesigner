package org.openossad.palette;

import com.mxgraph.util.mxResources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 11/10/11
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
public class OoPaletteMetadata
{
    public String name;
    public String iconPath;
    public String style;
    public int width;
    public int height;
    public String value;

    public OoPaletteMetadata(String name, String iconPath, String style, int width, int height, String value)
    {
        this.name=name;
        this.iconPath=iconPath;
        this.style=style;
        this.width=width;
        this.height=height;
        this.value=value;
    }

    public static List<OoPaletteMetadata> getProcessLevel_1_Items()
    {
        List<OoPaletteMetadata> ooPaletteMetadataList = new ArrayList<OoPaletteMetadata>();
        final String path = "/ui/images/1p/";
        final String proces1_ = "proces1_";
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces1_ + "1"), path + "1.png","111",250,200,mxResources.get(proces1_ + "1")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces1_ + "2"), path + "2.png","112",205,90,mxResources.get(proces1_ + "2")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces1_ + "3"), path + "3.png","114",205,90,mxResources.get(proces1_ + "3")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces1_ + "4"), path + "4.png","113",140,40,mxResources.get(proces1_ + "4")));
        return ooPaletteMetadataList;
    }

    public static List<OoPaletteMetadata> getProcessLevel_2_Items()
    {
        List<OoPaletteMetadata> ooPaletteMetadataList = new ArrayList<OoPaletteMetadata>();
        final String path = "/ui/images/2p/";
        final String proces2_ = "proces2_";
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "1"), path + "1.png","10",100,40,mxResources.get(proces2_ + "1")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "2"), path + "2.png","6",100,40,mxResources.get(proces2_ + "2")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "3"), path + "3.png","9",100,40,mxResources.get(proces2_ + "3")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "4"), path + "4.png","333",120,150,mxResources.get(proces2_ + "4")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "5"), path + "5.png","16",100,40,mxResources.get(proces2_ + "5")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "6"), path + "6.png","3",100,40,mxResources.get(proces2_ + "6")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "7"), path + "7.png","5",300,40,mxResources.get(proces2_ + "7")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "8"), path + "8.png","4",100,40,mxResources.get(proces2_ + "8")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "9"), path + "9.png","17",100,40,mxResources.get(proces2_ + "9")));

        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "12"), path + "12.png","7",30,30,""));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "13"), path + "13.png","18",30,30,""));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "14"), path + "14.png","42",30,20,"X"));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "15"), path + "15.png","2",25,25,"Y"));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "16"), path + "16.png","2",25,25,"O"));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "17"), path + "17.png","8",80,60,mxResources.get(proces2_ + "17")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "18"), path + "18.png","8",80,40,mxResources.get(proces2_ + "18")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "19"), path + "19.png","8",25,20,mxResources.get(proces2_ + "19")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "20"), path + "20.png","14",80,50,mxResources.get(proces2_ + "20")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces2_ + "21"), path + "21.png","14",20,20,mxResources.get(proces2_ + "21")));

        return ooPaletteMetadataList;
    }

    public static List<OoPaletteMetadata> getProcessLevel_3_Items()
    {
        List<OoPaletteMetadata> ooPaletteMetadataList = new ArrayList<OoPaletteMetadata>();
        final String path = "/ui/images/3p/";
        final String proces3_ = "proces3_";
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "1"), path + "1.png","131",100,40,mxResources.get(proces3_ + "1")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "2"), path + "2.png","132",140,150,mxResources.get(proces3_ + "2")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "3"), path + "3.png","133",130,40,mxResources.get(proces3_ + "3")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "4"), path + "4.png","134",130,40,mxResources.get(proces3_ + "4")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "5"), path + "5.png","135",130,40,mxResources.get(proces3_ + "5")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "6"), path + "6.png","136",100,40,mxResources.get(proces3_ + "6")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "7"), path + "7.png","137",30,20,"X"));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "8"), path + "8.png","138",30,30,mxResources.get(proces3_ + "8")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "9"), path + "9.png","139",30,30,mxResources.get(proces3_ + "9")));

        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "12"), path + "12.png","137",77,18,"X"));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "13"), path + "13.png","18",9,79,mxResources.get(proces3_ + "13")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "14"), path + "14.png","42",77,9,mxResources.get(proces3_ + "14")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "15"), path + "15.png","143",30,30,"Y"));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "16"), path + "16.png","143",30,30,"O"));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "17"), path + "17.png","144",80,60,mxResources.get(proces3_ + "17")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "18"), path + "18.png","144",80,40,mxResources.get(proces3_ + "18")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "19"), path + "19.png","144",25,20,mxResources.get(proces3_ + "19")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "20"), path + "20.png","145",80,50,mxResources.get(proces3_ + "20")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(proces3_ + "21"), path + "21.png","145",20,20,mxResources.get(proces3_ + "21")));
        return ooPaletteMetadataList;

    }

    public static List<OoPaletteMetadata> getHumanLevel_1_Items()
    {
        List<OoPaletteMetadata> ooPaletteMetadataList = new ArrayList<OoPaletteMetadata>();
        final String path = "/ui/images/1h/";
        final String human1_ = "human1_";
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "1"), path + "1.png","801",180,200,mxResources.get(human1_ + "1")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "2"), path + "2.png","802",156,85,mxResources.get(human1_ + "2")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "3"), path + "3.png","803",268,174,mxResources.get(human1_ + "3")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "4"), path + "4.png","804",30,50,mxResources.get(human1_ + "4")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "5"), path + "5.png","805",30,50,mxResources.get(human1_ + "5")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "6"), path + "6.png","806",30,50,mxResources.get(human1_ + "6")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "7"), path + "7.png","807",148,116,mxResources.get(human1_ + "7")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "8"), path + "8.png","808",130,28,mxResources.get(human1_ + "8")));
        return ooPaletteMetadataList;
    }


    public static List<OoPaletteMetadata> getHumanLevel_2_Items()
    {
        List<OoPaletteMetadata> ooPaletteMetadataList = new ArrayList<OoPaletteMetadata>();
        final String path = "/ui/images/2h/";
        final String human1_ = "human2_";
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "1"), path + "1.png","821",100,40,mxResources.get(human1_ + "1")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "2"), path + "2.png","822",100,40,mxResources.get(human1_ + "2")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "3"), path + "3.png","823",100,40,mxResources.get(human1_ + "3")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "4"), path + "4.png","824",400,300,mxResources.get(human1_ + "4")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "5"), path + "5.png","825",300,200,mxResources.get(human1_ + "5")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "6"), path + "6.png","826",200,100,mxResources.get(human1_ + "6")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "7"), path + "7.png","827",148,116,mxResources.get(human1_ + "7")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "8"), path + "8.png","828",203,89,mxResources.get(human1_ + "8")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "9"), path + "9.png","829",69,16,mxResources.get(human1_ + "9")));
        return ooPaletteMetadataList;

    }

    public static List<OoPaletteMetadata> getHumanLevel_3_Items()
    {
        List<OoPaletteMetadata> ooPaletteMetadataList = new ArrayList<OoPaletteMetadata>();
        final String path = "/ui/images/3h/";
        final String human1_ = "human3_";
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "1"), path + "1.png","831",40,60,mxResources.get(human1_ + "1")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "2"), path + "2.png","832",100,40,mxResources.get(human1_ + "2")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "3"), path + "3.png","833",100,40,mxResources.get(human1_ + "3")));
        ooPaletteMetadataList.add(new OoPaletteMetadata(mxResources.get(human1_ + "4"), path + "4.png","834",9,192,mxResources.get(human1_ + "4")));
        return ooPaletteMetadataList;
    }
}
