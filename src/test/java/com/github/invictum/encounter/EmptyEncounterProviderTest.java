package com.github.invictum.encounter;

import org.junit.Assert;
import org.junit.Test;

public class EmptyEncounterProviderTest {

    @Test
    public void emptyEncounterProvider() {
        BlockEncounterProvider encounterProvider = new EmptyEncounterProvider();
        Assert.assertTrue(encounterProvider.get().isEmpty());
    }
}
