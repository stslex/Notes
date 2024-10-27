plugins {
    id("notes.android.library")
    id("notes.android.library.compose")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
}

android.namespace = "com.stslex93.notes.core.navigation"
