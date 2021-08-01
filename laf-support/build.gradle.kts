plugins {
    `java-library`
    `module-info-compile`
}

dependencies {
    compileOnly(libs.nullabilityAnnotations)
    api(projects.swingExtensionsVisualPadding)
}
