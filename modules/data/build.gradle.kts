plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "data"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.bundles.ktor.android)
            implementation(libs.sqldelight.android)
        }

        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(projects.modules.core)
            implementation(libs.bundles.ktor.common)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        iosMain.dependencies {
            implementation(libs.bundles.ktor.ios)
            implementation(libs.sqldelight.native)
        }
    }
}

sqldelight {
    databases {
        create("WeatherUnionDatabase") {
            packageName.set("com.areeb.weatherunion.data")
            srcDirs("src/sqldelight")
        }
        linkSqlite = true
    }
}

android {
    namespace = "com.areeb.weatherunion.data"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
