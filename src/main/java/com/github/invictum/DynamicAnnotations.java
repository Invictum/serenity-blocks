package com.github.invictum;

import com.github.invictum.annotations.Block;
import com.github.invictum.block.BaseBlock;
import com.github.invictum.encounter.AnnotationBlockEncounter;
import com.github.invictum.encounter.DefaultBlockEncounter;
import net.serenitybdd.core.annotations.locators.SmartAnnotations;
import net.thucydides.core.webdriver.MobilePlatform;
import org.mockito.Mockito;
import org.openqa.selenium.By;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Builds {@link By} from any context. For now includes {@link Field} and {@link Class}
 */
public class DynamicAnnotations {

    private SmartAnnotations smartAnnotations;

    public DynamicAnnotations(Field field, MobilePlatform platform) {
        smartAnnotations = new SmartAnnotations(field, platform);
    }

    /**
     * Transfer locator annotation under {@link Block} using mocked {@link Field} object
     * Allows to resolve locators with integrated Serenity's facility
     *
     * @param blockType to resole locator for
     * @param platform  detected from {@link org.openqa.selenium.WebDriver}
     */
    public DynamicAnnotations(Class<? extends BaseBlock> blockType, MobilePlatform platform) {
        Annotation[] tags = blockType.getAnnotations();
        for (AnnotationBlockEncounter encounter : SerenityBlocksConfiguration.getBlockEncounterProvider().get()) {
            Optional<Annotation> annotation = encounter.extract(tags);
            if (annotation.isPresent()) {
                initWith(annotation.get(), platform);
                return;
            }
        }
        // Last chance with default implementation
        Supplier<IllegalStateException> supplier = () -> new IllegalStateException("Unable to find a way to locate " + blockType);
        Annotation locator = new DefaultBlockEncounter().extract(tags).orElseThrow(supplier);
        initWith(locator, platform);
    }

    public By buildBy() {
        return smartAnnotations.buildBy();
    }

    /**
     * Creates a fake field that passes annotation details to {@link SmartAnnotations} to extract locator
     *
     * @param annotation to pass
     * @param platform   to use
     */
    private void initWith(Annotation annotation, MobilePlatform platform) {
        Field field = Mockito.mock(Field.class);
        Mockito.when(field.getAnnotation(annotation.annotationType())).thenAnswer(invocation -> annotation);
        smartAnnotations = new SmartAnnotations(field, platform);
    }
}
