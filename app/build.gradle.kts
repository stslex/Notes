plugins {
    id("notes.android.application")
    id("notes.android.application.compose")
}

android.namespace = "com.stslex93.notes"

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:database"))
    implementation(project(":core:notes"))

    implementation(project(":feature:edit"))

    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
    androidTestApi(libs.androidx.room.testing)

    implementation(libs.androidx.paging.runtime)

    // TODO remove after compose integration
    val navigationVersion = "2.5.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}
