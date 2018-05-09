package com.github.invictum;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementFacadeImpl;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementFacadeUtil {

    /**
     * Wraps an element into {@link WebElementFacade} with default values
     *
     * @param webElement to wrap
     * @return ready to use {@link WebElementFacade} instance
     */
    public static WebElementFacade wrapWebElement(WebElement webElement) {
        WebDriver driver = ThucydidesWebDriverSupport.getDriver();
        long timeout = DriverUtils.getTimeout(driver);
        return WebElementFacadeImpl.wrapWebElement(driver, webElement, timeout);
    }
}
