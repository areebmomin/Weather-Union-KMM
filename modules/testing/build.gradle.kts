import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "testing"
            isStatic = true
        }
    }

    sourceSets {
        sourceSets {
            commonMain.dependencies {
                api(projects.modules.core)
                api(projects.modules.data)
                api(projects.modules.logic)

                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlin.inject.runtime)
            }
            commonTest.dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.viewmodel)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.turbine)
                implementation(libs.kotlin.inject.runtime)
            }
        }
    }
}

dependencies {
    add("kspAndroid", libs.kotlin.inject.ksp)
    add("kspIosX64", libs.kotlin.inject.ksp)
    add("kspIosArm64", libs.kotlin.inject.ksp)
    add("kspIosSimulatorArm64", libs.kotlin.inject.ksp)
}

ksp {
    arg("me.tatarka.inject.generateCompanionExtensions", "true")
    arg("me.tatarka.inject.dumpGraph", "true")
}

android {
    namespace = "com.areeb.weatherunion.testing"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}