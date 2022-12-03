plugins {
    id("notes.android.library")
    id("notes.android.library.compose")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))

    libs.apply {
        api(bundles.compose)
    }

    /*Drawer Layout*/
    api("com.google.android.material:material:1.7.0")
    api("androidx.constraintlayout:constraintlayout:2.1.4")
}

android.namespace = "com.stslex93.core_ui"