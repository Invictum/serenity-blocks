package com.github.invictum;

import com.github.invictum.annotations.Block;
import com.github.invictum.block.BaseBlock;
import net.serenitybdd.core.annotations.findby.di.CustomFindByAnnotationProviderService;
import net.serenitybdd.core.annotations.locators.SmartAnnotations;
import net.thucydides.core.webdriver.MobilePlatform;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

/**
 * Extends {@link SmartAnnotations} functionality with ability to build locators from block classes
 */
public class EnhancedAnnotations extends SmartAnnotations {

    private Class<? extends BaseBlock> blockType;

    public EnhancedAnnotations(Field field, MobilePlatform platform) {
        super(field, platform);
    }

    public EnhancedAnnotations(Field field, MobilePlatform platform, CustomFindByAnnotationProviderService customFindByAnnotationProviderService) {
        super(field, platform, customFindByAnnotationProviderService);
    }

    public EnhancedAnnotations(Class<? extends BaseBlock> blockType) {
        super(null, null);
        this.blockType = blockType;
    }

    @Override
    public By buildBy() {
        return blockType == null ? super.buildBy() : buildByFromBlock();
    }

    private By buildByFromBlock() {
        com.github.invictum.annotations.Block block = blockType.getAnnotation(Block.class);
        if (block != null) {
            return super.buildByFromFindBy(block.value());
        }
        throw new IllegalStateException("Unable to build locator for " + blockType);
    }
}
