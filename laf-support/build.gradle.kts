plugins {
    `java-library`
    `module-info-compile`
}

dependencies {
    implementation(libs.nullabilityAnnotations)
    api(projects.swingExtensionsVisualPadding)
}
