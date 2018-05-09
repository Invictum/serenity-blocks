package com.github.invictum.annotations;

import net.serenitybdd.core.annotations.findby.FindBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to mark block class. Internal {@link FindBy} will be used to determinate block root locator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Block {
    FindBy value();
}
