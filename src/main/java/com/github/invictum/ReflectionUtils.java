package com.github.invictum;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ReflectionUtils {

    /**
     * Utilizes reflection to set specific {@link Field} to specific value
     */
    public static void setField(Object object, Field field, Object value) {
        try {
            FieldUtils.writeField(field, object, value, true);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unable to set field value", e);
        }
    }

    /**
     * Uses reflection to create an instance of given type with default constructor
     */
    public static <T> T newInstance(Class<T> type) {
        try {
            Constructor<T> constructor = type.getConstructor();
            return constructor.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Unable to init an object of " + type);
        }
    }
}
