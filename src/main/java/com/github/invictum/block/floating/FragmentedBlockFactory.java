package com.github.invictum.block.floating;

import com.github.invictum.block.BlockFactory;

public class FragmentedBlockFactory<T extends FragmentedBlock> extends BlockFactory<T> {

    public FragmentedBlockFactory(Class<T> type) {
        super(type);
    }

    @Override
    public T create() {
        return null;
    }
}
