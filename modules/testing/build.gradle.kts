import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ksp)
}

kotlin {
    tasks.create("testClasses")

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
                implementation(libs.bundles.ktor.common)
                implementation(libs.ktor.client.mock)
            }
            androidMain.dependencies {
                implementation(libs.datastore)
                implementation(libs.androidx.junit.ktx)
                implementation(libs.androidx.espresso.core)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.coroutines.test)
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
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
