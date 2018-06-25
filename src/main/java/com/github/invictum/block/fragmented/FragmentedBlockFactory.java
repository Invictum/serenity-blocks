package com.github.invictum.block.fragmented;

import com.github.invictum.ReflectionUtils;
import com.github.invictum.block.BlockFactory;
import com.github.invictum.populator.Populators;
import com.github.invictum.populator.ListProxied;
import com.github.invictum.populator.SingleProxied;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;

public class FragmentedBlockFactory<T extends FragmentedBlock> extends BlockFactory<T> {

    private WebDriver driver;

    public FragmentedBlockFactory(Class<T> type, WebDriver driver) {
        super(type, driver);
        this.driver = driver;
    }

    @Override
    public T create() {
        T object = ReflectionUtils.newInstance(type);
        Populators populators = Populators.prepare(new SingleProxied(platform, driver), new ListProxied(platform, driver));
        Stream.of(type.getDeclaredFields()).forEach(field -> populators.proceed(field, object));
        return object;
    }
}
