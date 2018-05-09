package com.github.invictum.block;

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
}
