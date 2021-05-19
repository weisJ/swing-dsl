import com.github.vlsi.gradle.crlf.CrLfSpec
import com.github.vlsi.gradle.crlf.LineEndings

plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    api(projects.swingExtensionsCore)
    api(projects.swingExtensionsLafSupport)
    implementation(libs.miglayout)

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
