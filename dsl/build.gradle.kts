import com.github.vlsi.gradle.crlf.CrLfSpec
import com.github.vlsi.gradle.crlf.LineEndings

plugins {
    `java-library`
    kotlin("jvm")
}

dependencies {
    api(project(":swing-dsl-core"))
    implementation(project(":swing-dsl-laf-support"))
    implementation("com.miglayout:miglayout-swing")
    compileOnly(kotlin("stdlib-jdk8"))

    testImplementation(kotlin("stdlib-jdk8"))
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
