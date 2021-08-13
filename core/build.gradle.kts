plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    implementation(libs.obersvableCollections)
    implementation(kotlin("reflect"))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
