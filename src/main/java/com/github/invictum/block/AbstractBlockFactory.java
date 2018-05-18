package com.github.invictum.block;

import com.github.invictum.block.fragmented.FragmentedBlock;
import com.github.invictum.block.fragmented.FragmentedBlockFactory;
import com.github.invictum.block.regular.RegularBlock;
import com.github.invictum.block.regular.RegularBlockFactory;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.WebDriver;

public class AbstractBlockFactory {

    /**
     * Chooses proper instance of {@link BlockFactory} to use for future block creation
     *
     * @param type of block to create
     * @param <T>  type of {@link BaseBlock}
     * @return situated factory instance
     */
    @SuppressWarnings("unchecked")
    public static <T extends BaseBlock> BlockFactory get(Class<T> type) {
        WebDriver driver = ThucydidesWebDriverSupport.getDriver();
        if (RegularBlock.class.isAssignableFrom(type)) {
            return new RegularBlockFactory(type, driver);
        }
        if (FragmentedBlock.class.isAssignableFrom(type)) {
            return new FragmentedBlockFactory(type, driver);
        }
        throw new IllegalStateException("Unable to find factory for " + type);
    }
}
