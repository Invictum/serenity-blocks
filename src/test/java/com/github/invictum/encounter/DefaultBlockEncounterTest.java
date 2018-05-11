package com.github.invictum.encounter;

import com.github.invictum.AnnotationDummy;
import com.github.invictum.annotations.Block;
import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Optional;

public class DefaultBlockEncounterTest {

    @Test
    public void defaultEncounter() {
        AnnotationBlockEncounter encounter = new DefaultBlockEncounter();
        Optional<Annotation> annotation = encounter.extract(AnnotationDummy.class.getAnnotations());
        Annotation actual = annotation.orElseThrow(() -> new IllegalArgumentException("Extraction is failed"));
        Annotation expected = AnnotationDummy.class.getAnnotation(Block.class).value();
        Assert.assertEquals(expected, actual);
    }
}
