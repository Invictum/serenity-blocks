package com.github.invictum.block.regular;

import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class RegularBlockTest {

    @Test
    public void asWebElementFacade() {
        RegularBlock baseBlock = new RegularBlock();
        WebElementFacade elementMock = Mockito.mock(WebElementFacade.class);
        baseBlock.setBlock(elementMock);
        Assert.assertEquals(elementMock, baseBlock.asWebElementFacade());
    }

    @Test(expected = IllegalStateException.class)
    public void setBlockMultiple() {
        RegularBlock baseBlock = new RegularBlock();
        WebElementFacade elementMock = Mockito.mock(WebElementFacade.class);
        baseBlock.setBlock(elementMock);
        baseBlock.setBlock(elementMock);
    }
}
