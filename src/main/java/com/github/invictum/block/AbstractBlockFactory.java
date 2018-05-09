package com.github.invictum.block;

import com.github.invictum.block.floating.FloatingBlock;
import com.github.invictum.block.floating.FloatingBlockFactory;
import com.github.invictum.block.regular.RegularBlock;
import com.github.invictum.block.regular.RegularBlockFactory;

public class AbstractBlockFactory {

    /**
     * Chooses proper instance of {@link BlockFactory} to use for future block creation
     *
     * @param type of block to create
     * @param <T>  type of {@link BaseBlock}
     * @return situated factory instance
     */
    @SuppressWarnings("unchecked")
    public static <T extends BaseBlock> BlockFactory get(Class<T> type) {
        if (RegularBlock.class.isAssignableFrom(type)) {
            return new RegularBlockFactory(type);
        }
        if (FloatingBlock.class.isAssignableFrom(type)) {
            return new FloatingBlockFactory(type);
        }
        throw new IllegalStateException("Unable to find factory for " + type);
    }
}
