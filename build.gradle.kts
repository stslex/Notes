plugins {
    id("com.android.application") version "7.0.2" apply false
    id("com.android.library") version "7.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.6.20" apply false
    kotlin("jvm") version "1.6.20"
    kotlin("plugin.serialization") version "1.6.20"
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}