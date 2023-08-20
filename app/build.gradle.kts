plugins {
    id("notes.android.application")
    id("notes.android.application.compose")
}

android.namespace = "com.stslex93.notes"

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
    androidTestApi(libs.androidx.room.testing)

    implementation(libs.androidx.paging.runtime)

    implementation(libs.dagger.core)
    kapt(libs.dagger.compiler)

    // TODO remove after compose integration
    val navigationVersion = "2.5.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}
