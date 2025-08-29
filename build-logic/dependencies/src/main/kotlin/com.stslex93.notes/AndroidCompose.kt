package com.stslex93.notes

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {

        buildFeatures.compose = true

        dependencies {
            val composeBom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(composeBom))
            add("androidTestImplementation", platform(composeBom))

            val composeApi = libs.findBundle("compose").get()
            add("implementation", composeApi)

//            val composeManifest = libs.findBundle("androidx-compose-manifest").get()
//            add("debugImplementation", composeManifest)

            val tooling = libs.findLibrary("androidx-compose-tooling").get()
            add("debugImplementation", tooling)

            val composeTest = libs.findLibrary("androidx-compose-ui-test-junit4").get()
            add("androidTestImplementation", composeTest)

            val accompanist = libs.findBundle("accompanist").get()
            add("implementation", accompanist)

            val appcompat = libs.findLibrary("appcompat").get()
            add("implementation", appcompat)

            val material = libs.findLibrary("material").get()
            add("implementation", material)

            val lifecycle = libs.findBundle("lifecycle").get()
            add("implementation", lifecycle)
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
//            freeCompilerArgs.add(buildComposeMetricsParameters())
        }
    }
}
