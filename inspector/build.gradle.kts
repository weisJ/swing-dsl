import com.github.vlsi.gradle.crlf.CrLfSpec
import com.github.vlsi.gradle.crlf.LineEndings

plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    implementation(projects.swingExtensionsDsl)
    implementation(libs.darklaf.propertyLoader)
    compileOnly(libs.svgSalamander)
    implementation(libs.miglayout)

    testImplementation(testLibs.darklaf.core)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

fun Jar.includeLicenses() {
    CrLfSpec(LineEndings.LF).run {
        into("META-INF") {
            filteringCharset = "UTF-8"
            textFrom("$rootDir/licenses/INTELLIJ_LICENSE.txt")
            textFrom("$rootDir/licenses/INTELLIJ_NOTICE.txt")
        }
    }
}
