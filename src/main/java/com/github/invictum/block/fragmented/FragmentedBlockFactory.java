package com.github.invictum.block.fragmented;

import com.github.invictum.ReflectionUtils;
import com.github.invictum.block.BlockFactory;
import com.github.invictum.filler.Fillers;
import com.github.invictum.filler.ListProxied;
import com.github.invictum.filler.SingleProxied;
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
        Fillers fillers = Fillers.prepare(new SingleProxied(platform, driver), new ListProxied(platform, driver));
        Stream.of(type.getDeclaredFields()).forEach(field -> fillers.proceed(field, object));
        return object;
    }
}
