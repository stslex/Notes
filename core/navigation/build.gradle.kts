plugins {
    id("notes.android.library")
    id("notes.android.library.compose")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
}
