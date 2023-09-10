plugins {
    id("notes.android.library")
    id("notes.android.library.compose")
}

android.namespace = "com.stslex93.notes.feature.home"

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:notes"))
    implementation(project(":core:label"))
}