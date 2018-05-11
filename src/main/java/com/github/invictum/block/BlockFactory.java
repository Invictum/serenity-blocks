package com.github.invictum.block;

import net.serenitybdd.core.pages.WebElementFacade;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Parent factory for blocks
 * Implements some similar routines
 *
 * @param <T> a type of block class to use with current factory
 */
public abstract class BlockFactory<T extends BaseBlock> {

    protected Class<T> type;

    public BlockFactory(Class<T> type) {
        this.type = type;
    }

    /**
     * Creates a Block object
     *
     * @return ready to use block
     */
    public abstract T create();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockFactory)) return false;
        BlockFactory<?> that = (BlockFactory<?>) o;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    /**
     * Checks filed is compatible with {@link List} of {@link WebElementFacade}
     *
     * @param field to check
     * @return true if field is compatible, false otherwise
     */
    protected boolean isListCompatible(Field field) {
        if (List.class.isAssignableFrom(field.getType())) {
            ParameterizedType listType = (ParameterizedType) field.getGenericType();
            Class<?> type = (Class<?>) listType.getActualTypeArguments()[0];
            return WebElementFacade.class.isAssignableFrom(type);
        }
        return false;
    }
}
