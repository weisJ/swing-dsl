plugins {
    kotlin("jvm")
    `java-library`
    id("name.remal.sonarlint")
}

dependencies {
    implementation(libs.obersvableCollections)
    implementation(kotlin("reflect"))
}
