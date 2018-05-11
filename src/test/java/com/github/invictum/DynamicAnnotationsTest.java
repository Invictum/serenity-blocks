package com.github.invictum;

import io.appium.java_client.MobileBy;
import net.thucydides.core.webdriver.MobilePlatform;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

public class DynamicAnnotationsTest {

    @Test
    public void buildForField() throws NoSuchFieldException {
        Field field = AnnotationDummy.class.getDeclaredField("xpath");
        By actual = new DynamicAnnotations(field, MobilePlatform.NONE).buildBy();
        Assert.assertEquals(By.xpath("//div"), actual);
    }

    @Test
    public void buildForFieldIos() throws NoSuchFieldException {
        Field field = AnnotationDummy.class.getDeclaredField("iosAppium");
        By actual = new DynamicAnnotations(field, MobilePlatform.IOS).buildBy();
        Assert.assertEquals(MobileBy.AccessibilityId("test"), actual);
    }

    @Test
    public void buildForFieldAndroid() throws NoSuchFieldException {
        Field field = AnnotationDummy.class.getDeclaredField("androidAppium");
        By actual = new DynamicAnnotations(field, MobilePlatform.ANDROID).buildBy();
        Assert.assertEquals(MobileBy.AccessibilityId("test"), actual);
    }

    @Test
    public void buildForType() {
        By actual = new DynamicAnnotations(AnnotationDummy.class, MobilePlatform.ANDROID).buildBy();
        Assert.assertEquals(By.id("id"), actual);
    }
}
