plugins {
    id("notes.android.library")
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

