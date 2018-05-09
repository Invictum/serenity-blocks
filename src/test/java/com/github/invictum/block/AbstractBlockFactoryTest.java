package com.github.invictum.block;

import com.github.invictum.block.floating.FloatingBlock;
import com.github.invictum.block.floating.FloatingBlockFactory;
import com.github.invictum.block.regular.RegularBlock;
import com.github.invictum.block.regular.RegularBlockFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class AbstractBlockFactoryTest {

    private Class type;
    private BlockFactory factory;

    public AbstractBlockFactoryTest(Class type, BlockFactory factory) {
        this.type = type;
        this.factory = factory;
    }

    @Test
    public void get() {
        BlockFactory actual = AbstractBlockFactory.get(type);
        Assert.assertEquals(factory, actual);
    }

    @Parameterized.Parameters(name = "{0} - {1}")
    public static Object[][] data() {
        return new Object[][]{
                {RegularBlock.class, new RegularBlockFactory(RegularBlock.class)},
                {FloatingBlock.class, new FloatingBlockFactory(FloatingBlock.class)}
        };
    }
}
