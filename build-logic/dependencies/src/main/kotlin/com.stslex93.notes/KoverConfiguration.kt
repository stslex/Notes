package com.stslex93.notes

import AppExt.findPluginId
import AppExt.libs

import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension

import org.gradle.api.Project

import org.gradle.kotlin.dsl.configure

internal fun Project.configureKover() {
    configure<KoverProjectExtension> {
        reports {
            total {
                html {
                    onCheck.value(true)
                    htmlDir.dir("${layout.buildDirectory}/reports/kover/html")
                }
            }
            filters.excludes {
                androidGeneratedClasses()
                classes(coverageExclusions)
                packages("*.di", "*.hilt_aggregated_deps", "build")
                annotatedBy("*Generated*", "*Composable")
                classes("*Dto", "*Entity", "*Response")
            }
        }
    }
}

private val coverageExclusions = listOf(
    "*.databinding.*",
    "*_Impl",
    "*_Factory",
    "*_MembersInjector",
    "Manifest*.*",
    "*Activity*.*",
    "*Fragment*.*",
    "*FXCMApp*.*",
    "*View*.*",
    "*Adapter*.*",
    "*View.class",
    "**/R",
    "*.R$*",
    "R",
    "R$*",
    "BuildConfig.*",
    "*.BuildConfig",
    "BuildConfig",
    "*_MembersInjector",
    "Dagger*Component",
    "Dagger*Component*",
    "*Module_*Factory",
    "*Application",
    "*Store"
)