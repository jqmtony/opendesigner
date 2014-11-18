package org.openossad.util;

import org.junit.Test;
import org.openossad.BasicGraphEditorOO;
import org.openossad.CustomOpenDESIGNERGraph;
import org.openossad.CustomOpenDESIGNERGraphComponent;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

/**
 * Created by IntelliJ IDEA.
 * User: fjhidalgo
 * Date: 23/10/11
 * Time: 16:36
 * To change this template use File | Settings | File Templates.
 */
public class PromptUtilTest
{
    private PromptUtil sut;

    @BeforeMethod
    public void setUp() throws Exception
    {
        sut = getSut();

    }

    private PromptUtil getSut()
    {
        return new PromptUtil();
    }

    @Test
    public void getPromptForCommentCell_mustPresentPrettyInterface(){
        String initialValue = "this is initial value";
        CustomOpenDESIGNERGraphComponent obj = new CustomOpenDESIGNERGraphComponent(new CustomOpenDESIGNERGraph());
        BasicGraphEditorOO editor = new BasicGraphEditorOO("",obj);
        String comment = PromptUtil.promptCellComment(editor,initialValue);
        Assert.assertEquals(comment,"");


    }
}
