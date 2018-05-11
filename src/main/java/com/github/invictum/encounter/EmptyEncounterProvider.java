package com.github.invictum.encounter;

import java.util.ArrayList;
import java.util.List;

public class EmptyEncounterProvider implements BlockEncounterProvider {

    @Override
    public List<AnnotationBlockEncounter> get() {
        return new ArrayList<>();
    }
}
