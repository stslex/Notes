pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Notes"

include(":app")
include(":core:ui")
include(":core:database")
include(":core:notes")
include(":feature:edit")
include(":feature:home")
include(":core:navigation")
include(":core:core")
include(":core:label")
include(":feature:edit-label")
