plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    implementation(projects.swingExtensionsDsl)
    testImplementation(projects.swingExtensionsInspector)
    testImplementation(libs.miglayout)
    testImplementation(libs.darklaf.core) { isChanging = true }
    testImplementation(libs.flatInspector)
}
