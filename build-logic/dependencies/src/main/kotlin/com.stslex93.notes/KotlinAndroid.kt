package com.stslex93.notes

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

@Suppress("UnstableApiUsage")
fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = 33

        defaultConfig {
            minSdk = 26

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            packagingOptions {
                with(resources.excludes) {
                    add("kotlin/internal/internal.kotlin_builtins")
                    add("kotlin/reflect/reflect.kotlin_builtins")
                    add("kotlin/kotlin.kotlin_builtins")
                    add("kotlin/coroutines/coroutines.kotlin_builtins")
                    add("kotlin/ranges/ranges.kotlin_builtins")
                    add("kotlin/collections/collections.kotlin_builtins")
                    add("kotlin/annotation/annotation.kotlin_builtins")
                }
            }

            vectorDrawables {
                useSupportLibrary = true
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
            isCoreLibraryDesugaringEnabled = true
        }

        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlin.Experimental"
            )
            jvmTarget = "11"
            sourceSets.all {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }

        packagingOptions {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }

        sourceSets {
            getByName("androidTest").assets.srcDir("$projectDir/schemas")
        }

        compileOptions {
            sourceCompatibility(JavaVersion.VERSION_11)
            targetCompatibility(JavaVersion.VERSION_11)
        }

        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
            getByName("debug") {
                isTestCoverageEnabled = true
            }
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
    }
}

private fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}