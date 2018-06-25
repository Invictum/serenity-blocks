package com.github.invictum.fillers;

import com.github.invictum.AnnotationDummy;
import com.github.invictum.populator.ListProxied;
import net.thucydides.core.webdriver.MobilePlatform;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.SearchContext;

import java.lang.reflect.Field;

public class ListProxiedTest {

    @Test
    public void proceed() throws NoSuchFieldException {
        SearchContext searchContext = Mockito.mock(SearchContext.class);
        ListProxied listProxied = new ListProxied(MobilePlatform.NONE, searchContext);
        Field field = AnnotationDummy.class.getDeclaredField("compatibleList");
        Assert.assertTrue(listProxied.fill(field, new AnnotationDummy()));
    }

    @Test
    public void notCompatibleList() throws NoSuchFieldException {
        SearchContext searchContext = Mockito.mock(SearchContext.class);
        ListProxied listProxied = new ListProxied(MobilePlatform.NONE, searchContext);
        Field field = AnnotationDummy.class.getDeclaredField("incompatibleList");
        Assert.assertFalse(listProxied.fill(field, new AnnotationDummy()));
    }

    @Test
    public void notListAtAll() throws NoSuchFieldException {
        SearchContext searchContext = Mockito.mock(SearchContext.class);
        ListProxied listProxied = new ListProxied(MobilePlatform.NONE, searchContext);
        Field field = AnnotationDummy.class.getDeclaredField("data");
        Assert.assertFalse(listProxied.fill(field, new AnnotationDummy()));
    }
}
