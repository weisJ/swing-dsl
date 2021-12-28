enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("VERSION_CATALOGS")
rootProject.name = "swing-extensions"

pluginManagement {
    plugins {
        fun String.v() = extra["$this.version"].toString()
        fun PluginDependenciesSpec.idv(id: String, key: String = id) = id(id) version key.v()

        kotlin("jvm") version "kotlin".v()
        idv("com.github.autostyle")
        idv("com.github.vlsi.crlf", "com.github.vlsi.vlsi-release-plugins")
        idv("com.github.vlsi.gradle-extensions", "com.github.vlsi.vlsi-release-plugins")
        idv("com.github.vlsi.license-gather", "com.github.vlsi.vlsi-release-plugins")
        idv("com.github.vlsi.stage-vote-release", "com.github.vlsi.vlsi-release-plugins")
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        fun VersionCatalogBuilder.idv(name: String, coordinates: String, versionRef: String = name) {
            val parts = coordinates.split(':', limit = 2)
            alias(name).to(parts[0], parts[1]).version(extra["$versionRef.version"].toString())
        }
        class VersionBundle(val bundleName: String, val builder: VersionCatalogBuilder) {
            val libs = mutableListOf<String>()
            fun idv(name: String, coordinates: String, versionRef: String = bundleName) =
                builder.idv("$bundleName-$name".also { libs.add(it) }, coordinates, versionRef)
        }
        fun VersionCatalogBuilder.bundle(name: String, init: VersionBundle.() -> Unit) = VersionBundle(name, this).run {
            init()
            bundle(name, libs)
        }

        create("libs") {
            idv("darklaf-propertyLoader", "com.github.weisj:darklaf-property-loader", "darklaf")
            idv("miglayout", "com.miglayout:miglayout-swing")
            idv("svgSalamander", "com.formdev:svgSalamander")
            idv("nullabilityAnnotations", "org.jetbrains:annotations")
            idv("observableCollections", "net.pearx.okservable:okservable-jvm")
            idv("fuzzySearch", "me.xdrop:fuzzywuzzy", "fuzzywuzzy")
        }
        create("testLibs") {
            idv("darklaf-core", "com.github.weisj:darklaf-core", "darklaf")
            idv("flatInspector", "com.formdev:flatlaf-extras")
            bundle("junit") {
                idv("api", "org.junit.jupiter:junit-jupiter-api")
                idv("engine", "org.junit.jupiter:junit-jupiter-engine")
            }
        }
    }
}

include(
    "core",
    "components",
    "laf-support",
    "dsl",
    "settings",
    "visual-padding",
    "inspector",
    "util"
)

for (p in rootProject.children) {
    // Rename leaf projects only
    // E.g. we don't expect to publish examples as a Maven module
    when {
        p.children.isEmpty() -> p.name = rootProject.name + "-" + p.name
    }
}

fun property(name: String) =
    when (extra.has(name)) {
        true -> extra.get(name) as? String
        else -> null
    }

property("localDarklaf")?.ifBlank { "../darklaf" }?.let {
    println("Importing project '$it'")
    includeBuild(it)
}
