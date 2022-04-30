plugins {
    id("com.android.application") version "7.0.2" apply false
    id("com.android.library") version "7.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
    kotlin("jvm") version "1.6.21" apply false
    kotlin("plugin.serialization") version "1.6.21"
}

buildscript {
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.6.21"))
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
        classpath("org.jacoco:org.jacoco.core:0.8.6")
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}