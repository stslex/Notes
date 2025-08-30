plugins {
    id("notes.android.application")
    id("notes.android.application.compose")
}

dependencies {
    implementWithCover(":core:core")
    implementWithCover(":core:database")
    implementWithCover(":core:label")
    implementWithCover(":core:navigation")
    implementWithCover(":core:notes")
    implementWithCover(":core:ui")
    implementWithCover(":feature:edit")
    implementWithCover(":feature:edit-label")
    implementWithCover(":feature:home")
}

fun DependencyHandler.implementWithCover(name: String) {
    val project = project(name)
    implementation(project)
    kover(project)
}
