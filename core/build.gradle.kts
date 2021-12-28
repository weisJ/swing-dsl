plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    implementation(libs.observableCollections)
    implementation(kotlin("reflect"))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
