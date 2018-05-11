package com.github.invictum.encounter;

import java.util.List;

/**
 * Provides a list of {@link AnnotationBlockEncounter}s that will be used to locate a block
 */
public interface BlockEncounterProvider {
    List<AnnotationBlockEncounter> get();
}
