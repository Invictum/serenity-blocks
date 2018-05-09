package com.github.invictum;

import com.github.invictum.annotations.Block;
import com.github.invictum.block.BaseBlock;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import net.serenitybdd.core.annotations.findby.FindBy;

@Block(@FindBy(id = "id"))
public class AnnotationDummy extends BaseBlock {

    @FindBy(xpath = "//div")
    private Object xpath;

    @iOSFindBy(accessibility = "test")
    private Object iosAppium;

    @AndroidFindBy(accessibility = "test")
    private Object androidAppium;

    @iOSXCUITBy(iOSClassChain = "**/")
    private Object chain;
}
