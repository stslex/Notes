plugins {
    id("org.jetbrains.kotlinx.kover") version "0.7.6"
    alias(libs.plugins.application) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.library) apply false
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp) apply false
}

buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}