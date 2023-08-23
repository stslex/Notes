plugins {
    id("notes.android.application")
    id("notes.android.application.compose")
}

android.namespace = "com.stslex93.notes"

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:database"))
    implementation(project(":core:notes"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:home"))
    implementation(project(":feature:edit"))
}
