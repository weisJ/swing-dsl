plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    implementation(projects.swingExtensionsDsl)
    implementation(libs.darklaf.propertyLoader)
    compileOnly(libs.svgSalamander)
    implementation(libs.miglayout)
    testImplementation(libs.darklaf.core)
}
