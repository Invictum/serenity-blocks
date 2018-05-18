package com.github.invictum.fillers;

import com.github.invictum.AnnotationDummy;
import com.github.invictum.filler.FieldFiller;
import com.github.invictum.filler.Fillers;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

public class FillersTest {

    @Test
    public void proceed() throws NoSuchFieldException {
        FieldFiller mock = Mockito.mock(FieldFiller.class);
        Field field = AnnotationDummy.class.getDeclaredField("xpath");
        Object target = new Object();
        Fillers.prepare(mock).proceed(field, target);
        Mockito.verify(mock).fill(field, target);
    }

    @Test
    public void stopFill() throws NoSuchFieldException {
        FieldFiller mockOne = Mockito.mock(FieldFiller.class);
        Mockito.when(mockOne.fill(Mockito.any(), Mockito.any())).thenReturn(true);
        FieldFiller mockTwo = Mockito.mock(FieldFiller.class);
        Field field = AnnotationDummy.class.getDeclaredField("xpath");
        Object target = new Object();
        Fillers.prepare(mockOne, mockTwo).proceed(field, target);
        Mockito.verify(mockTwo, Mockito.never()).fill(field, target);
    }
}
