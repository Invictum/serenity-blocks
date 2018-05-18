package com.github.invictum;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

public class WebElementFacadeHandlerTest {

    @Test
    public void toStringCheck() throws Throwable {
        By locator = By.xpath("//div");
        SearchContext contextMock = Mockito.mock(SearchContext.class);
        Mockito.when(contextMock.toString()).thenReturn("Context");
        Mockito.when(contextMock.toString()).thenReturn("Context");
        WebElementFacadeHandler handler = new WebElementFacadeHandler(locator, contextMock);
        String actual = (String) handler.invoke(new Object(), Object.class.getMethod("toString"), new Object[]{});
        Assert.assertEquals("Context -> By.xpath: //div", actual);
    }
}
