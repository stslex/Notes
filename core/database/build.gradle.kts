plugins {
    id("notes.android.library")
    alias(libs.plugins.robolectric.junit5)
}

dependencies {
    implementation(project(":core:core"))
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
    androidTestApi(libs.androidx.room.testing)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.google.gson)
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

