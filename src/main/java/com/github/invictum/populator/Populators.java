package com.github.invictum.populator;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Populators {

    private List<FieldPopulator> registered;

    private Populators(FieldPopulator... populators) {
        registered = Stream.of(populators).collect(Collectors.toList());
    }

    /**
     * Try to fill passed {@link Field} {@link Object} combination with all registered {@link FieldPopulator}s
     */
    public void proceed(Field field, Object target) {
        for (FieldPopulator filler : registered) {
            if (filler.fill(field, target)) {
                break;
            }
        }
    }

    /**
     * Creates an instance of {@link Populators}
     */
    public static Populators prepare(FieldPopulator... fillers) {
        return new Populators(fillers);
    }
}
