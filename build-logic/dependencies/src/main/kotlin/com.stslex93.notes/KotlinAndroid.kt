package com.stslex93.notes

import AppExt.APP_PREFIX
import AppExt.androidTestImplementationBundle
import AppExt.androidTestImplementationPlatform
import AppExt.coreLibraryDesugaring
import AppExt.findVersionInt
import AppExt.implementation
import AppExt.ksp
import AppExt.libs
import AppExt.testImplementationBundle
import AppExt.testImplementationPlatform
import AppExt.testRuntimeOnly
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.AppExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
): Unit = with(commonExtension) {

    compileSdk = libs.findVersionInt("compileSdk")

    namespace = getNameSpace(commonExtension)

    defaultConfig {
        minSdk = libs.findVersionInt("minSdk")
        buildFeatures.buildConfig = true
    }

    compileOptions {
        // Up to Java 11 APIs are available through desugaring
        // https://developer.android.com/studio/write/java11-minimal-support-table
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    configureKotlin()

    dependencies {
        coreLibraryDesugaring("android-desugarJdkLibs")

        testImplementationPlatform("junit-bom")
        androidTestImplementationPlatform("junit-bom")
        testRuntimeOnly("junit-launcher")
        testImplementationBundle("test")
        androidTestImplementationBundle("android-test")
        implementation(
            "kotlinx-collections-immutable",
            "dagger-core",
            "androidx-core-ktx"
        )
        ksp("dagger-compiler")
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }


    testOptions { unitTests.isIncludeAndroidResources = true }
}

private fun Project.getNameSpace(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
): String {
    val isApp = commonExtension is AppExtension
    val dropValue = if (isApp) 2 else 1
    val moduleName = path.split(":")
        .drop(dropValue)
        .joinToString(".")
        .replace("-", "_")
    return if (moduleName.isNotEmpty()) "$APP_PREFIX.$moduleName" else APP_PREFIX
}

private fun Project.configureKotlin() {
    // Use withType to workaround https://youtrack.jetbrains.com/issue/KT-55947
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            // Set JVM target to 11
            jvmTarget.set(JvmTarget.JVM_17)
            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors.set(warningsAsErrors?.toBoolean() ?: false)
            freeCompilerArgs.addAll("-opt-in=kotlin.RequiresOptIn", "-Xcontext-parameters")
        }
    }
}
