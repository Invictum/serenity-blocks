package com.github.invictum;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Handler applied to single {@link WebElementFacade} object
 */
public class WebElementFacadeHandler implements InvocationHandler {

    private By locator;
    private SearchContext context;

    public WebElementFacadeHandler(By locator, SearchContext context) {
        this.locator = locator;
        this.context = context;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().contentEquals("toString")) {
            return context + " -> " + locator;
        }
        try {
            WebElement element = context.findElement(locator);
            WebElementFacade facade = WebElementFacadeUtil.wrapWebElement(element);
            return method.invoke(facade, args);
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
    }
}
