package com.github.invictum;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handler applied to list of {@link WebElementFacade} objects
 */
public class WebElementListHandler implements InvocationHandler {

    private By locator;
    private SearchContext context;

    public WebElementListHandler(By locator, SearchContext context) {
        this.locator = locator;
        this.context = context;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().contentEquals("toString")) {
            return String.format("List of <%s -> %s>", context, locator);
        }
        try {
            DriverUtils.ignite();
            List<WebElementFacade> elementsList = context.findElements(locator).stream()
                    .map(WebElementFacadeUtil::wrapWebElement).collect(Collectors.toList());
            return method.invoke(elementsList, args);
        } catch (Exception e) {
            throw e.getCause();
        }
    }
}
