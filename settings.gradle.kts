pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Notes"
include(":app", ":core")
include(":feature-main")
include(":feature-note-edit")
include(":core-ui")
include(":core-data-notes")
include(":core-test")
