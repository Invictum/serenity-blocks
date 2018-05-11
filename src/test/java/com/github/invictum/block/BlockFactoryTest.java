package com.github.invictum.block;

import com.github.invictum.AnnotationDummy;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

public class BlockFactoryTest {

    // Mock to avoid regular object init
    private BlockFactory factory = Mockito.mock(BlockFactory.class);

    @Test
    public void compatibleList() throws NoSuchFieldException {
        Field field = AnnotationDummy.class.getField("compatibleList");
        Mockito.when(factory.isListCompatible(field)).thenCallRealMethod();
        Assert.assertTrue(factory.isListCompatible(field));
    }

    @Test
    public void notCompatibleList() throws NoSuchFieldException {
        Field field = AnnotationDummy.class.getField("incompatibleList");
        Mockito.when(factory.isListCompatible(field)).thenCallRealMethod();
        Assert.assertFalse(factory.isListCompatible(field));
    }

    @Test
    public void notListAtAll() throws NoSuchFieldException {
        Field field = AnnotationDummy.class.getField("xpath");
        Mockito.when(factory.isListCompatible(field)).thenCallRealMethod();
        Assert.assertFalse(factory.isListCompatible(field));
    }
}
