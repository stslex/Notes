plugins {
    id("notes.android.library")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:database"))

    implementation(libs.androidx.paging.runtime)
}