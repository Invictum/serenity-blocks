package com.github.invictum.populator;

import com.github.invictum.DynamicAnnotations;
import com.github.invictum.ProxyBuilder;
import com.github.invictum.ReflectionUtils;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

import java.lang.reflect.Field;

public class SingleProxied implements FieldPopulator {

    private MobilePlatform platform;
    private SearchContext context;

    public SingleProxied(MobilePlatform platform, SearchContext context) {
        this.platform = platform;
        this.context = context;
    }

    @Override
    public boolean fill(Field field, Object target) {
        if (WebElementFacade.class.isAssignableFrom(field.getType())) {
            By by = new DynamicAnnotations(field, platform).buildBy();
            WebElementFacade elementProxy = new ProxyBuilder(by, context).buildWebElementFacadeProxy();
            ReflectionUtils.setField(target, field, elementProxy);
            return true;
        }
        return false;
    }
}
