import com.github.vlsi.gradle.crlf.CrLfSpec
import com.github.vlsi.gradle.crlf.LineEndings

plugins {
    kotlin("jvm")
    `java-library`
    id("name.remal.sonarlint")
}

dependencies {
    api(projects.swingExtensionsCore)
    api(projects.swingExtensionsLafSupport)
    api(projects.swingExtensionsComponents)
    api(projects.swingExtensionsUtil)
    implementation(libs.darklaf.propertyLoader)
    implementation(libs.miglayout)
    implementation(libs.fuzzySearch)

    testImplementation(libs.darklaf.core) {
        isChanging = true
    }
    testImplementation(kotlin("reflect"))
}

fun Jar.includeLicenses() {
    CrLfSpec(LineEndings.LF).run {
        into("META-INF") {
            filteringCharset = "UTF-8"
            textFrom("$rootDir/licenses/INTELLIJ_LICENSE.txt")
            textFrom("$rootDir/licenses/INTELLIJ_NOTICE.txt")
            textFrom("$rootDir/licenses/MIGLAYOUT_LICENSE.txt")
        }
    }
}

tasks.jar {
    includeLicenses()
}
