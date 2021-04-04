plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    implementation(project(":swing-dsl"))
    testImplementation("com.miglayout:miglayout-swing")
    testImplementation("com.github.weisj:darklaf-core") {
        isChanging = true
    }
}
