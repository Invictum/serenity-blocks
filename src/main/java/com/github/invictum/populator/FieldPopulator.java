package com.github.invictum.populator;

import java.lang.reflect.Field;

public interface FieldPopulator {
    /**
     * Try to fill specified {@link Field} for specified {@link Object} target
     *
     * @param field  to set
     * @param target to set
     * @return true is process was successful, false otherwise
     */
    boolean fill(Field field, Object target);
}
