pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "advent-of-code-2023"
include("star-1")
include("star-2")
include("star-3")
include("star-4")
include("star-5")
