plugins {
    kotlin("jvm")
    `java-library`
    id("name.remal.sonarlint")
}

dependencies {
    implementation(projects.swingExtensionsDsl)

    testImplementation(projects.swingExtensionsInspector)
    testImplementation(libs.miglayout)
    testImplementation(libs.darklaf.core)
    testImplementation(libs.flatInspector)
}
