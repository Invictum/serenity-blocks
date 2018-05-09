package com.github.invictum;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

import java.lang.reflect.InvocationHandler;

public class WebElementListHandlerTest {

    @Test
    public void toStringCheck() throws Throwable {
        SearchContext contextMock = Mockito.mock(SearchContext.class);
        Mockito.when(contextMock.toString()).thenReturn("Context");
        InvocationHandler handler = new WebElementListHandler(By.cssSelector(".class"), contextMock);
        String actual = (String) handler.invoke(new Object(), Object.class.getMethod("toString"), new Object[]{});
        Assert.assertEquals("List of <Context -> By.cssSelector: .class>", actual);
    }
}
