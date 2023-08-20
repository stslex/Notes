plugins {
    `kotlin-dsl`
}

group = "com.stslex93.notes.buildlogic"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.kotlin.serialization)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "notes.android.application.compose"
            implementationClass = "AndroidApplicationComposePlugin"
        }
        register("androidApplication") {
            id = "notes.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidLibraryCompose") {
            id = "notes.android.library.compose"
            implementationClass = "AndroidLibraryComposePlugin"
        }
        register("androidLibrary") {
            id = "notes.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
    }
}