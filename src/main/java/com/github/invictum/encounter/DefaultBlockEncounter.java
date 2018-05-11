package com.github.invictum.encounter;

import com.github.invictum.annotations.Block;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Default {@link AnnotationBlockEncounter} implementation
 * Extracts any underlying {@link Annotation} under {@link Block}
 */
public class DefaultBlockEncounter implements AnnotationBlockEncounter {

    @Override
    public Optional<Annotation> extract(Annotation[] annotations) {
        return Stream.of(annotations).filter(annotation -> annotation.annotationType().equals(Block.class))
                .findFirst().map(block -> ((Block) block).value());
    }
}
