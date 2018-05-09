package com.github.invictum.block.floating;

import com.github.invictum.block.BlockFactory;

public class FloatingBlockFactory<T extends FloatingBlock> extends BlockFactory<T> {

    public FloatingBlockFactory(Class<T> type) {
        super(type);
    }

    @Override
    public T create() {
        return null;
    }
}
