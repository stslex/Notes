plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.21" apply false
    id("org.jetbrains.kotlinx.kover") version "0.5.0"
    kotlin("jvm") version "1.6.20" apply false
    kotlin("plugin.serialization") version "1.7.21"
}

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.21")
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}