plugins {
    id("notes.android.library")
}

android.namespace = "com.stslex93.notes.core.label"

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:database"))

    implementation(libs.androidx.paging.runtime)
}