package com.github.invictum.block.regular;

import com.github.invictum.DynamicAnnotations;
import com.github.invictum.ProxyBuilder;
import com.github.invictum.ReflectionUtils;
import com.github.invictum.block.BlockFactory;
import com.github.invictum.populator.Populators;
import com.github.invictum.populator.ListProxied;
import com.github.invictum.populator.SingleProxied;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;

public class RegularBlockFactory<T extends RegularBlock> extends BlockFactory<T> {

    private WebDriver driver;

    public RegularBlockFactory(Class<T> type, WebDriver driver) {
        super(type, driver);
        this.driver = driver;
    }

    @Override
    public T create() {
        // Build underlying block
        By blockBy = new DynamicAnnotations(type, platform).buildBy();
        WebElementFacade block = new ProxyBuilder(blockBy, driver).buildWebElementFacadeProxy();
        // Init block object
        T object = ReflectionUtils.newInstance(type);
        object.setBlock(block);
        Populators populators = Populators.prepare(new SingleProxied(platform, block), new ListProxied(platform, block));
        Stream.of(type.getDeclaredFields()).forEach(field -> populators.proceed(field, object));
        return object;
    }
}
