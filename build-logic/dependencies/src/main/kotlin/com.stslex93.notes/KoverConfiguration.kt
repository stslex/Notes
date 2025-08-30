package com.stslex93.notes

import AppExt.findPluginId
import AppExt.libs
import org.gradle.api.Project

internal fun Project.configureKover() {
    pluginManager.apply(libs.findPluginId("kover"))
}