package com.github.invictum.block;

import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BaseBlockTest {

    @Test
    public void asWebElementFacade() {
        BaseBlock baseBlock = new BaseBlock();
        WebElementFacade elementMock = Mockito.mock(WebElementFacade.class);
        baseBlock.setBlock(elementMock);
        Assert.assertEquals(elementMock, baseBlock.asWebElementFacade());
    }

    @Test(expected = IllegalStateException.class)
    public void setBlockMultiple() {
        BaseBlock baseBlock = new BaseBlock();
        WebElementFacade elementMock = Mockito.mock(WebElementFacade.class);
        baseBlock.setBlock(elementMock);
        baseBlock.setBlock(elementMock);
    }
}
