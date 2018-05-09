package com.github.invictum.block.regular;

import com.github.invictum.DriverUtils;
import com.github.invictum.EnhancedAnnotations;
import com.github.invictum.WebElementFacadeHandler;
import com.github.invictum.WebElementListHandler;
import com.github.invictum.block.BlockFactory;
import net.serenitybdd.core.annotations.findby.di.ClasspathCustomFindByAnnotationProviderService;
import net.serenitybdd.core.annotations.findby.di.CustomFindByAnnotationProviderService;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

public class RegularBlockFactory<T extends RegularBlock> extends BlockFactory<T> {

    /**
     * Classpath provider - add integration with Serenity's facility
     */
    private CustomFindByAnnotationProviderService provider = new ClasspathCustomFindByAnnotationProviderService();

    public RegularBlockFactory(Class<T> type) {
        super(type);
    }

    @Override
    public T create() {
        // Build underlying block
        By blockBy = new EnhancedAnnotations(type).buildBy();
        WebDriver driver = ThucydidesWebDriverSupport.getDriver();
        WebElementFacade block = facadeProxy(blockBy, driver);
        // Init block object
        T object = buildWithReflection(type);
        object.setBlock(block);
        for (Field field : type.getDeclaredFields()) {
            // Proxy for single element
            if (WebElementFacade.class.isAssignableFrom(field.getType())) {
                By by = new EnhancedAnnotations(field, DriverUtils.resolvePlatform(driver), provider).buildBy();
                setField(object, field, facadeProxy(by, block));
            }
            // Proxy for list of elements
            if (List.class.isAssignableFrom(field.getType())) {
                By by = new EnhancedAnnotations(field, DriverUtils.resolvePlatform(driver), provider).buildBy();
                // TODO: Check List's generic type here
                setField(object, field, facadeList(by, block));
            }
        }
        return object;
    }

    private T buildWithReflection(Class<T> type) {
        try {
            Constructor<T> constructor = type.getConstructor();
            return constructor.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Unable to init an object of " + type);
        }
    }

    private WebElementFacade facadeProxy(By by, SearchContext context) {
        InvocationHandler handler = new WebElementFacadeHandler(by, context);
        ClassLoader loader = this.getClass().getClassLoader();
        return (WebElementFacade) Proxy.newProxyInstance(loader, new Class[]{WebElementFacade.class}, handler);
    }

    private List<WebElementFacade> facadeList(By by, SearchContext context) {
        InvocationHandler handler = new WebElementListHandler(by, context);
        ClassLoader loader = this.getClass().getClassLoader();
        return (List<WebElementFacade>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }

    private void setField(Object object, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
