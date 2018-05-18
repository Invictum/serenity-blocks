package com.github.invictum.filler;

import com.github.invictum.DynamicAnnotations;
import com.github.invictum.ProxyBuilder;
import com.github.invictum.ReflectionUtils;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class ListProxied implements FieldFiller {

    private MobilePlatform platform;
    private SearchContext context;

    public ListProxied(MobilePlatform platform, SearchContext context) {
        this.platform = platform;
        this.context = context;
    }

    private boolean isCompatible(Field field) {
        if (List.class.isAssignableFrom(field.getType())) {
            ParameterizedType listType = (ParameterizedType) field.getGenericType();
            Class<?> type = (Class<?>) listType.getActualTypeArguments()[0];
            return WebElementFacade.class.isAssignableFrom(type);
        }
        return false;
    }

    @Override
    public boolean fill(Field field, Object target) {
        if (isCompatible(field)) {
            By by = new DynamicAnnotations(field, platform).buildBy();
            List<WebElementFacade> proxiedList = new ProxyBuilder(by, context).buildWebElementFacadeListProxy();
            ReflectionUtils.setField(target, field, proxiedList);
            return true;
        }
        return false;
    }
}
