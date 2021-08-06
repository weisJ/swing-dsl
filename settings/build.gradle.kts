plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    implementation(projects.swingExtensionsDsl)

    testImplementation(projects.swingExtensionsInspector)
    testImplementation(libs.miglayout)
    testImplementation(libs.darklaf.core)
    testImplementation(libs.flatInspector)
}
