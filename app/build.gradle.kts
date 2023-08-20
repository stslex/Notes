plugins {
    id("notes.android.application")
    id("notes.android.application.compose")
    id("kotlin-kapt")
//    id("kotlin-kapt")
//    id("androidx.navigation.safeargs.kotlin")
//    id("kotlin-parcelize")
//    id("com.google.devtools.ksp")
}

android.namespace = "com.stslex93.notes"

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    /*Paging*/
    val pagingVersion = "3.1.1"
    implementation("androidx.paging:paging-runtime:$pagingVersion")

    /*Dagger 2*/
    val daggerVersion = "2.47"
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    /*Navigation*/
    val navigationVersion = "2.5.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    /*Room*/
    val roomVersion = "2.4.3"
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    val robolectricVersion = "4.9"
    api("org.robolectric:robolectric:$robolectricVersion")
    api("androidx.test:core-ktx:1.5.0")

    api("junit:junit:4.13.2")
    api("androidx.test.ext:junit:1.1.4")
    api("androidx.test.espresso:espresso-core:3.5.0")

    androidTestApi("androidx.room:room-testing:2.4.3")

//    libs.apply {
//        implementation(androidx.core.ktx)
//        implementation(bundles.ksp)
//        implementation(kotlinx.coroutines)
//        implementation(kotlin.stdlib)
//        implementation(androidx.appcompat)
//        implementation(bundles.compose)
//    }
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}
