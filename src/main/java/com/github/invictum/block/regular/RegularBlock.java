package com.github.invictum.block.regular;

import com.github.invictum.block.BaseBlock;
import net.serenitybdd.core.pages.WebElementFacade;

public class RegularBlock extends BaseBlock {

    private WebElementFacade block;

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

    /**
     * Represents block object as an underlying {@link WebElementFacade} item
     *
     * @return block representation
     */
    protected WebElementFacade asWebElementFacade() {
        return block;
    }
}
