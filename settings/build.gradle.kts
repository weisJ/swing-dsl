plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    implementation(projects.swingExtensionsDsl)
    testImplementation(libs.miglayout)
    testImplementation(libs.darklaf.core) {
        isChanging = true
    }
}
