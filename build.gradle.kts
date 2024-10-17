import dev.iurysouza.modulegraph.Theme

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.sqlDelight) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.wire) apply false
    id("org.jetbrains.dokka") version "1.9.20"
    id(libs.plugins.module.graph.get().pluginId) version libs.versions.module.graph
}

buildscript {
    dependencies {
        classpath(libs.maps.secrets.gradle.plugin)
    }
}

moduleGraphConfig {
    readmePath.set("./README.md")
    heading = "Weather Union KMM Module Graph"
    theme.set(Theme.FOREST)
}

subprojects {
    plugins.apply("org.jetbrains.dokka")
}
