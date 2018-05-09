package com.github.invictum;

import io.appium.java_client.MobileBy;
import net.thucydides.core.webdriver.MobilePlatform;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

public class EnhancedAnnotationsTest {

    @Test
    public void buildByBasic() throws NoSuchFieldException {
        Field field = AnnotationDummy.class.getDeclaredField("xpath");
        By actual = new EnhancedAnnotations(field, MobilePlatform.NONE).buildBy();
        Assert.assertEquals(net.serenitybdd.core.annotations.findby.By.xpath("//div"), actual);
    }

    @Test
    public void buildByIosAppium() throws NoSuchFieldException {
        Field field = AnnotationDummy.class.getDeclaredField("iosAppium");
        By actual = new EnhancedAnnotations(field, MobilePlatform.IOS).buildBy();
        Assert.assertEquals(MobileBy.AccessibilityId("test"), actual);
    }

    @Test
    public void buildByAndroidAppium() throws NoSuchFieldException {
        Field field = AnnotationDummy.class.getDeclaredField("androidAppium");
        By actual = new EnhancedAnnotations(field, MobilePlatform.ANDROID).buildBy();
        Assert.assertEquals(MobileBy.AccessibilityId("test"), actual);
    }

    @Test
    public void buildByFromBlock() {
        By actual = new EnhancedAnnotations(AnnotationDummy.class).buildBy();
        Assert.assertEquals(By.id("id"), actual);
    }

    @Test(expected = IllegalStateException.class)
    public void buildByFromBlockInvalid() {
        new EnhancedAnnotations(AnnotationDummyInvalid.class).buildBy();
    }
}
