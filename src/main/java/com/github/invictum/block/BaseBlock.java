package com.github.invictum.block;

import net.serenitybdd.core.pages.WebElementFacade;

/**
 * Parent class for all blocks
 */
public class BaseBlock {

    private WebElementFacade block;

    /**
     * Represents block object as an underlying {@link WebElementFacade} item
     *
     * @return block representation
     */
    protected WebElementFacade asWebElementFacade() {
        return block;
    }

    /**
     * Sets block underlying representation
     * Used for initialization and allows only one set. Throws {@link IllegalStateException} during attempt to set block twice
     *
     * @param block to use as underlying representation
     */
    public void setBlock(WebElementFacade block) {
        if (this.block != null) {
            throw new IllegalStateException("Underlying block is already defined");
        }
        this.block = block;
    }
}
