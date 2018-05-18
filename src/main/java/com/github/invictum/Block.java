package com.github.invictum;

import com.github.invictum.block.AbstractBlockFactory;
import com.github.invictum.block.BaseBlock;
import com.github.invictum.block.BlockFactory;

/**
 * Entry point facility for creating block instances
 */
public class Block {

    /**
     * Builds a new block instance
     *
     * @param blockType to newInstance, should extend {@link BaseBlock}
     * @param <T>       type
     * @return assembled block
     */
    @SuppressWarnings("unchecked")
    public static <T extends BaseBlock> T get(Class<T> blockType) {
        BlockFactory factory = AbstractBlockFactory.get(blockType);
        return (T) factory.create();
    }
}
