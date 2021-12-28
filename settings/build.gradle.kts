plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    implementation(projects.swingExtensionsDsl)

    testImplementation(projects.swingExtensionsInspector)
    testImplementation(libs.miglayout)
    testImplementation(testLibs.darklaf.core)
    testImplementation(testLibs.flatInspector)
}
