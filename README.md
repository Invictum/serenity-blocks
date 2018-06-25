[![Build Status](https://travis-ci.org/Invictum/serenity-blocks.svg?branch=develop)](https://travis-ci.org/Invictum/serenity-blocks)
[![Version](https://img.shields.io/github/release/Invictum/serenity-blocks.svg)](https://github.com/Invictum/serenity-blocks/releases/latest)

Serenity Blocks
=============================================

Module adds missed blocks to Serenity test automation framework. Basically Block itself is a part of web page that may be reused accross tests and isolated in separate class. It is suitable in application when it is dificult to use classical `PageObject` pattern.
For example in one page applications.

Setup
-------------
To turn on Serenity Blocks module support supply dependency to your preffered build tool.

**Maven**

```
<dependency>
    <groupId>com.github.invictum</groupId>
    <artifactId>serenity-blocks</artifactId>
    <version>1.0.0</version>
</dependency>
```

**Gradle**

```
compile 'com.github.invictum:serenity-blocks:1.0.0'
```

Just make sure you are using latest version of a module. It may be checked in the tag in the top of current page or directly at [Maven Central](https://mvnrepository.com/artifact/com.github.invictum/serenity-blocks) repository.

Usage
-----

Let's assume an example application: online books catalog. There is a search widget in the top of a page. It is present on any page of a site, so it is a good candidate to turn it into Block.

First of all you need to create a class that represents a search as a block
```
@Block(@FindBy(css = ".search-panel"))
public class SearchBlock extends RegularBlock {

    @FindBy(css = ".search")
    private WebElementFacade serchInput;

    @FindBy(xpath = "//button[@class = 'go-search']")
    private WebElementFacade serchButton;

    public void fillSearchTerm(String term) {
        serchInput.type(term);
    }

    public void clickSearchButton() {
        serchButton.click();
    }
}
```

Note following aspects:
 - Class is extends `RegularBlock` class. This marks what block type will be used, for more details about available block types reffer to [Block Types](#block-types) section
 - There is a type annotation `@Block(@FindBy(css = ".search-panel"))`. It defines a locator that will be used a root for inner elements search. Locator inside `@Block` is required only for specific types of blocks
 - All other intems is a `SearchBlock` class similar to Serenity's `PageObject` regular class

When block is defined you are ready to use it directly in your tests
```
@RunWith(SerenityRunner.class)
public class BlockTest {

    @Test
    public void someTest() {
        Block.get(SearchBlock.class).fillTerm("Search details");
    }
}
```

Or if you are using Serenity's `ScenarioSteps` it is nice place for block using too
```
public class SearchSteps extends ScenarioSteps {

    @Step
    public void performSearch(String term) {
        SearchBlock searchBlock = Block.get(SearchBlock.class);
        searchBlock.fillTerm(term);
        searchBlock.clickSearchButton();
    }
}
```

Note following points:
 - you don't need to create a `PageObject` and store block inside it. Block is a fully independent facility
 - you don't need to worry about implicit or explicit waits. Blocks uses underlined lazy proxy mechanism and inits objects just before invocation

Block Types
-----------

There are several block types available in a module. Each type provides unique logic and should be used in accordance to test needs.

**Regular Block**

A key feature of Regular blocks that they have a mechanism for root locator defining. As a result all `WebElementFacade` objects inside this block are searched relativety to its root.

```
@Block(@FindBy(css = ".some-panel"))
public class SomeBlock extends RegularBlock {

    @FindBy(css = ".search")
    private WebElementFacade input;
}
```

In code snippet above `input` will be located as `WebDriver -> Element css = .some-panel -> Element css = .search`

By default `@Block` expects `net.serenitybdd.core.annotations.findby.FindBy` inner locator annotation, but it is possible to alter this behaviour and add ability to use you own locators.
See [Custom Annotation Block](#custom-annotation-block) for more details.

**Fragmented Block**

Fragmented block is a some kind of virtual blocks. They don't have a root and all inner elements will be searched from `WebDriver` root. However they usefull as a storage for elements that linked logically.
`@Block` annotation is not mandatory for Fragmented blocks.

**Frame Block**

Frame blocks are not implemented yet.

Custom Annotation Block
-----------------------

`@Block` annotation expects inner `net.serenitybdd.core.annotations.findby.FindBy` annotation as a source of root locators. However sometimes it is nessesary to provide special annoation.

Just for example let's add an Appium's iOS predicate annotation support for custom `@CustomIOSBlock`.

> **Note**
> Following steps described just as an example. Serenity already had Appium support. To use `@iOSXCUITFindBy` just use annotation `@Block(@FindBy(how = How.IOS_PREDICATE, using = "some locator"))`

1. First of all you need to tell to internal Serenity mechanism how to resolve new type of annotation

Implement Serenity's `CustomFindByAnnotationService` with logic for @iOSXCUITFindBy resolving
```
public class IOSXCuteFindByAnnotationService implements CustomFindByAnnotationService {

    @Override
    public boolean isAnnotatedByCustomFindByAnnotation(Field field) {
        // Match on desired annotation
        return field.isAnnotationPresent(iOSXCUITFindBy.class);
    }

    @Override
    public By buildByFromCustomFindByAnnotation(Field field) {
        iOSXCUITFindBy annotation = field.getAnnotation(iOSXCUITFindBy.class);
        String locatorString = annotation.iOSNsPredicate();
        if (!locatorString.isEmpty()) {
            return MobileBy.iOSNsPredicateString(locatorString);
        }
        ...
        throw new IllegalArgumentException("Unable to locate field " + field);
    }
}
```
Register your implementation with service loader.
Let's assume `IOSXCuteFindByAnnotationService` class is located in `com.package` package, so full class name is `com.package.IOSXCuteFindByAnnotationService`.
Create a text file `resources/META-INF/services/net.serenitybdd.core.annotations.findby.di.CustomFindByAnnotationService` with following content `com.pack.IOSXCuteFindByAnnotationService`

All described code is related to pure Serenity, so check [Serenity Guide](http://www.thucydides.info/docs/serenity/) for more details.

2. Define your cistom annotation

```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CustomIOSBlock {
    iOSXCUITFindBy value();
}
```

3. Now you have to describe how to extract locator annotation from custom block annotation

Implement `DefaultBlockEncounter` interface and supply proper logic

```
public class IOSBlockEncounter implements AnnotationBlockEncounter {

    @Override
    public Optional<Annotation> extract(Annotation[] annotations) {
        return Stream.of(annotations).filter(annotation -> annotation.annotationType().equals(CustomIOSBlock.class))
                .findFirst().map(block -> ((CustomIOSBlock) block).value());
    }
}
```

4. In order to use `IOSBlockEncounter` it should be supplied from `BlockEncounterProvider`

```
public class MyEncounterProvider implements BlockEncounterProvider {

    @Override
    public List<AnnotationBlockEncounter> get() {
        return Collections.singletonList(new IOSBlockEncounter());
    }
}
```

5. Finally `BlockEncounterProvider` should be configured in `SerenityBlocksConfiguration`

```
BlockEncounterProvider provider = new MyEncounterProvider();
SerenityBlocksConfiguration.setBlockEncounterProvider(provider);
```

For now all preparation is ready and it is possible to use `@CustomIOSBlock` on your blocks

```
@CustomIOSBlock(@iOSXCUITFindBy(iOSNSPredicate = "some locator"))
public class SomeBlock extends RegularBlock {
    
}
```