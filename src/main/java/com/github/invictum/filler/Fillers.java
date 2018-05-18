package com.github.invictum.filler;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Fillers {

    private List<FieldFiller> registered;

    private Fillers(FieldFiller... fillers) {
        registered = Stream.of(fillers).collect(Collectors.toList());
    }

    /**
     * Try to fill passed {@link Field} {@link Object} combination with all registered {@link FieldFiller}s
     */
    public void proceed(Field field, Object target) {
        for (FieldFiller filler : registered) {
            if (filler.fill(field, target)) {
                break;
            }
        }
    }

    /**
     * Creates an instance of {@link Fillers}
     */
    public static Fillers prepare(FieldFiller... fillers) {
        return new Fillers(fillers);
    }
}
