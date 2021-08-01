plugins {
    kotlin("jvm")
    `java-library`
    id("name.remal.sonarlint")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
