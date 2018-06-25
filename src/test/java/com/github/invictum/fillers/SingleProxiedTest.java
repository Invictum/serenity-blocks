package com.github.invictum.fillers;

import com.github.invictum.AnnotationDummy;
import com.github.invictum.populator.SingleProxied;
import net.thucydides.core.webdriver.MobilePlatform;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.SearchContext;

import java.lang.reflect.Field;

public class SingleProxiedTest {

    @Test
    public void proceed() throws NoSuchFieldException {
        SearchContext searchContext = Mockito.mock(SearchContext.class);
        SingleProxied singleProxied = new SingleProxied(MobilePlatform.NONE, searchContext);
        Field field = AnnotationDummy.class.getDeclaredField("data");
        Assert.assertTrue(singleProxied.fill(field, new AnnotationDummy()));
    }

    @Test
    public void notCompatibleField() throws NoSuchFieldException {
        SearchContext searchContext = Mockito.mock(SearchContext.class);
        SingleProxied singleProxied = new SingleProxied(MobilePlatform.NONE, searchContext);
        Field field = AnnotationDummy.class.getDeclaredField("xpath");
        Assert.assertFalse(singleProxied.fill(field, new AnnotationDummy()));
    }
}
