package com.github.invictum;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyBuilder {

    private By by;
    private SearchContext context;

    public ProxyBuilder(By by, SearchContext context) {
        this.by = by;
        this.context = context;
    }

    /**
     * Builds a lazy proxy for {@link WebElementFacade} based on specified {@link By} and {@link SearchContext}
     */
    public WebElementFacade buildWebElementFacadeProxy() {
        InvocationHandler handler = new WebElementFacadeHandler(by, context);
        ClassLoader loader = this.getClass().getClassLoader();
        return (WebElementFacade) Proxy.newProxyInstance(loader, new Class[]{WebElementFacade.class}, handler);
    }

    /**
     * Builds a lazy proxy for list of {@link WebElementFacade} based on specified {@link By} and {@link SearchContext}
     */
    @SuppressWarnings("unchecked")
    public List<WebElementFacade> buildWebElementFacadeListProxy() {
        InvocationHandler handler = new WebElementListHandler(by, context);
        ClassLoader loader = this.getClass().getClassLoader();
        return (List<WebElementFacade>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }
}
