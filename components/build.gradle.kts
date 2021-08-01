import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    `java-library`
    id("name.remal.sonarlint")
}

dependencies {
    api(projects.swingExtensionsLafSupport)
    implementation(projects.swingExtensionsUtil)
    implementation(libs.darklaf.propertyLoader)
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
