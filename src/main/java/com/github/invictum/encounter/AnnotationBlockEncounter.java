package com.github.invictum.encounter;

import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * Analyzes and extracts annotation from a block class
 */
public interface AnnotationBlockEncounter {
    /**
     * Extracts locator based annotation
     *
     * @param annotations collected from block class
     * @return an {@link Optional} of extracted annotation or empty if related annotation is not found
     */
    Optional<Annotation> extract(Annotation[] annotations);
}
