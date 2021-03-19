apply(from= "../gradle/loadProps.gradle.kts")

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(kotlin("gradle-plugin"))
}

repositories {
    mavenCentral()
    jcenter()
    gradlePluginPortal()
}

configure<KotlinDslPluginOptions> {
    experimentalWarning.set(false)
}
