plugins {
    id("notes.android.library")
}

android.namespace = "com.stslex93.notes.core.notes"

dependencies {
    implementation(project(":core:database"))

    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
    androidTestApi(libs.androidx.room.testing)

    implementation(libs.androidx.paging.runtime)
}