package com.github.invictum.fillers;

import com.github.invictum.AnnotationDummy;
import com.github.invictum.populator.FieldPopulator;
import com.github.invictum.populator.Populators;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

public class PopulatorsTest {

    @Test
    public void proceed() throws NoSuchFieldException {
        FieldPopulator mock = Mockito.mock(FieldPopulator.class);
        Field field = AnnotationDummy.class.getDeclaredField("xpath");
        Object target = new Object();
        Populators.prepare(mock).proceed(field, target);
        Mockito.verify(mock).fill(field, target);
    }

    @Test
    public void stopFill() throws NoSuchFieldException {
        FieldPopulator mockOne = Mockito.mock(FieldPopulator.class);
        Mockito.when(mockOne.fill(Mockito.any(), Mockito.any())).thenReturn(true);
        FieldPopulator mockTwo = Mockito.mock(FieldPopulator.class);
        Field field = AnnotationDummy.class.getDeclaredField("xpath");
        Object target = new Object();
        Populators.prepare(mockOne, mockTwo).proceed(field, target);
        Mockito.verify(mockTwo, Mockito.never()).fill(field, target);
    }
}
