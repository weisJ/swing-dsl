[![CI](https://github.com/weisJ/swing-dsl/actions/workflows/gradle.yml/badge.svg)](https://github.com/weisJ/swing-dsl/actions/workflows/gradle.yml)
[![Autostyle](https://github.com/weisJ/swing-dsl/actions/workflows/autostyle.yml/badge.svg)](https://github.com/weisJ/swing-dsl/actions/workflows/autostyle.yml)
[![Sonarlint](https://github.com/weisJ/swing-dsl/actions/workflows/sonarlint.yml/badge.svg)](https://github.com/weisJ/swing-dsl/actions/workflows/sonarlint.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.weisj/swing-extensions-dsl?label=Maven%20Central)](https://search.maven.org/artifact/com.github.weisj/swing-extensions-dsl)

# Swing DSL

Note: This library is still in an early phase and the API may change drastically between versions.

A standalone library port of the IntelliJ plugin api UI builder dsl.

See https://plugins.jetbrains.com/docs/intellij/kotlin-ui-dsl.html for more information.

This library doesn't implement the full DSL as available in the IntelliJ API as this library was created
to meet my own demands for a DSL, and I did need the additional functionality. If there is interest in some specific
features fell free to create an issue for it.

The other main difference to the IntelliJ API is the usage of [`Text`](https://github.com/weisJ/swing-dsl/blob/master/core/src/main/kotlin/com/github/weisj/swingdsl/text/Text.kt) instead of `String` for displayed text 
content (although 'String' is still supported). To support content localization without having to rebuild the entire UI or
manually update all components one can make use of the [`InternationalizedText`](https://github.com/weisJ/swing-dsl/blob/master/core/src/main/kotlin/com/github/weisj/swingdsl/text/InternationalizedText.kt).

Currently, the produced layout is only tested with [Darklaf](https://github.com/weisJ/darklaf) however it should also work with almost
all other LaFs. For better layout results LaFs can implement [`VisualPaddingProvider`](https://github.com/weisJ/swing-dsl/blob/master/visual-padding/src/main/java/com/github/weisj/swingdsl/visualPadding/VisualPaddingProvider.java) for
their borders.

To provide more flexibility with the kind of components used for the UI one can specify a custom [`ComponentFactory`](https://github.com/weisJ/swing-dsl/blob/master/laf-support/src/main/java/com/github/weisj/swingdsl/laf/ComponentFactory.java).
