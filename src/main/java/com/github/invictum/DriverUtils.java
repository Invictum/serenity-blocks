package com.github.invictum;

import net.thucydides.core.webdriver.MobilePlatform;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

public class DriverUtils {

    /**
     * Returns mobile platform based on {@link WebDriver} instance
     *
     * @param driver instance to check
     * @return detected {@link MobilePlatform}
     */
    public static MobilePlatform resolvePlatform(WebDriver driver) {
        String driverName = driver.getClass().getSimpleName();
        if (StringUtils.containsIgnoreCase(driverName, "iOS")) {
            return MobilePlatform.IOS;
        }
        if (StringUtils.containsIgnoreCase(driverName, "Android")) {
            return MobilePlatform.ANDROID;
        }
        return MobilePlatform.NONE;
    }

    /**
     * Returns default timeout if possible or default value in ms
     *
     * @param driver to extract timeout from
     * @return calculated timeout in ms
     */
    public static long getTimeout(WebDriver driver) {
        if (driver instanceof WebDriverFacade) {
            return ((WebDriverFacade) driver).getCurrentImplicitTimeout().toMillis();
        } else {
            return 10000;
        }
    }
}
