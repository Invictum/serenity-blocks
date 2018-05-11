package com.github.invictum;

import com.github.invictum.encounter.BlockEncounterProvider;
import com.github.invictum.encounter.EmptyEncounterProvider;

/**
 * Entry point to work with module configurations
 */
public class SerenityBlocksConfiguration {

    private static BlockEncounterProvider blockEncounterProvider = new EmptyEncounterProvider();

    public static BlockEncounterProvider getBlockEncounterProvider() {
        return blockEncounterProvider;
    }

    public static void setBlockEncounterProvider(BlockEncounterProvider blockEncounterProvider) {
        SerenityBlocksConfiguration.blockEncounterProvider = blockEncounterProvider;
    }
}
