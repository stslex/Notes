plugins {
    `kotlin-dsl`
}

group = "com.stslex93.notes.buildlogic"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
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
        register("androidTest") {
            id = "notes.android.test"
            implementationClass = "AndroidTestPlugin"
        }
        register("androidLibraryJacoco") {
            id = "notes.android.library.jacoco"
            implementationClass = "AndroidLibraryJacocoPlugin"
        }
        register("androidApplicationJacoco") {
            id = "notes.android.application.jacoco"
            implementationClass = "AndroidApplicationJacocoPlugin"
        }
    }
}