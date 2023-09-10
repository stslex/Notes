plugins {
    id("notes.android.application")
    id("notes.android.application.compose")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:database"))
    implementation(project(":core:notes"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:home"))
    implementation(project(":feature:edit"))
    implementation(project(":feature:edit-label"))
}

android.namespace = "com.stslex93.notes"
