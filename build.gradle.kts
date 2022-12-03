plugins {
    id("org.jetbrains.kotlinx.kover") version "0.5.0"
    kotlin("plugin.serialization") version "1.7.21"
    id("com.google.devtools.ksp") version "1.7.21-1.0.8"
}

buildscript {
    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}