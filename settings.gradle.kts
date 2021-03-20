pluginManagement {
    plugins {
        fun String.v() = extra["$this.version"].toString()
        fun PluginDependenciesSpec.idv(id: String, key: String = id) = id(id) version key.v()

        kotlin("jvm") version "kotlin".v()
        kotlin("kapt") version "kotlin".v()
        idv("com.github.autostyle")
        idv("com.github.vlsi.crlf", "com.github.vlsi.vlsi-release-plugins")
        idv("com.github.vlsi.gradle-extensions", "com.github.vlsi.vlsi-release-plugins")
        idv("com.github.vlsi.license-gather", "com.github.vlsi.vlsi-release-plugins")
        idv("com.github.vlsi.stage-vote-release", "com.github.vlsi.vlsi-release-plugins")
        idv("name.remal.sonarlint")
    }
}

rootProject.name = "swing-dsl"

include(
    "dependencies-bom",
    "core",
    "laf-support",
    "dsl"
)

for (p in rootProject.children) {
    // Rename leaf projects only
    // E.g. we don't expect to publish examples as a Maven module
    when {
        p.name == "dsl" -> p.name = "swing-dsl"
        p.children.isEmpty() && p.name != "dependencies-bom" -> p.name = "swing-dsl" + "-" + p.name
    }
}
