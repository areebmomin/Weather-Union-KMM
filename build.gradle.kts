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
    id(libs.plugins.module.graph.get().pluginId) version libs.versions.module.graph
}

moduleGraphConfig {
    readmePath.set("./README.md")
    heading = "Weather Union KMM Module Graph"
    theme.set(Theme.FOREST)
}
